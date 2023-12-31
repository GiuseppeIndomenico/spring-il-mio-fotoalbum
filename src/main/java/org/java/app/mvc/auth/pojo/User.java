package org.java.app.mvc.auth.pojo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.java.app.db.pojo.Message;
import org.java.app.db.pojo.Photo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

@Entity
public class User implements UserDetails, Serializable  {

	 private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, unique = true)
	@NotNull
	private String username;

	@Column(nullable = false)
	@NotNull
	private String password;

	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Role> roles;
	
	@OneToMany(mappedBy = "user")
	@JsonManagedReference
	private List<Photo> Photos;
	
    @OneToMany(mappedBy = "user")
    @JsonIgnore 
    private List<Message> messages;


	public User() {
	}

	public User(String username, String password, Role... roles) {
	    setUsername(username);
	    setPassword(password);
	    setRoles(new HashSet<>(Arrays.asList(roles)));
	    
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	
	
	public List<Photo> getPhotos() {
		return Photos;
	}

	public void setPhotos(List<Photo> photos) {
		Photos = photos;
	}

	@Override
	public String toString() {

		return "[" + getId() + "] " + getUsername();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getName())).toList();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}

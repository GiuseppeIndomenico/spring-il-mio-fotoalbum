package org.java.app.db.pojo;

import java.util.Arrays;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.Length;
import org.java.app.api.dto.PhotoDTO;
import org.java.app.mvc.auth.pojo.User;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "photos")
public class Photo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 128, nullable = false)
	@Length(min = 3, max = 128, message = "Il titolo deve essere di almeno tre caratteri")
	private String title;

	private String description;

	@Column(nullable = false)
	private String url;

	@ColumnDefault("1")
	private boolean visible;

	@ManyToMany
	@JsonManagedReference
	private List<Category> categories;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private User user;
	
	@JsonProperty("user_id")
	public int getUserId() {
	    if (user != null) {
	        return user.getId();
	    }
	    return 0; // O qualsiasi valore di default desiderato se user è null
	}


	public Photo() {
	}

	public Photo(String title, String description, String url, boolean visible, User user, Category... categories) {

		setTitle(title);
		setDescription(description);
		setUrl(url);
		setVisible(visible);
		setCategories(Arrays.asList(categories));
		setUser(user);
	}

	public Photo(PhotoDTO photoDto) {
		setTitle(photoDto.getTitle());
		setDescription(photoDto.getDescription());
		setUrl(photoDto.getUrl());
		setVisible(photoDto.isVisible());
	}

	public void fillFromPhotoDto(PhotoDTO photoDto) {
		setTitle(photoDto.getTitle());
		setDescription(photoDto.getDescription());
		setUrl(photoDto.getUrl());
		setVisible(photoDto.isVisible());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}

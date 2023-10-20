package org.java.app.db.pojo;

import org.java.app.mvc.auth.pojo.User;

import com.fasterxml.jackson.annotation.JsonIdentityReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIdentityReference(alwaysAsId = true)
	private User user;

	private String email;

	private String messageContent;

	public Message() {
	}

	public Message(User user, String email, String messageContent) {
		setEmail(email);
		setUser(user);
		setMessageContent(messageContent);

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", user=" + user + ", email=" + email + ", messageContent=" + messageContent
				+ ", getId()=" + getId() + ", getUser()=" + getUser() + ", getEmail()=" + getEmail()
				+ ", getMessageContent()=" + getMessageContent() + "]";
	}

}

package org.java.app.api.dto;

public class PhotoDTO {

	private String title;

	private String url;

	private String description;

	private boolean visible;

	public PhotoDTO() {
	}

	public PhotoDTO(String title, String description, String url, boolean visible) {
		setVisible(visible);
		setTitle(title);
		setDescription(description);
		setUrl(url);
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "PhotoDTO [title=" + title + ", url=" + url + ", description=" + description + ", visible=" + visible
				+ ", isVisible()=" + isVisible() + ", getTitle()=" + getTitle() + ", getUrl()=" + getUrl()
				+ ", getDescription()=" + getDescription() + "]";
	}
	
	

}

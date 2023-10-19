package org.java.app.db.serv;

import java.util.List;
import java.util.Optional;

import org.java.app.db.pojo.Photo;
import org.java.app.db.repo.PhotoRepo;
import org.java.app.mvc.auth.pojo.User;
import org.java.app.mvc.auth.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoServ {

	@Autowired
	private PhotoRepo photoRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	public Photo save (Photo photo) {
		return photoRepo.save(photo);
	}
	
	public List<Photo> findAll() {
		return photoRepo.findAll();
	}

	public List<Photo> findPhotosByTitle(String title) {
		return photoRepo.findByTitleContaining(title);
	}

	public void deletePhoto(Photo photo) {
		photoRepo.delete(photo);
	}

	public Optional<Photo> findById(int id) {
		return photoRepo.findById(id);
	}
	
	public List<Photo> findPhotosByUsername(String username) {
	    User user = userRepo.findByUsername(username);
	    return user.getPhotos();
	}

	
	
	
}

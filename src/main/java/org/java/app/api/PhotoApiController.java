package org.java.app.api;

import java.util.List;
import java.util.Optional;

import org.java.app.api.dto.PhotoDTO;
import org.java.app.db.pojo.Photo;
import org.java.app.db.serv.PhotoServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/photos")
public class PhotoApiController {

	@Autowired
	private PhotoServ photoServ;

	@GetMapping
	public ResponseEntity<List<Photo>> getAll() {
		List<Photo> photos = photoServ.findAll();
		return new ResponseEntity<>(photos, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Photo> save(@RequestBody PhotoDTO photoDto) {
		Photo photo = new Photo(photoDto);
		photo = photoServ.save(photo);
		return new ResponseEntity<>(photo, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<Photo> getphoto(@PathVariable int id) {
		Optional<Photo> optPhoto = photoServ.findById(id);
		if (optPhoto.isPresent()) {
			return new ResponseEntity<>(optPhoto.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("{id}")
	public ResponseEntity<Photo> updatephoto(@PathVariable int id, @RequestBody PhotoDTO photoDto) {

		Optional<Photo> optphoto = photoServ.findById(id);

		if (optphoto.isEmpty()) {

			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		Photo photo = optphoto.get();
		photo.fillFromPhotoDto(photoDto);

		try {

			photo = photoServ.save(photo);

			return new ResponseEntity<>(photo, HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Boolean> deletephoto(@PathVariable int id) {
		Optional<Photo> optPhoto = photoServ.findById(id);
		if (optPhoto.isPresent()) {
			photoServ.deletePhoto(optPhoto.get());
			return new ResponseEntity<>(true, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
		}
	}

}

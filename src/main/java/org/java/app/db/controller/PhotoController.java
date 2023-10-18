package org.java.app.db.controller;

import java.util.List;

import org.java.app.db.pojo.Photo;
import org.java.app.db.serv.PhotoServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/photos")
public class PhotoController {

	@Autowired
	private PhotoServ photoServ;

	@GetMapping
	public String getIndex(Model model) {

		List<Photo> photos = photoServ.findAll();

		model.addAttribute("photos", photos);
		return "photo-index";
	}

	@GetMapping("{id}")
	public String getShow(@PathVariable int id, Model model) {
		Photo photo = photoServ.findById(id).orElse(null);

		if (photo != null) {
			model.addAttribute("photo", photo);
			return "photo-show";
		} else {
			return "photo-not-found";
		}

	}
	
	@GetMapping("/search")
	public String searchPhotoByTitle(@RequestParam(name = "title") String title, Model model) {
		List<Photo> photos= photoServ.findPhotosByTitle(title);
		model.addAttribute("photos", photos);
		
		return"photo-index";
	}
}

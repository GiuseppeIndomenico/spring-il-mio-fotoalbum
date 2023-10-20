package org.java.app.db.controller;

import java.util.List;

import org.java.app.db.pojo.Category;
import org.java.app.db.pojo.Message;
import org.java.app.db.pojo.Photo;
import org.java.app.db.serv.CategoryServ;
import org.java.app.db.serv.MessageServ;
import org.java.app.db.serv.PhotoServ;
import org.java.app.mvc.auth.pojo.User;
import org.java.app.mvc.auth.repo.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/photos")
public class PhotoController {

	@Autowired
	private PhotoServ photoServ;

	@Autowired
	private CategoryServ categoryServ;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private MessageServ messageServ;

	@GetMapping
	public String getIndex(Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String loggedInUsername = userDetails.getUsername();
		User loggedInUser = userRepo.findByUsername(loggedInUsername);

		model.addAttribute("username", loggedInUsername);
		
		List<Message> messages = messageServ.findMessagesByUserId(loggedInUser.getId());
		model.addAttribute("messages", messages);

		List<Photo> photos;

		if (userDetails.getAuthorities().stream()
				.anyMatch(a -> a.getAuthority().equals("SuperMegaDirettoreGalattico"))) {
			photos = photoServ.findAll();
		} else {
			photos = photoServ.findPhotosByUsername(loggedInUsername);
		}

		model.addAttribute("photos", photos);
		return "photo-index";
	}

	@GetMapping("{id}")
	public String getShow(@PathVariable int id, Model model) {
		Photo photo = photoServ.findById(id).orElse(null);

		if (photo != null) {
			User user = photo.getUser(); 
			String username = (user != null) ? user.getUsername() : "Utente sconosciuto"; 
			model.addAttribute("photo", photo);
			model.addAttribute("username", username);
			return "photo-show";
		} else {
			return "photo-not-found";
		}
	}

	@GetMapping("/search")
	public String searchPhotoByTitle(@RequestParam(name = "title") String title, Model model) {
		List<Photo> photos = photoServ.findPhotosByTitle(title);
		model.addAttribute("photos", photos);

		return "photo-index";
	}

	@GetMapping("/create")
	public String getCreateForm(Model model) {
		List<Category> categories = categoryServ.findAll();

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String loggedInUsername = userDetails.getUsername();
		User user = userRepo.findByUsername(loggedInUsername);

		model.addAttribute("categories", categories);
		model.addAttribute("user", user);
		model.addAttribute("photo", new Photo());

		return "photo-create";
	}

	@PostMapping("/create")
	public String storePhoto(@Valid @ModelAttribute Photo photo, Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String loggedInUsername = userDetails.getUsername();

		User user = userRepo.findByUsername(loggedInUsername);

		photo.setUser(user);

		photoServ.save(photo);

		return "redirect:/photos";
	}

	@GetMapping("/update/{id}")
	public String getUpdateForm(@PathVariable int id, Model model) {

		Photo photo = photoServ.findById(id).orElse(null);

		if (photo != null) {
			List<Category> categories = categoryServ.findAll();
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			String loggedInUsername = userDetails.getUsername();
			User user = userRepo.findByUsername(loggedInUsername);

			model.addAttribute("categories", categories);
			model.addAttribute("user", user);
			model.addAttribute("photo", photo);

			return "photo-update";
		} else {

			return "photo-not-found";
		}
	}

	@PostMapping("/update/{id}")
	public String updatePhoto(@PathVariable int id, @Valid @ModelAttribute Photo updatedPhoto) {

		if (updatedPhoto.getId() == id) {

			photoServ.save(updatedPhoto);
			return "redirect:/photos";
		} else {

			return "photo-not-found";
		}
	}

	@PostMapping("/delete/{id}")
	public String deletePhoto(@PathVariable int id) {
		Photo photo = photoServ.findById(id).orElse(null);

		if (photo != null) {
			photoServ.deletePhoto(photo);
			return "redirect:/photos";
		} else {
			return "photo-not-found";
		}
	}

}

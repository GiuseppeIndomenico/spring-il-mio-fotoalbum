package org.java.app.db.controller;

import java.util.List;

import org.java.app.db.pojo.Category;
import org.java.app.db.serv.CategoryServ;
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

@Controller
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryServ categoryServ;

	@GetMapping
	public String getIndex(Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String loggedInUsername = userDetails.getUsername();
		model.addAttribute("username", loggedInUsername);
		List<Category> categories = categoryServ.findAll();
		model.addAttribute("categories", categories);
		model.addAttribute("newCategory", new Category());
		return "category-index";
	}

	@PostMapping
	public String createCategory(@ModelAttribute Category newCategory, Model model) {
		categoryServ.save(newCategory);
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String loggedInUsername = userDetails.getUsername();
		model.addAttribute("username", loggedInUsername);

		List<Category> categories = categoryServ.findAll();
		model.addAttribute("categories", categories);

		return "category-index";
	}

	@PostMapping("/delete/{id}")
	public String deleteCategory(@PathVariable int id) {
		Category category = categoryServ.findById(id);

		categoryServ.delete(category);
		return "redirect:/categories";

	}

}

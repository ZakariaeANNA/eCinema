package com.hendisantika.controller;

import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hendisantika.entity.Genre;
import com.hendisantika.service.GenreService;

@RequestMapping(value = "genre")
@Controller
public class GenreController {
	private GenreService genreService;

	/*
	 * public GenreService getGenreService() { return genreService; }
	 */
	@Autowired
	public void setGenreService(GenreService filmService) {
		this.genreService = filmService;;
	}
	
	@GetMapping
    public String index() {
        return "redirect:/genre/1";
    }
	@GetMapping(value = "/{pageNumber}")
	public String list(@PathVariable Integer pageNumber, Model model) {
		Page<Genre> page = genreService.getList(pageNumber);

		int current = page.getNumber() + 1;
		int begin = Math.max(1, current - 5);
		int end = Math.min(begin + 10, page.getTotalPages());

		model.addAttribute("listGenrelites", page);
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		return "parametre/genre/listNG";

	}

	@GetMapping("/add")
	public String add(Model model) {
		
		List<Genre> allGenres = new ArrayList<Genre>();
		allGenres = genreService.getListAll();

		model.addAttribute("listeGenres", allGenres);
		model.addAttribute("genre", new Genre());
		return "parametre/genre/form";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {

		List<Genre> allGenres = new ArrayList<Genre>();
		allGenres = genreService.getListAll();

		model.addAttribute("listeGenres", allGenres);
		model.addAttribute("genre", genreService.get(id));
		return "parametre/genre/form";

	}

	@PostMapping(value = "/save")
	public String save(Genre genre, final RedirectAttributes ra) {

		Genre save = genreService.save(genre);
		ra.addFlashAttribute("successFlash", "Genre foi salvo com sucesso.");
		return "redirect:/genre";

	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {

		genreService.delete(id);
		return "redirect:/genre";

	}

	@GetMapping("/show")
	public String show(Model model) {
		List<Genre> page = genreService.getListAll();
		model.addAttribute("listGenre", page);
		return "genre/list";

	}

}

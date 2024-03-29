package com.hendisantika.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.sql.Time;

import com.hendisantika.entity.Film;
import com.hendisantika.entity.Salle;
import com.hendisantika.entity.Seance;
import com.hendisantika.service.FilmService;
import com.hendisantika.service.SalleService;
import com.hendisantika.service.SeanceService;

@Controller
@RequestMapping("seance")
public class SeanceController {

	private SeanceService seanceService;
	private SalleService salleService;
	private FilmService filmService;

	@Autowired
	public void setSeanceService(FilmService filmService, SeanceService seanceService, SalleService salleService) {
		this.seanceService = seanceService;
		this.salleService = salleService;
		this.filmService = filmService;
	}

	@GetMapping
	public String index() {
		return "redirect:/seance/1";
	}

	@GetMapping(value = "/{pageNumber}")
	public String list(@PathVariable Integer pageNumber, Model model) {
		Page<Seance> page = seanceService.getList(pageNumber);
		System.out.println("Taille de la page : ");
		int current = page.getNumber() + 1;
		int begin = Math.max(1, current - 5);
		int end = Math.min(begin + 10, page.getTotalPages());
		
		model.addAttribute("listSeances", page);
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		
		return "seance/list";

	}

	@GetMapping("/add")
	public String add(Model model) {
		List<Salle> lesSalles = salleService.getListAll();
		List<Film> lesFilms = filmService.getListAll();
		model.addAttribute("lesFilms", lesFilms);
		model.addAttribute("lesSalles", lesSalles);
		model.addAttribute("seance", new Seance());
		return "seance/form";

	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		List<Salle> lesSalles = salleService.getListAll();
		List<Film> lesFilms = filmService.getListAll();
		model.addAttribute("lesFilms", lesFilms);
		model.addAttribute("lesSalles", lesSalles);
		model.addAttribute("seance", seanceService.get(id));
		return "seance/form";
	}

	@PostMapping(value = "/save")
	public String save(Seance seance, final RedirectAttributes ra, Model model) {
		System.out.println(
				seanceService.isRoomFree(seance.getDateProjection(), seance.getHeureDebut(), seance.getHeureFin()));
		if (!seanceService.isRoomFree(seance.getDateProjection(), seance.getHeureDebut(), seance.getHeureFin())) {
			Seance save = seanceService.save(seance);
		} else {
			ra.addFlashAttribute("error", "The room is already reserved in this periode of time ");
			return "redirect:/seance/add";
		}
		ra.addFlashAttribute("successFlash", "Seance Ajoutée avec succès");
		return "redirect:/seance";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {

		seanceService.delete(id);
		return "redirect:/seance";

	}

	@GetMapping("/show/list")
	public String showSeances(Model model) {
		List<Seance> lesSeances = seanceService.getListAll();
		model.addAttribute("listSeances", lesSeances);
		return "/seance/list";

	}

	@GetMapping(path = "/NG/listp", produces = "application/json")
	public @ResponseBody List<Seance> getAllPersons() {
		List<Seance> allSeances = new ArrayList<Seance>();
		allSeances = seanceService.getListAll();
		return allSeances;
	}
}

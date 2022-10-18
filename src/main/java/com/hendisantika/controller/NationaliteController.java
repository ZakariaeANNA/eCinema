package com.hendisantika.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hendisantika.entity.Nationalite;
import com.hendisantika.service.NationaliteService;


@RequestMapping(value = "nationalite")
@Controller
public class NationaliteController {
	private NationaliteService nationaliteService;
	@Autowired
	public void setNatinaliteService(NationaliteService filmService) {
		this.nationaliteService = filmService;

	}

	@GetMapping
	public String index() {
		return "redirect:/nationalite/1";
	}

	@GetMapping(value = "/{pageNumber}")
	public String list(@PathVariable Integer pageNumber, Model model) {
		Page<Nationalite> page = nationaliteService.getList(pageNumber);

		int current = page.getNumber() + 1;
		int begin = Math.max(1, current - 5);
		int end = Math.min(begin + 10, page.getTotalPages());

		model.addAttribute("listNationalites", page);
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);

		return "parametre/nationalite/list";

	}

	@GetMapping("/add")
	public String add(Model model) {
		
		List<Nationalite> allNationalites = new ArrayList<Nationalite>();
		allNationalites = nationaliteService.getListAll();

		model.addAttribute("listeNationalites", allNationalites);
		model.addAttribute("nationalite", new Nationalite());
		return "parametre/nationalite/form";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {

		List<Nationalite> allNationalites = new ArrayList<Nationalite>();
		allNationalites = nationaliteService.getListAll();

		model.addAttribute("listeNationalites", allNationalites);
		model.addAttribute("nationalite", nationaliteService.get(id));
		return "parametre/nationalite/form";

	}

	@PostMapping(value = "/save")
	public String save(Nationalite nationalite, final RedirectAttributes ra) {

		Nationalite save = nationaliteService.save(nationalite);
		ra.addFlashAttribute("successFlash", "Nationalite foi salvo com sucesso.");
		return "redirect:/nationalite";

	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {

		nationaliteService.delete(id);
		return "redirect:/nationalite";

	}

	@GetMapping("/show")
	public String show(Model model) {
		List<Nationalite> page = nationaliteService.getListAll();
		model.addAttribute("listNationalite", page);
		return "nationalite/list";

	}
}

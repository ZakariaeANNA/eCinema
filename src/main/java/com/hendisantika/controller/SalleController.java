package com.hendisantika.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hendisantika.entity.Salle;
import com.hendisantika.service.SalleService;

@Controller
@RequestMapping("salle")
public class SalleController {

	private SalleService salleService;

	@Autowired
	public void setSalleService(SalleService salleService) {
		this.salleService = salleService;
	}

	@GetMapping
	public String index() {
		return "redirect:/salle/show/list";
	}

	@GetMapping(value = "/{pageNumber}")
	public String list(@PathVariable Integer pageNumber, Model model) {
		Page<Salle> page = salleService.getList(pageNumber);
		System.out.println("Taille de la page : ");
		int current = page.getNumber() + 1;
		int begin = Math.max(1, current - 5);
		int end = Math.min(begin + 10, page.getTotalPages());

		model.addAttribute("listSalles", page);
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);

		return "salle/list";

	}

	@GetMapping("/add")
	public String add(Model model) {

		model.addAttribute("salle", new Salle());
		return "salle/form";

	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {

		model.addAttribute("personne", salleService.get(id));
		return "salle/form";

	}

	@PostMapping(value = "/save")
	public String save(Salle salle, final RedirectAttributes ra) {

		Salle save = salleService.save(salle);
		ra.addFlashAttribute("successFlash", "Salle Ajoutée avec succès");
		return "redirect:/salle";

	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {

		salleService.delete(id);
		return "redirect:/salle";

	}

	@GetMapping("/show/list")
	public String showSalles(Model model) {
		List<Salle> lesSalles = salleService.getListAll();
		model.addAttribute("listSalles",lesSalles);
		return "/salle/list";

	}

	@GetMapping(path = "/NG/listp", produces = "application/json")
	public @ResponseBody List<Salle> getAllPersons() {
		List<Salle> allSalles = new ArrayList<Salle>();
//    	for (int i = 0; i < 5; i++) {
//    		Personne p = new Personne();
//    		p.setId((long) i);
//    		p.setNom("Nom_"+i);
//    		p.setPrenom("Prenom _"+i);
//    		p.setTypePersonne("Type_"+1);
//    		allPersons.add(p);
//		}
		allSalles = salleService.getListAll();
		return allSalles;
	}

	@RequestMapping(value = "/ng/get", params = { "page", "size" }, method = RequestMethod.GET)
	public @ResponseBody Page<Salle> findPaginated(@RequestParam("page") int page, @RequestParam("size") int size) {

		Page<Salle> resultPage = salleService.findPaginated(page, size);
		if (page > resultPage.getTotalPages()) {
			//throw new MyResourceNotFoundException();
			System.err.println("Numero de page incorrect");
		}

		return resultPage;
	}
}

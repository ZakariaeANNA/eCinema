package com.hendisantika.controller;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hendisantika.entity.Genre;
import com.hendisantika.entity.Nationalite;
import com.hendisantika.service.GenreService;
import com.hendisantika.service.NationaliteService;

@Controller
@RequestMapping("parametre")
public class ParametreController {

    private GenreService genreService;
    private NationaliteService nationaliteService;


    @Autowired
    public void setGenreService(GenreService genreService) {
        this.genreService = genreService;
    }
    
    @Autowired
    public void setNationaliteService(NationaliteService nationaliteService) {
        this.nationaliteService = nationaliteService;
    }

    @GetMapping
    public String index() {
        return "redirect:parametre/nationalite/1";
    }

    @GetMapping(value = "nationalite/{pageNumber}")
    public String listNationalites(@PathVariable Integer pageNumber, Model model) {
        Page<Nationalite> page = nationaliteService.getList(pageNumber);
        System.out.println("Taille de la page : ");
        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, page.getTotalPages());

        model.addAttribute("listNationalites", page);
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);

        return "parametre/nationalite/list";

    }
    
    @GetMapping(value = "genre/{pageNumber}")
    public String listGenres(@PathVariable Integer pageNumber, Model model) {
        Page<Genre> page = genreService.getList(pageNumber);
        System.out.println("Taille de la page : ");
        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, page.getTotalPages());

        model.addAttribute("listGenres", page);
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);

        return "parametre/genre/list";

    }

    @GetMapping("nationalite/action/add")
    public String addNationalite(Model model) {

        model.addAttribute("nationalite", new Nationalite());
        return "parametre/nationalite/form";

    }
    
    @GetMapping("genre/action/add")
    public String addGenre(Model model) {

        model.addAttribute("genre", new Genre());
        return "parametre/genre/form";

    }

    @GetMapping("nationalite/edit/{id}")
    public String editNationalite(@PathVariable Long id, Model model) {

        model.addAttribute("nationalite", nationaliteService.get(id));
        return "parametre/nationalite/form";

    }
    
    @GetMapping("genre/edit/{id}")
    public String editGenre(@PathVariable Long id, Model model) {

        model.addAttribute("genre", genreService.get(id));
        return "parametre/genre/form";

    }

    @PostMapping(value = "nationalite/save")
    public String save(Nationalite nationalite, final RedirectAttributes ra) {

    	Nationalite save = nationaliteService.save(nationalite);
        ra.addFlashAttribute("successFlash", "Personne Ajoutée avec succès");
        return "redirect:/parametre/nationalite/1";

    }
    
    @PostMapping(value = "genre/save")
    public String saveGenre(Genre genre, final RedirectAttributes ra) {

    	Genre save = genreService.save(genre);
        ra.addFlashAttribute("successFlash", "Genre Ajouté avec succès");
        return "redirect:/parametre/genre";

    }

    @GetMapping("nationalite/delete/{id}")
    public String deleteNationalite(@PathVariable Long id) {

    	nationaliteService.delete(id);
        return "redirect:/parametre/nationalite/1";

    }
    
    @GetMapping("genre/delete/{id}")
    public String deleteGenre(@PathVariable Long id) {

    	genreService.delete(id);
        return "redirect:/parametre/genre/1";

    }
    
//    @GetMapping("/show/list")
//    public String showPersons() {
//
//        return "/personne/listNG";
//
//    }
//    
//    @GetMapping(path="/NG/listp", produces = "application/json")
//    public @ResponseBody List<Personne> getAllPersons() {
//    	List<Personne> allPersons = new ArrayList<Personne>();
//    	for (int i = 0; i < 5; i++) {
//    		Personne p = new Personne();
//    		p.setId((long) i);
//    		p.setNom("Nom_"+i);
//    		p.setPrenom("Prenom _"+i);
//    		p.setTypePersonne("Type_"+1);
//    		allPersons.add(p);
//		}
//    	allPersons = personneService.getListAll();
//        return allPersons;
//    }
    
    
    @GetMapping(value = "genreNG")
    public String listGenresNG() {
        return "parametre/genre/listNG";
    }

    @GetMapping(path="genreNG/listGenre", produces = "application/json")
    public @ResponseBody List<Genre> getAllGenres() {
    	List<Genre> allGenres = new ArrayList<Genre>();
    	allGenres = genreService.getListAll();
        return allGenres;
    }

    @GetMapping(value = "genreNG/{pageNumber}", produces = "application/json")
    public  @ResponseBody List<Genre> listGenresNG(@PathVariable Integer pageNumber) {
        Page<Genre> page = genreService.getList(pageNumber);
        System.out.println("Taille de la page : ");
        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, page.getTotalPages());

//        model.addAttribute("listGenres", page);
//        model.addAttribute("beginIndex", begin);
//        model.addAttribute("endIndex", end);
//        model.addAttribute("currentIndex", current);

        return page.toList();

    }
}

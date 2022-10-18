package com.hendisantika.controller;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.hendisantika.entity.Film;
import com.hendisantika.entity.Genre;
import com.hendisantika.entity.Nationalite;
import com.hendisantika.entity.Personne;
import com.hendisantika.service.FilmService;
import com.hendisantika.service.GenreService;
import com.hendisantika.service.NationaliteService;
import com.hendisantika.service.PersonneService;
import com.hendisantika.util.AddActors;
import com.hendisantika.util.FileUploadUtil;

@RequestMapping(value = "film")
@Controller
public class FilmController {

	private FilmService filmService;
	private GenreService genreService;
	private NationaliteService nationaliteService;
	private PersonneService personneService;

	@Autowired
	public void setFilmService(FilmService filmService) {
		this.filmService = filmService;

	}

	@Autowired
	public void setGenreService(GenreService genreService) {
		this.genreService = genreService;
	}

	@Autowired
	public void setNationalteService(NationaliteService nationaliteService) {
		this.nationaliteService = nationaliteService;
	}

	@Autowired
	public void setPersonneService(PersonneService personneService) {
		this.personneService = personneService;
	}

	@GetMapping
	public String index() {
		return "redirect:/film/1";
	}

	@GetMapping(value = "/{pageNumber}")
	public String list(@PathVariable Integer pageNumber, Model model) {
		Page<Film> page = filmService.getList(pageNumber);

		int current = page.getNumber() + 1;
		int begin = Math.max(1, current - 5);
		int end = Math.min(begin + 10, page.getTotalPages());

		model.addAttribute("listFilm", page);
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);

		return "film/list";

	}

	@GetMapping("/add")
	public String add(Model model) {

		List<Personne> allPersonnes = new ArrayList<Personne>();
		allPersonnes = personneService.getRealisateurs();
		List<Genre> allGenres = new ArrayList<Genre>();
		allGenres = genreService.getListAll();
		List<Nationalite> allNationalites = new ArrayList<Nationalite>();
		allNationalites = nationaliteService.getListAll();
		List<Personne> lesActeurs = new ArrayList<Personne>();
		lesActeurs = personneService.getActeurs();
		
		model.addAttribute("listActeurs", lesActeurs);
		model.addAttribute("listeNationalites", allNationalites);
		model.addAttribute("listegenres", allGenres);
		model.addAttribute("listerealisateurs", allPersonnes);
		model.addAttribute("film", new Film());
		return "film/form";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		List<Personne> allPersonnes = new ArrayList<Personne>();
		allPersonnes = personneService.getRealisateurs();
		List<Genre> allGenres = new ArrayList<Genre>();
		allGenres = genreService.getListAll();
		List<Nationalite> allNationalites = new ArrayList<Nationalite>();
		allNationalites = nationaliteService.getListAll();
		List<Personne> lesActeurs = new ArrayList<Personne>();
		lesActeurs = personneService.getActeurs();

		model.addAttribute("listeNationalites", allNationalites);
		model.addAttribute("listegenres", allGenres);
		model.addAttribute("listerealisateurs", allPersonnes);
		model.addAttribute("listActeurs", lesActeurs);
		model.addAttribute("film", filmService.get(id));
		return "film/form";
	}

	@PostMapping(value = "/save")
	public String save(@RequestParam("file") MultipartFile file, Film film,@RequestParam("acteur") String acteur,final RedirectAttributes ra) {
		if (!file.isEmpty()) {
			// normalize the file path
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			String uuid = UUID.randomUUID().toString();
			String uploadDir = "covers\\";
			FileUploadUtil.saveFile(uploadDir, uuid + fileName, file);
			film.setCover("/photos/covers/" + uuid + fileName);
		}
		List<Personne> acteurs = AddActors.stringToPersonne(acteur,personneService);
		film.setActeurs(acteurs);
		Film save = filmService.save(film);
		ra.addFlashAttribute("successFlash", "Film foi salvo com sucesso.");
		return "redirect:/film";

	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {

		filmService.delete(id);
		return "redirect:/film";

	}

	@GetMapping("/show")
	public String show(Model model) {
		List<Film> page = filmService.getListAll();
		model.addAttribute("listFilms", page);
		return "film/list";

	}
    
    @GetMapping("/details/{id}")
    public String showDetails(@PathVariable Long id, Model model) {
    	Film film = filmService.get(id);
    
		Time date = new Time(film.getDuree()*60*1000);
		System.out.print(date);
		String formattedDate = new SimpleDateFormat("HH:mm:ss").format(date);
		model.addAttribute("time",formattedDate);
        model.addAttribute("film",film);
        return "film/details";

    }
//    @GetMapping("/getrecentmovies")
//    public String getRecentMovies() {
//    	List<Film> allFilms = filmService.getListAll();
//    	
//    	List<Film> recentFilms = new ArrayList<Film>();
//    	
//    	return "/dashboard";
//    }
   
}
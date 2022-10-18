package com.hendisantika.controller;



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

import com.hendisantika.entity.Personne;
import com.hendisantika.service.NationaliteService;
import com.hendisantika.service.PersonneService;
import com.hendisantika.util.FileUploadUtil;

@Controller
@RequestMapping("personne")
public class PersonneController {

    private PersonneService personneService;
    private NationaliteService natService;

    @Autowired
    public void setPersonneService(PersonneService personneService) {
        this.personneService = personneService;
    }
    
    @Autowired
    public void setNatService(NationaliteService natService) {
        this.natService = natService;
    }

    @GetMapping
    public String index() {
        return "redirect:/personne/1";
    }

    @GetMapping(value = "/{pageNumber}")
    public String list(@PathVariable Integer pageNumber, Model model) {
        Page<Personne> page = personneService.getList(pageNumber);
       	List<Personne> allPersons = new ArrayList<Personne>();
    	allPersons = personneService.getListAll();
        allPersons.stream().forEach(e->{
    		System.out.println(e.getNom());
    		System.out.println(e.getFilms().size());
    	});
        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, page.getTotalPages());
        model.addAttribute("listPersonnes", page);
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);

        return "personne/list";

    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("personne", new Personne());
        model.addAttribute("listeNationalites", natService.getListAll());
        return "personne/form";

    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("personne", personneService.get(id));
        model.addAttribute("listeNationalites", natService.getListAll());
        return "personne/form";

    }

    @PostMapping(value = "/save")
    public String save(@RequestParam("file") MultipartFile file,Personne personne, final RedirectAttributes ra) {
    	//check if is there a file
    	if (!file.isEmpty()) {
    		// normalize the file path
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            String uuid = UUID.randomUUID().toString();
			String uploadDir = "personnes\\";
			FileUploadUtil.saveFile(uploadDir, uuid + fileName, file);
			personne.setPhoto("/photos/personnes/" + uuid + fileName);
    	}
    	Personne save = personneService.save(personne);
        ra.addFlashAttribute("successFlash", "Personne Ajoutée avec succès");
        return "redirect:/personne";

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

    	personneService.delete(id);
        return "redirect:/personne";

    }
    
    @GetMapping("/details/{id}")
    public String showDetails(@PathVariable Long id, Model model) {
        model.addAttribute("personne", personneService.get(id));
        return "personne/details";

    }
    
    @GetMapping("/show/list")
    public String showPersons(Model model) {
    	List<Personne> allPersons = new ArrayList<Personne>();
    	allPersons = personneService.getListAll();
        model.addAttribute("listPersonnes", allPersons);
        return "personne/list";
    }
    
    @GetMapping(path="/NG/listp", produces = "application/json")
    public @ResponseBody List<Personne> getAllPersons() {
    	List<Personne> allPersons = new ArrayList<Personne>();
    	allPersons = personneService.getListAll();
    	System.out.println("Size of List allPersons : "+allPersons.size());
        return allPersons;
    }
}

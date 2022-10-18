package com.hendisantika.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hendisantika.entity.Personne;
import com.hendisantika.service.PersonneService;

public class AddActors {

	public static List<Personne> stringToPersonne(String  ids,PersonneService personneService) {
		List personnes = new ArrayList<Personne>();
		String[] tableId = ids.split(",");
		for(int i=0;i<ids.length();i++) {
			try {
				Personne personne = personneService.get(Long.valueOf(tableId[i]));
				personnes.add(personne);
			}catch(Exception e) {
				
			}
		}
		return personnes;
	}
}

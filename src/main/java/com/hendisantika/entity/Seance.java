package com.hendisantika.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Seance extends AbstractModel<Long>{
	
	private static final long serialVersionUID = 6992208427439369561L;

	@Column(name = "date_projection")
	@Temporal(value = TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date dateProjection;
	
	@Column(name = "heure_debut")
	@Temporal(value = TemporalType.TIME)
	@DateTimeFormat(pattern = "HH:mm")
    private Date heureDebut;
	
	@Column(name = "heure_fin")
	@Temporal(value = TemporalType.TIME)
	@DateTimeFormat(pattern = "HH:mm")
    private Date heureFin;
	
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="Film_ID")
    private Film film;
	
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="salle_ID")
    private Salle salle;

}

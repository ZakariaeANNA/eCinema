package com.hendisantika.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Media extends AbstractModel<Long> {
	
	public enum TypeMedia {IMAGE,VIDEO,DOCUMENT}
	
	@Column(nullable = false, length = 100)
	private String media;
	
	@Column(nullable = true, length = 255)
	@Enumerated(EnumType.STRING)
	private TypeMedia typeMedia;
	
	@Column(name = "added_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
	private Date addedDate;
	
	@ManyToOne(cascade=CascadeType.PERSIST, fetch = FetchType.LAZY)	@JoinColumn(name="FILM_ID")
	private Film film;

}

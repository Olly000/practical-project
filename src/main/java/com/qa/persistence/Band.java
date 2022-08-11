package com.qa.persistence;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.JoinColumn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bands")
public class Band {
	
	public Band(String bandName, String genre, int yearFormed) {
		this.bandName = bandName;
		this.genre = genre;
		this.yearFormed = yearFormed;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="band_id")
	private Long bandId;

	
	@Column(nullable=false)
	private String bandName;
	
	@Column
	private String genre;
	
	@Column
	private int yearFormed;
	
//targetEntity = Recording.class
//	@JsonIgnore
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "band", cascade = CascadeType.ALL)
//	private Set<Recording> recordings = new HashSet<>();
	
//	@JsonIgnore
//	@ManyToMany
//	@JoinTable(
//			  name = "bandmembers", 
//			  joinColumns = @JoinColumn(name = "band_id"), 
//			  inverseJoinColumns = @JoinColumn(name = "musician_id"))
//	private Set<Musician> musicians = new HashSet<>();	


}

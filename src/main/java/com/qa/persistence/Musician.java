package com.qa.persistence;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "musicians")
public class Musician {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "musician_id")
	private Long musicianId;
	
	@Column(nullable=false)
	private String fullName;
	
	@Column(nullable=false)
	private String instrument;
	
	public Musician(String fullName, String instrument) {
		this.fullName = fullName;
		this.instrument = instrument;
	}
	
	//@JsonIgnore
	@ManyToMany
	@JoinTable(name = "recorded", joinColumns=
	@JoinColumn(name="musician_id"),
	inverseJoinColumns = @JoinColumn(name = "recording_id"))
    private Set<Recording> recordings = new HashSet<>();
	
//	@JsonIgnore
//	@ManyToMany(mappedBy = "bandId")
//	private Set<Band> playsIn = new HashSet<>();
	
	@ManyToMany
	@JoinTable(name = "plays_in", joinColumns=
	@JoinColumn(name="musician_id"),
	inverseJoinColumns = @JoinColumn(name = "band_id"))
	private Set<Band> memberOf = new HashSet<>();
	
	
}

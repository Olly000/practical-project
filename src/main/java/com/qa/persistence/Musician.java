package com.qa.persistence;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	
	@JsonIgnore
	@ManyToMany(mappedBy = "players")
    private Set<Recording> recordings = new HashSet<>();
	
	@JsonIgnore
	@ManyToMany(mappedBy = "musicians")
	private Set<Band> bands = new HashSet<>();
	
	
}

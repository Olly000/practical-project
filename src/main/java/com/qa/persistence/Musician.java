package com.qa.persistence;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "musicians")
public class Musician {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "musician_id")
	private int musicianId;
	
	@Column(nullable=false)
	private String surname;
	

	@Column(nullable=false)
	private String forename;
	
	@Column(nullable=false)
	private String instrument;
	
	@ManyToMany(mappedBy = "musican_id")
    private Set<Record> recordings = new HashSet<>();
	
	@ManyToMany(mappedBy = "musician_id")
	private Set<Band> bands = new HashSet<>();
	
}

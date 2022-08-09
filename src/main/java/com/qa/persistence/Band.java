package com.qa.persistence;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="band_id")
	private Long bandId;

	
	@Column(nullable=false)
	private String name;
	
	@Column
	private String genre;
	
	@Column
	private int yearFormed;
	
	@Column
	private boolean active;
	

	@OneToMany(mappedBy = "bandId", targetEntity = Record.class)
	private Set<Record> records = new HashSet<>();
	
	
	@JoinTable(
			  name = "bandmembers", 
			  joinColumns = @JoinColumn(name = "band_id"), 
			  inverseJoinColumns = @JoinColumn(name = "musician_id"))
	@ManyToMany
	private Set<Musician> musicians = new HashSet<>();	


}

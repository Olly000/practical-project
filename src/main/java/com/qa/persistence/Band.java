package com.qa.persistence;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Band {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="band_id")
	private int bandId;
	
	@Column(nullable=false)
	private String name;
	
	@Column
	private String genre;
	
	@Column
	private int yearFormed;
	
	@Column
	private boolean active;
	
	@OneToMany(mappedBy = "band")
	private Set<Record> recordings = new HashSet<>();
	

}

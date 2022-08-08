package com.qa.persistence;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="records")
public class Record {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name="record_id")
	private int recordId;
	
	@Column(nullable=false)
	private String title;
	

	@ManyToOne
	@JoinColumn(name="band_id")
	@Column(nullable=false)
	private int bandId;
	
	@Column
	private String label;
	
	@Column
	private int releaseYear;
	
	@Column
	private String linkToCover;
	

	@ManyToMany(mappedBy = "record_id")
    private Set<Musician> musicians = new HashSet<>();
	
}

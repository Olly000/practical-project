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
@Table(name="recordings")
public class Recording {
	
	public Recording(String title, Band band, String label, int releaseYear) {
		this.title = title;
		this.band = band;
		this.label = label;
		this.releaseYear = releaseYear;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="recording_id")
	private Long recordingId;
	
	@Column(nullable=false)
	private String title;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "band_id", referencedColumnName = "band_id")
	private Band band;
	
	@Column
	private String label;
	
	@Column
	private int releaseYear;
	
	@JoinTable(
			  name = "played_on", 
			  joinColumns = @JoinColumn(name = "recording_id"), 
			  inverseJoinColumns = @JoinColumn(name = "musician_id"))
	@ManyToMany
    private Set<Musician> players = new HashSet<>();
	
}

package com.qa.repositories;

import org.springframework.stereotype.Repository;
import com.qa.persistence.Musician;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface MusicianRepository extends JpaRepository<Musician, Long> {

//	public Musician findByFullName(String forename, String surname);
	
	public List<Musician> findAllByInstrument(String instrument);


}
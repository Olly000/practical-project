package com.qa.repositories;

import org.springframework.stereotype.Repository;
import com.qa.persistence.Musician;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface MusicianRepository extends JpaRepository<Musician, Long> {

	@Query(value = "SELECT * FROM musicians WHERE full_name = ?1", nativeQuery = true)
	public Musician findByName(String fullName);
	
	
	public List<Musician> findAllByInstrument(String instrument);


}
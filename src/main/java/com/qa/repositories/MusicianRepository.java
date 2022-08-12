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
	
	@Query(value = "DELETE FROM plays_in WHERE band_id =?1", nativeQuery = true)
	public Long deletePlaysInAssoc(Long id);
	
	public List<Musician> findAllByInstrument(String instrument);


}
package com.qa.repositories;

import org.springframework.stereotype.Repository;

import com.qa.persistence.Recording;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface RecordingRepository extends JpaRepository<Recording, Long> {
	
	@Query(value = "SELECT * FROM recordings WHERE title = ?1", nativeQuery = true)
	public Recording findByTitle(String title);
	
	@Query(value = "SELECT * FROM recordings WHERE label = ?1", nativeQuery = true)
	public List<Recording> findAllByLabel(String label);


}

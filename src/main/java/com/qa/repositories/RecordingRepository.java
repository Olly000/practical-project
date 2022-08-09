package com.qa.repositories;

import org.springframework.stereotype.Repository;

import com.qa.persistence.Recording;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RecordingRepository extends JpaRepository<Recording, Long> {

	public Recording findByTitle(String title);
	
	public List<Recording> findAllByLabel(String label);


}

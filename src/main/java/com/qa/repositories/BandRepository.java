package com.qa.repositories;

import org.springframework.stereotype.Repository;

import com.qa.persistence.Band;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface BandRepository extends JpaRepository<Band, Long> {
	
	@Query(value = "SELECT * FROM bands WHERE band_name = ?1", nativeQuery = true)
	public Band findByBandName(String BandName);


}

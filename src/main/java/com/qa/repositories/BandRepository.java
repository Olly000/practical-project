package com.qa.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.qa.persistence.Band;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface BandRepository extends JpaRepository<Band, Long> {
	
	@Query(value = "SELECT * FROM bands WHERE band_name = ?1", nativeQuery = true)
	public Band findByBandName(String BandName);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM plays_in WHERE band_id =?1", nativeQuery = true)
	public void deletePlaysInAssoc(Long id);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM recordings WHERE band_id = ?1", nativeQuery = true)
	public void deleteBandDiscog(Long id);
	
}

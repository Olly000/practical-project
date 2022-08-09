package com.qa.repositories;

import org.springframework.stereotype.Repository;

import com.qa.persistence.Band;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface BandRepository extends JpaRepository<Band, Long> {

	public Band findByName(String name);


}

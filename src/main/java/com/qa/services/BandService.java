package com.qa.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.qa.exceptions.NoDeleteException;
import com.qa.persistence.Band;
import com.qa.persistence.BandDTO;
import com.qa.repositories.BandRepository;

@Service
public class BandService {
	
	@Autowired
	private BandRepository repo;
	
	private ModelMapper mapper;
	

	public BandService(BandRepository repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	
	// utility methods
	public BandDTO mapToDTO(Band band) {
		return this.mapper.map(band, BandDTO.class);
	}
	
	public Long idFromName(String bandName) {
		Band band = repo.findByBandName(bandName);
		return band.getBandId();
	}
	
	public Band checkBandName(String bandName) {
		Band bandCheck = repo.findByBandName(bandName);
		System.out.println(bandCheck);
		if (bandCheck != null) {
			return bandCheck;
		}
		return new Band(0L, bandName, "tba", 0);
		
	}
	
	// create
	public BandDTO addBand(Band band) {
		return this.mapToDTO(this.repo.save(band));
	}
	
	// read
	public List<BandDTO> getAllBands() {
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}
	
	public BandDTO getOneBand(Long id) throws NotFoundException {
		return this.mapToDTO(repo.findById(id).orElseThrow(NotFoundException::new));
	}
	
	// update
	public BandDTO updateBand(Band band) throws NotFoundException {
		Band existingBand = this.repo.findByBandName(band.getBandName());
		if(existingBand != null) {
			existingBand.setBandName(band.getBandName());
			existingBand.setGenre(band.getGenre());
			existingBand.setYearFormed(band.getYearFormed());
		} else {
			return this.addBand(band);
		}
		return this.mapToDTO(this.repo.save(existingBand));
	}
	
	//delete
	public boolean deleteBand(Long id) throws NoDeleteException {
		this.repo.deleteById(id);
		boolean exists = this.repo.existsById(id);
		if(exists) {
			throw new NoDeleteException();
		}
		return !exists;
	}

	public void removeMemberAssoc(Long id) {
		repo.deletePlaysInAssoc(id);
	}
	
	public void deleteDiscog(Long id) {
		repo.deleteBandDiscog(id);
	}

}


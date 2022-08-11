package com.qa.controllers;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.exceptions.NoDeleteException;
import com.qa.persistence.Band;
import com.qa.persistence.BandDTO;
import com.qa.services.BandService;

@Service
@RestController
public class BandController {
	
	private BandService service;

	public BandController(BandService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/")
	public String BandHome() {
		return "<h1>This is the Bands homepage</h1>";
	}
	
	@GetMapping("/error")
	public String error() {
		return "<h2> It ain't working chief<h2>";
	}
	
	@PostMapping("/addBand")
	public BandDTO add(@RequestBody Band band) {
		return service.addBand(band);
	}
	
	@GetMapping("/getAllBands")
	public List<BandDTO> getAll() {
		return service.getAllBands();
	}
	
	@GetMapping("/getOneBand")
	public BandDTO getOne(@PathParam("bandName") String bandName) throws NotFoundException {
		Long id = service.idFromName(bandName);
		return service.getOneBand(id);
	}
	
	@PostMapping("/updateBand")
	public BandDTO update(@RequestBody Band band) throws NotFoundException {
		Long id = service.idFromName(band.getBandName());
		band.setBandId(id);
		return service.updateBand(band.getBandId(), band);
	}
	
	@PostMapping("/deleteBand")
	public boolean delete(@PathParam("bandName") String bandName) throws NoDeleteException {
		Long id = service.idFromName(bandName);
		return service.deleteBand(id);
	}
	
	@PostMapping("/deleteBandById")
	public boolean delete(@PathParam("id") Long id) throws NoDeleteException {
		return service.deleteBand(id);
	}

}
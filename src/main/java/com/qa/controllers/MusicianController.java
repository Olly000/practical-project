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
import com.qa.persistence.Musician;
import com.qa.persistence.MusicianDTO;
import com.qa.services.MusicianService;

@Service
@RestController
public class MusicianController {
	
	private MusicianService service;

	public MusicianController(MusicianService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/musicians")
	public String musicianHome() {
		return "<h1>This is the musician homepage</h1>";
	}
	
	@PostMapping("/addMusician")
	public MusicianDTO add(@RequestBody Musician musician) {
		return service.addMusician(musician);
	}
	
	@GetMapping("/getAllMusicians")
	public List<MusicianDTO> getAll() {
		return service.getAllMusicians();
	}
	
	@GetMapping("/getOneMusician")
	public MusicianDTO getOne(@PathParam("id") Long id) throws NotFoundException {
		return service.getOneMusician(id);
	}
	
	@PostMapping("/updateMusician")
	public MusicianDTO update(@RequestBody Musician musician) throws NotFoundException {
		return service.updateMusician(musician.getMusicianId(), musician);
	}
	
	@PostMapping("/deleteMusician")
	public boolean delete(@PathParam("id") Long id) throws NoDeleteException {
		return service.deleteMusician(id);
	}

}

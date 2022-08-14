package com.qa.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.persistence.Musician;
import com.qa.persistence.MusicianDTO;
import com.qa.runner.PracticalProjectApplication;


@SpringBootTest(classes = {PracticalProjectApplication.class, MusicianController.class})
@AutoConfigureMockMvc
public class MusicianControllerTest {

	

	@Autowired
	private MockMvc mock;
	
	@Autowired
	private ModelMapper mapper;
	
	private MusicianDTO mapToDTO(Musician musician) {
        return this.mapper.map(musician, MusicianDTO.class);
    }

    @Autowired
    private ObjectMapper jsonifier;
    
    private final Long TEST_ID = 55L;
    private final Musician TEST_MUSICIAN = new Musician (1L, "John McIntyre", "drums", null, null);
    private final  Musician TEST_MUSICIAN2 = new Musician(2L, "David Pajo", "guitar", null, null);
    private final  Musician TEST_MUSICIAN3 = new Musician(2L, "Sam Prekop", "guitar", null, null);
    
    @Test
    public void testCreateMusician() {
    	Musician expected = TEST_MUSICIAN;
    	expected.setMusicianId(TEST_ID);
    	try {
    	this.mock.perform(post("/addMusician")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(this.jsonifier.writeValueAsString(expected)))
        .andExpect(status().isOk())
        .andExpect(content().json(this.jsonifier.writeValueAsString(this.mapToDTO(expected))));
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    @Test
    public void testGetOneBand() {
    	Musician expected = TEST_MUSICIAN;
    	expected.setMusicianId(TEST_ID);
    	try {
    	this.mock.perform(get("/getOneMusician")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(this.jsonifier.writeValueAsString(expected)))
        .andExpect(status().isOk())
        .andExpect(content().json(this.jsonifier.writeValueAsString(this.mapToDTO(expected))));
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
   
    @Test
    public void testgetAllMusicians() {
    	List<MusicianDTO> musicianList = new ArrayList<>();
    	musicianList.add(mapToDTO(TEST_MUSICIAN));
    	musicianList.add(mapToDTO(TEST_MUSICIAN2));
    	musicianList.add(mapToDTO(TEST_MUSICIAN3));
    	
    	try {
    	this.mock.perform(get("/getAllMusicians")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(this.jsonifier.writeValueAsString(musicianList)))
        .andExpect(status().isOk())
        .andExpect(content().json(this.jsonifier.writeValueAsString(musicianList)));
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    @Test
    public void testUpdateMusician() {
    	Musician expected = TEST_MUSICIAN;
    	expected.setMusicianId(TEST_ID);
    	try {
    	this.mock.perform(post("/updateBand")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(this.jsonifier.writeValueAsString(expected)))
        .andExpect(status().isOk())
        .andExpect(content().json(this.jsonifier.writeValueAsString(this.mapToDTO(expected))));
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    @Test
    public void testdeleteMusician() {
    	Musician expected = TEST_MUSICIAN;
    	try {
    	this.mock.perform(post("/deleteMusician")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(this.jsonifier.writeValueAsString(expected)))
        .andExpect(status().isOk())
        .andExpect(content().json(this.jsonifier.writeValueAsString(expected)));
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

}

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
import com.qa.persistence.Band;
import com.qa.persistence.BandDTO;
import com.qa.runner.PracticalProjectApplication;


@SpringBootTest(classes = {PracticalProjectApplication.class, BandController.class})
@AutoConfigureMockMvc
public class BandControllerTest {

	

	@Autowired
	private MockMvc mock;
	
	@Autowired
	private ModelMapper mapper;
	
	private BandDTO mapToDTO(Band band) {
        return this.mapper.map(band, BandDTO.class);
    }

    @Autowired
    private ObjectMapper jsonifier;
    
    private final Long TEST_ID = 1L;
    private final Band TEST_BAND = new Band(1L, "tortoise", "post-rock", 1990);
    private final Band TEST_BAND2 = new Band(2L, "the sea and cake", "jazz-rock", 1994);
    private final Band TEST_BAND3 = new Band(3L, "slint", "indie-rock", 1986);
    
    @Test
    public void testCreateBand() {
    	Band expected = new Band("autechre", "electronic", 1994);
    	expected.setBandId(TEST_ID);
    	try {
    	this.mock.perform(post("/addBand")
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
    	Band expected = TEST_BAND;
    	expected.setBandId(TEST_ID);
    	try {
    	this.mock.perform(get("/getOneBand")
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
    public void testgetAllBands() {
    	List<BandDTO> bandList = new ArrayList<>();

    	bandList.add(mapToDTO(TEST_BAND));
    	bandList.add(mapToDTO(TEST_BAND2));
    	bandList.add(mapToDTO(TEST_BAND3));
    	
    	try {
    	this.mock.perform(get("/getAllBands")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(this.jsonifier.writeValueAsString(bandList)))
        .andExpect(status().isOk())
        .andExpect(content().json(this.jsonifier.writeValueAsString(bandList)));
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    @Test
    public void testUpdateBand() {
    	Band expected = TEST_BAND;
    	
    	try {
    	this.mock.perform(post("/updateBand")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(this.jsonifier.writeValueAsString(TEST_BAND)))
        .andExpect(status().isOk())
        .andExpect(content().json(this.jsonifier.writeValueAsString(this.mapToDTO(expected))));
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    @Test
    public void testdeleteBand() {
    	boolean expected = true;
    	try {
    	this.mock.perform(post("/deleteBand")
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

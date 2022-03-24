package com.qa.census.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.census.entity.Saiyan;
import com.qa.census.service.SaiyanService;

@RestController
@RequestMapping("/saiyan")
public class SaiyanController {
	
	private SaiyanService service;
	
	private SaiyanController(SaiyanService service) {
		this.service = service;
	}
	
	//Create
	@PostMapping("/create")
	public ResponseEntity<Saiyan> createSaiyan(@RequestBody Saiyan saiyan){
		return new ResponseEntity<Saiyan>(this.service.create(saiyan), HttpStatus.CREATED);
	}
	
	//Read All
	@GetMapping("/readAll")
	public ResponseEntity<List<Saiyan>> readAllSaiyans(){
		return new ResponseEntity<List<Saiyan>>(this.service.readAll(), HttpStatus.OK);
	}
	//Read by id
	
	@GetMapping("/readById/{id}")
	public ResponseEntity<Saiyan> readById(@PathVariable long id){
		return new ResponseEntity<Saiyan>(this.service.readById(id), HttpStatus.OK);
		
	}
	
	//Update
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Saiyan> updateDuck(@PathVariable long id, @RequestBody Saiyan saiyan){
		return new ResponseEntity<Saiyan>(this.service.update(id, saiyan), HttpStatus.ACCEPTED);
		
	}
	
	//Delete
	//Using if (?)/else (:)
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> deleteSaiyan(@PathVariable long id){
		return (this.service.delete(id)) ? new ResponseEntity<Boolean>(HttpStatus.NO_CONTENT) :
			new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);
	}

}

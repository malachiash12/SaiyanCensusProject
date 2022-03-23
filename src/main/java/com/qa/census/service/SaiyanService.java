package com.qa.census.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.qa.census.entity.Saiyan;
import com.qa.census.repo.SaiyanRepo;

@Service
public class SaiyanService implements ServiceMethods<Saiyan> {
	
	//Creating a variable called repo, not instantiating an object because the repo is an interface.
	private SaiyanRepo repo;
	
	public SaiyanService(SaiyanRepo repo) {
		this.repo = repo;
	}

	@Override
	public Saiyan create(Saiyan t) {
		// TODO Auto-generated method stub
		return this.repo.save(t);
	}

	@Override
	public List<Saiyan> readAll() {
		// TODO Auto-generated method stub
		return this.repo.findAll();
	}

	@Override
	public Saiyan readById(long id) {
		// TODO Auto-generated method stub
		Optional<Saiyan> getSaiyan = this.repo.findById(id);
		if(getSaiyan.isPresent()) {
			return getSaiyan.get();
		}
		return null;
	}

	//Don't set the id when updating
	@Override
	public Saiyan update(long id, Saiyan t) {
		// TODO Auto-generated method stub
		Optional<Saiyan> existingSaiyan = this.repo.findById(id);
		if(existingSaiyan.isPresent()) {
			Saiyan exists = existingSaiyan.get();
			exists.setAge(t.getAge());
			exists.setName(t.getName());
			exists.setSex(t.getSex());
			exists.setPowerLevel(t.getPowerLevel());
			exists.setIsSSj(t.getIsSSj());
			return this.repo.saveAndFlush(exists);
		}
		return null;
	}

	//Deletes the id of the Saiyan, then checks to see if it no longer exists (returns true if it's been deleted)
	@Override
	public boolean delete(long id) {
		// TODO Auto-generated method stub
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

}

package com.qa.census.service;

import java.util.List;

public interface ServiceMethods<T> {

	//Create
	T create(T t);
	
	//Read All
	List<T> readAll();
	
	//Read By Id
	T readById(long id);
	
	//Update
	T update(long id, T t);
	
	//Delete
	boolean delete(long id);
	
}

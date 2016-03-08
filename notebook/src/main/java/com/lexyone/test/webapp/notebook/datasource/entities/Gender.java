package com.lexyone.test.webapp.notebook.datasource.entities;

public enum Gender {
	MAN("M"), 
	WOMEN("W");
	private final String id;

	private Gender(String id) {
		this.id = id;
		
	}

	public String getId() {
		return id;
	}
	
	static public Gender identify(String id) {
		for(Gender gender : Gender.values()){
			if(gender.getId().equals(id)) {
				return gender;
			}
			
		}
		throw new IllegalArgumentException();
	}
}

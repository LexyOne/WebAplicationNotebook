package com.lexyone.test.webapp.notebook.datasource.entities;

import java.util.Objects;

public class User {
	private Long id = null;
	private String surname = "";
	private String name = "";
	private Gender gender = null;
	private Integer age = -1;
	private Phone phone = Phone.getEmptyPhone();
	
	public static final int MIN_AGE = 0; 
	public static final int MAX_AGE = 100; 

	public User() {
	}

	public User(User user) {
		this.id = user.id;
		this.surname = user.surname;
		this.name = user.name;
		this.gender = user.gender;
		this.age = user.age;
		this.phone = user.phone;
	}
	
	public static User makeTestUser(int id) {
		User user = new User();
		user.setId((long) id);
		user.setSurname("Surname-"+id);
		user.setName("Name-"+id);
		user.setGender(((id%2)>0) ? Gender.MAN : Gender.WOMEN);
		user.setAge(id%100);
		user.setPhone("+(111)111-11-11");
		return user;
	}

	@Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;
        User that = (User) obj;
        return 	(this.id == that.id) &&
        		(this.surname.equals(that.surname)) &&
        		(this.name.equals(that.name)) &&
        		(this.gender == that.gender) &&
        		(this.age == that.age) &&
        		(this.phone.equals(that.phone));
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Objects.hashCode(id);
		result = prime * result + Objects.hashCode(surname);
		result = prime * result + Objects.hashCode(name);
		result = prime * result + Objects.hashCode(gender);
		result = prime * result + age;
		result = prime * result + Objects.hashCode(phone);
		return result;
	}

	public boolean isCorrect() {
		if(surname.isEmpty()) return false;
		if(name.isEmpty()) return false;
		if(gender == null) return false;
		if(age<MIN_AGE || age>MAX_AGE) return false;
		if(!phone.isCorrect()) return false;
		return true;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname!=null ? surname.trim() : "";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name!=null ? name.trim() : "";
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public void setGender(String gender) {
		try {
			this.gender = Gender.identify(gender);
		} catch (Exception e) {
			this.gender = null;
		}
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age!=null ? age : -1;
	}

	public Phone getPhone() {
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone!=null ? phone : Phone.getEmptyPhone();
	}	

	public void setPhone(String phone) {
		try {
			this.phone = Phone.valueOf(phone);
		} catch (Exception e) {
			this.phone = Phone.getEmptyPhone();
		}
	}
	
	public static class Filter {
		private String field;
		private Object value;
		private String condition;
		
		Filter(String field, Object value, String condition) {
			this.field = field;
			this.value = value;
			this.condition = condition;
		}
		
		public String getField() {
			return field;
		}

		public Object getValue() {
			return value;
		}

		public String getCondition() {
			return condition;
		}

		@Override
		public String toString() {
			StringBuilder result = new StringBuilder();
			return result.append(field).append(" ").append(value).append(" ").append(condition).toString();
		}
	}
}



























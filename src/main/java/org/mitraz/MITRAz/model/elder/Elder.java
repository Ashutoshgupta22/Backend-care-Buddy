package org.mitraz.MITRAz.model.elder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Elder {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private int age;
	private String location;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return "Elder [id=" + id + ", name=" + name + ", age=" + age + ", location=" + location + "]";
	}
	
	
	

}

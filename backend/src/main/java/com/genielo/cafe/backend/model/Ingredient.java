package com.genielo.cafe.backend.model;

import java.util.List;

import jakarta.persistence.*;


@Entity
@Table(name = "ingredients")
public class Ingredient {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name= "name")
	private String name;
	
	@Column(name= "cost")
	private double cost;
	
	@Column(name = "description")
	private String description;
	
	@ManyToMany(mappedBy = "ingredients")
	private List<Item> items;
	
	public Ingredient() {
		
	}
	
	public Ingredient(String name, double cost, String description) {
		this.name = name;
		this.cost = cost;
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}

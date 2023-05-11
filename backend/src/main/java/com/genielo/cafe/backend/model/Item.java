package com.genielo.cafe.backend.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "items")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "cost")
	private double cost;
	
	@Column(name = "description")
	private String description;
	
	@Column(name="type")
	private String type ;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@ManyToMany(mappedBy = "items")
	private List<Command> commands;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "item_ingredients",
	joinColumns = @JoinColumn(name="ingredient_id",
referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name="item_id",
	referencedColumnName = "id"))
	private List<Ingredient> ingredients;

	public Item(String name, double cost, String description) {
		super();
		this.name = name;
		this.cost = cost;
		this.description = description;
	}

	public Item() {
		
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

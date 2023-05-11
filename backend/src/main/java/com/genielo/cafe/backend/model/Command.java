package com.genielo.cafe.backend.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="commands")
public class Command {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "amount")
	private double amount;
	
	@Column(name = "tablenumber")
	private int tablenumber;
	
	@Column(name = "type")
	private String type;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "command_items",
	joinColumns = @JoinColumn(name="item_id",
referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name="command_id",
	referencedColumnName = "id"))
	private List<Item> items;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "completed")
	private boolean completed;

	public Command() {
		super();
	}

	

	public Command(double amount, int tablenumber, String type, boolean completed) {
		super();
		this.amount = amount;
		this.tablenumber = tablenumber;
		this.type = type;
		this.completed = completed;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getTablenumber() {
		return tablenumber;
	}

	public void setTablenumber(int tablenumber) {
		this.tablenumber = tablenumber;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	
	
}

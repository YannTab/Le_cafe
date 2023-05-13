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
	
	@Column(name = "served")
	private boolean served = false;
	
	@Column(name = "cooked")
	private boolean cooked = false;
	
	@Column(name = "confirmed")
	private boolean confirmed = false;
	
	@Column(name = "type")
	private String type;
	
	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "command_items",
	joinColumns = @JoinColumn(name="item_id",
referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name="command_id",
	referencedColumnName = "id"))
	private List<Item> items;
	
	
	public Command() {
		super();
	}

	public Command(double amount, int tablenumber, boolean served, boolean cooked, String type, boolean confirmed) {
		super();
		this.amount = amount;
		this.tablenumber = tablenumber;
		this.served = served;
		this.cooked = cooked;
		this.confirmed = confirmed;
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public boolean isServed() {
		return served;
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}

	public void setServed(boolean served) {
		this.served = served;
	}

	public boolean isCooked() {
		return cooked;
	}

	public void setCooked(boolean cooked) {
		this.cooked = cooked;
	}

	
}

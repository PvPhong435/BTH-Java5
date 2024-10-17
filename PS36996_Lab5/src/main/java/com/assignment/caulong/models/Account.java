package com.assignment.caulong.models;



import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity 
@Table(name = "Accounts")
public class Account  implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer id;
	String username;
	String password;
	String fullname;
	String email;
	String photo;
	boolean admin;
	boolean activated;
	@OneToMany(mappedBy= "account")
	List<Cart> carts;
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	List<Order> orders;
}

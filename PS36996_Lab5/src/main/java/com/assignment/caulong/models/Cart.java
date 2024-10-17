package com.assignment.caulong.models;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table
public class Cart {
	@EmbeddedId
	private CartId cartId;
	@ManyToOne
	@MapsId("accountId")
	@JoinColumn(name="account_id")
	private Account account;
	@ManyToOne
	@MapsId("productId")
	@JoinColumn(name="")
	private Product product;
	private int quantity;
	public CartId getCartId() {
		return cartId;
	}
	public void setCartId(CartId cartId) {
		this.cartId = cartId;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}

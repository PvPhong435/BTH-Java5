package com.Thi.entity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity @Table(name = "Products")
public class Product  implements Serializable{
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull (message = "ID is null")
	Integer id;
	
//	@NotBlank(message = "Name is null")
	String name;
	
//	@NotNull(message = "Price is null")
	@DecimalMin(message = "Price must more than 1",value = "1")
	Double price;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "Createdate")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	@NotNull(message = "DateTime is null")
	Date createDate = new Date();
	Boolean available;
	@ManyToOne
	@JoinColumn(name = "Categoryid")
	Category category;
	

	@OneToMany(mappedBy="product", cascade = CascadeType.ALL)
//	@Valid
	List<ProductImage> productImages;
	
	@Override
	public String toString() {
	    return "Product{" +
	            "id=" + id +
	            ", name='" + name + '\'' +
	            ", price=" + price +
	            // Tránh gọi toString() của đối tượng Category để tránh đệ quy
	            '}';
	}

	
}

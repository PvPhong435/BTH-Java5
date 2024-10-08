package com.lab7.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@IdClass(ProductImageId.class)
@Table(name = "ProductImage")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductImage {

    @Id
    @Column(name = "ProductId")
    private Integer productId; // Tham chiếu đến Product

    @Id
    @Column(name = "imageLink")
    private String imageLink; // Liên kết hình ảnh

    @ManyToOne
    @JoinColumn(name = "ProductId", insertable = false, updatable = false)
    private Product product; // Đối tượng sản phẩm

}

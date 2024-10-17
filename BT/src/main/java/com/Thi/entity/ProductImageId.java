package com.Thi.entity;

import java.io.Serializable;
import java.util.Objects;

public class ProductImageId implements Serializable {
    private Integer productId;
    private String imageLink;

    // Constructors
    public ProductImageId() {}

    public ProductImageId(Integer productId, String imageLink) {
        this.productId = productId;
        this.imageLink = imageLink;
    }

    // Getters and Setters
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    // Override equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductImageId)) return false;
        ProductImageId that = (ProductImageId) o;
        return Objects.equals(productId, that.productId) &&
                Objects.equals(imageLink, that.imageLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, imageLink);
    }
}

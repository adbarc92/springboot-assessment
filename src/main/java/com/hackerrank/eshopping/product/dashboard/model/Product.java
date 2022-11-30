package com.hackerrank.eshopping.product.dashboard.model;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
@Table
public class Product {
  @Id
  private Long id;
  private String name;
  private String category;
  private Double retailPrice;
  private Double discountedPrice;
  private Boolean availability;

  public Product() {
  }

  public Product(Long id, String name, String category, Double retailPrice, Double discountedPrice,
      Boolean availability) {
    this.id = id;
    this.name = name;
    this.category = category;
    this.retailPrice = retailPrice;
    this.discountedPrice = discountedPrice;
    this.availability = availability;
  }

  @JsonGetter("id")
  public Long getId() {
    return id;
  }

  @JsonSetter("id")
  public void setId(Long id) {
    this.id = id;
  }

  @JsonGetter("name")
  public String getName() {
    return name;
  }

  @JsonSetter("name")
  public void setName(String name) {
    this.name = name;
  }

  @JsonGetter("category")
  public String getCategory() {
    return category;
  }

  @JsonSetter("category")
  public void setCategory(String category) {
    this.category = category;
  }

  @JsonGetter("retail_price")
  public Double getRetailPrice() {
    return retailPrice;
  }

  @JsonSetter("retail_price")
  public void setRetailPrice(Double retailPrice) {
    this.retailPrice = retailPrice;
  }

  @JsonGetter("discounted_price")
  public Double getDiscountedPrice() {
    return discountedPrice;
  }

  @JsonSetter("discounted_price")
  public void setDiscountedPrice(Double discountedPrice) {
    this.discountedPrice = discountedPrice;
  }

  @JsonGetter("availability")
  public Boolean getAvailability() {
    return availability;
  }

  @JsonSetter("availability")
  public void setAvailability(Boolean availability) {
    this.availability = availability;
  }

  @Override
  public String toString() {
    return String.format(
        "ProductId: %s\nName: %s\nCategory: %s\nRetail Price: %s\nDiscounted Price: %s\nAvailability: %s\n",
        id, name, category, retailPrice, discountedPrice, availability);
  }

  public String toJson() {
    return String.format(
        "{\"id\": %s, \"name\": \"%s\", \"category\": \"%s\", \"retail_price\": %s, \"discounted_price\": %s, \"availability\": %s}",
        id, name, category, retailPrice, discountedPrice, availability);
  }
}

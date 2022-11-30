package com.hackerrank.eshopping.product.dashboard.service;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.hackerrank.eshopping.product.dashboard.model.Product;
import com.hackerrank.eshopping.product.dashboard.repository.ProductsRepository;

@Service
public class ProductsService {
  private final ProductsRepository productsRepository;

  @Autowired
  public ProductsService(ProductsRepository productsRepository) {
    this.productsRepository = productsRepository;
  }

  // TODO: This could be refactored to dynamically construct the query.
  // Note: this is a bit finnicky as it requires the category to match exactly
  public List<Product> getProducts(String category, Boolean availability) {
    Optional<List<Product>> potentialProducts = null;
    String newCategory = category;
    try {
      String result = java.net.URLDecoder.decode(category, StandardCharsets.UTF_8.name());
      newCategory = result;
    } catch (UnsupportedEncodingException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category encoding is not supported.");
    }
    if (!category.isEmpty() && availability) {
      potentialProducts = productsRepository.findAvailableByCategory(newCategory, availability);
      if (potentialProducts.isPresent()) {
        return potentialProducts.get();
      } else {
        return Collections.emptyList();
      }
    } else if (!category.isEmpty()) {
      potentialProducts = productsRepository.findByCategory(category);
      if (potentialProducts.isPresent()) {
        return potentialProducts.get();
      } else {
        return Collections.emptyList();
      }
    } else {
      return productsRepository.findAllByOrderByIdAsc();
    }
  }

  public Product getProduct(Long productId) {
    Optional<Product> potentialProduct = productsRepository.findById(productId);
    if (potentialProduct.isPresent()) {
      return potentialProduct.get();
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product (id: " + productId + ") does not exist");
    }
  }

  public Product saveProduct(Product product) {
    boolean productExists = productsRepository.findById(product.getId()).isPresent();
    if (productExists) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product already exists");
    } else {
      return productsRepository.save(product);
    }
  }

  @Transactional
  public Product updateProduct(long productId, Product fixedProduct) {
    Optional<Product> productExists = productsRepository.findById(productId);
    if (productExists.isPresent()) {
      Product product = productExists.get();

      String newName = fixedProduct.getName();
      if (newName != null) {
        product.setName(newName);
      }

      String newCategory = fixedProduct.getCategory();
      if (newCategory != null) {
        product.setCategory(newCategory);
      }

      Boolean newAvailability = fixedProduct.getAvailability();
      if (newAvailability != null) {
        product.setAvailability(newAvailability);
      }

      product.setRetailPrice(fixedProduct.getRetailPrice());
      product.setDiscountedPrice(fixedProduct.getDiscountedPrice());
      return productsRepository.save(product);
    } else {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with id " + productId + " not exist");
    }
  }
}

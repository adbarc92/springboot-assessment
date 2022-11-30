package com.hackerrank.eshopping.product.dashboard.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hackerrank.eshopping.product.dashboard.model.Product;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {
  @Query("SELECT p FROM Product p WHERE p.category = ?1 ORDER BY p.availability DESC, p.discountedPrice ASC, p.id ASC")
  Optional<List<Product>> findByCategory(String category);

  // For larger sets, it is more efficient to store the discount percentage as a
  // separate column, but that would require updates on put requests.
  @Query("SELECT p FROM Product p WHERE p.category = ?1 AND p.availability = ?2 ORDER BY ((p.retailPrice - p.discountedPrice) / p.retailPrice * 100) DESC, p.discountedPrice DESC, p.id ASC")
  Optional<List<Product>> findAvailableByCategory(String category, Boolean availability);

  public List<Product> findAllByOrderByIdAsc();
}

package com.hackerrank.eshopping.product.dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hackerrank.eshopping.product.dashboard.model.Product;
import com.hackerrank.eshopping.product.dashboard.service.ProductsService;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/products")
public class ProductsController {
  private final ProductsService productsService;

  @Autowired
  public ProductsController(ProductsService productsService) {
    this.productsService = productsService;
  }

  @PostMapping(produces = "application/json")
  @ResponseStatus(code = HttpStatus.CREATED)
  public String registerProduct(@Valid @RequestBody Product product) {
    return productsService.saveProduct(product).toJson();
  }

  @PutMapping(path = "{productId}")
  public Product updateProduct(@PathVariable("productId") Long id, @Valid @RequestBody Product product) {
    return productsService.updateProduct(id, product);
  }

  @GetMapping(path = "{productId}", produces = "application/json")
  public String getProduct(@PathVariable("productId") Long id) {
    return productsService.getProduct(id).toJson();
  }

  @GetMapping(produces = "application/json")
  public String getProducts(@Valid @RequestParam(defaultValue = "") String category,
      @Valid @RequestParam(defaultValue = "0") Boolean availability) {
    List<Product> productList = productsService.getProducts(category, availability);
    String agg = "[";
    for (int i = 0; i < productList.size(); i++) {
      agg += productList.get(i).toJson();
      if (i < productList.size() - 1) {
        agg += ", ";
      }
    }
    return agg + "]";
  }
}

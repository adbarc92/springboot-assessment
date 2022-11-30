// package com.hackerrank.eshopping.product.dashboard.config;

// import org.springframework.boot.CommandLineRunner;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import com.hackerrank.eshopping.product.dashboard.model.Product;
// import
// com.hackerrank.eshopping.product.dashboard.repository.ProductsRepository;

// import java.util.List;

// @Configuration
// public class ProductsConfig {

// @Bean
// CommandLineRunner commandLineRunner(ProductsRepository repository) {
// return args -> {

// Product j0 = new Product(2L, "j2", "Coats and Jackets",
// 274.0, 229.16, true);

// Product j1 = new Product(1L, "j1", "Coats and Jackets",
// 274.0, 230.16, true);

// Product j2 = new Product(2L, "j2", "Coats and Jackets",
// 274.0, 229.16, true);

// Product j3 = new Product(3L, "j3", "Coats and Jackets",
// 300.0, 232.16, false);

// Product j4 = new Product(4L, "j4", "Coats and Jackets",
// 400.0, 231.16, true);

// Product j5 = new Product(5L, "j5", "Coats and Jackets", 1000.0, 10.0, true);

// Product j6 = new Product(6L, "j6", "Coats and Jackets", 100.0, 1.0, true);

// Product j7 = new Product(7L, "j7", "Coats and Jackets", 100.0, 1.0, true);

// Product j99 = new Product(99L, "j99", "Boots",
// 400.0, 231.16, true);

// repository.saveAll(List.of(j0, j1, j2, j3, j4, j5, j6, j7, j99));
// };
// }
// }

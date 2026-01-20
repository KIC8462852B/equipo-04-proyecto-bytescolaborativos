package com.motorRecomendacionesAPI.motorRecomendaciones.service;

import com.motorRecomendacionesAPI.motorRecomendaciones.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    Product getProductById(UUID productId);

    List<Product> getProductsByTags(List<String> tags);

    List<Product> getTopPopularProducts();
}

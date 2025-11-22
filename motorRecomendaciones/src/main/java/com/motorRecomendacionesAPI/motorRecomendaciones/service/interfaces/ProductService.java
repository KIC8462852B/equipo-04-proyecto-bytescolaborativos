package com.motorRecomendacionesAPI.motorRecomendaciones.service.interfaces;

import com.motorRecomendacionesAPI.motorRecomendaciones.model.Product;

import java.util.UUID;

public interface ProductService {

    Product getProductById(UUID productId);
}

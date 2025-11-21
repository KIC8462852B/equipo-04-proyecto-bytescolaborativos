package com.motorRecomendacionesAPI.motorRecomendaciones.service.implementation;

import com.motorRecomendacionesAPI.motorRecomendaciones.exception.ProductNotFoundException;
import com.motorRecomendacionesAPI.motorRecomendaciones.model.Product;
import com.motorRecomendacionesAPI.motorRecomendaciones.repository.ProductRepository;
import com.motorRecomendacionesAPI.motorRecomendaciones.service.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    public final ProductRepository repository;

    @Override
    @Transactional(readOnly = true)
    public Product getProductById(UUID productId) {
        return repository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + productId + " not found."));
    }
}

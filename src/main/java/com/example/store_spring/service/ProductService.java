package com.example.store_spring.service;

import com.example.store_spring.models.Product;
import com.example.store_spring.models.dto.ProductCreateRequestDTO;
import com.example.store_spring.models.dto.ProductUpdateRequestDTO;
import com.example.store_spring.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.store_spring.util.InvalidException;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProductsService() {
        return productRepository.findAll();
    }

    public Product CreateAProducts(ProductCreateRequestDTO productReq) {
        Product product = modelMapper.map(productReq, Product.class);
        return productRepository.save(product);
    }

    public void deleteAProductByName(String productName) {
        Product product = productRepository.findByName(productName);

        if (product != null) {
            productRepository.delete(product);
        }
    }

    public Product updateAProducts(ProductUpdateRequestDTO productReq) throws InvalidException  {

        Product product = productRepository.findByName(productReq.getName());

        if (product == null) {
            throw new InvalidException("Product not found");
        }

        modelMapper.map(productReq, product);

        return productRepository.save(product);
    }
}

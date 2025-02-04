package com.example.store_spring.controller;


import com.example.store_spring.models.Product;
import com.example.store_spring.models.dto.ProductCreateRequestDTO;
import com.example.store_spring.models.dto.ProductUpdateRequestDTO;
import com.example.store_spring.models.respone.RestRespone;
import com.example.store_spring.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.store_spring.util.InvalidException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<RestRespone<Product>> CreateAProductController(@Valid @RequestBody ProductCreateRequestDTO productReq) {
        Product productRes =  productService.CreateAProducts(productReq);

        RestRespone<Product> respone = new RestRespone<>();
        respone.setData(productRes);
        respone.setStatusCode(200);
        respone.setMessage("Product created");

        return ResponseEntity.ok().body(respone);
    }

    @GetMapping
    public ResponseEntity<RestRespone<List<Product>>> getAllProductController() {
        List<Product> productList =  productService.getAllProductsService();

        RestRespone<List<Product>> respone = new RestRespone<>();
        respone.setData(productList);
        respone.setStatusCode(200);

        return ResponseEntity.ok().body(respone);
    }

    @DeleteMapping("/{nameProduct}")
    public ResponseEntity<RestRespone<String>> DeleteProductController(@PathVariable("nameProduct") String nameProduct) {
        productService.deleteAProductByName(nameProduct);

        RestRespone<String> respone = new RestRespone<>();
        respone.setStatusCode(200);
        respone.setMessage("Deleted product with name " + nameProduct + " successfully");

        return ResponseEntity.ok().body(respone);
    }

    @PutMapping
    public ResponseEntity<RestRespone<Product>> updateProductController(@Valid @RequestBody ProductUpdateRequestDTO productReq)  {
        Product product = productService.updateAProducts(productReq);
        RestRespone<Product> respone = new RestRespone<>();

        respone.setData(product);
        respone.setStatusCode(200);
        respone.setMessage("Product created");

        return ResponseEntity.ok().body(respone);

    }
}

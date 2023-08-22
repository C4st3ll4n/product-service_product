package br.com.ph.productservice.controller;

import br.com.ph.productservice.dto.ProductRequest;
import br.com.ph.productservice.dto.ProductResponse;
import br.com.ph.productservice.model.Product;
import br.com.ph.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest request) {
        productService.createProduct(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<Product> products = productService.getAll();
        List<ProductResponse> productResponse = products.stream().map(this::mapToResponse).toList();
        return ResponseEntity.ok(productResponse);
    }

    private ProductResponse mapToResponse(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice());
    }
}

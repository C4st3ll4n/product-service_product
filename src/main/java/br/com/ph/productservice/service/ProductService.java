package br.com.ph.productservice.service;

import br.com.ph.productservice.dto.ProductRequest;
import br.com.ph.productservice.dto.ProductResponse;
import br.com.ph.productservice.model.Product;
import br.com.ph.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductRequest request) {
        Product product = Product.builder()
                .name(request.name())
                .description(request.description())
                .price(request.price()).build();

        productRepository.save(product);
        log.info("Product {} created !", product.getId());

    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }
}

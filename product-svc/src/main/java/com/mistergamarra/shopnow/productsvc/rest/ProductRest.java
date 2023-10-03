package com.mistergamarra.shopnow.productsvc.rest;

import com.mistergamarra.shopnow.productsvc.dto.ProductDto;
import com.mistergamarra.shopnow.productsvc.service.ProductService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductRest {
    ProductService productService;

    public ProductRest(ProductService productService) {
        this.productService = productService;
    }

    @Cacheable(value="products", key = "#root.methodName")
    @GetMapping
     public List<ProductDto> getProducts(){
         return productService.findAll();
     }

    @Cacheable(value = "product", key = "#id")
    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id){
        return productService.getById(id);
    }

    @CacheEvict(value = "products", allEntries = true)
    @PostMapping
    public ProductDto addProduct(@RequestBody ProductDto product){
        return productService.save(product);
    }
}

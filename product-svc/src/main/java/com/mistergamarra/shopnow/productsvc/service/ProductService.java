package com.mistergamarra.shopnow.productsvc.service;

import com.mistergamarra.shopnow.productsvc.dto.ProductDto;
import com.mistergamarra.shopnow.productsvc.exception.BusinessException;
import com.mistergamarra.shopnow.productsvc.exception.NotFoundException;
import com.mistergamarra.shopnow.productsvc.model.Product;
import com.mistergamarra.shopnow.productsvc.repository.ProductRepository;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    ProductRepository productRepository;
    DozerBeanMapper dozerBeanMapper;

    public ProductService(ProductRepository productRepository, DozerBeanMapper dozerBeanMapper) {
        this.productRepository = productRepository;
        this.dozerBeanMapper = dozerBeanMapper;
    }

    public List<ProductDto> findAll(){
        List<Product> products = productRepository.findAll();
        List<ProductDto> productsDto = new ArrayList<>();
        products.forEach(p->{
            ProductDto productDto = dozerBeanMapper.map(p, ProductDto.class);
            productsDto.add(productDto);
        });
        return productsDto;
    }

    public ProductDto getById(Long id){
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()){
            Product p = product.get();
            return dozerBeanMapper.map(p, ProductDto.class);
        }
        throw new NotFoundException("product not found");
    }

    public ProductDto save(ProductDto productDto){
        Product product = dozerBeanMapper.map(productDto, Product.class);
        product.setUuid(UUID.randomUUID().toString());
        product.setCreatedAt(LocalDateTime.now(ZoneOffset.UTC));
        product.setModifiedAt(LocalDateTime.now(ZoneOffset.UTC));
        Product saved = productRepository.save(product);
        return dozerBeanMapper.map(saved, ProductDto.class);
    }


}

package com.mistergamarra.shopnow.productsvc.repository;

import com.mistergamarra.shopnow.productsvc.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.sound.sampled.Port;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

}

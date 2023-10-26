package com.app.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.app.entidad.Product;

@Repository
public interface ProductRespository extends MongoRepository<Product, Integer> {

}

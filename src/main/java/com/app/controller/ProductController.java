package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ProductDTO;
import com.app.servicio.ProductServicio;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductServicio productServicio;
	
	
	@GetMapping("/listar")
	public ResponseEntity<List<ProductDTO>> ListarProductos()	{
		
		return ResponseEntity.ok(productServicio.listarProductos());
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductDTO> verProducto(@PathVariable("id") int id){
		
		return ResponseEntity.ok(productServicio.verProducto(id));
		
	}	
	
	@PostMapping
	public ResponseEntity<ProductDTO>  guardarProducto(@RequestBody ProductDTO productDTO) {
		
		return ResponseEntity.ok(productServicio.guardarProducto(productDTO));
		
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<ProductDTO>  editarProducto(@PathVariable("id")int id ,@RequestBody ProductDTO productDTO) {
		
		return ResponseEntity.ok(productServicio.editarProducto(id, productDTO));
		
	}
	
	
	
	
	
	
}

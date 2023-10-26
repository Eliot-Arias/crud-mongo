package com.app.dao;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.ProductDTO;
import com.app.entidad.Product;
import com.app.repositorio.ProductRespository;
import com.app.servicio.ProductServicio;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class ProductImpl implements ProductServicio {

	@Autowired
	ProductRespository productRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	Gson gson;
	
	@Override
	public List<ProductDTO> listarProductos() {
		List<Product> productos = productRepository.findAll();
//
//	    String json = gson.toJson(productos, new TypeToken<List<Product>>() {}.getType());
//
//	    List<ProductDTO> productosDTO = gson.fromJson(json, new TypeToken<List<ProductDTO>>() {}.getType());
//	    
//		return productosDTO;
	    List<ProductDTO> productosDTO = productos.stream()
	        .map(this::mapearDTO)
	        .collect(Collectors.toList());
	    return productosDTO;
		
	}

	@Override
	public ProductDTO verProducto(int id) {
		// TODO Auto-generated method stub
		Product productoEncontrado = productRepository.findById(id).get();
		
		return mapearDTO(productoEncontrado);
	}

	@Override
	public ProductDTO guardarProducto(ProductDTO productoDTO) {
		int id = autoIncrement();
		Product product = mapearEntidad(productoDTO);
		product.setId(id);
		System.out.println(id);
		Product productGuardado = productRepository.save(product);
//		System.out.println(productGuardado.getNombre());
//		System.out.println(productGuardado.getPrecio());
		return mapearDTO(productGuardado);
	}
		
	@Override
	public ProductDTO editarProducto(int id, ProductDTO productoDTO) {
	    System.out.println("Entrando a editarProducto con ID: " + id);
	    
	    // Recupera el producto de la base de datos
	    Optional<Product> optionalProduct = productRepository.findById(id);

	    if (optionalProduct.isPresent()) {
	        Product product = optionalProduct.get();
	        System.out.println("Producto recuperado de la base de datos: " + product.getNombre());

	        // Verifica que los campos no sean nulos o vacíos
	        if (productoDTO.getNombre() != null && !productoDTO.getNombre().isEmpty()) {
	            product.setNombre(productoDTO.getNombre());
	        }
	        if (productoDTO.getPrecio() != null) {
	            product.setPrecio(productoDTO.getPrecio());
	        }

	        // Guarda el producto editado en la base de datos
	        Product editedProduct = productRepository.save(product);
	        System.out.println("Producto editado y guardado en la base de datos: " + editedProduct.getNombre());

	        return mapearDTO(editedProduct);
	    } else {
	        // Manejo de error si no se encuentra el producto con el ID proporcionado
	        System.out.println("Producto no encontrado con el ID proporcionado: " + id);
	        throw new RuntimeException("Producto no encontrado con el ID proporcionado: " + id);
	    }
	}


	@Override
	public ProductDTO eliminarProducto(int id) {
		Product product = productRepository.findById(id).get();
		productRepository.delete(product);
		return mapearDTO(product);	
	}
	
	private int autoIncrement() {
		List<Product> products = productRepository.findAll();
 		return products.isEmpty() ? 1 : products.stream().max(Comparator.comparing(Product::getId)).get().getId() + 1;
	}
	
	private ProductDTO mapearDTO(Product producto) {
		ProductDTO productoDTO = modelMapper.map(producto, ProductDTO.class);
		return productoDTO;
	}
	
	private Product mapearEntidad(ProductDTO productoDTO) {
		Product producto= modelMapper.map(productoDTO, Product.class);
		return producto;
	}

}

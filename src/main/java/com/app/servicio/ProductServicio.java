package com.app.servicio;

import java.util.List;


import com.app.dto.ProductDTO;


public interface ProductServicio {

	public List<ProductDTO> listarProductos();
	
	public ProductDTO verProducto(int id);
	
	public ProductDTO guardarProducto(ProductDTO productoDTO);
	
	public ProductDTO editarProducto(int id, ProductDTO productoDTO);
	
	public ProductDTO eliminarProducto(int id);
	
}

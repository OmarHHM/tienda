package service;

import java.util.List;

import dto.Producto;

public interface ProductoService {

	public List<Producto> consultarProductos();
	public Producto consultarProducto (int id);	
	public void agregarProducto(Producto prod);
	public boolean disminuyeInventario(Producto prod, int cantidad);
	
}

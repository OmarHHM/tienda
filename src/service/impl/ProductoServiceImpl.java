package service.impl;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dto.Empleado;
import dto.Producto;
import service.ProductoService;

public class ProductoServiceImpl implements ProductoService {

	private List<Producto> productos= new ArrayList<Producto>();

	@Override
	public List<Producto> consultarProductos() {
		return productos;
	}

	@Override
	public Producto consultarProducto(int id) {
		List<Producto> resultado= productos.stream().filter((prod)-> prod.getId()==id).collect(toList());
		return resultado.isEmpty()?null:resultado.get(0);	
	}

	@Override
	public void agregarProducto(Producto prod) {
		productos.add(prod);
		JOptionPane.showMessageDialog(null, "Producto agregado exitosamente id=" + prod.getId());
	}

	@Override
	public boolean disminuyeInventario(Producto producto, int cantidadSolicitada) {
		int cantidadAsignada= producto.getCantidad();
		if(cantidadSolicitada>cantidadAsignada) {
			return false;
		}else {
			producto.setCantidad(cantidadAsignada-cantidadSolicitada);
			productos.remove(producto.getId()-1);
			productos.add(producto.getId()-1, producto);
			return true;
		}
	}

}

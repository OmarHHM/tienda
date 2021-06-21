package dto;

public class Producto {
	
	private int id;
    private String descripcion;
    private Catalogo marca;
    private Catalogo categoria;
    private boolean disponible;
    private int cantidad;
    private double precio;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Catalogo getMarca() {
		return marca;
	}
	public void setMarca(Catalogo marca) {
		this.marca = marca;
	}
	public Catalogo getCategoria() {
		return categoria;
	}
	public void setCategoria(Catalogo categoria) {
		this.categoria = categoria;
	}
	public boolean isDisponible() {
		return disponible;
	}
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}



}

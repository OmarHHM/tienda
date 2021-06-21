package constroller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JOptionPane;

import dto.Catalogo;
import dto.Producto;
import service.ProductoService;
import service.impl.ProductoServiceImpl;
import util.Constants;
import util.Operacion;

public class Tienda extends Operacion {
	private static List<Catalogo> categorias = new ArrayList<Catalogo>();
	private static List<Catalogo> marcas = new ArrayList<Catalogo>();

	ProductoService productoService = new ProductoServiceImpl();
	private static AtomicInteger generadorIds;

	static {
		init();
		generadorIds = new AtomicInteger(0);
	}

	public static void init() {
		Catalogo catalogo = new Catalogo();
		catalogo.setId(1);
		catalogo.setDescripcion("CONSTRUCCION");
		categorias.add(catalogo);
		
		catalogo = new Catalogo();
		catalogo.setId(2);
		catalogo.setDescripcion("PLOMERIA");
		categorias.add(catalogo);
		
		catalogo = new Catalogo();
		catalogo.setId(3);
		catalogo.setDescripcion("ALBAÑILERIA");
		categorias.add(catalogo);
		
		catalogo = new Catalogo();
		catalogo.setId(4);
		catalogo.setDescripcion("CARPINTERIA");
		categorias.add(catalogo);
		
		
		catalogo = new Catalogo();
		catalogo.setId(1);
		catalogo.setDescripcion("TRUPPER");
		marcas.add(catalogo);
		

		catalogo = new Catalogo();
		catalogo.setId(2);
		catalogo.setDescripcion("PRETUL");
		marcas.add(catalogo);
		

		catalogo = new Catalogo();
		catalogo.setId(3);
		catalogo.setDescripcion("URREA");
		marcas.add(catalogo);
		

		catalogo = new Catalogo();
		catalogo.setId(4);
		catalogo.setDescripcion("HELVEX");
		marcas.add(catalogo);
	}

	
	@Override
	public void asignarOpcion() {
		while (true) {
			String[] seleccion = { Constants.VENTAS, Constants.MANTENIMIENTO_PROD, Constants.EXIT };
			String opcion = (String) JOptionPane.showInputDialog(null, "Selecciona una operación", "MI FERRETERA", JOptionPane.CLOSED_OPTION, null, seleccion, "MI FERRETERA");

			if (opcion.equalsIgnoreCase(Constants.MANTENIMIENTO_PROD)) {
				String[] opcionMantenimiento = { Constants.AGREGAR_PROD, Constants.LISTAR_PROD, Constants.CONSULTAR_PROD, Constants.EXIT };
				opcion = (String) JOptionPane.showInputDialog(null, "Selecciona una operación", "MI FERRETERA", JOptionPane.CLOSED_OPTION, null, opcionMantenimiento, "MI FERRETERA");

				switch (opcion) {
				case Constants.AGREGAR_PROD:
					incluirProducto();
					break;
				case Constants.LISTAR_PROD:
					List<Producto> productos = productoService.consultarProductos();
					String prods = "";
					if (productos.isEmpty()) {
						JOptionPane.showMessageDialog(null, "No existen productos en la Base de datos.");
					} else {
						for (Producto producto : productos) {
							prods += "---------------------------\n";
							prods += "Id : " + producto.getId() + "\n";
							prods += "Descripcion : " + producto.getDescripcion() + "\n";
							prods += "Precio : " + producto.getPrecio()+ "\n";
							prods += "Categoria : " + producto.getCategoria()+ "\n";
							prods += "Marca : " + producto.getMarca()+ "\n";
							prods += "Existencia : " + producto.getCantidad()+ "\n";
						}
						JOptionPane.showMessageDialog(null, "LISTADO DE PRODUCTOS\n" + prods);
					}
					break;
				case Constants.CONSULTAR_PROD:
					int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de producto a consultar"));
					String datosProducto = "";
					Producto prod = productoService.consultarProducto(id);
					if (null == prod) {
						JOptionPane.showMessageDialog(null, "El Producto con id =" + id + " no existe");
						break;
					}

					datosProducto += "---------------------------\n";
					datosProducto += "Descripcion : " + prod.getDescripcion() + "\n";
					datosProducto += "Precio : " + prod.getPrecio()+ "\n";
					datosProducto += "Categoria : " + prod.getCategoria()+ "\n";
					datosProducto += "Marca : " + prod.getMarca()+ "\n";
					datosProducto += "Existencia : " + prod.getCantidad()+ "\n";
					JOptionPane.showMessageDialog(null,"DATOS DEL PRODUCTO "+id+"\n"+ datosProducto);
					break;
				default:
					break;
				}

			} else if(opcion.equalsIgnoreCase(Constants.VENTAS)) {

				String[] opcionVentas = { "VENDER", "CONSULTAR TICKET", Constants.EXIT };
				opcion = (String) JOptionPane.showInputDialog(null, "Selecciona una operación", "MI FERRETERA", JOptionPane.CLOSED_OPTION, null, opcionVentas, "MI FERRETERA");
				
				switch (opcion) {
				
					case "VENDER":
						int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de producto."));
						Producto prod = productoService.consultarProducto(id);
						if (null == prod) {
							JOptionPane.showMessageDialog(null, "El Producto con id =" + id + " no existe");
							break;
						}
						int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de productos."));
						
						if(productoService.disminuyeInventario(prod, cantidad)) {
							JOptionPane.showMessageDialog(null, "Venta Exitosa");
						}else {
							JOptionPane.showMessageDialog(null, "No se pudo completar la venta, verifique inventario.");
						}
						
						break;
					case "CONSULTAR TICKET":
						break;
						
					default:  
						break;
				}
				
				
				
				
				
			}else {
				break;
			}
		}
	}

	private void incluirProducto() {
		Producto prod = new Producto();
		prod.setDescripcion(JOptionPane.showInputDialog("Ingrese la descripcion del producto"));

		Catalogo marca = (Catalogo) JOptionPane.showInputDialog(null, "Selecciona una marca", null, JOptionPane.QUESTION_MESSAGE, null, marcas.toArray(), "MI FERRETERA");
		prod.setMarca(marca);
		prod.setPrecio(Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio del producto")));
		prod.setCantidad(Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad existente del producto")));
		
		Catalogo categoria = (Catalogo) JOptionPane.showInputDialog(null, "Selecciona una categoria", null, JOptionPane.QUESTION_MESSAGE, null, categorias.toArray(), "MI FERRETERA");
		prod.setCategoria(categoria);
		prod.setId(generadorIds.incrementAndGet());
		productoService.agregarProducto(prod);
	}
}

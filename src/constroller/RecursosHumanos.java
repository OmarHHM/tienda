package constroller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JOptionPane;
import dto.Catalogo;
import dto.Empleado;
import service.EmpleadoService;
import service.impl.EmpleadoServiceImpl;
import util.Constants;
import util.Operacion;

public class RecursosHumanos extends Operacion {

	private static List<Catalogo> depto = new ArrayList<Catalogo>();
	private static List<Catalogo> puesto = new ArrayList<Catalogo>();
	EmpleadoService empleadoService = new EmpleadoServiceImpl();
	private static AtomicInteger generadorIds;	

	static {
		init();
		generadorIds= new AtomicInteger(0);
	}

	public static void init() {

		Catalogo catalogo = new Catalogo();

		catalogo.setId(1);
		catalogo.setDescripcion("CAJERO (A)");
		puesto.add(catalogo);

		catalogo = new Catalogo();
		catalogo.setId(2);
		catalogo.setDescripcion("GERENTE");
		puesto.add(catalogo);

		catalogo = new Catalogo();
		catalogo.setId(3);
		catalogo.setDescripcion("VENDEDOR (A)");
		puesto.add(catalogo);

		catalogo = new Catalogo();
		catalogo.setId(4);
		catalogo.setDescripcion("LIMPIEZA");
		puesto.add(catalogo);

		catalogo = new Catalogo();
		catalogo.setId(1);
		catalogo.setDescripcion("ADMINISTRATIVO");
		depto.add(catalogo);

		catalogo = new Catalogo();
		catalogo.setId(2);
		catalogo.setDescripcion("VENTAS");
		depto.add(catalogo);

		catalogo = new Catalogo();
		catalogo.setId(3);
		catalogo.setDescripcion("MATENIMIENTO");
		depto.add(catalogo);
	}

	@Override
	public void asignarOpcion() {
		while (true) {

			String[] seleccion = { Constants.AGREGAR_EMP, Constants.LISTAR_EMP, Constants.BUSQUEDA_POR_EMP, Constants.EXIT };
			String opcion = (String) JOptionPane.showInputDialog(null, "Selecciona una opción", "MI FERRETERA", JOptionPane.CLOSED_OPTION, null, seleccion, "MI FERRETERA");

			switch (opcion) {
			case Constants.AGREGAR_EMP:
				IncluirEmpleado();
				break;
			case Constants.LISTAR_EMP:
				List<Empleado> empleados = empleadoService.consultarEmpleados();
				String planilla = "";
				if (empleados.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Planilla vacia.");
				} else {
					for (Empleado emp : empleados) {
						planilla += "---------------------------\n";
						planilla += "Id : " + emp.getId() + "\n";
						planilla += "Nombre : " + emp.getNombre() + "\n";
						planilla += "Apellido Paterno: " + emp.getApellidoPaterno() + "\n";
						planilla += "Apellido Materno: " + emp.getApellidoMaterno() + "\n";
						planilla += "Salario: " + emp.getSalario() + "\n";
						planilla += "Departamento: " + emp.getDepartamento().getDescripcion() + "\n";
						planilla += "Puesto: " + emp.getPuesto().getDescripcion() + "\n";
					}

					JOptionPane.showMessageDialog(null, "LISTADO DE EMPLEADOS\n " + planilla);
				}
				break;

			case Constants.BUSQUEDA_POR_EMP:
				int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de empledo a consultar"));
				String datosEmpleado = "";
				Empleado emp = empleadoService.consultarEmpleado(id);
				if (null == emp) {
					JOptionPane.showMessageDialog(null, "El empleado con id =" + id + " no existe");
					break;
				}

				datosEmpleado += "---------------------------\n";
				datosEmpleado += "Nombre : " + emp.getNombre() + "\n";
				datosEmpleado += "Apellido Paterno: " + emp.getApellidoPaterno() + "\n";
				datosEmpleado += "Apellido Materno: " + emp.getApellidoMaterno() + "\n";
				datosEmpleado += "Salario: " + emp.getSalario() + "\n";
				datosEmpleado += "Departamento: " + emp.getDepartamento().getDescripcion() + "\n";
				datosEmpleado += "Puesto: " + emp.getPuesto().getDescripcion() + "\n";
				JOptionPane.showMessageDialog(null, "DATOS DEL EMPLEADO "+id+"\n"+datosEmpleado);
				break;
			default:
				break;
			}

			if (opcion.equals(Constants.EXIT))
				break;

		}

	}

	public void IncluirEmpleado() {
		Empleado empleado = new Empleado();
		empleado.setNombre(JOptionPane.showInputDialog("Ingrese el nombre del empleado"));
		empleado.setApellidoPaterno(JOptionPane.showInputDialog("Ingrese el apellido paterno del empleado"));
		empleado.setApellidoMaterno(JOptionPane.showInputDialog("Ingrese el apellido materno del empleado"));

		Catalogo departamento = (Catalogo) JOptionPane.showInputDialog(null, "Selecciona un departamento", null, JOptionPane.QUESTION_MESSAGE, null, depto.toArray(), "MI FERRETERA");
		empleado.setDepartamento(departamento);

		Catalogo cargo = (Catalogo) JOptionPane.showInputDialog(null, "Selecciona un puesto", null, JOptionPane.QUESTION_MESSAGE, null, puesto.toArray(), "MI FERRETERA");

		empleado.setPuesto(cargo);
		empleado.setSalario(Double.parseDouble(JOptionPane.showInputDialog("Ingrese el salario del empleado")));
		empleado.setId(generadorIds.incrementAndGet());
		empleadoService.agregarEmpleado(empleado);
	}

}

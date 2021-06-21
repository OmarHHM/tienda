package service.impl;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dto.Empleado;
import service.EmpleadoService;

public class EmpleadoServiceImpl implements EmpleadoService {

	private List<Empleado> empleados= new ArrayList<Empleado>();
	@Override
	public List<Empleado> consultarEmpleados() {
		return empleados;
	}

	@Override
	public void agregarEmpleado(Empleado emp) {
		empleados.add(emp);
		JOptionPane.showMessageDialog(null, "Empleado agregado exitosamente id=" + emp.getId());
	}

	@Override
	public Empleado consultarEmpleado(int id) {
		List<Empleado> resultado= empleados.stream().filter((emp)-> emp.getId()==id).collect(toList());
		return resultado.isEmpty()?null:resultado.get(0);
	}
}

package service;

import java.util.List;

import dto.Empleado;

public interface EmpleadoService {
	
	public List<Empleado> consultarEmpleados();
	public void agregarEmpleado(Empleado emp);
	public Empleado consultarEmpleado(int id);
	
}

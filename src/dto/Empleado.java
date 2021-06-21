package dto;

public  class Empleado {
	
	private int id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno; 
    private Catalogo departamento;
    private Catalogo puesto;
    private double salario;
    
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	
	public Catalogo getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Catalogo departamento) {
		this.departamento = departamento;
	}
	public Catalogo getPuesto() {
		return puesto;
	}
	public void setPuesto(Catalogo puesto) {
		this.puesto = puesto;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
    
    
}

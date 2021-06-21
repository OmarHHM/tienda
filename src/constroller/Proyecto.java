package constroller;

import javax.swing.JOptionPane;

import util.Constants;
import util.Operacion;

public class Proyecto {
	
    public static void main(String[] args) {	
        Operacion rhh = new RecursosHumanos();
        Operacion tienda = new Tienda();
        
        while (true) {        	
            String[] seleccion = {Constants.TIENDA, Constants.RHH, Constants.EXIT};
            String opcion = (String) JOptionPane.showInputDialog(null, "Bienvenido a MI FERRETERA",
                    "MI FERRETERA", JOptionPane.CLOSED_OPTION, null, seleccion, "MI FERRETERA");
            if (opcion.equals(Constants.TIENDA)) {
                tienda.asignarOpcion();
            } else if (opcion.equals(Constants.RHH)) {
                rhh.asignarOpcion();
            } else if (opcion.equals(Constants.EXIT)) {
                break;
            }
        }

        JOptionPane.showMessageDialog(null, "Gracias por utilizar nuestro programa", "MI FERRETERA", JOptionPane.INFORMATION_MESSAGE);

    }
}

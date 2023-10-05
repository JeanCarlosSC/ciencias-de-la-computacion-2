package control;

import javax.swing.JOptionPane;

import model.Dato;
import view.Frame;

public class App {
    Estructura estructura;

    public App() {
        new Frame(this) {
            @Override
            public void guardarEstructura(int rango, int tamanio, int caracteres, int tipo, int direccion,
                    int busqueda, int colision) {
                if (estructura == null) {
                    estructura = new Estructura(rango, tamanio, caracteres, tipo, direccion, busqueda, colision);
                } else {
                    estructura.setProperties(direccion, busqueda, colision);
                }
            }
        };
    }

    public int getDigitos() {
        return estructura.getDigitos();
    }

    public void insertar(int clave) {
        estructura.insertarClave(clave);
    }

    public Dato[] getDatos() {
        return estructura.getDatos();
    }

    public void buscar(int clave) {
        Dato ans = estructura.buscarClave(clave);
        if (ans == null) {
            JOptionPane.showMessageDialog(
                null,
                "La clave no se encuentra en la estructura de información",
                "Resultado de búsqueda",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
        else {
            JOptionPane.showMessageDialog(
                null,
                "La clave "+clave+" fue encontrada en la dirección "+ans.direccion,
                "Resultado de búsqueda",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
    }
}

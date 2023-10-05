package control;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Dato;
import model.busqueda.AlgoritmoBusqueda;
import model.busqueda.BusquedaInterna;
import model.direccion.AlgoritmoDireccion;

public class Estructura {
    private int n, N, nChar, tipo;
    private AlgoritmoDireccion aDir;
    private AlgoritmoBusqueda aBus;
    // private AlgoritmoColision aCol;
    private Dato[] datos;

    public Estructura(int rango, int tamanio, int caracteres, int tipo, int direccion, int busqueda, int colision) {
        n = rango;
        N = tamanio;
        nChar = caracteres;
        this.tipo = tipo;
        setProperties(direccion, busqueda, colision);

        datos = new Dato[N];
    }

    public void setProperties(int direccion, int busqueda, int colision) {
        if (tipo == 0) {
            switch (direccion) {
                case 0:
                    aDir = new AlgoritmoDireccion(direccion);
            }
            switch (busqueda) {
                case 0:
                    aBus = new BusquedaInterna(busqueda);
                    break;
            }
        }
    }

    public void insertarClave(int x) {
        if (!contains(x)) {
            int direccion = aDir.getDireccion(x, n);
            Dato d = new Dato(direccion, x, contains(x));
            datos[direccion] = d;
        }
        else {
            JOptionPane.showMessageDialog(
                null, "No se permiten claves repetidas", "Error", JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public Dato[] getDatos() {
        return datos;
    }

    private boolean contains(int x) {
        for (Dato dato : datos) {
            if (dato != null && dato.clave == x) {
                return true;
            }
        }
        return false;
    }

    public int getDigitos() {
        return nChar;
    }

    public Dato buscarClave(int clave) {
        try {
            return aBus.buscar(this, clave);
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

}

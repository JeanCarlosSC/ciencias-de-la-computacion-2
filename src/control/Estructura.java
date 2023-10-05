package control;

import java.util.Arrays;
import java.util.Comparator;

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
            aBus = new BusquedaInterna(busqueda);
        }
    }

    public void insertarClave(int x) {
        if (!contains(x)) {
            int direccion = aDir.getDireccion(x, N);
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
        Arrays.sort(
            datos, 0, N, Comparator.comparingInt(a -> a!=null?a.direccion:0));
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
            e.printStackTrace();
            return null;
        }
    }

    public int getRango() {
        return n;
    }

}

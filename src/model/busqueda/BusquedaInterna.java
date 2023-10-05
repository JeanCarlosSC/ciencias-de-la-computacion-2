package model.busqueda;

import java.util.ArrayList;
import java.util.Arrays;

import control.Estructura;
import model.Dato;

public class BusquedaInterna extends AlgoritmoBusqueda {
    public BusquedaInterna(int type) {
        super(type);
    }

    @Override
    public Dato binario(Estructura estructura, int x) throws AlgoritmoException {
        // carga claves en array list
        Dato[] v = estructura.getDatos();
        ArrayList array = new ArrayList<Integer>();
        int c = 0;
        for (Dato dato : v) {
            if(dato!=null) {
                array.add(dato.clave);
                c++;
            }
        }
        if(c == 0) {
            return null;
        }

        // carga array list en lista
        int[] lista = new int[c];
        for (int i=0; i<array.size(); i++) {
            lista[i] = (int) array.get(i);
        }
        Arrays.sort(lista);

        // asigna nuevas direcciones a las claves
        for (int i=0; i<lista.length; i++) {
            Dato d = secuencial(estructura, lista[i]);
            if(d != null) {
                d.direccion = i;
            }
        }

        return secuencial(estructura, x);
    }

    @Override
    public Dato hashCuadrado(Estructura estructura, int x) throws AlgoritmoException {
        throw new AlgoritmoException("Not implemented yet");
    }

    @Override
    public Dato hashMod(Estructura estructura, int x) throws AlgoritmoException {
        throw new AlgoritmoException("Not implemented yet");
    }

    @Override
    public Dato hashPlegamiento(Estructura estructura, int x) throws AlgoritmoException {
        throw new AlgoritmoException("Not implemented yet");
    }

    @Override
    public Dato hashTruncamiento(Estructura estructura, int x) throws AlgoritmoException {
        throw new AlgoritmoException("Not implemented yet");
    }

    @Override
    public Dato secuencial(Estructura estructura, int x) throws AlgoritmoException {
        Dato[] ref = estructura.getDatos();

        int i = 0;
        int n = ref.length;
        while (i < n) {
            if(ref[i] != null && ref[i].clave == x) {
                break;
            }
            i++;
        }
        if (i >= n) {
            return null;
        } else {
            return ref[i];
        }
    }
}

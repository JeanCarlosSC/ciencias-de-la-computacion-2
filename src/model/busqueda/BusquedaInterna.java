package model.busqueda;

import control.Estructura;
import model.Dato;

public class BusquedaInterna extends AlgoritmoBusqueda {
    @Override
    public Dato binario(Estructura estructura, int x) throws AlgoritmoException {
        Dato[] ref = estructura.getDatos();

        int i = 0;
        int n = ref.length;
        while (i < n && ref[i].clave != x) {
            i++;
        }
        if (i >= n) {
            return null;
        } else {
            return ref[i];
        }
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
        throw new AlgoritmoException("Not implemented yet");
    }
}

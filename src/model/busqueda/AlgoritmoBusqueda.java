package model.busqueda;

import control.Estructura;
import model.Dato;

public abstract class AlgoritmoBusqueda {
    int type;
    public AlgoritmoBusqueda(int type) {
        this.type = type;
    }

    public Dato buscar(Estructura estructura, int clave) throws AlgoritmoException {
        switch(type) {
            case 0:
                return secuencial(estructura, clave);
            default:
                return binario(estructura, clave);
        }
    }
    public Dato binario(Estructura estructura, int x) throws AlgoritmoException {
        throw new AlgoritmoException("Not implemented yet");
    }
    public Dato hashCuadrado(Estructura estructura, int x) throws AlgoritmoException {
        throw new AlgoritmoException("Not implemented yet");
    }
    public Dato hashMod(Estructura estructura, int x) throws AlgoritmoException {
        throw new AlgoritmoException("Not implemented yet");
    }
    public Dato hashPlegamiento(Estructura estructura, int x) throws AlgoritmoException {
        throw new AlgoritmoException("Not implemented yet");
    }
    public Dato hashTruncamiento(Estructura estructura, int x) throws AlgoritmoException {
        throw new AlgoritmoException("Not implemented yet");
    }
    public Dato secuencial(Estructura estructura, int x) throws AlgoritmoException {
        throw new AlgoritmoException("Not implemented yet");
    }
    public Dato transformacionBases(Estructura estructura, int x) throws AlgoritmoException {
        throw new AlgoritmoException("Not implemented yet");
    }
}

class AlgoritmoException extends Exception {
    public AlgoritmoException(String message) {
        super(message);
    }
}
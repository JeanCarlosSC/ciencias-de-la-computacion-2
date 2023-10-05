package model.busqueda;

import control.Estructura;
import model.Dato;

public abstract class AlgoritmoBusqueda {
    int type;

    public AlgoritmoBusqueda(int type) {
        this.type = type;
    }

    public Dato buscar(Estructura estructura, int clave) throws AlgoritmoException {
        switch (type) {
            case 0:
                return secuencial(estructura, clave);
            case 1:
                return binario(estructura, clave);
            case 2:
                return hashMod(estructura, clave);
            case 3:
                return hashCuadrado(estructura, clave);
            case 4:
                return hashPlegamiento(estructura, clave);
            case 5:
                return hashTruncamiento(estructura, clave);
            default:
                return transformacionBases(estructura, clave);
        }
    }

    public Dato secuencial(Estructura estructura, int x) throws AlgoritmoException {
        throw new AlgoritmoException("Not implemented yet");
    }

    public Dato binario(Estructura estructura, int x) throws AlgoritmoException {
        throw new AlgoritmoException("Not implemented yet");
    }

    public Dato hashMod(Estructura estructura, int x) throws AlgoritmoException {
        throw new AlgoritmoException("Not implemented yet");
    }

    public Dato hashCuadrado(Estructura estructura, int x) throws AlgoritmoException {
        throw new AlgoritmoException("Not implemented yet");
    }

    public Dato hashPlegamiento(Estructura estructura, int x) throws AlgoritmoException {
        throw new AlgoritmoException("Not implemented yet");
    }

    public Dato hashTruncamiento(Estructura estructura, int x) throws AlgoritmoException {
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
package model.busqueda;

import model.estructura.Estructura

import model.Dato;
import model.direccion.DirInterna

class BusquedaInterna(type: Int) : AlgoritmoBusqueda(type) {
    val dir = DirInterna()

    /**
     * x: clave
     */
    override fun secuencial(estructura: Estructura, x: Int): Dato? {
        val lista = mutableListOf<Dato>()

        for (i in estructura.getDatos()) {
            for (j in i) {
                if(j!=null) {
                    lista.add(j)
                }
            }
        }

        var i = 0;
        val n = lista.size;
        while (i < n) {
            if(lista[i].clave == x) {
                break;
            }
            i++;
        }
        return if (i >= n) {
            null;
        } else {
            lista[i];
        }
    }

    override fun binario(estructura: Estructura, x: Int): Dato? {
        val lista = mutableListOf<Dato>()

        for (i in estructura.getDatos()) {
            for (j in i) {
                if(j!=null) {
                    lista.add(j)
                }
            }
        }
        lista.sortBy { t -> t.clave }
        lista.forEachIndexed { i, dato ->  dato.dir = i}

        return secuencial(estructura, x);
    }

    override fun hashMod(estructura: Estructura, x: Int): Dato? {
        val v = estructura.getDatos()
        var dir = dir.hashMod(x, estructura.N);

        if(v[dir] == null) {
            return null
        }
        else if(v[dir][0]!!.clave == x) {
            return v[dir][0]
        }
        else {
            for (i in 0 until v.size) {
                dir++
                if(dir == v.size) {
                    dir = 0
                }
                if(v[dir] == null) {
                    return null
                }
                for (dato in v[dir]) {
                    if (dato!!.clave == x) {
                        return dato
                    }
                }
            }
        }

        return secuencial(estructura, x);
    }

    override fun hashCuadrado(estructura: Estructura, x: Int): Dato? {
        /*
        // actualiza direcciones con hashCuadrado
        for (Dato dato : estructura.getDatos()) {
            if(dato != null) {
                dato.dir = AlgoritmoDireccion.fHashCuadrado(dato.clave, estructura.getRango());
            }
        }

        // busqueda
        return secuencial(estructura, x);*/
        return null
    }

    override fun hashPlegamiento(estructura: Estructura, x: Int): Dato? {
        // actualiza direcciones con hashPlegamiento
        /*
        for (Dato dato : estructura.getDatos()) {
            if(dato != null) {
                dato.dir = AlgoritmoDireccion.fHashPlegamiento(dato.clave, estructura.getRango());
            }
        }

        // busqueda
        return secuencial(estructura, x);*/
        return null
    }

    override fun hashTruncamiento(estructura: Estructura, x: Int): Dato? {
        // busqueda
        //return secuencial(estructura, x);
        return null
    }

}

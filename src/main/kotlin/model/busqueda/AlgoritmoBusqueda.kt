package model.busqueda

import model.estructura.Estructura
import model.Dato

abstract class AlgoritmoBusqueda(private var type: Int) {
    @Throws(AlgoritmoException::class)
    fun buscar(estructura: Estructura, clave: Int): Dato? {
        return when (type) {
            0 -> secuencial(estructura, clave)
            1 -> binario(estructura, clave)
            2 -> hashMod(estructura, clave)
            3 -> hashCuadrado(estructura, clave)
            4 -> hashPlegamiento(estructura, clave)
            5 -> hashTruncamiento(estructura, clave)
            6 -> transformacionBases(estructura, clave)
            else -> throw AlgoritmoException("Tipo de algoritmo de búsqueda inválido")
        }
    }

    @Throws(AlgoritmoException::class)
    open fun secuencial(estructura: Estructura, x: Int): Dato? {
        throw AlgoritmoException("Not implemented yet")
    }

    @Throws(AlgoritmoException::class)
    open fun binario(estructura: Estructura, x: Int): Dato? {
        throw AlgoritmoException("Not implemented yet")
    }

    @Throws(AlgoritmoException::class)
    open fun hashMod(estructura: Estructura, x: Int): Dato? {
        throw AlgoritmoException("Not implemented yet")
    }

    @Throws(AlgoritmoException::class)
    open fun hashCuadrado(estructura: Estructura, x: Int): Dato? {
        throw AlgoritmoException("Not implemented yet")
    }

    @Throws(AlgoritmoException::class)
    open fun hashPlegamiento(estructura: Estructura, x: Int): Dato? {
        throw AlgoritmoException("Not implemented yet")
    }

    @Throws(AlgoritmoException::class)
    open fun hashTruncamiento(estructura: Estructura, x: Int): Dato? {
        throw AlgoritmoException("Not implemented yet")
    }

    @Throws(AlgoritmoException::class)
    fun transformacionBases(estructura: Estructura, x: Int): Dato? {
        throw AlgoritmoException("Not implemented yet")
    }
}

internal class AlgoritmoException(message: String) : Exception(message)
package model.estructura

import model.Dato
import model.busqueda.AlgoritmoBusqueda
import model.busqueda.AlgoritmoException
import model.busqueda.BusquedaExterna
import model.busqueda.BusquedaInterna
import model.direccion.AlgoritmoDireccion
import model.direccion.DireccionExterna
import model.direccion.DirInterna

const val ESTRUCTURA_INTERNA = 0
const val ESTRUCTURA_EXTERNA = 1

abstract class Estructura(
    val type: Int,
    val rango: Int,
    val N: Int,
    val digitos: Int,
    private val tipo: Int,
    dirType: Int,
    busqueda: Int,
    colision: Int
) {
    var aDir: AlgoritmoDireccion? = null
    var aBus: AlgoritmoBusqueda? = null
    var espaciosOcupados = 0

    abstract fun insertarClave(x: Int)
    abstract fun estaOcupada(dir: Int): Boolean
    abstract fun hayEspacioDisponible(): Boolean
    abstract fun estaClave(clave: Int): Boolean

    open fun getDatosInternos(): MutableList<MutableList<Dato?>> {
        throw AlgoritmoException("Not implemented yet")
    }

    open fun getDatosExternos(): MutableList<MutableList<Dato?>> {
        throw AlgoritmoException("Not implemented yet")
    }

    init {
        setProperties(dirType, busqueda, colision)
    }

    fun setProperties(dirType: Int, busqueda: Int, colision: Int) {
        if (tipo == ESTRUCTURA_INTERNA) {
            aDir = DirInterna(dirType)
            aBus = BusquedaInterna(busqueda)
        }
        else if(tipo == ESTRUCTURA_EXTERNA) {
            aDir = DireccionExterna(dirType)
            aBus = BusquedaExterna(busqueda)
        }
    }

    fun buscarClave(clave: Int): Dato? {
        return try {
            aBus!!.buscar(this, clave)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
    fun getDatos(): MutableList<MutableList<Dato?>> {
        return if (type == ESTRUCTURA_INTERNA) {
            return getDatosInternos()
        }
        else {
            return getDatosExternos()
        }
    }

    abstract fun getDatosParaMostrar(): MutableList<Dato?>?
}

fun crearEstructura(
    type: Int,
    rango: Int,
    size: Int,
    caracteres: Int,
    tipo: Int,
    direccion: Int,
    busqueda: Int,
    colision: Int
): Estructura {
    if(type == ESTRUCTURA_INTERNA) {
        return EstructuraInterna(rango, size, caracteres, tipo, direccion, busqueda, colision)
    }
    else {
        return EstructuraExterna(rango, size, caracteres, tipo, direccion, busqueda, colision)
    }
}

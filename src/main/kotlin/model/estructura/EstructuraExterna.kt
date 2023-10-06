package model.estructura

import model.Dato

class EstructuraExterna(
    rango: Int,
    tamanio: Int,
    caracteres: Int,
    tipo: Int,
    direccion: Int,
    busqueda: Int,
    colision: Int
) : Estructura(ESTRUCTURA_EXTERNA, rango, tamanio, caracteres, tipo, direccion, busqueda, colision) {
    override fun insertarClave(x: Int) {
        TODO("Not yet implemented")
    }

    override fun estaOcupada(dir: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun hayEspacioDisponible(): Boolean {
        TODO("Not yet implemented")
    }

    override fun estaClave(clave: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun getDatosExternos(): MutableList<MutableList<Dato?>> {
        TODO("Not yet implemented")
    }

    override fun getDatosParaMostrar(): MutableList<Dato?>? {
        return null
    }

    override fun setDatos(newDatos: MutableList<MutableList<Dato?>>) {
        TODO("Not yet implemented")
    }

}

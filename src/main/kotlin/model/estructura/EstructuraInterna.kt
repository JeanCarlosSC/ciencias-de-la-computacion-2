package model.estructura

import model.Dato
import javax.swing.JOptionPane

class EstructuraInterna(rango: Int, N: Int, digitos: Int, tipo: Int, direccion: Int, busqueda: Int, colision: Int) :
    Estructura(ESTRUCTURA_INTERNA, rango, N, digitos, tipo, direccion, busqueda, colision) {
    private val datos = MutableList(N) {
        mutableListOf<Dato?>()
    }

    override fun insertarClave(clave: Int) {
        val dir = aDir!!.getDir(clave, N)

        if(estaClave(clave)) {
            JOptionPane.showMessageDialog(
                null,
                "No se permiten claves repetidas",
                "Error",
                JOptionPane.ERROR_MESSAGE
            )
        }
        else if(!hayEspacioDisponible()) {
            JOptionPane.showMessageDialog(
                null,
                "No hay espacio disponible en la estructura",
                "Error",
                JOptionPane.ERROR_MESSAGE
            )
        }
        else {
            datos[dir].add(Dato(dir, clave))
            espaciosOcupados++
        }
    }

    override fun estaOcupada(dir: Int): Boolean {
        return datos[dir][0] != null
    }

    override fun hayEspacioDisponible(): Boolean {
        return espaciosOcupados < N
    }

    override fun estaClave(clave: Int): Boolean {
        for (fila in datos) {
            for (valor in fila) {
                if(valor != null && valor.clave == clave) {
                    return true
                }
            }
        }
        return false
    }

    override fun getDatosInternos(): MutableList<MutableList<Dato?>> {
        return datos
    }

    override fun getDatosParaMostrar(): MutableList<Dato?>? {
        val ans = mutableListOf<Dato?>()
        for (i in datos) {
            if(i.size>0) {
                for (j in i) {
                    ans.add(j)
                }
            }
        }
        return ans
    }
}
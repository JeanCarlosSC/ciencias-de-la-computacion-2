package control

import model.Dato
import model.busqueda.BUSQUEDA_HASH_MOD
import model.direccion.DirInterna
import model.estructura.Estructura
import model.estructura.crearEstructura
import service.Logic
import view.Frame
import javax.swing.JOptionPane

class App {
    val frame = Frame(this)
    var estructura: Estructura? = null

    val digitos: Int
        get() = estructura!!.digitos

    fun insertar(clave: Int) {
        estructura!!.insertarClave(clave)
    }

    val datos: MutableList<MutableList<Dato?>>
        get() = estructura!!.getDatos()

    fun buscar(clave: Int) {
        val ans = estructura!!.buscarClave(clave)
        frame.vDashboard.refrescar(estructura!!.getDatos(), estructura!!.digitos)
        if (ans == null) {
            JOptionPane.showMessageDialog(
                null,
                "La clave no se encuentra en la estructura de información",
                "Resultado de búsqueda",
                JOptionPane.INFORMATION_MESSAGE
            )
        } else {
            JOptionPane.showMessageDialog(
                null,
                "La clave " + clave + " fue encontrada en la dirección " + ans.dir,
                "Resultado de búsqueda",
                JOptionPane.INFORMATION_MESSAGE
            )
        }
    }

    fun guardarEstructura() {
        val rango = frame.vStructure.tfRango!!.getText()
        val size = frame.vStructure.tfSize!!.getText()
        val nDigits = frame.vStructure.tfDigitos!!.getText()
        val type = frame.vStructure.cbTipo!!.selectedIndex

        // Validate rango
        if (rango.isEmpty() || !Logic.isInt(rango) || rango.toInt() < 100 || rango.toInt() > 1000) {
            JOptionPane.showMessageDialog(
                null,
                "El rango debe ser un número entero positivo mayor o igual a 100 y menor o igual que 1000",
                "Error",
                JOptionPane.ERROR_MESSAGE
            )
        }
        // Validate size
        else if (size.isEmpty() || !Logic.isInt(size) || size.toInt() > rango.toInt()) {
            JOptionPane.showMessageDialog(
                null,
                "El tamaño debe ser un número entero positivo menor o igual al rango",
                "Error",
                JOptionPane.ERROR_MESSAGE
            )
        }
        // Validate nDigits
        else if (nDigits.isEmpty() || !Logic.isInt(nDigits) || nDigits.toInt() < 4 || nDigits.toInt() > 9) {
            JOptionPane.showMessageDialog(
                null,
                "La cantidad de caracteres debe ser un número entero positivo mayor que 3 y menor que 10",
                "Error",
                JOptionPane.ERROR_MESSAGE
            )
        }
        // Validate type
        else if (type == 1 && size.toInt() < 100) {
            JOptionPane.showMessageDialog(
                null,
                "El tamaño de una estructura externa debe ser un número entero positivo mayor o igual a 100",
                "Error",
                JOptionPane.ERROR_MESSAGE
            )
        }
        // Crea estructura
        else {
            val dirType = frame.vStructure.cbDir!!.selectedIndex
            val busType = frame.vStructure.cbBusqueda!!.selectedIndex
            val colType = frame.vStructure.cbColision!!.selectedIndex

            JOptionPane.showMessageDialog(
                null,
                "La estructura de datos ha sido guardada satisfactoriamente",
                "Mensaje informativo",
                JOptionPane.INFORMATION_MESSAGE
            )

            if(estructura == null) {
                estructura = crearEstructura(
                    type,
                    rango.toInt(),
                    size.toInt(),
                    nDigits.toInt(),
                    type,
                    dirType,
                    busType,
                    colType
                )
            }
            else {
                estructura!!.setProperties(dirType, busType, colType)
            }

            if(busType == BUSQUEDA_HASH_MOD) {
                for (i in estructura!!.getDatos()) {
                    if(i.size>0) {
                        var pos = 0
                        for (j in i) {
                            //j!!.dir = estructura!!.aDir!!.getDir(j!!.clave, estructura!!.N)+pos
                            //pos++
                        }
                    }
                }
                frame.vDashboard.refrescar(estructura!!.getDatos(), digitos)
            }
            frame.contentPane = frame.vDashboard
            frame.vStructure.setCreated()
            frame.vDashboard.refrescar(estructura!!.getDatos(), digitos)
        }
    }
}

package view

import control.App
import service.Logic
import java.awt.Font
import javax.swing.*

class Frame(app: App) : JFrame() {
    // Vistas
    val vWelcome: VWelcome = object: VWelcome() {
        override fun bCrear() {
            contentPane = vStructure
        }
    }
    val vStructure = object: VStructure() {
        override fun gotoMenu() {
            contentPane = vWelcome
        }

        override fun bGuardar() {
            app.guardarEstructura()
        }
    }
    val vDashboard = object : VDashboard() {
        override fun settings() {
            contentPane = vStructure
        }

        override fun insertar(clave: String?) {
            if (clave!!.isEmpty() || !Logic.isInt(clave) || clave.length != app.digitos || clave.toInt() < 0) {
                JOptionPane.showMessageDialog(
                    null,
                    "La clave debe ser un número entero positivo de " + app.digitos + " dígitos",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                )
            } else {
                app.insertar(clave.toInt())
                refrescar(app.estructura!!.getDatosParaMostrar()!!, app.digitos)
            }
        }

        override fun buscar(clave: String?) {
            if (clave!!.isEmpty() || !Logic.isInt(clave) || clave.length != app.digitos || clave.toInt() < 0) {
                JOptionPane.showMessageDialog(
                    null,
                    "La clave debe ser un número entero positivo de " + app.digitos + " dígitos",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                )
            } else {
                app.buscar(clave.toInt())
                //vDashboard!!.refrescar(app.datos, app.digitos)
            }
        }
    }

    // Componentes
    var lTitle: JLabel? = null
    var pContent: JPanel? = null
    var p1: JPanel? = null
    var p2: JPanel? = null
    var p3: JPanel? = null
    var data: ArrayList<String>? = null
    var text = Font("Arial", Font.PLAIN, 20)
    var cbAlgoritmos: JComboBox<*>? = null

    init {
        contentPane = vWelcome
        setSize(1280, 720)
        layout = null
        setLocationRelativeTo(null)
        setDefaultCloseOperation(EXIT_ON_CLOSE)
        isVisible = true
    }
}
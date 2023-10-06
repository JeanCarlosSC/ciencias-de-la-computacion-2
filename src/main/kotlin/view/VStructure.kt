package view

import service.ObjGraficos
import service.Recursos
import java.util.*
import javax.swing.JComboBox
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField

abstract class VStructure : JPanel() {
    // Options
    private val vTipo = Vector<String>()
    private val vDir = Vector<String>()
    private val vBusqueda = Vector<String>()
    private val vColision = Vector<String>()

    // TextFields
    var tfSize: JTextField? = null
    var tfRango: JTextField? = null
    var tfDigitos: JTextField? = null

    var cbTipo: JComboBox<String>? = null
    var cbDir: JComboBox<String>? = null
    var cbBusqueda: JComboBox<String>? = null
    var cbColision: JComboBox<String>? = null

    private val x = 300
    private val y = 200

    // Services
    abstract fun gotoMenu()
    abstract fun bGuardar()

    init {
        loadLabels()
        loadTextField()
        loadComboBoxes()
        loadButtons()

        this.setSize(1280, 720)
        this.setLayout(null)
    }

    private fun loadButtons() {
        val bGuardar = ObjGraficos.getButton(x + 500, 322 + y, "Guardar")
        bGuardar.addActionListener {bGuardar()}
        add(bGuardar)

        val bEliminar = ObjGraficos.getButton(x + 300, 322 + y, "Eliminar")
        bEliminar.addActionListener { gotoMenu() }
        add(bEliminar)

        cbTipo!!.addActionListener {
            if (cbTipo!!.getSelectedIndex() == 1) {
                vDir.add("Hash transformación de bases")
                vBusqueda.add("Búsqueda hash transformación de bases")
                vColision.remove("Lineal")
                vColision.remove("Cuadrática")
                vColision.remove("Doble dirección hash")
                vColision.remove("Arreglos anidados")
                vColision.remove("Encadenamiento")
                vColision.add("Áreas independientes")
                vColision.add("Áreas de colisiones")
                cbColision!!.setSelectedIndex(1)
            } else {
                vDir.remove("Hash transformación de bases")
                vBusqueda.remove("Búsqueda hash transformación de bases")
                vColision.add("Lineal")
                vColision.add("Cuadrática")
                vColision.add("Doble dirección hash")
                vColision.add("Arreglos anidados")
                vColision.add("Encadenamiento")
                vColision.remove("Áreas independientes")
                vColision.remove("Áreas de colisiones")
                cbColision!!.setSelectedIndex(0)
            }
        }
    }

    private fun loadTextField() {
        tfRango = ObjGraficos.getTextField(x + 550, 32 + y)
        add(tfRango)
    }

    private fun loadComboBoxes() {
        // Tipo
        vTipo.add("Estructura interna")
        vTipo.add("Estructura externa")

        cbTipo = JComboBox(vTipo)
        cbTipo!!.setBounds(x + 550, 152 + y, 300, 32)
        add(cbTipo)

        // Direccion
        vDir.add("Hash mod")
        vDir.add("Hash cuadrado")
        vDir.add("Hash plegamiento")
        vDir.add("Hash truncamiento")

        cbDir = JComboBox(vDir)
        cbDir!!.setBounds(x + 550, 192 + y, 300, 32)
        add(cbDir)

        // Busqueda
        vBusqueda.add("Búsqueda secuencial")
        vBusqueda.add("Búsqueda binaria")
        vBusqueda.add("Búsqueda hash mod")
        vBusqueda.add("Búsqueda hash cuadrado")
        vBusqueda.add("Búsqueda hash plegamiento")
        vBusqueda.add("Búsqueda hash truncamiento")

        cbBusqueda = JComboBox(vBusqueda)
        cbBusqueda!!.setBounds(x + 550, 232 + y, 300, 32)
        add(cbBusqueda)

        // Colision
        vColision.add("Lineal")
        vColision.add("Cuadrática")
        vColision.add("Doble dirección hash")
        vColision.add("Arreglos anidados")
        vColision.add("Encadenamiento")

        cbColision = JComboBox(vColision)
        cbColision!!.setBounds(x + 550, 272 + y, 300, 32)
        //add(cbColision)
    }

    private fun loadLabels() {
        val lTitulo = JLabel("Datos de la estructura")
        lTitulo.setBounds(x + 150, 100, 500, 50)
        lTitulo.setFont(Recursos.fTitle)
        add(lTitulo)

        val lRango = JLabel("Rango de las direcciones de memoria (n)")
        lRango.setFont(Recursos.fText)
        lRango.setBounds(x, 32 + y, 500, 32)
        add(lRango)

        val lSize = JLabel("Tamaño de la estructura (N)") // N < n
        lSize.setBounds(x, 72 + y, 500, 32)
        lSize.setFont(Recursos.fText)
        add(lSize)
        tfSize = ObjGraficos.getTextField(x + 550, 72 + y)
        add(tfSize)

        val lDigitos = JLabel("Cantidad de caracteres de cada clave")
        lDigitos.setBounds(x, 112 + y, 500, 32)
        lDigitos.setFont(Recursos.fText)
        add(lDigitos)
        tfDigitos = ObjGraficos.getTextField(x + 550, 112 + y)
        add(tfDigitos)

        val lTipo = JLabel("Tipo de estructura")
        lTipo.setBounds(x, 152 + y, 500, 32)
        lTipo.setFont(Recursos.fText)
        add(lTipo)

        val lDireccion = JLabel("Algoritmo para asignar direcciones de memoria")
        lDireccion.setBounds(x, 192 + y, 500, 32)
        lDireccion.setFont(Recursos.fText)
        add(lDireccion)

        val lBusqueda = JLabel("Algoritmo para realizar búsqueda de claves")
        lBusqueda.setBounds(x, 232 + y, 500, 32)
        lBusqueda.setFont(Recursos.fText)
        add(lBusqueda)

        val lColision = JLabel("Algoritmo para solucionar colisión de datos en memoria")
        lColision.setBounds(x, 272 + y, 500, 32)
        lColision.setFont(Recursos.fText)
        //add(lColision)
    }

    fun setCreated() {
        tfSize!!.setEnabled(false)
        tfRango!!.setEnabled(false)
        tfDigitos!!.setEnabled(false)
        cbTipo!!.setEnabled(false)
    }
}

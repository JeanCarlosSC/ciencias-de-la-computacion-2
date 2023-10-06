package view

import model.Dato
import service.ObjGraficos
import service.Recursos
import java.awt.Color
import java.awt.Dimension
import java.awt.event.ActionEvent
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JScrollPane

abstract class VDashboard : JPanel() {
    var pEstructura: JPanel? = null
    abstract fun settings()
    abstract fun insertar(clave: String?)
    abstract fun buscar(clave: String?)

    init {
        setSize(1280, 720)
        setLayout(null)
        loadComponents()
        refrescar(mutableListOf(), 0)
    }

    private fun loadComponents() {
        val lTitle = JLabel("Estructura de datos")
        lTitle.setFont(Recursos.fTitle)
        lTitle.setBounds(145, 48, 335, 44)
        add(lTitle)
        pEstructura = JPanel(null)
        pEstructura!!.setBounds(0, 0, 2000, 4500)
        pEstructura!!.preferredSize = Dimension(2000, 4500)
        pEstructura!!.setBackground(Color.WHITE)
        val pScroll = JScrollPane(pEstructura)
        pScroll.setBounds(168, 137, 245, 501)
        pScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS)
        pScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS)
        add(pScroll)
        val lBClave = JLabel("Buscar clave")
        lBClave.setBounds(640, 212, 144, 29)
        lBClave.setFont(Recursos.fText)
        add(lBClave)
        val lIClave = JLabel("Insertar clave")
        lIClave.setBounds(640, 287, 154, 29)
        lIClave.setFont(Recursos.fText)
        add(lIClave)
        val tfB = ObjGraficos.getTextField(838, 212)
        tfB.addActionListener { e: ActionEvent? ->
            buscar(tfB.getText())
            tfB.text = ""
        }
        add(tfB)
        val tfI = ObjGraficos.getTextField(838, 287)
        tfI.addActionListener { e: ActionEvent? ->
            insertar(tfI.getText())
            tfI.text = ""
        }
        add(tfI)
        val bSettings = ObjGraficos.getButton(787, 454, "Ajustes")
        bSettings.addActionListener { e: ActionEvent? -> settings() }
        add(bSettings)
    }

    fun refrescar(datos: MutableList<Dato?>, nDigitos: Int) {
        pEstructura!!.removeAll()
        val lDir = JLabel("Dirección")
        lDir.setBounds(32, 32, 100, 32)
        lDir.setFont(Recursos.fText)
        pEstructura!!.add(lDir)

        val lClave = JLabel("Clave")
        lClave.setBounds(142, 32, 100, 32)
        lClave.setFont(Recursos.fText)
        pEstructura!!.add(lClave)
        if (datos.size == 0) {
            return
        }
        var y = 0
        for (i in datos.indices) {
            if (datos[i] != null) {
                val lDirA = JLabel(datos[i]!!.dir.toString() + "")
                lDirA.setBounds(32, 64 + 32 * y, 100, 32)
                lDirA.setFont(Recursos.fText)
                pEstructura!!.add(lDirA)
                val lClaveA = JLabel(String.format("%0" + nDigitos + "d", datos[i]!!.clave))
                lClaveA.setBounds(142, 64 + 32 * y, 100, 32)
                lClaveA.setFont(Recursos.fText)
                pEstructura!!.add(lClaveA)
                y++
            }
        }
        repaint()
    }
}

package view

import java.awt.Font
import java.awt.event.ActionEvent
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextArea

abstract class VWelcome : JPanel() {
    init {
        setSize(1280, 720)
        setLayout(null)
        val lBienvenido = JLabel("¡Bienvenido!")
        lBienvenido.setBounds(400, 200, 400, 64)
        lBienvenido.setFont(Font("Arial", Font.BOLD, 64))
        add(lBienvenido)
        val lText =
            JTextArea("Al sistema de información numérica CC2, presione el botón de Crear para crear una nueva estructura de datos")
        lText.setBounds(220, lBienvenido.y + lBienvenido.height + 32, 800, 60)
        lText.setBackground(null)
        lText.setFocusable(false)
        lText.isEditable = false
        lText.setLineWrap(true)
        lText.setFont(Font("Arial", Font.ITALIC, 24))
        add(lText)
        val bCrear = JButton("Crear")
        bCrear.setBounds(550, 450, 100, 32)
        bCrear.setFont(lText.font)
        bCrear.setFocusable(false)
        bCrear.addActionListener { e: ActionEvent? -> bCrear() }
        add(bCrear)
    }

    abstract fun bCrear()
}
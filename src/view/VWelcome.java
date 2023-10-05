package view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public abstract class VWelcome extends JPanel {
    public VWelcome() {
        setSize (1280, 720);
        setLayout(null);
        
        JLabel lBienvenido = new JLabel("¡Bienvenido!");
        lBienvenido.setBounds(400, 200, 400, 64);
        lBienvenido.setFont(new Font("Arial", Font.BOLD, 64));
        add(lBienvenido);

        JTextArea lText = new JTextArea("Al sistema de información numérica CC2, presione el botón de Crear para crear una nueva estructura de datos");
        lText.setBounds(220, lBienvenido.getY()+lBienvenido.getHeight()+32, 800, 60);
        lText.setBackground(null);
        lText.setFocusable(false);
        lText.setEditable(false);
        lText.setLineWrap(true);
        lText.setFont(new Font("Arial", Font.ITALIC, 24));
        add(lText);

        JButton bCrear = new JButton("Crear");
        bCrear.setBounds(550, 450, 100, 32);
        bCrear.setFont(lText.getFont());
        bCrear.setFocusable(false);
        bCrear.addActionListener(e -> bCrear());
        add(bCrear);
    }

    public abstract void bCrear();
}
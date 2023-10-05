package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import model.Dato;
import service.ObjGraficos;
import service.Recursos;

public abstract class VDashboard extends JPanel {
    JPanel pEstructura;

    public abstract void settings();
    public abstract void insertar(String clave);
    public abstract void buscar(String clave);

    public VDashboard() {
        setSize(1280, 720);
        setLayout(null);

        loadComponents();
        refrescar(null, 0);
    }

    private void loadComponents() {
        JLabel lTitle = new JLabel("Estructura de datos");
        lTitle.setFont(Recursos.fTitle);
        lTitle.setBounds(145, 48, 335, 44);
        add(lTitle);

        pEstructura = new JPanel(null);
        pEstructura.setBounds(0, 0, 2000, 4500);
        pEstructura.setPreferredSize(new Dimension(2000, 4500));
        pEstructura.setBackground(Color.WHITE);

        JScrollPane pScroll = new JScrollPane(pEstructura);
        pScroll.setBounds(168, 137, 245, 501);
        pScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        pScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(pScroll);

        JLabel lBClave = new JLabel("Buscar clave");
        lBClave.setBounds(640, 212, 144, 29);
        lBClave.setFont(Recursos.fText);
        add(lBClave);

        JLabel lIClave = new JLabel("Insertar clave");
        lIClave.setBounds(640, 287, 154, 29);
        lIClave.setFont(Recursos.fText);
        add(lIClave);

        JTextField tfB = ObjGraficos.getTextField(838, 212);
        tfB.addActionListener(e -> buscar(tfB.getText()));
        add(tfB);

        JTextField tfI = ObjGraficos.getTextField(838, 287);
        tfI.addActionListener(e -> {
            insertar(tfI.getText());
            tfI.setText("");
        });
        add(tfI);

        JButton bSettings = ObjGraficos.getButton(787, 454, "Ajustes");
        bSettings.addActionListener(e -> settings());
        add(bSettings);
    }

    public void refrescar(Dato[] datos, int nDigitos) {
        pEstructura.removeAll();

        JLabel lDir = new JLabel("Dirección");
        lDir.setBounds(32, 32, 100, 32);
        lDir.setFont(Recursos.fText);
        pEstructura.add(lDir);

        JLabel lClave = new JLabel("Clave");
        lClave.setBounds(142, 32, 100, 32);
        lClave.setFont(Recursos.fText);
        pEstructura.add(lClave);

        if(datos == null) {
            return;
        }

        int y = 0;
        for (int i = 0; i<datos.length; i++) {
            if(datos[i] != null){
                JLabel lDirA = new JLabel(datos[i].direccion+"");
                lDirA.setBounds(32, 64+32*y, 100, 32);
                lDirA.setFont(Recursos.fText);
                pEstructura.add(lDirA);

                JLabel lClaveA = new JLabel(String.format("%0"+nDigitos+"d", datos[i].clave));
                lClaveA.setBounds(142, 64+32*y, 100, 32);
                lClaveA.setFont(Recursos.fText);
                pEstructura.add(lClaveA);
                y++;
            }
        }

        repaint();
    }
}

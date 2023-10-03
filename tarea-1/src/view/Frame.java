package view;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Frame extends JFrame {
    JLabel lTitle;
    JPanel pContent;
    JPanel p1, p2, p3;
    ArrayList<String> data;
    Font text = new Font("Arial", Font.PLAIN, 20);
    JComboBox cbAlgoritmos;

    public Frame() {
        data = new ArrayList<String>();
        pContent = new JPanel();
        pContent.setBounds(200, 100, 850, 500);
        pContent.setBackground(Color.WHITE);
        pContent.setLayout(null);
        add(pContent);

        loadJLabels();
        showIntro();

        getContentPane().setBackground(new Color(32, 32, 32));
        setSize(1280, 720);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void showIntro() {
        if (p1 == null) {
            p1 = new JPanel();
            p1.setLayout(null);
            p1.setBounds(0, 0, 850, 500);

            Font text = new Font("Arial", Font.PLAIN, 20);

            JLabel lP1 = new JLabel("¿Qué algoritmo desea probar?");
            lP1.setBounds(32, 32, 400, 30);
            lP1.setFont(text);
            p1.add(lP1);

            Vector options = new Vector<>();
            options.addElement("Búsqueda secuencial");
            options.addElement("Búsqueda binaria");
            options.addElement("Búsqueda HASH: Función módulo");
            options.addElement("Búsqueda HASH: Función cuadrada");
            options.addElement("Búsqueda HASH: Función plegamiento");
            options.addElement("Búsqueda HASH: Función truncamiento");
            options.addElement("Búsqueda HASH: Función transformación de bases");
            options.addElement("Búsqueda por residuos: Árboles digitales");
            options.addElement("Búsqueda por residuos: Residuos");
            options.addElement("Búsqueda por residuos: Residuos múltiples");
            options.addElement("Búsqueda por residuos: Árboles de Huffman");
            options.addElement("Búsqueda dinámica: Expansión total");
            options.addElement("Búsqueda dinámica: Expansión parcial");
            options.addElement("Búsqueda dinámica: Reducción total");
            options.addElement("Búsqueda dinámica: Reducción parcial");

            cbAlgoritmos = new JComboBox<>(options);
            cbAlgoritmos.setBounds(64, 80, 700, 40);
            cbAlgoritmos.setFont(text);
            p1.add(cbAlgoritmos);

            JButton bExecute = new JButton("Ejecutar");
            bExecute.setBounds(600, 150, 150, 32);
            bExecute.addActionListener(e -> {
                if (!hayDatos()) {
                    showDatos();
                }
                else {
                    // add modify option here
                    execute();
                }
            });
            bExecute.setFont(text);
            p1.add(bExecute);
        }
        pContent.removeAll();
        pContent.add(p1);
        pContent.repaint();
    }

    private void showDatos() {
        lTitle.setText("Ingreso de datos");
        if (p2 == null) {
            p2 = new JPanel();
            p2.setLayout(null);
            p2.setBounds(0, 0, 850, 500);

            JLabel lP1 = new JLabel("Ingrese clave (4 caracteres numéricos)");
            lP1.setBounds(32, 32, 400, 30);
            lP1.setFont(text);
            p2.add(lP1);

            JTextField tValue = new JTextField();
            tValue.setBounds(420, 32, 90, 30);
            tValue.setFont(text);
            p2.add(tValue);

            JTextArea tClaves = new JTextArea();
            tClaves.setBounds(32, tValue.getY() + tValue.getHeight() + 25, 790, 390);
            tClaves.setFont(text);
            tClaves.setLineWrap(true);
            tClaves.setEditable(false);
            p2.add(tClaves);

            JButton bInsertar = new JButton("Insertar");
            bInsertar.setBounds(tValue.getX() + tValue.getWidth() + 32, tValue.getY(), 120, tValue.getHeight());
            bInsertar.addActionListener(e -> {
                insertar(tValue.getText());
                tClaves.setText(getDataToString());
                tClaves.repaint();
                p2.repaint();
            });
            bInsertar.setFont(text);
            p2.add(bInsertar);

            JButton bEjecutar = new JButton("Ejecutar");
            bEjecutar.setBounds(bInsertar.getX() + bInsertar.getWidth() + 32, tValue.getY(), 120, tValue.getHeight());
            bEjecutar.setFont(text);
            bEjecutar.addActionListener(e -> {
                if (data.size() == 0) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese al menos una clave");
                } else {
                    execute();
                }
            });
            p2.add(bEjecutar);

        }

        pContent.removeAll();
        pContent.add(p2);
        pContent.repaint();
    }

    private void execute() {
        p3 = new JPanel(null);
        p3.setBounds(0, 0, 850, 500);

        JTextArea textArea = new JTextArea();
        textArea.setBounds(32, 62 + 25, 790, 390);
        textArea.setFont(text);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setText(getAnswer());
        p3.add(textArea);

        JButton bRegresar = new JButton("Volver");
        bRegresar.setBounds(32, 32, 120, 30);
        bRegresar.setFont(text);
        bRegresar.addActionListener(e -> {
            showIntro();
        });
        p3.add(bRegresar);

        pContent.removeAll();
        pContent.add(p3);
        pContent.repaint();
    }

    private String getDataToString() {
        String str = "";
        for (String i : data) {
            str += i + ", ";
        }
        return str;
    }

    private String getAnswer() {
        switch(cbAlgoritmos.getSelectedIndex()) {
            case 0:
                return "Primer algoritmo";
            default:
                return "No se ha implementado";
        }
    }

    private void loadJLabels() {
        lTitle = new JLabel("Menú principal");
        lTitle.setFont(new Font("Arial", Font.BOLD, 40));
        lTitle.setForeground(Color.WHITE);
        lTitle.setBounds(400, 32, 700, 50);
        lTitle.setVerticalAlignment(JLabel.CENTER);
        add(lTitle);
    }

    private void insertar(String in) {
        if (in.length() != 4) {
            JOptionPane.showMessageDialog(null, "Recuerde que la longitud de cada clave es 4");
            return;
        }
        if (!isInt(in) || Integer.parseInt(in) < 0) {
            JOptionPane.showMessageDialog(null, "Recuerde ingresar un valor numérico entero positivo");
            return;
        }
        if (data.contains(in)) {
            JOptionPane.showMessageDialog(null, "El valor ya se encuentra insertado");
            return;
        }
        data.add(in);
    }

    public static Boolean isInt(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException numberFormat) {
            return false;
        }
    }

    private boolean hayDatos() {
        return data.size() != 0;
    }
}

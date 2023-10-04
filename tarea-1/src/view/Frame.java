package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints.Key;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Algoritmos;

public class Frame extends JFrame {
    public static int n = 100;
    public static int digitos = 4;

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
        lTitle.setText("Menú principal");
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
            options.addElement("Búsqueda interna: Secuencial");
            options.addElement("Búsqueda interna Binaria");
            options.addElement("Búsqueda interna HASH: Función módulo");
            options.addElement("Búsqueda interna HASH: Función cuadrada");
            options.addElement("Búsqueda interna HASH: Función plegamiento");
            options.addElement("Búsqueda interna HASH: Función truncamiento");
            options.addElement("Búsqueda externa HASH: Función transformación de bases");
            options.addElement("Búsqueda externa: Secuencial");
            options.addElement("Búsqueda externa: Binaria");
            options.addElement("Búsqueda externa HASH: Función cuadrada");
            options.addElement("Búsqueda externa HASH: Función plegamiento");
            options.addElement("Búsqueda externa HASH: Función truncamiento");
            options.addElement("Búsqueda externa HASH: Función módulo");

            cbAlgoritmos = new JComboBox<>(options);
            cbAlgoritmos.setBounds(64, 80, 700, 40);
            cbAlgoritmos.setFont(text);
            p1.add(cbAlgoritmos);

            JButton bInsert = new JButton("Insertar datos");
            bInsert.setBounds(350, 150, 200, 32);
            bInsert.setFont(text);
            bInsert.addActionListener(e1 -> showDatos());
            p1.add(bInsert);

            JButton bExecute = new JButton("Ejecutar");
            bExecute.setBounds(600, 150, 150, 32);
            bExecute.addActionListener(e -> {
                if (!hayDatos()) {
                    showDatos();
                } else {
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

            JLabel lP1 = new JLabel("Ingrese clave (" + digitos + " caracteres numéricos)");
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
                tValue.setText("");
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
        String value = "";
        int option = cbAlgoritmos.getSelectedIndex();
        do {
            value = JOptionPane.showInputDialog("Inserte la clave que desea buscar");
            if (value != null && (value.length() != digitos || !isInt(value) || Integer.parseInt(value) < 0)) {
                JOptionPane.showMessageDialog(null,
                        "Por favor busque una clave numérica entera positiva con " + digitos + " caracteres");
            } else {
                break;
            }
        } while (true);

        int[] arreglo = new int[data.size()];
        for (int i = 0; i < arreglo.length; i++) {
            arreglo[i] = Integer.parseInt(data.get(i));
        }
        int valor = Integer.parseInt(value);

        switch (option) {
            case 0:
                lTitle.setText("Búsqueda interna: Secuencial");
                return Algoritmos.secuencial(arreglo, valor);
            case 1:
                lTitle.setText("Búsqueda interna Binaria");
                return Algoritmos.binario(arreglo, valor);
            case 2:
                lTitle.setText("Búsqueda interna HASH: Función módulo");
                return Algoritmos.hashMod(arreglo, valor);
            case 3:
                lTitle.setText("Búsqueda interna HASH: Función cuadrada");
                return Algoritmos.hashCuadrado(arreglo, valor);
            case 4:
                lTitle.setText("Búsqueda interna HASH: Función plegamiento");
                return getInternoPlegamiento(value);
            case 5:
                lTitle.setText("Búsqueda interna HASH: Función truncamiento");
                return getInternoTruncamiento(value);
            case 6:
                lTitle.setText("Búsqueda externa HASH: Función transformación de bases");
                return getInternoTransBases(value);
            case 7:
                lTitle.setText("Búsqueda externa: Secuencial");
                return getExtSecuencial(value);
            case 8:
                lTitle.setText("Búsqueda externa: Binaria");
                return getExtBinario(value);
            case 9:
                lTitle.setText("Búsqueda externa HASH: Función cuadrada");
                return getExtCuadrado(value);
            case 10:
                lTitle.setText("Búsqueda externa HASH: Función plegamiento");
                return getExtPlegamiento(value);
            case 11:
                lTitle.setText("Búsqueda externa HASH: Función truncamiento");
                return getExtTruncamiento(value);
            case 12:
                lTitle.setText("Búsqueda externa HASH: Función módulo");
                return getExtMod(value);
            default:
                return "No se ha implementado";
        }
    }

    private String getExtMod(String clave) {
        lTitle.setText("Búsqueda externa HASH: F. módulo");
        int ans = bInternoBinario(Integer.parseInt(clave));
        if (ans < 0) {
            return "No se encontró la clave " + clave + " en la estructura" + getEstructura();
        } else {
            return "Se encontró la clave " + clave + " en la dirección " + hashMod(Integer.parseInt(clave))
                    + " de la estructura"
                    + getEstructura();
        }
    }

    private String getExtTruncamiento(String clave) {
        lTitle.setText("Búsqueda externa HASH: F. Truncamiento");
        int ans = bInternoBinario(Integer.parseInt(clave));
        if (ans < 0) {
            return "No se encontró la clave " + clave + " en la estructura" + getEstructura();
        } else {
            return "Se encontró la clave " + clave + " en la dirección " + hashTruncamiento(Integer.parseInt(clave))
                    + " de la estructura"
                    + getEstructura();
        }
    }

    private String getExtPlegamiento(String clave) {
        lTitle.setText("Búsqueda externa HASH: F. Plegamiento");
        int ans = bInternoBinario(Integer.parseInt(clave));
        if (ans < 0) {
            return "No se encontró la clave " + clave + " en la estructura" + getEstructura();
        } else {
            return "Se encontró la clave " + clave + " en la dirección " + hashPlegamiento(Integer.parseInt(clave))
                    + " de la estructura"
                    + getEstructura();
        }
    }

    private String getExtCuadrado(String clave) {
        lTitle.setText("Búsqueda externa HASH: F. cuadrado");
        int ans = bInternoBinario(Integer.parseInt(clave));
        if (ans < 0) {
            return "No se encontró la clave " + clave + " en la estructura" + getEstructura();
        } else {
            return "Se encontró la clave " + clave + " en la dirección " + hashCuadrado(Integer.parseInt(clave), 0)
                    + " de la estructura"
                    + getEstructura();
        }
    }

    private String getExtBinario(String clave) {
        lTitle.setText("Búsqueda externa HASH: F. Binario");
        int ans = bInternoBinario(Integer.parseInt(clave));
        if (ans < 0) {
            return "No se encontró la clave " + clave + " en la estructura" + getEstructura();
        } else {
            return "Se encontró la clave " + clave + " en la dirección " + hashMod(Integer.parseInt(clave))
                    + " de la estructura"
                    + getEstructura();
        }
    }

    private String getExtSecuencial(String clave) {
        lTitle.setText("Búsqueda HASH: F. Transformación de bases");
        int ans = bInternoBinario(Integer.parseInt(clave));
        if (ans < 0) {
            return "No se encontró la clave " + clave + " en la estructura" + getEstructura();
        } else {
            return "Se encontró la clave " + clave + " en la dirección " + hashMod(Integer.parseInt(clave))
                    + " de la estructura"
                    + getEstructura();
        }
    }

    private String getInternoTransBases(String clave) {
        lTitle.setText("Búsqueda HASH: F. Transformación de bases");
        int ans = bInternoBinario(Integer.parseInt(clave));
        if (ans < 0) {
            return "No se encontró la clave " + clave + " en la estructura" + getEstructura();
        } else {
            return "Se encontró la clave " + clave + " en la dirección "
                    + hashTransformacionBases(Integer.parseInt(clave))
                    + " de la estructura"
                    + getEstructura();
        }
    }

    public int hashTransformacionBases(int valor) {
        // Convertir el valor a una base diferente (por ejemplo, base 7)
        String valorBase7 = Integer.toString(valor, 7);

        // Tomar el módulo del valor convertido para obtener el índice
        int indice = Integer.parseInt(valorBase7) % n;

        return indice;

    }

    private String getInternoTruncamiento(String clave) {
        lTitle.setText("Búsqueda interna HASH: F. Truncamiento");
        int ans = bInternoBinario(Integer.parseInt(clave));
        if (ans < 0) {
            return "No se encontró la clave " + clave + " en la estructura" + getEstructura();
        } else {
            return "Se encontró la clave " + clave + " en la dirección " + hashTruncamiento(Integer.parseInt(clave))
                    + " de la estructura"
                    + getEstructura();
        }
    }

    public int hashTruncamiento(int valor) {
        // Tomar los últimos dígitos (por ejemplo, los últimos dos dígitos)
        int digitos = valor % 100;

        // Tomar el módulo del resultado para obtener el índice
        int indice = digitos % n;

        return indice;
    }

    private String getInternoPlegamiento(String clave) {
        lTitle.setText("Búsqueda interna HASH: F. Plegamiento");
        int ans = bInternoBinario(Integer.parseInt(clave));
        if (ans < 0) {
            return "No se encontró la clave " + clave + " en la estructura" + getEstructura();
        } else {
            return "Se encontró la clave " + clave + " en la dirección " + hashPlegamiento(Integer.parseInt(clave))
                    + " de la estructura"
                    + getEstructura();
        }
    }

    public int hashPlegamiento(int valor) {
        int sumaPartes = 0;
        int numero = valor;

        // Dividir el número en partes y sumarlas
        while (numero > 0) {
            sumaPartes += numero % 1000;
            numero /= 1000;
        }

        // Tomar el módulo del resultado
        int indice = sumaPartes % n;

        return indice;
    }

    private String getInternoCuadrado(String clave) {
        lTitle.setText("Búsqueda interna HASH: F. cuadrado");
        int ans = bInternoBinario(Integer.parseInt(clave));
        if (ans < 0) {
            return "No se encontró la clave " + clave + " en la estructura" + getEstructura();
        } else {
            return "Se encontró la clave " + clave + " en la dirección " + hashCuadrado(Integer.parseInt(clave), 0)
                    + " de la estructura"
                    + getEstructura();
        }
    }

    private int hashCuadrado(int valor, int intento) {
        int hash1 = valor % n;
        int hash2 = 1 + (valor % (n - 1)); // Asegura que sea impar para evitar ciclos infinitos
        int indice = (hash1 + intento * hash2) % n;

        return indice;
    }

    private String getInternoMod(String clave) {
        lTitle.setText("Búsqueda interna HASH: F. módulo");
        int ans = bInternoBinario(Integer.parseInt(clave));
        if (ans < 0) {
            return "No se encontró la clave " + clave + " en la estructura" + getEstructura();
        } else {
            return "Se encontró la clave " + clave + " en la dirección " + hashMod(Integer.parseInt(clave))
                    + " de la estructura"
                    + getEstructura();
        }
    }

    private int hashMod(int clave) {
        return clave % n + 1;
    }

    private String getInternoBinario(String clave) {
        lTitle.setText("Búsqueda interna: Binaria");
        int ans = bInternoBinario(Integer.parseInt(clave));
        if (ans < 0) {
            return "No se encontró la clave " + clave + " en la estructura" + getEstructura();
        } else {
            return "Se encontró la clave " + clave + " en la dirección " + hashMod(Integer.parseInt(clave))
                    + " de la estructura"
                    + getEstructura();
        }
    }

    private int bInternoBinario(int clave) {
        int[] num = new int[data.size()];
        for (int i = 0; i < data.size(); i++) {
            num[i] = Integer.parseInt(data.get(i));
        }
        Arrays.sort(num);
        return Arrays.binarySearch(num, clave);
    }

    private String getInternoSecuencial(String clave) {
        lTitle.setText("Búsqueda interna: Secuencial");
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).equals(clave)) {
                return "Se encontró la clave " + clave + " en la dirección " + hashMod(Integer.parseInt(clave))
                        + " de la estructura"
                        + getEstructura();
            }
        }
        return "No se encontró la clave " + clave + " en la estructura" + getEstructura();
    }

    private String getEstructura() {
        String estructura = "\nEstructura (direccion, clave):\n";
        for (int i = 0; i < data.size(); i++) {
            int dir = 0;
            if (cbAlgoritmos.getSelectedIndex() == 3 || cbAlgoritmos.getSelectedIndex() == 9) {
                dir = hashCuadrado(Integer.parseInt(data.get(i)), 0);
            } else if (cbAlgoritmos.getSelectedIndex() == 4 || cbAlgoritmos.getSelectedIndex() == 10) {
                dir = hashPlegamiento(Integer.parseInt(data.get(i)));
            } else if (cbAlgoritmos.getSelectedIndex() == 5 || cbAlgoritmos.getSelectedIndex() == 11) {
                dir = hashTruncamiento(Integer.parseInt(data.get(i)));
            } else if (cbAlgoritmos.getSelectedIndex() == 6) {
                dir = hashTransformacionBases(Integer.parseInt(data.get(i)));
            } else {
                dir = hashMod(Integer.parseInt(data.get(i)));
            }
            estructura += "(" + dir + ", " + data.get(i) + "), ";
        }
        return estructura;
    }

    private void loadJLabels() {
        lTitle = new JLabel("Menú principal");
        lTitle.setFont(new Font("Arial", Font.BOLD, 40));
        lTitle.setForeground(Color.WHITE);
        lTitle.setBounds(300, 32, 800, 50);
        lTitle.setVerticalAlignment(JLabel.CENTER);
        add(lTitle);
    }

    private void insertar(String in) {
        if (in.length() != digitos) {
            JOptionPane.showMessageDialog(null, "Recuerde que la longitud de cada clave es " + digitos);
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

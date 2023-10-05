package view;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import control.App;
import model.Adapter;
import service.Logic;

public abstract class Frame extends JFrame {
    private VWelcome vWelcome;
    private VStructure vStructure;
    private VDashboard vDashboard;

    JLabel lTitle;
    JPanel pContent;
    JPanel p1, p2, p3;
    ArrayList<String> data;

    Font text = new Font("Arial", Font.PLAIN, 20);
    JComboBox cbAlgoritmos;

    public Frame(App app) {
        vWelcome = new VWelcome() {
            @Override
            public void bCrear() {
                vStructure.newStructureScreen();
                setContentPane(vStructure);
            }
        };
        setContentPane(vWelcome);

        vStructure = new VStructure() {
            @Override
            public void bGuardar(String rango, String size, String caracteres, int tipo, int direccion, int busqueda,
                    int colision) {
                // Validar rango
                if(rango.isEmpty() || !Logic.isInt(rango) || Integer.parseInt(rango)<100 || Integer.parseInt(rango)>1000) {
                    JOptionPane.showMessageDialog(
                        null,
                        "El rango debe ser un número entero positivo mayor o igual a 100 y menor o igual que 1000",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
                
                // Validar tamaño
                else if(size.isEmpty() || !Logic.isInt(size) || Integer.parseInt(size)>Integer.parseInt(rango)) {
                    JOptionPane.showMessageDialog(
                        null,
                        "El tamaño debe ser un número entero positivo menor o igual al rango",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
                
                // Validar caracteres
                else if(caracteres.isEmpty() || !Logic.isInt(caracteres) || Integer.parseInt(caracteres)<4 || 
                        Integer.parseInt(caracteres)>9) {
                    JOptionPane.showMessageDialog(
                        null,
                        "La cantidad de caracteres debe ser un número entero positivo mayor que 3 y menor que 10",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }

                // Validar tipo
                else if(tipo == 1 && Integer.parseInt(size)<100) {
                    JOptionPane.showMessageDialog(
                        null,
                        "El tamaño de una estructura externa debe ser un número entero positivo mayor o igual a 100",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }

                // Guardar
                else {
                    JOptionPane.showMessageDialog(
                        null,
                        "La estructura de datos ha sido guardada satisfactoriamente",
                        "Mensaje informativo",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                    vStructure.setCreated();
                    guardarEstructura(
                        Integer.parseInt(rango),
                        Integer.parseInt(size),
                        Integer.parseInt(caracteres),
                        tipo,
                        direccion,
                        busqueda,
                        colision
                    );
                    vDashboard = new VDashboard() {
                        @Override
                        public void settings() {
                            setContentPane(vStructure);
                        }

                        @Override
                        public void insertar(String clave) {
                            if(clave.isEmpty() || !Logic.isInt(clave) || clave.length()!=app.getDigitos() || Integer.parseInt(clave)<0) {
                                JOptionPane.showMessageDialog(
                                    null,
                                    "La clave debe ser un número entero positivo de "+app.getDigitos()+" dígitos",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE
                                );
                            }
                            else {
                                app.insertar(Integer.parseInt(clave));
                                vDashboard.refrescar(app.getDatos(), app.getDigitos());
                            }
                        }

                        @Override
                        public void buscar(String clave) {
                            if(clave.isEmpty() || !Logic.isInt(clave) || clave.length()!=app.getDigitos() || Integer.parseInt(clave)<0) {
                                JOptionPane.showMessageDialog(
                                    null,
                                    "La clave debe ser un número entero positivo de "+app.getDigitos()+" dígitos",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE
                                );
                            }
                            else {
                                app.buscar(Integer.parseInt(clave));
                                vDashboard.refrescar(app.getDatos(), app.getDigitos());
                            }
                        }

                    };
                    vDashboard.refrescar(app.getDatos(), app.getDigitos());
                    setContentPane(vDashboard);
                }
            }
        };

        setSize(1280, 720);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public abstract void guardarEstructura(
        int rango,
        int tamanio,
        int caracteres,
        int tipo,
        int direccion,
        int busqueda,
        int colision
    );

/* 
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
                if (!true/*hayDatos()) {
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

            JLabel lP1 = new JLabel("Ingrese clave (" + App.DIGITOS + " caracteres numéricos)");
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
/* 
    private String getAnswer() {
        String value = "";
        int option = cbAlgoritmos.getSelectedIndex();
        do {
            value = JOptionPane.showInputDialog("Inserte la clave que desea buscar");
            if (value != null && (value.length() != App.DIGITOS || !App.isInt(value) || Integer.parseInt(value) < 0)) {
                JOptionPane.showMessageDialog(null,
                        "Por favor busque una clave numérica entera positiva con " + App.DIGITOS + " caracteres");
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
                lTitle.setText("B. interna: Secuencial");
                return Adapter.secuencial(arreglo, valor);
            case 1:
                lTitle.setText("B. interna Binaria");
                return Adapter.binario(arreglo, valor);
            case 2:
                lTitle.setText("B. interna HASH: Función módulo");
                return Adapter.hashMod(arreglo, valor);
            case 3:
                lTitle.setText("B. interna HASH: Función cuadrada");
                return Adapter.hashCuadrado(arreglo, valor);
            case 4:
                lTitle.setText("B. interna HASH: Función plegamiento");
                return getInternoPlegamiento(value);
            case 5:
                lTitle.setText("B. interna HASH: Función truncamiento");
                return getInternoTruncamiento(value);
            case 6:
                lTitle.setText("B. externa HASH: Función transformación de bases");
                return getInternoTransBases(value);
            case 7:
                lTitle.setText("B. externa: Secuencial");
                return getExtSecuencial(value);
            case 8:
                lTitle.setText("B. externa: Binaria");
                return getExtBinario(value);
            case 9:
                lTitle.setText("B. externa HASH: Función cuadrada");
                return getExtCuadrado(value);
            case 10:
                lTitle.setText("B. externa HASH: Función plegamiento");
                return getExtPlegamiento(value);
            case 11:
                lTitle.setText("B. externa HASH: Función truncamiento");
                return getExtTruncamiento(value);
            case 12:
                lTitle.setText("B. externa HASH: Función módulo");
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
        int indice = Integer.parseInt(valorBase7) % App.N;

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
        int indice = digitos % App.N;

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
        int indice = sumaPartes % App.N;

        return indice;
    }

    private int hashCuadrado(int valor, int intento) {
        int hash1 = valor % App.N;
        int hash2 = 1 + (valor % (App.N - 1)); // Asegura que sea impar para evitar ciclos infinitos
        int indice = (hash1 + intento * hash2) % App.N;

        return indice;
    }

    private int hashMod(int clave) {
        return clave % App.N + 1;
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
        if (in.length() != App.DIGITOS) {
            JOptionPane.showMessageDialog(null, "Recuerde que la longitud de cada clave es " + App.DIGITOS);
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
    */
}
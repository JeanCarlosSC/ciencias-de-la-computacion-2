package view;

import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import service.ObjGraficos;
import service.Recursos;

public abstract class VStructure extends JPanel {
    private JTextField tfSize, tfRango, tfDigitos;
    private JComboBox cbTipo;

    public VStructure() {
        setSize(1280, 720);
        setLayout(null);
    }

    public void newStructureScreen() {
        removeAll();

        int x = 300;
        int y = 200;

        JLabel lTitulo = new JLabel("Datos de la estructura");
        lTitulo.setBounds(x + 150, 100, 500, 50);
        lTitulo.setFont(Recursos.fTitle);
        add(lTitulo);

        JLabel lRango = new JLabel("Rango de las direcciones de memoria (n)");
        lRango.setFont(Recursos.fText);
        lRango.setBounds(x, 32 + y, 500, 32);
        add(lRango);

        tfRango = ObjGraficos.getTextField(x + 550, 32 + y);
        add(tfRango);

        JLabel lSize = new JLabel("Tamaño de la estructura (N)"); // N < n
        lSize.setBounds(x, 72 + y, 500, 32);
        lSize.setFont(Recursos.fText);
        add(lSize);

        tfSize = ObjGraficos.getTextField(x + 550, 72 + y);
        add(tfSize);

        JLabel lDigitos = new JLabel("Cantidad de caracteres de cada clave");
        lDigitos.setBounds(x, 112 + y, 500, 32);
        lDigitos.setFont(Recursos.fText);
        add(lDigitos);

        tfDigitos = ObjGraficos.getTextField(x + 550, 112 + y);
        add(tfDigitos);

        JLabel lTipo = new JLabel("Tipo de estructura");
        lTipo.setBounds(x, 152 + y, 500, 32);
        lTipo.setFont(Recursos.fText);
        add(lTipo);

        JLabel lDireccion = new JLabel("Algoritmo para asignar direcciones de memoria");
        lDireccion.setBounds(x, 192 + y, 500, 32);
        lDireccion.setFont(Recursos.fText);
        add(lDireccion);

        Vector vTipo = new Vector<String>();
        vTipo.add("Estructura interna");
        vTipo.add("Estructura externa");

        Vector vDir = new Vector<String>();
        vDir.add("Hash mod");
        vDir.add("Hash cuadrado");
        vDir.add("Hash plegamiento");
        vDir.add("Hash truncamiento");

        Vector vBusqueda = new Vector<String>();
        vBusqueda.add("Búsqueda secuencial");
        vBusqueda.add("Búsqueda binaria");
        vBusqueda.add("Búsqueda hash mod");
        vBusqueda.add("Búsqueda hash cuadrado");
        vBusqueda.add("Búsqueda hash plegamiento");
        vBusqueda.add("Búsqueda hash truncamiento");

        Vector vColision = new Vector<String>();
        vColision.add("Lineal");
        vColision.add("Cuadrática");
        vColision.add("Doble dirección hash");
        vColision.add("Arreglos anidados");
        vColision.add("Encadenamiento");

        cbTipo = new JComboBox<>(vTipo);
        cbTipo.setBounds(x + 550, 152 + y, 300, 32);
        add(cbTipo);

        JComboBox cbDir = new JComboBox<>(vDir);
        cbDir.setBounds(x + 550, 192 + y, 300, 32);
        add(cbDir);

        JLabel lBusqueda = new JLabel("Algoritmo para realizar búsqueda de claves");
        lBusqueda.setBounds(x, 232 + y, 500, 32);
        lBusqueda.setFont(Recursos.fText);
        add(lBusqueda);

        JComboBox cbBusqueda = new JComboBox<String>(vBusqueda);
        cbBusqueda.setBounds(x + 550, 232 + y, 300, 32);
        add(cbBusqueda);

        JLabel lColision = new JLabel("Algoritmo para solucionar colisión de datos en memoria");
        lColision.setBounds(x, 272 + y, 500, 32);
        lColision.setFont(Recursos.fText);
        add(lColision);

        JComboBox cbColision = new JComboBox<String>(vColision);
        cbColision.setBounds(x + 550, 272 + y, 300, 32);
        add(cbColision);

        JButton bGuardar = ObjGraficos.getButton(x + 500, 322 + y, "Guardar");
        bGuardar.addActionListener(e -> bGuardar(
                tfRango.getText(), tfSize.getText(), tfDigitos.getText(), cbTipo.getSelectedIndex(),
                cbDir.getSelectedIndex(), cbBusqueda.getSelectedIndex(), cbColision.getSelectedIndex()));
        add(bGuardar);

        cbTipo.addActionListener(e -> {

            if (cbTipo.getSelectedIndex() == 1) {
                vDir.add("Hash transformación de bases");
                vBusqueda.add("Búsqueda hash transformación de bases");

                vColision.remove("Lineal");
                vColision.remove("Cuadrática");
                vColision.remove("Doble dirección hash");
                vColision.remove("Arreglos anidados");
                vColision.remove("Encadenamiento");
                vColision.add("Áreas independientes");
                vColision.add("Áreas de colisiones");
                cbColision.setSelectedIndex(1);
            } else {
                vDir.remove("Hash transformación de bases");
                vBusqueda.remove("Búsqueda hash transformación de bases");

                vColision.add("Lineal");
                vColision.add("Cuadrática");
                vColision.add("Doble dirección hash");
                vColision.add("Arreglos anidados");
                vColision.add("Encadenamiento");
                vColision.remove("Áreas independientes");
                vColision.remove("Áreas de colisiones");
                cbColision.setSelectedIndex(0);
            }
        });
    }

    public abstract void bGuardar(String rango, String size, String digitos, int tipo, int direccion, int busqueda,
            int colision);

    public void setCreated() {
        tfSize.setEnabled(false);
        tfRango.setEnabled(false);
        tfDigitos.setEnabled(false);
        cbTipo.setEnabled(false);
    }
}

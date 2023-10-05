package service;

import javax.swing.JButton;
import javax.swing.JTextField;

public class ObjGraficos {
    public static JTextField getTextField(int x, int y) {
        JTextField tf = new JTextField();
        tf.setBounds(x, y, 100, 32);
        tf.setFont(Recursos.fText);
        return tf;
    }
    public static JButton getButton(int x, int y, String text) {
        JButton tf = new JButton(text);
        tf.setBounds(x, y, 150, 32);
        tf.setFont(Recursos.fText);
        tf.setFocusable(false);
        return tf;
    }
}

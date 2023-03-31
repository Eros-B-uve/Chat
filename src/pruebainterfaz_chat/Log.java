package pruebainterfaz_chat;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Log extends JFrame implements ActionListener {

    JTextField FieldNombre;
    JButton conectar, colores;
    Color colorr;

    public Log() {
        this.setTitle("Interfaz Chat");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);

        componentes();
    }

    private void componentes() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(500, 500);

        JLabel titulo = new JLabel("Chat de Eros y Erick");
        titulo.setForeground(Color.BLACK);
        titulo.setFont(new Font("Comic Sans MS", Font.CENTER_BASELINE, 20));
        titulo.setBounds(150, 100, 500, 100);

        FieldNombre = new JTextField();
        FieldNombre.setBounds(100, 200, 200, 30);

        colores = new JButton("Colores");
        colores.setBounds(300, 200, 100, 30);
        colores.addActionListener(this);

        conectar = new JButton("Conectar");
        conectar.setBounds(200, 250, 100, 30);
        conectar.addActionListener(this);

        panel.add(titulo);
        panel.add(FieldNombre);
        panel.add(conectar);
        panel.add(colores);
        this.add(panel);
    }

    public static void main(String args[]) {
        Log log = new Log();
        //log.setVisible(true);
        new Chat(new Usuario("Erick", Color.red));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == conectar) {
            if (FieldNombre.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debes ingresar un nombte");
            } else if(colorr == null) {
                JOptionPane.showMessageDialog(null, "Debes seleccionar un color");
            } else {
                Chat ventanaChat = new Chat(new Usuario(FieldNombre.getText(), colorr));
                ventanaChat.setVisible(true);
                ventanaChat.setLocationRelativeTo(null);
                setVisible(false);
            }
        }

        if (e.getSource() == colores) {
            colorr = JColorChooser.showDialog(null, "Seleccione un Color", Color.BLUE);
        }
    }
}

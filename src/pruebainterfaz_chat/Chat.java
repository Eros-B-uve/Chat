/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebainterfaz_chat;
import javax.swing.*;
//import java.awt.*;
import java.awt.event.*;

public class Chat extends JFrame{

    private JTextArea chatBox;
    private JTextField inputField;
    Usuario u;

    public Chat(Usuario u) {
        this.setTitle("Interfaz Chat");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);

        this.u = u;

        componentes();
    }

    private void componentes() {
        JPanel panel = new JPanel(null);
        panel.setBounds(0,0,500, 500);

        chatBox = new JTextArea();
        chatBox.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatBox);
        scrollPane.setBounds(0,0,300, 430);

        inputField = new JTextField();
        inputField.setBounds(1, 430,400, 30);

        JButton enviar = new JButton("Enviar");
        //enviar.setBackground(u.getColor());
        enviar.addActionListener(new SendButtonListener());
        enviar.setBounds(400, 430, 100, 30);
        

        panel.add(scrollPane);
        panel.add(inputField);
        panel.add(enviar);
        add(panel);
    }

    private class SendButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            chatBox.append(u.getNombre() + ": " + inputField.getText() + "\n");
            inputField.setText("");
        }
    }


}

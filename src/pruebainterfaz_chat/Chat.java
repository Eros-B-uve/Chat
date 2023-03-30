/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebainterfaz_chat;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Chat extends JFrame{

    private JTextArea chatBox;
    private JTextField inputField;
    Usuario u;

    public Chat(Usuario u) {
        this.u = u;
        setTitle("Interfaz Chat");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setLayout(null);
        

        JPanel chatPanel = new JPanel(null);
        chatBox = new JTextArea();
        chatBox.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatBox);
        scrollPane.setBounds(0,0,500,500);
        chatPanel.add(scrollPane);

        JPanel inputPanel = new JPanel(null);
        inputField = new JTextField();
        inputField.setBounds(0,0,400,30);
        JButton enviar = new JButton("Enviar");
        enviar.addActionListener(new SendButtonListener());
        enviar.setBounds(400, 0, 100, 30);
        inputPanel.add(inputField);
        inputPanel.add(enviar);
        
        chatPanel.setBounds(0,0,500,400);
        inputPanel.setBounds(0,400,500,100);

        add(chatPanel);
        add(inputPanel);
    }

    private class SendButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            chatBox.append(u.getNombre() + ": " + inputField.getText() + "\n");
            inputField.setText("");
        }
    }


}

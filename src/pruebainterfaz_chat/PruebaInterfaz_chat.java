/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebainterfaz_chat;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PruebaInterfaz_chat extends JFrame{

    private JTextArea chatBox;
    private JTextField inputField;
    Usuario u;

    public PruebaInterfaz_chat() {
        setTitle("Interfaz Chat");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);

        JPanel chatPanel = new JPanel(new BorderLayout());
        chatBox = new JTextArea();
        chatBox.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatBox);
        chatPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputField = new JTextField();
        JButton enviar = new JButton("Enviar");
        enviar.addActionListener(new SendButtonListener());
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(enviar, BorderLayout.EAST);

        add(chatPanel, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);
        System.out.println("Hola");
        System.out.println("Holaaa");
        
        //String s = inputField.getText();
        Color color = Color.GREEN;
        u = new Usuario("Eros", color);
    }

    private class SendButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String inputText = inputField.getText();
            chatBox.append(u.getNombre() + ": " + inputText + "\n");
            inputField.setText("");
        }
    }

    public static void main(String[] args) {
        PruebaInterfaz_chat chat = new PruebaInterfaz_chat();
        chat.setVisible(true);
        chat.setLocationRelativeTo(null);
    }


}

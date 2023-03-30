package pruebainterfaz_chat;
import javax.swing.*;

import java.awt.Font;
//import java.awt.*;
import java.awt.event.*;

//https://stackoverflow.com/questions/9650992/how-to-change-text-color-in-the-jtextarea
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
        scrollPane.setBounds(0,0, 500, 430);

        inputField = new JTextField();
        inputField.setBounds(1, 430,400, 30);
        inputField.setFont(new Font("Arial", Font.PLAIN, 12));

        JButton enviar = new JButton("Enviar");
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

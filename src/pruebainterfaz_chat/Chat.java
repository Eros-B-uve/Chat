package pruebainterfaz_chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.*;

//https://stackoverflow.com/questions/9650992/how-to-change-text-color-in-the-jtextarea
public class Chat extends JFrame {

    private JTextPane chatBox;
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
        panel.setBounds(0, 0, 500, 500);

        chatBox = new JTextPane();
        chatBox.setEditable(false);
        chatBox.setBounds(0, 0, 100, 100);
        JScrollPane scrollPane = new JScrollPane(chatBox, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(0, 0, 490, 430);

        inputField = new JTextField();
        inputField.setBounds(1, 430, 400, 30);
        inputField.setFont(new Font("Arial", Font.PLAIN, 12));

        JButton enviar = new JButton("Enviar");
        enviar.addActionListener(new SendButtonListener());
        enviar.setBounds(400, 430, 100, 30);

            appendToPane(chatBox, "Hola\n", Color.red);
            appendToPane(chatBox, "Hola\n", Color.red);
        panel.add(chatBox);
        panel.add(inputField);
        panel.add(enviar);
        add(panel);
    }

    private void appendToPane(JTextPane tp, String msg, Color c) {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

        int len = tp.getDocument().getLength();
        tp.setCaretPosition(len);
        tp.setCharacterAttributes(aset, false);
        tp.replaceSelection(msg);
    }

    private class SendButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            appendToPane(chatBox, "Hola\n", Color.red);
            inputField.setText("");
        }
    }

}

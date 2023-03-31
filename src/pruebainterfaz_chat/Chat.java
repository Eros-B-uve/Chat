package pruebainterfaz_chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.*;

//https://stackoverflow.com/questions/9650992/how-to-change-text-color-in-the-jtextarea
public class Chat extends JFrame {

    private JTextPane chatBox;
    JTextArea area;
    JButton boton;
    private JTextField inputField;
    private JScrollPane scrollPane;
    private Usuario u;

    public Chat(Usuario u) {
        this.setVisible(true);
        
        this.setTitle("Interfaz Chat");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new GridBagLayout());

        this.u = u;

        componentes();
    }

    private void componentes() {
        GridBagConstraints cons = new GridBagConstraints();
        area = new JTextArea();
        scrollPane = new JScrollPane(area);
        inputField = new JTextField(20);
        boton = new JButton("Enviar");
        
        cons.gridx = 0;
        cons.gridy = 0;
        cons.gridwidth = 2;
        cons.gridheight = 1;
        cons.weightx = 1.0;
        cons.weighty = 1.0;
        cons.fill = GridBagConstraints.BOTH;
        this.add(scrollPane, cons);
        
        cons.gridx = 0;
        cons.gridy = 1;
        cons.gridwidth = 1;
        cons.gridheight = 1;
        cons.weightx = 1.0;
        cons.weighty = 0.0;
        cons.fill = GridBagConstraints.BOTH;
        add(inputField, cons);
        
        cons.gridx = 1;
        cons.gridy = 1;
        cons.gridwidth = 1;
        cons.gridheight = 1;
        cons.weightx = 0.0;
        cons.weighty = 0.0;
        cons.fill = GridBagConstraints.BOTH;
        this.add(boton, cons);
        
        
    }
    
    private void chat() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBounds(0, 0, 500, 400);
        panel.setBackground(Color.red);
        
        GridBagConstraints constrains = new GridBagConstraints();
        constrains.fill = GridBagConstraints.BOTH;
        constrains.gridheight = 1;
        constrains.gridwidth = 1;
        constrains.gridx = 0;
        constrains.gridy = 0;
        
        chatBox = new JTextPane();
        scrollPane = new JScrollPane(new JTextArea(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        panel.add(scrollPane, constrains);
        panel.add(new JButton("Hola"));
        this.add(panel);
    }
    
    private void mensaje (GridBagConstraints cons) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.blue);
        GridBagConstraints constrains = new GridBagConstraints();
        cons.fill = GridBagConstraints.BOTH;
        
        
        inputField = new JTextField();
        inputField.setBounds(1, 430, 400, 30);
        inputField.setFont(new Font("Arial", Font.PLAIN, 12));

        JButton enviar = new JButton("Enviar");
        enviar.addActionListener(new SendButtonListener());
        enviar.setBounds(400, 430, 100, 30);
        
        this.add(panel, cons);
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
            appendToPane(chatBox, u.getNombre() + ": ", u.getColor());
            appendToPane(chatBox, inputField.getText() + "\n", Color.BLACK);
            inputField.setText("");
        }
    }

}

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
        JPanel panel = new JPanel(null);
        
        GridBagConstraints cons = new GridBagConstraints();
        cons.fill = GridBagConstraints.BOTH;
        
        cons.gridheight = 1;
        cons.gridwidth = 1;
        cons.gridx = 0;
        cons.gridy = 0;
        chat(cons);
        
        cons.gridheight = 1;
        cons.gridy++;
        mensaje(cons);
        

        inputField = new JTextField();
        inputField.setBounds(1, 430, 400, 30);
        inputField.setFont(new Font("Arial", Font.PLAIN, 12));

        JButton enviar = new JButton("Enviar");
        enviar.addActionListener(new SendButtonListener());
        enviar.setBounds(400, 430, 100, 30);
        
    }
    
    private void chat(GridBagConstraints cons) {
        JPanel panel = new JPanel(null);
        panel.setBounds(0, 0, 100, 100);
        panel.setSize(400, 400);
        panel.setBackground(Color.red);
        
        GridBagConstraints constrains = new GridBagConstraints();
        cons.fill = GridBagConstraints.BOTH;
        cons.ipadx = 500;
        cons.ipady = 400;
        
        chatBox = new JTextPane();
        JScrollPane scrollPane = new JScrollPane(chatBox, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        panel.add(new JButton("Hola"), constrains);
        this.add(panel, cons);
    }
    
    private void mensaje (GridBagConstraints cons) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.blue);
        GridBagConstraints constrains = new GridBagConstraints();
        cons.fill = GridBagConstraints.BOTH;
        
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

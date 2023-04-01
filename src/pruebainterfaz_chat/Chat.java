package pruebainterfaz_chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.*;

//https://stackoverflow.com/questions/9650992/how-to-change-text-color-in-the-jtextarea
public class Chat extends JFrame implements ActionListener, KeyListener {

    private JTextPane chatBox;
    private JButton enviar;
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

        chatBox = new JTextPane();
        chatBox.setFont(new Font("Arial", Font.PLAIN, 15));
        chatBox.setEditable(false);
        scrollPane = new JScrollPane(chatBox);

        inputField = new JTextField(20);
        inputField.setFont(new Font("Arial", Font.PLAIN, 15));
        inputField.addKeyListener(this);

        enviar = new JButton("Enviar");

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
        enviar.addActionListener(this);
        this.add(enviar, cons);

    }

    private void appendToPane(JTextPane tp, String msg, Color c) {
        tp.setEditable(true);
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

        int len = tp.getDocument().getLength();
        tp.setCaretPosition(len);
        tp.setCharacterAttributes(aset, false);
        tp.replaceSelection(msg);
        tp.setEditable(false);
    }

    public void enviar() {
        if (!inputField.getText().isEmpty()) {
            appendToPane(chatBox, u.getNombre() + ": ", u.getColor());
            appendToPane(chatBox, inputField.getText() + "\n", Color.BLACK);
            inputField.setText("");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == enviar) {
            enviar();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            System.out.println("Hola");
            enviar();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}

package pruebainterfaz_chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.text.*;

//https://stackoverflow.com/questions/9650992/how-to-change-text-color-in-the-jtextarea
public class Chat extends JFrame implements ActionListener, KeyListener {

    public JTextPane chatBox;
    private JButton enviar;
    private JTextField inputField;
    private JScrollPane scrollPane;

    private Usuario u;
    public PrintWriter cout;
    private Socket socket;

    public Chat(Usuario u) {
        this.setVisible(true);

        this.setTitle("Interfaz Chat");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new GridBagLayout());

        this.u = u;

        inicarSocket();
        componentes();
    }
    
    private void inicarSocket() {
        try {
            socket = new Socket("172.29.192.1", 5000);
            cout = new PrintWriter(socket.getOutputStream(), true);
            
            ThreadClient threadClient = new ThreadClient(socket, this);
            new Thread(threadClient).start(); // start thread to receive message
            cout.println(u.getNombre() + ": ha entrado al chat¤-8355712");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e + "No se ha podido establecer conexion");
        }
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

    public void appendToPane(JTextPane tp, String msg, Color c) {
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
            String msg = inputField.getText();
            if (msg.charAt(0) == '/') {
                switch (msg.substring(1).toLowerCase()) {
                    case "clear":
                        chatBox.setText("");
                        break;
                    case "color":
                        u.setColor(JColorChooser.showDialog(null, "Seleccione un Color", u.getColor()));
                        appendToPane(chatBox, "El color se ha cambiado\n", Color.gray);
                        break;
                    case "exit":
                        System.exit(0);
                        break;
                    case "help":
                        appendToPane(chatBox, "CLEAR    Borra la pantalla\n", Color.gray);
                        appendToPane(chatBox, "COLOR    Cambia el color del usuario\n", Color.gray);
                        appendToPane(chatBox, "EXIT     Cierra la aplicacion\n", Color.gray);
                        appendToPane(chatBox, "HELP     Muestra el menu de comandos\n", Color.gray);
                        appendToPane(chatBox, "NAME     Cambia el nombre del usuario\n", Color.gray);
                        break;
                    case "name":
                        String nuevoNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre", "nombre");
                        if (!nuevoNombre.isBlank()) {
                            u.setNombre(nuevoNombre);
                            appendToPane(chatBox, "Nuevo nombre cambiado a '" + nuevoNombre + "'\n", Color.gray);
                        } else {
                            appendToPane(chatBox, "Nombre no valido" + "\n", Color.gray);
                        }
                        break;
                    default:
                        appendToPane(chatBox, "'" + msg + "' no es un comando reconocido\n", Color.gray);
                        break;
                }
            } else {
                appendToPane(chatBox, u.getNombre() + "(Tu): ", u.getColor());
                appendToPane(chatBox, msg + "\n", Color.BLACK);

                cout.println(u.getNombre() + ": " + msg + "¤" + u.getColor().getRGB());
            }
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
            enviar();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}

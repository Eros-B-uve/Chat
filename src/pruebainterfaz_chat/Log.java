package pruebainterfaz_chat;

import javax.swing.JFrame;

public class Log extends JFrame{

    public Log () {
        this.setLocationRelativeTo(null);
        this.setTitle("Interfaz Chat");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500, 500);
    }
    public static void main(String args []) {
        Log log = new Log();
        log.setVisible(true);
    }
}
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame("Pendataan UKT Mahasiswa");
        jFrame.setContentPane(new GUI().getRootPanel());
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(700, 400);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }
}

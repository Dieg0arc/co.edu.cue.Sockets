import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

public class WindowConfig extends JDialog {
    private Logger logger = Logger.getLogger(String.valueOf(WindowConfig.class));
    private JTextField tfUser;
    private JTextField tfHost;
    private JTextField tfPort;

    public WindowConfig(JFrame padre) {
        super(padre, "init config", true);

        JLabel userLb = new JLabel("User:");
        JLabel hostLb = new JLabel("Host:");
        JLabel portLb = new JLabel("Port:");

        tfUser = new JTextField();
        tfHost = new JTextField("localhost");
        tfPort = new JTextField("5050"); //Revisar pq puse el 5050
        JButton acceptBt = new JButton("Accept");
        acceptBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        Container container = this.getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(20, 20, 0, 20);
        gbc.gridx = 0;
        gbc.gridy = 0;
        container.add(userLb, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        container.add(hostLb, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        container.add(portLb, gbc);

        gbc.ipadx = 100;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 1;
        gbc.gridy = 0;
        container.add(tfUser, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        container.add(tfHost, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        container.add(tfPort, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 20, 20, 20);
        container.add(acceptBt, gbc);

        this.pack(); // Le da a la ventana el mínimo tamaño posible
        this.setLocation(450, 200); // Posición de la ventana
        this.setResizable(false); //Evita que se pueda estirar la ventana
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); // Deshabilita el boton de cierre de la ventana
        this.setVisible(true);
    }
    public String getUser(){
        return this.tfUser.getText();
    }

    public String getHost(){
        return this.tfHost.getText();
    }

    public int getPort(){
        return Integer.parseInt(this.tfPort.getText());
    }
}

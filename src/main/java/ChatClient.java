import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.rmi.UnexpectedException;
import java.util.logging.Logger;

public class ChatClient extends JFrame {
    private Logger logger = Logger.getLogger(String.valueOf(ChatClient.class));
    private JTextArea chatMessage;
    private Socket socket;
    private int port;
    private String host;
    private String user;

    public ChatClient() {
        super("Client Chat");

        //Elementos de la ventana
        chatMessage = new JTextArea();
        chatMessage.setEnabled(false); // El area de mensajes del chat no se debe poder editar
        chatMessage.setLineWrap(true); // Las lineas se parten al llegar al ancho del TextArea
        chatMessage.setWrapStyleWord(true); // Las lineas se parten entre palabras (por los espacios en blanco)
        JScrollPane scrollChatMessage = new JScrollPane(chatMessage);
        JTextField tfMessage = new JTextField("");
        JButton btSend = new JButton("Send");

        //Colocación de los componentes en la ventana
        Container container = this.getContentPane();
        container.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20,20,20,20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        container.add(scrollChatMessage, gbc);

        // Restaura valores por defecto
        gbc.gridwidth = 1;
        gbc.weighty = 0;

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 20, 20, 20);

        gbc.gridx = 0;
        gbc.gridy = 1;
        container.add(tfMessage, gbc);

        // Restaura valores por defecto
        gbc.weightx = 0;

        gbc.gridx = 1;
        gbc.gridy = 1;
        container.add(btSend, gbc);

        this.setBounds(400, 100, 400, 500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Vemtana de configuración inicial
        WindowConfig windowConfig = new WindowConfig(this);
        host = windowConfig.getHost();
        port = windowConfig.getPort();
        user = windowConfig.getUser();

        logger.info("You are trying to connect to " + host + " in the port " + port + " with the username: " + user + ".");

        //se crea el socket para conectar con  el servidor del chat
        try {
            socket = new Socket(host, port);
        } catch (UnexpectedException e) {
            logger.info("Could not connect to the server (" + e.getMessage() + ").");
        } catch (IOException exp) {
            logger.info("Could not connect to the server (" + exp.getMessage() + ").");
        }
        //Accion para el botón enviar
        btSend.addActionListener(new ConnectionServer(socket, tfMessage, user));
    }
    public void receiveServerMessages() {
        //Obtiene el flujo de entrada del socket
        DataInputStream inputData = null;
        String message;
        try {
            inputData = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            logger.info("Error creating input stream: " + e.getMessage());
        } catch (NullPointerException exp) {
            logger.info("The socket was not created correctly. ");
        }
        //Bucle infinito que recibe mensajes del servidor
        boolean connected = true;
        while (connected) {
            try {
                message = inputData.readUTF();
                chatMessage.append(message + System.lineSeparator() );
            } catch (IOException e) {
                logger.info("Error reading from input stream: " + e.getMessage());
                connected = false;
            } catch (NullPointerException exp) {
                logger.info("The socket was not created correctly. ");
                connected = false;
            }
        }
    }

    public static void main(String[] args) {
        // Carga el archivo de configuracion de log4J
        //PropertyConfigurator.configure("log4j.properties");

        ChatClient clientChat =  new ChatClient();
        clientChat.receiveServerMessages();
    }
}

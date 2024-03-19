
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

    public class ServerChat {
        public static void main(String[] args) {
            // Carga el archivo de configuracion de log4J
            // PropertyConfigurator.configure("log4j.properties");
            Logger logger = Logger.getLogger(String.valueOf(ServerChat.class));

            int port = 5050;
            int maxConnections = 10; // maximo de conexiones simultáneas
            ServerSocket server = null;
            Socket socket = null;
            ChatMessage chatMessage = new ChatMessage();

            try {
                //se crea el serversocket
                server = new ServerSocket(port, maxConnections);

                //Aquí se hace un bucle infinito para esperar conexiones
                while (true) {
                    logger.info("Server is waiting for new connections.");
                    socket = server.accept();
                    logger.info("Client with the ip: " + socket.getInetAddress().getHostName() + "has been connected");

                    // falta la conexion del cliente
                }
            } catch (IOException e) {
                logger.info("Error al cerrar el servidor: " + e.getMessage());
            } finally {
                try {
                    socket.close();
                    server.close();
                } catch (IOException e) {
                    logger.info("Error al cerrar el servidor: " + e.getMessage());
                }
            }
        }
    }


import java.util.Observable;

    public class ChatMessage extends Observable {
        private String message;

        public ChatMessage() {
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
            //Indica que el mensaje ha cambiado
            this.setChanged();
            //Notifica a los observadores que el mensaje ha sido cambiado y se lo pasa
            //(Internamente notifyObservers llama al metodo update del observador)
            this.notifyObservers(this.getMessage());
        }
}

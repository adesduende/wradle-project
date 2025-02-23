public class Main {
    public static void main(String[] args) {
        // Mostramos un mensaje de bienvenida
        WordleFileManager.SendMessageAndWriteToFile("Bienvenido a Wordle!\nEste es un juego de adivinanza, en el cual debemos adivinar la palabra aleatoria en un numero maximo de intentos\n");
        
        // Obtenemos las palabras del archivo
        String[] fileWords = WordleFileManager.readWords();

        // Creamos una instancia de la clase WordleGame con el array de palabras
        WordleGame game = new WordleGame(fileWords);

        // Iniciamos el juego
        game.Start();

    }    
}

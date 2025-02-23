import java.util.Random;
import java.util.Scanner;

public class WordleGame {
    private final int MAX_TRIES;
    private int WORD_LENGTH = 5;
    private final String[] fileWords;
    private final String secretWord;
    private int remainingAttempts;
    private final String[] triesHistory;

    // Constructor, inicializamos la clase con las variables que necesitamos
    public WordleGame(String[] fileWords) {
        this.fileWords = fileWords;
        this.secretWord = selectRandomWord(this.fileWords);
        this.WORD_LENGTH = this.secretWord.length();
        this.MAX_TRIES = 6;
        this.remainingAttempts = this.MAX_TRIES;
        this.triesHistory = new String[this.MAX_TRIES];
    }

    public void Start() {

        try (
                // Intentamos iniciar el escanner para leer la entrada del usuario
                Scanner scanner = new Scanner(System.in)
            ) {
            // Mostramos el mensaje de inicio
            WordleFileManager.SendMessageAndWriteToFile("INCIANDO JUEGO DE WORDLE");
            WordleFileManager.SendMessageAndWriteToFile("La palabra secreta tiene " + this.WORD_LENGTH + " letras");
            WordleFileManager.SendMessageAndWriteToFile("Tienes " + this.MAX_TRIES + " intentos para adivinar la palabra secreta");
            WordleFileManager.SendMessageAndWriteToFile("Inserte una palabra de " + this.WORD_LENGTH + " letras");

            // Mientras el usuario tenga intentos
            while (remainingAttempts > 0) {
                // Obtenemos la palabra del usuario
                String userInput = getUserInput(scanner);

                // Palabra ingresada por el usuario coloreada
                final String coloredString = WordleFeedBack.getColoredString(userInput, secretWord);

                // Guardamos la palabra coloreada en el historial
                this.triesHistory[this.MAX_TRIES - remainingAttempts] = coloredString;

                // Chekeamos si la palabra es igual a la palabra secreta
                if (userInput.equals(secretWord)) {
                    // Guardamos la palabra en el archivo de texto
                    WordleFileManager.SendMessageAndWriteToFile(coloredString);
                    // Si es igual imprimimos el mensaje de felicidades
                    WordleFileManager.SendMessageAndWriteToFile("Felicidades! Has adivinado la palabra secreta");
                    break;
                }
                // Informamos al usuario cuantos intentos le quedan
                WordleFileManager.SendMessageAndWriteToFile("Te quedan " + (remainingAttempts-1) + " intentos");

                // Mostramos el historial de palabras ingresadas
                showTriesHistory();

                // DIminuimos el numero de intentos en uno
                remainingAttempts--;
            }

            // Cerramos el escanner
            scanner.close();
        }

        // Si no nos quedan intentos imprimimos el mensaje de game over
        if (remainingAttempts == 0) {
            // Imprimimos game over
            WordleFileManager.SendMessageAndWriteToFile("GAME OVER");
            // Mostramos la palabra secreta
            WordleFileManager.SendMessageAndWriteToFile("La palabra secreta era: " + this.secretWord);
        }
    }

    // Metodo para obtener la palabra del usuario
    private String getUserInput(Scanner scanner) {
        // Cambiamos el color de la consola a verde
        System.out.print(WordleFeedBack.ANSI_GREEN);
        // Comprobamos que tenma un maximo de WORD_LENGTH letras hasta que no lo tenga no continuamos
        String userInput = scanner.nextLine();
        while (userInput.length() != this.WORD_LENGTH) {
            WordleFileManager.SendMessageAndWriteToFile(WordleFeedBack.ANSI_RED + "La palabra ingresada tiene " + userInput.length()
                    + " letras, por favor ingrese una palabra de " + this.WORD_LENGTH + " letras"
                    + WordleFeedBack.ANSI_GREEN);
            userInput = scanner.nextLine();
        }
        return userInput.toUpperCase();
    }

    // Metodo para mostrar por consola todas las palabras del historial
    private void showTriesHistory() {
        for (int i = 0; i < this.MAX_TRIES; i++) {
            if (this.triesHistory[i] != null) {
                WordleFileManager.SendMessageAndWriteToFile(this.triesHistory[i]);
            }
        }
    }

    // Metodo para seleccionar una palabra aleatoria de un array de palabras
    private String selectRandomWord(String[] words) {
        // Creamos un objeto random
        Random random = new Random();
        // Retornamos una palabra aleatoria en mayusculas
        return words[random.nextInt(words.length)].toUpperCase();
    }

}

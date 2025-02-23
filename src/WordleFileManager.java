import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class WordleFileManager {
    private static final String FILE_PATH = "src/";
    private static final String FILE_NAME = "words.txt";
    private static final String OUTPUT_FILE_PATH = "src/logs/";
    private static String OUTPUT_FILE_NAME;

    /*
     * Metodo para leer las palabras del archivo
     * @return array de palabras
     * @return array vacio si no se puede leer el archivo
     */
    public static String[] readWords() {
        // Intentamos leer el archivo sino devolvemos un array vacio con un mensaje de error
        try {
            // Leemos el archivo
            File file = new File(FILE_PATH + FILE_NAME);
            // Creamos un escanner para leer el archivo y controlamos que sea posible
            try (Scanner scanner = new Scanner(file)) {

                // Creamos un array de palabras vacio
                List<String> words = new ArrayList<>();

                // Iteramos sobre las palabras del archivo y las agregamos al array
                while (scanner.hasNextLine()) {
                    String word = scanner.nextLine();
                    words.add(word);
                }

                // Cerramos el scanner
                scanner.close();

                // Devolvemos el array de palabras
                return words.toArray(String[]::new);
            }
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no se ha encontrado: " + e.getMessage());
            return new String[0];
        }
    }
    /*
     * Metodo para enviar un mensaje y escribirlo en un archivo de salida
     * @param message mensaje a enviar
     */
    public static void SendMessageAndWriteToFile(String message) {
        try {
            // Revisamos si el nombre del archivo de salida ha sido creado
            if (OUTPUT_FILE_NAME == null) {
                CreateOutputFile();
            }
            // Creamos enlace al archivo de salida y intentamos escribir el mensaje
            File file = new File(OUTPUT_FILE_PATH + OUTPUT_FILE_NAME);
            
            // Creamos un string vacio para almacenar el contenido del archivo
            String text = "";
            try (Scanner scanner = new Scanner(file)) {

                // Leemos el archivo para no sobreescribir el contenido
                while (scanner.hasNextLine()) {
                    text += scanner.nextLine() + "\n";
                }
                scanner.close();
            }
            try (FileWriter fileWriter = new FileWriter(file)) {
                // Escribimo el mensaje al final del archivo
                fileWriter.write(text + message);
                // Cerramos el archivo y el escanner
                fileWriter.close();
                // Imprimimos por consola el mensaje
                System.out.println(message);
            }
        } catch (IOException e) {
            System.err.println("Un error ha ocurrido durante la manipulacion del archivo: " + e.getMessage());
        }
    }
    /*
     * Metodo para crear un archivo de salida
     */
    private static void CreateOutputFile() {
        try {
            // Creamos un nombre para el archivo de salida con la fecha actual
            // de esta manera evitamos sobreescribir archivos
            Date date = new Date();
            OUTPUT_FILE_NAME = "output-" + date.getTime() + ".txt";
            File file = new File(OUTPUT_FILE_PATH + OUTPUT_FILE_NAME);
            file.createNewFile();
        } catch (IOException e) {
            System.err.println("Un error ha ocurrido durante la creacion del archivo: " + e.getMessage());
        }

    }
}

public class WordleFeedBack {
    //private static final int WORD_LENGTH = 5;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    // Metodo para aplicar el color a un char
    private static String applyColor(String color, char  letter) {
        return color + letter + ANSI_RESET;
    }

    // Metodo para devolver el String coloreado
    public static String getColoredString(String guess, String secretWord) {
        StringBuilder feedback = new StringBuilder();

        // Iteramos sobre cada letra de la palabra ingresada
        for (int i = 0; i < guess.length(); i++) {
            char letter = guess.charAt(i);
            /* Si la letra esta en la palabra secreta y en la misma posicion
            // la pintamos de verde y si esta en la palabra secreta pero no en la misma posicion
            // la pintamos de amarillo sino no la pintamos blanca
            */
            if (secretWord.contains(String.valueOf(letter))) {
                if (secretWord.charAt(i) == guess.charAt(i)) {
                    feedback.append(applyColor(ANSI_GREEN, letter));
                } else {
                    feedback.append(applyColor(ANSI_YELLOW, letter));
                }
            } else {
                feedback.append(applyColor(ANSI_WHITE, letter));
            }
            
        }

        // Devolvemos la palabra coloreada
        return feedback.toString();
    }
}

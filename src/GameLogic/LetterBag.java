package GameLogic;
import java.util.Random;

import java.util.ArrayList;

public class LetterBag {
    public static ArrayList<Letter> letters = new ArrayList<>();
    private static final Random random = new Random();
    public static void initialise() {
        // Ajoutez chaque lettre avec ses points et son nombre d'occurrences
        letters.add(new Letter("A", 1, 9));
        letters.add(new Letter("B", 3, 2));
        letters.add(new Letter("C", 3, 2));
        letters.add(new Letter("D", 2, 4));
        letters.add(new Letter("E", 1, 12));
        letters.add(new Letter("F", 4, 2));
        letters.add(new Letter("G", 2, 3));
        letters.add(new Letter("H", 4, 2));
        letters.add(new Letter("I", 1, 9));
        letters.add(new Letter("J", 8, 1));
        letters.add(new Letter("K", 5, 1));
        letters.add(new Letter("L", 1, 4));
        letters.add(new Letter("M", 3, 2));
        letters.add(new Letter("N", 1, 6));
        letters.add(new Letter("O", 1, 8));
        letters.add(new Letter("P", 3, 2));
        letters.add(new Letter("Q", 10, 1));
        letters.add(new Letter("R", 1, 6));
        letters.add(new Letter("S", 1, 4));
        letters.add(new Letter("T", 1, 6));
        letters.add(new Letter("U", 1, 4));
        letters.add(new Letter("V", 4, 2));
        letters.add(new Letter("W", 4, 2));
        letters.add(new Letter("X", 8, 1));
        letters.add(new Letter("Y", 4, 2));
        letters.add(new Letter("Z", 10, 1));
    }
    public static void printLetterBag(){
        String str = new String("");
        for(Letter l : letters){
            str += l.toString() + "\n";
        }
        System.out.println(str);
    }
    public static int getWorldPoints(String word) {
        int points = 0;
        word = word.toUpperCase();
        for (char c : word.toCharArray()) {
            Letter l = getLetter(String.valueOf(c));
            if (l != null){
                points += l.getPoints();
            }

        }
        return points;
    }
    public static Letter getLetter(String letter) {
        for (Letter l : letters) {
            if (l.name.equals(letter)) {
                return l;
            }
        }
        return null;
    }
    public static void modifyOccurence(String letter) {
        modifyOccurence(letter,true);
    }
    public static void modifyOccurence(String letter , boolean add) {
        Letter l = getLetter(letter);
        if (l != null) {
            if (add) {
                l.addOccurence();
            }
            else{
                l.removeOccurence();
            }
        }
    }
    public static String drawLetters() {
        // Construire une liste de toutes les lettres disponibles
        ArrayList<String> availableLetters = new ArrayList<>();
        for (Letter letter : letters) {
            for (int i = 0; i < letter.nbOccurence; i++) {
                availableLetters.add(letter.name);
            }
        }

        // Si aucune lettre n'est disponible, retourner null
        if (availableLetters.isEmpty()) {
            return null;
        }
        String chosenLetter = availableLetters.get(random.nextInt(availableLetters.size()));
        modifyOccurence(chosenLetter, false);

        return chosenLetter;
   }

}

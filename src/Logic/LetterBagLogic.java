package Logic;

import Entity.Letter;
import Entity.LetterBag;
import java.util.Random;

import java.util.ArrayList;

public class LetterBagLogic {
    private static final Random random = new Random();
    public int getWorldPoints(String word) {
        int points = 0;
        word = word.toUpperCase();
        for (char c : word.toCharArray()) {
            Entity.Letter l = getLetter(String.valueOf(c));
            if (l != null){
                points += l.getPoints();
            }

        }
        return points;
    }
    public Entity.Letter getLetter(String letter) {
        for (Entity.Letter l : LetterBag.letters) {
            if (l.name.equals(letter)) {
                return l;
            }
        }
        return null;
    }
    public String drawLetters() {
        // Construire une liste de toutes les lettres disponibles
        ArrayList<String> availableLetters = new ArrayList<>();
        for (Letter letter : LetterBag.letters) {
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
    public void modifyOccurence(String letter) {
        modifyOccurence(letter,true);
    }
    public void modifyOccurence(String letter , boolean add) {
        Entity.Letter l = getLetter(letter);
        if (l != null) {
            if (add) {
                l.addOccurence();
            }
            else{
                l.removeOccurence();
            }
        }
    }
}

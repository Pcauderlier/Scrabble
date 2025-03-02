package Entity;

import Entity.Letter;
import Logic.LetterBagLogic;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class LetterBag {
    public static ArrayList<Entity.Letter> letters = new ArrayList<>();
    public static void initialise() {
        // Ajoutez chaque lettre avec ses points et son nombre d'occurrences
        letters.add(new Entity.Letter("A", 1, 9));
        letters.add(new Entity.Letter("B", 3, 2));
        letters.add(new Entity.Letter("C", 3, 2));
        letters.add(new Entity.Letter("D", 2, 4));
        letters.add(new Entity.Letter("E", 1, 12));
        letters.add(new Entity.Letter("F", 4, 2));
        letters.add(new Entity.Letter("G", 2, 3));
        letters.add(new Entity.Letter("H", 4, 2));
        letters.add(new Entity.Letter("I", 1, 9));
        letters.add(new Entity.Letter("J", 8, 1));
        letters.add(new Entity.Letter("K", 5, 1));
        letters.add(new Entity.Letter("L", 1, 4));
        letters.add(new Entity.Letter("M", 3, 2));
        letters.add(new Entity.Letter("N", 1, 6));
        letters.add(new Entity.Letter("O", 1, 8));
        letters.add(new Entity.Letter("P", 3, 2));
        letters.add(new Entity.Letter("Q", 10, 1));
        letters.add(new Entity.Letter("R", 1, 6));
        letters.add(new Entity.Letter("S", 1, 4));
        letters.add(new Entity.Letter("T", 1, 6));
        letters.add(new Entity.Letter("U", 1, 4));
        letters.add(new Entity.Letter("V", 4, 2));
        letters.add(new Entity.Letter("W", 4, 2));
        letters.add(new Entity.Letter("X", 8, 1));
        letters.add(new Entity.Letter("Y", 4, 2));
        letters.add(new Entity.Letter("Z", 10, 1));
    }
    public static void printLetterBag(){
        String str = new String("");
        for(Entity.Letter l : letters){
            str += l.toString() + "\n";
        }
        System.out.println(str);
    }
    // Méthode pour convertir le sac de lettres en JSON
    public static String convertLetterBagToJson() {
        Gson gson = new Gson();
        return gson.toJson(LetterBag.letters);
    }

    // Méthode pour transformer un JSON en LetterBag
    public static void convertJsonToLetterBag(String json) {
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Letter>>(){}.getType();
        ArrayList<Entity.Letter> letterList = gson.fromJson(json, listType);
        // Mettre à jour la liste des lettres
        letters.clear();  // Effacer les anciennes lettres
        letters.addAll(letterList);  // Ajouter les nouvelles lettres depuis le JSON
    }
}


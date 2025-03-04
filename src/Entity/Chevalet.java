package Entity;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Chevalet {
    private final List<String> lettres;

    public Chevalet() {
        this.lettres = new ArrayList<>();
    }

    public void ajouterLettre(String lettre) {
        this.lettres.add(lettre);
    }

    public void retirerLettre(String lettre) {
        this.lettres.remove(lettre);
    }

    public String getLettre(int index) {
        if (index >= 0 && index < lettres.size()) {
            return lettres.get(index);
        }
        return null;
    }
    public void update(List<String> nouvellesLettres) {
        this.clear();
        this.lettres.addAll(nouvellesLettres);
    }
    public void clear() {
        this.lettres.clear();
    }

    public List<String> getLettres() {
        return new ArrayList<>(lettres); // Retourne une copie pour éviter la modification externe
    }

    public int taille() {
        return lettres.size();
    }

    public String chevaletToJson(){
        Gson gson = new Gson();
        return gson.toJson(lettres);
    }
    public void jsonToChevalet(String json){
        Gson gson = new Gson();
        update( gson.fromJson(json, ArrayList.class));
    }

    public String afficher() {
        StringBuilder sb = new StringBuilder();
        for (String lettre : lettres) {
            sb.append("| ").append(lettre).append(" ");
        }
        sb.append("|\n");

        for (int i = 0; i < lettres.size(); i++) {
            sb.append("| ").append(i).append(" ");
        }

        return sb.toString();
    }
}

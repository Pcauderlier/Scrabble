package GUI;

import Data.GameRepository;
import Entity.Board;
import Entity.Game;
import Entity.LetterBag;
import Entity.Player;
import Logic.BoardLogic;
import Logic.GameLogic;
import Logic.PlayerLogic;

import java.util.ArrayList;
import java.util.Scanner;

public class GameProgress {
    private static Scanner sc = new Scanner(System.in);

    public static int askNum(){
        int num;
        while (true) {
            if (sc.hasNextInt()) {
                num = sc.nextInt();
                sc.nextLine();
                return num;
            } else {
                System.out.println("Entrée invalide. Veuillez entrer un nombre entier.");
                sc.nextLine();
            }
        }
    }
    public static void main (String[] args){
        System.out.println("Bienvenue dans Scrabble !");
        System.out.println("Voulez commencer une nouvelle partie (1) ou continuer une partie existante (2)");
        int num = askNum();
        GameLogic gameLogic = new GameLogic(new Game());
        while(!(num == 1 || num == 2)){
            System.out.println("Voulez commencer une nouvelle partie (1) ou continuer une partie existante (2)");
            num = askNum();
        }
        if (num == 1){
            gameLogic.newGame();

        }
        else if (num == 2){
            gameLogic.loadGame();
        }
    }
}

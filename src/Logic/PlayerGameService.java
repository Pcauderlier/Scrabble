package Logic;

import Data.PlayerGameRepository;
import Entity.Chevalet;
import Entity.Game;
import Entity.Player;

public class PlayerGameService {
    private PlayerGameRepository playerGameRepository = new PlayerGameRepository();
    public void upsertPlayerGame (Game game){
        Player player = game.getCurrentPlayer();
        int playerIndex = game.getPlayerIndex();
        int score = player.getScore();
        String chevalet = player.getChevalet().chevaletToJson();
        if (playerGameRepository.playerGameExists(player, game)) {
            playerGameRepository.updatePlayerGame(player, game, score, chevalet);
        }
        else{
            playerGameRepository.createPlayerGame(player, game, playerIndex, score, chevalet);
        }
    }

}

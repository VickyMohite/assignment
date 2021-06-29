package main.game.com.services;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import main.game.com.models.Board;
import main.game.com.models.Player;
import main.game.com.models.Snake;

public class SnakeAndLadderService {
	
	private Board board;
	
	public static final int BOARD_SIZE = 100;
	
	
	private Queue<Player> players;
	
	public SnakeAndLadderService() {
		this.board = new Board(BOARD_SIZE);
	}
	
	public void setPlayers(List<Player> players) {
		this.players = new LinkedList<Player>();
        Map<String, Integer> playerPieces = new HashMap<String, Integer>();
        for (Player player : players) {
            this.players.add(player);
            playerPieces.put(player.getId(), 0);
        }
        board.setPlayerPieces(playerPieces);
	}

	public void setSnakes(List<Snake> snakes) {
        board.setSnakes(snakes);
    }
	
	private void movePlayer(Player player, int positions) {
        int oldPosition = board.getPlayerPieces().get(player.getId());
        int newPosition = oldPosition + positions;

        int boardSize = board.getSize();
        
        if (newPosition > boardSize) {
            newPosition = oldPosition;
        } else {
            newPosition = getNewPositionAfterGoingThroughSnakesAndLadders(newPosition);
        }

        board.getPlayerPieces().put(player.getId(), newPosition);

        System.out.println(player.getName() + " rolled a " + positions + " and moved from " + oldPosition +" to " + newPosition);
    }
	
	private int getNewPositionAfterGoingThroughSnakesAndLadders(int newPosition) {
        int previousPosition;

        do {
            previousPosition = newPosition;
            for (Snake snake : board.getSnakes()) {
                if (snake.getStart() == newPosition) {
                	System.out.println("****************Snake****************");
                    newPosition = snake.getEnd();
                }
            }

        } while (newPosition != previousPosition);
        return newPosition;
    }
	
	private boolean hasPlayerWon(Player player) {
        int playerPosition = board.getPlayerPieces().get(player.getId());
        int winningPosition = board.getSize();
        return playerPosition == winningPosition;
    }
	
	public void startGame() {
		int turn = 0;
		while(turn < 10) {
			int totalDiceValue = DiceService.roll();
			System.out.println(" Turn : " + (turn+1));
			Player currentPlayer = players.poll();
			movePlayer(currentPlayer, totalDiceValue);
			if(hasPlayerWon(currentPlayer)) {
				System.out.println(currentPlayer.getName() + " wins the game");
				board.getPlayerPieces().remove(currentPlayer.getId());
			}else {
				players.add(currentPlayer);
			}
			turn++;
		}
	}
}

package main.game.com;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.game.com.models.Player;
import main.game.com.models.Snake;
import main.game.com.services.SnakeAndLadderService;

public class SnakeAndLadderGame {
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);		
		SnakeAndLadderService game = new SnakeAndLadderService();
		
		List<Player> players = new ArrayList<Player>();
		System.out.println("Enter player name : ");
		Player player = new Player(scanner.next());	
		players.add(player);
		game.setPlayers(players);
		
		List<Snake> snakes = new ArrayList<Snake>();
		snakes.add(new Snake(14, 7));
		game.setSnakes(snakes);
		
		game.startGame();
		scanner.close();
	}
	
}

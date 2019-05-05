
import enigma.core.Enigma;
import enigma.event.TextMouseEvent;
import enigma.event.TextMouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import enigma.console.TextAttributes;
import java.awt.Color;

public class Game {

	static enigma.console.Console cn = Enigma.getConsole("Game", 80, 25, 20, 0);
	
	char[][] screen = new char[21][60];
	char[][] backup= new char[21][60];
	Snake snake;
	int score = 0;
	int level = 0;
	int time = 0;

	public void printScreen() {
		for (int i = 0; i < 21; i++) {
			for (int j = 0; j < 60; j++) {
				if (i == 0 || j == 0 || j == 59 || i == 20) {
					backup[i][j] = '#';
					System.out.print(backup[i][j]);
				}

				else {
					backup[i][j] = ' ';
					System.out.print(backup[i][j]);
				}

			}
			System.out.println();

		}
		for (int j = 0; j < 50; j++) {
			Random rnd = new Random();
			int x = rnd.nextInt(19) + 1;
			int y = rnd.nextInt(58) + 1;

			cn.getTextWindow().setCursorPosition(y, x);
			backup[x][y]=snake.randomChar();
			System.out.println(backup[x][y]);
		}

		cn.getTextWindow().setCursorPosition(65, 0);

		System.out.println("SCORE: " + score);
		cn.getTextWindow().setCursorPosition(65, 1);

		System.out.println("----------");

	}

	public void printSnake() {
		int x = snake.linkedsnake.head.data.getX();
		int y = snake.linkedsnake.head.data.getY();
		screen[y][x]=snake.linkedsnake.head.data.getDnapart();
		cn.getTextWindow().setCursorPosition(x, y);
		snake.print();

	}
	
	public void cleanSnake() {
		
		int x = snake.linkedsnake.head.data.getX();
		int y = snake.linkedsnake.head.data.getY();
		cn.getTextWindow().setCursorPosition(x,y);
		for (int i = 0; i < snake.linkedsnake.size(); i++) {
			screen[y][x]=' ';
			System.out.print(" ");
			
		}
	}

	Game() throws Exception { // --- Contructor
		snake = new Snake();
		int countTime = 0;
		printScreen();
		printSnake();
		while (true) {

			cleanSnake();
			
			snake.linkedsnake.head.data.setX(snake.linkedsnake.head.data.getX()-1);

			int x = snake.linkedsnake.head.data.getX();
			int y = snake.linkedsnake.head.data.getY();
			
			if(backup[y][x]=='#') {
				System.out.println("Tisss");
				break;
			}
			if(backup[y][x]!=' ') {
				Node_data nd = new Node_data();
				nd.setDnapart(backup[y][x]);
				nd.setX(x);
				nd.setY(y);
				snake.add(nd);
				
			}
			cn.getTextWindow().setCursorPosition(x, y);
			printSnake();


			Thread.sleep(500);
			countTime++;
			if (countTime == 2) {
				countTime = 0;
				time++;
			}
			if (time % 20 == 0) {
				level++;
			}

			cn.getTextWindow().setCursorPosition(65, 20);

			System.out.println("Time:  " + time);
			cn.getTextWindow().setCursorPosition(65, 19);

			System.out.println("Level: " + level);

		}
	}

}

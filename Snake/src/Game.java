
import enigma.core.Enigma;
import enigma.event.TextMouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Scanner;

import java.awt.Color;
import java.awt.Window;

public class Game {
	Menu menu = new Menu();

	
	static enigma.console.Console cn = Enigma.getConsole("Game", 80, 25, 20, 0);
	public TextMouseListener tmlis;
	public KeyListener klis;

	// ------ Standard variables for mouse and keyboard ------
	public int keypr; // key pressed?
	public int rkey; // key (for press/release)
	// ----------------------------------------------------

	char[][] screen = new char[21][60];
	char[][] backup = new char[21][60];
	Snake snake;
	boolean flag = true;
	int score = 0;
	int level = 0;
	int time = 0;
	int countTime = 0;


	public static void consoleClear() {
		cn.getTextWindow().setCursorPosition(0, 0);
		for (int i = 0; i < 23; i++) {
			for (int j = 0; j < 78; j++) {
				System.out.print(" ");
			}
		}
		cn.getTextWindow().setCursorPosition(0, 0);
	}

	public void printScreen() {
		for (int i = 0; i < 21; i++) {
			for (int j = 0; j < 60; j++) {
				if (i == 0 || j == 0 || j == 59 || i == 20) {
					backup[i][j] = '#';
					System.out.print(backup[i][j]);
				} else {
					backup[i][j] = ' ';
					System.out.print(backup[i][j]);
				}
			}
			System.out.println();
		}
		/*for (int j = 0; j < 3; j++) {
			randomPosition(snake.randomChar());
		}*/
	}

	public void randomPosition(char ch) {
		Random rnd = new Random();
		int x = rnd.nextInt(19) + 1;
		int y = rnd.nextInt(58) + 1;

		cn.getTextWindow().setCursorPosition(y, x);
		backup[x][y] = ch;
		System.out.println(backup[x][y]);
	}

	public void printSnake() {
		snake.print();
	}

	public void cleanSnake() {
		
		for (int i = 0; i < 55; i++) {
			for (int j = 0; j < 15; j++) {
				cn.getTextWindow().setCursorPosition(3+i, 3+j);
				System.out.println(" ");
			}
		}
	}

	public void scoringTable() {
		cn.getTextWindow().setCursorPosition(65, 20);
		System.out.println("Time:  " + time);
		cn.getTextWindow().setCursorPosition(65, 19);
		System.out.println("Level: " + level);
		cn.getTextWindow().setCursorPosition(65, 0);
		System.out.println("SCORE: " + score);
		cn.getTextWindow().setCursorPosition(65, 1);
		System.out.println("----------");
	}

	public String login() {
		Scanner scan = new Scanner(System.in);
		System.out.print("name: ");
		String input = scan.next();
		consoleClear();
		return input;
	}

	Game() throws Exception { // --- Contructor
		//menu.menu();
		//login();
		snake = new Snake();
		
		printSnake();

		// ------ Standard code for mouse and keyboard ------ Do not change

		klis = new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
				if (keypr == 0) {
					keypr = 1;
					rkey = e.getKeyCode();
				}
			}

			public void keyReleased(KeyEvent e) {
			}
		};
		cn.getTextWindow().addKeyListener(klis);

		snake.direction = 0;
		while (flag) {

			
			cleanSnake();
			//cn.getTextWindow().setCursorPosition(x, y);
			consoleClear();
			printScreen();
			printSnake();
			// ----------------------------------------------------
			if (keypr == 1) { // if keyboard button pressed
				if (rkey == KeyEvent.VK_LEFT) {
					snake.direction = 0;
				}

				if (rkey == KeyEvent.VK_RIGHT) {
					snake.direction = 1;
				}
			
		        if(rkey==KeyEvent.VK_UP) {
		            snake.direction = 2;
		       }
		        if(rkey==KeyEvent.VK_DOWN) {
		            snake.direction = 3;
		       }
		            
				keypr = 0;
			}

			


			int x = snake.linkedsnake.head.data.getX();
			int y = snake.linkedsnake.head.data.getY();

			if (backup[y][x] == '#') {
				System.out.println("Tisss");
				flag = false;
			}
			else if (backup[y][x] != ' ') {
				Node_data nd = new Node_data();
				nd.setDnapart(backup[y][x]);
				nd.setX(x);
				nd.setY(y);
				snake.add(nd);
				score += 5;

			}
			

			countTime++;
			if (countTime == 2) {
				countTime = 0;
				time++;
			}
			if (time % 20 == 0 && countTime == 0) {
				level++;
				randomPosition('#');
			}

			scoringTable();
			Thread.sleep(500);

		}
	}

}

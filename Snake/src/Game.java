
import enigma.core.Enigma;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
public class Game {
	Menu menu = new Menu();


	static enigma.console.Console cn = Enigma.getConsole("Game", 80, 25, 20, 0);
	public KeyListener klis;

	// ------ Standard variables for mouse and keyboard ------
	public int keypr; // key pressed?
	public int rkey; // key (for press/release)
	// ----------------------------------------------------

	static char[][] screen = new char[21][60];
	static char[][] backup = new char[21][60];
	Snake snake;
	boolean flag = true;
	int score = 0;
	int level = 0;
	int time = 0;
	int countTime = 0;
	String name = "";
	static Player player;
	DoubleLinkedList dll = new DoubleLinkedList();
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
		for (int j = 0; j < 3; j++) {
			randomPosition(snake.randomChar());
		}
	}

	public void randomPosition(char ch) {
		Random rnd = new Random();
		int x = rnd.nextInt(19) + 1;
		int y = rnd.nextInt(58) + 1;

		cn.getTextWindow().setCursorPosition(y, x);
		if(backup[x][y]== ' ' ) {
			Node_data nd = new Node_data(x,y,ch,"apple");
			backup[x][y] = nd.getDnapart();
		}	
		System.out.println(backup[x][y]);
	}

	public void printSnake() {
		snake.print();
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

	
	
	public void Scoring() {
		int x = snake.linkedsnake.head.data.getX();
		int y = snake.linkedsnake.head.data.getY();

		if (backup[y][x] == '#') {
			System.out.println("Tisss");
			flag = false;
		}
		else if (backup[y][x] != ' ') {
			Node_data nd = new Node_data(x,y,backup[y][x],"snake");
			snake.add(nd);
			score += 5;
			randomPosition(snake.randomChar());
	
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


	}
	
	public String login() {
		Scanner scan = new Scanner(System.in);
		cn.getTextWindow().setCursorPosition(33, 12);
		int cl=	cn.getTextAttributes().getBackground().getBlue();
		System.out.printf("NAME: ",cl);
		name = scan.next();
		Game.consoleClear();
		return name;
	}
	
	
	public void writeIntoFile() throws IOException {
		File file = new File("dosya.txt");

        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(file, false);
        BufferedWriter bWriter = new BufferedWriter(fileWriter);
        bWriter.write(dll.display1());
        bWriter.close();

	}
	
	public void readFile() throws IOException {
		File file = new File("dosya.txt");
		
        if (!file.exists()) {
            file.createNewFile();
        }
        
		FileReader fileReader = new FileReader("dosya.txt");
		String line;

		BufferedReader br = new BufferedReader(fileReader);
	
			while ((line = br.readLine()) != null) {
				String[] spl = line.split(" "); 
				int scr =Integer.parseInt(spl[2].trim());
				 player = new Player(scr,spl[1].trim());
				 dll.add(player);
			}	

		
		br.close();

	}
	static public void writeToScreen() throws IOException {
		File file = new File("dosya.txt");
		
        if (!file.exists()) {
            file.createNewFile();
        }
        
		FileReader fileReader = new FileReader("dosya.txt");
		String line;

		BufferedReader br = new BufferedReader(fileReader);
		
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
		br.close();

	}
	
	
	
	
	
	Game() throws Exception { // --- Contructor
		//menu.menu();
		name = login();
		consoleClear();
		snake = new Snake();
		printScreen();
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

		//snake.direction = 0;
		while (flag) {
			printSnake();
			
			// ----------------------------------------------------

			if (keypr == 1) { // if keyboard button pressed
				if (snake.direction != 1 && rkey == KeyEvent.VK_LEFT) {
					snake.direction = 0;
				}

				if (snake.direction != 0 &&rkey == KeyEvent.VK_RIGHT) {
					snake.direction = 1;
				}
			
		        if(snake.direction != 3 &&rkey==KeyEvent.VK_UP) {
		            snake.direction = 2;
		       }
		        if(snake.direction != 2 && rkey==KeyEvent.VK_DOWN) {
		            snake.direction = 3;
		       }
		            
				keypr = 0;
			}


			Scoring();
			scoringTable();
			Thread.sleep(100);

		}
		player= new Player(score,name);
		dll.add(player);
		readFile();
		writeIntoFile();
		Menu.HighScoreScreen();

	}

}

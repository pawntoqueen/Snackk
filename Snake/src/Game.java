
import enigma.core.Enigma;
import enigma.event.TextMouseEvent;
import enigma.event.TextMouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import enigma.console.TextAttributes;
import java.awt.Color;

public class Game {

	static enigma.console.Console cn = Enigma.getConsole("Game", 80, 25, 20, 0);
	public TextMouseListener tmlis;
	public KeyListener klis;
	// ------ Standard variables for mouse and keyboard ------
	public int mousepr; // mouse pressed?
	public int mousex, mousey; // mouse text coords.
	public int keypr; // key pressed?
	public int rkey; // key (for press/release)
	// ----------------------------------------------------

	char[][] screen = new char[21][60];
	Snake snake;
	int score = 0;
	int level = 0;
	int time = 0;

	public void printScreen() {
		for (int i = 0; i < 21; i++) {
			for (int j = 0; j < 60; j++) {
				if (i == 0 || j == 0 || j == 59 || i == 20) {
					screen[i][j] = '#';
					System.out.print("#");
				}

				else
					System.out.print(" ");
			}
			System.out.println();
		}

		cn.getTextWindow().setCursorPosition(65, 0);

		System.out.println("SCORE: " + score);
		cn.getTextWindow().setCursorPosition(65, 1);

		System.out.println("----------");
		cn.getTextWindow().setCursorPosition(65, 19);

		System.out.println("Level: " + level);
		cn.getTextWindow().setCursorPosition(65, 20);

		System.out.println("Time:  " + time);

	}

	public void printSnake() {
		int x = snake.linkedsnake.head.data.getX();
		int y = snake.linkedsnake.head.data.getY();
		cn.getTextWindow().setCursorPosition(x, y);
		snake.print();

	}

	Game() throws Exception { // --- Contructor
		snake = new Snake();
		printScreen();
		printSnake();
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
		// ----------------------------------------------------

		while (true) {

			cn.getTextWindow().setCursorPosition(snake.linkedsnake.head.data.getX(),
					snake.linkedsnake.head.data.getY());
			System.out.println(" ");
			snake.linkedsnake.head.data.setX(snake.linkedsnake.head.data.getX() + 1);

			int x = snake.linkedsnake.head.data.getX();
			int y = snake.linkedsnake.head.data.getY();

			cn.getTextWindow().setCursorPosition(x, y);
			snake.print();

			keypr = 0;

			Thread.sleep(500);
		}
	}

}

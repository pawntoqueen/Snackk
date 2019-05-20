import java.awt.Color;
import java.io.IOException;

import enigma.console.TextAttributes;
import enigma.event.TextMouseEvent;
import enigma.event.TextMouseListener;

public class Menu {

	public static TextMouseListener tmlis;

	// ------ Standard variables for mouse ------
	public static int mousepr; // mouse pressed?
	public static int mousex; // mouse text coords.
	public static int mousey;
	// ----------------------------------------------------

	public static String choice = "";

	public static String mouseControl() throws InterruptedException {

		tmlis = new TextMouseListener() {
			public void mouseClicked(TextMouseEvent arg0) {
			}

			public void mousePressed(TextMouseEvent arg0) {
				if (mousepr == 0) {
					mousepr = 1;
					mousex = arg0.getX();
					mousey = arg0.getY();
				}
			}

			public void mouseReleased(TextMouseEvent arg0) {
			}
		};
		Game.cn.getTextWindow().addTextMouseListener(tmlis);

		// ----------------------------------------------------

		int px = 0, py = 0;

		while (true) {
			if (mousepr == 1) { // if mouse button pressed

				px = mousex;
				py = mousey;
				if (mousex >= 0 && mousex <= 13 && mousey == 1)
					choice = "start";
				if (mousex == 0 && mousey == 3)
					choice = "back to menu";
				if (mousex >= 0 && mousex <= 15 && mousey == 3)
					choice = "instructions";
				if (mousex >= 0 && mousex <= 7 && mousey == 5)
					choice = "exit";
				if (mousex >= 0 && mousex <= 15 && mousey == 20)
					choice = "instructions -> menu";
				if (mousex >= 39 && mousex <= 47 && mousey == 20)
					choice = "go to menu";
				if (mousex >= 0 && mousex <= 21 && mousey == 7)
					choice = "high score table";
				if (mousex >= 65 && mousex <= 80 && mousey == 23)
					choice = "play again";

				mousepr = 0; // last action

			}
			// thread command just used for mouse control
			Thread.sleep(20);
			return choice;
		}

	}

	public void menu() throws InterruptedException, IOException {

		Game.cn.getTextWindow().setCursorPosition(5, 5);
		System.out.println("      __  __________    _____  __    _____ _   _____    __ __ ______");
		Game.cn.getTextWindow().setCursorPosition(5, 6);	
		System.out.println( 		"     / / / / ____/ /   /  _/ |/ /   / ___// | / /   |  / //_// ____/");
		Game.cn.getTextWindow().setCursorPosition(5, 7);	
		System.out.println( 		"    / /_/ / __/ / /    / / |   /    \\__ \\/  |/ / /| | / ,<  / __/   ");
		Game.cn.getTextWindow().setCursorPosition(5, 8);	
		System.out.println( 		"   / __  / /___/ /____/ / /   |    ___/ / /|  / ___ |/ /| |/ /___   ");
		Game.cn.getTextWindow().setCursorPosition(5, 9);	
		System.out.println( 		"  /_/ /_/_____/_____/___//_/|_|   /____/_/ |_/_/  |_/_/ |_/_____/   "); 
		Game.cn.getTextWindow().setCursorPosition(5, 10);	
		System.out.println("                                                                    ");
		

		Game.cn.getTextWindow().setCursorPosition(30, 20);
		System.out.println("click to | Start |");

		while (true) {
			mouseControl();
			if (choice.equals("go to menu"))
				break;
		}

		Game.consoleClear();

		while (!choice.equals("start")) {

			Game.consoleClear();

			Game.cn.getTextWindow().setCursorPosition(0, 1);
			System.out.println("| Start Game |");
			System.out.println();
			System.out.println("| Instructions |");
			System.out.println();
			System.out.println("| Exit |");
			System.out.println();
			System.out.println("| High Score Table |");

			mouseControl();

			if (choice.equals("instructions")) {

				Game.consoleClear();
				Game.cn.getTextWindow().setCursorPosition(20, 2);
				TextAttributes attrs4 = new TextAttributes(Color.pink, Color.black);
			    Game.cn.setTextAttributes(attrs4);
				System.out.println(" * * * I N S T R U C T I O N S * * *");
				System.out.println("");
				System.out.println("    - Hello! Welcome to the Helix Snake's instructions. ");
				System.out.println("    - You have a snake with 3 letters assigned randomly out of four letter "
						+ "\n   (A, C, G, T).");
				System.out.println("    - When the snake eats a letter, a new letter must be generated in the game "
						+ "\n   area to maintain starting number of letters. ");
				System.out.println("    - The snake is moving by the player using arrow keys. But be careful! "
						+ "\n The snake do not be able to move back.");
				System.out.println("    - When the snake eats a letter, you will earn 5 points. You will also "
						+ "\n   earn extra points when your snake completes an amino-acid codon.");
				System.out.println("    - You cannot use same letters for different codons!!");
				System.out.println("    - “#” character shows the wall, if the snake bumps into a wall "
						+ "\n   or its own body the game will be over. ");
				System.out.println("    - One “#” wall character will appear randomly in every 20 seconds"
						+ "\n   and level increases.");
				System.out.println();
				Game.cn.getTextWindow().setCursorPosition(0, 20);
				TextAttributes attrs2 = new TextAttributes(Color.pink, Color.black);
			    Game.cn.setTextAttributes(attrs2);
				System.out.println("| Back to Menu |");

				while (true) {
					mouseControl();
					if (choice.equals("instructions -> menu")) {

						Game.consoleClear();
						break;
					}

				}
			}
			if (choice.equals("exit"))
				System.exit(0);

			if (choice.equals("high score table")) {

				Game.consoleClear();
				Game.writeToScreen();

				Game.cn.getTextWindow().setCursorPosition(0, 20);

				System.out.println("| Back to Menu |");
				while (true) {
					mouseControl();
					if (choice.equals("instructions -> menu")) {

						Game.consoleClear();
						break;
					}

				}
			}
		}

		Game.consoleClear();

	}
}

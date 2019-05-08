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

	public String mouseControl() throws InterruptedException {

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

				mousepr = 0; // last action

			}
			// thread command just used for mouse control
			Thread.sleep(20);
			return choice;
		}

	}

	public void menu() throws InterruptedException {

		Game.cn.getTextWindow().setCursorPosition(0, 10);
		System.out.println();

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

			mouseControl();

			
			if (choice.equals("instructions")) {

				Game.consoleClear();
				Game.cn.getTextWindow().setCursorPosition(0, 0);
				System.out.println();
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
			if (choice.equals("exit"))
				System.exit(0);

		}

		Game.consoleClear();

	}
}

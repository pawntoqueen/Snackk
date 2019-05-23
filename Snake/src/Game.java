
import enigma.console.TextAttributes;
import enigma.core.Enigma;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;
import java.awt.Color;
import javax.sound.sampled.*;
public class Game {
	boolean flag1 = false;
	Menu menu = new Menu();
	Snake snake;
	DoubleLinkedList dll ;
	MultiLinkedList mll ;
	Aminoacid aa;
	static enigma.console.Console cn = Enigma.getConsole("Game", 80, 25, 20, 1);
	public KeyListener klis;

	// ------ Standard variables for mouse and keyboard ------
	public int keypr; // key pressed?
	public int rkey; // key (for press/release)
	// ----------------------------------------------------

	static char[][] screen = new char[21][60];
	static char[][] backup = new char[21][60];
	
	static boolean flag;
	int score ;
	int level ;
	int time ;
	int countTime = 0;
	String name = "";
	static Player player;
	
	public static void consoleClear() {
		cn.getTextWindow().setCursorPosition(0, 0);
		for (int i = 0; i < 24; i++) {
			for (int j = 0; j < 78; j++) {
				System.out.print(" ");
			}
		}
		cn.getTextWindow().setCursorPosition(0, 0);
	}

	public void initScreen() {
		cn.setTextAttributes(new TextAttributes(new Color(255, 102, 0)));
		TextAttributes attrs = new TextAttributes(Color.magenta, Color.black);
		 cn.setTextAttributes(attrs);
		for (int i = 0; i < 21; i++) {
			for (int j = 0; j < 60; j++) {
				screen[i][j] = ' ';
				backup[i][j] = ' ';
				if (i == 0 || j == 0 || j == 59 || i == 20) {
					backup[i][j] = '#';
				} 
				System.out.print(backup[i][j]);
			}
			System.out.println();
		}
		for (int j = 0; j < 3; j++) {
			randomPosition(snake.randomChar());
		}
	}

	public void randomPosition(char ch) {
		TextAttributes attrs = new TextAttributes(Color.gray, Color.black);
		cn.setTextAttributes(attrs);
		Random rnd = new Random();
		int x = rnd.nextInt(19) + 1;
		int y = rnd.nextInt(58) + 1;

		cn.getTextWindow().setCursorPosition(y, x);
		if(backup[x][y]== ' ' && screen[x][y]== ' ') {
			Node_data nd = new Node_data(x,y,ch);
			backup[x][y] = nd.getDnapart();
			System.out.println(backup[x][y]);

		}	
		else
			randomPosition(ch);
	}
	
	public void playsound(File Sound) {
		  try {
		   Clip clip = AudioSystem.getClip();
		   clip.open(AudioSystem.getAudioInputStream(Sound));
		   clip.start();
		  } catch (Exception e) {
		  }
		 }

	public void printSnake() {
		TextAttributes attrs = new TextAttributes(Color.pink, Color.black);
		cn.setTextAttributes(attrs);
		snake.print();
	}

	public void scoringTable() {
		cn.setTextAttributes(new TextAttributes(new Color(255, 102, 0)));	
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
		int x = Snake.linkedsnake.head.data.getX();
		int y = Snake.linkedsnake.head.data.getY();
		int	cnt =(Snake.linkedsnake.size()-1)%3;

		if (backup[y][x] == '#') {
			File Clap = new File("death.wav");
			playsound(Clap);
			flag = false;
		}
		else if (backup[y][x] != ' ') {
			Node_data nd = new Node_data(x,y,backup[y][x]);
			backup[y][x] = ' ';
			File Clap = new File("bit.wav");
			playsound(Clap);
			snake.add(nd);
			score += 5;
			randomPosition(snake.randomChar());
			
		}
		if(cnt==0)
			scoreCodons();

		countTime++;
		if (countTime == 2) {
			countTime = 0;
			time++;
		}
		if (time % 20 == 0 && countTime == 0) {
			level++;
			File Clap = new File("levelup.wav");
			playsound(Clap);
			TextAttributes attrs = new TextAttributes(Color.RED, Color.YELLOW);
			cn.setTextAttributes(attrs);
			
			randomPosition('#');
		}


	}
	
	public String login() {
		cn.setTextAttributes(new TextAttributes(new Color(255, 0, 0)));		
		Scanner scan = new Scanner(System.in);
		cn.getTextWindow().setCursorPosition(33, 12);
		int cl=	cn.getTextAttributes().getBackground().getBlue();
		System.out.printf("NAME: ",cl);
		name = scan.next();
		if(!name.contains(";"))
			return name;
		else
			consoleClear();
			login();
		return name;
	}

	public void writeIntoFile() throws IOException {
		File file = new File("highscoretable.txt");

        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(file, false);
        BufferedWriter bWriter = new BufferedWriter(fileWriter);
        bWriter.write(dll.display1());
        bWriter.close();

	}
	
	public void readFile() throws IOException {

		File file = new File("highscoretable.txt");
		
        if (!file.exists()) {
            file.createNewFile();
        }
        
		FileReader fileReader = new FileReader("highscoretable.txt");
		String line;

		BufferedReader br = new BufferedReader(fileReader);
	
			while ((line = br.readLine()) != null) {
				String[] spl = line.split(";"); 
				int scr =Integer.parseInt(spl[1].trim());
				 player = new Player(scr,spl[0].trim());
				 dll.add(player);
			}	

		
		br.close();

	}
	
	static public void writeToScreen() throws IOException {
		File file = new File("highscoretable.txt");
		int i =0;
        if (!file.exists()) {
            file.createNewFile();
        }
        
		FileReader fileReader = new FileReader("highscoretable.txt");
		String line;

		BufferedReader br = new BufferedReader(fileReader);
		
		while ((line = br.readLine()) != null) {
			cn.getTextWindow().setCursorPosition(30,7+i);
			System.out.println(line);
			i++;
		}
		br.close();

	}
	
	public void createMll() throws Exception {

		FileReader fileReader = new FileReader("aminoacids.txt");
		String line;
		BufferedReader br = new BufferedReader(fileReader);
		
		while ((line = br.readLine()) != null) {
			String [] aa= line.split(",");
			mll.addCategory(aa[0]);
			for (int i = 2; i < aa.length; i++) {
				String[] codon = aa[i].split("-");
				int point = Integer.parseInt(codon[1]);
				this.aa = new Aminoacid(codon[0],point);
				mll.addItem(aa[0], this.aa);
			}
		}
		br.close();
	}
	
	public void playGame() throws Exception {
		snake = new Snake();
		initScreen();
		printSnake();
		flag=true;
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

		while (flag) {
			
			

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
			snake.move();
			printSnake();
			Scoring();
			scoringTable();
			Thread.sleep(200);

		}
	}
		
	public void scoreCodons() {
		SingleLinkedList sll = snake.badTwinSnake();
		score=0;
		String codons ="";
		int scr=0;
		int f =0;
		Node_SLL temp = sll.head;
		while (temp != null) {
			for (int i = 0; i < 3; i++) {
				codons+=temp.getData().getDnapart();
				temp= temp.getLink();
			
				if(temp == null) 
					break;
			}
			cn.getTextWindow().setCursorPosition(65, 2+f);
			if(codons.length()==3) {
				ItemNode ndI = mll.search(codons);
				System.out.println(((Aminoacid)(ndI.getItemName())).getCodon()+"-"+((Aminoacid)ndI.getItemName()).getPoint());
				f++;
				scr+=((Aminoacid)ndI.getItemName()).getPoint();
				codons ="";
			}
			
		}
		score+=scr;
		score+=5*(Snake.linkedsnake.size()-4);
		
	}
	
	
	Game() throws Exception { // --- Contructor
		int count=0;
		
		while(!(flag1==true)) {
			dll = new DoubleLinkedList();
			mll = new MultiLinkedList();
			consoleClear();
			menu.menu();
			time=0;
			score=0;
			level=0;
			cn.setTextAttributes(new TextAttributes(new Color(0, 255, 0)));
			if(count==0) {
				name = login();
			}
			consoleClear();
			createMll();
			playGame(); 
			player= new Player(score,name);
			dll.add(player);
			readFile();
			writeIntoFile();
			menu.whoseIsThisProtein();
			Thread.sleep(2000);
			consoleClear();
			
			writeToScreen();
			menu.menu();
			
			
		/*	cn.getTextWindow().setCursorPosition(0,15);
			System.out.println("-->Your Score : "+name+" "+score);
			
			System.out.printf("*Enter an input to close the HighScore Table*");
			cn.getTextWindow().setCursorPosition(15,4);
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			br.readLine();
			consoleClear();
			String input="";
			while(!(input.equals("play"))||(!(input.equals("exit")))){
				cn.setTextAttributes(new TextAttributes(new Color(0, 255, 0)));
				cn.getTextWindow().setCursorPosition(13,6);
				System.out.printf("*********************************************************");
				cn.getTextWindow().setCursorPosition(13,7);
				System.out.printf("**Please enter 'play' to start again or 'exit' to quit.**");
				cn.getTextWindow().setCursorPosition(13,8);
				System.out.printf("*********************************************************");
				cn.getTextWindow().setCursorPosition(13,9);
				System.out.printf("-->>");
				cn.getTextWindow().setCursorPosition(18,9);
				Scanner scan = new Scanner(System.in);
				input=scan.next();
				if(input.equals("play")) {
					consoleClear();
					flag=true;
					break;
					
				}else if(input.equals("exit"))
					System.exit(0);
				else
					consoleClear();
			}
			*/
				count++;
				
		}
		
		
		
	
	}
}

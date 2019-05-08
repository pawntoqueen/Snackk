
import java.awt.Color;
import java.util.Random;

public class Snake {

	SingleLinkedList linkedsnake;
	int direction;
	
	Random rndm = new Random();

	public void turnLeft() {

	}

	public void turnRight() {

	}

	public void move() {

	}

	public void draw() {

	}

	public void add(Node_data input) {
		linkedsnake.add(input);	

	}
	public void print() {
		Color renk = java.awt.Color.CYAN.brighter();
		String output= linkedsnake.display();	
		System.out.printf(output,renk);
	}
	
	public char randomChar() {
			int randomnum = rndm.nextInt(4) + 1;
			
			char data = ' ';
			switch (randomnum) {
			case 1:
				data = 'A';
				break;
			case 2:
				data = 'G';
				break;
			case 3:
				data = 'C';
				break;
			case 4:
				data = 'T';
				break;

			}
		return data;
	}
	
	Snake() {
		linkedsnake = new SingleLinkedList();
		for (int i = 0; i < 3; i++) {
			char data=randomChar();
			Node_data nd = new Node_data();
			nd.setDnapart(data);
			nd.setX(26+i);
			nd.setY(10+i);
			add(nd);
		}
		
		
		
	}
}

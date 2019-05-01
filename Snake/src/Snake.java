
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

	public void add(Object input) {
		linkedsnake.add(input);	

	}
	public void print() {
		String output= linkedsnake.display();	
		System.out.print(output);

	}
	
	Snake() {
		linkedsnake = new SingleLinkedList();
		
		for (int i = 0; i < 3; i++) {
			int randomnum = rndm.nextInt(4) + 1;
			Node_data nd = new Node_data();
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

			nd.setDnapart(data);
			nd.setX(12);
			nd.setY(15);
			add(nd);
		}
		
	}
}

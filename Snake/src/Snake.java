
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
		Node_SLL temp = linkedsnake.head;
		int x = temp.getData().getX();
		int y = temp.getData().getY();		
		Node_data nd1 = ((Node_data) temp.getData());
		int a = nd1.getX();
		int b= nd1.getY();
		Node_data nd2 = ((Node_data) temp.getLink().getData());
		int c = nd2.getX();
		int d= nd2.getY();
		
		switch (direction) {
		case 0:
			linkedsnake.head.data.setX(linkedsnake.head.data.getX() - 1);
			break;
		case 1:
			linkedsnake.head.data.setX(linkedsnake.head.data.getX() + 1);
			break;
		case 2:
			linkedsnake.head.data.setY(linkedsnake.head.data.getY() - 1);
			break;
		case 3:
			linkedsnake.head.data.setY(linkedsnake.head.data.getY() + 1);
			break;
		}
		
		 
		while(temp != null)
		{
			c = nd2.getX();
			d = nd2.getY();
			
			nd2.setX(x);
			nd2.setY(y);
			x=c; y=d;
			temp = temp.getLink();
			if(temp != null)
				nd2 = ((Node_data) temp.getData());
		}
	}

	

	public void add(Node_data input) {
		linkedsnake.add(input);	

	}
	public void print() {
		Node_SLL temp = linkedsnake.head;
		move();
		while(temp != null)
		{
			
			int x = temp.data.getX();
			int y = temp.data.getY();
			Game.cn.getTextWindow().setCursorPosition(x, y);
			System.out.println(((Node_data) temp.getData()).getDnapart());
			temp = temp.getLink();
		}
		
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
		Node_data nd = new Node_data();
		nd.setDnapart(' ');
		nd.setX(27);
		nd.setY(10);
		add(nd);
		for (int i = 0; i < 11; i++) {
			char data=randomChar();
			nd = new Node_data();
			nd.setDnapart(data);
			nd.setX(26-i);
			nd.setY(10);
			add(nd);
		}
		
		
		
	}
}

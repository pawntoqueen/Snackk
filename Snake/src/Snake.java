
import java.util.Random;

public class Snake {

	SingleLinkedList linkedsnake;
	int direction;
	
	Random rndm = new Random();

	public void move() {
		Node_SLL temp = linkedsnake.head;
		int x = temp.getData().getX();
		int y = temp.getData().getY();		
		Node_data nd1 = ((Node_data) temp.getData());
		int a,b,xx,yy;
		
		xx=linkedsnake.head.data.getX();
		yy=linkedsnake.head.data.getY();
		switch (direction) {
		case 0:
			xx=xx-1;
			break;
		case 1:
			xx=xx+1;
			break;
		case 2:
			yy=yy-1;
			break;
		case 3:
			yy=yy+1;
			break;
		}
		
		if(Game.screen[yy][xx]!=' ') Game.flag=false;
		
		linkedsnake.head.data.setX(xx);
		linkedsnake.head.data.setY(yy);
		temp = temp.getLink();
		
		
		while(temp != null)
		{
			nd1 = ((Node_data) temp.getData());
			a = nd1.getX();
			b = nd1.getY();

			nd1.setX(x);
			nd1.setY(y);
			
			temp = temp.getLink();
	
			x=a; y=b;
		}
	}

	

	public void add(Node_data input) {
		linkedsnake.add(input);	

	}
	public void print() {
		Node_SLL temp = linkedsnake.head;
		
		while(temp != null)
		{
			int x = temp.data.getX();
			int y = temp.data.getY();
			Game.cn.getTextWindow().setCursorPosition(x, y);
			System.out.println(((Node_data) temp.getData()).getDnapart());
			Game.screen[y][x]= temp.data.getDnapart();
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
	
	public SingleLinkedList badTwinSnake() {
		SingleLinkedList sll2 = new SingleLinkedList();
		Node_SLL temp = linkedsnake.head;
		while(temp.getData().getDnapart()!=' ')
		{
			sll2.add(temp.getData());
			temp = temp.getLink();
		}
		return sll2;
	}

	
	Snake() {
		linkedsnake = new SingleLinkedList();
		Node_data nd = new Node_data(0,0,' ');
		add(nd);
		for (int i = 0; i < 3; i++) {
			char data=randomChar();
			nd = new Node_data(26-i,10,data);
			add(nd);
		}
		
		
		
	}
}

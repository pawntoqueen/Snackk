public class DoubleLinkedList {
	private Node_DLL head;	
	private Node_DLL tail;

	public DoubleLinkedList() {
		head = null;
		tail = null;
	}
 
	public void add(Integer dataToAdd) {
		Node_DLL newNode_DLL;
		if (head == null) {  //list is empty
			newNode_DLL = new Node_DLL(dataToAdd); 
			head = newNode_DLL;
			tail = newNode_DLL;	     
		}
		else {   //add to the end
			newNode_DLL = new Node_DLL(dataToAdd); 
			newNode_DLL.setPrev(tail);
			tail.setNext(newNode_DLL);				
			tail=newNode_DLL;			
		}
	}

	public void remove(Integer s)
	{		
		if (head == null)    
			System.out.println("linked list is empty");
		else   
		{
			while (((Integer) head.getData()).equals(s))	{	   
				head = head.getNext();
				head.setPrev(null);
			}
			Node_DLL temp = head;
			while (temp != null)
			{
				if (((Integer)temp.getData()).equals(s)) {
					if (temp.getNext() == null) {
						tail = tail.getPrev();
						tail.setNext(null);
					}
					else {
						temp.getPrev().setNext(temp.getNext());
						temp.getNext().setPrev(temp.getPrev());
					}		    			
				}
				temp=temp.getNext();
			}    	
		}
	}

	public int size()
	{
		int count = 0;
		if (head == null)
			System.out.println("linked list is empty");
		else {
			Node_DLL temp = head;
			while (temp != null)
			{
				count++;
				temp=temp.getNext();
			}
		}
		return count;   
	}

	public void display1()
	{
		if (head == null)    
			System.out.println("linked list is empty");
		else {
			Node_DLL temp = head;
			while (temp != null)
			{
				System.out.print(temp.getData() + " ");
				temp = temp.getNext();
			}
			System.out.println();
		}
	}	
	
	public void display2()
	{
		if (head == null)    
			System.out.println("linked list is empty");
		else {
			Node_DLL temp = tail;
			while (temp != null)
			{
				System.out.print(temp.getData() + " ");
				temp = temp.getPrev();
			}
			System.out.println();
		}
	}

	public boolean search(Integer s)
	{
		boolean flag = false;
		if (head == null)    
			System.out.println("linked list is empty");
		else {
			Node_DLL temp = head;
			while (temp != null)
			{
				if (temp.getData().equals(s)) {
					flag = true;
					break;
				}
				temp = temp.getNext();
			}	    
		}
		return flag;
	}

	public void display()
	{
		if (head == null)    
			System.out.println("linked list is empty");
		else {
			Node_DLL temp1 = head;
			Node_DLL temp2 = tail;
			while (temp1 != temp2 && temp1 != temp2.getNext())
			{
				System.out.print(temp1.getData() + " ");
				System.out.print(temp2.getData() + " ");
				temp1 = temp1.getNext();
				temp2 = temp2.getPrev();
			}
			if(temp1 == temp2) System.out.print(temp1.getData());
			System.out.println();
		}
	}

	public Object getElement(int x)
	{
		boolean flag = false;
		if (head == null)    
		{
			System.out.println("linked list is empty");
			return null;
		}
		else if(x > size())
		{
			System.out.println("index is out of range");
			return null;
		}
		else {
			Node_DLL temp = head;
			int count = 1;
			while (temp != null)
			{
				if (count == x) {
					return temp.getData();
				}
				temp = temp.getNext();
				count++;
			}	    
		}
		return null;
	}
}

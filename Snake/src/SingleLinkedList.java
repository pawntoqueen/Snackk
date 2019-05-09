
public class SingleLinkedList {
		
		Node_SLL head = null;
		
		
		public SingleLinkedList ()
		{
			
		}
		
		public SingleLinkedList (Node_SLL head)
		{
			this.head =head;
		}
		
		public void add (Object input)
		{
			Node_SLL inputNode_SLL = new Node_SLL (input);
			if(head == null)
			{
				head = inputNode_SLL;
			}
			else
			{
				Node_SLL temp = head;
				head= inputNode_SLL;
				head.setLink(temp);
			}
			
		}
		
		public String display ()
		{
			
			String output = "";
			Node_SLL temp = head;
			while(temp != null)
			{
				output += ((Node_data) temp.getData()).getDnapart();
				temp = temp.getLink();
			}
			
			return output;
		}
		
		public int size ()
		{
			int size = 0;
			
			Node_SLL temp = head;
			
			while ( temp != null)
			{
				size++;
				temp = temp.getLink();
			}
			
			return size;
		}
		
		public boolean search (Object input)
		{
			Node_SLL temp = head;
			
			while(temp != null)
			{
				if (temp.getData().equals(input) == true)
				{
					return true;
				}
				else
				{
					temp = temp.getLink();
				}
			}
			return false;
		}
		public Object findMax ()
		{
			
			if(head == null)
			{
				System.out.println("SLL is empty!");
				return null;
			}
			else
			{
				Node_SLL temp = head;
				Object max = temp.getData();
				temp = temp.getLink();
				
				while(temp != null)
				{
					if(((int) temp.getData().getDnapart()) > ((int) max))
					{
						max = temp.getData();
					}
					
					temp = temp.getLink();
					
				}
			
			return max;
			}	
		
		}
		
		
		
		public boolean remove (Object input)
		{
			if(head == null)
			{
				System.out.println("SLL is empty!");
				return false;
			}
			else if (head.getData().equals(input) == true)
			{
				head = head.getLink();
				return true;
			}
			else 
			{
				Node_SLL prev = head;
				Node_SLL temp = prev.getLink();
				
				while(temp != null)
				{
					if(temp.getData().equals(input) == true)
					{
						prev.setLink(temp.getLink());
						return true;
					}
					
					prev = temp;
					temp = temp.getLink();
					
				}
				
				return false;
			}
			
		}
	
	}



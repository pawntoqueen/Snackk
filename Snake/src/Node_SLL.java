public class Node_SLL {
		   Node_data data;
		   Node_SLL link; 

		   public Node_SLL(Object dataToAdd) {
			     data = (Node_data)dataToAdd;
			     link = null;
		   }
		   
		   public Node_data getData() { return data; }
		   public void setData(Node_data data) { this.data = data;  }

		   public Node_SLL getLink() { return link;  }
		   public void setLink(Node_SLL link) { this.link = link;   }   
		}

		


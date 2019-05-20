public class MultiLinkedList {
	CategoryNode head;

	public void addCategory(String dataToAdd) {
		CategoryNode temp;
		if (head == null) {
			temp = new CategoryNode(dataToAdd);
			head = temp;
		} else {
			temp = head;
			while (temp.getDown() != null)
				temp = temp.getDown();
			CategoryNode newnode = new CategoryNode(dataToAdd);
			temp.setDown(newnode);
		}
	}

	public void addItem(String Category, Object Item) {
		if (head == null)
			System.out.println("Add a Category before Item");
		else {
			CategoryNode temp = head;
			while (temp != null) {
				if (Category.equals(temp.getCategoryName())) {
					ItemNode temp2 = temp.getRight();
					if (temp2 == null) {
						temp2 = new ItemNode(Item);
						temp.setRight(temp2);
					} else {
						while (temp2.getNext() != null)
							temp2 = temp2.getNext();
						ItemNode newnode = new ItemNode(Item);

						temp2.setNext(newnode);
					}
				}
				temp = temp.getDown();
			}
		}
	}

	public int sizeCategory() {
		int count = 0;
		if (head == null)
			System.out.println("linked list is empty");
		else {
			CategoryNode temp = head;
			while (temp != null) {
				count++;
				temp = temp.getDown();
			}
		}
		return count;
	}

	public int sizeItem() {
		int count = 0;
		if (head == null)
			System.out.println("linked list is empty");
		else {
			CategoryNode temp = head;
			while (temp != null) {
				ItemNode temp2 = temp.getRight();
				while (temp2 != null) {
					count++;
					temp2 = temp2.getNext();
				}
				temp = temp.getDown();
			}
		}
		return count;
	}

	public void display() {
		if (head == null)
			System.out.println("linked list is empty");
		else {
			CategoryNode temp = head;
			while (temp != null) {
				System.out.print(temp.getCategoryName());
				ItemNode temp2 = temp.getRight();
				while (temp2 != null) {
					System.out.print(((Aminoacid)temp2.getItemName()).getCodon()+((Aminoacid)temp2.getItemName()).getPoint());
					temp2 = temp2.getNext();
				}
				temp = temp.getDown();
				System.out.println();
			}
		}
	}
}
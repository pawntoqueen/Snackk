public class ItemNode {
	private Object ItemName;
	private ItemNode next;

	public ItemNode(Object item) {
		ItemName = item;
		next = null;
	}

	public Object getItemName() {
		return ItemName;
	}

	public void setItemName(String data) {
		this.ItemName = data;
	}

	public ItemNode getNext() {
		return next;
	}

	public void setNext(ItemNode next) {
		this.next = next;
	}
}
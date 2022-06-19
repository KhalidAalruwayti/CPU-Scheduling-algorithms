    public class ListPCB{
    
	private NodePCB head ;
	private NodePCB current;
	public int count;
	
	public ListPCB() {
		head = current = null;
		count=0;
	}
	
	
	public boolean empty() {
		
		return head == null;
	}

	
	public boolean full() {
		
		return false;
	}

	
	public void findFirst() {
		current = head;
		}

	
	public void findNext() {
		current= current.next;

	}

	
	public boolean last() {
		
		return current.next==null;
	}

	
	public PCB retrieve() throws NullPointerException{
		
		return current.data;
	}

	
	public void update(PCB e) {
		current.data=e;

	}

	
	public void insert(PCB e) {
		try {
      NodePCB temp;
      if(empty()) {
    	  current = head = new NodePCB (e);
    	  count++;
      }
      else {
    	  temp=current.next;
    	  current.next=new NodePCB(e);
    	  current = current.next;
    	  current.next=temp;
    	  count++;
    	  
      }
		}catch(Exception s) {
			s.printStackTrace();
		}
	}

	
	public void remove() {
		try {
		if(current==head) {
			head = head.next;
			count--;
		}
		else {
			NodePCB temp=head;
			while(temp.next!=current) {
				temp=temp.next;
			}
			temp.next=current.next;
			count--;
			
		}
		if(current.next==null) {
			current=head;
		}
		else
			current=current.next;
	}catch(Exception s) {
		s.printStackTrace();
	}
	}
	public String toString() {
		return "ListPCB [head=" + head + ", current=" + current + "]";
	}
}
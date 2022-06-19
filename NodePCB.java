
public class NodePCB {
public PCB data;
public NodePCB next;

public NodePCB() {
	data = null;
	next = null;
}
public NodePCB(PCB data) {
	this.data=data;
	next=null;
}
}

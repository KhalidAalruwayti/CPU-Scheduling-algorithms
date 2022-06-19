
public class PCB {
public String PID;
public int Burst;
public int burstOG;
public int Priorty;
public PCB(String pID, int burst) {
	PID = pID;
	Burst = burst;
	burstOG=burst;
	Priorty =-1;
}
public PCB(String pID, int burst, int priorty) {
	PID = pID;
	Burst = burst;
	burstOG=burst;
	Priorty = priorty;
}


}

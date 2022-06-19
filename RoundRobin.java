
public class RoundRobin {
	public static void RR(ListPCB P,int Q) {
		int time=0;
		int totalBurst=0;
		int waitCount=0;
		double totalWait=0;
		double avgWait=0;
		Waiting [] wait=new Waiting[P.count];
		int completeCount=0;
		double totalComplete=0;
		double avgComplete=0;
		Completion [] complete = new Completion[P.count];
		 
	    
		int c=P.count;
		
		P.findFirst();
	while(P.count!=0) {
		
		if(P.last()!=true) {
		for(int k=0;k<Q;k++) {
			if(P.retrieve().Burst==0) {
				
				if (k!=0) {
					wait[waitCount++]=new Waiting(P.retrieve().PID,time-P.retrieve().burstOG);
					complete[completeCount++]=new Completion(P.retrieve().PID,time);
				}
				//System.out.print("PID:"+P.retrieve().PID);
				//System.out.print(" Burst: "+P.retrieve().Burst);
				//System.out.print(" Quantum: "+(k+1));
				//System.out.println(" Time: "+time);
				
				//time++;
				System.out.println("-------------------------------");
				P.remove();
				break;
			}
			else {
				
				System.out.print("PID:"+P.retrieve().PID);
			System.out.print(" Burst: "+P.retrieve().Burst);
			System.out.print(" Quantum: "+(k+1));
			System.out.println(" Time: "+time);
			P.retrieve().Burst--;
			time++;
			
			if(k==Q-1){
				if(P.retrieve().Burst==0) {
					wait[waitCount++]=new Waiting(P.retrieve().PID,time-P.retrieve().burstOG);
					complete[completeCount++]=new Completion(P.retrieve().PID,time);
				}
				System.out.println("-------------------------------");
				P.findNext();
			}
			}
		}
		}
		else {
			
			
			if(P.count==0) 
				break;
			for(int k=0;k<Q;k++) {
				if(P.retrieve().Burst==0) {
				
					if (k!=0) {
						wait[waitCount++]=new Waiting(P.retrieve().PID,time-P.retrieve().burstOG);
						complete[completeCount++]=new Completion(P.retrieve().PID,time);
					}
					//System.out.print("PID:"+P.retrieve().PID);
					//System.out.print(" Burst: "+P.retrieve().Burst);
					//System.out.print(" Quantum: "+(k+1));
					//System.out.println(" Time: "+time);
					
					//time++;
					System.out.println("-------------------------------");
					P.remove();
					break;
				}
				else {
				
				System.out.print("PID:"+P.retrieve().PID);
				System.out.print(" Burst: "+P.retrieve().Burst);
				System.out.print(" Quantum: "+(k+1));
				System.out.println(" Time: "+time);
				P.retrieve().Burst--;
				time++;
				
				if(k==Q-1) {
					if(P.retrieve().Burst==0) {
						wait[waitCount++]=new Waiting(P.retrieve().PID,time-P.retrieve().burstOG);
						complete[completeCount++]=new Completion(P.retrieve().PID,time);
					}
					System.out.println("-------------------------------");
					P.findFirst();
				}
				}
			}
		}
	}
	

	for(int i=0;i<wait.length;i++) {
totalWait+=wait[i].waiting;
}
System.out.println("Waiting time for each process: ");
for(int i=0; i<wait.length;i++) {
System.out.println("PID: "+wait[i].PID+" Waiting time: "+wait[i].waiting);
}
avgWait=totalWait/c;
System.out.println("Average waiting time: "+avgWait);
System.out.println("-----------------------------------");
for(int i =0;i<complete.length;i++) {
totalComplete+=complete[i].Completion;
}
System.out.println("Completion time for each process: ");
for(int i=0;i<complete.length;i++) {
System.out.println("PID: "+complete[i].PID+" Completion time: "+complete[i].Completion);

}
avgComplete=totalComplete/c;
System.out.println("Average completion time : "+avgComplete);
}

}

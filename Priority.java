
public class Priority {
	public static void Priortiy(ListPCB P) {
		int time=0;
		int waitCount=0;
		double totalWait=0;
		double avgWait=0;
		Waiting [] wait=new Waiting[P.count];
		int completeCount=0;
		double totalComplete=0;
		double avgComplete=0;
		Completion [] complete = new Completion[P.count];
		 
	    P.findFirst();
		int c=P.count;
		for (int i =0;i<c;i++) {
			if(P.count==0) {
				break;
			}
			P.findFirst();
			
			int minPriority = P.retrieve().Priorty;
			
			String minPID = P.retrieve().PID;
			
			
			while(!P.last()) {
				if(P.retrieve().Priorty<minPriority) {
					minPriority = P.retrieve().Priorty;
					minPID=P.retrieve().PID;
				}
				P.findNext();
			}
			if(P.retrieve().Priorty<minPriority) {
				minPriority = P.retrieve().Priorty;
				minPID=P.retrieve().PID;
			}
				
			P.findFirst();
			while(!P.last()) {
				if(minPID.equalsIgnoreCase(P.retrieve().PID))
					break;
				P.findNext();
			}
			while(true) {
				if(P.retrieve().Burst==0) {
					
					//System.out.print("PID:"+P.retrieve().PID);
				//	System.out.print(" Burst: "+P.retrieve().Burst);
					//System.out.print(" Priority : "+P.retrieve().Priorty);
					//System.out.println(" Time: "+time);
                   					

					wait[waitCount++]=new Waiting(P.retrieve().PID,time-P.retrieve().burstOG);
					complete[completeCount++]=new Completion(P.retrieve().PID,time);
				//	time++;
					P.remove();
					System.out.println("-------------------------------");
					break;
				}
				System.out.print("PID:"+P.retrieve().PID);
				System.out.print(" Burst: "+P.retrieve().Burst);
				System.out.print(" Priority : "+P.retrieve().Priorty);
				System.out.println(" Time: "+time);
				P.retrieve().Burst--;
				time++;
				
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

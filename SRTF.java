import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// Shortest Remaining Time First (SRTF)
public class SRTF
{
	// Method to find the waiting time for all
	// processes
	static void ShowAll(process proc[], int n) {
		int wt[] = new int[n], tat[] = new int[n];
		int total_wt = 0, total_tat = 0;

		findTurnAroundTime(proc, n, wt, tat);
		for (int q = 0; q < n; q++) {
			total_tat = total_tat + tat[q];
		}
		process[] rt = new process[n];
		
		// Copy the burst time into rt[]
		for (int i = 0; i < n; i++)
			rt[i] = proc[i];
	    
		int minart = proc[0].art;
		int minarti = 0;

	    for(int f=1;f<n;f++){ 
	      if(proc[f].art < minart){ 
	    	  minart = proc[f].art; 
	    	  minarti=f;
	      } 
	    }
		int time=minart;
		int minbt=1000;
		int minpid=0;
		int minj=0;

		for(int i=minart; i<=total_tat+minart; i++) {
			for(int j=0; j<n; j++) {
				if(proc[j].bt <= 0)
					continue;
				if(proc[j].art > time)
					continue;
				if(proc[j].bt==minbt && Integer.parseInt(proc[j].pid) > minpid)
					continue;
				if(proc[j].bt<=minbt) {
					minbt=proc[j].bt;
					minj=j;
					}
						
				
			}
				
			if(proc[minj].bt <= 0)
				continue;

			System.out.print("PID:"+proc[minj].pid);
			System.out.print(" Burst: "+proc[minj].bt);
			System.out.println(" Time: "+time);
			proc[minj].bt=proc[minj].bt-1;
			time++;
			
			if(proc[minj].bt==0) {

			for (int x = 0, z = 0; x < rt.length; x++) {
			    if (x != minj) {
			        proc[z++] = rt[x];
			    }
			}
			rt = new process[n];
			for (int h = 0; h < n; h++)
				rt[h] = proc[h];
			}
			for(int f=1;f<n;f++){ 
			      if(proc[f].art < minart){ 
			    	  minart = proc[f].art; 
			    	  minarti=f;
			      } 
			    }
			minbt=10000;
			minj=0;

		}
	}


	static void findWaitingTime(process proc[], int n,
									int wt[])
	{
		int rt[] = new int[n];
	
		// Copy the burst time into rt[]
		for (int i = 0; i < n; i++)
			rt[i] = proc[i].bt;
	
		int complete = 0, t = 0, minm = Integer.MAX_VALUE;
		int shortest = 0, finish_time;
		boolean check = false;
		int time=0;

		// Process until all processes gets
		// completed
		while (complete != n) {
	
			// Find process with minimum
			// remaining time among the
			// processes that arrives till the
			// current time`
			for (int j = 0; j < n; j++)
			{
				if ((proc[j].art <= t) &&
				(rt[j] < minm) && rt[j] > 0) {
					minm = rt[j];
					shortest = j;
					check = true;	
					}
			}
	
			if (check == false) {
				t++;
				continue;
			}
	
			// Reduce remaining time by one
			rt[shortest]--;
	
			// Update minimum
			minm = rt[shortest];
			if (minm == 0)
				minm = Integer.MAX_VALUE;
	
			// If a process gets completely
			// executed
			if (rt[shortest] == 0) {
	
				// Increment complete
				complete++;
				check = false;
	
				// Find finish time of current
				// process
				finish_time = t + 1;
	
				// Calculate waiting time
				wt[shortest] = finish_time -
							proc[shortest].bt -
							proc[shortest].art;
	
				if (wt[shortest] < 0)
					wt[shortest] = 0;
			}
			// Increment time
			t++;
		}
	}
	
	// Method to calculate turn around time
	static void findTurnAroundTime(process proc[], int n,
							int wt[], int tat[])
	{
		// calculating turnaround time by adding
		// bt[i] + wt[i]
		for (int i = 0; i < n; i++)
			tat[i] = proc[i].bt + wt[i];
	}
	
	// Method to calculate average time
	static void findavgTime(process proc[], int n)
	{			
		
		int wt[] = new int[n], tat[] = new int[n];
		int total_wt = 0, total_tat = 0;
	
		// Function to find waiting time of all
		// processes
		findWaitingTime(proc, n, wt);
	
		// Function to find turn around time for
		// all processes
		findTurnAroundTime(proc, n, wt, tat);
	
		// Display processes along with all
		// details
		System.out.println("Processes " +
						" Burst time " +
						"       Waiting time " +
						"       Turn around time");
	
		// Calculate total waiting time and
		// total turnaround time
		for (int i = 0; i < n; i++) {
			total_wt = total_wt + wt[i];
			total_tat = total_tat + tat[i];
			System.out.println(" " + proc[i].pid + "\t\t"
							+ proc[i].bt + "\t\t " + wt[i]
							+ "\t\t\t" + tat[i]);
		}
	
		System.out.println("Average waiting time = " +
						(float)total_wt / (float)n);
		System.out.println("Average turn around time = " +
						(float)total_tat / (float)n);
	}
	
	// Driver Method
	public static void main(String[] args) throws FileNotFoundException
	{
//		process proc[] = { new process("1", 6, 4),
//                   new process("2", 8, 2),
//                   new process("3", 7, 0),
//                   new process("4", 3, 3)};
//
//		   findavgTime(proc, proc.length);
		Scanner s = new Scanner(new File("/Users/khaldalrwyt/eclipse-workspace/CSC-227/bin/testdata1forSRTF.txt"));
		ArrayList<String> list = new ArrayList<String>();
		process[] proc= new process[30];
		int i=0;
		while (s.hasNext()){
			String PID = s.next();
            String[] PIDList = PID.split(",");
			String ID = PIDList[0];  
			int art = Integer.parseInt(PIDList[1]);
			int bt = Integer.parseInt(PIDList[2]); 
		    proc[i]= new process(ID,bt,art);
		    i++;

		}
		s.close();
		if(i!=0) {
		SRTF.findavgTime(proc, i);
		SRTF.ShowAll(proc, i);
		}
//		process proc[] = {new process("1", 6, 60)};
//		if(proc.length!=0) {
//		findavgTime(proc, proc.length);
//		ShowAll(proc, proc.length);
//		}
//      try{
//		Scanner s = new Scanner(new File("/Users/khaldalrwyt/eclipse-workspace/CSC-227/bin/testdata1.txt"));
//		ArrayList<String> list = new ArrayList<String>();
//		while (s.hasNext()){
//		    list.add(s.next());  
//		}
//		s.close();
//		process[] proc=new process[30];
//		int j=0;
//		for(int i=0; i<=(list.size()/2)-1; i++) {
//		    proc[i]= new process(list.get(j),Integer.parseInt(list.get(j+1)));
//		    j=j+2;
//		}
//		if(proc.length!=0) {
//		findavgTime(proc, list.size()/2);
//		ShowAll(proc, list.size()/2);
//	    } 
//      }
//		catch{
//			System.out.println("erorr in in the file");
//		}
	}

}

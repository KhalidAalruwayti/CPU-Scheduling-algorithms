import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class main {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		Scanner in=new Scanner(System.in).useLocale(Locale.US);
		
		int x=0;
		do {
			System.out.println("************************************");
			System.out.println("* 1- Round Robin                   *");
			System.out.println("* 2- Shortest Remaining Time First *");
			System.out.println("* 3- Priorty                       *");
			System.out.println("* 4- Exit                          *");
			System.out.println("************************************");
			System.out.print("Choose what scheduling algorithm do you want:");
			try{
				x=in.nextInt();
			}catch(InputMismatchException e) {
				e.printStackTrace();
				break;
			}catch(Exception e1) {
				e1.printStackTrace();
				break;
			}
			switch(x) {
			case 1:
				
				
				try {
				System.out.print("Enter quantum size:");
				int Quant=in.nextInt();
				
				System.out.print("Enter the file name and the directory: ");
				String fileName=in.next();
				
				Scanner s = new Scanner(new File(fileName));
				
				ListPCB RRProcesses = new ListPCB();
				while(s.hasNext()) {
					RRProcesses.insert(new PCB(s.next(),Integer.parseInt(s.next())));
				}
				if(RRProcesses.count==0) {
				System.out.println("The file is empty!!");
				break;
				}
				RoundRobin.RR(RRProcesses,Quant);
				}catch(FileNotFoundException e) {
					System.out.println("Error in the file name or the directory !!!");
				}catch(IOException s) {
					System.out.println("Error!!!");
				}catch(NullPointerException i) {
					i.printStackTrace();
				}catch(Exception o) {
					o.printStackTrace();
				}
				break;

            case 2:
            	 try{
     		    	System.out.print("Enter the file name and the directory: ");
     				String fileName=in.next();
     		  		Scanner s = new Scanner(new File(fileName));
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
     		      }
     		      catch(FileNotFoundException e) {
     					System.out.println("Error in the file name or the directory !!!");
     				}catch(IOException s) {
     					System.out.println("Error!!!");
     				}catch(NullPointerException i) {
     					System.out.println("Null pointer Excaption");
     				}catch(Exception o) {
     					o.printStackTrace();
     				}
     				break;
			case 3:
				try {
					
					System.out.print("Enter the file name and the directory: ");
					String fileName=in.next();
					Scanner s = new Scanner(new File(fileName));
					ListPCB PriortiyProcesses = new ListPCB();
					while(s.hasNext()) {
						String PID = s.next();
						String BP = s.next();
                        String[] BPList = BP.split(",");
						int Burst = Integer.parseInt(BPList[0]);
						int Priority = Integer.parseInt(BPList[1]);
						
						
						
			            
						PriortiyProcesses.insert(new PCB(PID,Burst,Priority));
					}
					if(PriortiyProcesses.count==0) {
					System.out.println("The file is empty!!");
					break;
					}
					Priority.Priortiy(PriortiyProcesses);
					}catch(FileNotFoundException e) {
						System.out.println("Error in the file name or the directory !!!");
					}catch(IOException s) {
						System.out.println("Error!!!");
					}catch(NullPointerException i) {
						System.out.println("Null pointer Excaption");
					}catch(IllegalArgumentException k) {
						k.printStackTrace();
					}catch(SecurityException w) {
						w.printStackTrace();
					}catch(Exception o) {
						o.printStackTrace();
					}
				break;
			case 4:
			 break;
			default:
				System.out.println("Invalid entry!!!");
			}
			
			
		}while(x!=4);

	    
	} 
	
	


	
}

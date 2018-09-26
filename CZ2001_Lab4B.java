

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

	public class CZ2001_Lab4B{	
	    public static int GRAPH_SIZE = 100;
	    public static int numberofconnections;
		static List<Integer>[] graph = new LinkedList[GRAPH_SIZE];

		public static String[] a = new String[GRAPH_SIZE];
		public static int[][] carray = new int[GRAPH_SIZE][GRAPH_SIZE];
		
		public static float stopcount = 0;
		public static float oneormorecount = 0;
		
		public static void changeGraphSize(int x){
			GRAPH_SIZE = x;
		}
		
		public static void changeNumberOfConnections(int x) {
			int totalno = ((GRAPH_SIZE)*(GRAPH_SIZE-1))/2;
			
			switch(x) {
			case 1: numberofconnections = (totalno/100); break; //1%
			case 25: numberofconnections = (totalno/4); break; //25%
			case 50: numberofconnections = (totalno/2); break; //50%
			case 75: numberofconnections = ((totalno/4)*3); break; //75%
			case 100: numberofconnections = (totalno/1); break; //100%
			default: numberofconnections = (totalno/2); break; //50%
			}
			
		}
		

		public static void countriesCreated() throws IOException{
			for(int i=0;i<GRAPH_SIZE;i++){
				graph[i] = new LinkedList();
				}
			
			try {
				BufferedReader readl = new BufferedReader(new FileReader("countries.txt"));
				for (int j = 0; j < GRAPH_SIZE; j++) {
					String inputLine = readl.readLine();
					a[j] = inputLine; 
				}
				readl.close();
			} catch (FileNotFoundException e) {}
		}
		
		public static class Node{
			int ID;
			String name;
			public boolean inQueue = false;
			public boolean visited=false;
			
			Node(int ID,String name){
				this.ID = ID;
				this.name = name;
				}
			}
		
		public static boolean checkConnected(int[][] carray) {
			for (int m = 0; m < GRAPH_SIZE; m++) {
				int ecount = 0;
				for (int n = 0; n < GRAPH_SIZE; n++) {
					if (carray[m][n] == 1) {
						ecount++;
					}
				}
				if (ecount == 0) {
					return true;
				}
			}
			return false;
		}
		
		
		public static void generateNodes() throws IOException{
			countriesCreated();
			System.out.println();
			System.out.println("Generated nodes.");
			System.out.println("Connecting nodes.");
			

			
			for (int m = 0; m < GRAPH_SIZE; m++) {
				for (int n = 0; n < GRAPH_SIZE; n++) {
					carray[m][n] = 0;
				}
			}
			
			Random rn = new Random();
			boolean x = true;
			
			do {
				int count = 0;
				while (count < numberofconnections) {
					int i = rn.nextInt(GRAPH_SIZE);
					int j = rn.nextInt(GRAPH_SIZE);
					if (i == j) { continue; }
					else if ((carray[i][j] != 1)) {
						carray[i][j] = 1;
						carray[j][i] = 1;
						i = rn.nextInt(GRAPH_SIZE);
						j = rn.nextInt(GRAPH_SIZE);
						count++;
					}
				}
				x = checkConnected(carray);
			} while(x);
			
			
			for (int a = 0; a < GRAPH_SIZE; a++) {
				for (int b = a+1; b < GRAPH_SIZE; b++) {
					if (carray[a][b] == 1) {
						graph[a].add(b);
						graph[b].add(a);
					}
				}
			}

			int noofedges = 0;
			for (int a = 0; a < GRAPH_SIZE; a++) {
				for (int b = a+1; b < GRAPH_SIZE; b++) {
					if (carray[a][b] == 1) {
						noofedges++;
					}
				}
			}
			System.out.println("\nTotal number of edges: " + noofedges + "\n");
			
			System.out.println("Finished generating graph.\n");
		}
	
	
		
		public static int findCountryID(String find){
			for(int i=0;i<a.length;i++){
				if((a[i].toLowerCase()).equals(find.toLowerCase())){
					return i;
					}
				}
			System.out.println("Invalid input. Please try again.");
			return -1;
			}
		
		public static void printgraph(){
			int j =0;
			System.out.println("Print graph:");
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			for(int i = 0 ; i< GRAPH_SIZE; i++){
				System.out.print(a[i] + " -> ");
				for( j = 0; j < graph[i].size()-1;j++){
					System.out.print(a[graph[i].get(j)] + ", ");
					}
				System.out.println(a[graph[i].get(j)]);
				System.out.println("");
				}
			}
		
		public static long countTimeTaken() {
			long startTime, estimatedTime = 0, totalTime = 0;
			for (int m = 0; m < GRAPH_SIZE; m++) {
				for (int n = 0; n < GRAPH_SIZE; n++) {
					startTime = System.nanoTime();
					bfs(a[m], a[n]);
					estimatedTime = System.nanoTime() - startTime;
					totalTime += estimatedTime;
				}
			}
			
			return (totalTime/(GRAPH_SIZE));
		}
		
		public static void bfs(String s, String e){
			int startCountryID = findCountryID(s),
					endCountryID = findCountryID(e);
			int currentID;
			int oneormore = 0;
			
			ArrayList <Node> nodeList = new ArrayList<Node>();
			Integer [] previousID = new Integer[GRAPH_SIZE];
			Queue<Integer> queue = new LinkedList();
			
			for(int i = 0;i <GRAPH_SIZE ;i ++){
				nodeList.add(new Node(i,a[i]));
			}
			nodeList.get(startCountryID).inQueue = true;
			queue.add(startCountryID);
			while(!queue.isEmpty()){
				nodeList.get(queue.peek()).visited= true;	//set next queue node as visited
				currentID = queue.poll();
				
				//for every neighbour at current node
				for(int j = 0; j< graph[currentID].size();j++){
					int neighbourID = graph[currentID].get(j);
					
					if(!nodeList.get(neighbourID).inQueue) {		//if neighbour not in queue
						nodeList.get(neighbourID).inQueue = true;
						queue.add(neighbourID);						//add neighbour to queue
					    previousID[neighbourID] = currentID;
					}
					//check endpoint
					if(neighbourID  == endCountryID ){
						LinkedList<String> route = new LinkedList<String>();
						int temp = endCountryID;
						route.addFirst(nodeList.get(temp).name);	
						
						//add path to route, backwards
						while(previousID[temp] != null){
							route.addFirst(nodeList.get(previousID[temp]).name);
							temp = previousID[temp];
						}
						
						//print route
						while(route.size()!=0){	
							//route.poll();
							System.out.print(route.poll());
							if(route.size()!=0)	{ 
								System.out.print("->");
								stopcount++;
								oneormore++;
								 }
							else {
								 System.out.println();
							}
						} 
						
						if (1 <= oneormore) {
							oneormorecount++;
						}
			
						queue.clear();
						break;
							
						

					}
				}
			}
		}
		
		public static void main(String[] args) throws IOException{
			long startTime, estimatedTime;
			String startCountryID, endCountryID;
			Scanner sc = new Scanner(System.in);
		
			System.out.println("Input number of vertices:");
			int nov = sc.nextInt();
			changeGraphSize(nov);

			System.out.println("Input % of connections between vertices (25%, 50%, 75%, 100%):"); 
			int con = sc.nextInt();
			changeNumberOfConnections(con);
			
			generateNodes();
			printgraph();
			
			System.out.println();
			System.out.println("Average time taken: " + countTimeTaken());

			
			float avgcount = (stopcount/oneormorecount);
			System.out.println("Total number of stops: " + stopcount);
			
			System.out.print("Average number of stops: ");
			System.out.printf("%.5f", avgcount);
			System.out.println();
			System.out.println(oneormorecount); 
			
			/* boolean cont = true;
			
			 do {

					System.out.println("============================================");
					System.out.println("Enter Starting country(case insensitive): ");
					System.out.println("============================================");
					while (true) {
						sc.nextLine();
						startCountryID = sc.nextLine();
						if (findCountryID(startCountryID)!= -1) break;
					}
					
					System.out.println("============================================");
					System.out.println("Enter Destination country(case insensitive): ");
					System.out.println("============================================");
					while (true) {
						endCountryID = sc.nextLine();
						if (findCountryID(endCountryID)!= -1) break;
					}
					startTime = System.nanoTime();
					System.out.println();
					bfs(startCountryID,endCountryID);
					estimatedTime = System.nanoTime() - startTime;
					
					System.out.println("CPU running time: " + estimatedTime + "ns");
					
					System.out.println("\nPress 0 to exit or Press <Key> to continue");
					
					String input = sc.next();
					if(input.equals("0")) {
						cont = false;
					}
			} while(cont); */
		}
	}

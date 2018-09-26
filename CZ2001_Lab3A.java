import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CZ2001_Lab3A {

	public static class arraygen {
		
		private static int arraysize;
		private static int arrayAscending[];
		private static int arrayDescending[];
		private static int arrayRandom[];
		private static int aux[];
		
		private static long keycmp = 0;
		
		arraygen(int arsize) { //constructor
			
			int i;
			
			arraysize = arsize;
			
			aux = new int[arsize];						//aux array for merge
			
			arrayAscending = new int[arsize];
			arrayDescending = new int[arsize];
			arrayRandom = new int[arsize];

			ArrayList numbers = new ArrayList();		//generate an array of n = size (assuming each n is unique, no repeated integer n)
			for (i = 0; i < arsize; i++) {
				numbers.add(i+1);
			}
			
			Collections.shuffle(numbers);				//shuffle the numbers within the array, to make it randomized
			
			for (i = 0; i < arsize; i++) {
				arrayRandom[i] = (int) numbers.get(i);
			}
			
			for (i = 0; i < arsize; i++) {
				arrayAscending[i] = i + 1;
			}
			
			for (i = 0; i < arsize; i++) {
				arrayDescending[i] = arsize - i;
			}

		}
		
		
		public static void insertionsort(int ar[], int arsize) {
			
			int temp;
			
			for (int i = 1; i < arsize; i++) {
				for (int j = i; j > 0; j--) {
					keycmp++;
					if (ar[j] < ar[j-1]) {
						temp = ar[j];
						ar[j] = ar[j-1];
						ar[j-1] = temp;
					}
					else
						break;
				}
			}
		}
		
		public static void mergesort(int first, int last, int ar[]) {
			
			int mid = (first+last)/2;
			if (last-first <= 0) return;
			else if (last-first > 1) {
				mergesort(first, mid, ar);
				mergesort(mid+1, last, ar);
			}
			
			merge(first, last, ar);
		}
		
		public static void merge(int first, int last, int ar[]) {
				
			for (int i = first; i <= last; i++) {
				aux[i] = ar[i];
			}
			
			int mid = (last+first)/2;
			int a = first;
			int b = mid+1;
			int k = first;
			
			while (a <= mid && b <= last) {
				if (aux[a] <= aux[b]) {
					ar[k] = aux[a];			// using auxiliary array
					a++;
					keycmp++;
				}
				else {
					ar[k] = aux[b];
					b++;
					keycmp++;
				}
				k++;
			}
			
			while (a <= mid) {
				ar[k] = aux[a];
				k++;
				a++;
			}
			
		}

		public static void testrandom() {
			
			int arraystate[] = new int[arraysize];
			System.arraycopy(arrayRandom, 0, arraystate, 0, arrayRandom.length);
			
			System.out.print("\n");
			System.out.println("---------------------------------------------------------------------------------------");
			System.out.println("For array in randomized order: ");
			// System.out.println("Array in randomized order: ");				//print out randomized array
			// System.out.println(Arrays.toString(arrayRandom));
			
			long startTime = System.nanoTime();								//calc elapse time for insertion
			insertionsort(arraystate, arraysize);
			long estimatedTime = System.nanoTime() - startTime;
			float estimate = ((float) estimatedTime)/1000000;
			
			System.out.print("\n");
			// System.out.println("> After insertion sort: ");					//elapse time for insertion
			// System.out.println(Arrays.toString(arraystate) + "\n");
			System.out.println("> Time taken for insertion sort: ");
			System.out.println(estimate + " ms \n");
			
			System.out.println("> Number of key comparisons (for insertion): \n" + keycmp);
			keycmp = 0;

			startTime = System.nanoTime();									//calc elapse time for merge
			mergesort(0, arraysize-1, arrayRandom);
			estimatedTime = System.nanoTime() - startTime;
			estimate = ((float) estimatedTime)/1000000;
			
			System.out.print("\n");
			// System.out.println("> After merge sort: ");						//elapse time for merge
			// System.out.println(Arrays.toString(arrayRandom) + "\n");
			System.out.println("> Time taken for merge sort: ");
			System.out.println(estimate + " ms \n");
			
			System.out.println("> Number of key comparisons (for merge): \n" + keycmp);
			keycmp = 0;
			
		}
		
		public static void testascending() {
			
			int arraystate[] = new int[arraysize];
			System.arraycopy(arrayAscending, 0, arraystate, 0, arrayAscending.length);
			
			System.out.print("\n");
			System.out.println("---------------------------------------------------------------------------------------");
			System.out.println("For array in ascending order: ");
			// System.out.println("Array in ascending order: ");				//print out ascending array
			// System.out.println(Arrays.toString(arrayAscending));
			
			long startTime = System.nanoTime();								//calc elapse time for insertion
			insertionsort(arraystate, arraysize);
			long estimatedTime = System.nanoTime() - startTime;
			float estimate = ((float) estimatedTime)/1000000;
			
			System.out.print("\n");
			// System.out.println("> After insertion sort: ");					//elapse time for insertion
			// System.out.println(Arrays.toString(arraystate) + "\n");
			System.out.println("> Time taken for insertion sort: ");
			System.out.println(estimate + " ms \n");

			System.out.println("> Number of key comparisons (for insertion): \n" + keycmp);
			keycmp = 0;
			
			startTime = System.nanoTime();									//calc elapse time for merge
			mergesort(0, arraysize-1, arrayAscending);
			estimatedTime = System.nanoTime() - startTime;
			estimate = ((float) estimatedTime)/1000000;
			
			System.out.print("\n");
			// System.out.println("> After merge sort: ");						//elapse time for merge
			// System.out.println(Arrays.toString(arrayAscending) + "\n");
			System.out.println("> Time taken for merge sort: ");
			System.out.println(estimate + " ms \n");
			
			System.out.println("> Number of key comparisons (for merge): \n" + keycmp);
			keycmp = 0;
			
		}
		
		public static void testdescending() {
			
			int arraystate[] = new int[arraysize];
			System.arraycopy(arrayDescending, 0, arraystate, 0, arrayDescending.length);
			
			System.out.print("\n");
			System.out.println("---------------------------------------------------------------------------------------");
			System.out.println("For array in descending order: ");
			// System.out.println("Array in descending order: ");				//print out descending array
			// System.out.println(Arrays.toString(arrayDescending));
			
			long startTime = System.nanoTime();								//calc elapse time for insertion
			insertionsort(arraystate, arraysize);
			long estimatedTime = System.nanoTime() - startTime;
			float estimate = ((float) estimatedTime)/1000000;
			
			System.out.print("\n");
			// System.out.println("> After insertion sort: ");					//elapse time for insertion
			// System.out.println(Arrays.toString(arraystate) + "\n");
			System.out.println("> Time taken for insertion sort: ");
			System.out.println(estimate + " ms \n");

			System.out.println("> Number of key comparisons (for insertion): \n" + keycmp);
			keycmp = 0;
			
			startTime = System.nanoTime();									//calc elapse time for merge
			mergesort(0, arraysize-1, arrayDescending);
			estimatedTime = System.nanoTime() - startTime;
			estimate = ((float) estimatedTime)/1000000;
			
			System.out.print("\n");
			// System.out.println("> After merge sort: ");						//elapse time for merge
			// System.out.println(Arrays.toString(arrayDescending) + "\n");
			System.out.println("> Time taken for merge sort: ");
			System.out.println(estimate + " ms \n");
			
			System.out.println("> Number of key comparisons (for merge): \n" + keycmp);
			keycmp = 0;
			
		}
		
		
		public static void testresult() {
			
			testrandom();
			testascending();
			testdescending();
			
		}
	}
	
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		boolean x = true;

		
		do {
			
			System.out.println("\n \n \n \n \nPress (1) to key in the array size.");
			System.out.println("Press (2) to exit.");
			
			int i = sc.nextInt();
			
			switch (i) {
			
				case 1: 
					System.out.println("Kindly key in the array size:");
					int size = sc.nextInt();
					arraygen arrayList = new arraygen(size);
					arrayList.testresult();
					break;
					
				case 2:
					x = false;
					break;
					
				default:
					break;
			
			}	
		
		} while (x == true);
		
	}

}

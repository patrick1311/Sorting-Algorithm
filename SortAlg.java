import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SortAlg {
	
	public static final int SIZE = 100;
	
	public static void main(String []args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("src/sortData.txt"));
		
		int data[] = new int[SIZE];
		int size = 0;
		int i = 0;
		//copy data into array
		while(in.hasNextInt()) {
			data[i] = in.nextInt();
			size++;
			i++;
		}
		
		System.out.println("ORIGINAL DATA: ");
		for(int k = 0; k < size; k++)
			System.out.print(data[k] + " ");
		System.out.println("\nRUNNING SORTING ALGORITHMS...\n");
		
		//clone data[] to every sorting alg
		bubble(data.clone(), size);
		selection(data.clone(), size);
		insertion(data.clone(), size);
		quicksort(data.clone(), size);
		in.close();
	}
	
	public static void bubble(int a[], int size) {
		double time = System.nanoTime();
		for(int i = 0; i < size - 1; i++) {
			for(int j = 0; j < size - 1; j++) {
				if(a[j] > a[j+1]) {
					int temp = a[j];
					a[j] = a[j+1];
					a[j+1] = temp;
				}
			}
		}
		time = System.nanoTime() - time;
		System.out.println("BUBBLE SORT: " + time + " ns");
		print(a, size);
	}
	
	public static void selection(int a[], int size) {
		double time = System.nanoTime();
		int index;
		for(int i = 0; i < size; i++) {
			index = i;
			for(int j = i; j < size; j++) {
				if(a[j] < a[index])
					index = j;		//position of smallest value
			}
			int temp = a[index];
			a[index] = a[i];
			a[i] = temp;	
		}
		time = System.nanoTime() - time;
		System.out.println("SELECTION SORT: " + time + " ns");
		print(a, size);
	}
	
	public static void insertion(int a[], int size) {
		double time = System.nanoTime();
		
		for(int i = 0; i < size; i++) {
			for(int j = i; j > 0; j--) {
				if(a[j] < a[j-1]) {
					int temp = a[j];
					a[j] = a[j-1];
					a[j-1] = temp;
				}
			}
		}
		time = System.nanoTime() - time;
		System.out.println("INSERTION SORT: " + time + " ns");
		print(a, size);
	}
	
	public static void quicksort(int a[], int size) {
		double time = System.nanoTime();
		partition(a, 0, size-1);
		time = System.nanoTime() - time;
		System.out.println("QUICK SORT: " + time + " ns");
		print(a, size);
	}
	
	public static void swap(int a[], int x, int y) {
		int temp = a[y];
		a[y] = a[x];
		a[x] = temp;
	}
	
	public static void partition(int a[], int low, int high) {
		int p = (low + high) / 2;	//pivot index
		int pivot = a[p];
		
		int left = low;
		int right = high;
		while(left < right) {
			while(a[left] < pivot)
				left++;
			while(a[right] > pivot)
				right--;
			
			if(left <= right) {
				swap(a, left, right);
				left++;
				right--;
			}
		}
		//keep doing if sub array has 2 elements or above
		if(low < right)
			partition(a, low, right);
		if(high > left)
			partition(a, left, high);
	}
	
	public static void print(int x[], int s) {
		for(int num = 0; num < s; num++) {
			System.out.print(x[num] + " ");
		}
		System.out.println();
	}
}

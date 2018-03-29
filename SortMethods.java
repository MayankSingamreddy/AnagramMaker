import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;		// for testing purposes

/**
 *	SortMethods - Sorts an ArrayList of Strings in ascending order.
 *
 *	Requires OpenFile class to compile.
 *	Requires file randomWords.txt to execute a test run.
 *
 *	@author		Mayank Singamreddy
 *	@since		4 December 2017
 */
public class SortMethods {

	/**
	 *	Merge Sort algorithm - in ascending order
	 *	@param arr		List of String objects to sort
	 */
	public void mergeSort(List<String> arr) {
		mergeSortRecurse(arr, 0, arr.size() - 1);
	}

	/**
	 *	Recursive mergeSort method.
	 *	@param arr		List of String objects to sort
	 *	@param first	first index of arr to sort
	 *	@param last		last index of arr to sort
	 */
	public void mergeSortRecurse(List<String> arr, int first, int last) {
		int low = first;
		int high = last;
		if (low < high) {
           	int mid = low + (high - low) / 2;
           	mergeSortRecurse(arr, low, mid);
           	mergeSortRecurse(arr, mid + 1, high);
           	merge(arr, low, mid, high);
		}
	}

	/**
	 *	Merge two lists that are consecutive elements in array.
	 *	@param arr		List of String objects to merge
	 *	@param first	first index of first list
	 *	@param mid		the last index of the first list;
	 *					mid + 1 is first index of second list
	 *	@param last		last index of second list
	 */
	public void merge(List<String> arr, int first, int mid, int last) {
		List<String> temp = new ArrayList<String>();
		for (int x = 0; x <= last; x++) {
			temp.add("");
		}
       	for (int x = first; x <= last; x++) {
           	temp.set(x, arr.get(x));
       	}
       	int a = first;
       	int b = mid + 1;
       	int c = first;
				//Compare the values starting from low and mid + 1 because
				//these are the starts of two arrays that need to be merged.
				//Keep adding values while the indexes of the
				//two arrays that have yet to be merged
				//have not 'fallen off' their respective arrays.
        while (a <= mid && b <= last) {
					//compare the values of the two arrays and
			    //add the smaller to arr increment the
			    //corresponding index and the compare the counter
						if (temp.get(a).compareTo(temp.get(b)) <= 0) {
               	arr.set(c, temp.get(a));
               	a++;
           	}
			else {
               	arr.set(c, temp.get(b));
               	b++;
           	}
           	c++; //always increment the index
						//after a value is added
				}
				//Copy the rest of the left side
				//of the array into the target array.
			 	while (a <= mid) {
       		arr.set(c, temp.get(a));
       		c++;
       		a++;
       	}
	}


	/**
	 *	Print an List of Strings to the screen
	 *	@param arr		the List of Strings
	 */
	public void printArray(List<String> arr) {
		if (arr.size() == 0) System.out.print("(");
		else System.out.printf("( %-15s", arr.get(0));
		for (int a = 1; a < arr.size(); a++) {
			if (a % 5 == 0) System.out.printf(",\n  %-15s", arr.get(a));
			else System.out.printf(", %-15s", arr.get(a));
		}
		System.out.println(" )");
	}

	/*************************************************************/
	/********************** Test program *************************/
	/*************************************************************/
	private final String FILE_NAME = "randomWords.txt";

	public static void main(String[] args) {
		SortMethods se = new SortMethods();
		se.run();
	}

	public void run() {
		List<String> arr = new ArrayList<String>();
		// Fill List with random words from file
		fillArray(arr);

		System.out.println("\nMerge Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		mergeSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();
	}

	// Fill array with words
	public void fillArray(List<String> arr) {
		Scanner inFile = OpenFile.openToRead(FILE_NAME);
		while (inFile.hasNext())
			arr.add(inFile.next());
		inFile.close();
	}
}
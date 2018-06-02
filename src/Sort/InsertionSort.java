package Sort;

import java.util.Comparator;

public class InsertionSort<T> implements Sort<T> {

	@Override
	public void sort(T[] arr, Comparator<T> comparator) {
		for(int i = 1 ; i < arr.length - 1; ++i) {
			T temp = arr[i];
			int j = i -1;
			for(; j >= 0 && comparator.compare(temp, arr[j]) < 0; --j) {
				arr[i + 1] = arr[i];
			}
			arr[j + 1] = temp;
		}
	}

	@Override
	public void sort(T[] arr) {
		sort(arr, (T a, T b) ->  {
			Comparable<? super T> comparable = (Comparable<T>) a;
			return comparable.compareTo(b);
		});
	}
	
	public static void main(String[] args) {
		Integer arr[] = new Integer[100];
		for(int i = 100, j = 0 ; i > 0 && j < arr.length; --i, ++j) {
			arr[j] = i;
		}
		new InsertionSort<Integer>().sort(arr);
		for(int i = 0 ; i < arr.length - 1 ; ++i) {
			if(arr[i] > arr[i + 1]) {
				System.out.println("Helloooo");
			}
		}
	}
}

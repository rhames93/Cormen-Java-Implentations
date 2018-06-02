package Sort;

import java.util.Comparator;

public class InsertionSort<T> implements Sort<T> {

	@Override
	public void sort(T[] arr, Comparator<? super T> comparator) {
		for(int i = 1 ; i < arr.length; ++i) {
			T temp = arr[i];
			int j = i -1;
			for(; j >= 0 && comparator.compare(temp, arr[j]) < 0; --j) {
				arr[j + 1] = arr[j];
			}
			arr[j + 1] = temp;
		}
	}

	@Override
	public void sort(T[] arr) {
		sort(arr, (T a, T b) ->  {
			Comparable<? super T> comparable = (Comparable<? super T>) a;
			return comparable.compareTo(b);
		});
	}
}

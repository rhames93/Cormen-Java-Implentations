package Sort;

import java.util.Comparator;

public class SelectionSort<T> implements Sort<T> {

	@Override
	public void sort(T[] arr, Comparator<? super T> comparator) {
		for (int i = 0; i < arr.length; i++) {
			T min = arr[i];
			int pos = i;
			for (int j = i; j < arr.length; j++) {
				if(comparator.compare(min, arr[j]) > 0) {
					min = arr[j];
					pos = j;
				}
			}
			arr[pos] = arr[i];
			arr[i] = min;
		}
	}

	@Override
	public void sort(T[] arr) {
		sort(arr, (T a, T b) -> {
			Comparable<? super T> comparable = (Comparable<?super T>) a;
			return comparable.compareTo(b);
		});
	}
}

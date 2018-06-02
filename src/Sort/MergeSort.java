package Sort;

import java.util.Comparator;

/*
 * Note : This sorter is not thread safe
 */
public class MergeSort<T> implements Sort<T> {

	private Comparator<? super T> comparator;
	private T arr[];

	@Override
	public void sort(T[] arr, Comparator<? super T> comparator) {
		this.arr = arr;
		this.comparator = comparator;
		mergeSort(0, arr.length - 1);
	}

	@Override
	public void sort(T[] arr) {
		sort(arr, (T a, T b) -> {
			Comparable<? super T> comparable = (Comparable<? super T>) a;
			return comparable.compareTo(b);
		});
	}

	private void mergeSort(int l, int r) {
		if (l < r) {
			int mid = (l + r) / 2;
			mergeSort(l, mid);
			mergeSort(mid + 1, r);
			merge(l, mid, r);
		}
	}

	private void merge(int l, int mid, int r) {
		Object[] arr1 = new Object[mid - l + 1];
		Object[] arr2 = new Object[r - mid];
		for (int i = l; i <= mid; i++) {
			arr1[i - l] = arr[i];
		}
		for (int i = mid + 1; i <= r; i++) {
			arr2[i - mid - 1] = arr[i];
		}
		int i = l, j = 0, k = 0;
		for (; j < arr1.length && k < arr2.length; i++) {
			if (comparator.compare((T) arr1[j], (T) arr2[k]) < 0) {
				arr[i] = (T) arr1[j];
				j++;
			} else {
				arr[i] = (T) arr2[k];
				k++;
			}
		}
		while(j < arr1.length) {
			arr[i++] = (T) arr1[j++];
		}
		while(k < arr2.length) {
			arr[i++] = (T) arr2[k++];
		}
	}
}

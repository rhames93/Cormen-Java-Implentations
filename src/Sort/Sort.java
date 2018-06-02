package Sort;

import java.util.Comparator;

public interface Sort<T> {
	public void sort(T arr[], Comparator<T> comparator);
	
	public void sort(T arr[]);
}

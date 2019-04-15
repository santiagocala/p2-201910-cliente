package model.data_structures;

public interface Comparator2<T> {
	/*
	 * 0 if equal
	 * >0 if greater
	 * <0 if less
	 */
	public double compare(T o1, T o2);
}

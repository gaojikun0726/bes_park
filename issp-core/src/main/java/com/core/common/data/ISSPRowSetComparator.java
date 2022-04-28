package com.core.common.data;

import org.apache.commons.collections.comparators.ComparableComparator;

import java.io.Serializable;
import java.util.Comparator;

public class ISSPRowSetComparator implements Comparator, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
   *
   */
	private String keyName;
	/**
   *
   */
	private boolean ascending;
	/**
   *
   */
	private Comparator comparator;

	/**
   *
   */
	public ISSPRowSetComparator() {
		this(null);
	}

	/**
	 *
	 * @param keyName
	 *            String
	 */
	public ISSPRowSetComparator(String keyName) {
		this(keyName, true);
	}

	/**
	 *
	 * @param keyName
	 *            String
	 */
	public ISSPRowSetComparator(String keyName, boolean ascending) {
		this(keyName, ascending, null);
	}

	/**
	 *
	 * @param keyName
	 *            String
	 * @param ascending
	 *            boolean
	 * @param comparator
	 *            Comparator
	 */
	public ISSPRowSetComparator(String keyName, boolean ascending,
			Comparator comparator) {
		this.setKeyName(keyName);
		this.setAscending(ascending);
		if (comparator != null)
			this.comparator = comparator;
		else
			this.comparator = ComparableComparator.getInstance();
	}

	/**
	 *
	 * @param o1
	 *            Object
	 * @param o2
	 *            Object
	 * @return int
	 */
	public int compare(Object o1, Object o2) {
		if (keyName == null || keyName.trim().length() == 0)
			return comparator.compare(o1.toString(), o2.toString())
					* (ascending ? 1 : -1);
		ISSPRowSet rs1 = (ISSPRowSet) o1;
		ISSPRowSet rs2 = (ISSPRowSet) o2;
		Object data1 = rs1.getObject(keyName, null);
		Object data2 = rs2.getObject(keyName, null);
		if (data1 == null)
			data1 = "";
		if (data2 == null)
			data2 = "";
		return comparator.compare(data1, data2) * (ascending ? 1 : -1);
	}

	/**
	 *
	 * @param o
	 *            Object
	 * @return boolean
	 */
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ISSPRowSetComparator))
			return false;
		final ISSPRowSetComparator rsComparator = (ISSPRowSetComparator) o;
		if (!comparator.equals(rsComparator.comparator))
			return false;
		if (keyName != null)
			if (!keyName.equals(rsComparator.keyName))
				return false;
			else
				return (rsComparator.keyName == null);
		return true;
	}

	/**
	 *
	 * @param keyName
	 *            String
	 */
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	/**
	 *
	 * @param ascending
	 *            boolean
	 */
	public void setAscending(boolean ascending) {
		this.ascending = ascending;
	}

	/**
	 *
	 * @param comparator
	 *            Comparator
	 */
	public void setComparator(Comparator comparator) {
		this.comparator = comparator;
	}

	/**
	 *
	 * @return String
	 */
	public String getKeyName() {
		return keyName;
	}

	/**
	 *
	 * @return boolean
	 */
	public boolean isAscending() {
		return this.ascending;
	}

	/**
	 *
	 * @return Comparator
	 */
	public Comparator getComparator() {
		return this.comparator;
	}

}
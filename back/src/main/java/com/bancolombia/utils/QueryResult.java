package com.bancolombia.utils;

import java.util.List;

public class QueryResult {
	private int totalRecord;
	private List<Object> list;

	public QueryResult() {
		super();
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}

}

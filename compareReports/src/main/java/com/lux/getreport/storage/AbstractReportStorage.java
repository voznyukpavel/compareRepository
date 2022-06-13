package com.lux.getreport.storage;

import java.util.ArrayList;

abstract class AbstractReportStorage {

	protected ArrayList<String> headers;

	public ArrayList<String> getHeaders() {
		return headers;
	}

	public void setHeaders(ArrayList<String> headers) {
		this.headers = headers;
	}

	public void showHeaders() {
		headers.forEach(i -> System.out.println(i));
	}
}

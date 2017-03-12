package com.coding2017.week02.litestruts;

import java.util.HashMap;
import java.util.Map;

public class ActionBean {
	private String name;
	private String clazz;
	private Map<String, String> results = new HashMap<String, String>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public void put(String key,String value){
		results.put(key, value);
	}
	
	public Map<String, String> getResults() {
		return results;
	}

	public void setResults(Map<String, String> results) {
		this.results = results;
	}

	@Override
	public String toString() {
		return "ActionBean [name=" + name + ", clazz=" + clazz + ", result=" + results + "]";
	}

}

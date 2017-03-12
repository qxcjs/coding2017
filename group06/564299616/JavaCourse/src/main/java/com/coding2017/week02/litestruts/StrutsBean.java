package com.coding2017.week02.litestruts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StrutsBean {
	private List<ActionBean> actions = new ArrayList<ActionBean>();

	public List<ActionBean> getAction() {
		return actions;
	}

	public void setAction(List<ActionBean> actions) {
		this.actions = actions;
	}
	
	public void addAction(ActionBean action){
		actions.add(action);
	}
	
	public List<ActionBean> getActions() {
		return actions;
	}

	@Override
	public String toString() {
		return Arrays.toString(actions.toArray());
	}
	
}

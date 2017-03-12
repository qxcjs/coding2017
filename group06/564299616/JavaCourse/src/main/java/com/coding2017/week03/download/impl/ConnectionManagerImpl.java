package com.coding2017.week03.download.impl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.coding2017.week03.download.api.Connection;
import com.coding2017.week03.download.api.ConnectionException;
import com.coding2017.week03.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws ConnectionException {
		ConnectionImpl connection = null;
		try {
			URL u = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
			connection = new ConnectionImpl();
			connection.setConn(conn);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return connection;
	}

}

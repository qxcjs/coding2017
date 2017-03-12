package com.coding2017.week03.download.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;

import com.coding2017.week03.download.api.Connection;


public class ConnectionImpl implements Connection{
	
	private HttpURLConnection conn = null;
	
	private InputStream in = null;
	
	public void setConn(HttpURLConnection conn) {
		this.conn = conn;
	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		byte[] b = new byte[1024];
		int readCount=0;
		conn.setRequestMethod("GET");  
        conn.setReadTimeout(50000); 
		conn.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);  
		in = conn.getInputStream();
		RandomAccessFile raf = new RandomAccessFile(new File("D://rc.jpg"), "rw");
		raf.seek(startPos);
		while((readCount=in.read(b, 0, 1024))!=-1){
			raf.write(b,0,readCount);
		}
		try{
			
		}
		finally {
			raf.close();
		}
		return null;
	}

	@Override
	public int getContentLength() {
		return conn.getContentLength();
	}

	@Override
	public void close() {
		if(in!=null){
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}

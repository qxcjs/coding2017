package com.coding2017.week03.download;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import com.coding2017.week03.download.api.Connection;

public class DownloadThread extends Thread {

	Connection conn;
	int startPos;
	int endPos;
	CountDownLatch latch;

	public DownloadThread(Connection conn, int startPos, int endPos,CountDownLatch latch) {
		this.conn = conn;
		this.startPos = startPos;
		this.endPos = endPos;
		this.latch=latch;
	}

	public void run() {
		try {
			System.out.println(Thread.currentThread().getName()+"开始了");
			conn.read(startPos, endPos);
			latch.countDown();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			conn.close();
		}
	}
}

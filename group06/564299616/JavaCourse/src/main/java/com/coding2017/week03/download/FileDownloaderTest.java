package com.coding2017.week03.download;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coding2017.week03.download.api.ConnectionManager;
import com.coding2017.week03.download.api.DownloadListener;
import com.coding2017.week03.download.impl.ConnectionManagerImpl;

public class FileDownloaderTest {
	boolean downloadFinished = false;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDownload() {

		String url = "https://edmullen.net/test/rc.jpg";

		FileDownloader downloader = new FileDownloader(url);

		ConnectionManager cm = new ConnectionManagerImpl();
		downloader.setConnectionManager(cm);

		downloader.setListener(new DownloadListener() {
			@Override
			public boolean notifyFinished() {
				return false;
			}
		});

		downloader.execute();

		// 等待多线程下载程序执行完毕
		while (!downloader.getListener().notifyFinished()) {
			try {
				System.out.println("还没有下载完成，休眠五秒");
				// 休眠5秒
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("下载完成！");

	}

}

package com.tiecloud.crawler;

import org.junit.Test;

import com.tiecloud.util.LogUtil;

public class CrawlerTest {

	@Test
	public void test() {
		Crawler test = new Crawler("JUnitTestTieba");
		test.searchMode = Crawler.FETCH_TIEBA_INFO_MODE;
		test.searchText = "linux";
		test.setStartTiebaIndex(0);
		test.setEndingTiebaIndex(30);
		test.setWriteTiebaFile(false);
		test.run();
		new LogUtil(LogUtil.WRITE_TO_CONSOLE_MODE).write(test.getCrawledString()
				);
	}

}

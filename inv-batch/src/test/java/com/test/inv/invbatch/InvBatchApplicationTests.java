package com.test.inv.invbatch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.inv.batch.InvBatchApplication;
import com.test.inv.batch.job.QueryWinningListTask;
import com.test.inv.entity.WinningList;
import com.test.inv.repo.WinningListRepository;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvBatchApplication.class)
@Slf4j
public class InvBatchApplicationTests {
	@Autowired
	private WinningListRepository winningListRepository;
	
	@Autowired
	private QueryWinningListTask queryWinningListTask;

	@Test
	public void testContextLoads() {
		log.info("start");
	}
	
	@Test
	public void testIns() {
		WinningList entity = new WinningList();
		
		entity.setInvTerm("10104");
		entity.setSuperPrizeNo("11111111");
		entity.setSuperPrizeAmt(10000000);
		winningListRepository.save(entity);
	}
	
	@Test
	public void testJob() {
		queryWinningListTask.doJob();
	}

}

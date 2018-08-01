package com.test.invoice.adapter;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.test.inv.adapter.ctl.query.QueryInvoiceController;
import com.test.inv.adapter.util.SignatureUtil;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
//@Slf4j
public class AdapterApplicationTests {
	
	private MockMvc mvc;
	
	@Test
	public void contextLoads() {
		System.out.println("****************************************************");
	}
	
//	@Before
//    public void setUp() throws Exception {
//        mvc = MockMvcBuilders.standaloneSetup(new QueryInvoiceController()).build();
//    }

    @Test
    public void qryWinningList() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/inv-query/winningList/10104")
        		.accept(MediaType.APPLICATION_JSON))
             .andExpect(status().isOk());
             //.andExpect(content().string(equalTo("Hello World")));
    }
	
	
	

}

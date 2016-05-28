package com.carloscardona.tns;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.carloscardona.tns.ReservasApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ReservasApplication.class)
@WebAppConfiguration
public class ReservasApplicationTests {

	@Test
	public void contextLoads() {
	}

}

package com.rar.sampleapi;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.rar.sampleapi.utils.MessageResourceUtil;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public abstract class AbstractRestControllerTest {
	
	@Autowired
	protected MockMvc mockMcv;

	@Autowired
	protected MessageResourceUtil messageResourceUtil;
	
	@Before
	public void initGlobal() {
		System.out.println("Inicialização global dos testes");
		init();
	}
	
	public abstract void init();
	
}

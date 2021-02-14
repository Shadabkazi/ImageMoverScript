package com.skaz.imageMover.process;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.skaz.imageMover.BlogImageMoverApplication;
import com.skaz.imageMover.config.DatabaseConfig;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {BlogImageMoverApplication.class,DatabaseConfig.class})
public class ImageMoverTest {
	
	@Test
	public void hello() {
		
	}
}

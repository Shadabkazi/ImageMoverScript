package com.skaz.imageMover;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.skaz.imageMover.process.ImageMover;

@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = "com.skaz.imageMover")
public class BlogImageMoverApplication implements CommandLineRunner{

	@Autowired
	private ImageMover imageMover;
	
	public static void main(String[] args) {
		SpringApplication.run(BlogImageMoverApplication.class, args);
//		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DatabaseConfig.class);
//        ImageMover imageMover=context.getBean(ImageMover.class);
//        imageMover.MoveImages();
	}
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		imageMover.MoveImages();
	}

}

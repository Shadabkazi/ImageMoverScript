package com.skaz.imageMover.process;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.sql.DataSource;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ImageMover {
	
	
	private static final Logger log = LoggerFactory.getLogger(ImageMover.class);


	JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(final DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void MoveImages() {
		var rowMapper = BeanPropertyRowMapper.newInstance(LocalDateTime.class);
		for (int i = 1; i < 510; i++) {
			try {
			Object p = jdbcTemplate.queryForObject("SELECT created_at FROM posts where id=\"" + i + "\";",Object.class);
			Timestamp created_at=(Timestamp)p;
			System.out.println(created_at.toLocalDateTime().toLocalDate().getYear());
			System.out.println(created_at.toLocalDateTime().toLocalDate().getMonthValue());
			String year=String.valueOf(created_at.toLocalDateTime().toLocalDate().getYear());
			String month=String.valueOf(created_at.toLocalDateTime().toLocalDate().getMonthValue());
			
			File srcDir = new File("/var/opt/apps/resources/images"+File.separator+"posts"+File.separator+i);
			
			
			File yearDir = new File("/var/opt/apps/dummy/images"+File.separator+"posts"+File.separator+year);
			if(yearDir.exists()) {
				
				// check if month folder exists
				File monthDir = new File("/var/opt/apps/dummy/images"+File.separator+"posts"+File.separator+year+File.separator+month);
				File tgtDir = new File("/var/opt/apps/dummy/images"+File.separator+"posts"+File.separator+year+File.separator+month+File.separator+i);
				try {
					if(monthDir.exists() && !isDirEmpty(srcDir.toPath())) {
						// Move posts folder
						tgtDir.mkdir();
						try {
							FileUtils.copyDirectory(srcDir, tgtDir);
							log.info("Success-Copying Folder from "+srcDir.getAbsolutePath()+ " to "+monthDir.getAbsolutePath());
						} catch (IOException e) {
							log.error("Error- Copying Folder from "+srcDir.getAbsolutePath()+ " to "+monthDir.getAbsolutePath());
						}
					}else if(!isDirEmpty(srcDir.toPath())){
						// create month Directory
						monthDir.mkdir();
						// Move posts folder 
						tgtDir.mkdir();
						try {
							FileUtils.copyDirectory(srcDir, tgtDir);
							log.info("Success-Copying Folder from "+srcDir.getAbsolutePath()+ " to "+monthDir.getAbsolutePath());
						} catch (IOException e) {
							log.error("Error- Copying Folder from "+srcDir.getAbsolutePath()+ " to "+monthDir.getAbsolutePath());
						}
					}
				} catch (IOException e) {
					
				}
				
			}
			else {
				yearDir.mkdir();
				// check if month folder exists
				File monthDir = new File("/var/opt/apps/dummy/images"+File.separator+"posts"+File.separator+year+File.separator+month);
				try {
					if(monthDir.exists() && !isDirEmpty(srcDir.toPath())) {
						// Move posts folder 
						try {
							FileUtils.copyDirectory(srcDir, monthDir);
							log.info("Success-Copying Folder from "+srcDir.getAbsolutePath()+ " to "+monthDir.getAbsolutePath());
						} catch (IOException e) {
							log.error("Error- Copying Folder from "+srcDir.getAbsolutePath()+ " to "+monthDir.getAbsolutePath());
						}
					}else if(!isDirEmpty(srcDir.toPath())){
						// create month Directory
						monthDir.mkdir();
						// Move posts folder 
						try {
							FileUtils.copyDirectory(srcDir, monthDir);
							log.info("Success-Copying Folder from "+srcDir.getAbsolutePath()+ " to "+monthDir.getAbsolutePath());
						} catch (IOException e) {
							log.error("Error- Copying Folder from "+srcDir.getAbsolutePath()+ " to "+monthDir.getAbsolutePath());
						}
					}
				} catch (IOException e) {
					
				}
				
			}

		}catch(Exception e) {
			log.error("Error-No Posts found in DB Skipping. Post ID: "+i);
		}
			
		}
		System.exit(0);
		
	}

	private static boolean isDirEmpty(final Path directory) throws IOException {
	    try(DirectoryStream<Path> dirStream = Files.newDirectoryStream(directory)) {
	        return !dirStream.iterator().hasNext();
	    }
	}
}

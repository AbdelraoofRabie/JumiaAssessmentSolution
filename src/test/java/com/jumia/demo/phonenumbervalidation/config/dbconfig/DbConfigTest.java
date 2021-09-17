package com.jumia.demo.phonenumbervalidation.config.dbconfig;

import static org.assertj.core.api.Assertions.assertThat;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@PropertySource("classpath:application.properties")
class DbConfigTest {

	ApplicationContextRunner context = new ApplicationContextRunner()
			.withUserConfiguration( DbConfig.class,DbConfigTest.class);

	@Test
	public void should_check_presence_of_DBConfig_and_myDataSource() {
		context.run(it -> {
			assertThat(it).hasSingleBean(DbConfig.class);
			assertThat(it).hasBean("myDataSource");
			System.out.println(((DriverManagerDataSource)it.getBean("myDataSource")).getUrl());
		});
	}

}

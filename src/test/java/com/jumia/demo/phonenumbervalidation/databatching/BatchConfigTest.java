package com.jumia.demo.phonenumbervalidation.databatching;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

@RunWith(SpringRunner.class)
@SpringBatchTest
@EnableAutoConfiguration
@ContextConfiguration(classes = { BatchConfig.class ,JobCompletionNotificationListener.class})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, 
  DirtiesContextTestExecutionListener.class})
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@PropertySource("classpath:application.properties")
class BatchConfigTest {

	 @Autowired
	    private JobLauncherTestUtils jobLauncherTestUtils;
	  
	    @Autowired
	    private JobRepositoryTestUtils jobRepositoryTestUtils;
	  
	    @After
	    public void cleanUp() {
	        jobRepositoryTestUtils.removeJobExecutions();
	    }
	    
	    
	    @Test
	    public void whenJobExecuted_thenSuccess() throws Exception {
	        
	        // when
	        JobExecution jobExecution = jobLauncherTestUtils.launchJob();
	        JobInstance actualJobInstance = jobExecution.getJobInstance();
	        ExitStatus actualJobExitStatus = jobExecution.getExitStatus();
	      
	        // then
	        assertThat(actualJobInstance.getJobName()).isEqualTo("importPhoneNumbers");
	        assertThat(actualJobExitStatus.getExitCode()).isEqualTo("COMPLETED");
	    }

}

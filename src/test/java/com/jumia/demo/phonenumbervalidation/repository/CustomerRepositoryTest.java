package com.jumia.demo.phonenumbervalidation.repository;


import javax.persistence.EntityManager;
import javax.sql.DataSource;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.jumia.demo.phonenumbervalidation.entity.TCustomer;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class CustomerRepositoryTest {

	  @Autowired private DataSource dataSource;
	  @Autowired private JdbcTemplate jdbcTemplate;
	  @Autowired private EntityManager entityManager;
	  @Autowired private CustomerRepository customerRepository;

	  @Test
	  void injectedComponentsAreNotNull(){
	    assertThat(dataSource).isNotNull();
	    assertThat(jdbcTemplate).isNotNull();
	    assertThat(entityManager).isNotNull();
	    assertThat(customerRepository).isNotNull();
	  }
	  
	  @Test
	  void dataBatchRan() {
		  Page<TCustomer> customersPageRequest = customerRepository.getFilteredPhoneNumbers("(237)", 1, PageRequest.of(0, 20));
		  System.out.println(customersPageRequest.getContent());
	  }
}

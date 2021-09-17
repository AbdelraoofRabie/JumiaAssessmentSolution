package com.jumia.demo.phonenumbervalidation.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jumia.demo.phonenumbervalidation.entity.TCustomer;

@Repository
public interface CustomerRepository extends BaseRepository<TCustomer, Integer> {

	
//	@Query(value = "SELECT id, name, phone,case when regexp( '?2', phone ) then '1' else '0' end FROM customer c where c.phone like '?1%'", nativeQuery = true)
	@Query(value = "SELECT * FROM t_customer c where c.phone like  ?1||'%' and valid = ?2", nativeQuery = true)
	Page<TCustomer> getFilteredPhoneNumbers(String countryCode,int valid, Pageable page);

	
	
}

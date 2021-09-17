package com.jumia.demo.phonenumbervalidation.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.jumia.demo.phonenumbervalidation.entity.TCustomer;
import com.jumia.demo.phonenumbervalidation.repository.CustomerRepository;

@Service
public class CustomerService extends BaseService<Integer, TCustomer> {

	public static final String[][] countryCodeRegex = { { "Cameroon", "(237)", "\\(237\\)\\ ?[2368]\\d{7,8}$" },
			{ "Ethiopia", "(251)", "\\(251\\)\\ ?[1-59]\\d{8}$" }, { "Morocco", "(212)", "\\(212\\)\\ ?[5-9]\\d{8}$" },
			{ "Mozambique", "(258)", "\\(258\\)\\ ?[28]\\d{7,8}$" }, { "Uganda", "(256)", "\\(256\\)\\ ?\\d{9}$" } };
	
	public static final Map<String, String> codeRegex = createMap();

	private static Map<String, String> createMap() {
	    Map<String,String> codeRegexMap = new HashMap<String,String>();

	    for(String [] row: countryCodeRegex ) {
	    	codeRegexMap.put(row[1], row[2]);
	    	
		}
	    return codeRegexMap;
	}

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	protected CustomerRepository getBaseRepository() {
		return customerRepository;
	}

	public Page<TCustomer> getFilteredPhoneNumbers(int countryIndex, int valid, int page, int size) {
		return customerRepository.getFilteredPhoneNumbers(countryCodeRegex[countryIndex][1],valid,
				PageRequest.of(page, size));
		

	}

}

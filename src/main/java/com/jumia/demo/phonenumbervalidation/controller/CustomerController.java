package com.jumia.demo.phonenumbervalidation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jumia.demo.phonenumbervalidation.dto.CustomerDto;
import com.jumia.demo.phonenumbervalidation.entity.TCustomer;
import com.jumia.demo.phonenumbervalidation.mapper.CustomerMapper;
import com.jumia.demo.phonenumbervalidation.model.response.DTOPageResult;
import com.jumia.demo.phonenumbervalidation.service.CustomerService;
import com.jumia.demo.phonenumbervalidation.util.ResponseKeys;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController extends BaseController<Integer, TCustomer, CustomerDto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 792627128955638258L;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CustomerMapper customerMapper;

	@Override
	protected CustomerMapper getBaseMapper() {
		return customerMapper;
	}

	@Override
	protected CustomerService getBaseService() {
		return customerService;
	}

	
	
	@RequestMapping(path = "/getFilteredPhoneNumbers", method = RequestMethod.GET)
	public ResponseEntity<DTOPageResult<Integer, CustomerDto>> getFilteredPhoneNumbers(
			@RequestParam(required = false, value = "countryIndex") int countryIndex,
            @RequestParam(required = false, value = "valid") int valid, 
            @RequestParam(required = false, value = "page") int page,
            @RequestParam(required = false, value = "size") int size, 
			@RequestHeader(value = "authentication", required = false) String authentication,
			@RequestHeader(value = "Authorization", required = false) String authorization) {
		if (authorization == null
				|| !authorization.substring(6).equals("anVtaWE6anVtaWFwYXNzd29yZA==")) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}else {
			Page<TCustomer> entityPage = getBaseService().getFilteredPhoneNumbers(countryIndex, valid, page, size);
			long totalElements = entityPage.getTotalElements();
			int numberOfPages = entityPage.getTotalPages();
			List<CustomerDto> DTOList = getBaseMapper().toDTOList(entityPage.getContent());
			DTOPageResult<Integer, CustomerDto> response = new DTOPageResult<Integer, CustomerDto>(false,
					ResponseKeys.OK, "", DTOList, totalElements, numberOfPages);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
	}

}

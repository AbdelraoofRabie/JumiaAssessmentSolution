package com.jumia.demo.phonenumbervalidation.mapper;

import org.mapstruct.Mapper;

import com.jumia.demo.phonenumbervalidation.dto.CustomerDto;
import com.jumia.demo.phonenumbervalidation.entity.TCustomer;

@Mapper(componentModel = "spring")
public interface CustomerMapper extends BaseMapper<Integer, CustomerDto, TCustomer> {

}

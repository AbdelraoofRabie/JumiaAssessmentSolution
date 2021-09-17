package com.jumia.demo.phonenumbervalidation.databatching;

import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.jumia.demo.phonenumbervalidation.entity.TCustomer;
import com.jumia.demo.phonenumbervalidation.service.CustomerService;

public class RawCustomerDataProcessor implements ItemProcessor<RawCustomerData, TCustomer> {

    private static final Logger log = LoggerFactory.getLogger(RawCustomerDataProcessor.class);

    @Override
    public TCustomer process(final RawCustomerData RawCustomerData) throws Exception {
        
        TCustomer tCustomer = new TCustomer();
        tCustomer.setId(RawCustomerData.getId());
        tCustomer.setName(RawCustomerData.getName());
        tCustomer.setPhone(RawCustomerData.getPhone());
        
        try {
        boolean valid = Pattern.matches(CustomerService.codeRegex.get(RawCustomerData.getPhone().substring(0, 5)), RawCustomerData.getPhone());
        tCustomer.setValid(valid ? 1 : 0);
        }catch(Exception ex) {
        	log.error("valid property not set "+ ex.getMessage());
        	}
        System.out.println(tCustomer.getId());
        System.out.println(tCustomer.getName());
        System.out.println(tCustomer.getPhone());
        System.out.println(tCustomer.getValid());
        return tCustomer;
    }

}

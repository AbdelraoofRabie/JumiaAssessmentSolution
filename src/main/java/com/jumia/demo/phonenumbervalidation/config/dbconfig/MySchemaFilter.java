package com.jumia.demo.phonenumbervalidation.config.dbconfig;

import org.hibernate.boot.model.relational.Namespace;
import org.hibernate.boot.model.relational.Sequence;
import org.hibernate.mapping.Table;
import org.hibernate.tool.schema.spi.SchemaFilter;

public class MySchemaFilter implements SchemaFilter {

	 public static final MySchemaFilter INSTANCE = new MySchemaFilter();

	    @Override
	    public boolean includeNamespace(Namespace namespace) {
	        return true;
	    }

	    @Override
	    public boolean includeTable(Table table) {
	        if (table.getName().equals("customer")){
	            return false;
	        }
	        return true;
	    }

	    @Override
	    public boolean includeSequence(Sequence sequence) {
	        return true;
	    }

}

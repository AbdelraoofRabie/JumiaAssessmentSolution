package com.jumia.demo.phonenumbervalidation.databatching;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.support.SqlitePagingQueryProvider;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.sqlite.SQLiteDataSource;

import com.jumia.demo.phonenumbervalidation.entity.TCustomer;


@Configuration
@EnableBatchProcessing
public class BatchConfig {


    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    @StepScope
	public JdbcPagingItemReader<RawCustomerData> reader(){
       try {
    	   BeanWrapperFieldSetMapper<RawCustomerData> personBeanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<RawCustomerData>() {{
               setTargetType(RawCustomerData.class);
           }};
//    	   
//    	   DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
//           dataSourceBuilder.driverClassName("org.sqlite.JDBC");
//           dataSourceBuilder.url("jdbc:sqlite:memory:phonedb");
//    	   SQLiteDataSource ds = new SQLiteDataSource();
//           ds.setUrl("jdbc:sqlite:D:/LearningSpace/Jumia2ndStageAssessment/phone-number-validation/sample.db/customer.sqlite");
//           final DriverManagerDataSource dataSource = new DriverManagerDataSource();
//           dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//           dataSource.setUrl("jdbc:mysql://localhost:3306/chatbotdb?useSSL=false&allowPublicKeyRetrieval=true&useUnicode=yes&characterEncoding=UTF-8&characterSetResults=UTF-8");
//           dataSource.setUsername("root");
//           dataSource.setPassword("abdelraoof12");
    	DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.sqlite.JDBC");
        dataSource.setUrl("jdbc:sqlite:sample.db");
//        dataSource.setUsername("");
//        dataSource.setPassword("");
//		final JdbcCursorItemReader<RawCustomerData> reader = new JdbcCursorItemReader<>();
        final JdbcPagingItemReader<RawCustomerData> reader = new      JdbcPagingItemReader<>();
		reader.setDataSource(dataSource);
//		reader.setSql("SELECT name, phone FROM customer");
		SqlitePagingQueryProvider  sqlitePagingQueryProvider = new SqlitePagingQueryProvider();
		sqlitePagingQueryProvider.setSelectClause("*");
		sqlitePagingQueryProvider.setFromClause("customer");
		final Map<String, Order> sortKeys = new HashMap<>();
		   sortKeys.put("name", Order.ASCENDING);
		   sqlitePagingQueryProvider.setSortKeys(sortKeys);
		reader.setQueryProvider(sqlitePagingQueryProvider);
		reader.setFetchSize(100);
		reader.setPageSize(100);
		reader.setSaveState(false);
		reader.setRowMapper(new RowMapper<RawCustomerData>() {
			
			@Override
			public RawCustomerData mapRow(ResultSet rs, int rowNum) throws SQLException {
				RawCustomerData customerData = new RawCustomerData();
				customerData.setName(rs.getString("name"));
				customerData.setPhone(rs.getString("phone"));
				customerData.setId(Integer.parseInt(rs.getString("id")));
				System.out.println(customerData.getPhone());
				System.out.println(customerData.getName());
				System.out.println(customerData.getId());
				return customerData;
			}
		});
		
		return reader;
       }catch(Exception ex) {
    	   System.out.println(ex.getMessage() + "errrrrrrrrrrrrrrrrrrrrrrrrrrrrrror");
    	   return null;
       }
	}

    @Bean
    public RawCustomerDataProcessor processor() {
        return new RawCustomerDataProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<TCustomer> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<TCustomer>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO t_customer (id, name, phone, valid) "
                        + " VALUES (:id, :name, :phone, :valid )")
                .dataSource(dataSource).build();
    }

    @Bean
    public Job importPhoneNumbers(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory
            .get("importPhoneNumbers")
            .incrementer(new RunIdIncrementer())
            .listener(listener)
            .flow(step1)
            .end()
            .build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<TCustomer> writer) {
        return stepBuilderFactory
            .get("step1")
            .<RawCustomerData, TCustomer>chunk(10)
            .reader(reader())
            .processor(processor())
            .writer(writer)
            .build();
    }
}
package com.jumia.demo.phonenumbervalidation.model.response;

import java.io.Serializable;
import java.util.List;

import com.jumia.demo.phonenumbervalidation.dto.BaseDto;

public class DTOPageResult<ID extends Serializable, T extends BaseDto<ID>> extends BaseResponse implements Serializable {

	private static final long serialVersionUID = -7231576061006024761L;
		private List<T> data;
	    private Long totalElements;
	    private int numberOfPages;

	    public DTOPageResult(boolean errorStatus, String responseMessage, String errorCode, List<T> data, Long totalElements, int numberOfPages) {
	        super(errorStatus, responseMessage, errorCode);
	        this.data = data;
	        this.totalElements = totalElements;
	        this.numberOfPages = numberOfPages;
	    }

	    public DTOPageResult(boolean errorStatus, String errorCode, List<T> data, Long totalElements, int numberOfPages) {
	        super(errorStatus, errorCode);
	        this.data = data;
	        this.totalElements = totalElements;
	        this.numberOfPages = numberOfPages;
	    }

	    public DTOPageResult(List<T> data, Long totalElements, int numberOfPages) {
	        this.data = data;
	        this.totalElements = totalElements;
	        this.numberOfPages = numberOfPages;
	    }

	    public DTOPageResult() {
	    }

	    public List<T> getData() {
	        return data;
	    }

	    public void setData(List<T> data) {
	        this.data = data;
	    }

	    public Long getTotalElements() {
	        return totalElements;
	    }

	    public void setTotalElements(Long totalElements) {
	        this.totalElements = totalElements;
	    }

	    public int getNumberOfPages() {
	        return numberOfPages;
	    }

	    public void setNumberOfPages(int numberOfPages) {
	        this.numberOfPages = numberOfPages;
	}

}

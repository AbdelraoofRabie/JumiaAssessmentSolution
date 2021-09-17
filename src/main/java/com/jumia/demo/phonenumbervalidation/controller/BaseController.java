package com.jumia.demo.phonenumbervalidation.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jumia.demo.phonenumbervalidation.dto.BaseDto;
import com.jumia.demo.phonenumbervalidation.entity.BaseEntity;
import com.jumia.demo.phonenumbervalidation.mapper.BaseMapper;
import com.jumia.demo.phonenumbervalidation.model.response.DTOPageResult;
import com.jumia.demo.phonenumbervalidation.model.response.DTOSingleResult;
import com.jumia.demo.phonenumbervalidation.service.BaseService;
import com.jumia.demo.phonenumbervalidation.util.ResponseKeys;

public abstract class BaseController<ID extends Serializable, Entity extends BaseEntity<ID>, DTO extends BaseDto<ID>>
		implements Serializable {


	private static final long serialVersionUID = -3786938583481668260L;

	protected abstract BaseMapper<ID, DTO, Entity> getBaseMapper();

	protected abstract BaseService<ID, Entity> getBaseService();

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<DTOPageResult<ID, DTO>> listAll(@RequestParam("page") int page,
			@RequestParam("size") int size)
//                                  @RequestParam(required = false, value = "direction") Direction direction,
//                                  @RequestParam(required = false, value = "sort") String... sort) 
	{
		Page<Entity> entityPage = getBaseService().findAll(page, size);
		long totalElements = entityPage.getTotalElements();
		int numberOfPages = entityPage.getTotalPages();
		List<DTO> DTOList = getBaseMapper().toDTOList(entityPage.getContent());
		DTOPageResult<ID, DTO> response = new DTOPageResult<ID, DTO>(false, ResponseKeys.OK, "", DTOList, totalElements,
				numberOfPages);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<DTOSingleResult<ID, DTO>> findById(@PathVariable(value = "id") ID id) {
		Entity entity = getBaseService().find(id);
		DTOSingleResult<ID, DTO> response;
		if (entity != null) {
			DTO dTO = getBaseMapper().toDTO(entity);
			response = new DTOSingleResult<ID, DTO>(false, ResponseKeys.OK, dTO);
		} else {
			response = new DTOSingleResult<ID, DTO>(true, "NotFound", ResponseKeys.NOT_FOUND, null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<DTOSingleResult<ID, DTO>> newEntity(@RequestBody Entity entityParam) {
		Entity entity = getBaseService().create(entityParam);
		DTO dto = getBaseMapper().toDTO(entity);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new DTOSingleResult<ID, DTO>(false, ResponseKeys.OK, dto));
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<DTOSingleResult<ID, DTO>> updateEntity(@RequestBody Entity entityParam) {
		Entity entity = getBaseService().update(entityParam);
		DTO dto = getBaseMapper().toDTO(entity);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(new DTOSingleResult<ID, DTO>(false, ResponseKeys.OK, dto));
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<DTOSingleResult<ID, DTO>> deleteEntity(@PathVariable(value = "id") ID id) {
		Entity entity = getBaseService().deleteById(id);
		DTOSingleResult<ID, DTO> response;
		if (entity != null) {
			DTO dTO = getBaseMapper().toDTO(entity);
			response = new DTOSingleResult<ID, DTO>(false, ResponseKeys.OK_DELETED, dTO);
		} else {
			response = new DTOSingleResult<ID, DTO>(true, "NotFound", ResponseKeys.NOT_FOUND, null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}

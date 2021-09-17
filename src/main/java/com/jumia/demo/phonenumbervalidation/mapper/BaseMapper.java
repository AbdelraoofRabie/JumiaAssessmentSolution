package com.jumia.demo.phonenumbervalidation.mapper;

import java.io.Serializable;
import java.util.List;

import com.jumia.demo.phonenumbervalidation.dto.BaseDto;
import com.jumia.demo.phonenumbervalidation.entity.BaseEntity;

public interface BaseMapper<ID extends Serializable, DTO extends BaseDto<ID>, Entity extends BaseEntity<ID>> {

	Entity toEntity(DTO dtoParam);

	DTO toDTO(Entity entityParam);

	List<Entity> toEntityList(List<DTO> dtoListParam);

	List<DTO> toDTOList(List<Entity> entityListParam);

}

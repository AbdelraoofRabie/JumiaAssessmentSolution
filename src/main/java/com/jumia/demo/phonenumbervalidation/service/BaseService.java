package com.jumia.demo.phonenumbervalidation.service;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.jumia.demo.phonenumbervalidation.entity.BaseEntity;
import com.jumia.demo.phonenumbervalidation.repository.BaseRepository;

public abstract class BaseService<ID extends Serializable, Entity extends BaseEntity<ID>> {

	protected abstract BaseRepository<Entity, ID> getBaseRepository();

	public Entity create(Entity entityParam) {
		return getBaseRepository().save(entityParam);
	}

	public Entity update(Entity entityParam) {
		return getBaseRepository().save(entityParam);
	}

	public Entity deleteById(ID id) {
		Optional<Entity> result = getBaseRepository().findById(id);
		if (result.isPresent()) {
			getBaseRepository().deleteById(id);
			return result.get();
		}
		return null;
	}

	public Entity find(ID id) {
		Optional<Entity> result = getBaseRepository().findById(id);
		if (result.isPresent()) {
			return result.get();
		}
		return null;
	}

	public Page<Entity> findAllSorted(int page, int size, Direction sortDirection, String... sortProperties) {
		PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sortDirection, sortProperties));
		return getBaseRepository().findAll(pageRequest);
	}

	public Page<Entity> findAll(int page, int size) {
		return getBaseRepository().findAll(PageRequest.of(page, size));
	}

	public Page<Entity> findByExampleSorted(Example<Entity> example, int page, int size, Direction sortDirection,
			String... sortProperties) {
		PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sortDirection, sortProperties));
		return getBaseRepository().findAll(example, pageRequest);
	}

	public Page<Entity> findByExample(Example<Entity> example, int page, int size, Direction sortDirection,
			String... sortProperties) {
		return getBaseRepository().findAll(example, PageRequest.of(page, size));
	}
}


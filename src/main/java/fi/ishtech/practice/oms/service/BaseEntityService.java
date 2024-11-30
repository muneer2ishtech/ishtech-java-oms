package fi.ishtech.practice.oms.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fi.ishtech.practice.oms.entity.BaseEntity;
import fi.ishtech.practice.oms.mapper.BaseMapper;
import fi.ishtech.practice.oms.payload.BaseEntityVo;
import fi.ishtech.practice.oms.payload.filter.BaseFilterParams;
import fi.ishtech.practice.oms.repo.BaseRepo;
import fi.ishtech.practice.oms.spec.BaseSpec;
import jakarta.persistence.EntityManager;

/**
 *
 * @author Muneer Ahmed Syed
 */
public interface BaseEntityService<T extends BaseEntity, V extends BaseEntityVo> extends BaseService {

	BaseRepo<T> getRepo();

	BaseMapper<T, V> getMapper();

	public default EntityManager getEntityManager() {
		return null;
	}

	/**
	 * Refreshes entity from DB with latest values
	 *
	 * @param entity
	 */
	public default void refresh(T entity) {
		getEntityManager().refresh(entity);
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	public default Optional<T> findOneById(Long id) {
		return getRepo().findById(id);
	}

	/**
	 *
	 * @param id
	 * @return T
	 */
	public default T findOneByIdOrElseNull(Long id) {
		return this.findOneById(id).orElse(null);
	}

	/**
	 * Finds by id and if not present, throws {@link NoSuchElementException}.
	 *
	 * @param id
	 * @return T
	 */
	public default T findOneByIdOrElseThrow(Long id) {
		return this.findOneById(id).orElseThrow();
	}

	/**
	 * Finds by id and if not present, throws {@link RuntimeException}.
	 *
	 * @param id
	 * @return T
	 */
	public default T findOneByIdOrElseThrow(Long id, Supplier<? extends RuntimeException> exceptionSupplier) {
		return this.findOneById(id).orElseThrow(exceptionSupplier);
	}

	public default V findOneByIdAndMapToVoOrElseThrow(Long id) {
		return getMapper().toVo(this.findOneByIdOrElseThrow(id));
	}

	/**
	 *
	 * @return {@link List} of {@link BaseEntity}
	 */
	public default List<T> findAll() {
		return getRepo().findAll();
	}

	public default Page<T> findAll(BaseSpec<T, ? extends BaseFilterParams> spec, Pageable pageable) {
		return getRepo().findAll(spec, pageable);
	}

	public default Page<V> findAllAndMapToVo(BaseSpec<T, ? extends BaseFilterParams> spec, Pageable pageable) {
		return this.findAll(spec, pageable).map(getMapper()::toVo);
	}

}
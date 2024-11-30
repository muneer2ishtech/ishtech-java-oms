package fi.ishtech.practice.oms.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapping;

import fi.ishtech.practice.oms.entity.BaseEntity;
import fi.ishtech.practice.oms.payload.BaseEntityVo;

/**
 *
 * @author Muneer Ahmed Syed
 */
public interface BaseMapper<T extends BaseEntity, V extends BaseEntityVo> {

	/**
	 *
	 * @param entity {@link BaseEntity}
	 * @return {@link BaseEntityVo}
	 */
	@BeanMapping(ignoreByDefault = true)
	@Mapping(source = "active", target = "active")
	@Mapping(source = "description", target = "description")
	BaseEntityVo toBaseVo(BaseEntity entity);

	V toVo(T t);

}
package fi.ishtech.practice.oms.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import fi.ishtech.practice.oms.entity.SalesOrder;
import fi.ishtech.practice.oms.payload.SalesOrderVo;

/**
 *
 * @author Muneer Ahmed Syed
 */
@Mapper(componentModel = "spring")
public interface SalesOrderMapper extends BaseMapper<SalesOrder, SalesOrderVo> {

	/**
	 *
	 * @param entity {@link SalesOrder}
	 * @return {@link SalesOrderVo}
	 */
	@BeanMapping(ignoreByDefault = true)
	@InheritConfiguration(name = "toBaseVo")
	@Mapping(source = "id", target = "id")
	@Mapping(source = "customerId", target = "customerId")
	@Mapping(source = "origTotalAmount", target = "origTotalAmount")
	@Mapping(source = "discountPercent", target = "discountPercent")
	@Mapping(source = "discountAmount", target = "discountAmount")
	@Mapping(source = "netAmount", target = "netAmount")
	SalesOrderVo toVo(SalesOrder entity);

	/**
	 *
	 * @param vo     {@link SalesOrderVo}
	 * @param entity {@link SalesOrder}
	 * @return {@link SalesOrder} entity
	 */
	@BeanMapping(ignoreByDefault = true)
	@InheritInverseConfiguration(name = "toVo")
	SalesOrder toExistingEntity(SalesOrderVo vo, @MappingTarget SalesOrder entity);

	/**
	 *
	 * @param vo {@link SalesOrderVo}
	 * @return new {@link SalesOrder} entity
	 */
	@BeanMapping(ignoreByDefault = true)
	@InheritInverseConfiguration(name = "toVo")
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "active", constant = "true")
	@Mapping(target = "origTotalAmount", constant = "0")
	@Mapping(target = "totalAmount", constant = "0")
	@Mapping(target = "netAmount", constant = "0")
	SalesOrder toNewEntity(SalesOrderVo vo);

}
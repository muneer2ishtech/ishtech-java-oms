package fi.ishtech.practice.oms.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import fi.ishtech.practice.oms.entity.CustomerDiscount;
import fi.ishtech.practice.oms.payload.CustomerDiscountVo;

/**
 *
 * @author Muneer Ahmed Syed
 */
@Mapper(componentModel = "spring")
public interface CustomerDiscountMapper extends BaseMapper<CustomerDiscount, CustomerDiscountVo> {

	/**
	 *
	 * @param entity {@link CustomerDiscount}
	 * @return {@link CustomerDiscountVo}
	 */
	@BeanMapping(ignoreByDefault = true)
	@InheritConfiguration(name = "toBaseVo")
	@Mapping(source = "id", target = "id")
	@Mapping(source = "customerId", target = "customerId")
	@Mapping(source = "productId", target = "productId")
	@Mapping(source = "discountType", target = "discountType")
	@Mapping(source = "discountPercent", target = "discountPercent")
	@Mapping(source = "buyQuantity", target = "buyQuantity")
	@Mapping(source = "payQuantity", target = "payQuantity")
	CustomerDiscountVo toVo(CustomerDiscount entity);

	/**
	 *
	 * @param vo     - {@link CustomerDiscountVo}
	 * @param entity - {@link CustomerDiscount}
	 * @return {@link CustomerDiscount} entity
	 */
	@BeanMapping(ignoreByDefault = true)
	@InheritInverseConfiguration(name = "toVo")
	CustomerDiscount toExistingEntity(CustomerDiscountVo vo, @MappingTarget CustomerDiscount entity);

	/**
	 *
	 * @param vo - {@link CustomerDiscountVo}
	 * @return new {@link CustomerDiscount} entity
	 */
	@BeanMapping(ignoreByDefault = true)
	@InheritConfiguration(name = "toExistingEntity")
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "active", constant = "true")
	CustomerDiscount toNewEntity(CustomerDiscountVo vo);

}
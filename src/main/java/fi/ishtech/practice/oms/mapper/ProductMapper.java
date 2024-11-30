package fi.ishtech.practice.oms.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import fi.ishtech.practice.oms.entity.Product;
import fi.ishtech.practice.oms.payload.ProductVo;

/**
 *
 * @author Muneer Ahmed Syed
 */
@Mapper(componentModel = "spring")
public interface ProductMapper extends BaseMapper<Product, ProductVo> {

	/**
	 *
	 * @param entity {@link Product}
	 * @return {@link ProductVo}
	 */
	@BeanMapping(ignoreByDefault = true)
	@InheritConfiguration(name = "toBaseVo")
	@Mapping(source = "id", target = "id")
	@Mapping(source = "name", target = "name")
	@Mapping(source = "unitPrice", target = "unitPrice")
	ProductVo toVo(Product entity);

	/**
	 *
	 * @param vo     {@link ProductVo}
	 * @param entity {@link Product}
	 * @return {@link Product} entity
	 */
	@BeanMapping(ignoreByDefault = true)
	@InheritInverseConfiguration(name = "toVo")
	Product toExistingEntity(ProductVo vo, @MappingTarget Product entity);

	/**
	 *
	 * @param vo {@link ProductVo}
	 * @return new {@link Product} entity
	 */
	@BeanMapping(ignoreByDefault = true)
	@InheritInverseConfiguration(name = "toVo")
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "active", constant = "true")
	Product toNewEntity(ProductVo vo);

}
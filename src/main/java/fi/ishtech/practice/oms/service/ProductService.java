package fi.ishtech.practice.oms.service;

import fi.ishtech.practice.oms.entity.Product;
import fi.ishtech.practice.oms.payload.ProductVo;
import jakarta.validation.Valid;

/**
 *
 * @author Muneer Ahmed Syed
 */
public interface ProductService extends BaseEntityService<Product, ProductVo> {

	/**
	 * Creates a new Product
	 *
	 * @param productVo - {@link ProductVo}
	 * @return {@link Product}
	 */
	Product create(ProductVo productVo);

	ProductVo updateAndMapToVo(@Valid ProductVo productVo);

	/**
	 * Soft deletes Product
	 *
	 * @param id
	 */
	void deleteById(Long id);

}
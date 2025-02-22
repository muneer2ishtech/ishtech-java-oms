package fi.ishtech.practice.oms.repo;

import fi.ishtech.practice.oms.entity.CustomerDiscount;
import fi.ishtech.practice.oms.enums.DiscountTypeEnum;

/**
 *
 * @author Muneer Ahmed Syed
 */
public interface CustomerDiscountRepo extends BaseRepo<CustomerDiscount> {

	CustomerDiscount findOneByCustomerIdAndProductIdAndDiscountTypeAndIsActiveTrue(Long customerId, Long ProductId,
			DiscountTypeEnum discountType);

	CustomerDiscount findOneByCustomerIdAndProductIdIsNullAndDiscountTypeAndIsActiveTrue(Long customerId,
			DiscountTypeEnum discountType);

}
package fi.ishtech.practice.oms.spec;

import java.util.List;

import fi.ishtech.practice.oms.entity.CustomerDiscount;
import fi.ishtech.practice.oms.entity.CustomerDiscount_;
import fi.ishtech.practice.oms.entity.Product;
import fi.ishtech.practice.oms.entity.Product_;
import fi.ishtech.practice.oms.entity.UserProfile;
import fi.ishtech.practice.oms.entity.UserProfile_;
import fi.ishtech.practice.oms.payload.filter.CustomerDiscountFilterParams;
import fi.ishtech.practice.oms.payload.filter.ProductFilterParams;
import fi.ishtech.practice.oms.payload.filter.UserProfileFilterParams;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

/**
 *
 * @author Muneer Ahmed Syed
 */
public class CustomerDiscountSpec extends BaseSpec<CustomerDiscount, CustomerDiscountFilterParams> {

	private static final long serialVersionUID = -2693167671940681473L;

	public CustomerDiscountSpec(CustomerDiscountFilterParams params) {
		super(params);
	}

	@Override
	protected List<Predicate> toPredicateList(Root<CustomerDiscount> root, CriteriaBuilder cb) {
		List<Predicate> predicates = super.toPredicateList(root, cb);

		addPredicateLike(predicates, root, cb, params.getCustomerId(), CustomerDiscount_.CUSTOMER_ID);
		addPredicateLike(predicates, root, cb, params.getProductId(), CustomerDiscount_.PRODUCT_ID);

		addPredicateIn(predicates, root, cb, params.getDiscountType(), CustomerDiscount_.DISCOUNT_TYPE);

		addPredicateGE(predicates, root, cb, params.getMinDiscountPercent(), CustomerDiscount_.DISCOUNT_PERCENT);
		addPredicateLE(predicates, root, cb, params.getMaxDiscountPercent(), CustomerDiscount_.DISCOUNT_PERCENT);

		addPredicateGE(predicates, root, cb, params.getMinBuyQuantity(), CustomerDiscount_.BUY_QUANTITY);
		addPredicateLE(predicates, root, cb, params.getMaxBuyQuantity(), CustomerDiscount_.BUY_QUANTITY);

		addPredicateGE(predicates, root, cb, params.getMinPayQuantity(), CustomerDiscount_.PAY_QUANTITY);
		addPredicateLE(predicates, root, cb, params.getMaxPayQuantity(), CustomerDiscount_.PAY_QUANTITY);

		addProductPredicatesToPredicateList(root, cb, predicates);
		addCustomerPredicatesToPredicateList(root, cb, predicates);

		return predicates;
	}

	private void addProductPredicatesToPredicateList(Root<CustomerDiscount> root, CriteriaBuilder cb,
			List<Predicate> predicates) {
		if (params.getProduct() == null) {
			return;
		}

		ProductFilterParams productFilterParams = params.getProduct();
		Join<CustomerDiscount, Product> joinProduct = getJoin(null, root, CustomerDiscount_.PRODUCT);

		addPredicateLike(predicates, joinProduct, cb, productFilterParams.getName(), Product_.NAME);
	}

	private void addCustomerPredicatesToPredicateList(Root<CustomerDiscount> root, CriteriaBuilder cb,
			List<Predicate> predicates) {
		if (params.getCustomer() == null) {
			return;
		}

		UserProfileFilterParams customerFilterParams = params.getCustomer();
		Join<CustomerDiscount, UserProfile> joinCustomer = getJoin(null, root, CustomerDiscount_.CUSTOMER);

		addPredicateLike(predicates, joinCustomer, cb, customerFilterParams.getFirstName(), UserProfile_.FIRST_NAME);
	}

}
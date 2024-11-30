package fi.ishtech.practice.oms.spec;

import java.util.List;

import fi.ishtech.practice.oms.entity.SalesOrder;
import fi.ishtech.practice.oms.entity.SalesOrder_;
import fi.ishtech.practice.oms.entity.UserProfile;
import fi.ishtech.practice.oms.entity.UserProfile_;
import fi.ishtech.practice.oms.payload.filter.SalesOrderFilterParams;
import fi.ishtech.practice.oms.payload.filter.UserProfileFilterParams;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

/**
 *
 * @author Muneer Ahmed Syed
 */
public class SalesOrderSpec extends BaseSpec<SalesOrder, SalesOrderFilterParams> {

	private static final long serialVersionUID = -4027068875044076203L;

	public SalesOrderSpec(SalesOrderFilterParams params) {
		super(params);
	}

	@Override
	protected List<Predicate> toPredicateList(Root<SalesOrder> root, CriteriaBuilder cb) {
		List<Predicate> predicates = super.toPredicateList(root, cb);

		addPredicateLike(predicates, root, cb, params.getCustomerId(), SalesOrder_.CUSTOMER_ID);

		addPredicateGE(predicates, root, cb, params.getMinTotalAmount(), SalesOrder_.TOTAL_AMOUNT);
		addPredicateLE(predicates, root, cb, params.getMaxTotalAmount(), SalesOrder_.TOTAL_AMOUNT);

		addPredicateGE(predicates, root, cb, params.getMinDiscountPercent(), SalesOrder_.DISCOUNT_PERCENT);
		addPredicateLE(predicates, root, cb, params.getMaxDiscountPercent(), SalesOrder_.DISCOUNT_PERCENT);

		addPredicateGE(predicates, root, cb, params.getMinDiscountAmount(), SalesOrder_.DISCOUNT_AMOUNT);
		addPredicateLE(predicates, root, cb, params.getMaxDiscountAmount(), SalesOrder_.DISCOUNT_AMOUNT);

		addPredicateGE(predicates, root, cb, params.getMinNetAmount(), SalesOrder_.NET_AMOUNT);
		addPredicateLE(predicates, root, cb, params.getMaxNetAmount(), SalesOrder_.NET_AMOUNT);

		addCustomerPredicatesToPredicateList(root, cb, predicates);

		return predicates;
	}

	private void addCustomerPredicatesToPredicateList(Root<SalesOrder> root, CriteriaBuilder cb,
			List<Predicate> predicates) {
		if (params.getCustomer() == null) {
			return;
		}

		UserProfileFilterParams customerFilterParams = params.getCustomer();
		Join<SalesOrder, UserProfile> joinCustomer = getJoin(null, root, SalesOrder_.CUSTOMER);

		addPredicateLike(predicates, joinCustomer, cb, customerFilterParams.getFirstName(), UserProfile_.FIRST_NAME);
	}

}
package fi.ishtech.practice.oms.spec;

import java.util.List;

import fi.ishtech.practice.oms.entity.Product;
import fi.ishtech.practice.oms.entity.Product_;
import fi.ishtech.practice.oms.entity.SalesOrder;
import fi.ishtech.practice.oms.entity.SalesOrderItem;
import fi.ishtech.practice.oms.entity.SalesOrderItem_;
import fi.ishtech.practice.oms.entity.SalesOrder_;
import fi.ishtech.practice.oms.payload.filter.ProductFilterParams;
import fi.ishtech.practice.oms.payload.filter.SalesOrderFilterParams;
import fi.ishtech.practice.oms.payload.filter.SalesOrderItemFilterParams;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

/**
 *
 * @author Muneer Ahmed Syed
 */
public class SalesOrderItemSpec extends BaseSpec<SalesOrderItem, SalesOrderItemFilterParams> {

	private static final long serialVersionUID = -1373909197677613927L;

	public SalesOrderItemSpec(SalesOrderItemFilterParams params) {
		super(params);
	}

	@Override
	protected List<Predicate> toPredicateList(Root<SalesOrderItem> root, CriteriaBuilder cb) {
		List<Predicate> predicates = super.toPredicateList(root, cb);

		addPredicateLike(predicates, root, cb, params.getSalesOrderId(), SalesOrderItem_.SALES_ORDER_ID);
		addPredicateLike(predicates, root, cb, params.getProductId(), SalesOrderItem_.PRODUCT_ID);

		addPredicateGE(predicates, root, cb, params.getMinQuantity(), SalesOrderItem_.QUANTITY);
		addPredicateLE(predicates, root, cb, params.getMaxQuantity(), SalesOrderItem_.QUANTITY);

		addPredicateGE(predicates, root, cb, params.getMinUnitPrice(), SalesOrderItem_.UNIT_PRICE);
		addPredicateLE(predicates, root, cb, params.getMaxUnitPrice(), SalesOrderItem_.UNIT_PRICE);

		addPredicateGE(predicates, root, cb, params.getMinOrigLineAmount(), SalesOrderItem_.ORIG_LINE_AMOUNT);
		addPredicateLE(predicates, root, cb, params.getMaxOrigLineAmount(), SalesOrderItem_.ORIG_LINE_AMOUNT);

		addPredicateGE(predicates, root, cb, params.getMinDiscountPercent(), SalesOrderItem_.DISCOUNT_PERCENT);
		addPredicateLE(predicates, root, cb, params.getMaxDiscountPercent(), SalesOrderItem_.DISCOUNT_PERCENT);

		addPredicateGE(predicates, root, cb, params.getMinDiscountAmount(), SalesOrderItem_.DISCOUNT_AMOUNT);
		addPredicateLE(predicates, root, cb, params.getMaxDiscountAmount(), SalesOrderItem_.DISCOUNT_AMOUNT);

		addPredicateGE(predicates, root, cb, params.getMinLineAmount(), SalesOrderItem_.LINE_AMOUNT);
		addPredicateLE(predicates, root, cb, params.getMaxLineAmount(), SalesOrderItem_.LINE_AMOUNT);

		addProductPredicatesToPredicateList(root, cb, predicates);
		addSalesOrderPredicatesToPredicateList(root, cb, predicates);

		return predicates;
	}

	private void addProductPredicatesToPredicateList(Root<SalesOrderItem> root, CriteriaBuilder cb,
			List<Predicate> predicates) {
		if (params.getProduct() == null) {
			return;
		}

		ProductFilterParams productFilterParams = params.getProduct();
		Join<SalesOrderItem, Product> joinProduct = getJoin(null, root, SalesOrderItem_.PRODUCT);

		addPredicateLike(predicates, joinProduct, cb, productFilterParams.getName(), Product_.NAME);
	}

	private void addSalesOrderPredicatesToPredicateList(Root<SalesOrderItem> root, CriteriaBuilder cb,
			List<Predicate> predicates) {
		if (params.getSalesOrder() == null) {
			return;
		}

		SalesOrderFilterParams salesOrderFilterParams = params.getSalesOrder();
		Join<SalesOrderItem, SalesOrder> joinSalesOrder = getJoin(null, root, SalesOrderItem_.SALES_ORDER);

		addPredicateLike(predicates, joinSalesOrder, cb, salesOrderFilterParams.getCustomerId(),
				SalesOrder_.CUSTOMER_ID);
	}

}
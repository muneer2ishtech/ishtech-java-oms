package fi.ishtech.practice.oms.spec;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import fi.ishtech.practice.oms.entity.BaseEntity;
import fi.ishtech.practice.oms.entity.BaseEntity_;
import fi.ishtech.practice.oms.entity.BaseIdGenEntity_;
import fi.ishtech.practice.oms.payload.filter.BaseFilterParams;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class BaseSpec<T extends BaseEntity, P extends BaseFilterParams> implements Specification<T> {

	private static final long serialVersionUID = -3229114388128110817L;

	private static final String PERCENT = "%";

	protected final P params;

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicates = toPredicateList(root, cb);
		query.distinct(true);

		Predicate[] predicatesArray = new Predicate[predicates.size()];
		return cb.and(predicates.toArray(predicatesArray));
	}

	protected List<Predicate> toPredicateList(Root<T> root, CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<>();

		addPredicateEq(predicates, root, cb, params.getId(), BaseIdGenEntity_.ID);

		addPredicateEq(predicates, root, cb, params.getIsActive(), BaseEntity_.IS_ACTIVE);
		addPredicateLike(predicates, root, cb, params.getDescription(), BaseEntity_.DESCRIPTION);

		return predicates;
	}

	protected void addPredicateLike(List<Predicate> predicates, Root<T> root, CriteriaBuilder cb, String attribValue,
			String attribName) {
		if (StringUtils.hasText(attribValue)) {
			predicates.add(cb.like(root.get(attribName), padForSqlLike(attribValue)));
		}
	}

	protected void addPredicateLike(List<Predicate> predicates, Join<? extends BaseEntity, ? extends BaseEntity> join,
			CriteriaBuilder cb, String attribValue, String attribName) {
		if (StringUtils.hasText(attribValue)) {
			predicates.add(cb.like(join.get(attribName), padForSqlLike(attribValue)));
		}
	}

	protected void addPredicateLike(List<Predicate> predicates, Root<T> root, CriteriaBuilder cb, Number attribValue,
			String attribName) {
		if (attribValue != null) {
			predicates.add(cb.like(root.get(attribName).as(String.class), padForSqlLike(attribValue)));
		}
	}

	protected void addPredicateLike(List<Predicate> predicates, Join<? extends BaseEntity, ? extends BaseEntity> join,
			CriteriaBuilder cb, Number attribValue, String attribName) {
		if (attribValue != null) {
			predicates.add(cb.like(join.get(attribName).as(String.class), padForSqlLike(attribValue)));
		}
	}

	protected void addPredicateEq(List<Predicate> predicates, Root<T> root, CriteriaBuilder cb, String attribValue,
			String attribName) {
		if (StringUtils.hasText(attribValue)) {
			predicates.add(cb.equal(root.get(attribName), attribValue));
		}
	}

	protected void addPredicateEq(List<Predicate> predicates, Root<T> root, CriteriaBuilder cb, Number attribValue,
			String attribName) {
		if (attribValue != null) {
			predicates.add(cb.equal(root.get(attribName), attribValue));
		}
	}

	protected void addPredicateEq(List<Predicate> predicates, Root<T> root, CriteriaBuilder cb, Boolean attribValue,
			String attribName) {
		if (attribValue != null) {
			predicates.add(cb.equal(root.get(attribName), attribValue));
		}
	}

	protected void addPredicateEq(List<Predicate> predicates, Join<? extends BaseEntity, ? extends BaseEntity> join,
			CriteriaBuilder cb, String attribValue, String attribName) {
		if (attribValue != null) {
			predicates.add(cb.equal(join.get(attribName), attribValue));
		}
	}

	protected void addPredicateEq(List<Predicate> predicates, Join<? extends BaseEntity, ? extends BaseEntity> join,
			CriteriaBuilder cb, Number attribValue, String attribName) {
		if (attribValue != null) {
			predicates.add(cb.equal(join.get(attribName), attribValue));
		}
	}

	protected void addPredicateEq(List<Predicate> predicates, Join<? extends BaseEntity, ? extends BaseEntity> join,
			CriteriaBuilder cb, Boolean attribValue, String attribName) {
		if (attribValue != null) {
			predicates.add(cb.equal(join.get(attribName), attribValue));
		}
	}

	protected void addPredicateIn(List<Predicate> predicates, Root<T> root, CriteriaBuilder cb, Object[] attribValue,
			String attribName) {
		if (!ArrayUtils.isEmpty(attribValue)) {
			predicates.add(root.get(attribName).in(attribValue));
		}
	}

	protected void addPredicateIn(List<Predicate> predicates, Join<? extends BaseEntity, ? extends BaseEntity> join,
			CriteriaBuilder cb, Object[] attribValue, String attribName) {
		if (!ArrayUtils.isEmpty(attribValue)) {
			predicates.add(join.get(attribName).in(attribValue));
		}
	}

	protected void addPredicateGE(List<Predicate> predicates, Root<T> root, CriteriaBuilder cb, BigDecimal attribValue,
			String attribName) {
		if (attribValue != null) {
			predicates.add(cb.ge(root.get(attribName), attribValue));
		}
	}

	protected void addPredicateLE(List<Predicate> predicates, Root<T> root, CriteriaBuilder cb, BigDecimal attribValue,
			String attribName) {
		if (attribValue != null) {
			predicates.add(cb.le(root.get(attribName), attribValue));
		}
	}

	protected void addPredicateGE(List<Predicate> predicates, Root<T> root, CriteriaBuilder cb, Integer attribValue,
			String attribName) {
		if (attribValue != null) {
			predicates.add(cb.ge(root.get(attribName), attribValue));
		}
	}

	protected void addPredicateLE(List<Predicate> predicates, Root<T> root, CriteriaBuilder cb, Integer attribValue,
			String attribName) {
		if (attribValue != null) {
			predicates.add(cb.le(root.get(attribName), attribValue));
		}
	}

	@SuppressWarnings("hiding")
	protected <T, X> Join<T, X> getJoin(Join<T, X> join, Root<T> root, String joinAttrib) {
		if (join == null) {
			join = (Join<T, X>) root.<T, X>join(joinAttrib);
		}
		return join;
	}

	@SuppressWarnings("hiding")
	protected <T, X> Join<T, X> getJoin(Join<T, X> join, Root<T> root, String joinAttrib, JoinType joinType) {
		if (join == null) {
			join = (Join<T, X>) root.<T, X>join(joinAttrib, joinType);
		}
		return join;
	}

	protected <X, Y> Join<X, Y> getDeepJoin(Join<X, Y> join, Join<T, X> parentJoin, String joinAttrib) {
		if (join == null) {
			join = (Join<X, Y>) parentJoin.<X, Y>join(joinAttrib);
		}
		return join;
	}

	protected <X, Y> Join<X, Y> getDeepJoin(Join<X, Y> join, Join<T, X> parentJoin, String joinAttrib,
			JoinType joinType) {
		if (join == null) {
			join = (Join<X, Y>) parentJoin.<X, Y>join(joinAttrib, joinType);
		}
		return join;
	}

	/**
	 * TODO: move to some util class
	 *
	 * @param values
	 *
	 * @return {@code true} if {@code null} or empty else {@code false}
	 */
	@SuppressWarnings("unused")
	private boolean isArrayEmpty(Object[] values) {
		return values == null || values.length == 0;
	}

	/**
	 * TODO: move to some util class
	 *
	 * @param input
	 * @return input padded with % on either side
	 */
	protected String padForSqlLike(String input) {
		return PERCENT + input + PERCENT;
	}

	/**
	 * TODO: move to some util class
	 *
	 * @param input
	 * @return input padded with % on either side
	 */
	protected String padForSqlLike(Number input) {
		return PERCENT + input + PERCENT;
	}

	/**
	 * TODO: move to some util class
	 *
	 * @param input
	 * @return input padded with % on either side
	 */
	protected String padForSqlLike(Integer input) {
		return PERCENT + input + PERCENT;
	}

}
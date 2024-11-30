package fi.ishtech.practice.oms.spec;

import java.util.List;

import org.springframework.util.StringUtils;

import fi.ishtech.practice.oms.entity.User;
import fi.ishtech.practice.oms.entity.UserProfile;
import fi.ishtech.practice.oms.entity.UserProfile_;
import fi.ishtech.practice.oms.entity.User_;
import fi.ishtech.practice.oms.payload.filter.UserProfileFilterParams;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

/**
 *
 * @author Muneer Ahmed Syed
 */
public class UserProfileSpec extends BaseSpec<UserProfile, UserProfileFilterParams> {

	private static final long serialVersionUID = -2080551823386644045L;

	public UserProfileSpec(UserProfileFilterParams params) {
		super(params);
	}

	@Override
	protected List<Predicate> toPredicateList(Root<UserProfile> root, CriteriaBuilder cb) {
		List<Predicate> predicates = super.toPredicateList(root, cb);

		addPredicateLike(predicates, root, cb, params.getFirstName(), UserProfile_.FIRST_NAME);
		addPredicateLike(predicates, root, cb, params.getMiddleName(), UserProfile_.MIDDLE_NAME);
		addPredicateLike(predicates, root, cb, params.getLastName(), UserProfile_.LAST_NAME);
		addPredicateLike(predicates, root, cb, params.getTitle(), UserProfile_.TITLE);
		addPredicateLike(predicates, root, cb, params.getPrefix(), UserProfile_.PREFIX);
		addPredicateLike(predicates, root, cb, params.getSuffix(), UserProfile_.SUFFIX);

		addPredicateLike(predicates, root, cb, params.getMobilePhone(), UserProfile_.MOBILE_PHONE);
		addPredicateEq(predicates, root, cb, params.getDefaultLang(), UserProfile_.DEFAULT_LANG);
		addPredicateEq(predicates, root, cb, params.getAddressId(), UserProfile_.ADDRESS_ID);

		if (StringUtils.hasText(params.getEmail())) {
			Join<UserProfile, User> joinUser = getJoin(null, root, UserProfile_.USER);
			addPredicateLike(predicates, joinUser, cb, params.getEmail(), User_.EMAIL);
		}

		return predicates;
	}

}
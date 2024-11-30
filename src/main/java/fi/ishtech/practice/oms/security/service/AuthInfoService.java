package fi.ishtech.practice.oms.security.service;

import org.springframework.security.core.Authentication;

/**
 *
 * @author Muneer Ahmed Syed
 */
public interface AuthInfoService {

	/**
	 * Gets userId from {@link Authentication}
	 *
	 * @return Long
	 */
	Long getUserId();

	/**
	 * Gets username from {@link Authentication}
	 *
	 * @return String
	 */
	String getUsername();

	/**
	 * Check if user has ROLE_ADMIN from {@link Authentication}
	 *
	 * @return boolean
	 */
	boolean isAdmin();

}
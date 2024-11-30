package fi.ishtech.practice.oms.security.userdetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fi.ishtech.practice.oms.service.UserProfileService;
import fi.ishtech.practice.oms.service.UserRoleService;
import fi.ishtech.practice.oms.service.UserService;

/**
 *
 * @author Muneer Ahmed Syed
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private UserProfileService userProfileService;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var user = userService.findOneByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		var userRoleEnums = userRoleService.findAllActiveEnumsByUserId(user.getId());

		var userProfile = userProfileService.findOneByIdOrElseNull(user.getId());

		boolean isActive = user.isActive() && userProfile.isActive() && userRoleEnums != null
				&& !userRoleEnums.isEmpty();

		return UserDetailsImpl.of(user.getId(), user.getUsername(), user.getEmail(), user.getPasswordHash(), isActive,
				userRoleEnums, userProfile == null ? null : userProfile.getFullName(),
				userProfile == null ? null : userProfile.getDefaultLangEnum());
	}

}
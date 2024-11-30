package fi.ishtech.practice.oms.security.userdetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fi.ishtech.core.i18n.enums.LangEnum;
import fi.ishtech.practice.oms.enums.UserRoleEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Muneer Ahmed Syed
 */
@NoArgsConstructor
public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 6774079004291687456L;

	@Getter
	@Setter(lombok.AccessLevel.PRIVATE)
	private Long id;

	@Getter
	@Setter(lombok.AccessLevel.PRIVATE)
	private String username;

	@Getter
	@Setter(lombok.AccessLevel.PRIVATE)
	private String email;

	@Getter
	@Setter(lombok.AccessLevel.PRIVATE)
	@ToString.Exclude
	@JsonIgnore
	private String password;

	@Getter
	@Setter(lombok.AccessLevel.PRIVATE)
	private Collection<? extends GrantedAuthority> authorities;

	@Getter
	@Setter(lombok.AccessLevel.PRIVATE)
	private boolean isEnabled;

	@Getter
	@Setter(lombok.AccessLevel.PRIVATE)
	private String fullName;

	@Getter
	@Setter(lombok.AccessLevel.PRIVATE)
	private LangEnum lang;

	public static UserDetails of(Long id, String username, String email, String password, boolean isEnabled, List<UserRoleEnum> userRoleEnums, String fullName, LangEnum lang) {
		UserDetailsImpl userDetails = new UserDetailsImpl();

		userDetails.setId(id);
		userDetails.setUsername(username);
		userDetails.setEmail(email);

		userDetails.setPassword(password);

		userDetails.setEnabled(isEnabled);

		userDetails.setAuthorities(getAuthorities(userRoleEnums));

		userDetails.setFullName(fullName);
		userDetails.setLang(lang);

		return userDetails;
	}

	private static List<GrantedAuthority> getAuthorities(List<UserRoleEnum> userRoleEnums) {
		// @formatter:off
		return userRoleEnums
				.stream()
				.map(u -> u.asRole())
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
		// @formatter:on
	}

	@JsonIgnore
	public List<String> getScopes() {
		// @formatter:off
		return getAuthorities()
				.stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		// @formatter:on
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.isEnabled();
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.isEnabled();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.isEnabled();
	}

}
package fi.ishtech.practice.oms.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Muneer Ahmed Syed
 */
public enum UserRoleEnum {

	// @formatter:off
	ADMIN,
	USER;
	// @formatter:on

	private static final String SCOPE = "SCOPE_";
	private static final String ROLE = "ROLE_";

	public static UserRoleEnum valueOfOrElseThrow(String name) {
		// @formatter:off
        return Arrays.stream(UserRoleEnum.values())
                     .filter(e -> e.name().equalsIgnoreCase(name))
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException( "No enum constant UserRoleEnum." + name));
    	// @formatter:on
	}

	public static UserRoleEnum valueOfOrElseNull(String name) {
		// @formatter:off
        return Arrays.stream(UserRoleEnum.values())
                     .filter(e -> e.name().equalsIgnoreCase(name))
                     .findFirst()
                     .orElse(null);
    	// @formatter:on
	}

	public static List<UserRoleEnum> valueOfOrElseNull(List<String> names) {
		// @formatter:off
        return names.stream()
                    .map(name -> Arrays.stream(UserRoleEnum.values())
                                      .filter(e -> e.name().equalsIgnoreCase(name))
                                      .findFirst()
                                      .orElse(null))
                    .filter(e -> e != null)
                    .collect(Collectors.toList());
		// @formatter:on
	}

	public String asScope() {
		return SCOPE + name();
	}

	public String asRole() {
		return ROLE + name();
	}

	public static String asScope(UserRoleEnum userRoleEnum) {
		return userRoleEnum == null ? null : userRoleEnum.asScope();
	}

	public static String asRole(UserRoleEnum userRoleEnum) {
		return userRoleEnum == null ? null : userRoleEnum.asRole();
	}

	public static List<String> asScopes(List<UserRoleEnum> userRoleEnums) {
		// @formatter:off
        return userRoleEnums.stream()
                            .map(u -> u.asScope())
                            .collect(Collectors.toList());
		// @formatter:on
	}

	public static List<String> asRoles(List<UserRoleEnum> userRoleEnums) {
		// @formatter:off
        return userRoleEnums.stream()
                            .map(u -> u.asRole())
                            .collect(Collectors.toList());
		// @formatter:on
	}

}
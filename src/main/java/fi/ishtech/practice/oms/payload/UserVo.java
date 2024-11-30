package fi.ishtech.practice.oms.payload;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 *
 * @author Muneer Ahmed Syed
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserVo extends BaseEntityVo {

	private static final long serialVersionUID = 4782005236210790042L;

	private String username;

	private String email;

	private boolean forceChangePassword;

	private boolean isEmailVerified;

}
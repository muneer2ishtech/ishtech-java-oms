package fi.ishtech.practice.oms.payload.in;

import java.io.Serializable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

/**
 *
 * @author Muneer Ahmed Syed
 */
@Data
public class SigninRequest implements Serializable {

	private static final long serialVersionUID = 6071040922303039192L;

	@NotBlank
	@Email
	private String email;

	@NotBlank
	@ToString.Exclude
	private String password;

}
package fi.ishtech.practice.oms.payload.out;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author Muneer Ahmed Syed
 */
@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8464016196278556079L;

	@JsonProperty("token_type")
	private final String tokenType = "Bearer";

	@JsonProperty("access_token")
	private final String accessToken;

	public static JwtResponse of(String accessToken) {
		return new JwtResponse(accessToken);
	}

}
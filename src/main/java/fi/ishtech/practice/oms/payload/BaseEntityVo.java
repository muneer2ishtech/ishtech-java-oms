package fi.ishtech.practice.oms.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 *
 * @author Muneer Ahmed Syed
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class BaseEntityVo implements BaseVo {

	private static final long serialVersionUID = 263136202880986340L;

	protected Long id;

	@JsonProperty("isActive")
	protected boolean isActive = true;

	protected String description;

}
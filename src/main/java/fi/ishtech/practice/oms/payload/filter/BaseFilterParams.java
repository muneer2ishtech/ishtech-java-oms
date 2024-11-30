package fi.ishtech.practice.oms.payload.filter;

import fi.ishtech.practice.oms.payload.BaseVo;
import jakarta.validation.constraints.Positive;
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
public abstract class BaseFilterParams implements BaseVo {

	private static final long serialVersionUID = 7672866470370773784L;

	@Positive
	protected Long id;

	protected Boolean isActive;

	protected String description;

}
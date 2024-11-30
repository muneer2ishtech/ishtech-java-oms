package fi.ishtech.practice.oms.entity;

import java.io.Serializable;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.ToString;

/**
 *
 * @author Muneer Ahmed Syed
 */
@MappedSuperclass
@DynamicInsert
@DynamicUpdate
@Data
@ToString(callSuper = true)
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = -2648640835825829270L;

	@Column(name = "is_active", nullable = false, insertable = true, updatable = true)
	protected boolean isActive;

	@Column(name = "description", nullable = true, insertable = true, updatable = true)
	protected String description;

}
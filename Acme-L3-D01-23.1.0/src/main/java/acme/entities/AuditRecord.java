
package acme.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AuditRecord extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes --------------------------------------------------------------

	@NotBlank
	@Length(min = 1, max = 75)
	protected String			subject;

	@NotBlank
	@Length(min = 1, max = 100)
	protected String			assessment;

	@Temporal(TemporalType.TIMESTAMP)
	@PastOrPresent
	@NotNull
	protected Date				startAudition;

	@Temporal(TemporalType.TIMESTAMP)
	@PastOrPresent
	@NotNull
	protected Date				endAudition;

	@NotNull
	protected MarkType			mark;

	@URL
	protected String			link;

	// Derived attributes -----------------------------------------------------

	// Relationships -------------------------------------------------------
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Audit				audit;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Course			course;

}

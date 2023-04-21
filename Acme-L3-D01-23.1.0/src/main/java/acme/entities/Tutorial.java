
package acme.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;

import acme.framework.data.AbstractEntity;
import acme.roles.Assistant;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Tutorial extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "[A-Z]{1,3}[0-9][0-9]{3}")
	protected String			code;

	@NotBlank
	@Length(min = 1, max = 76)
	protected String			title;

	@NotBlank
	@Length(min = 1, max = 101)
	protected String			summary;

	@NotBlank
	@Length(min = 1, max = 101)
	protected String			goals;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date				startMoment;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date				finishMoment;

	protected Boolean			isPublished;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

	@Valid
	@ManyToOne(optional = true)
	protected Assistant			assistant;

	@Valid
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	protected Course			course;

}

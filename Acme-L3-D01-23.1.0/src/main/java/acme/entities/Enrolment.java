
package acme.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import acme.framework.data.AbstractEntity;
import acme.roles.Student;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
public class Enrolment extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "[A-Z]{1,3} [0-9][0-9]{3}")
	protected String			code;

	@NotBlank
	@Max(75)
	protected String			motivation;

	@NotBlank
	@Max(100)
	protected String			goals;

	// Derived attributes -----------------------------------------------------

	protected Double			workTime;

	// Relationships ----------------------------------------------------------

	@NotNull
	@Valid
	@ManyToOne
	protected Course			course;

	@NotNull
	@Valid
	@ManyToOne
	protected Student			student;

}

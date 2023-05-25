
package acme.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;

import acme.framework.data.AbstractEntity;
import acme.roles.Auditor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Audit extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes --------------------------------------------------------------

	@NotBlank
	@Pattern(regexp = "[A-Z]{1,3}[0-9][0-9]{3}")
	@Column(unique = true)
	protected String			code;

	@NotBlank
	@Length(min = 1, max = 100)
	protected String			conclusion;

	@NotBlank
	@Length(min = 1, max = 100)
	protected String			strongPoints;

	@NotBlank
	@Length(min = 1, max = 100)
	protected String			weakPoints;

	@NotBlank
	protected String			note;

	boolean						draftMode			= true;

	// Derived attributes -----------------------------------------------------

	// Relationships -------------------------------------------------------

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	protected Auditor			auditor;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	protected Course			course;

}

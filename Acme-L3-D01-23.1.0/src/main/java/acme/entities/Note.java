
package acme.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

/*
 * @pattern(reg)
 */

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
public class Note extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes --------------------------------------------------------------

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date				instantiationMoment;

	@NotBlank
	@Length(min = 1, max = 76)
	protected String			title;

	@NotBlank
	@Length(min = 1, max = 76)
	@Pattern(regexp = "^\\(\\w+\\) - \\(\\w+,\\s\\w+\\)$")
	protected String			author;

	@NotBlank
	@Length(min = 1, max = 101)
	protected String			message;

	@Email
	protected String			email;

	@URL
	protected String			link;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}

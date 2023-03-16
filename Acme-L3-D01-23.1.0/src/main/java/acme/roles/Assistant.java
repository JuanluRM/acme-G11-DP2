
package acme.roles;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.data.AbstractRole;
import lombok.Getter;
import lombok.Setter;

/*
 * -Relacion entre Asistente con las otras entidades
 * -Tutorial estimatedTime regulaion noNull/blank
 * -Session time period regulacion, explicacion
 * 
 */

@Entity
@Getter
@Setter
public class Assistant extends AbstractRole {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Length(min = 1, max = 76)
	protected String			supervisor;

	@NotBlank
	@Length(min = 1, max = 101)
	protected String			expertiseFields;

	@NotBlank
	@Length(min = 1, max = 101)
	protected String			resume;

	@URL
	protected String			infoLink;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}

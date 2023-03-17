
package acme.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Banner extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes --------------------------------------------------------------

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date				instantiationMoment;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date				start;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date				end;

	@NotBlank
	@URL
	protected String			picture;

	@NotBlank
	@Length(min = 1, max = 75)
	protected String			slogan;

	@NotBlank
	@URL
	protected String			link;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
}

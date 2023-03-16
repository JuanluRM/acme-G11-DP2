
package acme.entities;

import java.time.Period;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
<<<<<<<< HEAD:Acme-L3-D01-23.1.0/src/main/java/acme/entities/PracticumSession.java
//@CustomLog

public class PracticumSession extends AbstractEntity {

========
public class Offer extends AbstractEntity {
>>>>>>>> develop:Acme-L3-D01-23.1.0/src/main/java/acme/entities/Offer.java
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes --------------------------------------------------------------

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@NotNull
	protected Date				instantiationMoment;

	@NotBlank
	@Length(min = 1, max = 75)
	protected String			heading;

	@NotBlank
	@Length(min = 1, max = 100)
	protected String			summary;

	@NotBlank
	protected Period			availabilityPeriod;

	@Min(0)
	@NotNull
	protected Double			price;

	@URL
	protected String			link;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}

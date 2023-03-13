
package acme.entities.tutorial;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import acme.framework.data.AbstractEntity;
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
	@NotBlank
	protected Date				startMoment;

	@Temporal(TemporalType.TIMESTAMP)
	@NotBlank
	protected Date				finishMoment;

	@NotNull
	protected List<Session>		sessions;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

	@NotNull
	@Valid
	@OneToMany
	protected Session			session;

}

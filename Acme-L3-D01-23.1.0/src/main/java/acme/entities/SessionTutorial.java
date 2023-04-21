
package acme.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SessionTutorial extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Length(min = 1, max = 76)
	protected String			title;

	@NotBlank
	@Length(min = 1, max = 101)
	protected String			summary;

	@NotNull
	protected SessionType		tiposesion;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date				startMoment;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date				finishMoment;

	@URL
	protected String			moreInfo;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

	@Valid
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	protected Tutorial			tutorial;
}

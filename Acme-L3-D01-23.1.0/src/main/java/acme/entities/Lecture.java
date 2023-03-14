
package acme.entities;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;
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
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
public class Lecture extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes --------------------------------------------------------------

	@NotBlank
	@Length(max = 75)
	protected String			title;

	@NotBlank
	@Length(max = 100)
	protected String			lectureAbstract;

	@Min(0)
	protected Double			estimatedLearningTime;

	@NotBlank
	@Length(max = 100)
	protected String			body;

	@NotNull
	protected LectureType		type;

	@URL
	protected String			link;

	// Relationships ----------------------------------------------------------

	@NotNull
	@Valid
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne(optional = true)
	protected Course			course;

}

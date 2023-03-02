
package acme.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.URL;

import acme.framework.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter

public class Course extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes --------------------------------------------------------------

	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "[A-Z]{1,3} [0-9]{3}")
	protected String			code;

	@NotBlank
	@Max(75)
	protected String			title;

	@NotBlank
	@Max(100)
	protected String			courseAbstract;

	@NotNull
	@Min(0)
	protected Double			retailPrice;

	@NotNull
	protected List<Lecture>		lectures;

	@URL
	protected String			link;

	// Derived attributes -----------------------------------------------------


	@Transient
	public CourseType getCourseType() {
		CourseType courseType = null;
		int theorical = 0;
		int hands_on = 0;

		for (int i = 0; i < this.lectures.size(); i++) {
			final Lecture l = this.lectures.get(i);

			if (l.getType() == LectureType.HANDS_ON)
				hands_on++;
			else
				theorical++;
		}

		if (theorical > hands_on)
			courseType = CourseType.THEORICAL;
		else if (hands_on > theorical)
			courseType = CourseType.HANDS_ON;
		else
			courseType = CourseType.BALANCED;

		return courseType;
	}

	// Relationships ----------------------------------------------------------


	@NotNull
	@Valid
	@OneToMany
	protected Lecture lecture;

	//duda como hacer que el sistema descarte los cursos puramente teoricos --> formulario, aqui no

}

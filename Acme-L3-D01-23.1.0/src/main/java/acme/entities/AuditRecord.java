
package acme.entities;

import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.data.AbstractEntity;
import acme.framework.helpers.MomentHelper;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AuditRecord extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes --------------------------------------------------------------

	@NotBlank
	@Length(min = 1, max = 75)
	protected String			subject;

	@NotBlank
	@Length(min = 1, max = 100)
	protected String			assessment;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@NotNull
	protected Date				startAudition;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@NotNull
	protected Date				endAudition;

	@NotBlank
	@Pattern(regexp = "^((A\\+|A|B|C|F|F\\-))$", message = "{validation.regex.mark}")
	protected String			mark;

	@URL
	protected String			link;
	@NotNull
	protected boolean			draftMode;

	protected boolean			correction;

	// Derived attributes -----------------------------------------------------


	@Transient
	public Double getHoursFromPeriod() {
		final Duration duration = MomentHelper.computeDuration(this.startAudition, this.endAudition);
		return duration.getSeconds() / 3600.0;
	}

	public Double period() {
		double res;
		final Date start = this.startAudition;
		final Date end = this.endAudition;
		final Long st = TimeUnit.MILLISECONDS.toMinutes(start.getTime());
		final Long et = TimeUnit.MILLISECONDS.toMinutes(end.getTime());
		res = Double.parseDouble(et.toString()) / 60 - Double.parseDouble(st.toString()) / 60;
		return res;
	}


	// Relationships -------------------------------------------------------
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Audit audit;

}

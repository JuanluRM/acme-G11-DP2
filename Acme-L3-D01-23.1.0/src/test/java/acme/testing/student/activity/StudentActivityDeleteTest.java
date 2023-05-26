
package acme.testing.student.activity;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.Enrolment;
import acme.testing.TestHarness;
import acme.testing.student.enrolment.StudentEnrolmentTestRepository;

public class StudentActivityDeleteTest extends TestHarness {

	// Internal data ----------------------------------------------------------

	@Autowired
	protected StudentEnrolmentTestRepository repository;

	// Test methods -----------------------------------------------------------


	@ParameterizedTest
	@CsvFileSource(resources = "/student/activity/delete-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void test100Positive(final int recordEnrolmentIndex, final String code, final int recordActivityIndex, final String title) {

		super.signIn("student1", "student1");

		super.clickOnMenu("Student", "Enrolment List");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordEnrolmentIndex, 0, code);

		super.clickOnListingRecord(recordEnrolmentIndex);
		super.clickOnButton("Activities");

		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordActivityIndex, 0, title);

		super.clickOnListingRecord(recordActivityIndex);
		super.checkFormExists();
		super.clickOnSubmit("Delete");
		super.checkNotErrorsExist();

		super.signOut();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/student/activity/delete-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void test200Negative(final int recordEnrolmentIndex, final String code, final int recordActivityIndex, final String title) {
		// HINT: this test attempts to publish a course that cannot be published, yet.

		super.signIn("student1", "student1");

		super.clickOnMenu("Student", "Enrolment List");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordEnrolmentIndex, 0, code);

		super.clickOnListingRecord(recordEnrolmentIndex);
		super.clickOnButton("Activities");

		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordActivityIndex, 0, title);

		super.clickOnListingRecord(recordActivityIndex);
		super.checkFormExists();
		super.checkNotButtonExists("Delete");

		super.signOut();
	}

	@Test
	public void test300Hacking() {
		// HINT: this test tries to publish a job with a role other than "Lecturer".

		final Collection<Enrolment> enrolments;
		String param;

		enrolments = this.repository.findManyEnrolmentsByStudentUsername("student1");
		for (final Enrolment enrolment : enrolments)
			if (enrolment.getIsFinalised() == false) {
				param = String.format("id=%d", enrolment.getId());

				super.checkLinkExists("Sign in");
				super.request("/student/activity/delete", param);
				super.checkPanicExists();

				super.signIn("administrator1", "administrator1");
				super.request("/student/activity/delete", param);
				super.checkPanicExists();
				super.signOut();

				super.signIn("administrator2", "administrator2");
				super.request("/student/activity/delete", param);
				super.checkPanicExists();
				super.signOut();

				super.signIn("auditor1", "auditor1");
				super.request("/student/activity/delete", param);
				super.checkPanicExists();
				super.signOut();
			}
	}

	@Test
	public void test301Hacking() {
		// HINT: this test tries to publish a published course that was registered by the principal.

		final Collection<Enrolment> enrolments;
		String param;

		super.signIn("student1", "student1");
		enrolments = this.repository.findManyEnrolmentsByStudentUsername("student1");
		for (final Enrolment enrolment : enrolments)
			if (enrolment.getIsFinalised() == true) {
				param = String.format("id=%d", enrolment.getId());
				super.request("/student/enrolment/delete", param);
			}
		super.signOut();
	}

	@Test
	public void test302Hacking() {
		// HINT: this test tries to publish a job that wasn't registered by the principal,
		// HINT+ be it published or unpublished.

		final Collection<Enrolment> enrolments;
		String param;

		super.signIn("student2", "student2");
		enrolments = this.repository.findManyEnrolmentsByStudentUsername("student1");
		for (final Enrolment enrolment : enrolments) {
			param = String.format("id=%d", enrolment.getId());
			super.request("/student/enrolment/delete", param);
		}
		super.signOut();
	}
}

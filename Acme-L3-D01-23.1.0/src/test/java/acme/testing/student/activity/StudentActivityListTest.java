
package acme.testing.student.activity;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.Enrolment;
import acme.testing.TestHarness;

public class StudentActivityListTest extends TestHarness {
	// Internal state ---------------------------------------------------------

	@Autowired
	protected StudentActivityTestRepository repository;

	// Test methods -----------------------------------------------------------


	@ParameterizedTest
	@CsvFileSource(resources = "/student/activity/list-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void test100Positive(final int enrolmentRecordIndex, final String code, final int activityRecordIndex, final String title, final String activityAbstract, final String activityType, final String startPeriod, final String endPeriod,
		final String link) {

		super.signIn("student1", "student1");

		super.clickOnMenu("Student", "Enrolment List");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.checkColumnHasValue(enrolmentRecordIndex, 0, code);
		super.clickOnListingRecord(enrolmentRecordIndex);
		super.checkInputBoxHasValue("code", code);
		super.clickOnButton("Activities");

		super.checkListingExists();
		super.checkColumnHasValue(activityRecordIndex, 0, title);
		super.checkColumnHasValue(activityRecordIndex, 1, activityAbstract);
		super.checkColumnHasValue(activityRecordIndex, 2, activityType);
		super.checkColumnHasValue(activityRecordIndex, 3, startPeriod);
		super.checkColumnHasValue(activityRecordIndex, 4, endPeriod);
		super.checkColumnHasValue(activityRecordIndex, 5, link);
		super.clickOnListingRecord(activityRecordIndex);

		super.signOut();
	}

	@Test
	public void test200Negative() {
		// HINT: there aren't any negative tests for this feature because it's a listing
		// HINT+ that doesn't involve entering any data in any forms.
	}

	@Test
	public void test300Hacking() {
		// HINT: this test tries to list the lectures of a course that is unpublished
		// HINT+ using a principal that didn't create it. 

		final Collection<Enrolment> enrolments;
		String param;

		enrolments = this.repository.findManyEnrolmentsByStudentUsername("student1");
		for (final Enrolment enrolment : enrolments) {
			param = String.format("enrolmentId=%d", enrolment.getId());

			super.checkLinkExists("Sign in");
			super.request("/student/activity/list", param);
			super.checkPanicExists();

			super.signIn("administrator1", "administrator1");
			super.request("/student/activity/list", param);
			super.checkPanicExists();
			super.signOut();

			super.signIn("administrator2", "administrator2");
			super.request("/student/activity/list", param);
			super.checkPanicExists();
			super.signOut();

			super.signIn("student2", "student2");
			super.request("/student/activity/list", param);
			super.checkPanicExists();
			super.signOut();

			super.signIn("auditor1", "auditor1");
			super.request("/student/activity/list", param);
			super.checkPanicExists();
			super.signOut();
		}
	}
}

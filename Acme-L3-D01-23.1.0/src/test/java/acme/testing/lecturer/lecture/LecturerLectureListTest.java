
package acme.testing.lecturer.lecture;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.Course;
import acme.testing.TestHarness;

public class LecturerLectureListTest extends TestHarness {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected LecturerLectureTestRepository repository;

	// Test methods -----------------------------------------------------------


	@ParameterizedTest
	@CsvFileSource(resources = "/lecturer/lecture/list-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void test100Positive(final int courseRecordIndex, final String title, final int lectureRecordIndex, final String titleLecture, final String lectureAbstract, final String type) {

		super.signIn("lecturer1", "lecturer1");

		super.clickOnMenu("Lecturer", "My courses");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.checkColumnHasValue(courseRecordIndex, 1, title);
		super.clickOnListingRecord(courseRecordIndex);
		super.checkInputBoxHasValue("title", title);
		super.clickOnButton("Lectures");

		super.checkListingExists();
		super.checkColumnHasValue(lectureRecordIndex, 0, titleLecture);
		super.checkColumnHasValue(lectureRecordIndex, 1, lectureAbstract);
		super.checkColumnHasValue(lectureRecordIndex, 2, type);
		super.clickOnListingRecord(lectureRecordIndex);

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

		final Collection<Course> courses;
		String param;

		courses = this.repository.findManyCoursesByLecturerUsername("lecturer1");
		for (final Course course : courses) {
			param = String.format("masterId=%d", course.getId());

			super.checkLinkExists("Sign in");
			super.request("/lecturer/lecture/list", param);
			super.checkPanicExists();

			super.signIn("administrator1", "administrator1");
			super.request("/lecturer/lecture/list", param);
			super.checkPanicExists();
			super.signOut();

			super.signIn("administrator2", "administrator2");
			super.request("/lecturer/lecture/list", param);
			super.checkPanicExists();
			super.signOut();

			super.signIn("lecturer2", "lecturer2");
			super.request("/lecturer/lecture/list", param);
			super.checkPanicExists();
			super.signOut();

			super.signIn("auditor1", "auditor1");
			super.request("/lecturer/lecture/list", param);
			super.checkPanicExists();
			super.signOut();
		}
	}
}

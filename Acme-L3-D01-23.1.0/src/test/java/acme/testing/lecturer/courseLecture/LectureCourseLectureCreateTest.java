
package acme.testing.lecturer.courseLecture;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.Course;
import acme.testing.TestHarness;

public class LectureCourseLectureCreateTest extends TestHarness {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected LecturerCourseLectureTestRepository repository;

	// Test methods -----------------------------------------------------------


	@ParameterizedTest
	@CsvFileSource(resources = "/lecturer/courseLecture/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void test100Positive(final int courseRecordIndex, final int lectureRecordIndex, final String lecture, final String title, final String lectureAbstract) {

		super.signIn("lecturer1", "lecturer1");

		super.clickOnMenu("Lecturer", "My courses");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.clickOnListingRecord(courseRecordIndex);
		super.clickOnButton("Add a lecture");

		super.fillInputBoxIn("lecture", lecture);
		super.clickOnSubmit("Add lecture");

		super.clickOnButton("Lectures");

		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(lectureRecordIndex, 0, title);
		super.checkColumnHasValue(lectureRecordIndex, 1, lectureAbstract);

		super.signOut();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/lecturer/courseLecture/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void test200Negative(final int courseRecordIndex) {

		super.signIn("lecturer1", "lecturer1");

		super.clickOnMenu("Lecturer", "My courses");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.clickOnListingRecord(courseRecordIndex);

		super.clickOnButton("Add a lecture");
		super.clickOnSubmit("Add lecture");

		super.checkErrorsExist();

		super.signOut();
	}

	@Test
	public void test300Hacking() {
		Collection<Course> courses;
		String param;

		courses = this.repository.findManyCoursesByLecturerUsername("lecturer1");
		for (final Course course : courses) {
			param = String.format("courseId=%d", course.getId());

			super.checkLinkExists("Sign in");
			super.request("/lecturer/course-lecture/create-courseLecture", param);
			super.checkPanicExists();

			super.signIn("administrator1", "administrator1");
			super.request("/lecturer/course-lecture/create-courseLecture", param);
			super.checkPanicExists();
			super.signOut();

			super.signIn("administrator2", "administrator2");
			super.request("/lecturer/course-lecture/create-courseLecture", param);
			super.checkPanicExists();
			super.signOut();

			super.signIn("auditor1", "auditor1");
			super.request("/lecturer/course-lecture/create-courseLecture", param);
			super.checkPanicExists();
			super.signOut();
		}
	}

	@Test
	public void test301Hacking() {
		Collection<Course> courses;
		String param;

		super.checkLinkExists("Sign in");
		super.signIn("lecturer2", "lecturer2");
		courses = this.repository.findManyPublishCoursesByLecturerUsername("lecturer2");
		for (final Course course : courses) {
			param = String.format("courseId=%d", course.getId());
			super.request("/lecturer/course-lecture/create-courseLecture", param);
			super.checkPanicExists();
		}
	}

	@Test
	public void test302Hacking() {
		Collection<Course> courses;
		String param;

		super.checkLinkExists("Sign in");
		super.signIn("lecturer2", "lecturer2");
		courses = this.repository.findManyCoursesByLecturerUsername("lecturer1");
		for (final Course course : courses) {
			param = String.format("courseId=%d", course.getId());
			super.request("/lecturer/course-lecture/create-courseLecture", param);
			super.checkPanicExists();
		}
	}
}


package acme.testing.company.practica;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;

import acme.testing.TestHarness;

public class CompanyPracticaCreateTest extends TestHarness {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected CompanyPracticaTestRepository repository;

	// Test methods -----------------------------------------------------------


	@ParameterizedTest
	@CsvFileSource(resources = "/company/practica/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void test100Positive(final int practicaRecordIndex, final String code, final String title, final String summary, final String goals, final String estimatedTotalTime, final String draftMode, final String course) throws InterruptedException {

		super.signIn("company1", "company1");

		super.clickOnMenu("Company", "List Practica");
		super.checkListingExists();

		super.clickOnButton("Create");
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("summary", summary);
		super.fillInputBoxIn("goals", goals);
		super.fillInputBoxIn("estimatedTotalTime", estimatedTotalTime);
		super.fillInputBoxIn("course", course);
		super.clickOnSubmit("Create");

		super.clickOnMenu("Company", "List Practica");
		super.checkListingExists();
		super.sortListing(0, "desc");
		super.checkColumnHasValue(practicaRecordIndex, 0, code);
		super.checkColumnHasValue(practicaRecordIndex, 1, title);
		super.checkColumnHasValue(practicaRecordIndex, 2, summary);
		super.clickOnListingRecord(practicaRecordIndex);

		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("summary", summary);
		super.checkInputBoxHasValue("goals", goals);
		super.checkInputBoxHasValue("estimatedTotalTime", estimatedTotalTime);
		super.checkInputBoxHasValue("course", course);

		super.clickOnButton("Session");
		super.checkListingExists();
		super.checkListingEmpty();

		super.signOut();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/company/practica/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void test200Negative(final int practicaRecordIndex, final String code, final String title, final String summary, final String goals, final String estimatedTotalTime, final String draftMode, final String course) {

		super.signIn("company1", "company1");

		super.clickOnMenu("Company", "List Practica");
		super.checkListingExists();
		super.sortListing(1, "asc");

		super.clickOnButton("Create");
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("summary", summary);
		super.fillInputBoxIn("goals", goals);
		super.fillInputBoxIn("estimatedTotalTime", estimatedTotalTime);
		super.fillInputBoxIn("course", course);
		super.clickOnSubmit("Create");
		super.checkErrorsExist();

		super.signOut();
	}
	//
	//	@Test
	//	public void test300Hacking() {
	//		// HINT: this test tries to create a tutorial using principals with
	//		// HINT: inappropriate roles.
	//
	//		super.checkLinkExists("Sign in");
	//		super.request("/company/practica/create");
	//		super.checkPanicExists();
	//
	//		super.signIn("administrator1", "administrator1");
	//		super.request("/company/practica/create");
	//		super.checkPanicExists();
	//		super.signOut();
	//
	//		super.signIn("student1", "student1");
	//		super.request("/company/practica/create");
	//		super.checkPanicExists();
	//		super.signOut();
	//	}
	//
	//	@Test
	//	public void test300Hacking() {
	//		// HINT: this test tries to create a lecture for a course as a principal without 
	//		// HINT: the "Lecturer" role.
	//
	//		Collection<Course> courses;
	//		String param;
	//
	//		courses = this.repository.findManyCoursesByCompanyUsername("company1");
	//		for (final Course course : courses) {
	//			param = String.format("masterId=%d", course.getId());
	//
	//			super.checkLinkExists("Sign in");
	//			super.request("/lecturer/lecture/create", param);
	//			super.checkPanicExists();
	//
	//			super.signIn("administrator1", "administrator1");
	//			super.request("/lecturer/lecture/create", param);
	//			super.checkPanicExists();
	//			super.signOut();
	//
	//			super.signIn("administrator2", "administrator2");
	//			super.request("/lecturer/lecture/create", param);
	//			super.checkPanicExists();
	//			super.signOut();
	//
	//			super.signIn("auditor1", "auditor1");
	//			super.request("/lecturer/lecture/create", param);
	//			super.checkPanicExists();
	//			super.signOut();
	//		}
	//	}
	//
	//	@Test
	//	public void test301Hacking() {
	//		// HINT: this test tries to create a lecture for a published course created by 
	//		// HINT+ the principal.
	//
	//		Collection<Course> courses;
	//		String param;
	//
	//		super.checkLinkExists("Sign in");
	//		super.signIn("lecturer2", "lecturer2");
	//		courses = this.repository.findManyCoursesByLecturerUsername("lecturer2");
	//		for (final Course course : courses)
	//			if (course.getPublish() == true) {
	//				param = String.format("masterId=%d", course.getId());
	//				super.request("/lecturer/lecture/create", param);
	//				super.checkPanicExists();
	//			}
	//	}
	//
	//	@Test
	//	public void test302Hacking() {
	//		// HINT: this test tries to create lectures for course that weren't created 
	//		// HINT+ by the principal.
	//
	//		Collection<Course> courses;
	//		String param;
	//
	//		super.checkLinkExists("Sign in");
	//		super.signIn("lecturer2", "lecturer2");
	//		courses = this.repository.findManyCoursesByLecturerUsername("lecturer1");
	//		for (final Course course : courses) {
	//			param = String.format("masterId=%d", course.getId());
	//			super.request("/lecturer/lecture/create", param);
	//			super.checkPanicExists();
	//		}
	//	}

}

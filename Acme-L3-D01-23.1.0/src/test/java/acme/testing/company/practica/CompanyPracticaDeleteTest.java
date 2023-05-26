
package acme.testing.company.practica;

public class CompanyPracticaDeleteTest {

	/*
	 * // Internal state ---------------------------------------------------------
	 * 
	 * @Autowired
	 * protected TutorialTestRepository repository;
	 * // Test methods ------------------------------------------------------------
	 * 
	 * 
	 * @ParameterizedTest
	 * 
	 * @CsvFileSource(resources = "/assistant/tutorial/delete-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	 * public void test100Positive(final int recordIndex, final String code, final String title, final String summary, final String goals, final String startMoment, final String finishMoment, final String course) {
	 * 
	 * super.signIn("assistant1", "assistant1");
	 * super.clickOnMenu("Assistant", "Tutorial list");
	 * super.sortListing(0, "asc");
	 * super.clickOnListingRecord(recordIndex);
	 * super.checkFormExists();
	 * 
	 * super.checkInputBoxHasValue("code", code);
	 * super.checkInputBoxHasValue("title", title);
	 * super.checkInputBoxHasValue("summary", summary);
	 * super.checkInputBoxHasValue("goals", goals);
	 * super.fillInputBoxIn("startMoment", startMoment);
	 * super.fillInputBoxIn("finishMoment", finishMoment);
	 * super.checkInputBoxHasValue("course", course);
	 * super.clickOnButton("Return");
	 * 
	 * super.checkListingExists();
	 * super.sortListing(0, "asc");
	 * super.clickOnListingRecord(recordIndex);
	 * super.checkFormExists();
	 * super.clickOnSubmit("Delete");
	 * super.checkNotErrorsExist();
	 * 
	 * super.signOut();
	 * }
	 * 
	 * @Test
	 * public void test300Hacking() {
	 * 
	 * Collection<Tutorial> tutorials;
	 * String param;
	 * 
	 * tutorials = this.repository.findManyTutorialByAssistantUsername("assistant1");
	 * for (final Tutorial tutorial : tutorials)
	 * if (!tutorial.getIsPublished() || tutorial.getIsPublished()) {
	 * param = String.format("id=%d", tutorial.getId());
	 * 
	 * super.checkLinkExists("Sign in");
	 * super.request("/assistant/tutorial/delete", param);
	 * super.checkPanicExists();
	 * 
	 * super.signIn("administrator1", "administrator1");
	 * super.request("/assistant/tutorial/delete", param);
	 * super.checkPanicExists();
	 * super.signOut();
	 * 
	 * super.signIn("lecturer2", "lecturer2");
	 * super.request("/assistant/tutorial/delete", param);
	 * super.checkPanicExists();
	 * super.signOut();
	 * 
	 * super.signIn("student1", "student1");
	 * super.request("/assistant/tutorial/delte", param);
	 * super.checkPanicExists();
	 * super.signOut();
	 * }
	 * }
	 */
}

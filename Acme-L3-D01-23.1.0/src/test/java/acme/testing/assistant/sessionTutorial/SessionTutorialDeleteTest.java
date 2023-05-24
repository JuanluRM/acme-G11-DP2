
package acme.testing.assistant.sessionTutorial;

import acme.testing.TestHarness;

public class SessionTutorialDeleteTest extends TestHarness {
	/*
	 * @Autowired
	 * protected SessionTutorialTestRepository repository;
	 * 
	 * 
	 * @ParameterizedTest
	 * 
	 * @CsvFileSource(resources = "/assistant/sessionTutorial/delete-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	 * public void test100Positive(final int tutorialRecordIndex, final String code, final int sessionRecordIndex, final String title, final String summary, final String sessionType, final String startDate, final String endDate, final String moreInfo) {
	 * 
	 * super.signIn("assistant1", "assistant1");
	 * 
	 * super.clickOnMenu("Assistant", "Tutorials");
	 * super.checkListingExists();
	 * super.sortListing(0, "asc");
	 * 
	 * super.checkColumnHasValue(tutorialRecordIndex, 0, code);
	 * super.clickOnListingRecord(tutorialRecordIndex);
	 * super.checkInputBoxHasValue("code", code);
	 * super.clickOnSubmit("Unpublish");
	 * 
	 * super.checkListingExists();
	 * super.sortListing(0, "asc");
	 * 
	 * super.checkColumnHasValue(tutorialRecordIndex, 0, code);
	 * super.clickOnListingRecord(tutorialRecordIndex);
	 * super.checkInputBoxHasValue("code", code);
	 * super.clickOnButton("Tutorial Session");
	 * 
	 * super.checkListingExists();
	 * super.checkColumnHasValue(sessionRecordIndex, 0, title);
	 * super.clickOnListingRecord(sessionRecordIndex);
	 * super.clickOnSubmit("Delete");
	 * 
	 * super.checkListingEmpty();
	 * 
	 * super.signOut();
	 * }
	 * 
	 * @Test
	 * public void test300Hacking() {
	 * 
	 * final Collection<Tutorial> tutorials;
	 * String param;
	 * Collection<SessionTutorial> sessions;
	 * 
	 * tutorials = this.repository.findManyTutorialByAssistantUsername("assistant1");
	 * for (final Tutorial tutorial : tutorials)
	 * if (!tutorial.getIsPublished() || tutorial.getIsPublished()) {
	 * sessions = this.repository.findManySessionsByTutorial(tutorial);
	 * for (final SessionTutorial session : sessions) {
	 * param = String.format("tutorialId=%d", session.getId());
	 * 
	 * super.checkLinkExists("Sign in");
	 * super.request("/assistant/tutorial-session/deletee", param);
	 * super.checkPanicExists();
	 * 
	 * super.signIn("administrator1", "administrator1");
	 * super.request("/assistant/tutorial-session/delete", param);
	 * super.checkPanicExists();
	 * super.signOut();
	 * 
	 * super.signIn("lecturer1", "lecturer1");
	 * super.request("/assistant/tutorial-session/delete", param);
	 * super.checkPanicExists();
	 * super.signOut();
	 * 
	 * }
	 * 
	 * }
	 * 
	 * }
	 */

}

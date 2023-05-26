
package acme.testing.assistant.sessionTutorial;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.SessionTutorial;
import acme.entities.Tutorial;
import acme.testing.TestHarness;

public class SessionTutorialDeleteTest extends TestHarness {

	@Autowired
	protected SessionTutorialTestRepository repository;


	@ParameterizedTest
	@CsvFileSource(resources = "/assistant/sessionTutorial/delete-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void test100Positive(final int tutorialRecordIndex, final int sessionRecordIndex, final String title, final String nextTitle) {

		super.signIn("assistant1", "assistant1");

		super.clickOnMenu("Assistant", "Tutorial list");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.clickOnListingRecord(tutorialRecordIndex);
		super.clickOnButton("Tutorial Sessions");

		super.checkListingExists();
		super.sortListing(0, "desc");
		super.clickOnListingRecord(sessionRecordIndex);
		super.checkFormExists();
		super.clickOnSubmit("Delete");
		super.checkNotErrorsExist();

		super.clickOnMenu("Assistant", "Tutorial list");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.clickOnListingRecord(tutorialRecordIndex);
		super.clickOnButton("Tutorial Sessions");

		super.checkListingExists();
		super.sortListing(0, "desc");
		super.checkColumnHasValue(sessionRecordIndex, 0, nextTitle);

		super.signOut();
	}

	@Test
	public void test200Negative() {
		// HINT: there aren't any negative tests for this feature 
	}

	@Test
	public void test300Hacking() {

		final Collection<Tutorial> tutorials;
		String param;
		Collection<SessionTutorial> sessions;

		tutorials = this.repository.findManyTutorialByAssistantUsername("assistant1");
		for (final Tutorial tutorial : tutorials)
			if (!tutorial.getIsPublished() || tutorial.getIsPublished()) {
				sessions = this.repository.findManySessionsByTutorial(tutorial);
				for (final SessionTutorial session : sessions) {
					param = String.format("tutorialId=%d", session.getId());

					super.checkLinkExists("Sign in");
					super.request("/assistant/tutorial-session/delete", param);
					super.checkPanicExists();

					super.signIn("administrator1", "administrator1");
					super.request("/assistant/tutorial-session/delete", param);
					super.checkPanicExists();
					super.signOut();

					super.signIn("lecturer1", "lecturer1");
					super.request("/assistant/tutorial-session/delete", param);
					super.checkPanicExists();
					super.signOut();

					super.signIn("assistant2", "assistant2");
					super.request("/assistant/tutorial-session/delete", param);
					super.checkPanicExists();
					super.signOut();

				}

			}

	}

}


package acme.testing.assistant.sessionTutorial;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.SessionTutorial;
import acme.entities.Tutorial;
import acme.testing.TestHarness;

public class SessionTutorialShowTest extends TestHarness {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected SessionTutorialTestRepository repository;

	// Test methods -----------------------------------------------------------


	@ParameterizedTest
	@CsvFileSource(resources = "/assistant/sessionTutorial/show-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void test100Positive(final int tutorialRecordIndex, final int sessionRecordIndex, final String title, final String sessionType, final String summary, final String startMoment, final String finishMoment, final String moreInfo) {

		super.signIn("assistant1", "assistant1");

		super.clickOnMenu("Assistant", "Tutorial list");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.clickOnListingRecord(tutorialRecordIndex);
		super.clickOnButton("Tutorial Sessions");

		super.checkListingExists();
		super.sortListing(0, "desc");
		super.clickOnListingRecord(sessionRecordIndex);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("tipoSesion", sessionType);
		super.checkInputBoxHasValue("summary", summary);
		super.checkInputBoxHasValue("startMoment", startMoment);
		super.checkInputBoxHasValue("finishMoment", finishMoment);
		super.checkInputBoxHasValue("moreInfo", moreInfo);

		super.signOut();
	}

	@Test
	public void test200Negative() {
		// HINT: there aren't any negative tests for this feature because it's a listing
		// HINT+ that doesn't involve entering any data in any forms.
	}

	@Test
	public void test300Hacking() {
		// HINT: this test tries to list all of the tutorials using 
		// HINT+ inappropriate roles.
		final Collection<Tutorial> tutorials;
		String param;
		Collection<SessionTutorial> sessions;

		tutorials = this.repository.findManyTutorialByAssistantUsername("assistant1");
		for (final Tutorial tutorial : tutorials)
			if (!tutorial.getIsPublished() || tutorial.getIsPublished()) {
				sessions = this.repository.findManySessionsByTutorial(tutorial);
				for (final SessionTutorial session : sessions) {
					param = String.format("id=%d", session.getId());

					super.checkLinkExists("Sign in");
					super.request("/assistant/session-tutorial/show", param);
					super.checkPanicExists();

					super.signIn("administrator1", "administrator1");
					super.request("/assistant/session-tutorial/show", param);
					super.checkPanicExists();
					super.signOut();

					super.signIn("lecturer1", "lecturer1");
					super.request("/assistant/session-tutorial/show", param);
					super.checkPanicExists();
					super.signOut();

					super.signIn("assistant2", "assistant2");
					super.request("/assistant/session-tutorial/show", param);
					super.checkPanicExists();
					super.signOut();

				}

			}

	}

}

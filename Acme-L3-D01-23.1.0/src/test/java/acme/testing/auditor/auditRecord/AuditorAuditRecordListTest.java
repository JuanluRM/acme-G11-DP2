/*
 * package acme.testing.auditor.auditRecord;
 * 
 * import org.junit.jupiter.params.ParameterizedTest;
 * import org.junit.jupiter.params.provider.CsvFileSource;
 * 
 * import acme.testing.TestHarness;
 * 
 * public class AuditorAuditRecordListTest extends TestHarness {
 * 
 * @ParameterizedTest
 * 
 * @CsvFileSource(resources = "/auditor/audit-record/list-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
 * public void test100Positive(final int masterIndex, final int recordIndex, final String correction, final String subject, final String assessment) {
 * 
 * super.signIn("auditor1", "auditor1");
 * 
 * super.clickOnMenu("Auditor", "List my audits");
 * super.checkListingExists();
 * super.sortListing(0, "asc");
 * super.clickOnListingRecord(masterIndex);
 * super.clickOnButton("Audit Record");
 * super.checkListingExists();
 * super.sortListing(0, "asc");
 * 
 * super.checkColumnHasValue(recordIndex, 0, correction);
 * super.checkColumnHasValue(recordIndex, 1, subject);
 * super.checkColumnHasValue(recordIndex, 2, assessment);
 * 
 * super.signOut();
 * }
 * 
 * 
 * @Test
 * public void test200Negative() {
 * // HINT: there aren't any negative tests for this feature because it's a listing
 * // HINT+ that doesn't involve entering any data in any forms.
 * }
 * 
 * @Test
 * public void test300Hacking() {
 * super.checkLinkExists("Sign in");
 * super.request("/auditor/audit-record/list");
 * super.checkPanicExists();
 * 
 * super.checkLinkExists("Sign in");
 * super.signIn("administrator1", "administrator1");
 * super.request("/auditor/audit-record/list");
 * super.checkPanicExists();
 * super.signOut();
 * 
 * super.checkLinkExists("Sign in");
 * super.signIn("administrator2", "administrator2");
 * super.request("/auditor/audit-record/list");
 * super.checkPanicExists();
 * super.signOut();
 * 
 * super.checkLinkExists("Sign in");
 * super.signIn("lecturer1", "lecturer1");
 * super.request("/auditor/audit-record/list");
 * super.checkPanicExists();
 * super.signOut();
 * }
 * 
 * 
 * }
 */

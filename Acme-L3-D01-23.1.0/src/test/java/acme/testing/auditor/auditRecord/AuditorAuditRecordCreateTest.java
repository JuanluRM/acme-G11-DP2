/*
 * package acme.testing.auditor.auditRecord;
 * 
 * import org.junit.jupiter.api.Timeout;
 * import org.junit.jupiter.params.ParameterizedTest;
 * import org.junit.jupiter.params.provider.CsvFileSource;
 * 
 * import acme.testing.TestHarness;
 * 
 * public class AuditorAuditRecordCreateTest extends TestHarness {
 * 
 * // Test methods -----------------------------------------------------------
 * 
 * @ParameterizedTest
 * 
 * @CsvFileSource(resources = "/auditor/audit-record/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
 * 
 * @Timeout(value = 50000)
 * public void test100Positive(final int masterIndex, final int recordIndex, final String subject, final String assessment, final String startAudition, final String endAudition, final String mark, final String link) {
 * 
 * super.signIn("auditor1", "auditor1");
 * 
 * super.clickOnMenu("Auditor", "List my audits");
 * super.checkListingExists();
 * super.sortListing(0, "asc");
 * super.clickOnListingRecord(masterIndex);
 * super.clickOnButton("Audit Record");
 * super.checkListingExists();
 * super.clickOnButton("New Auditing Record");
 * super.checkFormExists();
 * 
 * super.fillInputBoxIn("subject", subject);
 * super.fillInputBoxIn("assessment", assessment);
 * super.fillInputBoxIn("startAudition", startAudition);
 * super.fillInputBoxIn("endAudition", endAudition);
 * super.fillInputBoxIn("mark", mark);
 * super.fillInputBoxIn("link", link);
 * super.clickOnSubmit("Create");
 * 
 * super.checkColumnHasValue(recordIndex, 0, subject);
 * 
 * super.clickOnListingRecord(recordIndex);
 * super.checkFormExists();
 * 
 * super.checkInputBoxHasValue("subject", subject);
 * super.checkInputBoxHasValue("assessment", assessment);
 * super.checkInputBoxHasValue("startAudition", startAudition);
 * super.checkInputBoxHasValue("endAudition", endAudition);
 * super.checkInputBoxHasValue("mark", mark);
 * super.checkInputBoxHasValue("link", link);
 * 
 * super.signOut();
 * }
 * 
 * 
 * @ParameterizedTest
 * 
 * @CsvFileSource(resources = "/auditor/audit-record/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
 * public void test200Negative(final int recordIndex, final String subject, final String assessment, final String startAudition, final String endAudition, final String mark, final String link, final String draftMode, final String correction) {
 * 
 * super.signIn("auditor1", "auditor1");
 * 
 * super.clickOnMenu("Auditor", "List my audits");
 * super.clickOnButton("Create");
 * super.checkFormExists();
 * 
 * super.fillInputBoxIn("subject", subject);
 * super.fillInputBoxIn("assessment", assessment);
 * super.fillInputBoxIn("startAudition", startAudition);
 * super.fillInputBoxIn("endAudition", endAudition);
 * super.fillInputBoxIn("mark", mark);
 * super.fillInputBoxIn("link", link);
 * super.fillInputBoxIn("draftMode", draftMode);
 * super.fillInputBoxIn("correction", correction);
 * 
 * super.clickOnSubmit("Create");
 * 
 * super.checkErrorsExist();
 * 
 * super.signOut();
 * }
 * 
 * @Test
 * public void test300Hacking() {
 * 
 * super.checkLinkExists("Sign in");
 * super.request("/auditor/audit-record/create");
 * super.checkPanicExists();
 * 
 * super.signIn("administrator1", "administrator1");
 * super.request("/auditor/audit-record/create");
 * super.checkPanicExists();
 * super.signOut();
 * 
 * super.signIn("administrator2", "administrator2");
 * super.request("/auditor/audit-record/create");
 * super.checkPanicExists();
 * super.signOut();
 * 
 * super.signIn("auditor1", "auditor1");
 * super.request("/auditor/audit-record/create");
 * super.checkPanicExists();
 * super.signOut();
 * }
 * 
 * }
 */

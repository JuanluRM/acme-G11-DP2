/*
 * package acme.testing.auditor.auditRecord;
 * 
 * import java.util.Collection;
 * 
 * import org.junit.jupiter.api.Test;
 * import org.junit.jupiter.params.ParameterizedTest;
 * import org.junit.jupiter.params.provider.CsvFileSource;
 * import org.springframework.beans.factory.annotation.Autowired;
 * 
 * import acme.entities.Audit;
 * import acme.testing.TestHarness;
 * import acme.testing.auditor.audit.AuditorAuditTestRepository;
 * 
 * public class AuditorAuditRecordUpdateTest extends TestHarness {
 * 
 * @Autowired
 * protected AuditorAuditTestRepository repository;
 * 
 * 
 * @ParameterizedTest
 * 
 * @CsvFileSource(resources = "/auditor/audit-record/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
 * public void test100Positive(final int masterIndex, final int recordIndex, final String subject, final String assessment, final String startAudition, final String endAudition, final String mark, final String link) {
 * 
 * super.signIn("auditor1", "auditor1");
 * 
 * super.clickOnMenu("Auditor", "List my audits");
 * super.checkListingExists();
 * super.sortListing(0, "asc");
 * super.clickOnListingRecord(masterIndex);
 * super.clickOnButton("New Auditing Record");
 * super.checkListingExists();
 * super.clickOnListingRecord(recordIndex);
 * super.checkFormExists();
 * 
 * super.fillInputBoxIn("subject", subject);
 * super.fillInputBoxIn("assessment", assessment);
 * super.fillInputBoxIn("startDate", startAudition);
 * super.fillInputBoxIn("finishDate", endAudition);
 * super.fillInputBoxIn("mark", mark);
 * super.fillInputBoxIn("link", link);
 * super.clickOnSubmit("Update");
 * 
 * super.checkColumnHasValue(recordIndex, 0, subject);
 * 
 * super.checkFormExists();
 * 
 * super.checkInputBoxHasValue("subject", subject);
 * super.checkInputBoxHasValue("assessment", assessment);
 * super.checkInputBoxHasValue("startDate", startAudition);
 * super.checkInputBoxHasValue("finishDate", endAudition);
 * super.checkInputBoxHasValue("mark", mark);
 * super.checkInputBoxHasValue("link", link);
 * 
 * super.signOut();
 * }
 * 
 * @ParameterizedTest
 * 
 * @CsvFileSource(resources = "/auditor/audit-record/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
 * public void test200Negative(final int masterIndex, final int recordIndex, final String subject, final String assessment, final String startAudition, final String endAudition, final String mark, final String link) {
 * 
 * super.signIn("auditor1", "auditor1");
 * 
 * super.clickOnMenu("Auditor", "List my audits");
 * super.checkListingExists();
 * super.sortListing(0, "asc");
 * super.clickOnListingRecord(masterIndex);
 * super.clickOnButton("New Auditing Record");
 * super.checkListingExists();
 * super.clickOnListingRecord(recordIndex);
 * super.checkFormExists();
 * 
 * super.fillInputBoxIn("subject", subject);
 * super.fillInputBoxIn("assessment", assessment);
 * super.fillInputBoxIn("startDate", startAudition);
 * super.fillInputBoxIn("finishDate", endAudition);
 * super.fillInputBoxIn("link", link);
 * super.fillInputBoxIn("mark", mark);
 * super.clickOnSubmit("Update");
 * 
 * super.checkErrorsExist();
 * 
 * super.signOut();
 * }
 * 
 * @Test
 * public void test300Hacking() {
 * Collection<Audit> audits;
 * String param;
 * 
 * audits = this.repository.findManyAuditByAuditorUsername("auditor1");
 * for (final Audit audit : audits) {
 * param = String.format("id=%d", audit.getId());
 * 
 * super.checkLinkExists("Sign in");
 * super.request("/auditor/audit-record/update", param);
 * super.checkPanicExists();
 * 
 * super.signIn("administrator1", "administrator1");
 * super.request("/auditor/audit-record/update", param);
 * super.checkPanicExists();
 * super.signOut();
 * 
 * super.signIn("administrator2", "administrator2");
 * super.request("/auditor/audit-record/update", param);
 * super.checkPanicExists();
 * super.signOut();
 * 
 * super.signIn("lecturer2", "lecturer2");
 * super.request("/auditor/audit-record/update", param);
 * super.checkPanicExists();
 * super.signOut();
 * 
 * super.signIn("auditor1", "auditor1");
 * super.request("/auditor/audit-record/update", param);
 * super.checkPanicExists();
 * super.signOut();
 * }
 * }
 * 
 * }
 */

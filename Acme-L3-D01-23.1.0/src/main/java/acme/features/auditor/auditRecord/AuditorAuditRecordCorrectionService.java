/*
 * package acme.features.auditor.auditRecord;
 * 
 * import java.time.Instant;
 * import java.util.Date;
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.stereotype.Service;
 * 
 * import acme.entities.Audit;
 * import acme.entities.AuditRecord;
 * import acme.framework.components.models.Tuple;
 * import acme.framework.services.AbstractService;
 * import acme.roles.Auditor;
 * 
 * @Service
 * public class AuditorAuditRecordCorrectionService extends AbstractService<Auditor, AuditRecord> {
 * 
 * @Autowired
 * protected AuditorAuditRecordRepository repository;
 * 
 * 
 * @Override
 * public void check() {
 * boolean status;
 * 
 * status = super.getRequest().hasData("id", int.class);
 * 
 * super.getResponse().setChecked(status);
 * }
 * 
 * @Override
 * public void authorise() {
 * boolean status;
 * int auditRecordId;
 * Audit audit;
 * 
 * auditRecordId = super.getRequest().getData("id", int.class);
 * audit = this.repository.findAuditById(auditRecordId);
 * status = audit != null && super.getRequest().getPrincipal().hasRole(audit.getAuditor());
 * 
 * super.getResponse().setAuthorised(status);
 * }
 * 
 * @Override
 * public void load() {
 * AuditRecord object;
 * int auditId;
 * Audit audit;
 * 
 * auditId = super.getRequest().getData("id", int.class);
 * audit = this.repository.findAuditById(auditId);
 * 
 * object = new AuditRecord();
 * object.setAudit(audit);
 * 
 * super.getBuffer().setData(object);
 * }
 * 
 * @Override
 * public void bind(final AuditRecord object) {
 * assert object != null;
 * 
 * super.bind(object, "subject", "assessment", "mark", "startAudition", "endAudition", "link");
 * }
 * 
 * @Override
 * public void validate(final AuditRecord object) {
 * final Boolean confirm = super.getRequest().getData("confirm", boolean.class);
 * super.state(confirm, "*", "auditor.auditingrecord.correction.confirmation");
 * assert object != null;
 * if (!super.getBuffer().getErrors().hasErrors("startDate"))
 * super.state(object.getStartAudition().before(object.getEndAudition()), "endAudition", "auditor.audit.form.error.start-before-end");
 * 
 * if (!super.getBuffer().getErrors().hasErrors("startAudition"))
 * super.state(object.getStartAudition().before(Date.from(Instant.now())), "startAudition", "auditor.audit.form.error.start-before-end");
 * 
 * if (!super.getBuffer().getErrors().hasErrors("endAudition"))
 * super.state(object.getEndAudition().before(Date.from(Instant.now())), "endAudition", "auditor.audit.form.error.start-before-end");
 * 
 * if (!super.getBuffer().getErrors().hasErrors("endAudition"))
 * super.state(object.period() > 1.0, "startDate", "auditor.audit.form.error.least-one-week-ahead");
 * 
 * }
 * 
 * @Override
 * public void perform(final AuditRecord object) {
 * assert object != null;
 * 
 * this.repository.save(object);
 * }
 * 
 * @Override
 * public void unbind(final AuditRecord object) {
 * assert object != null;
 * 
 * Tuple tuple;
 * 
 * tuple = super.unbind(object, "subject", "assessment", "mark", "startAudition", "endAudition", "link");
 * tuple.put("id", super.getRequest().getData("id", int.class));
 * 
 * super.getResponse().setData(tuple);
 * }
 * 
 * }
 */

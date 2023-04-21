<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="auditor.auditRecord.form.label.subject" path="subject"/>
	<acme:input-textbox code="auditor.auditRecord.form.label.assessment" path="assessment"/>
	<acme:input-moment code="auditor.auditRecord.form.label.startAudition" path="startAudition" />
	<acme:input-moment code="auditor.auditRecord.form.label.endAudition" path="endAudition"/>
	<acme:input-textbox code="auditor.auditRecord.form.label.mark" path="mark"/>
	<acme:input-url code="auditor.auditRecord.form.label.link" path="link"/>
	
	<jstl:choose>
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete') && draftMode == true}">
			<acme:submit code="auditor.auditRecord.form.button.update" action="/auditor/audit-record/update"/>
			<acme:submit code="auditor.auditRecord.form.button.delete" action="/auditor/audit-record/delete"/>
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="auditor.auditRecord.form.button.create" action="/auditor/audit-record/create?id=${id}"/>
		</jstl:when>		
	</jstl:choose>		
</acme:form>
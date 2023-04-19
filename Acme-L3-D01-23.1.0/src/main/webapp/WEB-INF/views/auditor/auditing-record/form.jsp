<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="auditor.auditingRecord.form.label.subject" path="subject"/>
	<acme:input-textbox code="auditor.auditingRecord.form.label.assessment" path="assessment"/>
	<acme:input-moment code="auditor.auditingRecord.form.label.startAudition" path="startAudition" />
	<acme:input-moment code="auditor.auditingRecord.form.label.endAudition" path="endAudition"/>
	<acme:input-textbox code="auditor.auditingRecord.form.label.mark" path="mark"/>
	<acme:input-url code="auditor.auditingRecord.form.label.link" path="link"/>
	
	<jstl:choose>
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete') && draftMode == true}">
			<acme:submit code="auditor.auditingRecord.form.button.update" action="/auditor/auditing-record/update"/>
			<acme:submit code="auditor.auditingRecord.form.button.delete" action="/auditor/auditing-record/delete"/>
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="auditor.auditingRecord.form.button.create" action="/auditor/auditing-record/create?masterId=${masterId}"/>
		</jstl:when>		
	</jstl:choose>		
</acme:form>
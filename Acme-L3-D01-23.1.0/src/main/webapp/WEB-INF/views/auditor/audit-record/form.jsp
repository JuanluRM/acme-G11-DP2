<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>


<%-- <jstl:if test="${correction != null && correction == true}"> --%>
<!-- 	<div style="margin-bottom: 3%;"> -->
<!-- 		<h2> -->
<%-- 			<acme:message code="auditor.auditrecord.message.correction"/> --%>
<!-- 		</h2> -->
<!-- 	</div> -->
<%-- </jstl:if> --%>




<acme:form>
	<acme:input-textbox code="auditor.auditrecord.form.label.subject" path="subject"/>
	<acme:input-textbox code="auditor.auditrecord.form.label.assessment" path="assessment"/>
	<acme:input-moment code="auditor.auditrecord.form.label.starAudition" path="startAudition"/>
	<acme:input-moment code="auditor.auditrecord.form.label.endAudition" path="endAudition"/>
	<acme:input-textbox code="auditor.auditrecord.form.label.mark" path="mark"/>
	<acme:input-url code="auditor.auditrecord.form.label.link" path="link"/>
	
<%-- 	<jstl:if test="${_command == 'correct'}"> --%>
<%-- 		<acme:message code="auditor.auditrecord.message.correction.disclaimer"/> --%>
<%-- 		<acme:input-checkbox code="auditor.auditrecord.label.confirm" path="confirm"/> --%>
<%-- 	</jstl:if> --%>
	
	<acme:hidden-data path="draftMode"/>
	

	<jstl:choose>	 
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete|publish') && draftMode == true}">
			<acme:submit code="auditor.auditrecord.form.button.update" action="/auditor/audit-record/update"/>
			<acme:submit code="auditor.auditrecord.form.button.delete" action="/auditor/audit-record/delete"/>
			<acme:submit code="auditor.auditrecord.form.button.publish" action="/auditor/audit-record/publish"/>
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="auditor.auditrecord.form.button.create" action="/auditor/audit-record/create?id=${id}"/>
		</jstl:when>
		
<%-- 		<jstl:when test="${_command == 'correct' }"> --%>
<%-- 				<acme:submit code="auditor.auditrecord.form.button.correct" action="/auditor/audit-record/correct?id=${id}"/> --%>
			
<%-- 		</jstl:when>	 --%>
			
	</jstl:choose>	
</acme:form>
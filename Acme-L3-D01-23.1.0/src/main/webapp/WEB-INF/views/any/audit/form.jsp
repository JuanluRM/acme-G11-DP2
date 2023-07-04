<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="any.audit.form.label.code" path="code"/>
	<acme:input-textbox code="any.audit.form.label.conclusion" path="conclusion"/>
	<acme:input-textbox code="any.audit.form.label.strongPoints" path="strongPoints"/>
	<acme:input-textbox code="any.audit.form.label.weakPoints" path="weakPoints"/>
	<acme:input-select code="any.audit.form.label.auditor" path="auditor" choices ="${auditors}"/>
	<acme:input-select code="any.audit.form.label.course" path="course" choices ="${courses}"/>
	<acme:input-checkbox code="any.audit.form.label.draftMode" path="draftMode" readonly="true"/>
</acme:form>

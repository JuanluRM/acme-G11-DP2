<%--
- form.jsp
-
- Copyright (C) 2012-2023 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="company.practica.list.label.code" path="code"/>
	<acme:input-textbox code="company.practica.list.label.title" path="title"/>
	<acme:input-textbox code="company.practica.list.label.summary" path="summary"/>
	<acme:input-textbox code="company.practica.list.label.goals" path="goals"/>
	<acme:input-textbox code="company.practica.list.label.estimatedTotalTime" path="estimatedTotalTime"/>
	<acme:input-select code="company.practica.list.label.course" path="course" choices="${courses}"/>

	<jstl:choose>     
    <jstl:when test="${_command == 'show' && published == true}">
        <acme:button code="company.practica.form.button.session" action="/company/session/list?practicaId=${id}"/>            
    </jstl:when>
    <jstl:when test="${acme:anyOf(_command, 'show|update|delete|publish') && published == false}">
        <acme:button code="company.practica.form.button.session" action="/company/session/list?practicaId=${id}"/>  
		<acme:submit code="company.practica.form.button.update" action="/company/practica/update"/>
        <acme:submit code="company.practica.form.button.delete" action="/company/practica/delete"/>
		<acme:submit code="company.practica.form.button.publish" action="/company/practica/publish"/>
    </jstl:when>
    <jstl:when test="${_command == 'create'}">
        <acme:submit code="company.practica.form.button.create" action="/company/practica/create"/>
    </jstl:when>        
</jstl:choose>
	
</acme:form>




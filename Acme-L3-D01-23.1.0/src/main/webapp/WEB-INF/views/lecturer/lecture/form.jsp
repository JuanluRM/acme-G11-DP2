<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="lecturer.lecture.form.label.title" path="title"/>
	<acme:input-textbox code="lecturer.lecture.form.label.lectureAbstract" path="lectureAbstract"/>
	<acme:input-double code="lecturer.lecture.form.label.estimatedLearningTime" path="estimatedLearningTime"/>
	<acme:input-textarea code="lecturer.lecture.form.label.body" path="body"/>
	
	<jstl:if test="${publish == true}">
	<acme:input-textbox code="lecturer.lecture.form.label.type" path="type" readonly="true"/>
	</jstl:if>
	<jstl:if test="${publish == false}">
	<acme:input-select code="lecturer.lecture.form.label.type" path="type" choices="${lectureTypes}"/>
	</jstl:if> 
	<acme:input-checkbox code="lecturer.lecture.form.label.publish" path="publish" readonly="true"/>
	<acme:input-url code="lecturer.lecture.form.label.link" path="link"/>
	
	<jstl:choose>
	
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete|publish') && publish == false}">
			<acme:submit code="lecturer.lecture.form.button.update" action="/lecturer/lecture/update"/>
			<acme:submit code="lecturer.lecture.form.button.delete" action="/lecturer/lecture/delete"/>
			<acme:submit code="lecturer.lecture.form.button.publish" action="/lecturer/lecture/publish"/>		
		</jstl:when>
		
		<jstl:when test="${_command == 'create' && not empty masterId}">
			<acme:submit code="lecturer.lecture.form.button.create" action="/lecturer/lecture/create?masterId=${masterId}"/>
		</jstl:when>
		
		<jstl:when test="${_command == 'create' && empty masterId}">
			<acme:submit code="lecturer.lecture.form.button.create" action="/lecturer/lecture/create"/>
		</jstl:when>
				
	</jstl:choose>		
	
</acme:form>

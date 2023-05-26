<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:form>

	<jstl:if test="${_command=='create-courseLecture'}">
		<acme:input-select code="lecturer.course-lecture.form.label.lecture" path="lecture" choices="${lectureChoices}"/>
		<acme:submit code="lecturer.course-lecture.form.button.add" action="/lecturer/course-lecture/create-courseLecture?courseId=${courseId}"/>
	</jstl:if>
	
</acme:form>

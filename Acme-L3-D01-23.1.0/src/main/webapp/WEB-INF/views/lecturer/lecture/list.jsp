<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:list>
	<acme:list-column code="lecturer.lecture.list.label.title" path="title" width="20%"/>
	<acme:list-column code="lecturer.lecture.list.label.lectureAbstract" path="lectureAbstract" width="20%"/>
	<acme:list-column code="lecturer.lecture.list.label.type" path="type" width="10%"/>
	<acme:list-column code="lecturer.lecture.list.label.publish" path="publish" width="10%"/>
</acme:list>

<acme:button test="${showCreate}" code="lecturer.lecture.list.button.create" 
action="/lecturer/lecture/create?courseId=${courseId}"/>



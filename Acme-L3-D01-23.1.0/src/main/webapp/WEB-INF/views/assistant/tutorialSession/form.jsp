<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:form>
  <acme:input-textbox code="assistant.tutorialSession.form.label.title" path="title"/>
  <acme:input-select code="assistant.tutorialSession.form.label.type" path="tipoSesion" choices="${types}"/>
  <acme:input-textarea code="assistant.tutorialSession.form.label.summary" path="summary"/>
  <acme:input-moment code="assistant.tutorialSession.form.label.startMoment" path="startMoment"/>
  <acme:input-moment code="assistant.tutorialSession.form.label.finishMoment" path="finishMoment"/>
  <acme:input-url code="assistant.tutorialSession.form.label.moreInfo"path="moreInfo"/>

  <jstl:choose>
    <jstl:when test="${acme:anyOf(_command, 'show|update|publish') && isPublish == false}">
      <acme:submit code="assistant.tutorialSession.form.button.update" action="/assistant/tutorialSession/update" />
      <acme:submit code="assistant.tutorialSession.form.button.publish" action="/assistant/tutorialSession/publish"/>
      <acme:submit code="assistant.tutorialSession.form.button.delete" action="/assistant/tutorialSession/delete"/>
    </jstl:when>
    <jstl:when test="${_command == 'create'}">
      <acme:submit code="assistant.tutorialSession.form.button.create" action="/assistant/tutorialSession/create?masterId=${masterId}"/>
    </jstl:when>
  </jstl:choose>
</acme:form>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:form>
  <acme:input-textbox code="assistant.session-tutorial.form.label.title" path="title"/>
  <acme:input-select code="assistant.session-tutorial.form.label.type" path="tipoSesion" choices="${types}"/>
  <acme:input-textarea code="assistant.session-tutorial.form.label.summary" path="summary"/>
  <acme:input-moment code="assistant.session-tutorial.form.label.startMoment" path="startMoment"/>
  <acme:input-moment code="assistant.session-tutorial.form.label.finishMoment" path="finishMoment"/>
  <acme:input-url code="assistant.session-tutorial.form.label.moreInfo" path="moreInfo"/>

  <jstl:choose>
    <jstl:when test="${acme:anyOf(_command, 'show|update|publish') && isPublished == false}">
      <acme:submit code="assistant.session-tutorial.form.button.update" action="/assistant/session-tutorial/update" />
      <acme:submit code="assistant.session-tutorial.form.button.publish" action="/assistant/session-tutorial/publish"/>
      <acme:submit code="assistant.session-tutorial.form.button.delete" action="/assistant/session-tutorial/delete"/>
    </jstl:when>
    <jstl:when test="${_command == 'create'}">
      <acme:submit code="assistant.session-tutorial.form.button.create" action="/assistant/session-tutorial/create?masterId=${masterId}"/>
    </jstl:when>
  </jstl:choose>
</acme:form>

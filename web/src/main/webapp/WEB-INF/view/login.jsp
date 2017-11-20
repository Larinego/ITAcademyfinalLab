<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>


<c:set var="context" value="${pageContext.request.contextPath}"/>
<spring:message code="secondDomain" var="secondDomain"/>

<spring:message code="registration.login.error" var="loginError"/>
<spring:message code="registration.password.error" var="passwordError"/>
<spring:message code="registration.name.error" var="nameError"/>
<spring:message code="registration.surname.error" var="surnameError"/>
<spring:message code="registration.email.error" var="emailError"/>
<spring:message code="registration.phoneNumber.error" var="phoneNumberError"/>

<spring:message code="login.incorrectLoginOrPassword" var="incorrectLoginOrPassword"/>


<div class="container">
    <div class="row">
        <c:url var="loginUrl" value="/login"/>
        <form action="${loginUrl}" method="post" class="form">
            <div class="col-xs-12">
                <div class="form-group">

                    <label class="input-group-addon" for="username"><i class="fa fa-user"></i> </label>
                    <input type="text" class="form-control" id="username" name="username" placeholder="Enter Username"
                           required="required"/>
                </div>
            </div>
            <div class="col-xs-12">
                <div class="form-group">

                    <label class="input-group-addon" for="password"><i class="fa fa-lock"></i> </label>
                    <input type="password" class="form-control" id="password" name="password"
                           placeholder="Enter Password" required="required"/>
                </div>
            </div>

            <input type="submit" class="btn btn-block btn-primary" value="Log in">

        </form>
        <p class="alert-warning"><c:if test="${not empty loginFailure}">${incorrectLoginOrPassword}</c:if></p>
    </div>
</div>




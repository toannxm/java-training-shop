<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%><%@ page language="java" contentType="text/html; charset=UTF-8"         pageEncoding="UTF-8"%><%@page session="true"%><!-- Bootstrap core CSS --><link href="${contextPath}/resource/view/admin/themes/css/bootstrap.min.css" rel="stylesheet"><link href="${contextPath}/resource/view/admin/themes/css/bootstrap-reset.css" rel="stylesheet"><!-- Custom styles for this template --><link href="${contextPath}/resource/view/admin/themes/css/style.css" rel="stylesheet"><link href="${contextPath}/resource/view/admin/themes/css/style-responsive.css" rel="stylesheet"/><c:url value="/j_spring_security_check" var="loginUrl" /><c:if test="${not empty error}"><div>${error}</div></c:if><c:if test="${not empty message}"><div>${message}</div></c:if><form class="form-signin" id="login" action="${loginUrl }" method="post">    <h2 class="form-signin-heading">sign in now</h2>    <div class="login-wrap">        <input name="username" type="text" class="form-control" placeholder="User ID" autofocus>        <input name="password" type="password" class="form-control" placeholder="Password">        <label class="checkbox">            <input type="checkbox" value="remember-me"> Remember me            <span class="pull-right">                    <a data-toggle="modal" href="#myModal"> Forgot Password?</a>                </span>        </label>        <button class="btn btn-lg btn-login btn-block" type="submit">Sign in</button>        <div class="registration">            Don't have an account yet?            <a class="" href="/registration">                Create an account            </a>        </div>    </div>    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /></form>
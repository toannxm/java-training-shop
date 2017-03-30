<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><%@ page language="java" contentType="text/html; charset=UTF-8"         pageEncoding="UTF-8" %><%@page session="true" %><c:set var="contextPath" value="${pageContext.request.contextPath}"/><!--main content start--><style>    .error {        color: #ff0000;        font-style: italic;        font-weight: bold;    }</style><section id="container" class="">    <section id="main-content">        <section class="wrapper">            <!-- page start-->            <section class="panel">                <header class="panel-heading"> Invoice </header>                <div class="panel-body">                    <div class="adv-table">                        <table cellpadding="0" cellspacing="0" border="0" class="display table table-bordered"                               id="hidden-table-info">                            <thead>                            <tr>                                <th>Username</th>                                <th>Email</th>                                <th>Phone</th>                                <th>First Name</th>                                <th>Last Name</th>                                <th>Address</th>                                <th>City</th>                                <th>Status</th>                                <th>Invoice Date</th>                                <th>Details</th>                                <th>Edit</th>                                <th>Delete</th>                            </tr>                            </thead>                            <tbody>                            <c:forEach items="${invoices }" var="invoice">                                <tr class="gradeX">                                    <td>${invoice.username }</td>                                    <td>${invoice.email }</td>                                    <td>${invoice.phone }</td>                                    <td>${invoice.firstName }</td>                                    <td>${invoice.lastName }</td>                                    <td>${invoice.address }</td>                                    <td>${invoice.city }</td>                                    <td>${invoice.status }</td>                                    <td>${invoice.invoiceDate }</td>                                    <td><a class="edit"                                           href="${contextPath }/admin/invoiceDetail?id=${invoice.id}">Details</a></td>                                    <td><a class="edit" href="${contextPath }/admin/editInvoice?id=${invoice.id}">Edit</a></td>                                    <td><a class="delete" onclick="deleteInvoice()"                                           href="${contextPath }/admin/deleteInvoice?id=${invoice.id}">Delete</a></td>                                </tr>                            </c:forEach>                            </tbody>                        </table>                    </div>                </div>            </section>            <div class="row">                <div class="col-lg-12">                    <section class="panel">                        <header class="panel-heading">                            Edit Invoice                        </header>                        <div class="panel-body">                            <div class="form">                                <form class="cmxform form-horizontal tasi-form" id="signupForm" method="POST"                                      commandName="invoice" action="/admin/updateInvoice">                                    <div class="form-group ">                                        <label for="username" class="control-label col-lg-2">ID</label>                                        <div class="col-lg-10">                                            <input class="form-control" id="id" name="id" type="text"                                                   value="${invoice.id}"/>                                        </div>                                    </div>                                    <div class="form-group ">                                        <label for="username" class="control-label col-lg-2">Username</label>                                        <div class="col-lg-10">                                            <input class="form-control" id="username" name="username" type="text"                                                   value="${invoice.username}"/>                                        </div>                                    </div>                                    <div class="form-group ">                                        <label class="control-label col-lg-2">Status</label>                                        <c:forEach items="${listStatus}" var="status">                                            <label class="radio-inline">                                                <input type="radio" value="${status}"                                                       name="rbStatus">${status}</label>                                        </c:forEach>                                    </div>                                    <div class="form-group">                                        <div class="col-lg-offset-2 col-lg-10">                                            <button class="btn btn-danger" type="submit">Save</button>                                            <button class="btn btn-default" type="button">Cancel</button>                                        </div>                                    </div>                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>                                </form>                            </div>                        </div>                    </section>                </div>            </div>            <!-- page end-->        </section>    </section>    <!--main content end--></section><script>    function deleteInvoice() {        confirm('Are you want to remove this invoice ?')    }</script>
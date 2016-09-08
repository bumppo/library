<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<link rel="stylesheet" href="webjars/datatables/1.10.9/css/jquery.dataTables.min.css">

<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<br/>
<div class="jumbotron">
    <div class="container">
        <div class="row row-offcanvas row-offcanvas-right">
            <div class="col-xs-6  col-sm-3 sidebar-offcanvas" id="sidebar">
                <div class="list-group">
                    <a href="/library/books" class="list-group-item">Books</a>
                    <a href="/library/users" class="list-group-item">Users</a>
                </div>
            </div><!--/.sidebar-offcanvas-->
            <div class=" col-xs-12 col-sm-9 shadow">
                <div class="view-box">
                    <a class="btn btn-md btn-info" id="add">Add user</a>
                    <table class="table table-striped display" id="datatable">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <th></th>
                            </sec:authorize>
                            <sec:authorize access="hasRole('ROLE_USER')">
                                <th id="hideColumn"></th>
                            </sec:authorize>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </div><!--/row-->
    </div>
</div>

<div class="modal fade" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title" id="modalTitle"></h2>
            </div>
            <div class="modal-body">
                <form:form class="form-horizontal" method="post" id="detailsForm">
                    <input type="text" hidden="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="name" class="control-label col-xs-3">Name</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="name" name="name" placeholder="Name">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="password" class="control-label col-xs-3">Password</label>

                        <div class="col-xs-9">
                            <input type="password" class="form-control" id="password" name="password" placeholder="Password">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button type="submit" class="btn btn-primary">Save</button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="webjars/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" src="webjars/datatables/1.10.9/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="webjars/noty/2.3.7/js/noty/packaged/jquery.noty.packaged.min.js"></script>
<script type="text/javascript" src="resources/js/datatablesUtil.js"></script>
<script type="text/javascript" src="resources/js/userDatatables.js"></script>
</html>

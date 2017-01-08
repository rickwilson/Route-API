<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Route Open API - Getting Started</title>
    <jsp:include page="/WEB-INF/views/docs/header-include.jsp" flush="false" />
</head>
<body style="background-color: #f6f9fc; background-image: url('https://d1pv9ulu41r3n5.cloudfront.net/img/bluebg6.png'); background-repeat:no-repeat;">
<jsp:include page="/WEB-INF/views/docs/sideMenu.jsp" flush="true" >
    <jsp:param name="page" value="1.1" />
</jsp:include>
<div class="container-fluid myTestContainer">
    <div class="row">
        <div class="hidden-xs col-sm-12">
            <h1>&nbsp;</h1>
        </div>
    </div>
    <h1 class="text-center">Route API <i class="fa fa-cog fa-spin fa-fw"></i></h1>
    <div class="row">
        <div class="col-sm-12"><strong>
            The Route API is built around industry standard REST. Our API has predictable, resource-oriented URLs, and uses
                intuitive human readable API errors. We use built-in HTTP features, like HTTP
                authentication and HTTP verbs, which are understood by off-the-shelf HTTP clients.
                JSON is returned by all API responses, including errors. All API calls include postman scripts for easy
                testing and experimentation.
        </strong></div>
    </div>
    <br><br>
    <div class="row">
        <div class="col-sm-12">
            <img class="center-block img-responsive" src="https://d1pv9ulu41r3n5.cloudfront.net/img/RouteAPI-2.png">
        </div>
    </div>
    <br><br>
    <div class="row">
        <div class="col-sm-12">
            <div class="panel">
                <%--<div class="panel" style="border-color: #23282e;">--%>
                <%--<div class="panel-heading" style="background-color: #23282e; color: white;">API Keys</div>--%>
                <div class="panel-body">
                    To make the API as explorable as possible, accounts have test mode and live mode API keys. There is no "switch"
                        for changing between modes, just use the appropriate key to perform a live or test transaction.
                        Requests made with test mode credentials incur no cost and are meant to be permeate parameters of your
                        development and test environments.
                        <br><br>
                    There are six keys in all. The intended use of each key is defined here, in individual API documents and in
                        the administration section of your Dashboard. Each key has an identifier to make it easy to know where the
                        key belongs.
                        <br><br>
                    <table class="table table-bordered">
                        <tr style="background-color: #4f5b69; color: white">
                            <th>Key</th>
                            <th>Identifier</th>
                            <th>Use</th>
                        </tr>
                        <tr>
                            <td>Live API Key</td>
                            <td>LIVE_ak_&nbsp;&nbsp;&nbsp;&nbsp;(ak = API Key)</td>
                            <td>PRIVATE: Never share or hard-code. Part 1 of 2 used to authenticate LIVE API requests.</td>
                        </tr>
                        <tr>
                            <td>Live Secret Key</td>
                            <td>LIVE_s_&nbsp;&nbsp;&nbsp;&nbsp;(s = Secret)</td>
                            <td>PRIVATE: Never share or hard-code. Part 2 of 2 used to authenticate LIVE API requests.</td>
                        </tr>
                        <tr>
                            <td>Live Widget Key</td>
                            <td>LIVE_wk_&nbsp;&nbsp;&nbsp;&nbsp;(wk = Widget Key)</td>
                            <td>PUBLIC: Used to identify Route Widget owner. Designed to be used in UI code.</td>
                        </tr>
                        <tr>
                            <td>Test API Key</td>
                            <td>test_ak_&nbsp;&nbsp;&nbsp;&nbsp;(ak = API Key)</td>
                            <td>PRIVATE: Never share or hard-code. Part 1 of 2 used to authenticate test API requests.</td>
                        </tr>
                        <tr>
                            <td>Test Secret Key</td>
                            <td>test_s_&nbsp;&nbsp;&nbsp;&nbsp;(s = secret)</td>
                            <td>PRIVATE: Never share or hard-code. Part 2 of 2 used to authenticate test API requests.</td>
                        </tr>
                        <tr>
                            <td>Test Widget Key</td>
                            <td>test_wk_&nbsp;&nbsp;&nbsp;&nbsp;(wk = Widget Key)</td>
                            <td>PUBLIC: Used to identify Route Widget owner. Designed to be used in UI code.</td>
                        </tr>
                    </table>
                </div>
                <div class="panel-footer text-center alert-danger">Your API Keys and Secret Keys carry many privileges, so be
                    sure to keep them secret! Do not share your API Keys or Secret Keys in publicly accessible areas such
                    GitHub, client-side code, and so forth.
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            &nbsp;
        </div>
    </div>
</div>

</body>
</html>

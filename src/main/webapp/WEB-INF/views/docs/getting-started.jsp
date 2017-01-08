<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Route Documentation - Getting Started</title>
    <jsp:include page="/WEB-INF/views/docs/header-include.jsp" flush="false" />
</head>
<body style="background-color: #f6f9fc; background-image: url('https://d1pv9ulu41r3n5.cloudfront.net/img/greenbg2.png'); background-repeat:no-repeat;">
<jsp:include page="/WEB-INF/views/docs/sideMenu.jsp" flush="true" >
    <jsp:param name="page" value="0" />
</jsp:include>
<div class="container-fluid myTestContainer">
    <h1>&nbsp;</h1>
    <h1 class="text-center">Integrate the future</h1>
    <h3 class="text-center">of shipping insurance technology</h3>
    <br>
    <h5 class="text-center">Build customer confidence with Route's state of the art order protection</h5>
    <h5 class="text-center">Backed by Lloyd’s of London and Route's robust technology and security</h5>
    <br><br>
    <div class="row">
        <div class="col-md-4">
            <a href="/docs/api">
            <div class="panel" style="border-color: #2b87ff;">
                <div class="panel-body text-center">
                    <h1>API</h1>
                    <p>Explore our API comprehensive docs, including code samples and Postman Scripts.</p>
                </div>
            </div>
            </a>
        </div>
        <div class="col-md-4">
            <div class="panel" style="border-color: #2b87ff;">
                <div class="panel-body text-center">
                    <h1>Integrations</h1>
                    <p>Our list of specialized e-commerce platform integrations is growing, and these are the docs...</p>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="panel" style="border-color: #2b87ff;">
                <div class="panel-body text-center">
                    <h1>Knowledge Base</h1>
                    <p>Coming Soon...</p>
                    <p>&nbsp;</p>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>

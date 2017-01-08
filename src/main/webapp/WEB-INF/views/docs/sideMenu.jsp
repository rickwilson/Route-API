<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="nav-side-menu">
    <div class="brand" style="margin-bottom: 5px;"><img style="margin-bottom: 10px; margin-top: 10px;" src="https://d1pv9ulu41r3n5.cloudfront.net/img/route-logo-clear-180px.png" width="180" /></div>
    <i class="fa fa-bars fa-2x toggle-btn" data-toggle="collapse" data-target="#menu-content"></i>

    <div class="menu-list">

        <ul id="menu-content" class="menu-content collapse out">
            <li>
                <a href="/docs/">
                    <i class="fa fa-caret-square-o-right fa-lg"></i> Getting Started
                </a>
            </li>

            <li  data-toggle="collapse" data-target="#api" class="collapsed" aria-expanded="true">
                <a href="#"><i class="fa fa-cloud fa-lg"></i> API <span class="arrow"></span></a>
            </li>
            <ul class="sub-menu collapse
            <c:if test="${param.page == '1.1' || param.page == '1.2' || param.page == '1.3' || param.page == '1.4' || param.page == '1.5' || param.page == '1.6' || param.page == '1.7' || param.page == '1.8' || param.page == '1.9'}">
            in active
            </c:if>
            " id="api">
                <li<c:if test="${param.page == '1.1'}"> class="active"</c:if>><a href="/docs/api/overview">Overview</a></li>
                <li<c:if test="${param.page == '1.2'}"> class="active"</c:if>><a href="/docs/api/request/quote">Request Quote</a></li>
                <li<c:if test="${param.page == '1.9'}"> class="active"</c:if>><a href="/docs/api/widget">Checkout Widget</a></li>
                <li<c:if test="${param.page == '1.3'}"> class="active"</c:if>><a href="/docs/api/insure/order">Insure Order</a></li>
                <li<c:if test="${param.page == '1.4'}"> class="active"</c:if>><a href="/docs/api/update/tracking">Update Tracking</a></li>
                <li<c:if test="${param.page == '1.5'}"> class="active"</c:if>><a href="/docs/api/get/types/order">Order Types</a></li>
                <li<c:if test="${param.page == '1.6'}"> class="active"</c:if>><a href="/docs/api/get/types/payment">Payment Types</a></li>
                <li<c:if test="${param.page == '1.7'}"> class="active"</c:if>><a href="/docs/api/get/codes/currency">Currency Codes</a></li>
                <li<c:if test="${param.page == '1.8'}"> class="active"</c:if>><a href="/docs/api/get/codes/shipping/carriers">Shipping Carrier Codes</a></li>
            </ul>

            <li data-toggle="collapse" data-target="#integrations" class="collapsed">
                <a href="#"><i class="fa fa-plug fa-lg"></i> Integrations <span class="arrow"></span></a>
            </li>
            <ul class="sub-menu collapse
            <c:if test="${param.page == '2.1' || param.page == '2.2'}">
            in active
            </c:if>
            " id="integrations">
                <li<c:if test="${param.page == '2.1'}"> class="active"</c:if>><a href="/docs/integration/konnektive">Konnektive</a></li>
                <li<c:if test="${param.page == '2.2'}"> class="active"</c:if>><a href="/docs/integration/limelight">LimeLight</a></li>
            </ul>

            <li>
                <a href="https://routeit.cloud">
                    <i class="fa fa-area-chart fa-lg"></i> My Dashboard
                </a>
            </li>
        </ul>
    </div>
</div>

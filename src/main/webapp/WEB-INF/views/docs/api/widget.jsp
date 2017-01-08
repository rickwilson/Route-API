<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Route Open API - Widget</title>
    <jsp:include page="/WEB-INF/views/docs/header-include.jsp" flush="false" />
</head>
<body>
<jsp:include page="/WEB-INF/views/docs/sideMenu.jsp" flush="true" >
    <jsp:param name="page" value="1.9" />
</jsp:include>
<div class="container-fluid myTestContainer">
    <h1>Open API - Checkout Widget</h1>
    <h3>Overview:</h3>
    <div class="row">
        <div class="hidden-xs col-sm-1">&nbsp;</div>
        <div class="col-xs-12 col-sm-11">The Route Checkout Widget uses the Open API to dynamically generate a Route branded opt-in checkbox for insurance.
            To use the widget, just add the following two lines of code to your checkout page or any page where
            customers will select shipping. The generated code includes a checkbox that can be included as part
            of a form. The name and id of this checkbox is 'routeInsureOrder' and can be referenced like any other form variable.
            <br>When placing the widget in your form, make sure the javascript is included after the Route div tag, but before the closing body tag.
            If the javascript is added to the head or anywhere before the div tag, the javascript will fail to build the div content.
            The javascript can be included immediately after the div tag, as seen below, or it can be added right before the closing body tag.
            <br>If you plan to include insurance on all you orders, such as making it a
            handling charge, the widget is not required.<br><br>
            Replace <strong style="color: red">WIDGET-KEY-HERE</strong> with your public widget key and <strong style="color: red">QUOTE-ID</strong> with a quote id from the Request Quote API or use 'test' if you are working on the UI.
<pre>&lt;div id="rw-_container" data-publickey='<strong style="color: red">WIDGET-KEY-HERE</strong>' data-quoteid='<strong style="color: red">QUOTE-ID</strong>' data-overridedisplayprice='' data-style='true'&gt;&lt;/div&gt;
&lt;script src="https://d1pv9ulu41r3n5.cloudfront.net/routeWidgetScript.min.js" type="text/javascript"&gt;&lt;/script&gt;</pre>
        </div>
    </div>
    <h3>Parameters:</h3>
    <div class="row">
        <div class="hidden-xs col-sm-1">&nbsp;</div>
        <div class="col-xs-12 col-sm-11">
            <table class="table table-bordered">
                <tr style="background-color: black; color: white">
                    <th>Parameter Key</th>
                    <th>Parameter Value</th>
                    <th>Required</th>
                    <th>Description</th>
                </tr>
                <tr>
                    <td>data-publickey</td>
                    <td>WIDGET-KEY-HERE</td>
                    <td>YES</td>
                    <td>Your public (test or LIVE) widget key.</td>
                </tr>
                <tr>
                    <td>data-quoteid</td>
                    <td>QUOTE-ID</td>
                    <td>YES</td>
                    <td>A valid quote id from the request quote API or 'test'.</td>
                </tr>
                <tr>
                    <td>data-overridedisplayprice</td>
                    <td>Any String</td>
                    <td>no</td>
                    <td>Overrides the displayed insurance price. If empty, the suggested insurance price from the quote displays.</td>
                </tr>
                <tr>
                    <td>data-style</td>
                    <td>true or false</td>
                    <td>no</td>
                    <td>Controls if the default stylesheet is included or not.</td>
                </tr>
            </table>
        </div>
    </div>
    <h3>Styling:</h3>
    <div class="row">
        <div class="hidden-xs col-sm-1">&nbsp;</div>
        <div class="col-xs-12 col-sm-11">
            This is what the default Route Widget looks like. Mouse over the route logo to see the detail bubble.
            <br><br>
            <!--V2 Route Widget Start-->
            <span style="font-size:11px;position:relative;color:#000;">
                <img src=" https://d1pv9ulu41r3n5.cloudfront.net/infobox.png" alt="route" id="routePopup1" style="width:382px;position:absolute;bottom:27px;z-index:2147483647;border:1px solid #3a3a3a; display:none;">
                <img src="https://d1pv9ulu41r3n5.cloudfront.net/logoIcon.gif" alt="route" style="width:64px;height:16px;vertical-align:text-bottom; margin-right:4px;" onmouseout="document.getElementById('routePopup1').style.display='none';" onmouseover="document.getElementById('routePopup1').style.display='block';">
                (Accept <input type="checkbox" id="routeInsureOrder" name="routeInsureOrder" value="on" checked style="position: relative; top: 1px;"> ) Shipping Protection $3.97
            </span>
            <!--V2 Route Widget End-->
            <br><br>
            The Route Widget comes with a standard stylesheet that works with many ecommerce platforms. But it also includes
            the ability to substitute your own stylesheet. Simply change the value of 'data-style' in the Route div tag from 'true'
            to 'false' and include your own stylesheet with the following tags.
<pre>#rw-_container span{
    color:#000;
    font-size:11px;
    position:relative;
}
#rw-_popup{
    border:1px solid #3a3a3a;
    bottom:27px;
    position:absolute;
    width:382px;
    z-index:100;
}
#rw_logoIcon{
    width:64px;
    height:16px;
    vertical-align:text-bottom;
    margin-right:4px;
}
#rw-_decline{
    position:relative;
    top:3px;
}
#rw-_container .rw-_smFont{
    position:relative;
    font-size:9px;
    display:block;
    margin-top:2px;
}</pre>
        </div>
    </div>
    <h3>Form Example:</h3>
    <div class="row">
        <div class="hidden-xs col-sm-1">&nbsp;</div>
        <div class="col-xs-12 col-sm-11">
<pre>&lt;!DOCTYPE html&gt;
&lt;html lang="en"&gt;
&lt;head&gt;
    &lt;meta charset="UTF-8"&gt;
    &lt;title&gt;My Checkout Page&lt;/title&gt;
&lt;/head&gt;
&lt;body&gt;
&lt;h1&gt;Checkout Page&lt;/h1&gt;
&lt;form name="checkout" action="completeCheckout" method="post"&gt;
    First Name: &lt;input type="text" name="first"&gt;
    &lt;br&gt;
    Last Name: &lt;input type="text" name="last"&gt;
    &lt;br&gt;
    Address: &lt;input type="text" name="address"&gt;
    &lt;br&gt;
    ...
    &lt;br&gt;
    Shipping Method: &lt;select name="shipping_method"&gt;
        &lt;option value="UPS"&gt;UPS&lt;/option&gt;
        &lt;option value="FedEx"&gt;FedEx&lt;/option&gt;
        &lt;option value="USPS"&gt;USPS&lt;/option&gt;
    &lt;/select&gt;
    &lt;br&gt;
    <strong style="color: blue">&lt;div id="rw-_container" data-publickey='<strong style="color: red">WIDGET-KEY-HERE</strong>' data-quoteid='<strong style="color: red">QUOTE-ID</strong>' data-overridedisplayprice='' data-style='true'&gt;&lt;/div&gt;</strong>
    &lt;br&gt;
    &lt;input type="submit"&gt;
&lt;/form&gt;
<strong style="color: blue">&lt;script src="https://d1pv9ulu41r3n5.cloudfront.net/routeWidgetScript.min.js" type="text/javascript"&gt;&lt;/script&gt;</strong>
&lt;/body&gt;
&lt;/html&gt;</pre>
        </div>
    </div>
    <h3>Errors:</h3>
    <div class="row">
        <div class="hidden-xs col-sm-1">&nbsp;</div>
        <div class="col-xs-12 col-sm-11">
            The Route Widget can return the following errors:
            <table class="table table-bordered">
                <tr>
                    <td>Route Widget must include a valid widget_key.</td>
                </tr>
                <tr>
                    <td>No billing setup for these keys. Live API requests require valid billing. If this is a test, use the test widget key.</td>
                </tr>
                <tr>
                    <td>Route Widget must include a valid quote_id.</td>
                </tr>
                <tr>
                    <td>No Route records exist for the provided quote_id and widget_key.</td>
                </tr>
                <tr>
                    <td>The provided quote_id has already been used. Quotes can only be applied to a single order.</td>
                </tr>
                <tr>
                    <td>The provided quote_id has expired. Quotes must be used within 24 hours.</td>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>
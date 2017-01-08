<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Route Open API - Insure Order</title>
    <jsp:include page="/WEB-INF/views/docs/header-include.jsp" flush="false" />
</head>
<body>
<jsp:include page="/WEB-INF/views/docs/sideMenu.jsp" flush="true" >
    <jsp:param name="page" value="1.3" />
</jsp:include>
<div class="container-fluid myTestContainer">
    <h1>Open API - Insure Order</h1>
    <h3>API Endpoint:</h3>
    <div class="row">
        <div class="hidden-xs col-sm-1">&nbsp;</div>
        <div class="col-xs-12 col-sm-11">
            <strong>URL: </strong> https://api.routeit.cloud/konnektive/v2/post/json/insure_order
            <br>
            <strong>Protocol: </strong> HTTPS
            <br>
            <strong>Method: </strong> POST
            <br>
            <strong>Authentication: </strong> API Key and Secret via header
            <br>
            <strong>Content Type: </strong> application/json
            <br><br>
            All API calls must include this header to authorize the request.<br>
            Replace <strong style="color: red">YOUR-KEY-HERE</strong> with your API key and <strong style="color: red">YOUR-SECRET-HERE</strong> with your secret.
            <table class="table table-bordered">
                <tr style="background-color: black; color: white">
                    <th>Header Key</th>
                    <th>Header Value</th>
                </tr>
                <tr>
                    <td>api_key</td>
                    <td>YOUR-KEY-HERE</td>
                </tr>
                <tr>
                    <td>secret</td>
                    <td>YOUR-SECRET-HERE</td>
                </tr>
                <tr>
                    <td>Content-Type</td>
                    <td>application/json</td>
                </tr>
            </table>
        </div>
    </div>
            <h3>Body:</h3>
            <div class="row">
                <div class="hidden-xs col-sm-1">&nbsp;</div>
                <div class="col-xs-12 col-sm-11">
                    The body must be sent in JSON format. The keys include company_name, first, last, email, phone, and transactions.
                    <pre>
{
    "route_quote_id": "5",
    "order_id": "abc-123",
    "order_date": "08-15-2099",
    "order_insured": "true",
    "order_base_value": "67.50",
    "order_base_total": "67.50",
    "shipping_total": "7.49",
    "handling_total": "0",
    "sales_tax_total": "4.56",
    "customer_paid_insurance_price": "3.49",
    "payment_type": "CREDIT_CARD",
    "card_last_four": "0000",
    "card_expire_date": "12/99",
    "first_name": "Bob",
    "last_name": "Denver",
    "email": "test@test.test",
    "phone": "111-111-1111",
    "shipping_address_1": "123 Palm Tree Way",
    "shipping_city": "Island Town",
    "shipping_province": "Hawaii",
    "shipping_postal_code": "00000",
    "shipping_country": "USA",
    "billing_same_as_shipping": "true"
}
                    </pre>
                    <table class="table table-bordered">
                        <tr style="background-color: black; color: white">
                            <th>Body Key</th>
                            <th>Body Value</th>
                            <th>Required</th>
                            <th>Description</th>
                        </tr>
                        <tr>
                            <td>route_quote_id</td>
                            <td>string</td>
                            <td>YES</td>
                            <td>Valid quote id from the <a href="/docs/api/request/quote">Request Quote API</a>.</td>
                        </tr>
                        <tr>
                            <td>order_id</td>
                            <td>string</td>
                            <td>YES</td>
                            <td>The merchants' unique order identifier. Used for claim reference.</td>
                        </tr>
                        <tr>
                            <td>order_date</td>
                            <td>string</td>
                            <td>YES</td>
                            <td>Date the order was completed. Prefer ISO 8601 format.</td>
                        </tr>
                        <tr>
                            <td>order_insured</td>
                            <td>boolean</td>
                            <td>YES</td>
                            <td>Did the customer select to Insure their order?</td>
                        </tr>
                        <tr>
                            <td>order_base_value</td>
                            <td>integer, double or float greater than zero and greater than or equal to order_base_total</td>
                            <td>YES</td>
                            <td>Total cart replacement value, minus shipping, handling and tax. Must match order_base_value used when calling <a href="/docs/api/request/quote">Request Quote API</a>.</td>
                        </tr>
                        <tr>
                            <td>order_base_total</td>
                            <td>integer, double or float greater than or equal to zero</td>
                            <td>YES</td>
                            <td>Total cart price charged to the buyer, minus shipping, handling and tax. This is the amount the customer is paying after discounts, not the replacement value. Must match order_base_total used when calling <a href="/docs/api/request/quote">Request Quote API</a>.</td>
                        </tr>
                        <tr>
                            <td>shipping_total</td>
                            <td>integer, double or float greater than or equal to zero</td>
                            <td>YES</td>
                            <td>Shipping price charged to the buyer.</td>
                        </tr>
                        <tr>
                            <td>handling_total</td>
                            <td>integer, double or float greater than or equal to zero</td>
                            <td>YES</td>
                            <td>Handling price charged to the buyer.</td>
                        </tr>
                        <tr>
                            <td>sales_tax_total</td>
                            <td>integer, double or float greater than or equal to zero</td>
                            <td>YES</td>
                            <td>Taxes charged to the buyer.</td>
                        </tr>
                        <tr>
                            <td>customer_paid_insurance_price</td>
                            <td>integer, double or float greater than or equal to zero</td>
                            <td>YES</td>
                            <td>Insurnece price charged to the buyer.</td>
                        </tr>
                        <tr>
                            <td>payment_type</td>
                            <td>supported payment type as string</td>
                            <td>YES</td>
                            <td>Payment type the buyer used to pay for the order. Supported payment types can be pulled from our <a href="/docs/api/get/types/payment">Payment Types API</a>. Used for claim reference.</td>
                        </tr>
                        <tr>
                            <td>payment_type_other</td>
                            <td>string</td>
                            <td>MAYBE</td>
                            <td>REQUIRED if payment_type is OTHER. Used for claim reference.</td>
                        </tr>
                        <tr>
                            <td>card_last_four</td>
                            <td>string</td>
                            <td>MAYBE</td>
                            <td>REQUIRED if payment_type is CREDIT_CARD. Used for claim reference.</td>
                        </tr>
                        <tr>
                            <td>card_expire_date</td>
                            <td>integer</td>
                            <td>MAYBE</td>
                            <td>REQUIRED if payment_type is CREDIT_CARD. Used for claim reference.</td>
                        </tr>
                        <tr>
                            <td>first_name</td>
                            <td>string</td>
                            <td>YES</td>
                            <td>Customer's first name.</td>
                        </tr>
                        <tr>
                            <td>last_name</td>
                            <td>string</td>
                            <td>YES</td>
                            <td>Customer's last name.</td>
                        </tr>
                        <tr>
                            <td>email</td>
                            <td>string</td>
                            <td>YES</td>
                            <td>Customer's email address. REQUIRED to be a valid email address.</td>
                        </tr>
                        <tr>
                            <td>phone</td>
                            <td>string</td>
                            <td>string</td>
                            <td>Customer's phone number.</td>
                        </tr>
                        <tr>
                            <td>shipping_address_1</td>
                            <td>string</td>
                            <td>YES</td>
                            <td>Customer's shipping street address.</td>
                        </tr>
                        <tr>
                            <td>shipping_address_2</td>
                            <td>string</td>
                            <td>no</td>
                            <td>Customer's shipping street address, line two.</td>
                        </tr>
                        <tr>
                            <td>shipping_city</td>
                            <td>string</td>
                            <td>YES</td>
                            <td>Customer's shipping city.</td>
                        </tr>
                        <tr>
                            <td>shipping_province</td>
                            <td>string</td>
                            <td>no</td>
                            <td>Customer's shipping province or state.</td>
                        </tr>
                        <tr>
                            <td>shipping_postal_code</td>
                            <td>string</td>
                            <td>YES</td>
                            <td>Customer's shipping postal code.</td>
                        </tr>
                        <tr>
                            <td>shipping_country</td>
                            <td>string</td>
                            <td>YES</td>
                            <td>Customer's shipping country. Prefer ISO 3166 Alpha-3(three letters) country code as a string.</td>
                        </tr>
                        <tr>
                            <td>billing_same_as_shipping</td>
                            <td>boolean</td>
                            <td>YES</td>
                            <td>Is the billing address the same as the shipping address?</td>
                        </tr>
                        <tr>
                            <td>billing_address_1</td>
                            <td>string</td>
                            <td>MAYBE</td>
                            <td>REQUIRED if billing_same_as_shipping is false. Customer's billing street address.</td>
                        </tr>
                        <tr>
                            <td>billing_address_2</td>
                            <td>string</td>
                            <td>no</td>
                            <td>Customer's shipping street address, line two.</td>
                        </tr>
                        <tr>
                            <td>billing_city</td>
                            <td>string</td>
                            <td>MAYBE</td>
                            <td>REQUIRED if billing_same_as_shipping is false. Customer's shipping city.</td>
                        </tr>
                        <tr>
                            <td>billing_province</td>
                            <td>string</td>
                            <td>MAYBE</td>
                            <td>REQUIRED if billing_same_as_shipping is false. Customer's shipping province or state.</td>
                        </tr>
                        <tr>
                            <td>billing_postal_code</td>
                            <td>string</td>
                            <td>MAYBE</td>
                            <td>REQUIRED if billing_same_as_shipping is false. Customer's shipping postal code.</td>
                        </tr>
                        <tr>
                            <td>billing_country</td>
                            <td>string</td>
                            <td>MAYBE</td>
                            <td>REQUIRED if billing_same_as_shipping is false. Customer's shipping country. Prefer ISO 3166 Alpha-3(three letters) country code as a string.</td>
                        </tr>
                        <tr>
                            <td>tracking_number</td>
                            <td>string</td>
                            <td>YES</td>
                            <td>Valid carrier generated tracking number.</td>
                        </tr>
                        <tr>
                            <td>shipped_date</td>
                            <td>string</td>
                            <td>YES</td>
                            <td>Date order was shipped. Prefer ISO 8601 format.</td>
                        </tr>
                        <tr>
                            <td>shipping_carrier_code</td>
                            <td>supported shipping carrier code as a string</td>
                            <td>no</td>
                            <td>Carrier order was shipped through. Supported shipping carrier codes can be pulled from our <a href="/docs/api/get/codes/shipping/carriers">Shipping Carrier Codes API</a>.</td>
                        </tr>
                        <tr>
                            <td>shipping_method</td>
                            <td>string</td>
                            <td>no</td>
                            <td>Method used for shipping.</td>
                        </tr>
                        <tr>
                            <td>custom_b1</td>
                            <td>string</td>
                            <td>no</td>
                            <td>Custom field that accepts any text string. Max 255 characters.</td>
                        </tr>
                        <tr>
                            <td>custom_b2</td>
                            <td>string</td>
                            <td>no</td>
                            <td>Custom field that accepts any text string. Max 255 characters.</td>
                        </tr>
                        <tr>
                            <td>custom_b3</td>
                            <td>string</td>
                            <td>no</td>
                            <td>Custom field that accepts any text string. Max 255 characters.</td>
                        </tr>
                        <tr>
                            <td>custom_b4</td>
                            <td>string</td>
                            <td>no</td>
                            <td>Custom field that accepts any text string. Max 255 characters.</td>
                        </tr>
                    </table>
                </div>
            </div>
            <h3>Postman:</h3>
            <div class="row">
                <div class="hidden-xs col-sm-1">&nbsp;</div>
                <div class="col-xs-12 col-sm-11"><a href="https://www.getpostman.com/collections/d87e16f67da9aa5968f5">https://www.getpostman.com/collections/d87e16f67da9aa5968f5</a></div>
            </div>
            <h3>Code Examples:</h3>
            <div class="row">
                <div class="hidden-xs col-sm-1">&nbsp;</div>
                <div class="col-xs-12 col-sm-11">
                    The following are examples for reference and may require library dependencies not described here. An understanding of REST services, JSON, and web security is required to develop service clients.
                    <ul class="nav nav-tabs">
                        <li class="active"><a data-toggle="tab" href="#javascript">JavaScript</a></li>
                        <li><a data-toggle="tab" href="#nodejs">NodeJS</a></li>
                        <li><a data-toggle="tab" href="#java">Java</a></li>
                        <li><a data-toggle="tab" href="#csharp">C#</a></li>
                        <li><a data-toggle="tab" href="#go">Go</a></li>
                        <li><a data-toggle="tab" href="#python">Python</a></li>
                        <li><a data-toggle="tab" href="#ruby">Ruby</a></li>
                        <li><a data-toggle="tab" href="#php">PHP</a></li>
                        <li><a data-toggle="tab" href="#other">Other</a></li> <!--  http, curl, shell, ios  -->
                    </ul>
                    <div class="tab-content">
                        <div id="javascript" class="tab-pane fade in active">
                            <h5>Javascript - Jquery AJAX</h5>
                            <pre>
var settings = {
  "async": true,
  "crossDomain": true,
  "url": "https://api.routeit.cloud/open_api/v1/post/json/insure_order",
  "method": "POST",
  "headers": {
    "api_key": "<strong style="color: red">YOUR-KEY-HERE</strong>",
    "secret": "<strong style="color: red"><strong style="color: red">YOUR-SECRET-HERE</strong></strong>",
    "content-type": "application/json",
    "cache-control": "no-cache"
  },
  "processData": false,
  "data": "{\n\t\"route_quote_id\": \"4\",\n\t\"order_id\": \"abc-123\",\n\t\"order_date\": \"08-15-2099\",\n\t\"order_insured\": \"true\",\n\t\"order_base_value\": \"67.50\",\n\t\"order_base_total\": \"67.50\",\n\t\"shipping_total\": \"7.49\",\n\t\"handling_total\": \"0\",\n\t\"sales_tax_total\": \"4.56\",\n\t\"customer_paid_insurance_price\": \"3.49\",\n\t\"payment_type\": \"CREDIT_CARD\",\n\t\"card_last_four\": \"0000\",\n\t\"card_expire_date\": \"12/99\",\n\t\"first_name\": \"Bob\",\n\t\"last_name\": \"Denver\",\n\t\"email\": \"test@test.test\",\n\t\"phone\": \"111-111-1111\",\n\t\"shipping_address_1\": \"123 Palm Tree Way\",\n\t\"shipping_city\": \"Island Town\",\n\t\"shipping_province\": \"Hawaii\",\n\t\"shipping_postal_code\": \"00000\",\n\t\"shipping_country\": \"USA\",\n\t\"billing_same_as_shipping\": \"true\"\n}"
}

$.ajax(settings).done(function (response) {
  console.log(response);
});
                            </pre>
                            <h5>Javascript - XHR</h5>
                            <pre>
var data = JSON.stringify({
  "route_quote_id": "4",
  "order_id": "abc-123",
  "order_date": "08-15-2099",
  "order_insured": "true",
  "order_base_value": "67.50",
  "order_base_total": "67.50",
  "shipping_total": "7.49",
  "handling_total": "0",
  "sales_tax_total": "4.56",
  "customer_paid_insurance_price": "3.49",
  "payment_type": "CREDIT_CARD",
  "card_last_four": "0000",
  "card_expire_date": "12/99",
  "first_name": "Bob",
  "last_name": "Denver",
  "email": "test@test.test",
  "phone": "111-111-1111",
  "shipping_address_1": "123 Palm Tree Way",
  "shipping_city": "Island Town",
  "shipping_province": "Hawaii",
  "shipping_postal_code": "00000",
  "shipping_country": "USA",
  "billing_same_as_shipping": "true"
});

var xhr = new XMLHttpRequest();
xhr.withCredentials = true;

xhr.addEventListener("readystatechange", function () {
  if (this.readyState === 4) {
    console.log(this.responseText);
  }
});

xhr.open("POST", "https://api.routeit.cloud/open_api/v1/post/json/insure_order");
xhr.setRequestHeader("api_key", "<strong style="color: red">YOUR-KEY-HERE</strong>");
xhr.setRequestHeader("secret", "<strong style="color: red">YOUR-SECRET-HERE</strong>");
xhr.setRequestHeader("content-type", "application/json");
xhr.setRequestHeader("cache-control", "no-cache");

xhr.send(data);
                            </pre>
                        </div>
                        <div id="nodejs" class="tab-pane fade">
                            <h5>Node JS - Native</h5>
                            <pre>
var http = require("https");

var options = {
  "method": "POST",
  "hostname": "api.routeit.cloud",
  "port": null,
  "path": "/open_api/v1/post/json/insure_order",
  "headers": {
    "api_key": "<strong style="color: red">YOUR-KEY-HERE</strong>",
    "secret": "<strong style="color: red">YOUR-SECRET-HERE</strong>",
    "content-type": "application/json",
    "cache-control": "no-cache",
  }
};

var req = http.request(options, function (res) {
  var chunks = [];

  res.on("data", function (chunk) {
    chunks.push(chunk);
  });

  res.on("end", function () {
    var body = Buffer.concat(chunks);
    console.log(body.toString());
  });
});

req.write(JSON.stringify({ route_quote_id: '4',
  order_id: 'abc-123',
  order_date: '08-15-2099',
  order_insured: 'true',
  order_base_value: '67.50',
  order_base_total: '67.50',
  shipping_total: '7.49',
  handling_total: '0',
  sales_tax_total: '4.56',
  customer_paid_insurance_price: '3.49',
  payment_type: 'CREDIT_CARD',
  card_last_four: '0000',
  card_expire_date: '12/99',
  first_name: 'Bob',
  last_name: 'Denver',
  email: 'test@test.test',
  phone: '111-111-1111',
  shipping_address_1: '123 Palm Tree Way',
  shipping_city: 'Island Town',
  shipping_province: 'Hawaii',
  shipping_postal_code: '00000',
  shipping_country: 'USA',
  billing_same_as_shipping: 'true' }));
req.end();
                            </pre>
                            <h5>Node JS - Request</h5>
                            <pre>
var request = require("request");

var options = { method: 'POST',
  url: 'https://api.routeit.cloud/open_api/v1/post/json/insure_order',
  headers:
   { 'cache-control': 'no-cache',
     'content-type': 'application/json',
     secret: '<strong style="color: red">YOUR-SECRET-HERE</strong>',
     api_key: '<strong style="color: red">YOUR-KEY-HERE</strong>' },
  body:
   { route_quote_id: '4',
     order_id: 'abc-123',
     order_date: '08-15-2099',
     order_insured: 'true',
     order_base_value: '67.50',
     order_base_total: '67.50',
     shipping_total: '7.49',
     handling_total: '0',
     sales_tax_total: '4.56',
     customer_paid_insurance_price: '3.49',
     payment_type: 'CREDIT_CARD',
     card_last_four: '0000',
     card_expire_date: '12/99',
     first_name: 'Bob',
     last_name: 'Denver',
     email: 'test@test.test',
     phone: '111-111-1111',
     shipping_address_1: '123 Palm Tree Way',
     shipping_city: 'Island Town',
     shipping_province: 'Hawaii',
     shipping_postal_code: '00000',
     shipping_country: 'USA',
     billing_same_as_shipping: 'true' },
  json: true };

request(options, function (error, response, body) {
  if (error) throw new Error(error);

  console.log(body);
});
                            </pre>
                            <h5>Node JS - Unirest</h5>
                            <pre>
var unirest = require("unirest");

var req = unirest("POST", "https://api.routeit.cloud/open_api/v1/post/json/insure_order");

req.headers({
  "cache-control": "no-cache",
  "content-type": "application/json",
  "secret": "<strong style="color: red">YOUR-SECRET-HERE</strong>",
  "api_key": "<strong style="color: red">YOUR-KEY-HERE</strong>"
});

req.type("json");
req.send({
  "route_quote_id": "4",
  "order_id": "abc-123",
  "order_date": "08-15-2099",
  "order_insured": "true",
  "order_base_value": "67.50",
  "order_base_total": "67.50",
  "shipping_total": "7.49",
  "handling_total": "0",
  "sales_tax_total": "4.56",
  "customer_paid_insurance_price": "3.49",
  "payment_type": "CREDIT_CARD",
  "card_last_four": "0000",
  "card_expire_date": "12/99",
  "first_name": "Bob",
  "last_name": "Denver",
  "email": "test@test.test",
  "phone": "111-111-1111",
  "shipping_address_1": "123 Palm Tree Way",
  "shipping_city": "Island Town",
  "shipping_province": "Hawaii",
  "shipping_postal_code": "00000",
  "shipping_country": "USA",
  "billing_same_as_shipping": "true"
});

req.end(function (res) {
  if (res.error) throw new Error(res.error);

  console.log(res.body);
});
                            </pre>
                        </div>
                        <div id="java" class="tab-pane fade">
                            <h5>Java - OK HTTP</h5>
                            <pre>
OkHttpClient client = new OkHttpClient();

MediaType mediaType = MediaType.parse("application/json");
RequestBody body = RequestBody.create(mediaType, "{\n\t\"route_quote_id\": \"4\",\n\t\"order_id\": \"abc-123\",\n\t\"order_date\": \"08-15-2099\",\n\t\"order_insured\": \"true\",\n\t\"order_base_value\": \"67.50\",\n\t\"order_base_total\": \"67.50\",\n\t\"shipping_total\": \"7.49\",\n\t\"handling_total\": \"0\",\n\t\"sales_tax_total\": \"4.56\",\n\t\"customer_paid_insurance_price\": \"3.49\",\n\t\"payment_type\": \"CREDIT_CARD\",\n\t\"card_last_four\": \"0000\",\n\t\"card_expire_date\": \"12/99\",\n\t\"first_name\": \"Bob\",\n\t\"last_name\": \"Denver\",\n\t\"email\": \"test@test.test\",\n\t\"phone\": \"111-111-1111\",\n\t\"shipping_address_1\": \"123 Palm Tree Way\",\n\t\"shipping_city\": \"Island Town\",\n\t\"shipping_province\": \"Hawaii\",\n\t\"shipping_postal_code\": \"00000\",\n\t\"shipping_country\": \"USA\",\n\t\"billing_same_as_shipping\": \"true\"\n}");
Request request = new Request.Builder()
  .url("https://api.routeit.cloud/open_api/v1/post/json/insure_order")
  .post(body)
  .addHeader("api_key", "<strong style="color: red">YOUR-KEY-HERE</strong>")
  .addHeader("secret", "<strong style="color: red">YOUR-SECRET-HERE</strong>")
  .addHeader("content-type", "application/json")
  .addHeader("cache-control", "no-cache")
  .build();

Response response = client.newCall(request).execute();
                            </pre>
                            <h5>Java - Unirest</h5>
                            <pre>
HttpResponse&lt;String&gt; response = Unirest.post("https://api.routeit.cloud/open_api/v1/post/json/insure_order")
  .header("api_key", "<strong style="color: red">YOUR-KEY-HERE</strong>")
  .header("secret", "<strong style="color: red">YOUR-SECRET-HERE</strong>")
  .header("content-type", "application/json")
  .header("cache-control", "no-cache")
  .body("{\n\t\"route_quote_id\": \"4\",\n\t\"order_id\": \"abc-123\",\n\t\"order_date\": \"08-15-2099\",\n\t\"order_insured\": \"true\",\n\t\"order_base_value\": \"67.50\",\n\t\"order_base_total\": \"67.50\",\n\t\"shipping_total\": \"7.49\",\n\t\"handling_total\": \"0\",\n\t\"sales_tax_total\": \"4.56\",\n\t\"customer_paid_insurance_price\": \"3.49\",\n\t\"payment_type\": \"CREDIT_CARD\",\n\t\"card_last_four\": \"0000\",\n\t\"card_expire_date\": \"12/99\",\n\t\"first_name\": \"Bob\",\n\t\"last_name\": \"Denver\",\n\t\"email\": \"test@test.test\",\n\t\"phone\": \"111-111-1111\",\n\t\"shipping_address_1\": \"123 Palm Tree Way\",\n\t\"shipping_city\": \"Island Town\",\n\t\"shipping_province\": \"Hawaii\",\n\t\"shipping_postal_code\": \"00000\",\n\t\"shipping_country\": \"USA\",\n\t\"billing_same_as_shipping\": \"true\"\n}")
  .asString();
                            </pre>
                        </div>
                        <div id="csharp" class="tab-pane fade">
                            <h5>C# (ReSharp)</h5>
                            <pre>
var client = new RestClient("https://api.routeit.cloud/open_api/v1/post/json/insure_order");
var request = new RestRequest(Method.POST);
request.AddHeader("cache-control", "no-cache");
request.AddHeader("content-type", "application/json");
request.AddHeader("secret", "<strong style="color: red">YOUR-SECRET-HERE</strong>");
request.AddHeader("api_key", "<strong style="color: red">YOUR-KEY-HERE</strong>");
request.AddParameter("application/json", "{\n\t\"route_quote_id\": \"4\",\n\t\"order_id\": \"abc-123\",\n\t\"order_date\": \"08-15-2099\",\n\t\"order_insured\": \"true\",\n\t\"order_base_value\": \"67.50\",\n\t\"order_base_total\": \"67.50\",\n\t\"shipping_total\": \"7.49\",\n\t\"handling_total\": \"0\",\n\t\"sales_tax_total\": \"4.56\",\n\t\"customer_paid_insurance_price\": \"3.49\",\n\t\"payment_type\": \"CREDIT_CARD\",\n\t\"card_last_four\": \"0000\",\n\t\"card_expire_date\": \"12/99\",\n\t\"first_name\": \"Bob\",\n\t\"last_name\": \"Denver\",\n\t\"email\": \"test@test.test\",\n\t\"phone\": \"111-111-1111\",\n\t\"shipping_address_1\": \"123 Palm Tree Way\",\n\t\"shipping_city\": \"Island Town\",\n\t\"shipping_province\": \"Hawaii\",\n\t\"shipping_postal_code\": \"00000\",\n\t\"shipping_country\": \"USA\",\n\t\"billing_same_as_shipping\": \"true\"\n}", ParameterType.RequestBody);
IRestResponse response = client.Execute(request);
                            </pre>
                        </div>
                        <div id="go" class="tab-pane fade">
                            <h5>Go</h5>
                            <pre>
package main

import (
	"fmt"
	"strings"
	"net/http"
	"io/ioutil"
)

func main() {

	url := "https://api.routeit.cloud/open_api/v1/post/json/insure_order"

	payload := strings.NewReader("{\n\t\"route_quote_id\": \"4\",\n\t\"order_id\": \"abc-123\",\n\t\"order_date\": \"08-15-2099\",\n\t\"order_insured\": \"true\",\n\t\"order_base_value\": \"67.50\",\n\t\"order_base_total\": \"67.50\",\n\t\"shipping_total\": \"7.49\",\n\t\"handling_total\": \"0\",\n\t\"sales_tax_total\": \"4.56\",\n\t\"customer_paid_insurance_price\": \"3.49\",\n\t\"payment_type\": \"CREDIT_CARD\",\n\t\"card_last_four\": \"0000\",\n\t\"card_expire_date\": \"12/99\",\n\t\"first_name\": \"Bob\",\n\t\"last_name\": \"Denver\",\n\t\"email\": \"test@test.test\",\n\t\"phone\": \"111-111-1111\",\n\t\"shipping_address_1\": \"123 Palm Tree Way\",\n\t\"shipping_city\": \"Island Town\",\n\t\"shipping_province\": \"Hawaii\",\n\t\"shipping_postal_code\": \"00000\",\n\t\"shipping_country\": \"USA\",\n\t\"billing_same_as_shipping\": \"true\"\n}")

	req, _ := http.NewRequest("POST", url, payload)

	req.Header.Add("api_key", "<strong style="color: red">YOUR-KEY-HERE</strong>")
	req.Header.Add("secret", "<strong style="color: red">YOUR-SECRET-HERE</strong>")
	req.Header.Add("content-type", "application/json")
	req.Header.Add("cache-control", "no-cache")

	res, _ := http.DefaultClient.Do(req)

	defer res.Body.Close()
	body, _ := ioutil.ReadAll(res.Body)

	fmt.Println(res)
	fmt.Println(string(body))

}
                            </pre>
                        </div>
                        <div id="python" class="tab-pane fade">
                            <h5>Python - http.client (Python 3)</h5>
                            <pre>
import http.client

conn = http.client.HTTPSConnection("api.routeit.cloud")

payload = "{\n\t\"route_quote_id\": \"4\",\n\t\"order_id\": \"abc-123\",\n\t\"order_date\": \"08-15-2099\",\n\t\"order_insured\": \"true\",\n\t\"order_base_value\": \"67.50\",\n\t\"order_base_total\": \"67.50\",\n\t\"shipping_total\": \"7.49\",\n\t\"handling_total\": \"0\",\n\t\"sales_tax_total\": \"4.56\",\n\t\"customer_paid_insurance_price\": \"3.49\",\n\t\"payment_type\": \"CREDIT_CARD\",\n\t\"card_last_four\": \"0000\",\n\t\"card_expire_date\": \"12/99\",\n\t\"first_name\": \"Bob\",\n\t\"last_name\": \"Denver\",\n\t\"email\": \"test@test.test\",\n\t\"phone\": \"111-111-1111\",\n\t\"shipping_address_1\": \"123 Palm Tree Way\",\n\t\"shipping_city\": \"Island Town\",\n\t\"shipping_province\": \"Hawaii\",\n\t\"shipping_postal_code\": \"00000\",\n\t\"shipping_country\": \"USA\",\n\t\"billing_same_as_shipping\": \"true\"\n}"

headers = {
    'api_key': "<strong style="color: red">YOUR-KEY-HERE</strong>",
    'secret': "<strong style="color: red">YOUR-SECRET-HERE</strong>",
    'content-type': "application/json",
    'cache-control': "no-cache",
    }

conn.request("POST", "/open_api/v1/post/json/insure_order", payload, headers)

res = conn.getresponse()
data = res.read()

print(data.decode("utf-8"))
                            </pre>
                            <h5>Python - Requests</h5>
                            <pre>
import requests

url = "https://api.routeit.cloud/open_api/v1/post/json/insure_order"

payload = "{\n\t\"route_quote_id\": \"4\",\n\t\"order_id\": \"abc-123\",\n\t\"order_date\": \"08-15-2099\",\n\t\"order_insured\": \"true\",\n\t\"order_base_value\": \"67.50\",\n\t\"order_base_total\": \"67.50\",\n\t\"shipping_total\": \"7.49\",\n\t\"handling_total\": \"0\",\n\t\"sales_tax_total\": \"4.56\",\n\t\"customer_paid_insurance_price\": \"3.49\",\n\t\"payment_type\": \"CREDIT_CARD\",\n\t\"card_last_four\": \"0000\",\n\t\"card_expire_date\": \"12/99\",\n\t\"first_name\": \"Bob\",\n\t\"last_name\": \"Denver\",\n\t\"email\": \"test@test.test\",\n\t\"phone\": \"111-111-1111\",\n\t\"shipping_address_1\": \"123 Palm Tree Way\",\n\t\"shipping_city\": \"Island Town\",\n\t\"shipping_province\": \"Hawaii\",\n\t\"shipping_postal_code\": \"00000\",\n\t\"shipping_country\": \"USA\",\n\t\"billing_same_as_shipping\": \"true\"\n}"
headers = {
    'api_key': "<strong style="color: red">YOUR-KEY-HERE</strong>",
    'secret': "<strong style="color: red">YOUR-SECRET-HERE</strong>",
    'content-type': "application/json",
    'cache-control': "no-cache"
    }

response = requests.request("POST", url, data=payload, headers=headers)

print(response.text)
                            </pre>
                        </div>
                        <div id="ruby" class="tab-pane fade">
                            <h5>Ruby (NET::Http)</h5>
                            <pre>
require 'uri'
require 'net/http'

url = URI("https://api.routeit.cloud/open_api/v1/post/json/insure_order")

http = Net::HTTP.new(url.host, url.port)
http.use_ssl = true
http.verify_mode = OpenSSL::SSL::VERIFY_NONE

request = Net::HTTP::Post.new(url)
request["api_key"] = '<strong style="color: red">YOUR-KEY-HERE</strong>'
request["secret"] = '<strong style="color: red">YOUR-SECRET-HERE</strong>'
request["content-type"] = 'application/json'
request["cache-control"] = 'no-cache'
request.body = "{\n\t\"route_quote_id\": \"4\",\n\t\"order_id\": \"abc-123\",\n\t\"order_date\": \"08-15-2099\",\n\t\"order_insured\": \"true\",\n\t\"order_base_value\": \"67.50\",\n\t\"order_base_total\": \"67.50\",\n\t\"shipping_total\": \"7.49\",\n\t\"handling_total\": \"0\",\n\t\"sales_tax_total\": \"4.56\",\n\t\"customer_paid_insurance_price\": \"3.49\",\n\t\"payment_type\": \"CREDIT_CARD\",\n\t\"card_last_four\": \"0000\",\n\t\"card_expire_date\": \"12/99\",\n\t\"first_name\": \"Bob\",\n\t\"last_name\": \"Denver\",\n\t\"email\": \"test@test.test\",\n\t\"phone\": \"111-111-1111\",\n\t\"shipping_address_1\": \"123 Palm Tree Way\",\n\t\"shipping_city\": \"Island Town\",\n\t\"shipping_province\": \"Hawaii\",\n\t\"shipping_postal_code\": \"00000\",\n\t\"shipping_country\": \"USA\",\n\t\"billing_same_as_shipping\": \"true\"\n}"

response = http.request(request)
puts response.read_body
                            </pre>
                        </div>
                        <div id="php" class="tab-pane fade">
                            <h5>PHP - HttpRequest</h5>
                            <pre>
&lt;?php

$request = new HttpRequest();
$request->setUrl('https://api.routeit.cloud/open_api/v1/post/json/insure_order');
$request->setMethod(HTTP_METH_POST);

$request->setHeaders(array(
  'cache-control' => 'no-cache',
  'content-type' => 'application/json',
  'secret' => '<strong style="color: red">YOUR-SECRET-HERE</strong>',
  'api_key' => '<strong style="color: red">YOUR-KEY-HERE</strong>'
));

$request->setBody('{
	"route_quote_id": "4",
	"order_id": "abc-123",
	"order_date": "08-15-2099",
	"order_insured": "true",
	"order_base_value": "67.50",
	"order_base_total": "67.50",
	"shipping_total": "7.49",
	"handling_total": "0",
	"sales_tax_total": "4.56",
	"customer_paid_insurance_price": "3.49",
	"payment_type": "CREDIT_CARD",
	"card_last_four": "0000",
	"card_expire_date": "12/99",
	"first_name": "Bob",
	"last_name": "Denver",
	"email": "test@test.test",
	"phone": "111-111-1111",
	"shipping_address_1": "123 Palm Tree Way",
	"shipping_city": "Island Town",
	"shipping_province": "Hawaii",
	"shipping_postal_code": "00000",
	"shipping_country": "USA",
	"billing_same_as_shipping": "true"
}');

try {
  $response = $request->send();

  echo $response->getBody();
} catch (HttpException $ex) {
  echo $ex;
}
                            </pre>
                            <h5>PHP - peci_http</h5>
                            <pre>
&lt;?php

$client = new http\Client;
$request = new http\Client\Request;

$body = new http\Message\Body;
$body->append('{
	"route_quote_id": "4",
	"order_id": "abc-123",
	"order_date": "08-15-2099",
	"order_insured": "true",
	"order_base_value": "67.50",
	"order_base_total": "67.50",
	"shipping_total": "7.49",
	"handling_total": "0",
	"sales_tax_total": "4.56",
	"customer_paid_insurance_price": "3.49",
	"payment_type": "CREDIT_CARD",
	"card_last_four": "0000",
	"card_expire_date": "12/99",
	"first_name": "Bob",
	"last_name": "Denver",
	"email": "test@test.test",
	"phone": "111-111-1111",
	"shipping_address_1": "123 Palm Tree Way",
	"shipping_city": "Island Town",
	"shipping_province": "Hawaii",
	"shipping_postal_code": "00000",
	"shipping_country": "USA",
	"billing_same_as_shipping": "true"
}');

$request->setRequestUrl('https://api.routeit.cloud/open_api/v1/post/json/insure_order');
$request->setRequestMethod('POST');
$request->setBody($body);

$request->setHeaders(array(
  'cache-control' => 'no-cache',
  'content-type' => 'application/json',
  'secret' => '<strong style="color: red">YOUR-SECRET-HERE</strong>',
  'api_key' => '<strong style="color: red">YOUR-KEY-HERE</strong>'
));

$client->enqueue($request)->send();
$response = $client->getResponse();

echo $response->getBody();
                            </pre>
                            <h5>PHP - cURL</h5>
                            <pre>
&lt;?php

$curl = curl_init();

curl_setopt_array($curl, array(
  CURLOPT_URL => "https://api.routeit.cloud/open_api/v1/post/json/insure_order",
  CURLOPT_RETURNTRANSFER => true,
  CURLOPT_ENCODING => "",
  CURLOPT_MAXREDIRS => 10,
  CURLOPT_TIMEOUT => 30,
  CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
  CURLOPT_CUSTOMREQUEST => "POST",
  CURLOPT_POSTFIELDS => "{\n\t\"route_quote_id\": \"4\",\n\t\"order_id\": \"abc-123\",\n\t\"order_date\": \"08-15-2099\",\n\t\"order_insured\": \"true\",\n\t\"order_base_value\": \"67.50\",\n\t\"order_base_total\": \"67.50\",\n\t\"shipping_total\": \"7.49\",\n\t\"handling_total\": \"0\",\n\t\"sales_tax_total\": \"4.56\",\n\t\"customer_paid_insurance_price\": \"3.49\",\n\t\"payment_type\": \"CREDIT_CARD\",\n\t\"card_last_four\": \"0000\",\n\t\"card_expire_date\": \"12/99\",\n\t\"first_name\": \"Bob\",\n\t\"last_name\": \"Denver\",\n\t\"email\": \"test@test.test\",\n\t\"phone\": \"111-111-1111\",\n\t\"shipping_address_1\": \"123 Palm Tree Way\",\n\t\"shipping_city\": \"Island Town\",\n\t\"shipping_province\": \"Hawaii\",\n\t\"shipping_postal_code\": \"00000\",\n\t\"shipping_country\": \"USA\",\n\t\"billing_same_as_shipping\": \"true\"\n}",
  CURLOPT_HTTPHEADER => array(
    "api_key: <strong style="color: red">YOUR-KEY-HERE</strong>",
    "cache-control: no-cache",
    "content-type: application/json",
    "secret: <strong style="color: red">YOUR-SECRET-HERE</strong>"
  ),
));

$response = curl_exec($curl);
$err = curl_error($curl);

curl_close($curl);

if ($err) {
  echo "cURL Error #:" . $err;
} else {
  echo $response;
}
                            </pre>
                        </div>
                        <div id="other" class="tab-pane fade">
                            <h5>HTTP</h5>
                            <pre>
POST /open_api/v1/post/json/insure_order HTTP/1.1
Host: api.routeit.cloud
api_key: <strong style="color: red">YOUR-KEY-HERE</strong>
secret: <strong style="color: red">YOUR-SECRET-HERE</strong>
Content-Type: application/json
Cache-Control: no-cache

{
	"route_quote_id": "4",
	"order_id": "abc-123",
	"order_date": "08-15-2099",
	"order_insured": "true",
	"order_base_value": "67.50",
	"order_base_total": "67.50",
	"shipping_total": "7.49",
	"handling_total": "0",
	"sales_tax_total": "4.56",
	"customer_paid_insurance_price": "3.49",
	"payment_type": "CREDIT_CARD",
	"card_last_four": "0000",
	"card_expire_date": "12/99",
	"first_name": "Bob",
	"last_name": "Denver",
	"email": "test@test.test",
	"phone": "111-111-1111",
	"shipping_address_1": "123 Palm Tree Way",
	"shipping_city": "Island Town",
	"shipping_province": "Hawaii",
	"shipping_postal_code": "00000",
	"shipping_country": "USA",
	"billing_same_as_shipping": "true"
}
                            </pre>
                            <h5>Shell - wget</h5>
                            <pre>
wget --quiet \
  --method POST \
  --header 'api_key: <strong style="color: red">YOUR-KEY-HERE</strong>' \
  --header 'secret: <strong style="color: red">YOUR-SECRET-HERE</strong>' \
  --header 'content-type: application/json' \
  --header 'cache-control: no-cache' \
  --body-data '{\n	"route_quote_id": "4",\n	"order_id": "abc-123",\n	"order_date": "08-15-2099",\n	"order_insured": "true",\n	"order_base_value": "67.50",\n	"order_base_total": "67.50",\n	"shipping_total": "7.49",\n	"handling_total": "0",\n	"sales_tax_total": "4.56",\n	"customer_paid_insurance_price": "3.49",\n	"payment_type": "CREDIT_CARD",\n	"card_last_four": "0000",\n	"card_expire_date": "12/99",\n	"first_name": "Bob",\n	"last_name": "Denver",\n	"email": "test@test.test",\n	"phone": "111-111-1111",\n	"shipping_address_1": "123 Palm Tree Way",\n	"shipping_city": "Island Town",\n	"shipping_province": "Hawaii",\n	"shipping_postal_code": "00000",\n	"shipping_country": "USA",\n	"billing_same_as_shipping": "true"\n}' \
  --output-document \
  - https://api.routeit.cloud/open_api/v1/post/json/insure_order
                            </pre>
                            <h5>Shell - Httpie</h5>
                            <pre>
echo '{
	"route_quote_id": "4",
	"order_id": "abc-123",
	"order_date": "08-15-2099",
	"order_insured": "true",
	"order_base_value": "67.50",
	"order_base_total": "67.50",
	"shipping_total": "7.49",
	"handling_total": "0",
	"sales_tax_total": "4.56",
	"customer_paid_insurance_price": "3.49",
	"payment_type": "CREDIT_CARD",
	"card_last_four": "0000",
	"card_expire_date": "12/99",
	"first_name": "Bob",
	"last_name": "Denver",
	"email": "test@test.test",
	"phone": "111-111-1111",
	"shipping_address_1": "123 Palm Tree Way",
	"shipping_city": "Island Town",
	"shipping_province": "Hawaii",
	"shipping_postal_code": "00000",
	"shipping_country": "USA",
	"billing_same_as_shipping": "true"
}' |  \
  http POST https://api.routeit.cloud/open_api/v1/post/json/insure_order \
  api_key:<strong style="color: red">YOUR-KEY-HERE</strong> \
  cache-control:no-cache \
  content-type:application/json \
  secret:<strong style="color: red">YOUR-SECRET-HERE</strong>
                            </pre>
                            <h5>Shell - cURL</h5>
                            <pre>
curl --request POST \
  --url https://api.routeit.cloud/open_api/v1/post/json/insure_order \
  --header 'api_key: <strong style="color: red">YOUR-KEY-HERE</strong>' \
  --header 'cache-control: no-cache' \
  --header 'content-type: application/json' \
  --header 'secret: <strong style="color: red">YOUR-SECRET-HERE</strong>' \
  --data '{\n	"route_quote_id": "4",\n	"order_id": "abc-123",\n	"order_date": "08-15-2099",\n	"order_insured": "true",\n	"order_base_value": "67.50",\n	"order_base_total": "67.50",\n	"shipping_total": "7.49",\n	"handling_total": "0",\n	"sales_tax_total": "4.56",\n	"customer_paid_insurance_price": "3.49",\n	"payment_type": "CREDIT_CARD",\n	"card_last_four": "0000",\n	"card_expire_date": "12/99",\n	"first_name": "Bob",\n	"last_name": "Denver",\n	"email": "test@test.test",\n	"phone": "111-111-1111",\n	"shipping_address_1": "123 Palm Tree Way",\n	"shipping_city": "Island Town",\n	"shipping_province": "Hawaii",\n	"shipping_postal_code": "00000",\n	"shipping_country": "USA",\n	"billing_same_as_shipping": "true"\n}'
                            </pre>
                            <h5>cURL</h5>
                            <pre>
curl -X POST -H "api_key: <strong style="color: red">YOUR-KEY-HERE</strong>" -H "secret: <strong style="color: red">YOUR-SECRET-HERE</strong>" -H "Content-Type: application/json" -H "Cache-Control: no-cache" -H '{
	"route_quote_id": "4",
	"order_id": "abc-123",
	"order_date": "08-15-2099",
	"order_insured": "true",
	"order_base_value": "67.50",
	"order_base_total": "67.50",
	"shipping_total": "7.49",
	"handling_total": "0",
	"sales_tax_total": "4.56",
	"customer_paid_insurance_price": "3.49",
	"payment_type": "CREDIT_CARD",
	"card_last_four": "0000",
	"card_expire_date": "12/99",
	"first_name": "Bob",
	"last_name": "Denver",
	"email": "test@test.test",
	"phone": "111-111-1111",
	"shipping_address_1": "123 Palm Tree Way",
	"shipping_city": "Island Town",
	"shipping_province": "Hawaii",
	"shipping_postal_code": "00000",
	"shipping_country": "USA",
	"billing_same_as_shipping": "true"
}' "https://api.routeit.cloud/open_api/v1/post/json/insure_order"
                            </pre>
                            <h5>C (LibCurl)</h5>
                            <pre>
CURL *hnd = curl_easy_init();

curl_easy_setopt(hnd, CURLOPT_CUSTOMREQUEST, "POST");
curl_easy_setopt(hnd, CURLOPT_URL, "https://api.routeit.cloud/open_api/v1/post/json/insure_order");

struct curl_slist *headers = NULL;
headers = curl_slist_append(headers, "cache-control: no-cache");
headers = curl_slist_append(headers, "content-type: application/json");
headers = curl_slist_append(headers, "secret: <strong style="color: red">YOUR-SECRET-HERE</strong>");
headers = curl_slist_append(headers, "api_key: <strong style="color: red">YOUR-KEY-HERE</strong>");
curl_easy_setopt(hnd, CURLOPT_HTTPHEADER, headers);

curl_easy_setopt(hnd, CURLOPT_POSTFIELDS, "{\n\t\"route_quote_id\": \"4\",\n\t\"order_id\": \"abc-123\",\n\t\"order_date\": \"08-15-2099\",\n\t\"order_insured\": \"true\",\n\t\"order_base_value\": \"67.50\",\n\t\"order_base_total\": \"67.50\",\n\t\"shipping_total\": \"7.49\",\n\t\"handling_total\": \"0\",\n\t\"sales_tax_total\": \"4.56\",\n\t\"customer_paid_insurance_price\": \"3.49\",\n\t\"payment_type\": \"CREDIT_CARD\",\n\t\"card_last_four\": \"0000\",\n\t\"card_expire_date\": \"12/99\",\n\t\"first_name\": \"Bob\",\n\t\"last_name\": \"Denver\",\n\t\"email\": \"test@test.test\",\n\t\"phone\": \"111-111-1111\",\n\t\"shipping_address_1\": \"123 Palm Tree Way\",\n\t\"shipping_city\": \"Island Town\",\n\t\"shipping_province\": \"Hawaii\",\n\t\"shipping_postal_code\": \"00000\",\n\t\"shipping_country\": \"USA\",\n\t\"billing_same_as_shipping\": \"true\"\n}");

CURLcode ret = curl_easy_perform(hnd);
                            </pre>
                            <h5>Objective-C (NSURL)</h5>
                            <pre>
#import &lt;Foundation/Foundation.h&gt;

NSDictionary *headers = @{ @"api_key": @"<strong style="color: red">YOUR-KEY-HERE</strong>",
                           @"secret": @"<strong style="color: red">YOUR-SECRET-HERE</strong>",
                           @"content-type": @"application/json",
                           @"cache-control": @"no-cache" };
NSDictionary *parameters = @{ @"route_quote_id": @"4",
                              @"order_id": @"abc-123",
                              @"order_date": @"08-15-2099",
                              @"order_insured": @"true",
                              @"order_base_value": @"67.50",
                              @"order_base_total": @"67.50",
                              @"shipping_total": @"7.49",
                              @"handling_total": @"0",
                              @"sales_tax_total": @"4.56",
                              @"customer_paid_insurance_price": @"3.49",
                              @"payment_type": @"CREDIT_CARD",
                              @"card_last_four": @"0000",
                              @"card_expire_date": @"12/99",
                              @"first_name": @"Bob",
                              @"last_name": @"Denver",
                              @"email": @"test@test.test",
                              @"phone": @"111-111-1111",
                              @"shipping_address_1": @"123 Palm Tree Way",
                              @"shipping_city": @"Island Town",
                              @"shipping_province": @"Hawaii",
                              @"shipping_postal_code": @"00000",
                              @"shipping_country": @"USA",
                              @"billing_same_as_shipping": @"true" };

NSData *postData = [NSJSONSerialization dataWithJSONObject:parameters options:0 error:nil];

NSMutableURLRequest *request = [NSMutableURLRequest requestWithURL:[NSURL URLWithString:@"https://api.routeit.cloud/open_api/v1/post/json/insure_order"]
                                                       cachePolicy:NSURLRequestUseProtocolCachePolicy
                                                   timeoutInterval:10.0];
[request setHTTPMethod:@"POST"];
[request setAllHTTPHeaderFields:headers];
[request setHTTPBody:postData];

NSURLSession *session = [NSURLSession sharedSession];
NSURLSessionDataTask *dataTask = [session dataTaskWithRequest:request
                                            completionHandler:^(NSData *data, NSURLResponse *response, NSError *error) {
                                                if (error) {
                                                    NSLog(@"%@", error);
                                                } else {
                                                    NSHTTPURLResponse *httpResponse = (NSHTTPURLResponse *) response;
                                                    NSLog(@"%@", httpResponse);
                                                }
                                            }];
[dataTask resume];
                            </pre>
                            <h5>Swift (NSURL)</h5>
                            <pre>
import Foundation

let headers = [
  "api_key": "<strong style="color: red">YOUR-KEY-HERE</strong>",
  "secret": "<strong style="color: red">YOUR-SECRET-HERE</strong>",
  "content-type": "application/json",
  "cache-control": "no-cache"
]
let parameters = [
  "route_quote_id": "4",
  "order_id": "abc-123",
  "order_date": "08-15-2099",
  "order_insured": "true",
  "order_base_value": "67.50",
  "order_base_total": "67.50",
  "shipping_total": "7.49",
  "handling_total": "0",
  "sales_tax_total": "4.56",
  "customer_paid_insurance_price": "3.49",
  "payment_type": "CREDIT_CARD",
  "card_last_four": "0000",
  "card_expire_date": "12/99",
  "first_name": "Bob",
  "last_name": "Denver",
  "email": "test@test.test",
  "phone": "111-111-1111",
  "shipping_address_1": "123 Palm Tree Way",
  "shipping_city": "Island Town",
  "shipping_province": "Hawaii",
  "shipping_postal_code": "00000",
  "shipping_country": "USA",
  "billing_same_as_shipping": "true"
] as [String : Any]

let postData = JSONSerialization.data(withJSONObject: parameters, options: [])

let request = NSMutableURLRequest(url: NSURL(string: "https://api.routeit.cloud/open_api/v1/post/json/insure_order")! as URL,
                                        cachePolicy: .useProtocolCachePolicy,
                                    timeoutInterval: 10.0)
request.httpMethod = "POST"
request.allHTTPHeaderFields = headers
request.httpBody = postData as Data

let session = URLSession.shared
let dataTask = session.dataTask(with: request as URLRequest, completionHandler: { (data, response, error) -> Void in
  if (error != nil) {
    print(error)
  } else {
    let httpResponse = response as? HTTPURLResponse
    print(httpResponse)
  }
})

dataTask.resume()
                            </pre>
                            <h5>OCaml (Cohttp)</h5>
                            <pre>
open Cohttp_lwt_unix
open Cohttp
open Lwt

let uri = Uri.of_string "https://api.routeit.cloud/open_api/v1/post/json/insure_order" in
let headers = Header.init ()
  |> fun h -> Header.add h "api_key" "<strong style="color: red">YOUR-KEY-HERE</strong>"
  |> fun h -> Header.add h "secret" "<strong style="color: red">YOUR-SECRET-HERE</strong>"
  |> fun h -> Header.add h "content-type" "application/json"
  |> fun h -> Header.add h "cache-control" "no-cache"
in
let body = Cohttp_lwt_body.of_string "{\n\t\"route_quote_id\": \"4\",\n\t\"order_id\": \"abc-123\",\n\t\"order_date\": \"08-15-2099\",\n\t\"order_insured\": \"true\",\n\t\"order_base_value\": \"67.50\",\n\t\"order_base_total\": \"67.50\",\n\t\"shipping_total\": \"7.49\",\n\t\"handling_total\": \"0\",\n\t\"sales_tax_total\": \"4.56\",\n\t\"customer_paid_insurance_price\": \"3.49\",\n\t\"payment_type\": \"CREDIT_CARD\",\n\t\"card_last_four\": \"0000\",\n\t\"card_expire_date\": \"12/99\",\n\t\"first_name\": \"Bob\",\n\t\"last_name\": \"Denver\",\n\t\"email\": \"test@test.test\",\n\t\"phone\": \"111-111-1111\",\n\t\"shipping_address_1\": \"123 Palm Tree Way\",\n\t\"shipping_city\": \"Island Town\",\n\t\"shipping_province\": \"Hawaii\",\n\t\"shipping_postal_code\": \"00000\",\n\t\"shipping_country\": \"USA\",\n\t\"billing_same_as_shipping\": \"true\"\n}" in

Client.call ~headers ~body `POST uri
>>= fun (res, body_stream) ->
  (* Do stuff with the result *)
                            </pre>
                        </div>
                    </div>
                </div>
            </div>
            <h3>Response:</h3>
            <div class="row">
                <div class="hidden-xs col-sm-1">&nbsp;</div>
                <div class="col-xs-12 col-sm-11">
                    The response returns JSON and consists of the result, error_messages, and data.
                    <pre>
{
  "result": "SUCCESS",
  "error_messages": null,
  "data": {
    "route_order_id": 2,
    "route_quote_id": 5,
    "order_id": "abc-123",
    "order_date": "08-15-2099",
    "order_type": "PRODUCT",
    "order_insured": true,
    "order_base_value": 67.5,
    "order_base_total": 67.5,
    "shipping_total": 7.49,
    "handling_total": 0,
    "sales_tax_total": 4.56,
    "currency_code": "USD",
    "exchange_rate": 1,
    "insurance_cost_usd": 2.95,
    "customer_paid_insurance_price": 3.49,
    "payment_type": "CREDIT_CARD",
    "payment_type_other": null,
    "card_last_four": "0000",
    "card_expire_date": "12/99",
    "first_name": "Bob",
    "last_name": "Denver",
    "email": "test@test.test",
    "phone": "111-111-1111",
    "billing_address_1": "123 Palm Tree Way",
    "billing_address_2": null,
    "billing_city": "Island Town",
    "billing_province": "Hawaii",
    "billing_postal_code": "00000",
    "billing_country": "USA",
    "billing_same_as_shipping": true,
    "shipping_address_1": "123 Palm Tree Way",
    "shipping_address_2": null,
    "shipping_city": "Island Town",
    "shipping_province": "Hawaii",
    "shipping_postal_code": "00000",
    "shipping_country": "USA",
    "shipping_carrier_code": null,
    "shipping_method": null,
    "shipped_date": null,
    "tracking_number": null,
    "items": [
      {
        "item_sku": null,
        "item_id": "test_item_WN5",
        "item_qty": 2,
        "item_name": "Widget Number 5",
        "item_desc": null,
        "item_cost_per_unit": 33.75,
        "item_value_per_unit": 33.75
      }
    ],
    "custom_b1": null,
    "custom_b2": null,
    "custom_b3": null,
    "custom_b4": null,
    "ip_address": "192.182.192.205",
    "created": 1481849367948
  }
}
                </pre>
                <table class="table table-bordered">
                    <tr style="background-color: black; color: white">
                        <th>Response Body Key</th>
                        <th>Response Body Value</th>
                        <th>Description</th>
                    </tr>
                    <tr>
                        <td>result</td>
                        <td>SUCCESS or ERROR</td>
                        <td>Only returns SUCCESS or ERROR.</td>
                    </tr>
                    <tr>
                        <td>error_messages</td>
                        <td>null or error messages</td>
                        <td>Returns null if result is SUCCESS or a string[] of errors if result is ERROR.</td>
                    </tr>
                    <tr>
                        <td>data</td>
                        <td>data envelope or null</td>
                        <td>Returns data envelope if result is SUCCESS or null if result is ERROR.</td>
                    </tr>
                    <tr>
                        <td>route_order_id</td>
                        <td>integer</td>
                        <td>The Route Order ID.</td>
                    </tr>
                    <tr>
                        <td>route_quote_id</td>
                        <td>integer</td>
                        <td>The Route Quote ID.</td>
                    </tr>
                    <tr>
                        <td>order_id</td>
                        <td>string</td>
                        <td>Reiterates the value sent in to confirm acceptance.</td>
                    </tr>
                    <tr>
                        <td>order_date</td>
                        <td>string</td>
                        <td>Reiterates the value sent in to confirm acceptance.</td>
                    </tr>
                    <tr>
                        <td>order_type</td>
                        <td>string</td>
                        <td>Reiterates the value sent in with Request Quote.</td>
                    </tr>
                    <tr>
                        <td>order_insured</td>
                        <td>boolean</td>
                        <td>Reiterates the value sent in to confirm acceptance.</td>
                    </tr>
                    <tr>
                        <td>order_base_value</td>
                        <td>float</td>
                        <td>Reiterates the value sent in to confirm acceptance.</td>
                    </tr>
                    <tr>
                        <td>order_base_total</td>
                        <td>float</td>
                        <td>Reiterates the value sent in to confirm acceptance.</td>
                    </tr>
                    <tr>
                        <td>shipping_total</td>
                        <td>float</td>
                        <td>Reiterates the value sent in to confirm acceptance.</td>
                    </tr>
                    <tr>
                        <td>handling_total</td>
                        <td>float</td>
                        <td>Reiterates the value sent in to confirm acceptance.</td>
                    </tr>
                    <tr>
                        <td>sales_tax_total</td>
                        <td>float</td>
                        <td>Reiterates the value sent in to confirm acceptance.</td>
                    </tr>
                    <tr>
                        <td>currency_code</td>
                        <td>string</td>
                        <td>Reiterates the value sent in with Request Quote.</td>
                    </tr>
                    <tr>
                        <td>exchange_rate</td>
                        <td>float</td>
                        <td>Exchange rate of USD to order_currency_code at the time of quote.</td>
                    </tr>
                    <tr>
                        <td>insurance_cost_usd</td>
                        <td>float</td>
                        <td>Total cost of insurance USD, that Route will charge the merchant to insure this order.</td>
                    </tr>
                    <tr>
                        <td>customer_paid_insurance_price</td>
                        <td>float</td>
                        <td>Reiterates the value sent in to confirm acceptance.</td>
                    </tr>
                    <tr>
                        <td>payment_type</td>
                        <td>string</td>
                        <td>Reiterates the value sent in to confirm acceptance.</td>
                    </tr>
                    <tr>
                        <td>payment_type_other</td>
                        <td>string</td>
                        <td>Reiterates the value sent in to confirm acceptance.</td>
                    </tr>
                    <tr>
                        <td>card_last_four</td>
                        <td>string</td>
                        <td>Reiterates the value sent in to confirm acceptance.</td>
                    </tr>
                    <tr>
                        <td>card_expire_date</td>
                        <td>string</td>
                        <td>Reiterates the value sent in to confirm acceptance.</td>
                    </tr>
                    <tr>
                        <td>first_name</td>
                        <td>string</td>
                        <td>Reiterates the value sent in to confirm acceptance.</td>
                    </tr>
                    <tr>
                        <td>last_name</td>
                        <td>string</td>
                        <td>Reiterates the value sent in to confirm acceptance.</td>
                    </tr>
                    <tr>
                        <td>email</td>
                        <td>string</td>
                        <td>Reiterates the value sent in to confirm acceptance.</td>
                    </tr>
                    <tr>
                        <td>phone</td>
                        <td>string</td>
                        <td>Reiterates the value sent in to confirm acceptance.</td>
                    </tr>
                    <tr>
                        <td>shipping_address_1</td>
                        <td>string</td>
                        <td>Reiterates the value sent in to confirm acceptance.</td>
                    </tr>
                    <tr>
                        <td>shipping_address_2</td>
                        <td>string</td>
                        <td>Reiterates the value sent in to confirm acceptance.</td>
                    </tr>
                    <tr>
                        <td>shipping_city</td>
                        <td>string</td>
                        <td>Reiterates the value sent in to confirm acceptance.</td>
                    </tr>
                    <tr>
                        <td>shipping_province</td>
                        <td>string</td>
                        <td>Reiterates the value sent in to confirm acceptance.</td>
                    </tr>
                    <tr>
                        <td>shipping_postal_code</td>
                        <td>string</td>
                        <td>Reiterates the value sent in to confirm acceptance.</td>
                    </tr>
                    <tr>
                        <td>shipping_country</td>
                        <td>string</td>
                        <td>Reiterates the value sent in to confirm acceptance.</td>
                    </tr>
                    <tr>
                        <td>billing_same_as_shipping</td>
                        <td>boolean</td>
                        <td>Reiterates the value sent in to confirm acceptance.</td>
                    </tr>
                    <tr>
                        <td>billing_address_1</td>
                        <td>string</td>
                        <td>Reiterates the value sent in to confirm acceptance.</td>
                    </tr>
                    <tr>
                        <td>billing_address_2</td>
                        <td>string</td>
                        <td>Reiterates the value sent in to confirm acceptance.</td>
                    </tr>
                    <tr>
                        <td>billing_city</td>
                        <td>string</td>
                        <td>Reiterates the value sent in to confirm acceptance.</td>
                    </tr>
                    <tr>
                        <td>billing_province</td>
                        <td>string</td>
                        <td>Reiterates the value sent in to confirm acceptance.</td>
                    </tr>
                    <tr>
                        <td>billing_postal_code</td>
                        <td>string</td>
                        <td>Reiterates the value sent in to confirm acceptance.</td>
                    </tr>
                    <tr>
                        <td>billing_country</td>
                        <td>string</td>
                        <td>Reiterates the value sent in to confirm acceptance.</td>
                    </tr>
                    <tr>
                        <td>shipping_carrier_code</td>
                        <td>string</td>
                        <td>Reiterates the value sent in to confirm acceptance.</td>
                    </tr>
                    <tr>
                        <td>shipping_method</td>
                        <td>string</td>
                        <td>Reiterates the value sent in to confirm acceptance.</td>
                    </tr>
                    <tr>
                        <td>shipped_date</td>
                        <td>string</td>
                        <td>Reiterates the value sent in to confirm acceptance.</td>
                    </tr>
                    <tr>
                        <td>tracking_number</td>
                        <td>string</td>
                        <td>Reiterates the value sent in to confirm acceptance.</td>
                    </tr>
                    <tr>
                        <td>items</td>
                        <td>item array</td>
                        <td>Reiterates the array sent in to confirm acceptance.</td>
                    </tr>
                    <tr>
                        <td>item_sku</td>
                        <td>string</td>
                        <td>Reiterates the value sent in to confirm acceptance.</td>
                    </tr>
                    <tr>
                        <td>item_id</td>
                        <td>string</td>
                        <td>Reiterates the value sent in to confirm acceptance.</td>
                    </tr>
                    <tr>
                        <td>item_qty</td>
                        <td>integer</td>
                        <td>Reiterates the value sent in to confirm acceptance.</td>
                    </tr>
                    <tr>
                        <td>item_name</td>
                        <td>string</td>
                        <td>Reiterates the value sent in to confirm acceptance.</td>
                    </tr>
                    <tr>
                        <td>item_desc</td>
                        <td>string</td>
                        <td>Reiterates the value sent in to confirm acceptance.</td>
                    </tr>
                    <tr>
                        <td>item_cost_per_unit</td>
                        <td>float</td>
                        <td>Reiterates the value sent in to confirm acceptance.</td>
                    </tr>
                    <tr>
                        <td>item_value_per_unit</td>
                        <td>float</td>
                        <td>Reiterates the value sent in to confirm acceptance.</td>
                    </tr>
                    <tr>
                        <td>custom_b1</td>
                        <td>string</td>
                        <td>Reiterates the value sent in to confirm acceptance.</td>
                    </tr>
                    <tr>
                        <td>custom_b2</td>
                        <td>string</td>
                        <td>Reiterates the value sent in to confirm acceptance.</td>
                    </tr>
                    <tr>
                        <td>custom_b3</td>
                        <td>string</td>
                        <td>Reiterates the value sent in to confirm acceptance.</td>
                    </tr>
                    <tr>
                        <td>custom_b4</td>
                        <td>string</td>
                        <td>Reiterates the value sent in to confirm acceptance.</td>
                    </tr>
                    <tr>
                        <td>ip_address</td>
                        <td>string</td>
                        <td>IP address of the server making the request.</td>
                    </tr>
                    <tr>
                        <td>created</td>
                        <td>timestamp</td>
                        <td>Timestamp of the request.</td>
                    </tr>
                </table>
            </div>
        </div>
        <h3>Errors:</h3>
        <div class="row">
            <div class="hidden-xs col-sm-1">&nbsp;</div>
            <div class="col-xs-12 col-sm-11">
                This API can return the following errors as strings:
                <table class="table table-bordered">
                    <tr>
                        <td>This API requires request method POST.</td>
                    </tr>
                    <tr>
                        <td>This API call must include a valid partner_key in the header to authenticate.</td>
                    </tr>
                    <tr>
                        <td>Invalid api_key or secret.</td>
                    </tr>
                    <tr>
                        <td>No billing setup for these keys. Live API requests require valid billing. If this is a test, use the Test API keys.</td>
                    </tr>
                    <tr>
                        <td>Quote for route_quote_id ##### has already been used. Quotes can only be applied to a single order.</td>
                    </tr>
                    <tr>
                        <td>Quote for route_quote_id ##### has expired. Quotes must be used within 24 hours.</td>
                    </tr>
                    <tr>
                        <td>No quote exists for given api_key ##### and route_quote_id #####.</td>
                    </tr>
                    <tr>
                        <td>Missing ALL required parameters.</td>
                    </tr>
                    <tr>
                        <td>Missing required parameter order_id.</td>
                    </tr>
                    <tr>
                        <td>Missing required parameter order_date.</td>
                    </tr>
                    <tr>
                        <td>Parameter order_base_value must be greater than zero.</td>
                    </tr>
                    <tr>
                        <td>Parameter order_base_value is required to match the order_base_value used to generate quote_id #####.</td>
                    </tr>
                    <tr>
                        <td>Parameter order_base_total must be zero or greater.</td>
                    </tr>
                    <tr>
                        <td>Parameter order_base_total is required to match order_base_total used to generate quote_id #####.</td>
                    </tr>
                    <tr>
                        <td>Parameter shipping_total must be zero or greater.</td>
                    </tr>
                    <tr>
                        <td>Parameter handling_total must be zero or greater.</td>
                    </tr>
                    <tr>
                        <td>Parameter sales_tax_total must be zero or greater.</td>
                    </tr>
                    <tr>
                        <td>Parameter customer_paid_insurance_price must be zero or greater.</td>
                    </tr>
                    <tr>
                        <td>Missing required parameter payment_type.</td>
                    </tr>
                    <tr>
                        <td>Parameter card_last_four is required when payment_type is CREDIT_CARD.</td>
                    </tr>
                    <tr>
                        <td>Parameter card_expire_date is required when payment_type is CREDIT_CARD.</td>
                    </tr>
                    <tr>
                        <td>Parameter payment_type_other is required when payment_type is OTHER.</td>
                    </tr>
                    <tr>
                        <td>Missing required parameter first_name.</td>
                    </tr>
                    <tr>
                        <td>Missing required parameter last_name.</td>
                    </tr>
                    <tr>
                        <td>Missing required parameter email.</td>
                    </tr>
                    <tr>
                        <td>Missing required parameter phone.</td>
                    </tr>
                    <tr>
                        <td>Missing required parameter shipping_address_1.</td>
                    </tr>
                    <tr>
                        <td>Missing required parameter shipping_city.</td>
                    </tr>
                    <tr>
                        <td>Missing required parameter shipping_province.</td>
                    </tr>
                    <tr>
                        <td>Missing required parameter shipping_postal_code.</td>
                    </tr>
                    <tr>
                        <td>Missing required parameter shipping_country.</td>
                    </tr>
                    <tr>
                        <td>Value for shipping_carrier_code ### is invalid.</td>
                    </tr>
                    <tr>
                        <td>Missing required parameter billing_address_1.</td>
                    </tr>
                    <tr>
                        <td>Missing required parameter billing_city.</td>
                    </tr>
                    <tr>
                        <td>Missing required parameter billing_province.</td>
                    </tr>
                    <tr>
                        <td>Missing required parameter billing_postal_code.</td>
                    </tr>
                    <tr>
                        <td>Missing required parameter shipping_country.</td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
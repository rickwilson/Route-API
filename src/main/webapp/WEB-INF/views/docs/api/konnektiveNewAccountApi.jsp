<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Route Open API - Konnektive Add Account</title>
    <jsp:include page="/WEB-INF/views/docs/header-include.jsp" flush="false" />
</head>
<body>
<jsp:include page="/WEB-INF/views/docs/sideMenu.jsp" flush="true" >
    <jsp:param name="page" value="0" />
</jsp:include>
<div class="container-fluid myTestContainer">
            <h1>Open API - Konnektive Add Account</h1>
            <h3>Postman:</h3>
            <div class="row">
                <div class="hidden-xs col-sm-1">&nbsp;</div>
                <div class="col-xs-12 col-sm-11">https://documenter.getpostman.com/view/970856/tests4konnektive/2PPYnu</div>
            </div>
            <h3>API Endpoint:</h3>
            <div class="row">
                <div class="hidden-xs col-sm-1">&nbsp;</div>
                <div class="col-xs-12 col-sm-11">
                    <strong>URL: </strong> https://api.routeit.cloud/konnektive/v2/add/account/json
                    <br>
                    <strong>Protocal: </strong> HTTPS
                    <br>
                    <strong>Method: </strong> POST
                    <br>
                    <strong>Authentication: </strong> API Key via header
                    <br>
                    <strong>Content Type: </strong> application/json
                    <br><br>
                    All API calls must include this header to authorize the request.<br>
                    Replace <strong style="color: red">YOUR-KEY-HERE</strong> with your partner key.
                    <table class="table table-bordered">
                        <tr style="background-color: black; color: white">
                            <th>Header Key</th>
                            <th>Header Value</th>
                        </tr>
                        <tr>
                            <td>partner_key</td>
                            <td>YOUR-KEY-HERE</td>
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
    "company_name": "Test Widgets",
    "first": "Bob",
    "last": "Denver",
    "email": "bob@testwidgets.test",
    "phone": "111-111-1111",
    “transactions”: “5000”
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
                            <td>company_name</td>
                            <td>Company name</td>
                            <td>YES</td>
                            <td>Unique company name.</td>
                        </tr>
                        <tr>
                            <td>first</td>
                            <td>First name</td>
                            <td>YES</td>
                            <td>Account owner’s first name.</td>
                        </tr>
                        <tr>
                            <td>last</td>
                            <td>Last name</td>
                            <td>YES</td>
                            <td>Account owner’s last name.</td>
                        </tr>
                        <tr>
                            <td>email</td>
                            <td>Email address</td>
                            <td>YES</td>
                            <td>Account owner’s email address.</td>
                        </tr>
                        <tr>
                            <td>phone</td>
                            <td>Phone number</td>
                            <td>YES</td>
                            <td>Account owner’s phone number.</td>
                        </tr>
                        <tr>
                            <td>transactions</td>
                            <td>Average transactions</td>
                            <td>no</td>
                            <td>Account’s average transactions per month or year.</td>
                        </tr>
                    </table>
                </div>
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
  "url": "http://api.routeit.cloud/konnektive/v2/add/account/json",
  "method": "POST",
  "headers": {
    "partner_key": "YOUR-KEY-HERE",
    "content-type": "application/json",
    "cache-control": "no-cache"
  },
  "processData": false,
  "data": "{\r\"company_name\": \"Test Widgets\",\r\t\"first\": \"Bob\",\r\t\"last\": \"Denver\",\r\t\"email\": \"bob@testwidgets.test\",\r\t\"phone\": \"111-111-1111\",\r\t\"transactions\": \"5000\"\r}"
}

$.ajax(settings).done(function (response) {
  console.log(response);
});
                            </pre>
                            <h5>Javascript - XHR</h5>
                            <pre>
var data = JSON.stringify({
  "company_name": "Test Widgets",
  "first": "Bob",
  "last": "Denver",
  "email": "bob@testwidgets.test",
  "phone": "111-111-1111",
  "transactions": "5000"
});

var xhr = new XMLHttpRequest();
xhr.withCredentials = true;

xhr.addEventListener("readystatechange", function () {
  if (this.readyState === 4) {
    console.log(this.responseText);
  }
});

xhr.open("POST", "http://api.routeit.cloud/konnektive/v2/add/account/json");
xhr.setRequestHeader("partner_key", "YOUR-KEY-HERE");
xhr.setRequestHeader("content-type", "application/json");
xhr.setRequestHeader("cache-control", "no-cache");

xhr.send(data);
                            </pre>
                        </div>
                        <div id="nodejs" class="tab-pane fade">
                            <h5>Node JS - Native</h5>
                            <pre>
var http = require("http");

var options = {
  "method": "POST",
  "hostname": "api.routeit.cloud",
  "port": null,
  "path": "/konnektive/v2/add/account/json",
  "headers": {
    "partner_key": "YOUR-KEY-HERE",
    "content-type": "application/json",
    "cache-control": "no-cache"
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
                            </pre>
                            <h5>Node JS - Request</h5>
                            <pre>
var request = require("request");

var options = { method: 'POST',
  url: 'http://api.routeit.cloud/konnektive/v2/add/account/json',
  headers:
   { 'cache-control': 'no-cache',
     'content-type': 'application/json',
     partner_key: 'YOUR-KEY-HERE' },
  body:
   { company_name: 'Test Widgets',
     first: 'Bob',
     last: 'Denver',
     email: 'bob@testwidgets.test',
     phone: '111-111-1111',
     transactions: '5000' },
  json: true };

request(options, function (error, response, body) {
  if (error) throw new Error(error);

  console.log(body);
});
                            </pre>
                            <h5>Node JS - Unirest</h5>
                            <pre>
var unirest = require("unirest");

var req = unirest("POST", "http://api.routeit.cloud/konnektive/v2/add/account/json");

req.headers({
  "cache-control": "no-cache",
  "content-type": "application/json",
  "partner_key": "YOUR-KEY-HERE"
});

req.type("json");
req.send({
  "company_name": "Test Widgets",
  "first": "Bob",
  "last": "Denver",
  "email": "bob@testwidgets.test",
  "phone": "111-111-1111",
  "transactions": "5000"
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
RequestBody body = RequestBody.create(mediaType, "{\r\"company_name\": \"Test Widgets\",\r\t\"first\": \"Bob\",\r\t\"last\": \"Denver\",\r\t\"email\": \"bob@testwidgets.test\",\r\t\"phone\": \"111-111-1111\",\r\t\"transactions\": \"5000\"\r}");
Request request = new Request.Builder()
.url("http://api.routeit.cloud/konnektive/v2/add/account/json")
.post(body)
.addHeader("partner_key", "YOUR-KEY-HERE")
.addHeader("content-type", "application/json")
.addHeader("cache-control", "no-cache")
.build();

Response response = client.newCall(request).execute();
                            </pre>
                            <h5>Java - Unirest</h5>
                            <pre>
HttpResponse<String> response = Unirest.post("http://api.routeit.cloud/konnektive/v2/add/account/json")
  .header("partner_key", "YOUR-KEY-HERE")
  .header("content-type", "application/json")
  .header("cache-control", "no-cache")
  .body("{\r\"company_name\": \"Test Widgets\",\r\t\"first\": \"Bob\",\r\t\"last\": \"Denver\",\r\t\"email\": \"bob@testwidgets.test\",\r\t\"phone\": \"111-111-1111\",\r\t\"transactions\": \"5000\"\r}")
  .asString();
                            </pre>
                        </div>
                        <div id="csharp" class="tab-pane fade">
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                        </div>
                        <div id="go" class="tab-pane fade">
                            <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
                        </div>
                        <div id="python" class="tab-pane fade">
                            <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam.</p>
                        </div>
                        <div id="ruby" class="tab-pane fade">
                            <p>Eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.</p>
                        </div>
                        <div id="php" class="tab-pane fade">
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                        </div>
                        <div id="other" class="tab-pane fade">
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
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
        "company_name": "Test Widgets",
        "first": "Bob",
        "last": "Denver",
        "email": "bob@testwidgets.test",
        "phone": "111-111-1111",
        "transactions": "5000",
        "api_key": "LIVE_ak_u74592ba92ftrew3jn67l733d81dea29",
        "secret": "LIVE_s_d522a889dfhl0dc5hj4i52a2068f961p"
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
                            <td>company_name</td>
                            <td>Company name</td>
                            <td>The unique company name sent in the request with any changes made by the service.</td>
                        </tr>
                        <tr>
                            <td>first</td>
                            <td>First name</td>
                            <td>The first name sent in the request.</td>
                        </tr>
                        <tr>
                            <td>last</td>
                            <td>Last name</td>
                            <td>The last name sent in the request.</td>
                        </tr>
                        <tr>
                            <td>email</td>
                            <td>Email address</td>
                            <td>The unique email address sent in the request.</td>
                        </tr>
                        <tr>
                            <td>phone</td>
                            <td>Phone number</td>
                            <td>The phone number sent in the request.</td>
                        </tr>
                        <tr>
                            <td>transactions</td>
                            <td>Average transactions</td>
                            <td>The transactions sent in the request.</td>
                        </tr>
                        <tr>
                            <td>api_key</td>
                            <td>Customer API key</td>
                            <td>Newly generated customer API key</td>
                        </tr>
                        <tr>
                            <td>secret</td>
                            <td>Customer secret</td>
                            <td>Not needed for Konnektive</td>
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
                        <!--<tr style="background-color: black; color: white">-->
                        <!--<th>Header Key</th>-->
                        <!--<th>Header Value</th>-->
                        <!--</tr>-->
                        <tr>
                            <td>API calls to /konnektive/v2/add/account require request method POST.</td>
                        </tr>
                        <tr>
                            <td>This API call must include a valid partner_key in the header to authenticate.</td>
                        </tr>
                        <tr>
                            <td>This API call requires the following fields, company_name, first, last, email, and phone.</td>
                        </tr>
                        <tr>
                            <td>Missing required parameter company_name. Company name must be three or more characters.</td>
                        </tr>
                        <tr>
                            <td>Missing required parameter first. First name must be three or more characters.</td>
                        </tr>
                        <tr>
                            <td>Missing required parameter last. Last name must be three or more characters.</td>
                        </tr>
                        <tr>
                            <td>Missing required parameter email.</td>
                        </tr>
                        <tr>
                            <td>All new accounts must include a valid and unique email address.</td>
                        </tr>
                        <tr>
                            <td>This email address is associated with an account already.</td>
                        </tr>
                        <tr>
                            <td>Missing required parameter phone. Phone must be three or more characters.</td>
                        </tr>
                    </table>
                </div>
            </div>
</div>

</body>
</html>
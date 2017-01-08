<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Route Open API - Request Quote</title>
    <jsp:include page="/WEB-INF/views/docs/header-include.jsp" flush="false" />
</head>
<body>
<jsp:include page="/WEB-INF/views/docs/sideMenu.jsp" flush="true" >
    <jsp:param name="page" value="1.2" />
</jsp:include>
    <div class="container-fluid myTestContainer">
        <h1>Open API - Request Quote</h1>
        <h3>API Endpoint:</h3>
        <div class="row">
            <div class="hidden-xs col-sm-1">&nbsp;</div>
            <div class="col-xs-12 col-sm-11">
                <strong>URL: </strong> https://api.routeit.cloud/konnektive/v2/post/json/request_quote
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
                The body must be sent in JSON format.
                <pre>
{
    "order_base_value": "67.50",
	"order_base_total": "67.50",
	"order_currency_code": "USD",
	"order_type": "PRODUCT",
	"total_items": "2",
	"items": [{
		"item_id": "test_item_123",
		"item_qty": "2",
		"item_name": "Widget Number 4",
		"item_cost_per_unit": "33.75",
		"item_value_per_unit": "33.75"
	}]
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
                        <td>order_base_value</td>
                        <td>integer, double or float greater than zero and greater than or equal to order_base_total</td>
                        <td>YES</td>
                        <td>Total cart replacement value, minus shipping, handling and tax.</td>
                    </tr>
                    <tr>
                        <td>order_base_total</td>
                        <td>integer, double or float greater than or equal to zero</td>
                        <td>YES</td>
                        <td>Total cart price being charged to buyer, minus shipping, handling and tax. This is the amount the customer is paying after discounts, not the replacement value.</td>
                    </tr>
                    <tr>
                        <td>order_currency_code</td>
                        <td>supported ISO Alpha-3(three letters) currency code as a string</td>
                        <td>YES</td>
                        <td>Currency used for the order. Supported currency codes can be pulled from our <a href="/docs/api/get/codes/currency">Currency Codes API</a>.</td>
                    </tr>
                    <tr>
                        <td>order_type</td>
                        <td>supported order type as string</td>
                        <td>YES</td>
                        <td>Type of order. Supported order types can be pulled from our <a href="/docs/api/get/types/order">Order Types API</a>.</td>
                    </tr>
                    <tr>
                        <td>total_items</td>
                        <td>integer greater than zero</td>
                        <td>YES</td>
                        <td>Total number of items in the cart.</td>
                    </tr>
                    <tr>
                        <td>items</td>
                        <td>item array</td>
                        <td>YES</td>
                        <td>An array of all items in the cart.</td>
                    </tr>
                    <tr>
                        <td>item_sku</td>
                        <td>string</td>
                        <td>no</td>
                        <td>Item SKU.</td>
                    </tr>
                    <tr>
                        <td>item_id</td>
                        <td>string</td>
                        <td>YES</td>
                        <td>The merchants' unique product identifier. Used for claim reference.</td>
                    </tr>
                    <tr>
                        <td>item_qty</td>
                        <td>integer greater than zero</td>
                        <td>YES</td>
                        <td>Total number of this product in the cart.</td>
                    </tr>
                    <tr>
                        <td>item_name</td>
                        <td>string</td>
                        <td>YES</td>
                        <td>Display name of the product, as the customer would see it. Used for claim reference.</td>
                    </tr>
                    <tr>
                        <td>item_desc</td>
                        <td>string</td>
                        <td>no</td>
                        <td>Product description as the customer would see it. Used for claim reference. Max 255 characters.</td>
                    </tr>
                    <tr>
                        <td>item_value_per_unit</td>
                        <td>integer, double or float greater than zero and greater than or equal to item_cost_per_unit</td>
                        <td>YES</td>
                        <td>Replacement value per item.</td>
                    </tr>
                    <tr>
                        <td>item_cost_per_unit</td>
                        <td>integer, double or float greater than or equal to zero</td>
                        <td>YES</td>
                        <td>Product price being charged to buyer per item. This is the amount the customer is paying for one item after discounts, not the replacement value.</td>
                    </tr>
                    <tr>
                        <td>custom_a1</td>
                        <td>string</td>
                        <td>no</td>
                        <td>Custom field that accepts any text string. Max 255 characters.</td>
                    </tr>
                    <tr>
                        <td>custom_a2</td>
                        <td>string</td>
                        <td>no</td>
                        <td>Custom field that accepts any text string. Max 255 characters.</td>
                    </tr>
                    <tr>
                        <td>custom_a3</td>
                        <td>string</td>
                        <td>no</td>
                        <td>Custom field that accepts any text string. Max 255 characters.</td>
                    </tr>
                    <tr>
                        <td>custom_a4</td>
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
  "url": "https://api.routeit.cloud/open_api/v1/post/json/request_quote",
  "method": "POST",
  "headers": {
    "api_key": "<strong style="color: red">YOUR-KEY-HERE</strong>",
    "secret": "<strong style="color: red">YOUR-SECRET-HERE</strong>",
    "content-type": "application/json",
    "cache-control": "no-cache"
  },
  "processData": false,
  "data": "{\n\t\"order_base_value\": \"67.50\",\n\t\"order_base_total\": \"67.50\",\n\t\"order_currency_code\": \"USD\",\n\t\"order_type\": \"PRODUCT\",\n\t\"total_items\": \"2\",\n\t\"items\": [{\n\t\t\"item_id\": \"test_item_WN4\",\n\t\t\"item_qty\": \"2\",\n\t\t\"item_name\": \"Widget Number 4\",\n\t\t\"item_cost_per_unit\": \"33.75\",\n\t\t\"item_value_per_unit\": \"33.75\"\n\t}]\n}"
}

$.ajax(settings).done(function (response) {
  console.log(response);
});
                            </pre>
                            <h5>Javascript - XHR</h5>
                            <pre>
var data = JSON.stringify({
  "order_base_value": "67.50",
  "order_base_total": "67.50",
  "order_currency_code": "USD",
  "order_type": "PRODUCT",
  "total_items": "2",
  "items": [
    {
      "item_id": "test_item_WN4",
      "item_qty": "2",
      "item_name": "Widget Number 4",
      "item_cost_per_unit": "33.75",
      "item_value_per_unit": "33.75"
    }
  ]
});

var xhr = new XMLHttpRequest();
xhr.withCredentials = true;

xhr.addEventListener("readystatechange", function () {
  if (this.readyState === 4) {
    console.log(this.responseText);
  }
});

xhr.open("POST", "https://api.routeit.cloud/open_api/v1/post/json/request_quote");
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
  "path": "/open_api/v1/post/json/request_quote",
  "headers": {
    "api_key": "<strong style="color: red">YOUR-KEY-HERE</strong>",
    "secret": "<strong style="color: red">YOUR-SECRET-HERE</strong>",
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

req.write(JSON.stringify({ order_base_value: '67.50',
  order_base_total: '67.50',
  order_currency_code: 'USD',
  order_type: 'PRODUCT',
  total_items: '2',
  items:
   [ { item_id: 'test_item_WN4',
       item_qty: '2',
       item_name: 'Widget Number 4',
       item_cost_per_unit: '33.75',
       item_value_per_unit: '33.75' } ] }));
req.end();
                            </pre>
                            <h5>Node JS - Request</h5>
                            <pre>
var request = require("request");

var options = { method: 'POST',
  url: 'https://api.routeit.cloud/open_api/v1/post/json/request_quote',
  headers:
   { 'cache-control': 'no-cache',
     'content-type': 'application/json',
     secret: '<strong style="color: red">YOUR-SECRET-HERE</strong>',
     api_key: '<strong style="color: red">YOUR-KEY-HERE</strong>' },
  body:
   { order_base_value: '67.50',
     order_base_total: '67.50',
     order_currency_code: 'USD',
     order_type: 'PRODUCT',
     total_items: '2',
     items:
      [ { item_id: 'test_item_WN4',
          item_qty: '2',
          item_name: 'Widget Number 4',
          item_cost_per_unit: '33.75',
          item_value_per_unit: '33.75' } ] },
  json: true };

request(options, function (error, response, body) {
  if (error) throw new Error(error);

  console.log(body);
});
                            </pre>
                            <h5>Node JS - Unirest</h5>
                            <pre>
var unirest = require("unirest");

var req = unirest("POST", "https://api.routeit.cloud/open_api/v1/post/json/request_quote");

req.headers({
  "cache-control": "no-cache",
  "content-type": "application/json",
  "secret": "<strong style="color: red">YOUR-SECRET-HERE</strong>",
  "api_key": "<strong style="color: red">YOUR-KEY-HERE</strong>"
});

req.type("json");
req.send({
  "order_base_value": "67.50",
  "order_base_total": "67.50",
  "order_currency_code": "USD",
  "order_type": "PRODUCT",
  "total_items": "2",
  "items": [
    {
      "item_id": "test_item_WN4",
      "item_qty": "2",
      "item_name": "Widget Number 4",
      "item_cost_per_unit": "33.75",
      "item_value_per_unit": "33.75"
    }
  ]
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
RequestBody body = RequestBody.create(mediaType, "{\n\t\"order_base_value\": \"67.50\",\n\t\"order_base_total\": \"67.50\",\n\t\"order_currency_code\": \"USD\",\n\t\"order_type\": \"PRODUCT\",\n\t\"total_items\": \"2\",\n\t\"items\": [{\n\t\t\"item_id\": \"test_item_WN4\",\n\t\t\"item_qty\": \"2\",\n\t\t\"item_name\": \"Widget Number 4\",\n\t\t\"item_cost_per_unit\": \"33.75\",\n\t\t\"item_value_per_unit\": \"33.75\"\n\t}]\n}");
Request request = new Request.Builder()
  .url("https://api.routeit.cloud/open_api/v1/post/json/request_quote")
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
HttpResponse&lt;String&gt; response = Unirest.post("https://api.routeit.cloud/open_api/v1/post/json/request_quote")
  .header("api_key", "<strong style="color: red">YOUR-KEY-HERE</strong>")
  .header("secret", "<strong style="color: red">YOUR-SECRET-HERE</strong>")
  .header("content-type", "application/json")
  .header("cache-control", "no-cache")
  .body("{\n\t\"order_base_value\": \"67.50\",\n\t\"order_base_total\": \"67.50\",\n\t\"order_currency_code\": \"USD\",\n\t\"order_type\": \"PRODUCT\",\n\t\"total_items\": \"2\",\n\t\"items\": [{\n\t\t\"item_id\": \"test_item_WN4\",\n\t\t\"item_qty\": \"2\",\n\t\t\"item_name\": \"Widget Number 4\",\n\t\t\"item_cost_per_unit\": \"33.75\",\n\t\t\"item_value_per_unit\": \"33.75\"\n\t}]\n}")
  .asString();
                        </pre>
                    </div>
                    <div id="csharp" class="tab-pane fade">
                        <h5>C# (ReSharp)</h5>
                        <pre>
var client = new RestClient("https://api.routeit.cloud/open_api/v1/post/json/request_quote");
var request = new RestRequest(Method.POST);
request.AddHeader("cache-control", "no-cache");
request.AddHeader("content-type", "application/json");
request.AddHeader("secret", "<strong style="color: red">YOUR-SECRET-HERE</strong>");
request.AddHeader("api_key", "<strong style="color: red">YOUR-KEY-HERE</strong>");
request.AddParameter("application/json", "{\n\t\"order_base_value\": \"67.50\",\n\t\"order_base_total\": \"67.50\",\n\t\"order_currency_code\": \"USD\",\n\t\"order_type\": \"PRODUCT\",\n\t\"total_items\": \"2\",\n\t\"items\": [{\n\t\t\"item_id\": \"test_item_WN4\",\n\t\t\"item_qty\": \"2\",\n\t\t\"item_name\": \"Widget Number 4\",\n\t\t\"item_cost_per_unit\": \"33.75\",\n\t\t\"item_value_per_unit\": \"33.75\"\n\t}]\n}", ParameterType.RequestBody);
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

	url := "https://api.routeit.cloud/open_api/v1/post/json/request_quote"

	payload := strings.NewReader("{\n\t\"order_base_value\": \"67.50\",\n\t\"order_base_total\": \"67.50\",\n\t\"order_currency_code\": \"USD\",\n\t\"order_type\": \"PRODUCT\",\n\t\"total_items\": \"2\",\n\t\"items\": [{\n\t\t\"item_id\": \"test_item_WN4\",\n\t\t\"item_qty\": \"2\",\n\t\t\"item_name\": \"Widget Number 4\",\n\t\t\"item_cost_per_unit\": \"33.75\",\n\t\t\"item_value_per_unit\": \"33.75\"\n\t}]\n}")

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

payload = "{\n\t\"order_base_value\": \"67.50\",\n\t\"order_base_total\": \"67.50\",\n\t\"order_currency_code\": \"USD\",\n\t\"order_type\": \"PRODUCT\",\n\t\"total_items\": \"2\",\n\t\"items\": [{\n\t\t\"item_id\": \"test_item_WN4\",\n\t\t\"item_qty\": \"2\",\n\t\t\"item_name\": \"Widget Number 4\",\n\t\t\"item_cost_per_unit\": \"33.75\",\n\t\t\"item_value_per_unit\": \"33.75\"\n\t}]\n}"

headers = {
    'api_key': "<strong style="color: red">YOUR-KEY-HERE</strong>",
    'secret': "<strong style="color: red">YOUR-SECRET-HERE</strong>",
    'content-type': "application/json",
    'cache-control': "no-cache"
    }

conn.request("POST", "/open_api/v1/post/json/request_quote", payload, headers)

res = conn.getresponse()
data = res.read()

print(data.decode("utf-8"))
                        </pre>
                        <h5>Python - Requests</h5>
                        <pre>
import requests

url = "https://api.routeit.cloud/open_api/v1/post/json/request_quote"

payload = "{\n\t\"order_base_value\": \"67.50\",\n\t\"order_base_total\": \"67.50\",\n\t\"order_currency_code\": \"USD\",\n\t\"order_type\": \"PRODUCT\",\n\t\"total_items\": \"2\",\n\t\"items\": [{\n\t\t\"item_id\": \"test_item_WN4\",\n\t\t\"item_qty\": \"2\",\n\t\t\"item_name\": \"Widget Number 4\",\n\t\t\"item_cost_per_unit\": \"33.75\",\n\t\t\"item_value_per_unit\": \"33.75\"\n\t}]\n}"
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

url = URI("https://api.routeit.cloud/open_api/v1/post/json/request_quote")

http = Net::HTTP.new(url.host, url.port)
http.use_ssl = true
http.verify_mode = OpenSSL::SSL::VERIFY_NONE

request = Net::HTTP::Post.new(url)
request["api_key"] = '<strong style="color: red">YOUR-KEY-HERE</strong>'
request["secret"] = '<strong style="color: red">YOUR-SECRET-HERE</strong>'
request["content-type"] = 'application/json'
request["cache-control"] = 'no-cache'
request.body = "{\n\t\"order_base_value\": \"67.50\",\n\t\"order_base_total\": \"67.50\",\n\t\"order_currency_code\": \"USD\",\n\t\"order_type\": \"PRODUCT\",\n\t\"total_items\": \"2\",\n\t\"items\": [{\n\t\t\"item_id\": \"test_item_WN4\",\n\t\t\"item_qty\": \"2\",\n\t\t\"item_name\": \"Widget Number 4\",\n\t\t\"item_cost_per_unit\": \"33.75\",\n\t\t\"item_value_per_unit\": \"33.75\"\n\t}]\n}"

response = http.request(request)
puts response.read_body
                        </pre>
                    </div>
                    <div id="php" class="tab-pane fade">
                        <h5>PHP - HttpRequest</h5>
                        <pre>
&lt;?php

$request = new HttpRequest();
$request->setUrl('https://api.routeit.cloud/open_api/v1/post/json/request_quote');
$request->setMethod(HTTP_METH_POST);

$request->setHeaders(array(
  'cache-control' => 'no-cache',
  'content-type' => 'application/json',
  'secret' => '<strong style="color: red">YOUR-SECRET-HERE</strong>',
  'api_key' => '<strong style="color: red">YOUR-KEY-HERE</strong>'
));

$request->setBody('{
	"order_base_value": "67.50",
	"order_base_total": "67.50",
	"order_currency_code": "USD",
	"order_type": "PRODUCT",
	"total_items": "2",
	"items": [{
		"item_id": "test_item_WN4",
		"item_qty": "2",
		"item_name": "Widget Number 4",
		"item_cost_per_unit": "33.75",
		"item_value_per_unit": "33.75"
	}]
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
	"order_base_value": "67.50",
	"order_base_total": "67.50",
	"order_currency_code": "USD",
	"order_type": "PRODUCT",
	"total_items": "2",
	"items": [{
		"item_id": "test_item_WN4",
		"item_qty": "2",
		"item_name": "Widget Number 4",
		"item_cost_per_unit": "33.75",
		"item_value_per_unit": "33.75"
	}]
}');

$request->setRequestUrl('https://api.routeit.cloud/open_api/v1/post/json/request_quote');
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
  CURLOPT_URL => "https://api.routeit.cloud/open_api/v1/post/json/request_quote",
  CURLOPT_RETURNTRANSFER => true,
  CURLOPT_ENCODING => "",
  CURLOPT_MAXREDIRS => 10,
  CURLOPT_TIMEOUT => 30,
  CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
  CURLOPT_CUSTOMREQUEST => "POST",
  CURLOPT_POSTFIELDS => "{\n\t\"order_base_value\": \"67.50\",\n\t\"order_base_total\": \"67.50\",\n\t\"order_currency_code\": \"USD\",\n\t\"order_type\": \"PRODUCT\",\n\t\"total_items\": \"2\",\n\t\"items\": [{\n\t\t\"item_id\": \"test_item_WN4\",\n\t\t\"item_qty\": \"2\",\n\t\t\"item_name\": \"Widget Number 4\",\n\t\t\"item_cost_per_unit\": \"33.75\",\n\t\t\"item_value_per_unit\": \"33.75\"\n\t}]\n}",
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
POST /open_api/v1/post/json/request_quote HTTP/1.1
Host: api.routeit.cloud
api_key: <strong style="color: red">YOUR-KEY-HERE</strong>
secret: <strong style="color: red">YOUR-SECRET-HERE</strong>
Content-Type: application/json
Cache-Control: no-cache

{
	"order_base_value": "67.50",
	"order_base_total": "67.50",
	"order_currency_code": "USD",
	"order_type": "PRODUCT",
	"total_items": "2",
	"items": [{
		"item_id": "test_item_WN4",
		"item_qty": "2",
		"item_name": "Widget Number 4",
		"item_cost_per_unit": "33.75",
		"item_value_per_unit": "33.75"
	}]
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
  --body-data '{\n	"order_base_value": "67.50",\n	"order_base_total": "67.50",\n	"order_currency_code": "USD",\n	"order_type": "PRODUCT",\n	"total_items": "2",\n	"items": [{\n		"item_id": "test_item_WN4",\n		"item_qty": "2",\n		"item_name": "Widget Number 4",\n		"item_cost_per_unit": "33.75",\n		"item_value_per_unit": "33.75"\n	}]\n}' \
  --output-document \
  - https://api.routeit.cloud/open_api/v1/post/json/request_quote
                        </pre>
                        <h5>Shell - Httpie</h5>
                        <pre>
echo '{
	"order_base_value": "67.50",
	"order_base_total": "67.50",
	"order_currency_code": "USD",
	"order_type": "PRODUCT",
	"total_items": "2",
	"items": [{
		"item_id": "test_item_WN4",
		"item_qty": "2",
		"item_name": "Widget Number 4",
		"item_cost_per_unit": "33.75",
		"item_value_per_unit": "33.75"
	}]
}' |  \
  http POST https://api.routeit.cloud/open_api/v1/post/json/request_quote \
  api_key:<strong style="color: red">YOUR-KEY-HERE</strong> \
  cache-control:no-cache \
  content-type:application/json \
  secret:<strong style="color: red">YOUR-SECRET-HERE</strong>
                        </pre>
                        <h5>Shell - cURL</h5>
                        <pre>
curl --request POST \
  --url https://api.routeit.cloud/open_api/v1/post/json/request_quote \
  --header 'api_key: <strong style="color: red">YOUR-KEY-HERE</strong>' \
  --header 'cache-control: no-cache' \
  --header 'content-type: application/json' \
  --header 'secret: <strong style="color: red">YOUR-SECRET-HERE</strong>' \
  --data '{\n	"order_base_value": "67.50",\n	"order_base_total": "67.50",\n	"order_currency_code": "USD",\n	"order_type": "PRODUCT",\n	"total_items": "2",\n	"items": [{\n		"item_id": "test_item_WN4",\n		"item_qty": "2",\n		"item_name": "Widget Number 4",\n		"item_cost_per_unit": "33.75",\n		"item_value_per_unit": "33.75"\n	}]\n}'
                        </pre>
                        <h5>cURL</h5>
                        <pre>
curl -X POST -H "api_key: <strong style="color: red">YOUR-KEY-HERE</strong>" -H "secret: <strong style="color: red">YOUR-SECRET-HERE</strong>" -H "Content-Type: application/json" -H "Cache-Control: no-cache" -H '{
	"order_base_value": "67.50",
	"order_base_total": "67.50",
	"order_currency_code": "USD",
	"order_type": "PRODUCT",
	"total_items": "2",
	"items": [{
		"item_id": "test_item_WN4",
		"item_qty": "2",
		"item_name": "Widget Number 4",
		"item_cost_per_unit": "33.75",
		"item_value_per_unit": "33.75"
	}]
}' "https://api.routeit.cloud/open_api/v1/post/json/request_quote"
                        </pre>
                        <h5>C (LibCurl)</h5>
                        <pre>
CURL *hnd = curl_easy_init();

curl_easy_setopt(hnd, CURLOPT_CUSTOMREQUEST, "POST");
curl_easy_setopt(hnd, CURLOPT_URL, "https://api.routeit.cloud/open_api/v1/post/json/request_quote");

struct curl_slist *headers = NULL;
headers = curl_slist_append(headers, "cache-control: no-cache");
headers = curl_slist_append(headers, "content-type: application/json");
headers = curl_slist_append(headers, "secret: <strong style="color: red">YOUR-SECRET-HERE</strong>");
headers = curl_slist_append(headers, "api_key: <strong style="color: red">YOUR-KEY-HERE</strong>");
curl_easy_setopt(hnd, CURLOPT_HTTPHEADER, headers);

curl_easy_setopt(hnd, CURLOPT_POSTFIELDS, "{\n\t\"order_base_value\": \"67.50\",\n\t\"order_base_total\": \"67.50\",\n\t\"order_currency_code\": \"USD\",\n\t\"order_type\": \"PRODUCT\",\n\t\"total_items\": \"2\",\n\t\"items\": [{\n\t\t\"item_id\": \"test_item_WN4\",\n\t\t\"item_qty\": \"2\",\n\t\t\"item_name\": \"Widget Number 4\",\n\t\t\"item_cost_per_unit\": \"33.75\",\n\t\t\"item_value_per_unit\": \"33.75\"\n\t}]\n}");

CURLcode ret = curl_easy_perform(hnd);
                        </pre>
                        <h5>Objective-C (NSURL)</h5>
                        <pre>
#import &lt;Foundation/Foundation.h&gt;

NSDictionary *headers = @{ @"api_key": @"<strong style="color: red">YOUR-KEY-HERE</strong>",
                           @"secret": @"<strong style="color: red">YOUR-SECRET-HERE</strong>",
                           @"content-type": @"application/json",
                           @"cache-control": @"no-cache" };
NSDictionary *parameters = @{ @"order_base_value": @"67.50",
                              @"order_base_total": @"67.50",
                              @"order_currency_code": @"USD",
                              @"order_type": @"PRODUCT",
                              @"total_items": @"2",
                              @"items": @[ @{ @"item_id": @"test_item_WN4", @"item_qty": @"2", @"item_name": @"Widget Number 4", @"item_cost_per_unit": @"33.75", @"item_value_per_unit": @"33.75" } ] };

NSData *postData = [NSJSONSerialization dataWithJSONObject:parameters options:0 error:nil];

NSMutableURLRequest *request = [NSMutableURLRequest requestWithURL:[NSURL URLWithString:@"https://api.routeit.cloud/open_api/v1/post/json/request_quote"]
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
  "order_base_value": "67.50",
  "order_base_total": "67.50",
  "order_currency_code": "USD",
  "order_type": "PRODUCT",
  "total_items": "2",
  "items": [
    [
      "item_id": "test_item_WN4",
      "item_qty": "2",
      "item_name": "Widget Number 4",
      "item_cost_per_unit": "33.75",
      "item_value_per_unit": "33.75"
    ]
  ]
] as [String : Any]

let postData = JSONSerialization.data(withJSONObject: parameters, options: [])

let request = NSMutableURLRequest(url: NSURL(string: "https://api.routeit.cloud/open_api/v1/post/json/request_quote")! as URL,
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

let uri = Uri.of_string "https://api.routeit.cloud/open_api/v1/post/json/request_quote" in
let headers = Header.init ()
  |> fun h -> Header.add h "api_key" "<strong style="color: red">YOUR-KEY-HERE</strong>"
  |> fun h -> Header.add h "secret" "<strong style="color: red">YOUR-SECRET-HERE</strong>"
  |> fun h -> Header.add h "content-type" "application/json"
  |> fun h -> Header.add h "cache-control" "no-cache"
in
let body = Cohttp_lwt_body.of_string "{\n\t\"order_base_value\": \"67.50\",\n\t\"order_base_total\": \"67.50\",\n\t\"order_currency_code\": \"USD\",\n\t\"order_type\": \"PRODUCT\",\n\t\"total_items\": \"2\",\n\t\"items\": [{\n\t\t\"item_id\": \"test_item_WN4\",\n\t\t\"item_qty\": \"2\",\n\t\t\"item_name\": \"Widget Number 4\",\n\t\t\"item_cost_per_unit\": \"33.75\",\n\t\t\"item_value_per_unit\": \"33.75\"\n\t}]\n}" in

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
        "route_quote_id": 1,
        "order_base_value": 67.5,
        "order_base_total": 67.5,
        "order_currency_code": "USD",
        "order_type": "PRODUCT",
        "insurance_cost_usd": 2.95,
        "suggested_insurance_price": 3.84,
        "suggested_insurance_currency": "USD",
        "exchange_rate": 1,
        "total_items": 2,
        "items": [{
            "item_sku": null,
            "item_id": "test_item_123",
            "item_qty": 2,
            "item_name": "Widget Number 4",
            "item_desc": null,
            "item_cost_per_unit": 33.75,
            "item_value_per_unit": 33.75
        }],
    "custom_a1": null,
    "custom_a2": null,
    "custom_a3": null,
    "custom_a4": null,
    "ip_address": "192.182.192.205",
    "created": 1480878291796
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
                        <td>Returns null if result is SUCCESS or an array of error strings if result is ERROR.</td>
                    </tr>
                    <tr>
                        <td>data</td>
                        <td>data envelope or null</td>
                        <td>Returns data envelope if result is SUCCESS or null if result is ERROR.</td>
                    </tr>
                    <tr>
                        <td>route_quote_id</td>
                        <td>integer</td>
                        <td>The Route Quote ID. Needed to use the Route Widget and/or call the Insure Order API.</td>
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
                        <td>order_currency_code</td>
                        <td>string</td>
                        <td>Reiterates the value sent in to confirm acceptance.</td>
                    </tr>
                    <tr>
                        <td>order_type</td>
                        <td>string</td>
                        <td>Reiterates the value sent in to confirm acceptance.</td>
                    </tr>
                    <tr>
                        <td>insurance_cost_usd</td>
                        <td>float</td>
                        <td>Total cost of insurance USD, that Route will charge the merchant to insure this order.</td>
                    </tr>
                    <tr>
                        <td>suggested_insurance_price</td>
                        <td>float</td>
                        <td>Suggest amount, in the same currency as the order, the merchant should charge the customer to insure this order. </td>
                    </tr>
                    <tr>
                        <td>suggested_insurance_currency</td>
                        <td>ISO Alpha-3(three letters) currency code as a string</td>
                        <td>Currency code of suggested_insurance_price. Should be the same as order_currency_code.</td>
                    </tr>
                    <tr>
                        <td>exchange_rate</td>
                        <td>float</td>
                        <td>Exchange rate of USD to order_currency_code at the time of quote.</td>
                    </tr>
                    <tr>
                        <td>total_items</td>
                        <td>integer</td>
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
                        <td>custom_a1</td>
                        <td>string</td>
                        <td>Reiterates the value sent in to confirm acceptance.</td>
                    </tr>
                    <tr>
                        <td>custom_a2</td>
                        <td>string</td>
                        <td>Reiterates the value sent in to confirm acceptance.</td>
                    </tr>
                    <tr>
                        <td>custom_a3</td>
                        <td>string</td>
                        <td>Reiterates the value sent in to confirm acceptance.</td>
                    </tr>
                    <tr>
                        <td>custom_a4</td>
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
                        <td>Missing ALL required parameters.</td>
                    </tr>
                    <tr>
                        <td>Parameter order_base_value must be greater than zero.</td>
                    </tr>
                    <tr>
                        <td>Parameter order_base_value must be the same as order_base_total or greater than order_base_total.</td>
                    </tr>
                    <tr>
                        <td>Parameter order_base_value must be zero or greater.</td>
                    </tr>
                    <tr>
                        <td>Missing required parameter order_currency_code.</td>
                    </tr>
                    <tr>
                        <td>Missing required parameter order_type.</td>
                    </tr>
                    <tr>
                        <td>Missing required parameter total_items.</td>
                    </tr>
                    <tr>
                        <td>One or more items missing required parameter item_id.</td>
                    </tr>
                    <tr>
                        <td>Required parameter item_id must be one or greater.</td>
                    </tr>
                    <tr>
                        <td>One or more items missing required parameter item_name.</td>
                    </tr>
                    <tr>
                        <td>Required parameter item_value_per_unit must be zero or greater.</td>
                    </tr>
                    <tr>
                        <td>Parameter item_value_per_unit must be the same as item_cost_per_unit or greater than item_cost_per_unit.</td>
                    </tr>
                    <tr>
                        <td>Required parameter item_cost_per_unit must be zero or greater.</td>
                    </tr>
                    <tr>
                        <td>The sum of item_cost_per_unit does not equal order_base_total.</td>
                    </tr>
                    <tr>
                        <td>The sum of item_value_per_unit does not equal order_base_value.</td>
                    </tr>
                    <tr>
                        <td>The sum of item_qty does not equal total_items.</td>
                    </tr>
                </table>
            </div>
        </div>
    </div>

</body>
</html>
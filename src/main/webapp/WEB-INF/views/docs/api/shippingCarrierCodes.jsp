<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Route Open API - Shipping Carrier Codes</title>
    <jsp:include page="/WEB-INF/views/docs/header-include.jsp" flush="false" />
</head>
<body>
<jsp:include page="/WEB-INF/views/docs/sideMenu.jsp" flush="true" >
    <jsp:param name="page" value="1.8" />
</jsp:include>
<div class="container-fluid myTestContainer">
            <h1>Open API - Shipping Carrier Codes</h1>
            <h3>API Endpoint:</h3>
            <div class="row">
                <div class="hidden-xs col-sm-1">&nbsp;</div>
                <div class="col-xs-12 col-sm-11">
                    <strong>URL: </strong> https://api.routeit.cloud/konnektive/v2/get/json/shipping_carrier_codes
                    <br>
                    <strong>Protocal: </strong> HTTPS
                    <br>
                    <strong>Method: </strong> GET
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
                    This request is strictly informational. No body is required for this API request.
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
  "url": "http://api.routeit.cloud/open_api/v1/get/json/shipping_carrier_codes",
  "method": "GET",
  "headers": {
    "api_key": "YOUR-KEY-HERE",
    "secret": "YOUR-SECRET-HERE",
    "content-type": "application/json",
    "cache-control": "no-cache"
  }
}

$.ajax(settings).done(function (response) {
  console.log(response);
});
                            </pre>
                            <h5>Javascript - XHR</h5>
                            <pre>
var data = null;

var xhr = new XMLHttpRequest();
xhr.withCredentials = true;

xhr.addEventListener("readystatechange", function () {
  if (this.readyState === 4) {
    console.log(this.responseText);
  }
});

xhr.open("GET", "http://api.routeit.cloud/open_api/v1/get/json/shipping_carrier_codes");
xhr.setRequestHeader("api_key", "YOUR-KEY-HERE");
xhr.setRequestHeader("secret", "YOUR-SECRET-HERE");
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
  "method": "GET",
  "hostname": "api.routeit.cloud",
  "port": null,
  "path": "/open_api/v1/get/json/shipping_carrier_codes",
  "headers": {
    "api_key": "YOUR-KEY-HERE",
    "secret": "YOUR-SECRET-HERE",
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

req.end();
                            </pre>
                            <h5>Node JS - Request</h5>
                            <pre>
var request = require("request");

var options = { method: 'GET',
  url: 'http://api.routeit.cloud/open_api/v1/get/json/shipping_carrier_codes',
  headers:
   { 'cache-control': 'no-cache',
     'content-type': 'application/json',
     secret: 'YOUR-SECRET-HERE',
     api_key: 'YOUR-KEY-HERE' } };

request(options, function (error, response, body) {
  if (error) throw new Error(error);

  console.log(body);
});
                            </pre>
                            <h5>Node JS - Unirest</h5>
                            <pre>
var unirest = require("unirest");

var req = unirest("GET", "http://api.routeit.cloud/open_api/v1/get/json/shipping_carrier_codes");

req.headers({
  "cache-control": "no-cache",
  "content-type": "application/json",
  "secret": "YOUR-SECRET-HERE",
  "api_key": "YOUR-KEY-HERE"
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

Request request = new Request.Builder()
  .url("http://api.routeit.cloud/open_api/v1/get/json/shipping_carrier_codes")
  .get()
  .addHeader("api_key", "YOUR-KEY-HERE")
  .addHeader("secret", "YOUR-SECRET-HERE")
  .addHeader("content-type", "application/json")
  .addHeader("cache-control", "no-cache")
  .build();

Response response = client.newCall(request).execute();
                            </pre>
                            <h5>Java - Unirest</h5>
                            <pre>
HttpResponse&lt;String&gt; response = Unirest.get("http://api.routeit.cloud/open_api/v1/get/json/shipping_carrier_codes")
  .header("api_key", "YOUR-KEY-HERE")
  .header("secret", "YOUR-SECRET-HERE")
  .header("content-type", "application/json")
  .header("cache-control", "no-cache")
  .asString();
                            </pre>
                        </div>
                        <div id="csharp" class="tab-pane fade">
                            <h5>C# (ReSharp)</h5>
                            <pre>
var client = new RestClient("http://api.routeit.cloud/open_api/v1/get/json/shipping_carrier_codes");
var request = new RestRequest(Method.GET);
request.AddHeader("cache-control", "no-cache");
request.AddHeader("content-type", "application/json");
request.AddHeader("secret", "YOUR-SECRET-HERE");
request.AddHeader("api_key", "YOUR-KEY-HERE");
IRestResponse response = client.Execute(request);
                            </pre>
                        </div>
                        <div id="go" class="tab-pane fade">
                            <h5>Go</h5>
                            <pre>
package main

import (
	"fmt"
	"net/http"
	"io/ioutil"
)

func main() {

	url := "http://api.routeit.cloud/open_api/v1/get/json/shipping_carrier_codes"

	req, _ := http.NewRequest("GET", url, nil)

	req.Header.Add("api_key", "YOUR-KEY-HERE")
	req.Header.Add("secret", "YOUR-SECRET-HERE")
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

conn = http.client.HTTPConnection("api.routeit.cloud")

headers = {
    'api_key': "YOUR-KEY-HERE",
    'secret': "YOUR-SECRET-HERE",
    'content-type': "application/json",
    'cache-control': "no-cache"
    }

conn.request("GET", "/open_api/v1/get/json/shipping_carrier_codes", headers=headers)

res = conn.getresponse()
data = res.read()

print(data.decode("utf-8"))
                            </pre>
                            <h5>Python - Requests</h5>
                            <pre>
import requests

url = "http://api.routeit.cloud/open_api/v1/get/json/shipping_carrier_codes"

headers = {
    'api_key': "YOUR-KEY-HERE",
    'secret': "YOUR-SECRET-HERE",
    'content-type': "application/json",
    'cache-control': "no-cache"
    }

response = requests.request("GET", url, headers=headers)

print(response.text)
                            </pre>
                        </div>
                        <div id="ruby" class="tab-pane fade">
                            <h5>Ruby (NET::Http)</h5>
                            <pre>
require 'uri'
require 'net/http'

url = URI("http://api.routeit.cloud/open_api/v1/get/json/shipping_carrier_codes")

http = Net::HTTP.new(url.host, url.port)

request = Net::HTTP::Get.new(url)
request["api_key"] = 'YOUR-KEY-HERE'
request["secret"] = 'YOUR-SECRET-HERE'
request["content-type"] = 'application/json'
request["cache-control"] = 'no-cache'

response = http.request(request)
puts response.read_body
                            </pre>
                        </div>
                        <div id="php" class="tab-pane fade">
                            <h5>PHP - HttpRequest</h5>
                            <pre>
&lt;?php

$request = new HttpRequest();
$request->setUrl('http://api.routeit.cloud/open_api/v1/get/json/shipping_carrier_codes');
$request->setMethod(HTTP_METH_GET);

$request->setHeaders(array(
  'cache-control' => 'no-cache',
  'content-type' => 'application/json',
  'secret' => 'YOUR-SECRET-HERE',
  'api_key' => 'YOUR-KEY-HERE'
));

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

$request->setRequestUrl('http://api.routeit.cloud/open_api/v1/get/json/shipping_carrier_codes');
$request->setRequestMethod('GET');
$request->setHeaders(array(
  'cache-control' => 'no-cache',
  'content-type' => 'application/json',
  'secret' => 'YOUR-SECRET-HERE',
  'api_key' => 'YOUR-KEY-HERE'
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
  CURLOPT_URL => "http://api.routeit.cloud/open_api/v1/get/json/shipping_carrier_codes",
  CURLOPT_RETURNTRANSFER => true,
  CURLOPT_ENCODING => "",
  CURLOPT_MAXREDIRS => 10,
  CURLOPT_TIMEOUT => 30,
  CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
  CURLOPT_CUSTOMREQUEST => "GET",
  CURLOPT_HTTPHEADER => array(
    "api_key: YOUR-KEY-HERE",
    "cache-control: no-cache",
    "content-type: application/json",
    "secret: YOUR-SECRET-HERE"
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
GET /open_api/v1/get/json/shipping_carrier_codes HTTP/1.1
Host: api.routeit.cloud
api_key: YOUR-KEY-HERE
secret: YOUR-SECRET-HERE
Content-Type: application/json
Cache-Control: no-cache
                            </pre>
                            <h5>Shell - wget</h5>
                            <pre>
wget --quiet \\
  --method GET \\
  --header 'api_key: YOUR-KEY-HERE' \\
  --header 'secret: YOUR-SECRET-HERE' \
  --header 'content-type: application/json' \\
  --header 'cache-control: no-cache' \\
  --output-document \\
  - http://api.routeit.cloud/open_api/v1/get/json/shipping_carrier_codes
                            </pre>
                            <h5>Shell - Httpie</h5>
                            <pre>
http GET http://api.routeit.cloud/open_api/v1/get/json/shipping_carrier_codes \\
  api_key:YOUR-KEY-HERE \\
  cache-control:no-cache \\
  content-type:application/json \\
  secret:YOUR-SECRET-HERE
                            </pre>
                            <h5>Shell - cURL</h5>
                            <pre>
curl --request GET \\
  --url http://api.routeit.cloud/open_api/v1/get/json/shipping_carrier_codes \\
  --header 'api_key: YOUR-KEY-HERE' \\
  --header 'cache-control: no-cache' \\
  --header 'content-type: application/json' \\
  --header 'secret: YOUR-SECRET-HERE'
                            </pre>
                            <h5>cURL</h5>
                            <pre>
curl -X GET -H "api_key: YOUR-KEY-HERE" -H "secret: YOUR-SECRET-HERE" -H "Content-Type: application/json" -H "Cache-Control: no-cache" -H "http://api.routeit.cloud/open_api/v1/get/json/shipping_carrier_codes"
                            </pre>
                            <h5>C (LibCurl)</h5>
                            <pre>
CURL *hnd = curl_easy_init();

curl_easy_setopt(hnd, CURLOPT_CUSTOMREQUEST, "GET");
curl_easy_setopt(hnd, CURLOPT_URL, "http://api.routeit.cloud/open_api/v1/get/json/shipping_carrier_codes");

struct curl_slist *headers = NULL;
headers = curl_slist_append(headers, "cache-control: no-cache");
headers = curl_slist_append(headers, "content-type: application/json");
headers = curl_slist_append(headers, "secret: YOUR-SECRET-HERE");
headers = curl_slist_append(headers, "api_key: YOUR-KEY-HERE");
curl_easy_setopt(hnd, CURLOPT_HTTPHEADER, headers);

CURLcode ret = curl_easy_perform(hnd);
                            </pre>
                            <h5>Objective-C (NSURL)</h5>
                            <pre>
#import &lt;Foundation/Foundation.h&gt;

NSDictionary *headers = @{ @"api_key": @"YOUR-KEY-HERE",
                           @"secret": @"YOUR-SECRET-HERE",
                           @"content-type": @"application/json",
                           @"cache-control": @"no-cache";

NSMutableURLRequest *request = [NSMutableURLRequest requestWithURL:[NSURL URLWithString:@"http://api.routeit.cloud/open_api/v1/get/json/shipping_carrier_codes"]
                                                       cachePolicy:NSURLRequestUseProtocolCachePolicy
                                                   timeoutInterval:10.0];
[request setHTTPMethod:@"GET"];
[request setAllHTTPHeaderFields:headers];

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
  "api_key": "YOUR-KEY-HERE",
  "secret": "YOUR-SECRET-HERE",
  "content-type": "application/json",
  "cache-control": "no-cache"
]

var request = NSMutableURLRequest(URL: NSURL(string: "http://api.routeit.cloud/open_api/v1/get/json/shipping_carrier_codes")!,
                                        cachePolicy: .UseProtocolCachePolicy,
                                    timeoutInterval: 10.0)
request.HTTPMethod = "GET"
request.allHTTPHeaderFields = headers

let session = NSURLSession.sharedSession()
let dataTask = session.dataTaskWithRequest(request, completionHandler: { (data, response, error) -> Void in
  if (error != nil) {
    println(error)
  } else {
    let httpResponse = response as? NSHTTPURLResponse
    println(httpResponse)
  }
})

dataTask.resume()
                            </pre>
                            <h5>OCaml (Cohttp)</h5>
                            <pre>
open Cohttp_lwt_unix
open Cohttp
open Lwt

let uri = Uri.of_string "http://api.routeit.cloud/open_api/v1/get/json/shipping_carrier_codes" in
let headers = Header.init ()
  |> fun h -> Header.add h "api_key" "YOUR-KEY-HERE"
  |> fun h -> Header.add h "secret" "YOUR-SECRET-HERE"
  |> fun h -> Header.add h "content-type" "application/json"
  |> fun h -> Header.add h "cache-control" "no-cache"
in

Client.call ~headers `GET uri
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
    "directlink": "Direct Link",
    "ajexpress": "a j express",
    "dpd-poland": "DPD Poland",
    ...
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
                            <td>Returns data envelope with valid shipping carrier codes and descriptions if result is SUCCESS or null if result is ERROR.</td>
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
                            <td>This API requires request method GET.</td>
                        </tr>
                        <tr>
                            <td>All API calls must include a valid api_key and secret in the header to authenticate.</td>
                        </tr>
                        <tr>
                            <td>Invalid api_key or secret.</td>
                        </tr>
                        <tr>
                            <td>No billing setup for these keys. Live API requests require valid billing. If this is a test, use the Test API keys.</td>
                        </tr>
                    </table>
                </div>
            </div>
</div>

</body>
</html>
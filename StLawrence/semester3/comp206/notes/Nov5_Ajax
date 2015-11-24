/*AJAX
    -    asynchronous JavaScript and XML
         ------------
         asynchronous
            ->  makes a request to a server, but does not wait for the response
            ->  allows interaction while the server is handling the code
    Will code AJAX request in pure JavaScript and then use AJAX to see what happens
  
Click handler will start the Ajax request
callback function will handle the response


Ctrl -[ matches parentheses (in NetBeans)]*/
var http_request =new XMLHttpRequest();
...
http_request.onreadystatechange=handleResponse; // identify callback
                            // handleResponse is user variable
                            // handleResponse is not being called - just being named. Therfore no ()
http_request.open("GET","data.php", true); //configure request
http_request.send(); //starts request: no parameters needed unless using post request
function handleResponse()
{
    if(http_request.readyState==4 // 4 ready states with 4 meaning the request is complete
        // anything in the 400 range, there is a problem
        && http_request.status==200)// anything in the 200s (usually 200 exact) means it succeeded
    $("#content").append(http_request.responseText); // possibly called multiple times therefore need to test data before coming in before this
}

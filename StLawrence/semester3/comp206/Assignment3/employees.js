/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var http_request=new XMLHttpRequest();
$(document).ready(
    function(){
    setupDept();
    
});

function setupDept(){
        http_request.onreadystatechange = handleResponse;
        http_request.open("GET","deptTable.php",true);
        http_request.send();
}
function handleResponse(){
    if(http_request.readyState==4&&http_request.status==200)
        $("#conent1").html(http_request.responseText);
}

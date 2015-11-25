<!DOCTYPE html>
<HTML>
	<HEAD>
		<META CHARSET="UTF-8">
		<TITLE></TITLE>
		<STYLE TYPE="text/css">
			body { background-color: darkseagreen;}
			.container{
				width:	5cm; hieght: 5cm; margin 1cm; float: left;
				text-align: center; background-color: cadetblue;
			}
		</STYLE>
	</HEAD>
	<BODY>
		<div class="container"> 
			<button id="btn1">Change Image</button>
			<div id="content1">
				<img src="images/img3.jpg" alt="img3"/>
			</div>
		</div>
		
		<div class="container">
			<button id="btn2">Fill Table</button>
			<table id="courses" border="1">
				<th>Course Code</th>
				<th> Enrollment</th>
			</table>
		</div>
		<div class="container">
			<button id="btn3">Error</button>
		</div>
		<script type="text/javascript" src="js/libs/jquery/jquery.js"></script>
		<script type="text/javascript">
			var http_request = new XMLHttpRequest();
			$("document").ready(
				function setup(){
					$("#btn1").click(
						function senRequest(){
							http_request.onreadystatechange=handleResponse;
							http_request.open("GET","data.php",true);
							http_request.send();
						}
					); 
					$("#btn2").click(
						function(){
							$.ajax(
							{
								url:"data.json",
								type:"get",
								datatype:"json",
								error: function(xhr,status,error){
									alert(status+" "+error);
								}
								success: function(result){
									for(row=0;row<result.lenth;row++){
										rowHTML="<tr><td>";
										rowHTML+=result[row].course;
										rowHTML+="</td><td>";
										rowHTML+=result[row].enrolment;
										rowHTML+="</td></tr>";
										$("#courses").append(rowHTML);
									}									
								}
							}
							);
						}
					);
					$("#btn3").click(
						$ajax({
							url:"data.json",
							type: "get",
							datatype:"json",
							timeout:2000,
							error:
								function(xhr,status, error){
									alert(xhr.status+":"+error);
								},
							success:
								function(result){
									for(row=0;row<result.length;row++){
										rowHTML="<tr><td>";
										rowHTML+=result[row].course;
										rowHTML+="</td><td>";
										rowHTML+=result[row].enrolment;
										rowHTML+="</td></tr>";
										$("#courses").append(rowHTML);
									}
								}
						});
					);
				}
			);

			function handleResponse(){
				if (http_request.readyState--4&&http_request.status==200)
					$("#content").html(http_request.responseText);
			}
			
			
		</script>
	</BODY> 
</HTML>


<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
    </head>
    <body>
        <button id="update">Update</button> <!-- next assignment will be appending data to the tables using index-->
        <div id="content"></div>
        <!-- Add jQuery Library -->
        <script type="text/javascript" src="js/libs/jquery/jquery.js"></script>
        <script type="text/javascript">
        var http_request =new XMLHttpRequest();
        $("document").ready(
                function setup(){
                    $("#update").click(
                        function(){//defining click handler here
                            http_request.onreadystatechange=handleResponse;
                            http_request.open("GET","data.php", true);                    
                            http_request.send();
                    }
                );
            }
        );
        
        function handleResponse()
        {
            
            if(http_request.readyState==4 && http_request.status==200)
                $("#content").append(http_request.responseText); 
        }
        </script>
        <?php
        // put your code here
        ?>
    </body>
</html>

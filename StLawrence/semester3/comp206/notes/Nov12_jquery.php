for final assingment:
    jQuery
        -   supports ajax
    ajax
    sessions

/***************************************************
*************** SESSIONS ***************************
***************************************************/
              Standard Breakdown:
    browser                         server
       1.request php                2.create php
       3.renders php
       4.form submits request       5. handles request and process continues
       
                                        php 
                                        -   variables
                                            -   belong to php file
                                            -   can only use it's own variables
                                    might need to use a variable between php
                                        -   create session list
                                        
                                        session list    
                                        -   last for the duration of the browser session
                                        -   ctr-F5 starts new session
                                        -   can have parrallel sessins
                                        -   just a way of perserving values across pages
start by building simple example of session variable
everytime we load page it will display the number of times it was loaded
*/
/* Session code: */
<?php 
    session_start(); //use this on every page that uses session to either create or open the session
    $counter=$_SESSION["counter"]; 
        //create a variable called counter and get a value from the session variable counter
        // above is 
    
    //verify that there is data in the counter variable
    if (isset($counter)){
        $counter ++;
    }
    else{
        $counter = 1;
    }
    // php requires open and closing brackets {} every time with an if statement 
    echo "<h1>Counter = $counter </h1";
    $_SESSION["counter"]=$counter;
?>
        
    

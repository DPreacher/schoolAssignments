<?php
    $session_start();
    $deptId =$_SESSION["deptID"];
    $index=$_SESSION["emplIndex"];
    $move=$_POST["move"];
    
    if($move==0)
        $index=0;
    else{
        $index=$index+$move;
        if($index>=$_SESSION["emplMax"]-10)
            $index=$_SESSION["emplMax"];
        elseif ($index<0)
            $index=0;
    }
    
    $connection=  mysqli_connect("localhost", "root", "mysql", "employees");
    
    $sql = "SELECT first_name, last_name FROM dept_emp JOIN employees USING(emp_no) JOIN departments USING(dept_no) WHERE dept_no = '$deptId' LIMIT 10;";
    $result =mysqli_query($connection,$sql);                                
    
    $rows=Array();
    while($row=  mysqli_fetch_assoc($result)){
            $rows[]=$row;
    }
    mysqli_close($connection);
    $_SESSION["emplIndex="]=$index;
    echo json_encode($rows);

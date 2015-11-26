<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$connection=  mysqli_connect("localhost", "root", "mysql", "employees");
$departments =mysqli_query($connection,"SELECT * FROM departments;");
echo "<table id='deptTable'>";
echo "<tr><th>Departments</th></tr>";
while($row=  mysqli_fetch_assoc($departments)){
    
    $id=$row["dept_no"];
    $dept=$row["dept_name"];
    echo "<tr class='deptRow'><td id='$id'>$dept</td></tr>";

}
echo"</table>";

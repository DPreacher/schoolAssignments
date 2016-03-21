<?php
echo "
	<!doctype html>
	<html lang = \"en\">
	   <head>
		 <meta charset = \"UTF-8\">
		 <title>Testing DB Functions</title>
	   </head>
	   <body>";

	require_once("Asst2DatabaseFunctions.php");
	error_reporting(0);
	function test(){
		$conn = mysqli_connect("localhost","root","mysql","test");
		$tableName="testing";
		$fieldnames=array(
		0=>"Item", 
		1=>"City",
		2=>"DateReceived",
		3=>"Quantity",
		4=>"Price"
		);
		$dataTypes=array(
		0=>"varchar", 
		1=>"varchar",
		2=>"date",
		3=>"int",
		4=>"decimal"
		);
		$sizes=array(
		0=>25, 
		1=>10,
		2=>0,
		3=>0,
		4=>4
		);
		$decimal=array(
		0=>0, 
		1=>0,
		2=>0,
		3=>0,
		4=>2
		);
		if(CreateTable($tableName,$fieldnames,$dataTypes,$sizes,$decimal))
			echo "Table Created Successfully";
		else
			echo "Error Creating Table";
		$value=array(
		0=>"tea", 
		1=>"Toronto",
		2=>"2015-05-31",
		3=>15,
		4=>6.45
		);
		$dataTypes=array(
		0=>"varchar", 
		1=>"varchar",
		2=>"date",
		3=>"int",
		4=>"decimal"
		);
		if(InsertIntoTable($tableName,$value,$dataTypes)){
			echo "Data Inserted Successfully";
		}
		else{
			echo "Error Inserting Data";
		}
		$value=array(
		0=>"Milk", 
		1=>"Kingston",
		2=>"2014-06-17",
		3=>100,
		4=>0.99
		);
		$dataTypes=array(
		0=>"varchar", 
		1=>"varchar",
		2=>"date",
		3=>"int",
		4=>"decimal"
		);
		if(InsertIntoTable($tableName,$value,$dataTypes)){
			echo "Data Inserted Successfully";
		}
		else{
			echo "Error Inserting Data";
		}
		$result=RunSelect($tableName,"Item");
		while($row=mysqli_fetch_assoc($result)){
			print_r($row);
		}
	}
		
    // call BuildAndIssueSelectStatement sorted by Item
    // code a loop to output all values. It doesn't have to be pretty
     
  test();
    // Close the connection
	mysqli_close($conn);
    echo "</body>
          </html>";
?>

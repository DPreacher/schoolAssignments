<?php
/*Isometric web app */
// can use Here doc to get around \char to add special character 
// cannot use semicolon because it ends the statement
function WriteHeaders($Heading="Welcome",$Title="MySite"){
	echo "<!doctype html> 
<html lang = \"en\">
<head>
    <meta charset = \"UTF-8\">
    <title>$Title</title>
</head>
<body>
	<h1>$Heading</h1>";
}
function WriteFooters(){
	echo "</body> </html>";
}

function DataEntryForm(){
    //write headers here
    // whatever the user types in the animal txtbox will get stored in $_POST["$_POST["f_AnimalName"]"]
	
    //create a variable the user can't seeIt's name is f_nextform, its value is DipslayDataForm
    echo" <form action =\"ZooAdvance.php?\" method=post>
			<p>Name <Input type = text name = \"f_AnimalName\" Size = 20 value = \"Claws\" ></p>
			<p>Type of Animal <Input type = text name = \"f_AnimalType\" Size = 10 value = \"Tiger\" ></p>
			<p>Pounds of Food Per Day <Input type = number name = \"f_Pounds\" value = 25></p>
			<p>Notes <TEXTAREA name = \"f_Notes\" rows = 5 columns = 40></TEXTAREA></p>
			<button type=Submit>Submit</button>";
			//This creates a hidden form variable called f_nextform that
			//has the value DisplayDataForm. This variable gets carried
			//along to the new form in the same way as post variables
			
			echo"<input type=hidden name=\"f_nextform\" value=\"DisplayDataForm\"><br/><br/>\n
		</form>"; // above input is case sensitive
}
function DisplayDataForm(){
	echo "<form action=? method=post>\n";
	echo"<h1>It's A Zoo Around Here</h1>
			<h2>Animal Name:</h2>
				<p>".$_POST["f_AnimalName"]." </p>
			<h2> Type: </h2>
				<p>".$_POST["f_AnimalType"]."</p>
			<h2>Weight: </h2>
				<p>".$_POST["f_Pounds"]."lbs</p>
			<h2>Notes: </h2>
				<p>".$_POST["f_Notes"]."</p>";
	echo "<button type=submit>Done</button>";
	echo "<input type=hidden name=\"f_nextform\" value=\"GoodbyeForm\">";
	echo "</form>";
}
function GoodbyeForm(){
	echo "<h2>Good Bye</h2>";
}
WriteHeaders("It's A Zoo  Around Here","Zoo Land");
// main
//are we on the first page?


if(!isset($_POST["f_nextform"]))
	DataEntryForm();
else
	if(!strcmp($_POST["f_nextform"],"DisplayDataForm"))
		DisplayDataForm();
	else
		GoodbyeForm();

WriteFooters();

?>

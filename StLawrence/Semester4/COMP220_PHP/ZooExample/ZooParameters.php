<?php
/*Isometric web app */
// can use Here doc to get around \char to add special character 
// cannot use semicolon because it ends the statement
function WriteHeaders($Heading="Welcome",$Title="MySite"){
	echo "<!doctype html> 
<html lang = \"en\">
<head>
    <meta charset = \"UTF-8\">
    <link rel=\"stylesheet\" type=\"text/css\" href=\"ZooParameters.css\">
    <title>$Title</title>
</head>
<body>
	<h1>$Heading</h1>";
}
function WriteFooters(){
    DisplayContactInfo();
	echo "</body> </html>";
}

function DataEntryForm(){
	echo "<form action =\"ZooAdvance.php?\" method=post><p class=\"\">";
	echo " <p class=\"DataPair\">";
    DisplayLabel("Name");
	DisplayTextbox("f_AnimalName",20,"Claws");
    
    echo " </p><p class=\"DataPair\">";
    DisplayLabel("Animal Type");
    DisplayTextbox("f_AnimalType",20,"Tiger");
	
    echo " </p><p class=\"DataPair\">";
    DisplayLabel("Pounds");
	DisplayTextbox("f_Pounds",20,25);
    echo "</p>";
    
	echo "Pounds of Food Per Day <Input type = number name = \"f_Pounds\" value = 25></p><p>";
	DisplayLabel("Notes");
	echo "<TEXTAREA name = \"f_Notes\" rows = 5 columns = 40></TEXTAREA></p>
			<button type=Submit>Submit</button>";
			//This creates a hidden form variable called f_nextform that
			//has the value DisplayDataForm. This variable gets carried
			//along to the new form in the same way as post variables
        DisplayButton("submit","save","imgs/save.jpeg",$btnAlt="Save");
        //DisplayButton("submit","save");
			echo"<input type=hidden name=\"f_nextform\" value=\"DisplayDataForm\"><br/><br/>\n
		</form>";
}
function DisplayDataForm(){
	echo "<form action=? method=post>\n";
	echo"<h1>It's A Zoo Around Here</h1>
			<p class=\"DataPair\">
            <h2>Animal Name:</h2>
				".$_POST["f_AnimalName"]." </p>
			<p class=\"DataPair\">
            <h2> Type: </h2>
				".$_POST["f_AnimalType"]."</p>
			<p class=\"DataPair\">
            <h2>Weight: </h2>
				<p>".$_POST["f_Pounds"]."lbs</p>
			<p class=\"DataPair\">
            <h2>Notes: </h2>
				".$_POST["f_Notes"]."</p>";
	echo "<button type=submit>Done</button>";
	echo "<input type=hidden name=\"f_nextform\" value=\"GoodbyeForm\">";
	echo "</form>";
}
function GoodbyeForm(){
	echo "<h2>Good Bye</h2>";
}

function DisplayButton($btnName, $btnText, $btnFileName="na", $btnAlt="na") 
{
    echo "<button name=\"$btnName\" >";
    if($btnFileName !== "na")
    {
        echo "<img src=\"$btnFileName\" value=\"$btnText\" ";
        if($btnAlt!="na"){
            echo "alt=\"$btnAlt\" ";
        }
        echo "/> <br>";
    }
    else{
        echo "$btnText";
    }
    echo "</button>";
} 
function DisplayImage($fileName, $alt, $height=0, $width=0){
    echo "<img src=\"$filename\" alt=\"$alt\"";
    if($width>0){
        echo "width=\"$width\"";
    }
    if($height>0){
        echo "height=\"$height\"";
    }
    echo " />";
}
function DisplayContactInfo(){
    echo "<footer>Questions? Comments? <a href=\"mailto:dingram20@student.sl.on.ca\"> dingram20@student.sl.on.ca </a> </footer> ";
}
function DisplayLabel($Text){
	echo "<label >$Text</label>";
}
function DisplayTextbox($txtName,$txtSize,$txtValue){
	echo "<Input type = text name = $txtName Size = $txtSize value = $txtValue >";
}
WriteHeaders("It's A Zoo  Around Here","Zoo Land");
// main
//are we on the first page?
// isset is a function that returns true if the parameter
// $_POST["f_nextfom"] has a value  the first thime throguht isset returns false, the If is true and Dataentry from is run

//the second time $_POST has a value 
//ie it is set isset returns true the if returns false

if(!isset($_POST["f_nextform"]))
	DataEntryForm();
else
    //strcmp takes 2 parameters and is case sensitive
    // it returns 0 when the strings are an exact match.
	if(!strcmp($_POST["f_nextform"],"DisplayDataForm"))
		DisplayDataForm();
	else
		GoodbyeForm();
WriteFooters();

?>

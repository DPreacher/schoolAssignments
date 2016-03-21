<?php
	function RunSelect($tablename, $wherefield="", $wherevalue, $orderfield="", $sort="ASC") {
		$sql = "SELECT * FROM $tablename";
		
		if ($wherefield != "" && isset($wherevalue) && $wherevalue != "") {
			if (gettype($wherevalue) == "string" || gettype($wherevalue) == "date")
				$wherevalue = "'" . $wherevalue . "'";
			$sql = $sql . " WHERE $wherefield = $wherevalue ";
		}
		
		if ($orderfield != "")
			$sql = $sql . " ORDER BY $orderfield $sort";
		
		$result = $mysqli_query($conn, $sql);
		return $result;
	}

	//datatypes can be integer, decimal, string, date
	function CreateTable($tablename, $fieldnames, $datatypes, $sizes, $decimal) {
		mysqli_query($conn, "DROP TABLE IF EXISTS $tablename");
		
		$sql = "CREATE TABLE $tablename (";
		
		for ($i = 0; $i < count($fieldnames); $i++) {
			$sql = $sql . $fieldname[$i] . " " . $datatypes[$i];
			if ($datatypes[$i] == "varchar")
				$sql = $sql . "VARCHAR(" . $sizes[$i] . ")";
			else if ($datatypes[$i] == "decimal")
				$sql = $sql . "DECIMAL(" . $sizes[$i] . "," . $decimal[$i] . ")";
			
			if ($i < count($fieldnames) - 1)
				$sql = $sql . ",";
		}
		
		$sql = $sql . ")";
		
		$result = mysqli_query($conn, $sql);
		
		return $result != false;
	}
	function InsertIntoTable($tablename,$values,$datatypes){
		$sqlRecord = "INSERT INTO $tablename VALUES (";
		for($ctr=1;$ctr<count($values);$ctr++){
			if ($datatypes[$ctr]=="varchar"||$datatypes[$ctr]=="date"){
				$sqlRecord = $sqlRecord . "'" . $datatypes[i] . "'";
			}
			else{
				if($datatypes[$ctr]=="int"||$datatypes[$ctr]=="decimal"){
					if($values[$ctr]==null){
						$sqlRecord = 0;
					}
					else{
						$sqlRecord = $sqlRecord . $datatypes[$ctr];
					}
				}
			}
			if($ctr < count($values)-1)
				$sqlRecord=$sqlRecord.", ";
		}
		$sqlRecord=$sqlRecord+")";
		$result = mysqli_query($conn,$sqlRecord);
		return $result !=false;
	}
?>

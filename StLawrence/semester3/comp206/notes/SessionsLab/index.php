<html>
  <head>
    <title>Session Demo</title>
  </head>
  <body>
    <?php
      session_start();
      if(isset($_SESSION["counter1"])){
        $counter=$_SESSION["couter1"];
        $counter++;
      }
      else{
        $counter=1;
      }
      $_SESSION["counter1"]=$counter;
      
      if(isset($_SESSION["counter2"])){
        $_SESSION["counter2"]++;
      }
      else{
        $_SESSION["counter2"]=1;
      }
      echo "<h1>Counter1=",$_SESSION["counter1"],"</h1>";
      echo "<h1>Counter2=",$_SESSION["counter2"],"</h1>";
    ?>
    <form action="reset.php">
      <label for="reset1">Counter1</label>
      <input type="checkbox" id="reset1" name="reset1" /> </br>
      <label for="reset2">Counter2</label>
      <input type="checkbox" id="reset2" name="reset2"/> </br>
      <input type="submit" value="Reset Counter"/>
    </form>
    <form action="destroy.php">
      <input type="submit" value="Close Session"/>
    </form>
  </body>
</html>

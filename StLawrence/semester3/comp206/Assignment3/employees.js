/* id = this.id;
        $.ajax({
            url: "populateEmpl.php", 
            data: {"deptId": id,"index":index},
            type: "post",
            datatype:"json",
            error:  function(xhr,status,error){
                    alert(status+" "+error);
            },
            success: function (result){
                populateTable(result);
           }
		   
		   
function getIndex(){
    
    $.ajax({
       url: "getSession.php",
       data: {"option":option},
       datatype: "json",
       type: "post",
       error:  function(xhr,status,error){
                alert(status+" "+error);
        },
        success: function (result){
            
            //var obj = JSON.parse(result);
            var obj = JSON.parse(result);
            maxIndex= obj[0].emplIndex/10;
            index=0;
            ajaxCall(0);
        }
    });
}*/
var id="", index=0, maxIndex=0;
$("document").ready(function(){
    
    $(".deptRow td").click(function(){
        id=this.id;
        index=0;
        getIndex();
        // ajaxCall();
   
    });
    $(".prev").click(function(){
            alert(works);
            callAjax(-10);
       
    });
    $(".next").click(function(){
            alert(works);
            callAjax(10);
       
    });
});
function getIndex(){
    
    $.ajax({
       url: "getSession.php",
       data: {"deptId":id},
       datatype: "json",
       type: "post",
       error:  function(xhr,status,error){
                alert(status+" "+error);
        },
        success: function (result){
            
            //var obj = JSON.parse(result);
            var obj = JSON.parse(result);
            maxIndex= obj[0].emplIndex/10;
            index=0;
            ajaxCall(0);
        }
    });
}
function ajaxCall(move){
    $.ajax({
        url: "populateEmpl.php", 
        data: {"move":move},
        type: "post",
        datatype:"json",
        error:  function(xhr,status,error){
                alert(status+" "+error);
        },
        success: function (result){
            populateTable(result,move);
        }
    });
}

function populateTable(result, move){
        dept = document.getElementById(id);
        var empTbl=document.getElementById("empl");
        empTbl.innerHTML="<table id='emplTable'></table>";
        rowHTML="<tr id='emplData'></tr><tr><th colspan='2'>"
        rowHTML+=dept.innerHTML;
        rowHTML+="</th>";
         $("#emplTable").append(rowHTML);
         
        rowHTML="<tr><td><button type='button' id='prev'disabled>Previous</button></td>";
        rowHTML+="<td><button type='button' id='next'>Next</button></td>";
        $("#emplTable").append(rowHTML);
        
        var obj = JSON.parse(result);
        for(row=0;row<result.length;row++){

            rowHTML="<tr><td class='emplRow'>";
            rowHTML+=obj[row].first_name;
            rowHTML+="</td><td class='emplRow'>";
            rowHTML+=obj[row].last_name;
            rowHTML+="</td></tr>";
            $("#emplTable").append(rowHTML);
        }
		index+=move;
        if(move==0||index<=0){
            index==0;
            document.getElementById("prev").disabled=true;
        }
        else if(index>=MaxIndex-10){
            document.getElementById("next").disabled=true;
        }
        else{
            document.getElementById("prev").disabled=false;
            document.getElementById("next").disabled=false;
        }
}

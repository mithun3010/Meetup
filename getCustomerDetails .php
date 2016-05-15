<?php

if(isset($_GET['CustomerID']))
{

$con = mysql_connect('cs1.ucc.ie','nt2','aipeihuu');
if(!$con)
{
die('Could not connect: ' . mysql_error());
}
mysql_select_db('msccs2015_nt2',$con);
$customerID = $_GET['CustomerID'];

$result = mysql_query("SELECT * FROM user_data WHERE id = '$customerID' ") or die('Errant query: ' );

while($row = mysql_fetch_assoc($result))
{
$output[]=$row;
}
print(json_encode($output));

mysql_close($con);
}
else
{
$output = "not found!";
print(json_encode($output));
}

?>
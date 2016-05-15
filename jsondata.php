<?php

$con = mysql_connect('cs1.ucc.ie','nt2','aipeihuu');
mysql_select_db('msccs2015_nt2', $con);

$query_str = ("SELECT * FROM USER_DATA");
$result = mysql_query($query_str) or die(mysql_error());
if(!$con)
{
die("Couldnt connect to db: ". mysql_error());
}


while($row = mysql_fetch_assoc($result))
{
$output[] = $row;
}
print(json_encode($output));
mysql_close($con);

?>
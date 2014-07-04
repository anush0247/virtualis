<?php 
  
 $url = "txt/media.csv";
 
 $file = fopen($url,"r");
 $data =  fread($file, filesize($url));
 echo $data;
 fclose($file);

?>

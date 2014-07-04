<?php
	$data = trim($_POST['data']);
	//print_r($_POST);
	if(strlen($data) == 0 ) echo "Input empty";
	else {
		$file = fopen("./txt/media.csv","w");
		if(fwrite($file,$data)){
			echo "File Created";
		}
		else echo "Unable to create csv file";
		fclose($file);
	}
?>

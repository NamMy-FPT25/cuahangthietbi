<?php
    include "connect.php";
    $tenkhachhang = $_POST['tenkhachhang'];
    $sodienthoai = $_POST['sodienthoai'];
    $email = $_POST['email'];
    if (strlen($tenkhachhang) > 0 && strlen($email) > 0 && strlen($sodienthoai) > 0) {
    	$query = "INSERT INTO donhang(ID,tenkhachhang,sodienthoai,email) VALUES (null,'$tenkhachhang','$sodienthoai','$email')";
    	if (mysqli_query($conn,$query)) {
    		$iddonhang = $conn->insert_id;
    		echo $iddonhang;
    	}else{
    		echo "That bai";
    	}
    }else{
    	echo "Ban hay ktra lai cac du lieu";
    }
?>
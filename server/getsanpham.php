<?php
    include "connect.php";
    $page = $_GET['page'];
    $IDsp = $_POST['IDsanpham'];
    $space = 5;
    $limit =($page - 1) * $space;
    $mangsanpham = array();
    $query = "SELECT * FROM sanpham WHERE IDsanpham = $IDsp LIMIT $limit,$space";
    $data = mysqli_query($conn,$query);
    while ($row = mysqli_fetch_assoc($data)) {
    	array_push($mangsanpham, new Sanpham(
    		$row['ID'],
    	    $row['tensanpham'],
    	    $row['giasanpham'],
    	    $row['hinhanhsanpham'],
    	    $row['motasanpham'],
    	    $row['IDsanpham']));
    }
    echo json_encode($mangsanpham);
    class Sanpham{
    	function __construct($ID,$tensp,$giasp,$hinhanhsp,$motasp,$IDsanpham){
    		$this->ID = $ID;
    		$this->tensp = $tensp;
    		$this->giasp = $giasp;
    		$this->hinhanhsp = $hinhanhsp;
    		$this->motasp = $motasp;
    		$this->IDsanpham = $IDsanpham;
    	}
    }
?>
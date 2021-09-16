<?php
    include "connect.php";
    $mangspmoinhat = array();
    $query = "SELECT * FROM sanpham ORDER BY ID DESC LIMIT 4";

    $data = mysqli_query($conn,$query);
    while ($row = mysqli_fetch_assoc($data)) {
    	array_push($mangspmoinhat,new Sanphammoinhat(
    	     $row['ID'],
    	     $row['tensanpham'],
    	     $row['giasanpham'],
    	     $row['hinhanhsanpham'],
    	     $row['motasanpham'],
    	     $row['IDsanpham']
    	));
    }
    echo json_encode($mangspmoinhat);

    class Sanphammoinhat{
    	function __construct($ID,$tensp,$giasp,$hinhanhsp,$motasp,$IDsanpham){
    		$this->ID=$ID;
      		$this->tensp=$tensp;
    		$this->giasp=$giasp;
    		$this->hinhanhsp=$hinhanhsp;
    		$this->motasp=$motasp;
    		$this->IDsanpham=$IDsanpham;
    	}
    }
?>
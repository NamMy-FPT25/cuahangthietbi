<?php
    include "connect.php";
    $query = "SELECT * FROM loaisanpham";
    $data = mysqli_query($conn, $query);
    $mangloaisp = array();
    while($row = mysqli_fetch_assoc($data)){
        array_push($mangloaisp, new Loaisp(
            $row['ID'],
            $row['tenloaisanpham'],
            $row['hinhanhloaisanpham']));
    }
    echo json_encode($mangloaisp);
    class Loaisp{
        function __construct ($ID, $tenloaisp, $hinhanhloaisp){
            $this->ID = $ID;
            $this->tenloaisp = $tenloaisp;
            $this->hinhanhloaisp = $hinhanhloaisp;
        }
    }
?>
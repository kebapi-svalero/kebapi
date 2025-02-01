<?php

$url = 'http://localhost:8080';

$response = file_get_contents($url);

if ($response === FALSE) {
    echo 'Error al conectar con la API.';
} else {

    $data = json_decode($response, true);

    echo '<pre>';
    print_r($data);
    echo '</pre>';
}
?>

<?php
// Configuración de conexión a la base de datos
$host = 'localhost';
$dbname = 'kebapi'; // Nombre de la base de datos
$username = 'root'; // Usuario de la base de datos (por defecto en XAMPP es 'root')
$password = '';

try {
    $pdo = new PDO("mysql:host=$host;dbname=$dbname;charset=utf8", $username, $password);
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    // Crear la tabla si no existe
    $createTableSQL = "
    CREATE TABLE IF NOT EXISTS customers (
        id INT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(255) NOT NULL,
        email VARCHAR(255) NOT NULL UNIQUE,
        phone VARCHAR(15) NOT NULL,
        password VARCHAR(255) NOT NULL
    )";
// Crear la tabla items si no existe
    $createItemsTableSQL = "
    CREATE TABLE IF NOT EXISTS items (
        id INT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(255) NOT NULL,
        description TEXT NOT NULL,
        price DECIMAL(10, 2) NOT NULL,
        vegetarian TINYINT(1) NOT NULL DEFAULT 0
    )";
    $pdo->exec($createItemsTableSQL);
    $pdo->exec($createTableSQL);


} catch (PDOException $e) {
    die("Error al conectar a la base de datos: " . $e->getMessage());
}
?>
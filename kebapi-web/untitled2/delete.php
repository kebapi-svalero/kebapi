<?php
include 'db.php';

if (isset($_GET['type']) && isset($_GET['id'])) {
    $type = $_GET['type'];
    $id = intval($_GET['id']);

    if ($type === 'customer') {
        $stmt = $pdo->prepare("DELETE FROM customers WHERE id = ?");
        $stmt->execute([$id]);
        header("Location: customers.php");
    } elseif ($type === 'item') {
        $stmt = $pdo->prepare("DELETE FROM items WHERE id = ?");
        $stmt->execute([$id]);
        header("Location: items.php");
    } else {
        echo "Invalid type.";
    }
} else {
    echo "Invalid request.";
}
?>

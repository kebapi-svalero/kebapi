<?php include 'db.php'; ?>

<?php
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $name = $_POST['name'];
    $description = $_POST['description'];
    $price = $_POST['price'];
    $vegetarian = isset($_POST['vegetarian']) ? 1 : 0;

    $stmt = $pdo->prepare("INSERT INTO items (name, description, price, vegetarian) VALUES (?, ?, ?, ?)");
    $stmt->execute([$name, $description, $price, $vegetarian]);

    header("Location: items.php");
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add item</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center">Add new item</h1>
    <form method="POST">
        <div class="mb-3">
            <label for="name" class="form-label">Name</label>
            <input type="text" class="form-control" id="name" name="name" required>
        </div>
        <div class="mb-3">
            <label for="description" class="form-label">Description</label>
            <textarea class="form-control" id="description" name="description" rows="3" required></textarea>
        </div>
        <div class="mb-3">
            <label for="price" class="form-label">Price</label>
            <input type="number" step="0.01" class="form-control" id="price" name="price" required>
        </div>
        <div class="form-check mb-3">
            <input class="form-check-input" type="checkbox" id="vegetarian" name="vegetarian">
            <label class="form-check-label" for="vegetarian">Vegetarian</label>
        </div>
        <button type="submit" class="btn btn-success">Add Item</button>
        <a href="items.php" class="btn btn-secondary">Back</a>
    </form>
</div>
</body>
</html>

<?php include 'db.php'; ?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Items List</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center">Items List</h1>
    <a href="additem.php" class="">Add New Item</a>
    <table class="table table-striped table-hover">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Price</th>
            <th>Vegetarian</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <?php
        $stmt = $pdo->query("SELECT * FROM items");
        while ($row = $stmt->fetch(PDO::FETCH_ASSOC)) {
            echo "<tr>";
            echo "<td>{$row['id']}</td>";
            echo "<td>{$row['name']}</td>";
            echo "<td>{$row['description']}</td>";
            echo "<td>\${$row['price']}</td>";
            echo "<td>" . ($row['vegetarian'] ? 'Yes' : 'No') . "</td>";
            echo "<td>
                            <a href='delete.php?type=item&id={$row['id']}' class='btn btn-danger btn-sm'>Delete</a>
                          </td>";
            echo "</tr>";
        }
        ?>
        </tbody>
    </table>
</div>
</body>
</html>

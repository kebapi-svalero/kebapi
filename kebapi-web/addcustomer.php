<?php include 'db.php'; ?>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Add New Customer</title>
  <link rel="stylesheet" href="styles.css">
</head>
<body>
<div class="container mt-5">
  <h1 class="text-center">Add New Customer</h1>
  <form action="addcustomer.php" method="POST">
    <div class="form-group">
      <label for="name">Name:</label>
      <input type="text" name="name" id="name" required class="form-control">
    </div>
    <div class="form-group">
      <label for="email">Email:</label>
      <input type="email" name="email" id="email" required class="form-control">
    </div>
    <div class="form-group">
      <label for="phone">Phone:</label>
      <input type="text" name="phone" id="phone" required class="form-control">
    </div>
    <div class="form-group">
      <label for="password">Password:</label>
      <input type="password" name="password" id="password" required class="form-control">
    </div>
    <button type="submit" class="btn btn-primary">Add Customer</button>
  </form>

    <?php
    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        // Recoger datos del formulario
        $name = $_POST['name'];
        $email = $_POST['email'];
        $phone = $_POST['phone'];
        $password = $_POST['password'];

        // Preparar la consulta SQL
        $stmt = $pdo->prepare("INSERT INTO customers (name, email, phone, password) VALUES (:name, :email, :phone, :password)");

        // Ejecutar la consulta
        if ($stmt->execute(['name' => $name, 'email' => $email, 'phone' => $phone, 'password' => $password])) {
            echo "<div class='alert alert-success'>Customer added successfully!</div>";
        } else {
            echo "<div class='alert alert-danger'>Error adding customer.</div>";
        }
    }
    ?>
</div>
</body>
</html>
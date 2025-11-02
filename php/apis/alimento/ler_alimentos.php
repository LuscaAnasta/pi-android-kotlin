<?php
$host = 'www.thyagoquintas.com.br:3306';
$db = 'engenharia_64';
$user = 'engenharia_64';
$pass = 'tamanduamirim';
$charset = 'utf8mb4';
$dsn  =  "mysql:host=$host;dbname=$db;charset=$charset";
$options = [
    PDO::ATTR_ERRMODE            => PDO::ERRMODE_EXCEPTION,
    PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC,
    PDO::ATTR_EMULATE_PREPARES   => false,
];

try {
    $pdo = new PDO($dsn, $user, $pass, $options);
    $sql = "SELECT * FROM tb_alimentos";
    $stmt = $pdo->query($sql);

    $alimentos = $stmt->fetchAll();
    header('Content-Type: application/json');
    echo json_encode($alimentos);
} catch (\PDOException $e) {
    echo "Erro de conexÃ£o: " . $e->getMessage();
    exit;
}
?>

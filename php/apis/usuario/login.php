<?php
$host = 'www.thyagoquintas.com.br:3306';
$db = 'engenharia_64';
$user = 'engenharia_64';
$pass = 'tamanduamirim';
$charset = 'utf8mb4';
$dsn  =  "mysql:host=$host;dbname=$db;charset=$charset";
$options = [
PDO::ATTR_ERRMODE=> PDO::ERRMODE_EXCEPTION, 
PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC, 
PDO::ATTR_EMULATE_PREPARES => false,
];
try {
$pdo = new PDO($dsn, $user, $pass, $options);
$usuario = $_GET['usuario'] ?? '';
$senha = $_GET['senha'] ?? '';

// Query para verificar as credenciais
$sql = "SELECT id,
nome, 
nomeCompleto,
email, 
cpf
FROM tb_usuarios
WHERE email = :usuario 
AND senha = :senha";


$stmt = $pdo->prepare($sql);
$stmt->execute(['usuario' => $usuario, 'senha' => $senha]);
$usuarios = $stmt->fetchAll();

header('Content-Type: application/json'); echo json_encode($usuarios);

} catch (\PDOException $e) {
echo "Erro de conexão: " . $e->getMessage(); exit;
}
?>


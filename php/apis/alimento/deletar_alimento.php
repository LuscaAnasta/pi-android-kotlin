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

    if (isset($_POST['id'])) {
        $alimentoId = $_POST['id'];

        // Deletar o produto
        $sql = "DELETE FROM tb_alimentos WHERE id = :alimentoId";
        $stmt = $pdo->prepare($sql);
        $stmt->execute(['alimentoId' => $alimentoId]);

        echo json_encode(['status' => 'alimento deletado com sucesso']);
    } else {
        echo json_encode(['error' => 'ID do produto nao informado']);
    }

} catch (PDOException $e) {
    echo "Erro de comecao£o: " . $e->getMessage();
    exit;
}
?>


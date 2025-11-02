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

    if (isset($_POST['nome'], $_POST['porcao'], $_POST['caloria'])) {
        $alimentoNome = $_POST['nome'];
        $alimentoPorcao = $_POST['porcao'];
        $alimentoCaloria = $_POST['caloria'];
     

        // Inserir o produto
        $sql = "insert tb_alimentos (nome, porcao, caloria) values (:nome, :porcao, :caloria)";
        $stmt = $pdo->prepare($sql);
        $stmt->execute([
            'nome' => $alimentoNome,
            'porcao' => $alimentoPorcao,
            'caloria' => $alimentoCaloria
        ]);

        echo json_encode(['status' => 'Alimento Cadastrado com sucesso']);
    } else {
        echo json_encode(['error' => 'Dados incompletos']);
    }

} catch (PDOException $e) {
    echo "Erro de conexÃƒÂ£o: " . $e->getMessage();
    exit;
}
?>


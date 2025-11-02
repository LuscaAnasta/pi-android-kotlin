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

    if (isset($_POST['id'], $_POST['nome'], $_POST['email'], $_POST['senha'])) {
        $usuarioId = $_POST['id'];
        $usuarioNome = $_POST['nome'];
        $usuarioEmail = $_POST['email'];
        $usuarioSenha = $_POST['senha'];
        

        // Atualizar o produto no banco de dados
        $sql = "UPDATE tb_usuarios SET nome = :usuarioNome, email = :usuarioEmail, senha = :usuarioSenha 
                WHERE id = :usuarioId";
        $stmt = $pdo->prepare($sql);
        $stmt->execute([
            'usuarioNome' => $usuarioNome,
            'usuarioEmail' => $usuarioEmail,
            'usuarioSenha' => $usuarioSenha,
            'usuarioId' => $usuarioId
        ]);

        echo json_encode(['status' => 'Usuario atualizado com sucesso']);
    } else {
        echo json_encode(['error' => 'Dados incompletos']);
    }

} catch (PDOException $e) {
    echo "Erro de conexÃƒÂ£o: " . $e->getMessage();
    exit;
}
?>


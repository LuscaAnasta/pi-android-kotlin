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

    if (isset($_POST['cpf'], $_POST['nome'], $_POST['nomeCompleto'], $_POST['email'], $_POST['senha'], $_POST['dataNascimento'])) {
        $usuarioCpf = $_POST['cpf'];
        $usuarioNome = $_POST['nome'];
        $usuarioNomeCompleto = $_POST['nomeCompleto'];
        $usuarioEmail = $_POST['email'];
        $usuarioSenha = $_POST['senha'];
        $usuarioDataNasc = $_POST['dataNascimento'];

        // Inserir o produto
        $sql = "INSERT INTO tb_usuarios (cpf, nome, nomeCompleto, email, senha, dataNascimento)
                VALUES (:usuarioCpf, :usuarioNome, :usuarioNomeCompleto, :usuarioEmail, :usuarioSenha, :usuarioDataNasc)";
        $stmt = $pdo->prepare($sql);
        $stmt->execute([
            'usuarioCpf' => $usuarioCpf,
            'usuarioNome' => $usuarioNome,
            'usuarioNomeCompleto' => $usuarioNomeCompleto,
            'usuarioEmail' => $usuarioEmail,
            'usuarioSenha' => $usuarioSenha,
            'usuarioDataNasc' => $usuarioDataNasc
        ]);

        echo json_encode(['status' => 'Cliente Cadastrado com sucesso']);
    } else {
        echo json_encode(['error' => 'Dados incompletos']);
    }

} catch (PDOException $e) {
    echo "Erro de conexÃƒÂ£o: " . $e->getMessage();
    exit;
}
?>


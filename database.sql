CREATE TABLE cliente (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome VARCHAR(255) NOT NULL,
    cpf INTEGER,
    telefone VARCHAR(255) NOT NULL, 
);

CREATE TABLE clienteVeiculo (
        id INTEGER FOREIGN KEY,
        veiculo VARCHAR(255) NOT NULL,
        cliente VARCHAR(255) NOT NULL,
    );

CREATE TABLE mensalidade (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    valor INTEGER,
);

CREATE TABLE ticket (
    id INTEGER PRIMARY KEY AUTO,
    entrega INTEGER,
    saida INTEGER,
    veiculo INTEGER,
    
);
CREATE TABLE pagamento (
    id INTEGER FOREIGN KEY,
    pago INTEGER,
    mensalidade INTEGER,
    algo TABLE,

);

CREATE TABLE tabelaPreco (
    id INTEGER FOREIGN KEY,
    periodo INTEGER,
    valor INTEGER,
    campo INTEGER,

);

CREATE TABLE veiculo (
    id INTEGER PRIMARY KEY,
    placa INTEGER,
    cor VARCHAR(255) NOT NULL,
);

CREATE TABLE tabelaPreco (
    id INTEGER FOREIGN KEY,
    periodo INTEGER,
    valor INTEGER,
    campo INTEGER,
);
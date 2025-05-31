/* Quimera_BD_Logico: */

CREATE TABLE Usuario (
    id INTEGER PRIMARY KEY,
    usuario VARCHAR(30),
    senha VARCHAR(50),
    email VARCHAR(100),
    login_ativo VARCHAR(1),
    nome_completo VARCHAR(100),
    data_nascimento DATE
);

CREATE TABLE Tarefa (
    id INTEGER PRIMARY KEY,
    titulo VARCHAR(50),
    descricao VARCHAR(500),
    situacao VARCHAR(1),
    data_inicio DATE,
    data_fim DATE,
    fk_Usuario_id INTEGER
);
 
ALTER TABLE Tarefa ADD CONSTRAINT FK_Tarefa_2
    FOREIGN KEY (fk_Usuario_id)
    REFERENCES Usuario (id)
    ON DELETE SET NULL;
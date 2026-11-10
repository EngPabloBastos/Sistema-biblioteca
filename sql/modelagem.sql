CREATE TABLE item (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    codigo VARCHAR(20) NOT NULL UNIQUE,
    titulo VARCHAR(150) NOT NULL,
    tipo VARCHAR(10) NOT NULL CHECK (tipo IN ('livro', 'revista')),
    autor VARCHAR(100),
    edicao INTEGER,
    disponivel BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE usuario (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    tipo VARCHAR(10) NOT NULL CHECK (tipo IN ('aluno', 'professor')),
    limite_itens INTEGER NOT NULL CHECK (limite_itens > 0)
);

CREATE TABLE emprestimo (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    item_id INTEGER NOT NULL REFERENCES item(id),
    usuario_id INTEGER NOT NULL REFERENCES usuario(id),
    data_retirada DATE NOT NULL,
    data_devolucao_prevista DATE NOT NULL,
    data_devolucao DATE,
    valor_multa NUMERIC(8, 2) NOT NULL DEFAULT 0
);

INSERT INTO item (codigo, titulo, tipo, autor, edicao, disponivel) VALUES
    ('L001', 'Dom Casmurro', 'livro', 'Machado de Assis', NULL, TRUE),
    ('L002', 'Clean Code', 'livro', 'Robert C. Martin', NULL, FALSE),
    ('R001', 'Superinteressante', 'revista', NULL, 450, TRUE),
    ('R002', 'Veja', 'revista', NULL, 2900, TRUE);

INSERT INTO usuario (nome, tipo, limite_itens) VALUES
    ('Ana', 'aluno', 3),
    ('Carlos', 'professor', 5);

INSERT INTO emprestimo (item_id, usuario_id, data_retirada, data_devolucao_prevista, data_devolucao, valor_multa) VALUES
    ((SELECT id FROM item WHERE codigo = 'L001'), (SELECT id FROM usuario WHERE nome = 'Ana'),
     '2026-10-01', '2026-10-15', '2026-10-21', 3.00),
    ((SELECT id FROM item WHERE codigo = 'L002'), (SELECT id FROM usuario WHERE nome = 'Carlos'),
     '2026-11-03', '2026-11-17', NULL, 0);

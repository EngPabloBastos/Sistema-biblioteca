SELECT codigo, titulo, tipo, disponivel
FROM item
ORDER BY codigo;

SELECT u.nome AS usuario, i.titulo AS item
FROM emprestimo e
JOIN usuario u ON u.id = e.usuario_id
JOIN item i ON i.id = e.item_id
WHERE e.data_devolucao IS NULL;

SELECT u.nome AS usuario, SUM(e.valor_multa) AS total_multas
FROM usuario u
JOIN emprestimo e ON e.usuario_id = u.id
GROUP BY u.id, u.nome
ORDER BY u.nome;

SELECT i.codigo, i.titulo
FROM item i
LEFT JOIN emprestimo e ON e.item_id = i.id
WHERE e.id IS NULL;

-- CATEGORIAS
INSERT INTO categoria (nome, descricao) VALUES ('Perifericos', 'Perifericos e Acessorios para Computador');
INSERT INTO categoria (nome, descricao) VALUES ('Livros', 'Livros Tecnicos e Didaticos');
INSERT INTO categoria (nome, descricao) VALUES ('Hardware', 'Pecas e Componentes de Hardware');
INSERT INTO categoria (nome, descricao) VALUES ('Monitores', 'Monitores e Displays de Alta Definicao');
INSERT INTO categoria (nome, descricao) VALUES ('Audio', 'Headsets, Fones e Caixas de Som');

-- PRODUTOS
INSERT INTO produto (nome, descricao, preco, estoque, categoria_id) VALUES ('Teclado Mecanico RGB', 'Teclado switch blue com iluminacao RGB e anti-ghosting', 289.90, 25, 1);
INSERT INTO produto (nome, descricao, preco, estoque, categoria_id) VALUES ('Arquitetura Limpa', 'Livro do Autor Robert C. Martin sobre software design', 92.50, 15, 2);
INSERT INTO produto (nome, descricao, preco, estoque, categoria_id) VALUES ('Placa de Video RTX 4060 8GB', 'Placa de video com Ray Tracing e DLSS 3', 2399.00, 10, 3);
INSERT INTO produto (nome, descricao, preco, estoque, categoria_id) VALUES ('Monitor Gamer 24 Pol 144Hz', 'Painel IPS Full HD tempo de resposta 1ms', 899.90, 18, 4);
INSERT INTO produto (nome, descricao, preco, estoque, categoria_id) VALUES ('Headset Gamer 7.1 Surround', 'Headset com microfone retratil e cancelamento de ruido', 349.00, 30, 5);

-- CLIENTES
INSERT INTO cliente (nome, email, telefone) VALUES ('Kaua Gustavo', 'kaua.gustavo@email.com', '1499888-1122');
INSERT INTO cliente (nome, email, telefone) VALUES ('Mariana Silva', 'mariana.silva@email.com', '1499777-2233');
INSERT INTO cliente (nome, email, telefone) VALUES ('Lucas Ferreira', 'lucas.ferreira@email.com', '1499666-3344');
INSERT INTO cliente (nome, email, telefone) VALUES ('Beatriz Souza', 'beatriz.souza@email.com', '1499555-4455');
INSERT INTO cliente (nome, email, telefone) VALUES ('Gabriel Oliveira', 'gabriel.oliveira@email.com', '1499444-5566');

-- PEDIDOS
INSERT INTO pedido (data, status, valor_total, cliente_id) VALUES (NOW(), 'Aguardando Pagamento', 289.90, 1);
INSERT INTO pedido (data, status, valor_total, cliente_id) VALUES (NOW(), 'Pago', 92.50, 2);
INSERT INTO pedido (data, status, valor_total, cliente_id) VALUES (NOW(), 'Enviado', 2399.00, 3);
INSERT INTO pedido (data, status, valor_total, cliente_id) VALUES (NOW(), 'Entregue', 1248.90, 4);
INSERT INTO pedido (data, status, valor_total, cliente_id) VALUES (NOW(), 'Cancelado', 349.00, 5);

-- ITENS DE PEDIDO
INSERT INTO item_pedido (quantidade, valor_unitario, pedido_id, produto_id) VALUES (1, 289.90, 1, 1);
INSERT INTO item_pedido (quantidade, valor_unitario, pedido_id, produto_id) VALUES (1, 92.50, 2, 2);
INSERT INTO item_pedido (quantidade, valor_unitario, pedido_id, produto_id) VALUES (1, 2399.00, 3, 3);
INSERT INTO item_pedido (quantidade, valor_unitario, pedido_id, produto_id) VALUES (1, 899.90, 4, 4);
INSERT INTO item_pedido (quantidade, valor_unitario, pedido_id, produto_id) VALUES (1, 349.00, 4, 5);

-- PAGAMENTOS
INSERT INTO pagamento (valor, data, status, tipo, pedido_id) VALUES (289.90, NOW(), 'Pendente', 'PIX', 1);
INSERT INTO pagamento (valor, data, status, tipo, pedido_id) VALUES (92.50, DATE_SUB(NOW(), INTERVAL 3 DAY), 'Aprovado', 'CARTAO_CREDITO', 2);
INSERT INTO pagamento (valor, data, status, tipo, pedido_id) VALUES (2399.00, DATE_SUB(NOW(), INTERVAL 2 DAY), 'Aprovado', 'BOLETO', 3);
INSERT INTO pagamento (valor, data, status, tipo, pedido_id) VALUES (1248.90, DATE_SUB(NOW(), INTERVAL 1 DAY), 'Aprovado', 'PIX', 4);
INSERT INTO pagamento (valor, data, status, tipo, pedido_id) VALUES (349.00, NOW(), 'Cancelado', 'CARTAO_CREDITO', 5);
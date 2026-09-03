INSERT INTO categoria (nome, descricao) values ('Informática','Produtos de Informática');
INSERT INTO categoria (nome, descricao) values ('Eletrônicos','Produtos Eletrônicos');
INSERT INTO categoria (nome, descricao) values ('Games','Produtos de Games');
INSERT INTO categoria (nome, descricao) values ('Celulares','Produtos de Celulares');
INSERT INTO categoria (nome, descricao) values ('Livros','Livros tecnicos');

INSERT INTO produto (nome, descricao, estoque, preco, categoria_id) values ('teclado',' logitec gpro', 10, 120.00, 1 );
INSERT INTO produto (nome, descricao, estoque, preco, categoria_id) values ('mouse',' attack shark x11', 10, 197.00, 1 );
INSERT INTO produto (nome, descricao, estoque, preco, categoria_id) values ('noites brancas','Fiódor Dostoiévski', 10, 165.00, 2 );
INSERT INTO produto (nome, descricao, estoque, preco, categoria_id) values ('crime e castigo','Fiódor Dostoiévski', 10, 100.00, 2 );
INSERT INTO produto (nome, descricao, estoque, preco, categoria_id) values ('o idiota','Fiódor Dostoiévski', 10, 100.00, 2 );

INSERT INTO cliente (nome, email, telefone) values ('João Silva', 'joao.silva@email.com', '11999999999');
INSERT INTO cliente (nome, email, telefone) values ('Maria Souza', 'maria.souza@email.com', '11988888888');
INSERT INTO cliente (nome, email, telefone) values ('Carlos Oliveira', 'carlos.oliveira@email.com', '11977777777');
INSERT INTO cliente (nome, email, telefone) values ('Ana Santos', 'ana.santos@email.com', '11966666666');
INSERT INTO cliente (nome, email, telefone) values ('Pedro Lima', 'pedro.lima@email.com', '11955555555');

INSERT INTO pedido (data, cliente_id, status, valor_total) values ('2023-01-01', 1, 'PENDENTE', 112.00);
INSERT INTO pedido (data, cliente_id, status, valor_total) values ('2023-01-02', 2, 'ANDAMENTO', 104.00);
INSERT INTO pedido (data, cliente_id, status, valor_total) values ('2023-01-03', 3, 'FINALIZADO', 203.00);
INSERT INTO pedido (data, cliente_id, status, valor_total) values ('2023-01-04', 4, 'FINALIZADO', 20.00);
INSERT INTO pedido (data, cliente_id, status, valor_total) values ('2023-01-05', 5, 'ANDAMENTO', 59.00);

INSERT INTO item_pedido (pedido_id, produto_id, quantidade, valor_unitario) values (9, 1, 2, 50.00);
INSERT INTO item_pedido (pedido_id, produto_id, quantidade, valor_unitario) values (10, 2, 1, 12.00);
INSERT INTO item_pedido (pedido_id, produto_id, quantidade, valor_unitario) values (11, 3, 1, 104.00);
INSERT INTO item_pedido (pedido_id, produto_id, quantidade, valor_unitario) values (12, 6, 1, 203.00);
INSERT INTO item_pedido (pedido_id, produto_id, quantidade, valor_unitario) values (13, 5, 1, 20.00);

INSERT INTO pagamento (pedido_id, data, valor, tipo, status) values (9, '2023-01-01', 112.00, 'CARTAO', 'PENDENTE');
INSERT INTO pagamento (pedido_id, data, valor, tipo, status) values (10, '2023-01-02', 104.00, 'DINHEIRO', 'ANDAMENTO');
INSERT INTO pagamento (pedido_id, data, valor, tipo, status) values (11, '2023-01-03', 203.00, 'CARTAO', 'FINALIZADO');
INSERT INTO pagamento (pedido_id, data, valor, tipo, status) values (12, '2023-01-04', 20.00, 'DINHEIRO', 'FINALIZADO');
INSERT INTO pagamento (pedido_id, data, valor, tipo, status) values (13, '2023-01-05', 59.00, 'CARTAO', 'ANDAMENTO');
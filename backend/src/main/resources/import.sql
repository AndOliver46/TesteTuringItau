INSERT INTO tb_agencia(codigo) VALUES ('5300');
INSERT INTO tb_agencia(codigo) VALUES ('7420');
INSERT INTO tb_agencia(codigo) VALUES ('8569');
INSERT INTO tb_agencia(codigo) VALUES ('0249');

INSERT INTO tb_cliente(cpf, nome) VALUES ('42454678967', 'Anderson Souza');
INSERT INTO tb_cliente(cpf, nome) VALUES ('65784765433', 'Erica Santos');
INSERT INTO tb_cliente(cpf, nome) VALUES ('86987675899', 'Ana Green');
INSERT INTO tb_cliente(cpf, nome) VALUES ('65674654633', 'Bob Brown');
INSERT INTO tb_cliente(cpf, nome) VALUES ('76574876577', 'Alex Blue');

INSERT INTO tb_conta(numero, saldo, senha, authoritie, agencia_id, id_cliente) VALUES ('21554', 00.00, '$2a$10$acoc/IcsjzXAISDJmIN9w.ywkuKg6wAGZGP3m.rzIu9eymK6rpiKq', 'ROLE_CLIENT', 1, 1);
INSERT INTO tb_conta(numero, saldo, senha, authoritie, agencia_id, id_cliente) VALUES ('21675', 00.00, '$2a$10$acoc/IcsjzXAISDJmIN9w.ywkuKg6wAGZGP3m.rzIu9eymK6rpiKq', 'ROLE_CLIENT', 1, 2);
INSERT INTO tb_conta(numero, saldo, senha, authoritie, agencia_id, id_cliente) VALUES ('21564', 00.00, '$2a$10$acoc/IcsjzXAISDJmIN9w.ywkuKg6wAGZGP3m.rzIu9eymK6rpiKq', 'ROLE_CLIENT', 3, 3);
INSERT INTO tb_conta(numero, saldo, senha, authoritie, agencia_id, id_cliente) VALUES ('21567', 00.00, '$2a$10$acoc/IcsjzXAISDJmIN9w.ywkuKg6wAGZGP3m.rzIu9eymK6rpiKq', 'ROLE_CLIENT', 2, 4);
INSERT INTO tb_conta(numero, saldo, senha, authoritie, agencia_id, id_cliente) VALUES ('26156', 00.00, '$2a$10$acoc/IcsjzXAISDJmIN9w.ywkuKg6wAGZGP3m.rzIu9eymK6rpiKq', 'ROLE_CLIENT', 4, 5);

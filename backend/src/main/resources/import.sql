INSERT INTO tb_agencia(codigo) VALUES ('5300');
INSERT INTO tb_agencia(codigo) VALUES ('7420');
INSERT INTO tb_agencia(codigo) VALUES ('8569');
INSERT INTO tb_agencia(codigo) VALUES ('0249');
INSERT INTO tb_agencia(codigo) VALUES ('0048');
INSERT INTO tb_agencia(codigo) VALUES ('3789');
INSERT INTO tb_agencia(codigo) VALUES ('9265');
INSERT INTO tb_agencia(codigo) VALUES ('3751');

INSERT INTO tb_cliente(cpf, nome) VALUES ('42454678967', 'Anderson Souza');
INSERT INTO tb_cliente(cpf, nome) VALUES ('65784765433', 'Erica Santos');
INSERT INTO tb_cliente(cpf, nome) VALUES ('86987675899', 'Ana Green');
INSERT INTO tb_cliente(cpf, nome) VALUES ('65674654633', 'Bob Brown');
INSERT INTO tb_cliente(cpf, nome) VALUES ('76574876577', 'Alex Blue');

INSERT INTO tb_conta(numero, saldo, senha, authoritie, agencia_id, id_cliente) VALUES ('215545', 0.0, '$2a$10$kKPPPw4k67QtgeNgwmghwOKnPIZn2EVpFRepy7vOAFsKFtkUhhJyq', 'ROLE_CLIENT', 1, 1);
INSERT INTO tb_conta(numero, saldo, senha, authoritie, agencia_id, id_cliente) VALUES ('216755', 0.0, '$2a$10$kKPPPw4k67QtgeNgwmghwOKnPIZn2EVpFRepy7vOAFsKFtkUhhJyq', 'ROLE_CLIENT', 1, 2);
INSERT INTO tb_conta(numero, saldo, senha, authoritie, agencia_id, id_cliente) VALUES ('215647', 0.0, '$2a$10$kKPPPw4k67QtgeNgwmghwOKnPIZn2EVpFRepy7vOAFsKFtkUhhJyq', 'ROLE_CLIENT', 3, 3);
INSERT INTO tb_conta(numero, saldo, senha, authoritie, agencia_id, id_cliente) VALUES ('215674', 0.0, '$2a$10$kKPPPw4k67QtgeNgwmghwOKnPIZn2EVpFRepy7vOAFsKFtkUhhJyq', 'ROLE_CLIENT', 2, 4);
INSERT INTO tb_conta(numero, saldo, senha, authoritie, agencia_id, id_cliente) VALUES ('261567', 0.0, '$2a$10$kKPPPw4k67QtgeNgwmghwOKnPIZn2EVpFRepy7vOAFsKFtkUhhJyq', 'ROLE_CLIENT', 4, 5);


INSERT INTO tb_transferencia(data_hora, tipo, valor, id_emissor, id_receptor) VALUES (TIMESTAMP WITH TIME ZONE '2020-07-14T10:00:00Z', '1', 2000.00, 1, 2);

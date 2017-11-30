-- criar banco
CREATE DATABASE IF NOT EXISTS crud_agenda;

-- usar banco
USE crud_agenda;

-- criar tabela
CREATE TABLE IF NOT EXISTS contatos (
  contato_id INT(5) NOT NULL AUTO_INCREMENT,
  nome VARCHAR(100) NOT NULL,
  numero VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL,
  PRIMARY KEY (contato_id)
) ENGINE=INNODB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- popular tabela
INSERT INTO contatos (contato_id, nome, numero, email) VALUES
	(1, 'Rafael de Brito Lima', '98803-5636', 'rafaeldblima@gmail.com'),
	(2, 'Fulano', '3333-3333', 'fulano@hotmail.com'),
	(3, 'Pedro', '6444-8789', 'pedro@bol.com.br'),
	(4, 'Jorge', '4455-5584', 'jorge123@yahoo.com.br'); 
	
-- checar dados	
SELECT * FROM contatos
	
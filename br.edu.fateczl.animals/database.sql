CREATE DATABASE animalsdb;
USE animalsdb;

CREATE TABLE habitat(
	id INT AUTO_INCREMENT PRIMARY KEY,
	nome VARCHAR(32),
	tipo VARCHAR(32),
	descricao VARCHAR(128),
	clima VARCHAR(16),
	altitude NUMERIC(6,2)
);

CREATE TABLE animal(
	id INT AUTO_INCREMENT PRIMARY KEY,
	nome_popular VARCHAR(64),
	genero VARCHAR(32),
	especie VARCHAR(64),
	classe VARCHAR(16),
	id_habitat INT,
	altura NUMERIC(5,2),
	peso NUMERIC(8,2),
	FOREIGN KEY (id_habitat) REFERENCES habitat(id)
);

INSERT INTO habitat (nome, tipo, descricao, clima, altitude) VALUES
('Floresta Amazônica', 'Floresta Tropical', 'Maior floresta tropical do mundo, rica em biodiversidade', 'Tropical', 100.00),
('Savana Africana', 'Savana', 'Região de pastagens com árvores esparsas, habitat de muitos grandes mamíferos', 'Tropical', 500.00),
('Deserto do Saara', 'Deserto', 'Maior deserto quente do mundo, com clima árido e pouca vegetação', 'Árido', 200.00),
('Pantanal', 'Pântano', 'Maior área úmida tropical do mundo, rica em vida selvagem', 'Tropical', 150.00),
('Ártico', 'Polar', 'Região gelada no extremo norte do planeta, habitat de espécies adaptadas ao frio extremo', 'Polar', 0.00);

INSERT INTO animal (nome_popular, genero, especie, classe, id_habitat, altura, peso) VALUES
('Elefante Africano', 'Loxodonta', 'Loxodonta africana', 'Mammalia', 2, 3.30, 6000.00),
('Girafa', 'Giraffa', 'Giraffa camelopardalis', 'Mammalia', 2, 5.50, 1200.00),
('Urso Polar', 'Ursus', 'Ursus maritimus', 'Mammalia', 5, 2.50, 700.00),
('Crocodilo de Água Salgada', 'Crocodylus', 'Crocodylus porosus', 'Reptilia', 3, 6.00, 1000.00),
('Avestruz', 'Struthio', 'Struthio camelus', 'Aves', 2, 2.70, 150.00);



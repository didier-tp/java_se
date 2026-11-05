CREATE DATABASE person_db;
\c person_db;
DROP TABLE IF EXISTS personne;
CREATE TABLE personne  (id SERIAL NOT NULL,
                        nom VARCHAR(64),
                        age integer,
                        poids REAL,
                        PRIMARY KEY(id));

INSERT INTO personne(nom,age,poids) VALUES ('jean_bon',45, 67.8);
INSERT INTO personne(nom,age,poids) VALUES ('axelle_aire',38, 82.56);

SELECT * FROM personne;
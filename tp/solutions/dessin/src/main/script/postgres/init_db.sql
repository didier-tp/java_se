CREATE DATABASE drawing_db;
\c drawing_db
DROP TABLE IF EXISTS dessin;
CREATE TABLE dessin  (id SERIAL NOT NULL,
                        titre VARCHAR(64),
                        path VARCHAR(255),
                        description VARCHAR(64),
                        PRIMARY KEY(id));

INSERT INTO dessin(titre,path,description) VALUES ('dessin1','dessin.svg', 'genere par code java');

SELECT * FROM dessin;
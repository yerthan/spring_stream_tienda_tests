DROP DATABASE IF EXISTS tienda;
CREATE DATABASE tienda CHARACTER SET utf8mb4;
USE tienda;

CREATE TABLE fabricante (
                            codigo INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                            nombre VARCHAR(100) NOT NULL
);

CREATE TABLE producto (
                          codigo INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                          nombre VARCHAR(100) NOT NULL,
                          precio DOUBLE NOT NULL,
                          codigo_fabricante INT UNSIGNED NOT NULL,
                          FOREIGN KEY (codigo_fabricante) REFERENCES fabricante(codigo)
);

INSERT INTO fabricante VALUES(1, 'Asus');
INSERT INTO fabricante VALUES(2, 'Lenovo');
INSERT INTO fabricante VALUES(3, 'Hewlett-Packard');
INSERT INTO fabricante VALUES(4, 'Samsung');
INSERT INTO fabricante VALUES(5, 'Seagate');
INSERT INTO fabricante VALUES(6, 'Crucial');
INSERT INTO fabricante VALUES(7, 'Gigabyte');
INSERT INTO fabricante VALUES(8, 'Huawei');
INSERT INTO fabricante VALUES(9, 'Xiaomi');

INSERT INTO producto VALUES(1, 'Disco duro SATA3 1TB', 86.99, 5);
INSERT INTO producto VALUES(2, 'Memoria RAM DDR4 8GB', 120, 6);
INSERT INTO producto VALUES(3, 'Disco SSD 1 TB', 150.99, 4);
INSERT INTO producto VALUES(4, 'GeForce GTX 1050Ti', 185, 7);
INSERT INTO producto VALUES(5, 'GeForce GTX 1080 Xtreme', 755, 6);
INSERT INTO producto VALUES(6, 'Monitor 24 LED Full HD', 202, 1);
INSERT INTO producto VALUES(7, 'Monitor 27 LED Full HD', 245.99, 1);
INSERT INTO producto VALUES(8, 'Portátil Yoga 520', 559, 2);
INSERT INTO producto VALUES(9, 'Portátil Ideapd 320', 444, 2);
INSERT INTO producto VALUES(10, 'Impresora HP Deskjet 3720', 59.99, 3);
INSERT INTO producto VALUES(11, 'Impresora HP Laserjet Pro M26nw', 180, 3);


use tienda;


-- 1Lista los nombres y los precios de todos los productos de la tabla producto

    select p.nombre, p.precio from producto as p;

-- 2 Devuelve una lista de Producto completa con el precio de euros convertido a dólares .

    select * from producto where producto.precio * 1.1;

-- 3 Lista los nombres y los precios de todos los productos, convirtiendo los nombres a mayúscula

    select UPPER(producto.nombre) as nombre_Producto, producto.precio from producto ;

-- 4  Lista el nombre de todos los fabricantes y a continuación en mayúsculas los dos primeros caracteres del nombre del fabricante.

SELECT
    CONCAT(UPPER(SUBSTRING(fabricante.nombre, 1, 2)), LOWER(SUBSTRING(fabricante.nombre, 3))) AS nombre_formateado
FROM
    fabricante;

-- 5 Lista el código de los fabricantes que tienen productos.

select fabricante.codigo from fabricante join fabricante on producto.codigo_fabricante = fabricante.codigo where producto.codigo_fabricante LIKE fabricante.codigo;






/*
-- 18
select p.nombre, p.precio*100 from producto as p;

-- 19

select nombre from fabricante where nombre LIKE 's%';

SELECT * from fabricante where nombre like '%Port?til%';*/


select p.nombre, p.precio FROM producto AS p WHERE p.precio >= 180 ORDER BY precio DESC, nombre asc;




-- Ejercicio 29

Select f.* from fabricante as f where f.codigo not in (SELECT producto.codigo_fabricante from producto);
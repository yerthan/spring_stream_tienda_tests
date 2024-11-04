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

SELECT distinct codigo_fabricante from producto;

-- 6 Lista los nombres de los fabricantes ordenados de forma descendente.

    SELECT nombre from fabricante order by nombre desc;

-- 7 Lista los nombres de los productos ordenados en primer lugar por el
-- nombre de forma ascendente y en segundo lugar por el precio de forma descendente.

SELECT nombre, precio from producto order by nombre asc, precio desc;

-- 8 Devuelve una lista con los 5 primeros fabricantes.

SELECT nombre from fabricante limit 5;

-- 9 Devuelve una lista con 2 fabricantes a partir del cuarto fabricante.
-- El cuarto fabricante también se debe incluir en la respuesta.
SELECT nombre from fabricante limit 2 offset 3;

-- 10 Lista el nombre y el precio del producto más barato
SELECT nombre, precio from producto order by precio asc limit 1;

-- 11Lista el nombre y el precio del producto más caro
SELECT nombre, precio from producto order by precio desc limit 1;

/* 12 Lista el nombre de todos los productos del fabricante cuyo código de fabricante es igual a 2.*/

    SELECT nombre from producto where codigo_fabricante = 2;

/*13 Lista el nombre de los productos que tienen un precio menor o igual a 120€.*/

    SELECT nombre from producto where precio <= 120;

/*14 Lista los productos que tienen un precio mayor o igual a 400€.*/
SELECT * from tienda.producto p where p.precio >= 400;

/* 15. Lista todos los productos que tengan un precio entre 80€ y 300€. */

SELECT * from tienda.producto p where p.precio >= 80 and p.precio <= 300;


/* 16. Lista todos los productos que tengan un precio mayor que 200€ y que el código de fabricante sea igual a 6.*/


   select * from tienda.producto p where p.precio > 200 and codigo_fabricante = 6;

/* 17 Lista todos los productos donde el código de fabricante sea 1, 3 o 5*/

select * from producto where codigo_fabricante in (1, 3, 5);

/*18 Lista el nombre y el precio de los productos en céntimos */
select p.nombre, p.precio*100 from producto as p;

/* 19. Lista los nombres de los fabricantes cuyo nombre empiece por la letra S*/


select nombre from fabricante where nombre LIKE 's%';

/*20. Devuelve una lista con los productos que contienen la cadena Portátil en el nombre.*/

SELECT * from fabricante where nombre like '%Port?til%';

/*Devuelve una lista con el nombre de todos los productos que contienen la cadena Monitor en el nombre y tienen un precio inferior a 215 €.*/

select * from producto where producto.nombre like '%_onitor%' and producto.precio < 215;

/* 22 */

select * from producto where producto.precio >= 180 order by precio desc, nombre asc;

/* 23 */

select p.nombre, p.precio, f.nombre from producto
    as p join fabricante as f on
    p.codigo_fabricante = f.codigo order by f.nombre asc;

/* 24 */
    select * from producto order by precio desc limit 1;

/* 25 */

select p.nombre from producto as p join fabricante as f on
p.codigo_fabricante = f.codigo
where p.precio > 200 and f.nombre like 'Crucial';

/* 26 */
select p.* from producto as p join fabricante as f on
p.codigo_fabricante = f.codigo
where f.nombre in ('Asus', 'Hewlett-Packard', 'Seagate');

/* 27 */

select p.nombre, p.precio from producto as p join fabricante as f on
p.codigo_fabricante = f.codigo
where p.precio >= 100 order by p.precio desc, p.codigo asc;


/* 28 */
select * from fabricante join producto on
fabricante.codigo = producto.codigo_fabricante;

-- Ejercicio 29

Select f.* from fabricante as f where f.codigo
not in (SELECT producto.codigo_fabricante from producto);

/* 30 */
select count(*) as total_productos from producto;

/* 31 */
select count(distinct codigo_fabricante) from producto;

/* 32 */

select avg(producto.precio) from producto;

/* 33 */

select min(producto.precio) from producto;

/* 34 */
select sum(producto.precio) from producto;

/* 35 */
select count(p.codigo_fabricante) from producto p join tienda.fabricante f on
f.codigo = p.codigo_fabricante
where lower(f.nombre) = lower('asus');

/* 36 */
select avg(p.precio) from producto p join tienda.fabricante f on
p.codigo_fabricante = f.codigo
where f.nombre like 'Asus';

/* 37 */

select * from producto join tienda.fabricante on
producto.codigo_fabricante = fabricante.codigo
where fabricante.nombre = 'Crucial';

/* 38 */
select fabricante.nombre, count(*) from fabricante join producto on
producto.codigo_fabricante = fabricante.codigo group by fabricante.codigo;

/* 39 */


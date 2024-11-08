package org.iesvdm.tienda;

import org.iesvdm.tienda.modelo.Fabricante;
import org.iesvdm.tienda.modelo.Producto;
import org.iesvdm.tienda.repository.FabricanteRepository;
import org.iesvdm.tienda.repository.ProductoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FilterOutputStream;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.*;


@SpringBootTest
class TiendaApplicationTests {

	@Autowired
	FabricanteRepository fabRepo;

	@Autowired
	ProductoRepository prodRepo;

	@Test
	void testAllFabricante() {
		var listFabs = fabRepo.findAll();

		listFabs.forEach(f -> {
			System.out.println(">>" + f + ":");
			f.getProductos().forEach(System.out::println);
		});
	}

	@Test
	void testAllProducto() {
		var listProds = prodRepo.findAll();

		listProds.forEach(p -> {
			System.out.println(">>" + p + ":" + "\nProductos mismo fabricante " + p.getFabricante());
			p.getFabricante().getProductos().forEach(pF -> System.out.println(">>>>" + pF));
		});

	}


	/**
	 * 1. Lista los nombres y los precios de todos los productos de la tabla producto
	 */
	@Test
	void test1() {
		var listProds = prodRepo.findAll();
		//TODO

		listProds.stream().forEach(System.out::println);
		var lista = listProds.stream().count();
		listProds.stream().forEach(x -> System.out.println("Nombre: " + x.getNombre() + " precio " + x.getPrecio()));




		Assertions.assertEquals(11, lista);

	}


	/**
	 * 2. Devuelve una lista de Producto completa con el precio de euros convertido a dólares .
	 */
	@Test
	void test2() {
		var listProds = prodRepo.findAll();
		//TODO

		listProds.stream().forEach(producto -> producto.setPrecio(producto.getPrecio() * 1.1));
		listProds.forEach(System.out::println);



	}

	/**
	 * 3. Lista los nombres y los precios de todos los productos, convirtiendo los nombres a mayúscula.
	 */
	@Test
	void test3() {
		var listProds = prodRepo.findAll();
		//TODO

		listProds.stream().forEach(producto -> System.out.println(producto.getNombre().toUpperCase() + " " + producto.getPrecio()));

		Assertions.assertEquals(11, listProds.size());

	}

	/**
	 * 4. Lista el nombre de todos los fabricantes y a continuación en mayúsculas los dos primeros caracteres del nombre del fabricante.
	 */
	@Test
	void test4() {
		var listFabs = fabRepo.findAll();
		//TODO

		record ayuda(String nombre, String inicial) {
		}

		var result = listFabs.stream()
				.map(fabricante ->
						new ayuda(fabricante.getNombre(),
								fabricante.getNombre().substring(0, 2).toUpperCase()))
				.toList();

		System.out.println(result);
		result.forEach(ayuda -> System.out.println("nombre " + ayuda.nombre() + " inicial " + ayuda.inicial()));


		Assertions.assertEquals(9, result.size());
	}

	/**
	 * 5. Lista el código de los fabricantes que tienen productos.
	 */
	@Test
	void test5() {
		var listFabs = fabRepo.findAll();
		//TODO

		var lista = listFabs.stream().filter(fabricante -> !fabricante.getProductos().isEmpty()).toList();
		System.out.println(lista);

		Assertions.assertEquals(7, lista.size() );

	}

	/**
	 * 6. Lista los nombres de los fabricantes ordenados de forma descendente.
	 */
	@Test
	void test6() {
		var listFabs = fabRepo.findAll();
		//TODO

		var result = listFabs.stream().sorted(comparing(Fabricante::getNombre).reversed()).toList();
		System.out.println(result);

		Assertions.assertEquals(9, result.size());


		/*var result = listFabs.stream().sorted((f1, f2) ->  f2.getNombre().compareTo(f1.getNombre())).toList();
		result.forEach(fabricante -> System.out.println(fabricante.getNombre()));*/

/*
		var result= listFabs.stream().sorted(Comparator.comparing(Fabricante::getNombre).reversed()).toList();
		result.forEach(fabricante -> System.out.println(fabricante.getNombre()));
*/
	}

	/**
	 * 7. Lista los nombres de los productos ordenados en primer lugar por el nombre de forma ascendente y en segundo lugar por el precio de forma descendente.
	 */
	@Test
	void test7() {
		var listProds = prodRepo.findAll();
		//TODO

		listProds.stream().sorted(comparing(Producto::getNombre).thenComparing(Producto::getPrecio)).toList();
		System.out.println(listProds);

		Assertions.assertEquals(11, listProds.size());

/*
		var result =  listProds.stream().sorted(comparing(Producto::getNombre).thenComparing(Producto::getPrecio, reverseOrder()))
				.toList();

		result.forEach(fabricante -> System.out.println(fabricante.getNombre() + " " + fabricante.getPrecio()));

*/
	}


	/**
	 * 8. Devuelve una lista con los 5 primeros fabricantes.
	 */
	@Test
	void test8() {
		var listFabs = fabRepo.findAll();
		//TODO

		var lista = listFabs.stream()
				.limit(5)
				.toList();

		lista.forEach(System.out::println);

		Assertions.assertTrue(lista.size() == 5);
	}

	/**
	 * 9.Devuelve una lista con 2 fabricantes a partir del cuarto fabricante. El cuarto fabricante también se debe incluir en la respuesta.
	 */
	@Test
	void test9() {
		var listFabs = fabRepo.findAll();
		//TODO

		var lista = listFabs.stream()
				.skip(3)
				.limit(3)
				.toList();

		lista.forEach(System.out::println);

		Assertions.assertTrue(lista.size() == 3);

		/*
		var l = listFabs.stream().skip(3).limit(2).toList();
		System.out.println(l);*/

		Assertions.assertEquals(lista.get(0).getNombre() , "Samsung");
	}

	/**
	 * 10. Lista el nombre y el precio del producto más barato
	 */
	@Test
	void test10() {
		var listProds = prodRepo.findAll();
		//TODO

		var lista2 = listProds.stream()
				.sorted(comparing(Producto::getPrecio))
				.limit(1)
				.toList();

		lista2.forEach(Producto -> System.out.println(Producto.getNombre() + " Precio : " + Producto.getPrecio()));

		Assertions.assertTrue(lista2.size() == 1);


		/*
		var l = listProds.stream().sorted(comparing(Producto::getPrecio)).map(producto ->
				"nombre = " + producto.getNombre() + " precio "+ producto.getPrecio()).limit(1).
				findAny();

		Assertions.assertTrue(l.orElse("").contains("59.99"));*/
	}

	/**
	 * 11. Lista el nombre y el precio del producto más caro
	 */
	@Test
	void test11() {
		var listProds = prodRepo.findAll();
		//TODO

		var lista = listProds.stream()
				.sorted(comparing(Producto::getPrecio).reversed())
				.map(producto -> producto.getPrecio() + producto.getNombre())
				.limit(1)
				.toList();

		lista.forEach(System.out::println);

		Assertions.assertTrue(lista.size() == 1);
	}


	/**
	 * 12. Lista el nombre de todos los productos del fabricante cuyo código de fabricante es igual a 2.
	 */
	@Test
	void test12() {
		var listProds = prodRepo.findAll();
		//TODO

		var lista = listProds.stream().filter(producto -> producto.getFabricante().getCodigo() == 2).toList();

		lista.forEach(System.out::println);
		Assertions.assertEquals(2, lista.size());



	}

	/**
	 * 13. Lista el nombre de los productos que tienen un precio menor o igual a 120€.
	 */
	@Test
	void test13() {
		var listProds = prodRepo.findAll();
		//TODO


		var lista2 = listProds.stream().filter(producto -> producto.getPrecio() <= 120).toList();
		lista2.forEach(System.out::println);

		Assertions.assertTrue(lista2.size() == 3);
	}

	/**
	 * 14. Lista los productos que tienen un precio mayor o igual a 400€.
	 */
	@Test
	void test14() {
		var listProds = prodRepo.findAll();
		//TODO

		List<Producto> l = listProds.stream()
				.filter(producto -> producto.getPrecio() >= 400)
				.toList();

		l.forEach(System.out::println);

		Assertions.assertEquals(3, l.size());

		/*
		List<Producto> lista =listProds.stream().filter(producto -> producto.getPrecio() >= 400).toList();
		lista.stream().forEach(System.out::println);

		Assertions.assertEquals(3, lista.size());
*/
	}

	/**
	 * 15. Lista todos los productos que tengan un precio entre 80€ y 300€.
	 */
	@Test
	void test15() {
		var listProds = prodRepo.findAll();
		//TODO

		var list = listProds.stream()
				.filter(producto -> producto.getPrecio() >= 80 && producto.getPrecio() <= 300)
				.toList();

		list.forEach(System.out::println);
		Assertions.assertEquals(7, list.size());
		/*
		List<Producto> lista = listProds.stream().filter(producto -> producto.getPrecio() >= 80 && producto.getPrecio() <= 300).toList();
		lista.stream().forEach(System.out::println);
*/

	}

	/**
	 * 16. Lista todos los productos que tengan un precio mayor que 200€ y que el código de fabricante sea igual a 6.
	 */
	@Test
	void test16() {
		var listProds = prodRepo.findAll();
		//TODO

		var list = listProds.stream()
				.filter(producto -> producto.getPrecio() > 200 &&
						producto.getFabricante().getCodigo() == 6).toList();

		list.forEach(System.out::println);
		Assertions.assertEquals(1, list.size());

		/*
		var lista = listProds.stream().filter(producto -> producto.getPrecio() > 200 && producto.getCodigo() == 6).toList();

		lista.forEach(System.out::println);
		*/

	}

	/**
	 * 17. Lista todos los productos donde el código de fabricante sea 1, 3 o 5 utilizando un Set de codigos de fabricantes para filtrar.
	 */
	@Test
	void test17() {
		var listProds = prodRepo.findAll();
		//TODO


		Set<Integer> ayuda = Set.of(1, 3, 5);

		var lista = listProds.stream()
				.sorted(comparing(producto -> producto.getFabricante().getCodigo()))
				.filter(producto -> ayuda.contains(producto.getFabricante().getCodigo()))
				.toList();


		lista.forEach(System.out::println);

		Assertions.assertEquals(5, lista.size());



/*
		Set<Integer> ayuda = Set.of(1, 3, 5);

		var result = listProds.stream()
				.filter(producto -> ayuda.contains(producto.getCodigo() == 1 ||
						producto.getCodigo() == 3 ||
						producto.getCodigo() == 5))
				.toList();

		result.forEach(System.out::println);

		Assertions.assertTrue(result.get(0).getFabricante().getCodigo() == 1
					|| result.get(0).getFabricante().getCodigo() == 3
					|| result.get(0).getFabricante().getCodigo() == 5);
					*/

	}

	/**
	 * 18. Lista el nombre y el precio de los productos en céntimos.
	 */
	@Test
	void test18() {
		var listProds = prodRepo.findAll();
		//TODO
/*
		record tupla(String nombre, double precio) {}

		var result = listProds.parallelStream().map(producto ->new tupla(producto.getNombre(), producto.getPrecio()*100)).toList();
		result.forEach(System.out::println);

 */

		var result = listProds.stream()
						.map(p -> p.getNombre() + " " + p.getPrecio() *100)
								.toList();


		Assertions.assertEquals(11, result.size());


		/*var lista = listProds.stream().map(producto -> producto.getNombre() + (producto.getPrecio() *100)).toList();*/
		/*lista.forEach(System.out::println);*/

	}


	/**
	 * 19. Lista los nombres de los fabricantes cuyo nombre empiece por la letra S
	 */
	@Test
	void test19() {
		var listFabs = fabRepo.findAll();
		//TODOS

		var list = listFabs.stream()
				.filter(fabricante -> fabricante.getNombre().charAt(0) == 'S')
				.toList();

		list.forEach(System.out::println);
		Assertions.assertEquals(2, list.size());


		/*
		var lista = listFabs.stream().filter(fabricante -> fabricante.getNombre().charAt(0) == 'S').toList();

		lista.forEach(System.out::println);
		*/

	}

	/**
	 * 20. Devuelve una lista con los productos que contienen la cadena Portátil en el nombre.
	 */
	@Test
	void test20() {
		var listProds = prodRepo.findAll();
		//TODO

		var list = listProds.stream().filter(producto -> producto.getNombre().contains("Portátil"))
				.toList();

		list.forEach(System.out::println);

		Assertions.assertEquals(2, list.size());
	}

	/**
	 * 21. Devuelve una lista con el nombre de todos los productos que contienen la cadena Monitor en el nombre y tienen un precio inferior a 215 €.
	 */
	@Test
	void test21() {
		var listProds = prodRepo.findAll();
		//TODO

		var list = listProds.stream()
				.filter(producto -> producto.getNombre().contains("Monitor")
						&& producto.getPrecio() <= 215)
				.toList();

		list.forEach(System.out::println);

		Assertions.assertEquals(1, list.size());

		/*
		var lista = prodRepo.findAll().stream()
				.filter(producto -> producto.getNombre().contains("Monitor")
						&& producto.getPrecio() < 215).toList();

		lista.forEach(System.out::println);

 */
	}

	/**
	 * 22. Lista el nombre y el precio de todos los productos que tengan un precio mayor o igual a 180€.
	 * Ordene el resultado en primer lugar por el precio (en orden descendente) y en segundo lugar por el nombre (en orden ascendente).
	 */
	@Test
	void test22() {
		var listProds = prodRepo.findAll();
		//TODO

		var lista = listProds.stream().filter(producto -> producto.getPrecio() >= 180)
				.sorted(comparing(Producto::getPrecio).reversed()
						.thenComparing(Producto::getNombre)).toList();

		lista.forEach(System.out::println);

		Assertions.assertEquals(7, lista.size());

		/*
		var lista = prodRepo.findAll().stream()
				.filter(producto -> producto.getPrecio()>= 180)
				.sorted(comparing(Producto::getPrecio, reverseOrder())
						.thenComparing(Producto::getNombre))
				.toList();

		lista.forEach(System.out::println);
*/
	}

	/**
	 * 23. Devuelve una lista con el nombre del producto, precio y nombre de fabricante de todos los productos de la base de datos.
	 * Ordene el resultado por el nombre del fabricante, por orden alfabético.
	 */
	@Test
	void test23() {
		var listProds = prodRepo.findAll();
		//TODO

		var lista = listProds.stream()
				.sorted(comparing(Producto -> Producto.getFabricante().getNombre()))
				.map(producto -> STR."\{producto.getNombre()} \{producto.getPrecio()} \{producto.getFabricante().getNombre()}").toList();

		lista.forEach(System.out::println);

		Assertions.assertEquals(11, lista.size());


		/*var listaOrdenada = listProds.stream()
				.map(producto -> producto)*/

	}

	/**
	 * 24. Devuelve el nombre del producto, su precio y el nombre de su fabricante, del producto más caro.
	 */
	@Test
	void test24() {
		var listProds = prodRepo.findAll();
		//TODO

		var lista = listProds.stream().sorted(comparing(Producto::getPrecio).reversed())
				.map(producto -> producto.getNombre() + producto.getPrecio() + producto.getFabricante().getNombre())
				.limit(1).toList();


		lista.forEach(System.out::println);

		Assertions.assertEquals(1, lista.size());
	}


	/**
	 * 25. Devuelve una lista de todos los productos del fabricante Crucial que tengan un precio mayor que 200€.
	 */
	@Test
	void test25() {
		var listProds = prodRepo.findAll();
		//TODO

		var result = listProds.stream()
				.filter(p -> p.getFabricante().getNombre()
						.equalsIgnoreCase("Crucial")
						&& p.getPrecio() >= 200)
				.toList();

		result.forEach(System.out::println);
		Assertions.assertEquals(1, result.size());

		/*
		var lista = listProds.stream()
				.filter(producto -> producto.getPrecio() >= 200 &&
						producto.getFabricante().getNombre().equalsIgnoreCase("Crucial")).toList();

		lista.forEach(System.out::println);*/
	}

	/**
	 * 26. Devuelve un listado con todos los productos de los fabricantes Asus, Hewlett-Packard y Seagate
	 */
	@Test
	void test26() {
		var listProds = prodRepo.findAll();
		//TODO

		Set<String> lista = Set.of("Asus", "Hewlett-Packard", "Seagate");

		var list = listProds.stream()
				.filter(producto -> lista.contains(producto.getFabricante().getNombre()))
				.toList();

		list.forEach(System.out::println);
		Assertions.assertEquals(5, list.size());

	}

	/**
	 * 27. Devuelve un listado con el nombre de producto, precio y nombre de fabricante, de todos los productos que tengan un precio mayor o igual a 180€.
	 * Ordene el resultado en primer lugar por el precio (en orden descendente) y en segundo lugar por el nombre.
	 * El listado debe mostrarse en formato tabla. Para ello, procesa las longitudes máximas de los diferentes campos a presentar y compensa mediante la inclusión de espacios en blanco.
	 * La salida debe quedar tabulada como sigue:
	 * <p>
	 * Producto                Precio             Fabricante
	 * -----------------------------------------------------
	 * GeForce GTX 1080 Xtreme|611.5500000000001 |Crucial
	 * Portátil Yoga 520      |452.79            |Lenovo
	 * Portátil Ideapd 320    |359.64000000000004|Lenovo
	 * Monitor 27 LED Full HD |199.25190000000003|Asus
	 */
	@Test
	void test27() {
		var listProds = prodRepo.findAll();
		//TODO

		var result = listProds.stream().
				sorted(comparing(Producto::getPrecio, reverseOrder()).thenComparing(Producto::getNombre))
				.filter(producto -> producto.getPrecio() >= 180)
				.map(producto -> "Nombre " + producto.getNombre() + " Precio : "
						+ producto.getPrecio() +
						"Fabricante " + producto.getFabricante().getNombre()).toList();

		result.forEach(System.out::println);
		Assertions.assertEquals(7, result.size());


/*
		System.out.println("Producto \t" + " Precio \t" + "Farbicante" );
		System.out.println(result.stream().);*/

	}

	/**
	 * 28. Devuelve un listado de los nombres fabricantes que existen en la base de datos, junto con los nombres productos que tiene cada uno de ellos.
	 * El listado deberá mostrar también aquellos fabricantes que no tienen productos asociados.
	 * SÓLO SE PUEDEN UTILIZAR STREAM, NO PUEDE HABER BUCLES
	 * La salida debe queda como sigue:
	 * Fabricante: Asus
	 * <p>
	 * Productos:
	 * Monitor 27 LED Full HD
	 * Monitor 24 LED Full HD
	 * <p>
	 * Fabricante: Lenovo
	 * <p>
	 * Productos:
	 * Portátil Ideapd 320
	 * Portátil Yoga 520
	 * <p>
	 * Fabricante: Hewlett-Packard
	 * <p>
	 * Productos:
	 * Impresora HP Deskjet 3720
	 * Impresora HP Laserjet Pro M26nw
	 * <p>
	 * Fabricante: Samsung
	 * <p>
	 * Productos:
	 * Disco SSD 1 TB
	 * <p>
	 * Fabricante: Seagate
	 * <p>
	 * Productos:
	 * Disco duro SATA3 1TB
	 * <p>
	 * Fabricante: Crucial
	 * <p>
	 * Productos:
	 * GeForce GTX 1080 Xtreme
	 * Memoria RAM DDR4 8GB
	 * <p>
	 * Fabricante: Gigabyte
	 * <p>
	 * Productos:
	 * GeForce GTX 1050Ti
	 * <p>
	 * Fabricante: Huawei
	 * <p>
	 * Productos:
	 * <p>
	 * <p>
	 * Fabricante: Xiaomi
	 * <p>
	 * Productos:
	 */
	@Test
	void test28() {
		var listFabs = fabRepo.findAll();
		//TODO

		var lista = listFabs.stream()
				.map(f -> f.getNombre() + f.getCodigo() +
						f.getProductos()
								.stream().map(p -> p.getNombre() + p.getPrecio())
								.collect(Collectors.joining()))
				.toList();

		lista.forEach(System.out::println);


		/*var result = listFabs.stream()*/
	}

	/**
	 * 29. Devuelve un listado donde sólo aparezcan aquellos fabricantes que no tienen ningún producto asociado.
	 */
	@Test
	void test29() {
		var listFabs = fabRepo.findAll();
		//TODO

		var result = listFabs.stream().filter(f -> f.getProductos().isEmpty()).toList();

		result.forEach(System.out::println);
		Assertions.assertEquals(2, result.size());
	}

	/**
	 * 30. Calcula el número total de productos que hay en la tabla productos. Utiliza la api de stream.
	 */
	@Test
	void test30() {
		var listProds = prodRepo.findAll();
		//TODO

		var result = listProds.stream().count();
		System.out.println("El resultado de producto es " + result);
		Assertions.assertEquals(11, listProds.size());
	}


	/**
	 * 31. Calcula el número de fabricantes con productos, utilizando un stream de Productos.
	 */
	@Test
	void test31() {
		var listProds = prodRepo.findAll();
		//TODO

		var list = listProds.stream()
				.map(producto -> producto.getFabricante())
				.distinct()
				.count();

		System.out.println(list);
		//Assertions.assertEquals(9, listProds.size());
	}

	/**
	 * 32. Calcula la media del precio de todos los productos
	 */
	@Test
	void test32() {
		var listProds = prodRepo.findAll();
		//TODO

		var list = listProds.stream()
				.mapToDouble(producto -> producto.getPrecio())
				.average();


		System.out.println(list);
		Assertions.assertEquals(271.7236363636364, list.orElse(0.0));
	}

	/**
	 * 33. Calcula el precio más barato de todos los productos. No se puede utilizar ordenación de stream.
	 */
	@Test
	void test33() {
		var listProds = prodRepo.findAll();
		//TODO

		var precio = listProds.stream()
				.mapToDouble((Producto::getPrecio))
				.min();


		System.out.println(precio);
		Assertions.assertEquals(59.99, precio.orElse(0.0));
	}

	/**
	 * 34. Calcula la suma de los precios de todos los productos.
	 */
	@Test
	void test34() {
		var listProds = prodRepo.findAll();
		//TODO

		var suma = listProds.stream()
				.mapToDouble(producto -> producto.getPrecio())
				.sum();

		System.out.println(suma);

		Assertions.assertEquals(2988.96, suma);

	}

	/**
	 * 35. Calcula el número de productos que tiene el fabricante Asus.
	 */
	@Test
	void test35() {
		var listProds = prodRepo.findAll();
		//TODO

		var num = listProds.stream()
				.filter(producto -> producto.getFabricante().getNombre().equals("Asus")).count();

		System.out.println(num);
		Assertions.assertEquals(2, num);

	}

	/**
	 * 36. Calcula la media del precio de todos los productos del fabricante Asus.
	 */
	@Test
	void test36() {
		var listProds = prodRepo.findAll();
		//TODO

		var media = listProds.stream()
				.filter(producto -> "Asus".equals(producto.getFabricante().getNombre()))
				.mapToDouble(producto -> producto.getPrecio())
				.average().orElse(0.0);


		System.out.println(media);

		Assertions.assertEquals(223.995, media);
	}


	/**
	 * 37. Muestra el precio máximo, precio mínimo, precio medio y el número total de productos que tiene el fabricante Crucial.
	 * Realízalo en 1 solo stream principal. Utiliza reduce con Double[] como "acumulador".
	 */
	@Test
	void test37() {
		var listProds = prodRepo.findAll();
		//TODO

		/*var lista = listProds.stream().filter(producto -> "Crucial".equals(producto.getFabricante().getNombre()))
				.mapToDouble(producto -> producto.getPrecio()).summaryStatistics();*/

		/*System.out.println(lista);*/

		var result = listProds.stream()
				.filter(producto -> producto.getFabricante()
						.getNombre().equalsIgnoreCase("Crucial"))
				.map(producto -> new Double[]{
						producto.getPrecio(), producto.getPrecio(), producto.getPrecio(), 0.0})
				.reduce((doubles, doubles2) -> new Double[]{
						Math.min(doubles[0], doubles2[0]),
						Math.max(doubles[0], doubles2[0]),
						doubles[2] + doubles2[2],
						doubles[3]++})
				.orElse(new Double[]{});

		double media = result[3] > 0 ? result[2] / result[3] : 0.0;
		System.out.println("Valor min " + result[0]);
		System.out.println("Valor max " + result[1]);
	}

	/**
	 * 38. Muestra el número total de productos que tiene cada uno de los fabricantes.
	 * El listado también debe incluir los fabricantes que no tienen ningún producto.
	 * El resultado mostrará dos columnas, una con el nombre del fabricante y otra con el número de productos que tiene.
	 * Ordene el resultado descendentemente por el número de productos. Utiliza String.format para la alineación de los nombres y las cantidades.
	 * La salida debe queda como sigue:

	 Fabricante     #Productos
	 -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	 Asus              2
	 Lenovo              2
	 Hewlett-Packard              2
	 Samsung              1
	 Seagate              1
	 Crucial              2
	 Gigabyte              1
	 Huawei              0
	 Xiaomi              0

	 */
	@Test
	void test38() {
		var listFabs = fabRepo.findAll();
		//TODO
	}

	/**
	 * 39. Muestra el precio máximo, precio mínimo y precio medio de los productos de cada uno de los fabricantes.
	 * El resultado mostrará el nombre del fabricante junto con los datos que se solicitan. Realízalo en 1 solo stream principal. Utiliza reduce con Double[] como "acumulador".
	 * Deben aparecer los fabricantes que no tienen productos.
	 */
	@Test
	void test39() {
		var listFabs = fabRepo.findAll();
		//TODO
/*

		listFabs.stream()
				.forEach();*/
	}

	/**
	 * 40. Muestra el precio máximo, precio mínimo, precio medio y el número total de productos de los fabricantes que tienen un precio medio superior a 200€.
	 * No es necesario mostrar el nombre del fabricante, con el código del fabricante es suficiente.
	 */
	@Test
	void test40() {
		var listFabs = fabRepo.findAll();
		//TODO

		var lista = listFabs.stream()
				.map(fabricante -> fabricante.getProductos().stream()
						.mapToDouble(Producto::getPrecio).average()).filter(n -> n.isPresent() && n.getAsDouble() > 200);


		lista.forEach(System.out::println);


	}

	/**
	 * 41. Devuelve un listado con los nombres de los fabricantes que tienen 2 o más productos.
	 */
	@Test
	void test41() {
		var listFabs = fabRepo.findAll();
		//TODO
		var result = listFabs.stream()
				.filter(f -> f.getProductos().size() >= 2)
				.map(f -> f.getNombre())
				.toList();

		result.forEach(System.out::println);
		Assertions.assertEquals(4, result.size());
	}


	/**
	 * 42. Devuelve un listado con los nombres de los fabricantes y el número de productos que tiene cada uno con un precio superior o igual a 220 €.
	 * Ordenado de mayor a menor número de productos.
	 */
	@Test
	void test42() {
		var listFabs = fabRepo.findAll();
		//TODO
/*
		listFabs.stream()
				.filter(f -> f.)

*/
	}

	/**
	 * 43.Devuelve un listado con los nombres de los fabricantes donde la suma del precio de todos sus productos es superior a 1000 €
	 */
	@Test
	void test43() {
		var listFabs = fabRepo.findAll();
		//TODO
			var result = listFabs.stream()
					.filter(f -> f.getProductos().stream().mapToDouble(p -> p.getPrecio()).sum() > 1000)
					.map(f ->f.getNombre())
					.toList();

			result.forEach(System.out::println);
			Assertions.assertEquals(1, result.size());
	}

	/**
	 * 44. Devuelve un listado con los nombres de los fabricantes donde la suma del precio de todos sus productos es superior a 1000 €
	 * Ordenado de menor a mayor por cuantía de precio de los productos.
	 */
	@Test
	void test44() {
		var listFabs = fabRepo.findAll();
		//TODO

		var result = listFabs.stream()
				.sorted(comparingDouble(f -> f.getProductos().stream().mapToDouble(p -> p.getPrecio()).sum()))
				.filter(f -> f.getProductos().stream().mapToDouble(p -> p.getPrecio()).sum() > 1000)
				.map(f -> f.getNombre())
				.toList();

		result.forEach(System.out::println);
		Assertions.assertEquals(1, result.size());
	}

	/**
	 * 45. Devuelve un listado con el nombre del producto más caro que tiene cada fabricante.
	 * El resultado debe tener tres columnas: nombre del producto, precio y nombre del fabricante.
	 * El resultado tiene que estar ordenado alfabéticamente de menor a mayor por el nombre del fabricante.
	 */
	@Test
	void test45() {
		var listFabs = fabRepo.findAll();
		//TODO
		System.out.println("Nombre Producto			Precio 			Fabricante");
		var result = listFabs.stream()
				.sorted(comparing(f -> f.getNombre()))
				.map(f -> f.getProductos().stream()
						.max(comparingDouble(p -> p.getPrecio()))
						.map(p -> p.getNombre() + "-" + p.getPrecio() + "-" + f.getNombre())
						.orElse(null))
				.toList();

		result.forEach(System.out::println);
		Assertions.assertTrue(result.isEmpty());
	}

	/**
	 * 46. Devuelve un listado de todos los productos que tienen un precio mayor o igual a la media de todos los productos de su mismo fabricante.
	 * Se ordenará por fabricante en orden alfabético ascendente y los productos de cada fabricante tendrán que estar ordenados por precio descendente.
	 */
	@Test
	void test46() {
		var listFabs = fabRepo.findAll();
		//TODO

		var result = listFabs.stream()
				.sorted(comparing(f -> f.getNombre()))
				.flatMap(f ->f.getProductos().stream()
						.filter(p ->p.getPrecio() >= f.getProductos().stream()
								.mapToDouble(pr -> pr.getPrecio()).average().orElse(0.0)
						)
						.sorted(comparing(p -> p.getPrecio(),reverseOrder()))
						.map(producto -> producto.getNombre() + "-" + producto.getPrecio() + "-" + f.getNombre()))
				.toList();

		result.forEach(System.out::println);
		Assertions.assertEquals(7, result.size());


	}

}



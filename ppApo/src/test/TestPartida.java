package test;

import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import excepciones.JugadorExistenteException;
import excepciones.JugadorNoEncontradoException;
import modelo.Jugador;
import modelo.Partida;

class TestPartida {

	/**
	 * partida : Partida - clase a la cual se le hara la prueva
	 * */
	private Partida partida;
	
	
	/**
	 * setupEscenario1(): void - incializa una nueva parida
	 * */
	void setupEscenario1() {
		partida = new Partida();
	}
	
	/**
	 * setupEscenario2(): void - crea una partida con 7 jugadores
	 * */
	void setupEscenario2() {
		partida = new Partida();
		
		try {
			partida.agregar("jugador15");
			partida.agregar("jugador6");
			partida.agregar("jugador10");
			partida.agregar("jugador4");
			partida.agregar("jugador20");
			partida.agregar("jugador17");
			partida.agregar("jugador22");
		
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	
	/**
	 *  setupEscenario3() : void - costruye un arbol de jugadores
	 *  con tres jugadores
	 * */
	void setupEscenario3() {
		setupEscenario1();
		
		try {
			partida.agregar("1");
			partida.agregar("0");
			partida.agregar("5");
		}catch (Exception e) {
			fail("la prueba fallo");
		}
		
	}
	
	
	/**
	 * setupEscenario4() : void - construye una partida con nueve jugadores 
	 * */
	void setupEscenario4() {
		
		partida = new Partida();
		try {
			partida.agregar("jugador1");
			partida.agregar("jugador3");
			partida.agregar("jugador5");
			partida.agregar("jugador6");
			partida.agregar("jugador9");
			partida.agregar("jugador7");
			partida.agregar("jugador8");
			partida.agregar("jugador0");
			partida.agregar("jugador4");
		
		} catch (JugadorExistenteException e) {
	
		} catch (JugadorNoEncontradoException e) {
			// TODO: handle exception
		}

	}
	
	/**
	 * setupEscenario6() : construye una partida con un solo jugador. 
	 * */
	void setupEscenario6() {
		setupEscenario1();
		try {
			partida.agregar("jugadorExistente");
		} catch (JugadorExistenteException e) {
			
		} catch(JugadorNoEncontradoException e) {
			
		}
	}
	
	/**
	 * Prueba la adición de jugadores. <br>
	 * <b> Métodos a probar: </b> 
	 * <br> agregar. <br>
	 * <b> Objetivo: </b>  <br>probar que el método agregar(),  agrega
	 * correctamente jugadores al ABB. </br>
	 * <b> Resultados esperados: </b> 
	 * <br> 1. Al agregar un jugador la partida sin jugadores este debe ser
	 * insertado sin errores. </br>
	 */
	@Test
	void testAgregar1() {
		try {
			setupEscenario1();
			assertNull(partida.getRaiz());
			partida.agregar("1");
			assertNotNull(partida.getRaiz());
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Prueba la adición de jugadores. <br>
	 * <b> Métodos a probar: </b> 
	 * <br> agregar. <br>
	 * <b> Objetivo: </b>  <br>probar que el método agregar(),  agrega
	 * correctamente jugadores al ABB. </br>
	 * <b> Resultados esperados: </b> 
	 * <br> Al agregar un jugador menor que la raiz este se agrega al subarbol 
	 * izquierdo sin errores. </br>
	 */
	@Test
	void testAgregar2() {
		setupEscenario1();
		try {
			partida.agregar("1");
			partida.agregar("0");	
			assertEquals("0", partida.getRaiz().getIzquierda().getNickname());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	/**
	 * Prueba la adición de jugadores. <br>
	 * <b> Métodos a probar: </b> 
	 * <br> agregar. <br>
	 * <b> Objetivo: </b>  <br>probar que el método agregar(),  agrega
	 * correctamente jugadores al ABB. </br>
	 * <b> Resultados esperados: </b> 
	 * <br>  Al agregar un jugador mayor que la raiz a la partida este 
	 * se agrega al subarbol derecho sin errores. </br>
	 */
	@Test
	void testAgregar3() {
		try {
			setupEscenario3();
			
			assertEquals("5", partida.getRaiz().getDerecha().getNickname());
			assertEquals("0", partida.getRaiz().getIzquierda().getNickname());
			assertEquals("1", partida.getRaiz().getNickname());
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	/**
	 * Prueba la adición de jugadores. <br>
	 * <b> Métodos a probar: </b> 
	 * <br> agregar. <br>
	 * <b> Objetivo: </b>  <br>probar que el método agregar(),  agrega
	 * correctamente jugadores al ABB. </br>
	 * <b> Resultados esperados: </b> 
	 * <br> el metodo agregar agrega a los jugadores segun el criterio de
	 * un ABB, los jugadores se agregan sin errores </br>
	 */
	@Test
	void testAgregar4() {
		setupEscenario4();
		assertEquals("jugador1", partida.getRaiz().getNickname());
		assertEquals("jugador0", partida.getRaiz().getIzquierda().getNickname());
		assertEquals("jugador3", partida.getRaiz().getDerecha().getNickname());
		assertEquals("jugador5", partida.getRaiz().getDerecha().getDerecha().getNickname());
		assertEquals("jugador4", partida.getRaiz().getDerecha().getDerecha().getIzquierda().getNickname());
		assertEquals("jugador6", partida.getRaiz().getDerecha().getDerecha().getDerecha().getNickname());
		assertEquals("jugador7", partida.getRaiz().getDerecha().getDerecha().getDerecha().getDerecha().getIzquierda().getNickname());
		assertEquals("jugador8", partida.getRaiz().getDerecha().getDerecha().getDerecha().getDerecha().getIzquierda().getDerecha().getNickname());
		assertEquals("jugador9", partida.getRaiz().getDerecha().getDerecha().getDerecha().getDerecha().getNickname());
		
	}
	
	/**
	 * Prueba la adición de jugadores. <br>
	 * <b> Métodos a probar: </b> 
	 * <br> agregar. <br>
	 * <b> Objetivo: </b> 
	 * <br> probar que el método agregar(), lanza una 
	 * excepcion llamada JugadorExistenteException y 
	 * solamente lanza esa. </br>
	 * <b> Resultados esperados: </b> 
	 * <br> el metodo agregar, lanza la exepcion esperada 
	 * el jugador no se agrega </br>
	 */
	@Test
	void testAgregar5() {
		setupEscenario6();
		try {
			partida.agregar("jugadorExistente");
		} catch (JugadorExistenteException e) {
			assertTrue(true, "si esta excecion es lanzada entonces la prueba fue exitosa");
		} catch (JugadorNoEncontradoException e) {
			fail("la excepcion no deberia llegar hasta aca");
		}
		
	}
	
	/**
	 * Prueba la existencia de jugadores en el arbol. <br>
	 * <b> Métodos a probar: </b> 
	 * <br> existe <br>
	 * <b> Objetivo: </b> 
	 * <br> probar que el método existe(), retorna: -true = si el jugador existe
	 * 												existe en el arbol
	 * 												-false = de lo contrario </br>
	 * <b> Resultados esperados: </b> 
	 * <br> 1. el jugador : "jugadorExiste", es encontrado en el arbol, el metodo retorna true
	 * 2. el jugador : "jugadorNoExiste", NO es encontrado en el arbol, el metodo retorna false </br>
	 */
	@Test
	void testExiste() {
		setupEscenario6();
		
		assertTrue(	partida.existe("jugadorExistente") , "este jugador ya ha sido agregado en el arbol");
		
		assertFalse( partida.existe("jugadorNoExiste"), "este jugador no esta en el arbol" );
	}
	
	/**
	 * Prueba la existencia de jugadores en el arbol. <br>
	 * <b> Métodos a probar: </b> 
	 * <br> existe <br>
	 * <b> Objetivo: </b> 
	 * <br> probar que el método existe(), retorna: -true = si el jugador existe
	 * 												existe en el arbol
	 * 												-false = de lo contrario </br>
	 * <b> Resultados esperados: </b> 
	 * <br> los jugadores : "jugador9", "jugador3", "jugador5", "jugador6", son 
	 * encontrados en el arbol, el metodo retorna true en cada caso. </br>
	 */
	@Test
	void testExiste2() {
		
		setupEscenario4();
		assertTrue( partida.existe("jugador9") );
		assertTrue( partida.existe("jugador3") );
		assertTrue( partida.existe("jugador5") );
		assertTrue( partida.existe("jugador6") );
		
	}
	
	
	@Test
	void inOnorden() {
		
	try {
		setupEscenario1();
		
		partida.agregar("jugador15");
		partida.agregar("jugador6");
		partida.agregar("jugador10");
		partida.agregar("jugador4");
		partida.agregar("jugador20");
		partida.agregar("jugador17");
		partida.agregar("jugador22");
	
		partida.inOrden(partida.getRaiz());
		
		assertEquals( partida.getRaiz().getIzquierda() ,  partida.getMejoresPuntajes().get(0));
		assertEquals( partida.getRaiz().getIzquierda().getIzquierda() ,  partida.getMejoresPuntajes().get(1));
		assertEquals( partida.getRaiz().darMayor() ,  partida.getMejoresPuntajes().get(2));
		assertEquals( partida.getRaiz().getDerecha() ,  partida.getMejoresPuntajes().get(3));
		assertEquals( partida.getRaiz().getDerecha().getIzquierda() ,  partida.getMejoresPuntajes().get(4));
		assertEquals( partida.getRaiz().getIzquierda() ,  partida.getMejoresPuntajes().get(5));
		
		
		
		for (int i = 0; i < partida.getMejoresPuntajes().size(); i++) {
			System.out.println(partida.getMejoresPuntajes().get(i));
		}
		
		
		
		
	} catch (JugadorExistenteException e ) {
		fail("");
	} catch (JugadorNoEncontradoException e) {
		fail("");
		System.out.println("hh");
	}
			
		
	}
	

	
}

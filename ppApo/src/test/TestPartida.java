package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import excepciones.JugadorExistenteException;
import excepciones.JugadorNoEncontradoException;
import modelo.Jugador;
import modelo.Partida;

class TestPartida {

	private Partida partida;
	
	void setupEscenario1() {
		partida = new Partida();
	}
	
	void setupEscenario2() {
		setupEscenario1();
		try {
			partida.agregar("jugador1");
			partida.agregar("jugador0");	
		}catch (Exception e) {
			fail("la prueba fallo");
		}
	}
	
	void setupEscenario3() {
		setupEscenario1();
		
		try {
			partida.agregar("jugador1");
			partida.agregar("jugador0");
			partida.agregar("jugador5");
		}catch (Exception e) {
			fail("la prueba fallo");
		}
		
	}
	
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
		
		} catch (Exception e) {
			fail("la prueba fallo");
		}

	}
	
	void setupEscenerio5() {
		setupEscenario1();
		setupEscenario4();

	}

	void setupEscenario6() {
		setupEscenario1();
		try {
			partida.agregar("jugadorExistente");
		} catch (Exception e) {
			fail("esto no deberia pasar");
		}
	}
	
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

	@Test
	void testSAgregar2() {
		setupEscenario1();
		try {
			partida.agregar("1");
			partida.agregar("0");	
			assertEquals("0", partida.getRaiz().getIzquierda().getNickname());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	@Test
	void testAgregar3() {
		try {
			setupEscenario1();
			partida.agregar("1");
			partida.agregar("0");
			partida.agregar("5");
			
			assertEquals("5", partida.getRaiz().getDerecha().getNickname());
			assertEquals("0", partida.getRaiz().getIzquierda().getNickname());
			assertEquals("1", partida.getRaiz().getNickname());
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	@Test
	void testAgregar5() {
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
	
	@Test
	void testAghregar6() {
		setupEscenario6();
		try {
			partida.agregar("jugadorExistente");
		} catch (Exception e) {
			// si esta asserci�n se cumple entonces la prueba fue exitosa.
			assertTrue(true);
		}
		
	}
	
	@Test
	void testExiste() {
		setupEscenario6();
		assertTrue(	partida.existe("jugadorExistente"));
	}
	
	@Test
	void testExiste2() {
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
		
		} catch (Exception e) {
			fail("la prueba fallo");
		}
	
		assertTrue( partida.existe("jugador0") );
		
	}
	
	@Test
	void testInOrden() {
		partida = new Partida();
		try {
			partida.agregar("jugador9");
			partida.agregar("jugador8");
			partida.agregar("jugador15");
			partida.agregar("jugador7");
			partida.agregar("jugador13");
			partida.agregar("jugador12");
			partida.agregar("jugador16");
			partida.agregar("jugador18");
			partida.agregar("jugador6");
			
			
			ArrayList<Jugador> arreglo = partida.inOrden(partida.getRaiz()) ; 
			
			for (int i = 0; i < arreglo.size()-1 ; i++) {
				System.out.println(arreglo.get(i).getNickname());	
			}
			
		} catch (JugadorNoEncontradoException e) {
			fail("la prueba fallo");
		} catch( JugadorExistenteException e) {
			fail("la prueba fallo");
		}
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

//	@Test
//	void testInOrden() {
//		
//		setupEscenario1();
//		try {
//			partida.agregar("jugador1");
//			partida.agregar("jugador3");
//			partida.agregar("jugador5");
//			partida.agregar("jugador6");
//			partida.agregar("jugador9");
//			partida.agregar("jugador7");
//			partida.agregar("jugador8");
//			partida.agregar("jugador0");
//			partida.agregar("jugador4");
//			
//			partida.getRaiz();
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//		}	
//	}
	
	
	
	
	
}

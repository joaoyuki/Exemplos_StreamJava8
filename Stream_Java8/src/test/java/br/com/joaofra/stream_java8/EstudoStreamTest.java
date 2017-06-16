/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.joaofra.stream_java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author joaoassis
 */
public class EstudoStreamTest {
    

	/**
	 * Cálculo da soma de valores inteiros pares antes do Java 5.
	 */
	@Test
	public void somaIteratorJava4() {
		Iterator it = Arrays.asList(1, 2, 3).iterator();
		int soma = 0;
		while (it.hasNext()) {
			int num = (int) it.next();
			if (num % 2 == 0) {
				soma += num;
			}
		}

		assertEquals(2, soma);

	}
	
	/**
	 * Cálculo da soma de valores inteiros pares utilizando o laço for melhorado do Java 5.
	 */
	@Test
	public void somaIteratorJava5() {
	       int soma = 0;
	       for (int num : Arrays.asList(1, 2, 3)) {
	           if (num % 2 == 0) {
	               soma += num;
	           }
	       }
	       assertEquals(2, soma);
	  }
	
	@Test
	public void criarStreamDeString(){
		
		List<String> items = new ArrayList<String>();
		items.add("Um");
		items.add("Dois");
		items.add("Três");
		Stream<String> stream  = items.stream();
		stream.forEach(System.out::println);
		
	}
	
	@Test
	public void criarStreamDeMap(){
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("key1", "Abacate");
		map.put("key2", "Melancia");
		map.put("key3", "Manga");
		Stream<String> stream = map.values().stream();
		stream.forEach(System.out::println);
		
	}
	

	@Test
	public void criarStreamArray(){
		
		IntStream numbersFromArray = Arrays.stream(new int[] {1,2,3,4,5,6});
		numbersFromArray.forEach(System.out::println);
		
	}
	
	@Test
	public void criarStreamOf(){
		
		Stream<Integer> numbersFromValues = Stream.of(1,2,3,4,5,6,7,8,9);
		numbersFromValues.forEach(System.out::println);
		
	}

    
}

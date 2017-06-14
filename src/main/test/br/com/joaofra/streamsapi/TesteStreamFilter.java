package br.com.joaofra.streamsapi;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import main.Pessoa;

import org.junit.Test;


public class TesteStreamFilter {
	
	@Test
	public void ordenaNacionalidadeBrasil(){
		
		List<Pessoa> pessoas = new Pessoa().populaPessoas();
		pessoas.forEach(System.out::println);
		System.out.println("--**--");
		Stream<Pessoa> stream = pessoas.stream()
				.filter(pessoa -> pessoa.getNacionalidade().equals("Brasil"));
		stream.forEach(System.out::println);
		
		pessoas.stream()//Faz a mesma coisa que o código a cima
		.filter(pessoa -> pessoa.getNacionalidade().equals("Brasil"))
		.forEach(System.out::println);
		
	}

}

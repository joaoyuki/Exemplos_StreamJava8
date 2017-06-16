/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.joaofra.stream_java8;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import main.Pessoa;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author joaoassis
 */
public class TesteStreamFilter {
    
    public static Predicate<Pessoa> naoEBRasileiroPredicateStatico(){
       return  t -> !t.getNacionalidade().equals("Brasil");
    }
    
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
        
        @Test
        public void retornaIdadeMap(){
            
            List<Pessoa> pessoas = new Pessoa().populaPessoas();
            
            //Utlizando o IntStream evitamos o boxing interno do java
            IntStream stream = pessoas.stream()
		.filter(pessoa -> pessoa.getNacionalidade().equals("Brasil"))
                 .mapToInt(pessoa -> pessoa.getIdade());
            
            //Os dois códigos fazem a mesma coisa
            
            pessoas.stream()
		.filter(pessoa -> pessoa.getNacionalidade().equals("Brasil"))
                 .map(pessoa -> pessoa.getIdade())
		.forEach(System.out::println);
            
        }
        
        @Test
        public void ordenaStreamSorted(){
            List<Pessoa> pessoas = new Pessoa().populaPessoas();
            
            Stream<Pessoa> stream = pessoas.stream()
                    .filter(pessoa -> pessoa.getNacionalidade().equals("Brasil"))
                    .sorted(Comparator.comparing(Pessoa::getIdade));
            Optional<Pessoa> pessoa = stream.findFirst();
            
            Assert.assertEquals("Neymar Junior", pessoa.get().toString());

           
            
        }
        
        
        @Test
        public void limitaResultado(){
            
            List<Pessoa> pessoas = new Pessoa().populaPessoas();
            
            Stream<Pessoa> stream = pessoas.stream()
                    .filter(pessoa -> pessoa.getNacionalidade().equals("Brasil"))
                    .limit(1);
            
            Assert.assertEquals(1, stream.count());
            
        }
        
        @Test
        public void calculaIdadeMedia(){
            
            List<Pessoa> pessoas = new Pessoa().populaPessoas();
            
            Double idadeMedia = pessoas.stream()
                    .filter(pessoa -> pessoa.getNacionalidade().equals("Brasil"))
                    .mapToInt(pessoa -> pessoa.getIdade())
                    .average().getAsDouble();
            
            Assert.assertEquals(17,0, idadeMedia);
            
        }
        
        @Test
        public void retornaListaBrasileiros(){
            
            List<Pessoa> pessoas = new Pessoa().populaPessoas();
            
            List<Pessoa> brasileiros = pessoas.stream().filter(pessoa -> pessoa.getNacionalidade().equals("Brasil")).collect(Collectors.toList());
            List<Pessoa> naoEBrasileiro = brasileiros.stream().filter(pessoa -> !pessoa.getNacionalidade().equals("Brasil")).collect(Collectors.toList());
            
            Assert.assertEquals(2, brasileiros.size());
            Assert.assertEquals(0, naoEBrasileiro.size());
            
        }
        
        @Test
        public void retornaListaBrasileirosComPredicate(){
            Predicate<Pessoa> naoEBrasileiroPredicate = t -> !t.getNacionalidade().equals("Brasil");
            
            List<Pessoa> pessoas = new Pessoa().populaPessoas();
            
            List<Pessoa> brasileiros = pessoas.stream().filter(pessoa -> pessoa.getNacionalidade().equals("Brasil")).collect(Collectors.toList());
            List<Pessoa> naoEBrasileiro = brasileiros.stream().filter(naoEBrasileiroPredicate).collect(Collectors.toList());
            
            Assert.assertEquals(2, brasileiros.size());
            Assert.assertEquals(0, naoEBrasileiro.size());
            
        }   
        
        @Test
        public void retornaListaBrasileirosComPredicateStatico(){
            Predicate<Pessoa> naoEBrasileiroPredicate = t -> !t.getNacionalidade().equals("Brasil");
            
            List<Pessoa> pessoas = new Pessoa().populaPessoas();
            
            List<Pessoa> brasileiros = pessoas.stream().filter(pessoa -> pessoa.getNacionalidade().equals("Brasil")).collect(Collectors.toList());
            List<Pessoa> naoEBrasileiro = brasileiros.stream().filter(naoEBRasileiroPredicateStatico()).collect(Collectors.toList());
            
            Stream<Pessoa> conta = pessoas.stream().filter(naoEBRasileiroPredicateStatico());//Ver o que porquê não retorna 0 mas retorna 2
            //long contaLong = pessoas.stream().filter(naoEBRasileiroPredicateStatico()).count();
            
            Assert.assertEquals(2, brasileiros.size());
            Assert.assertEquals(0, naoEBrasileiro.size());
            //Assert.assertEquals(0, contaLong);
            
        }   
        
        @Test
        public void validaListaBrasileirosComPredicateStatico(){
            Predicate<Pessoa> naoEBrasileiroPredicate = t -> !t.getNacionalidade().equals("Brasil");
            
            List<Pessoa> pessoas = new Pessoa().populaPessoas();
            
            boolean isBrasileiros = pessoas.stream().allMatch(pessoa -> pessoa.getNacionalidade().equals("Brasil"));
            
            
            Assert.assertFalse(isBrasileiros);
            
        }        
    
}

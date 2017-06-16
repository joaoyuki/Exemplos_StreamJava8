/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.joaofra.stream_java8;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 *
 * @author joaoassis
 */
public class EstudoStream {
    
    public static void main(String []args){
        
        
            List<main.Pessoa> pessoas = new main.Pessoa().populaPessoas();
            
            Stream<main.Pessoa> stream = pessoas.stream()
                    .filter(pessoa -> pessoa.getNacionalidade().equals("Brasil"))
                    .sorted(Comparator.comparing(main.Pessoa::getIdade));
            Optional<main.Pessoa> pessoa = stream.findFirst();
            
            System.out.println(pessoa.toString());
        
    }
    
}

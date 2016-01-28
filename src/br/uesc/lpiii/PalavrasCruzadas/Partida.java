/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uesc.lpiii.PalavrasCruzadas;
import java.io.File;
import java.util.Random;


/**
 *
 * @author gabriel
 */
public class Partida 
{
    LeitorDicionario leitor;
    
    File dicionario;
    
    
    int tamanhoDicionario;
 
    int quantidadePalavras;
    
    Jogador jogador1;
    Jogador jogador2;
    
    String[] palavra;
    String[][] bancoPalavras;
    
    public Partida(Jogador jogador1, Jogador jogador2)
    {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;        
    }
    
    public void geraPalavras()
    {
        Random gerador = new Random(); //Gerador de número aleatorio
        leitor = new LeitorDicionario(); //Leitor do dicionario
        dicionario = new File("extras/dicionarioPalCruz.txt"); //Arquivo do dicionario
        tamanhoDicionario = leitor.QuantidadeLinhas(dicionario); //Leitura da quantidade de linhas para leitura aleatoria
        quantidadePalavras = 0; // Inicialização
        while(quantidadePalavras < 5) //Gera número aleatorio entre 5 e 15
            quantidadePalavras = gerador.nextInt(15) + 1;
        
        //Cada palavra vem armazenada em um vetor de 2 posições, onde em 0 fica a dica e em 1 a palavra
        bancoPalavras = new String[quantidadePalavras][2]; //Alocação da quantidade sorteada de palavras
        
        for(int i = 0; i < quantidadePalavras; i++) //Leitura aleatoria da quantidade necessaria de palavras
        {
            palavra = leitor.LeituraAleatoria(dicionario, tamanhoDicionario);
            bancoPalavras[i][0] = palavra[0];
            bancoPalavras[i][1] = palavra[1].toLowerCase();
        }
        
        for(int i = 0; i < quantidadePalavras; i++)
            System.out.println("Palavra " + (i+1) + ": " + bancoPalavras[i][1]);
    }
}

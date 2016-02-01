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
    Dicionario leitor;
    
    File dicionario;
    
    
    int tamanhoDicionario;
 
    int quantidadePalavras;
    
    Jogador jogador1;
    Jogador jogador2;
    
    String[] palavra;
    String[][] bancoPalavras;
    
    UI_Partida guiJogo;
    
    int[] posNasPalavras;
    
    int[] posNoPivout;
        
    public Partida(Jogador jogador1, Jogador jogador2)
    {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        geraPalavras();
    }
    
    private void geraPalavras()
    {
        leitor = new Dicionario(); //Leitor do dicionario
        dicionario = new File("extras/dicionarioPalCruz.txt"); //Arquivo do dicionario
        tamanhoDicionario = leitor.QuantidadeLinhas(dicionario); //Leitura da quantidade de linhas para leitura aleatoria
        quantidadePalavras = 0; // Inicialização
        while(quantidadePalavras < 5) //Gera a primeira palavra que será na vertical, precisa ter mais que 5 letras
        {    
            palavra = leitor.LeituraAleatoria(dicionario, tamanhoDicionario);
            quantidadePalavras = palavra[1].length() + 1;
        }
        
        //Cada palavra vem armazenada em um vetor de 2 posições, onde em 0 fica a dica e em 1 a palavra
        bancoPalavras = new String[quantidadePalavras][2]; //Alocação da quantidade sorteada de palavras
        
        bancoPalavras[0][0] = palavra[0];
        bancoPalavras[0][1] = palavra[1].toLowerCase();
        System.out.println("Palavra " + (1) + ": " + bancoPalavras[0][1]);
        boolean repetida = false;
        posNoPivout = new int[quantidadePalavras];
        posNasPalavras = new int[quantidadePalavras];
        posNasPalavras[0] = posNoPivout[0] = 0; //Não existe a palavra zero, pois é o pivout
        int i = 1;
        while(i < quantidadePalavras)
        {
            palavra = leitor.LeituraAleatoria(dicionario, tamanhoDicionario);
            palavra[1] = palavra[1].toLowerCase();
            //Vou andar a palavra lida e procurar a primeira letra do pivout em qualquer posição da palavra nova
            for(int j = 0; j < palavra[1].length(); j++)
            {
                for(int w = 0; w < i; w++)
                {
                    if(palavra[1].equals(bancoPalavras[w][1]) || palavra[1].equals(bancoPalavras[0][1]))
                        repetida = true;
                }
                if(palavra[1].substring(j, j + 1).equals(bancoPalavras[0][1].substring(i - 1, i)) && !repetida)
                {
                    bancoPalavras[i][0] = palavra[0];
                    bancoPalavras[i][1] = palavra[1].toLowerCase();
                    System.out.println("Palavra: " + palavra[1]);
                    posNasPalavras[i] = j;
                    posNoPivout[i] = i - 1;
                    i++;
                    repetida = false;
                    break;
                }
                repetida = false;
                    
            }
        }
        
        guiJogo = new UI_Partida(bancoPalavras, quantidadePalavras, posNasPalavras, posNoPivout, jogador1, jogador2);
    }
}

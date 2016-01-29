/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uesc.lpiii.PalavrasCruzadas;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
/**
 *
 * @author gabriel
 */
public class Dicionario 
{
    /*
    */
    public String[] LeituraAleatoria(File dicionario, int tamanhoDicionario)
    {
        String linha = ""; 
        String [] dicaPalavra = null;
        try 
        {
            Random gerador = new Random();
            FileReader leitor = new FileReader(dicionario);
            BufferedReader entrada = new BufferedReader(leitor);
            int linhaAleatoria = gerador.nextInt(tamanhoDicionario);
            for(int i = 0; i < linhaAleatoria; i++)
                linha = entrada.readLine();
            linha = entrada.readLine();
            dicaPalavra = linha.split(Pattern.quote(": "));
            entrada.close();
        } 
        catch (IOException ioe) 
        {
            System.out.println(ioe);
        }
        
        return dicaPalavra;
    }
    
    
    public int QuantidadeLinhas(File dicionario)
    {
        LineNumberReader linhaLeitura = null;
        try {
            linhaLeitura = new LineNumberReader(new FileReader(dicionario));
            linhaLeitura.skip(dicionario.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Dicionario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Dicionario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return linhaLeitura.getLineNumber();
    }
}

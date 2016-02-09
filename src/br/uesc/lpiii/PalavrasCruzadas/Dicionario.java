package br.uesc.lpiii.PalavrasCruzadas;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.net.URISyntaxException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
/**Classe responsavel pela leitura e adição de palavras do dicionario.
 *
 * @author gabriel
 */

public class Dicionario 
{
   
    int tamanhoDicionario;
    
    File dicionario;
    
    public Dicionario()
    {   
        try {
            String caminho = this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            caminho = caminho.substring(1, caminho.lastIndexOf('/') + 1);
            caminho =  "/"+ caminho + "extras/dicionarioPalCruz.txt";
            dicionario = new File(caminho);
	} catch (URISyntaxException e) {
            e.printStackTrace();
        }
        QuantidadeLinhas();
    }
    /**Método onde é feito uma leitura de uma palavra aleatoria do dicionario
     *  e é armazenada em um vetor de 2 posições, onde em 0 é a dica e em 1 é a palavra
     * @return dicaPalavra[]
     */
    public String[] LeituraAleatoria()
    {
        String linha; 
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
    
    /**Método que vê a quantidade de linhas(palavras) do dicionario.*/
    private void QuantidadeLinhas()
    {
        LineNumberReader linhaLeitura = null;
        try 
        {
            linhaLeitura = new LineNumberReader(new FileReader(dicionario));
            linhaLeitura.skip(dicionario.length());
        } 
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(Dicionario.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Dicionario.class.getName()).log(Level.SEVERE, null, ex);
        }
        tamanhoDicionario = linhaLeitura.getLineNumber();
    }
    /**Método responsavel por adicionar uma palavra nova no dicionario.
     * Recebe a dica e palavra e adiciona na ultima linha do mesmo.
     * @param dica
     * @param palavra*/
    public void AdicionaPalavra(String dica, String palavra)
    {
        
        try 
        {
            BufferedWriter bw = new BufferedWriter(new FileWriter(dicionario, true));
            bw.write(dica + ": " + palavra + "\n");
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(Dicionario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}

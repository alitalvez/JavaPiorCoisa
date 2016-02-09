package br.uesc.lpiii.PalavrasCruzadas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**Classe responsavel por lidar com o arquivo texto do placar de lideres.
 *
 * @author gabriel
 */
public class Placar 
{   
    /**Método responsavel por adicionar um novo Lider ao arquivo
     * @param lider*/
    String caminho;
    public Placar()
    {
        try {
            caminho = this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            caminho = caminho.substring(1, caminho.lastIndexOf('/') + 1);
            caminho =  "/"+ caminho + "extras/placarLideres.txt";
	} catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    
    public void adicionarLider(Jogador lider)
    {
        try 
        {
            BufferedWriter bw = new BufferedWriter(new FileWriter(caminho, true));
            bw.write(lider.getNome() + ": " + lider.getPontos() + "\n");
            bw.close();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Placar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**Método responsavel por retornar a quantidade de linhas do arquivo(Quantidade de lideres).
     @return linhaLeitura.getLineNumber().*/
    private int QuantidadeLinhas()
    {
        LineNumberReader linhaLeitura = null;
        try 
        {
            linhaLeitura = new LineNumberReader(new FileReader(caminho));
            linhaLeitura.skip(new File(caminho).length());
        } 
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(Dicionario.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Dicionario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return linhaLeitura.getLineNumber();
    }
    
    /**Método responsavel por ler e retornar um vetor com todos os lideres já registrados.
     * @return lideres. 
     */
    public Jogador[] getLideres()
    {
        int quantidadeLinhas = QuantidadeLinhas();
        Jogador[] lideres = null;
        if(quantidadeLinhas > 0)
        {
            lideres = new Jogador[quantidadeLinhas];
            String[] lider = new String[2];
            try 
            {
                BufferedReader br = new BufferedReader(new FileReader(caminho));            
                for(int i = 0; i < quantidadeLinhas; i++)
                {   
                    try 
                    {
                        lider = br.readLine().split(": ");
                    } 
                    catch (IOException ex) 
                    {
                        Logger.getLogger(Placar.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    lideres[i] = new Jogador(lider[0], Integer.parseInt(lider[1]));
                }
            } 
            catch (FileNotFoundException ex) 
            {
                Logger.getLogger(Placar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lideres;
    }
}

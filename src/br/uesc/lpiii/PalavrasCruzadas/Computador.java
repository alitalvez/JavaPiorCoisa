/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uesc.lpiii.PalavrasCruzadas;

/**
 *
 * @author gabriel
 */
public class Computador extends Jogador
{
    
    
    String[][] bancoDicas;
    
    private int bdPos;
    private String nome;
    
    public Computador(int pontos)
    {
        super("Computador", pontos);
        bdPos = 5;
        bancoDicas = new String[186][2];
        //A IA vem com 5 palavras quaisquer já no banco
        bancoDicas[0][0] = "Rugoso";
        bancoDicas[0][1] = "rofo";
        bancoDicas[1][0] = "pequeno automóvel, com embreagem automática, sem carroceria, nem caixas de mudanças, nem suspensão";
        bancoDicas[1][1] = "kart";
        bancoDicas[2][0] = "ritmo de dança originário da América Latina";
        bancoDicas[2][1] = "bamba";
        bancoDicas[3][0] = "desconfiança, temor ou antipatia por pessoas estranhas ao meio daquele que as ajuíza, ou pelo que é incomum ou vem de fora do país";
        bancoDicas[3][1] = "xenofobia";
        bancoDicas[4][0] = "soldado turco ou egípcio, filho de cristãos, subtraído em criança a seus pais, doutrinado no maometismo e adestrado para a guerra";
        bancoDicas[4][1] = "rume";
    }
    
    @Override
    public void addNoBanco(String dica, String palavra)
    {
        bancoDicas[bdPos][0] = dica;
        bancoDicas[bdPos][1] = palavra;
        bdPos++;
    }
    
    @Override
    public String consultaBanco(String dica)
    {
        String palavra = null;
        for(int i = 0; i < bdPos; i++)
        {
            if(bancoDicas[i][0].equals(dica))
            {
                palavra = bancoDicas[i][1];
                break;
            }
        }
        
        return palavra;
    }
    
    public void esqueceBanco()
    {
        for(int i = 5; i < bdPos; i++)
        {
            for(int j = 0; j < 2; j++)
                bancoDicas[i - 5][j] = bancoDicas[i][j];
        }
        bdPos -= 5;
        
    }
}

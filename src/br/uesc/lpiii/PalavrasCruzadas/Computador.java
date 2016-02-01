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
    private int pontos;
    
    public Computador(int pontos)
    {
        super("Computador", pontos);
        this.pontos = pontos;
    }
    
    public void addPontos()
    {
        pontos++;
    }
    
    public int getPontos()
    {
        return pontos;
    }
    
    public void setPontos(int pontos)
    {
        this.pontos = pontos;
    }
}

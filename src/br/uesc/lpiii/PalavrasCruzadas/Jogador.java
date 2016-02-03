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
public class Jogador 
{
    private final String nome;
    private int pontos;
    
    public Jogador(String nome, int pontos)
    {
        this.nome = nome;
        this.pontos = pontos;
    }
    
    public String getNome()
    {
        return nome;
    }
    
    public int getPontos()
    {
        return pontos;
    }
    
    public void addPontos()
    {
        pontos++;
    }
    
    public String consultaBanco(String dicas)
    {
        return null;
    }
    
    public void addNoBanco(String dica, String palavra)
    {
        
    }
    
    public void esqueceBanco()
    {
        
    }
}

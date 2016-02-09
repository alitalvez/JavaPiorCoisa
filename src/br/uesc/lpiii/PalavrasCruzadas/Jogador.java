package br.uesc.lpiii.PalavrasCruzadas;

/**Classe responsavel pela criação do tipo Jogador.
 * Aqui é guardado o nome do jogador e a sua pontuação.
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
    /**Os métodos a seguir só existem de verdade na classe Computador que é filha desta classe*/
    public String consultaBanco(String dicas)
    {
        return null;
    }
    public void addNoBanco(String dica, String palavra){}
    public void esqueceBanco(){}
}

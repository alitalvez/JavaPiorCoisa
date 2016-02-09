package br.uesc.lpiii.PalavrasCruzadas;

/**Classe responsavel por criar o tipo Computador que herda o tipo Jogador.
 *
 * @author gabriel
 */
public class Computador extends Jogador
{
    
    
    private String[][] bancoDicas; /**Matriz com o banco de dicas e palavras aprendidadas pelo Computador*/
    
    private int bdPos; /**Posição atual no banco de dicas*/
    
    public Computador(int pontos)
    {
        super("Computador", pontos);
        Dicionario leitor = new Dicionario();
        bdPos = 15;
        bancoDicas = new String[186][2];
        //A IA vem com 15 palavras quaisquer já no banco
        for(int i = 0; i < 15; i++)
            bancoDicas[i] = leitor.LeituraAleatoria();
    }
    
    
    /**Método responsavel por adicionar nova palavra ao banco de dados do Computador.
     *@param dica
     *@param palavra */
    @Override
    public void addNoBanco(String dica, String palavra)
    {
        if(bdPos == 186) /**Se banco estiver cheio, esquece 5 palavras para adicionar novas*/
            esqueceBanco();
        else
        {
            /**Se a palavra a ser adicionada já não existir no banco ela é adicionada*/
            if(consultaBanco(dica) != null)
            {
                bancoDicas[bdPos][0] = dica;
                bancoDicas[bdPos][1] = palavra;
                bdPos++;
            }
        }
    }
    
    /**Método responsavel por buscar e retornar uma palavra no banco a partir de uma dica
     * @param dica.
     * @return palavra.*/
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
    
    /**Método responsavel por fazer o computador esquecer 5 palavras a cada 5 jogos*/
    @Override
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

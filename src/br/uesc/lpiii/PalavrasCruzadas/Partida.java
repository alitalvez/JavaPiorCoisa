package br.uesc.lpiii.PalavrasCruzadas;


/** Classe responsavel por gerar a partida.
 * Nela é instanciado a UI da partida e passado os jogadores, juntamente as palavras do jogo que são geradas aqui.
 *
 * @author gabriel
 */
public class Partida 
{
    Dicionario leitor; /**Objeto do tipo Dicionario que vai ser utilizado para leitura das palavras e dicas*/
 
    int quantidadePalavras; /**Quantidade de palavras na partida*/
    
    Jogador jogador1;
    Jogador jogador2;
    
    String[] palavra; /**Vetor temporario utilizado para ler palavra e dica do dicionario e passao para o banco*/
    String[][] bancoPalavras; /**Matriz contendo as palavras e as dicas da partida*/
    

    int[] posNasPalavras; /**Vetor com posição da letra da palavra de acordo com o pivou, usado para montar as palavras cruzadas*/
    
    int[] posNoPivout; /**Vetor com posição da letra no pivout usada na palavra*/
    
    int numeroPartidas;
    
    /**
     * Construtor da classe, onde é chamado o método de gerar as palavras para o jogo
     * @param jogador1
     * @param jogador2
     * @param numeroPartidas Numero de partidas jogadas
     */
    public Partida(Jogador jogador1, Jogador jogador2, int numeroPartidas)
    {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        this.numeroPartidas = numeroPartidas;
        geraPalavras();
    }
    
    private void geraPalavras()
    {
        /**Neste método é lido do dicionario uma palavra com quantidade de letras maior que 5.
           A partir dessa primeira palavra lida(pivout) é lido mais outras palavras que contenham,
            letras que encaixem nesse pivout, pegando assim na palavra e no pivout as posições de
            encaixe.*/
        
        leitor = new Dicionario(); //Leitor do dicionario
        quantidadePalavras = 0; // Inicialização
        while(quantidadePalavras < 5) //Gera a primeira palavra que será na vertical, precisa ter mais que 5 letras
        {    
            palavra = leitor.LeituraAleatoria();
            quantidadePalavras = palavra[1].length() + 1;
        }
        //Cada palavra vem armazenada em um vetor de 2 posições, onde em 0 fica a dica e em 1 a palavra
        bancoPalavras = new String[quantidadePalavras][2]; //Alocação da quantidade sorteada de palavras
        
        bancoPalavras[0][0] = palavra[0];
        bancoPalavras[0][1] = palavra[1].toLowerCase();
        boolean repetida = false;
        posNoPivout = new int[quantidadePalavras];
        posNasPalavras = new int[quantidadePalavras];
        posNasPalavras[0] = posNoPivout[0] = 0; //Não existe a palavra zero, pois é o pivout
        int i = 1;
        while(i < quantidadePalavras)
        {
            palavra = leitor.LeituraAleatoria();
            palavra[1] = palavra[1].toLowerCase();
            //Percorre a palavra lida e procura a 'J' letra do pivout em qualquer posição da palavra nova
            for(int j = 0; j < palavra[1].length(); j++)
            {
                for(int w = 0; w < i; w++)
                { /**Verifica se a palavra lida não já foi lida antes e se não é a mesma do pivout*/
                    if(palavra[1].equals(bancoPalavras[w][1]) || palavra[1].equals(bancoPalavras[0][1]))
                        repetida = true;
                }
                if(palavra[1].substring(j, j + 1).equals(bancoPalavras[0][1].substring(i - 1, i)) && !repetida)
                {
                    bancoPalavras[i][0] = palavra[0];
                    bancoPalavras[i][1] = palavra[1].toLowerCase();
                    posNasPalavras[i] = j;
                    posNoPivout[i] = i - 1;
                    i++;
                    repetida = false;
                    break;
                }
                repetida = false;
                    
            }
        }
        /**Assim que todas as palavras forem lidas é instanciado a UI da partida.*/
        new UI_Partida(bancoPalavras, quantidadePalavras, posNasPalavras, posNoPivout, jogador1, jogador2, numeroPartidas);
    }
}

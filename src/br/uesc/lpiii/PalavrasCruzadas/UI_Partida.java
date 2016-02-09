package br.uesc.lpiii.PalavrasCruzadas;



import java.awt.Color;
import java.awt.Desktop;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**Classe responsavel por gerar a UI da partida(jogo) e também por verificar entradas
 * feitas pelo usuario dentro da UI.
 *
 * @author gabriel
 */
public class UI_Partida extends javax.swing.JFrame 
{

    /** Declaração de variaves principais */
    
    String[][] bancoPalavras; 
    
    int quantidadePalavras; 
    
    JTextField[] textPalavra; /**Vetor Temporariamente utilizado para guardar textField de uma palavra*/
    JTextField[][] bancoText; /**Matriz responsavel por guardar os textFields das palavras em jogo*/
    
    JLabel[] lbDica; /**Vetor com as dicas em uso na partida*/
    
    JLabel arrowP1; /**Label da jogada do player1*/
    JLabel arrowP2; /**Label da jogada do player2*/
    
    int[] posNasPalavras; /**Vetor com posição da letra da palavra de acordo com o pivou, usado para montar as palavras cruzadas*/
    
    int[] posNoPivout; /**Vetor com posição da letra no pivout usada na palavra*/
    
    int quantidadeLetras; /**Quantidade de letras na partida*/ 
    int quantidadeLetrasDigitadas; /**Quantidade de letras acertadas pelos jogadores*/
    
    int numeroPartidas;
    
    
    Jogador player1; /**Jogador 1*/
    Jogador player2; /**Jogador 2*/
    Jogador jogadorJogando; /**Jogador jogando no momento*/
    
    /**
     * Método Construtor da classe, onde se recebe os jogadores a jogar, juntamente as palavras, número da partida
     * e todo resto nécessario para criar montar o esquema de textField na UI.
     * @param bancoPalavras Matriz responsavel por guardar as palavras da partida atual
     * @param quantidadePalavras Quantidade de palavras na partida
     * @param PosNela Vetor com posição da letra da palavra de acordo com o pivou, usado para montar as palavras cruzadas
     * @param PosPivout Vetor com posição da letra no pivout usada na palavra
     * @param player1 Jogador 1
     * @param player2 Jogador 2
     * @param numeroPartidas Quantidade de partidas com os mesmos jogadores
     */
    public UI_Partida(String[][] bancoPalavras, int quantidadePalavras, int[] PosNela, int[] PosPivout, Jogador player1, Jogador player2, int numeroPartidas) 
    {
        this.numeroPartidas = numeroPartidas + 1;
        this.bancoPalavras = bancoPalavras;
        this.quantidadePalavras = quantidadePalavras;
        posNasPalavras = new int[quantidadePalavras];
        posNoPivout = new int[quantidadePalavras];
        quantidadeLetras = 0;
        for(int i = 0; i < quantidadePalavras; i++)
        {
            posNasPalavras[i] = PosNela[i];
            posNoPivout[i] = PosPivout[i];
            quantidadeLetras += bancoPalavras[i][1].length();
        }
        quantidadeLetras -= bancoPalavras[0][1].length();
        quantidadeLetrasDigitadas = 0;
        this.player1 = player1;
        this.player2 = player2;
        if(this.numeroPartidas % 2 == 0)
            jogadorJogando = player2; /**Cada partida um jogador diferente começa jogando*/
        else
            jogadorJogando = player1;
        initMyComponents(); /**Chamada para construção da UI*/
        if(this.numeroPartidas % 5 == 0)
            player2.esqueceBanco(); /**A cada 5 partidas o Computador esquece algumas palavras*/
        if (jogadorJogando instanceof Computador)
            jogadaIA(); /**Chamada do método de jogada do Computador caso ele comece jogando*/
    }
    
    private void initMyComponents()
    {
        initComponents(); /**Método gerado automaticamento pelo Matisse*/  
        /**Criação das labels de vez do jogador*/
        arrowP1 = new JLabel("Vez de " + player1.getNome());
        arrowP2 = new JLabel("Vez de " + player2.getNome());
        if(numeroPartidas % 2 == 0)
        {
            arrowP1.setVisible(false);
            arrowP2.setVisible(true);
        }
        else
        {
            arrowP1.setVisible(true);
            arrowP2.setVisible(false);
        }
        arrowP1.setLocation(430, 610);
        arrowP2.setLocation(430, 630);
        arrowP1.setSize(400, 20);
        arrowP2.setSize(400, 20);
        this.add(arrowP1);
        this.add(arrowP2);
        
        montaTextField(); /**Chamada do método responsavel pela montagem dos TextFields na UI*/
        
        /**Seta pontuação dos jogadores na tabela*/
        tabPlacar.setValueAt(player1.getNome(), 0, 0);
        tabPlacar.setValueAt(player1.getPontos(), 0, 1);
        tabPlacar.setValueAt(player2.getNome(), 1, 0);
        tabPlacar.setValueAt(player2.getPontos(), 1, 1);
        
        btProximoJogo.setEnabled(false);
        
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e)
            {
                /**Listener responsavel por pegar o fechamento da janela, assim guarda a pontuação atual e joga no placar de lideres*/
                int result = JOptionPane.showConfirmDialog(null,"Tem certeza que quer sair da partida?", "Sair do Jogo", JOptionPane.YES_NO_OPTION);
                if(result == JOptionPane.YES_OPTION)
                {
                    if(player1.getPontos() > player2.getPontos() && player1.getPontos() != 0)
                        new Placar().adicionarLider(player1);
                    else if(player2.getPontos() > player1.getPontos() && player2.getPontos() != 0)
                        new Placar().adicionarLider(player2);
                    else if (player1.getPontos() == player2.getPontos() && player1.getPontos() != 0)
                    {
                        new Placar().adicionarLider(player1);
                        new Placar().adicionarLider(player2);
                    }
                    new UI_Menu().setVisible(true);
                    dispose();
                }
            }
        });
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        

    }
    
    /** Neste método é gerado e posicionado na tela os TextFields.
     * Primeiramente é posicionado o Pivout na vertical no centro da UI, em seguida, é montada as outras na horizontal,
     *  pega-se a posição da letra da palavra da primeira linha e verifica-se a posição dessa letra no pivout,
     *  a partir dai é calculado a posição que deve começar a ser colocado o textField da palavra na UI.
     */
    private void montaTextField()
    {
        /**Montagem da primeira palavra, a palavra pivout, na vertical*/
        Point localizacao = new Point(500, 50);
        bancoText = new JTextField[quantidadePalavras][];
        lbDica = new JLabel[quantidadePalavras];
        
        
        textPalavra = new JTextField[bancoPalavras[0][1].length()];
        lbDica[0] = new JLabel();
        lbDica[0].setSize(30, 30);
        lbDica[0].setLocation(500, 25);
        lbDica[0].setText("Dica");
        lbDica[0].setToolTipText(bancoPalavras[0][0]); /**Dica para o hover do mouse*/
        lbDica[0].setVisible(true);
        this.add(lbDica[0]);
        bancoText[0] = new JTextField[bancoPalavras[0][1].length()];
        for(int i = 0; i < bancoPalavras[0][1].length(); i++)
        {
            textPalavra[i] = new JTextField();
            textPalavra[i].setSize(30, 30);
            textPalavra[i].setLocation(localizacao);
            textPalavra[i].setVisible(true);
            bancoText[0][i] = textPalavra[i];
            bancoText[0][i].setToolTipText("0:" + i);
            this.add(textPalavra[i]);
            localizacao.setLocation(localizacao.x, localizacao.y + 35);
            /**Listener adicionado para captar o enter e caso usuario apague letra em vermelho(errada), voltar a escrever em preto*/
            bancoText[0][i].addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt)
                        {
                            if(evt.getKeyCode() == KeyEvent.VK_ENTER)
                            {/**Ao usuario apertar enter é passado o campo que foi digitado ao método de verificação*/
                                JTextField campo = (JTextField) evt.getComponent();
                                jogada(campo);
                            }
                            if(evt.getKeyCode() == KeyEvent.VK_BACK_SPACE)
                            {
                                JTextField campo = (JTextField) evt.getComponent();
                                campo.setForeground(Color.BLACK);
                            }
                        }
                    });
        }
        
        /**Montagem das outras palavras na horizontal*/
        Point localPivout = new Point(500, 40); 
        for(int i = 1; i < quantidadePalavras; i++)
        {
                                              // -1 * pra ficar negativo | + 1 pq da label é da label                  | + 10 pq da label         
            Point localPalavra = new Point(localPivout.x +( -1 * ((posNasPalavras[i] + 1) * 35)), localPivout.y + (posNoPivout[i] * 35) + 10);
            
            textPalavra = new JTextField[bancoPalavras[i][1].length()];
            lbDica[i] = new JLabel();
            lbDica[i].setSize(30, 30);
            lbDica[i].setLocation(localPalavra);
            lbDica[i].setText("Dica");
            lbDica[i].setToolTipText(bancoPalavras[i][0]);/**Dica para o hover do mouse*/
            lbDica[i].setVisible(true);
            this.add(lbDica[i]);
            localPalavra.setLocation(localPalavra.x + 35, localPalavra.y);
            bancoText[i] = new JTextField[bancoPalavras[i][1].length()];
            for(int j = 0; j < bancoPalavras[i][1].length(); j++)
            {
                if(j != posNasPalavras[i]) /**Verifica se essa letra da palavra não é a que coincide com a do pivout*/
                {
                    textPalavra[j] = new JTextField();
                    textPalavra[j].setSize(30, 30);
                    textPalavra[j].setLocation(localPalavra);
                    textPalavra[j].setVisible(true);
                    bancoText[i][j] = textPalavra[j];
                    bancoText[i][j].setToolTipText(i + ":" + j);
                    /**Listener adicionado para captar o enter e caso usuario apague letra em vermelho(errada), voltar a escrever em preto*/
                    bancoText[i][j].addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt)
                        {
                            if(evt.getKeyCode() == KeyEvent.VK_ENTER)
                            {
                                /**Ao usuario apertar enter é passado o campo que foi digitado ao método de verificação*/
                                JTextField campo = (JTextField) evt.getComponent();
                                jogada(campo);
                            }
                            
                            if(evt.getKeyCode() == KeyEvent.VK_BACK_SPACE)
                            {
                                JTextField campo = (JTextField) evt.getComponent();
                                campo.setForeground(Color.BLACK);
                            }
                        }
                    });
                    this.add(textPalavra[j]);
                }
                else /**Caso sim, faz apenas a linkagem dos TextFields*/
                    bancoText[i][j] = bancoText[0][posNoPivout[i]];
                localPalavra.setLocation(localPalavra.x + 35, localPalavra.y);

            }
        }
    }
    
    /**Método responsavel por verificar as respostas enviadas pelos jogadores.
     * @param campo 
     */
    private void jogada(JTextField campo)
    {
        /**Pega-se o tooltiptext do campo, onde informa a posição do mesmo no banco de textFields*/
        String[] posicao = campo.getToolTipText().split(":");
        int i = Integer.parseInt(posicao[0]);
        int j = Integer.parseInt(posicao[1]);
        
        String letra = campo.getText().toLowerCase();
        if(letra.equals(bancoPalavras[i][1].substring(j, j + 1)))
        {
            /**Caso a letra entrada pelo jogador esteja correta o campo fica desativado na cor verde*/
            campo.setEnabled(false);
            campo.setForeground(Color.green);
            quantidadeLetrasDigitadas++;
            /**Verifica se toda a palavra foi digitada*/
            String respostaNoBox = "";
            for(int w = 0; w < bancoPalavras[i][1].length(); w++)
            {
                if(!bancoText[i][w].isEnabled())
                    respostaNoBox += bancoText[i][w].getText();
            }
            if(respostaNoBox.equals(bancoPalavras[i][1]))
            {   
                /**Caso a palavra toda for digitada, então é adicionado 1 ponto ao jogador e atualizado o placar*/
                jogadorJogando.addPontos();
                tabPlacar.setValueAt(player1.getPontos(), 0, 1);
                tabPlacar.setValueAt(player2.getPontos(), 1, 1);
            }

        }
        else
        {
            /**Se o jogador errou, é passado a vez para o outro jogador e a letra digitada errada fica vermelha*/
            campo.setForeground(Color.RED);
            
            if(jogadorJogando == player1)
            {
                jogadorJogando = player2;
                arrowP2.setVisible(true);
                arrowP1.setVisible(false);
                if(jogadorJogando instanceof Computador)
                    jogadaIA();/**Caso o jogador a seguir for um Computador, então é chamado o método de jogada do mesmo*/
            }
            else
            {
                jogadorJogando = player1;
                arrowP1.setVisible(true);
                arrowP2.setVisible(false);
            }
            
            
        }

        if(quantidadeLetrasDigitadas == quantidadeLetras)
        {
            /**Se todas as palavras já foram digitadas então é liberado a opção de continuar o jogo
             e o Computador vai guardar todas as dicas juntamente as palavras respectivas no seu banco de dados*/
            btProximoJogo.setEnabled(true);
            
            if(player1 instanceof Computador)
            {
                for(int w = 0; w < quantidadePalavras; w++)
                    player1.addNoBanco(lbDica[w].getToolTipText().toLowerCase(), bancoPalavras[w][1]);
            }
            else if(player2 instanceof Computador)
            {
                for(int w = 0; w < quantidadePalavras; w++)
                    player2.addNoBanco(lbDica[w].getToolTipText().toLowerCase(), bancoPalavras[w][1]);
            }
            
            if(player1.getPontos() > player2.getPontos())
                JOptionPane.showMessageDialog(null, player1.getNome() + " ganhou a partida com " + player1.getPontos() + " pontos.\nPara continuar clique em proximo jogo.", "Partida Finalizada", JOptionPane.PLAIN_MESSAGE);
            else if(player2.getPontos() > player1.getPontos())
                JOptionPane.showMessageDialog(null, player2.getNome() + " ganhou a partida com " + player2.getPontos() + " pontos.\nPara continuar clique em proximo jogo.", "Partida Finalizada", JOptionPane.PLAIN_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, "Jogo acabou empatado!!\nPara continuar clique em proxima partida", "Partida Finalizada", JOptionPane.PLAIN_MESSAGE);
        }
   }
    /**Método responsavel pela jogada do Computador.
     * Primeiramente o computador vai verificar se no banco de dados dele já existe as dicas presentes no jogo,
     *  caso exista ele vai colocar as palavras que tem no banco e em seguida tentar alguma letra aleatoria em qualquer
     *  textBox disponivel, se ele não souber nenhuma palavra então ele vai diretamente colocar uma letra aleatoria em
     *  qualquer TextBox disponivel.*/
    private void jogadaIA()
    {
        if(jogadorJogando instanceof Computador && quantidadeLetrasDigitadas != quantidadeLetras)
        {
            for(int i = 0; i < quantidadePalavras; i++)
            {
                String resposta = jogadorJogando.consultaBanco(lbDica[i].getToolTipText().toLowerCase());
                if(resposta != null)
                {
                    for(int j = 0; j < resposta.length(); j++)
                    {
                        if(bancoText[i][j].isEnabled())
                        {
                            /**Escreve a letra da palavra já sabida e chama o método de verificação da jogada*/
                            bancoText[i][j].setText(resposta.substring(j, j + 1)); 
                            jogada(bancoText[i][j]);
                        }
                    }
                }
            }
        
            while(jogadorJogando instanceof Computador && quantidadeLetrasDigitadas != quantidadeLetras)
            {
                Random gerador = new Random();
                int palEscolhida = gerador.nextInt(quantidadePalavras);
                int tentativas = 0;
                for(int i = 0; i < bancoPalavras[palEscolhida][1].length(); i++)
                {
                    if(!(jogadorJogando instanceof Computador) || quantidadeLetrasDigitadas == quantidadeLetras)
                        return;

                    if(bancoText[palEscolhida][i].isEnabled())
                    {
                        /**Joga uma letra aleatoria em um textField aleatorio e chama o método de verificação*/
                        char letra = ((char) (gerador.nextInt(26) + 97));
                        bancoText[palEscolhida][i].setText(String.valueOf(letra));
                        jogada(bancoText[palEscolhida][i]);
                    }
                    else
                    {
                        tentativas++;
                        if(tentativas == bancoPalavras[palEscolhida][1].length())
                        {
                            palEscolhida = gerador.nextInt(quantidadePalavras);
                            i = 0;
                        }
                    }
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        UI_Placar = new javax.swing.JFrame();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbPlacar = new javax.swing.JTable();
        UI_Sobre = new javax.swing.JFrame();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btProximoJogo = new javax.swing.JButton();
        btNovoJogo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabPlacar = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        btManual = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        btPlacarDeLideres = new javax.swing.JMenuItem();
        btSobre = new javax.swing.JMenuItem();
        btAdicionaPalavra = new javax.swing.JMenuItem();

        tbPlacar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Jogador", "Pontos"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tbPlacar);

        javax.swing.GroupLayout UI_PlacarLayout = new javax.swing.GroupLayout(UI_Placar.getContentPane());
        UI_Placar.getContentPane().setLayout(UI_PlacarLayout);
        UI_PlacarLayout.setHorizontalGroup(
            UI_PlacarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        UI_PlacarLayout.setVerticalGroup(
            UI_PlacarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        jLabel1.setText("Autor: Gabriel Rodrigues dos Santos");

        jLabel2.setText("JDK 1.8.0_65");

        jLabel3.setText("IDE utilizada: NetBeands 8.0.2");

        jLabel4.setText("Sobre Palavras Cruzadas");

        jLabel5.setText("09/01/2016");

        javax.swing.GroupLayout UI_SobreLayout = new javax.swing.GroupLayout(UI_Sobre.getContentPane());
        UI_Sobre.getContentPane().setLayout(UI_SobreLayout);
        UI_SobreLayout.setHorizontalGroup(
            UI_SobreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UI_SobreLayout.createSequentialGroup()
                .addGroup(UI_SobreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UI_SobreLayout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(jLabel1))
                    .addGroup(UI_SobreLayout.createSequentialGroup()
                        .addGap(212, 212, 212)
                        .addComponent(jLabel2))
                    .addGroup(UI_SobreLayout.createSequentialGroup()
                        .addGap(158, 158, 158)
                        .addComponent(jLabel3))
                    .addGroup(UI_SobreLayout.createSequentialGroup()
                        .addGap(177, 177, 177)
                        .addComponent(jLabel4))
                    .addGroup(UI_SobreLayout.createSequentialGroup()
                        .addGap(220, 220, 220)
                        .addComponent(jLabel5)))
                .addContainerGap(149, Short.MAX_VALUE))
        );
        UI_SobreLayout.setVerticalGroup(
            UI_SobreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UI_SobreLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel4)
                .addGap(49, 49, 49)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addContainerGap(86, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Palavras Cruzadas");
        setLocation(new java.awt.Point(500, 200));
        setMaximumSize(new java.awt.Dimension(500, 400));
        setMinimumSize(new java.awt.Dimension(500, 400));

        btProximoJogo.setText("Proximo Jogo");
        btProximoJogo.setToolTipText("");
        btProximoJogo.setName(""); // NOI18N
        btProximoJogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProximoJogoActionPerformed(evt);
            }
        });

        btNovoJogo.setText("Novo Jogo");
        btNovoJogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNovoJogoActionPerformed(evt);
            }
        });

        tabPlacar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null}
            },
            new String [] {
                "Jogador", "Pontos"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabPlacar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(tabPlacar);

        btManual.setText("Ajuda");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jMenuItem1.setText("Manual");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        btManual.add(jMenuItem1);

        jMenuBar1.add(btManual);

        jMenu2.setText("Opções");

        btPlacarDeLideres.setText("Placar de Lideres");
        btPlacarDeLideres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPlacarDeLideresActionPerformed(evt);
            }
        });
        jMenu2.add(btPlacarDeLideres);

        btSobre.setText("Sobre");
        btSobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSobreActionPerformed(evt);
            }
        });
        jMenu2.add(btSobre);

        btAdicionaPalavra.setText("Adicionar Palavra");
        btAdicionaPalavra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAdicionaPalavraActionPerformed(evt);
            }
        });
        jMenu2.add(btAdicionaPalavra);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 371, Short.MAX_VALUE)
                .addComponent(btNovoJogo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btProximoJogo)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btNovoJogo)
                            .addComponent(btProximoJogo)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 637, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btNovoJogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNovoJogoActionPerformed
        /**Caso selecionado a opção de novo Jogo, fecha-se essa partida e cria-se outro menu principal*/
        new UI_Menu().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btNovoJogoActionPerformed

    private void btProximoJogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProximoJogoActionPerformed
        /**Caso o jogo tenha acabado e o jogador pretende continuar para uma nova partida.
         É criado uma nova partida com os mesmos jogadores e fechada a partida atual*/
        new Partida(player1, player2, numeroPartidas);
        this.dispose();
    }//GEN-LAST:event_btProximoJogoActionPerformed
    /**Método responsavel pela criação da janela de adicionar uma nova palavra ao dicionario*/
    private void btAdicionaPalavraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAdicionaPalavraActionPerformed
        JFrame UI_AddPal = new JFrame("Adicionar Palavra");
        JLabel lbDica = new JLabel("Informe a Dica:");
        JTextField txDica = new JTextField();
        JLabel lbPalavra = new JLabel("Informe a Palavra:");
        JTextField txPalavra = new JTextField();
        JPanel panel = new JPanel();
        JButton btOk = new JButton();
        JButton btLimpar = new JButton();
        btOk.setText("Adicionar");
        btOk.setSize(100, 20);
        btOk.setLocation(210, 120);
        btLimpar.setText("Limpar");
        btLimpar.setSize(100, 20);
        btLimpar.setLocation(320, 120);
        lbDica.setLocation(10, 20);
        txDica.setLocation(150, 20);
        lbPalavra.setLocation(10, 50);
        txPalavra.setLocation(150, 50);  
        lbDica.setSize(400, 30);
        lbPalavra.setSize(400, 30);
        txDica.setSize(400, 25);
        txPalavra.setSize(400, 25);
        UI_AddPal.add(btOk);
        UI_AddPal.add(btLimpar);
        UI_AddPal.add(lbDica);
        UI_AddPal.add(txDica);
        UI_AddPal.add(lbPalavra);
        UI_AddPal.add(txPalavra);
        UI_AddPal.add(panel);
        UI_AddPal.setSize(600, 200);
        btOk.setVisible(true);
        btLimpar.setVisible(true);
        lbDica.setVisible(true);
        txDica.setVisible(true);
        lbPalavra.setVisible(true);
        txPalavra.setVisible(true);
        UI_AddPal.setVisible(true);
        UI_AddPal.setResizable(false);
        UI_AddPal.setLocationRelativeTo(null);
        txPalavra.setText(null);
        txDica.setText(null);
        
        btOk.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if(txDica.getText().equals("") || txPalavra.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Impossivel adicionar palavras em branco", "Erro", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    new Dicionario().AdicionaPalavra(txDica.getText(), txPalavra.getText());
                    JOptionPane.showMessageDialog(null, "Palavra adicionada com sucesso ao dicionario", "Adicionar Palavra", JOptionPane.PLAIN_MESSAGE);
                    txDica.setText(null);
                    txPalavra.setText(null);
                }
                    
            }
        });
        
        btLimpar.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                txDica.setText(null);
                txPalavra.setText(null);
            }
        });
    }//GEN-LAST:event_btAdicionaPalavraActionPerformed
    /**Método responsavel pela criação da janela do placar de lideres*/
    private void btPlacarDeLideresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPlacarDeLideresActionPerformed
        /**Aqui lê-se o arquivo do placar de lideres e o coloca em ordem crescente de pontos,
            em seguida é adicionado a tabela que é mostrada na janela do placar de lideres*/
        Placar pLider = new Placar();
        Jogador[] lideres = pLider.getLideres();
        if(lideres != null)
        {
            if(tbPlacar.getRowCount() != lideres.length)
            {
                for(int i = 0; i < lideres.length; i++)
                {
                    for(int j = 0; j < lideres.length - 1; j++)
                    {
                        if(lideres[j + 1].getPontos() > lideres[j].getPontos())
                        {
                            Jogador aux = lideres[j];
                            lideres[j] = lideres[j + 1];
                            lideres[j + 1] = aux;
                        }
                    }
                }
                DefaultTableModel model = (DefaultTableModel) tbPlacar.getModel();
                for(int i = 0; i < lideres.length; i++)
                {
                    Object linha[] = {lideres[i].getNome(), lideres[i].getPontos()};
                    model.addRow(linha);
                }
            }
            UI_Placar.pack();
            UI_Placar.setTitle("Placar de Lideres");
            UI_Placar.setVisible(true);
            UI_Placar.setLocationRelativeTo(null);
        }
        else
            JOptionPane.showMessageDialog(null, "Nenhum lider cadastrado.", "ERRO", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_btPlacarDeLideresActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        try {
            String caminho = this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            caminho = caminho.substring(1, caminho.lastIndexOf('/') + 1);
            caminho =  "/"+ caminho + "extras/Manual.pdf";
            File manual = new File(caminho);
            Desktop.getDesktop().open(manual);
	} catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex,  "ERRO",JOptionPane.ERROR_MESSAGE);  
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void btSobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSobreActionPerformed
        UI_Sobre.setVisible(true);
        UI_Sobre.setLocationRelativeTo(null);
        UI_Sobre.pack();
        UI_Sobre.setTitle("Sobre");
    }//GEN-LAST:event_btSobreActionPerformed

    
    //Variaveis da GUI geradas pelo Netbeans
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame UI_Placar;
    private javax.swing.JFrame UI_Sobre;
    private javax.swing.JMenuItem btAdicionaPalavra;
    private javax.swing.JMenu btManual;
    private javax.swing.JButton btNovoJogo;
    private javax.swing.JMenuItem btPlacarDeLideres;
    private javax.swing.JButton btProximoJogo;
    private javax.swing.JMenuItem btSobre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabPlacar;
    private javax.swing.JTable tbPlacar;
    // End of variables declaration//GEN-END:variables
    
}
/**
 * Quando eu fiz isso, só eu e deus sabia. Agora só deus sabe.
 */
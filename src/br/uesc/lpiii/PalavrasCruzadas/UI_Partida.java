/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uesc.lpiii.PalavrasCruzadas;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author gabriel
 */
public class UI_Partida extends javax.swing.JFrame 
{

    /*
        Declaração de variaves principais
    */
    String[] palavra;
    String[][] bancoPalavras;
    
    int quantidadePalavras;
    
    JTextField[] textPalavra;
    JTextField[][] bancoText;
    
    JTextField[][] textRespostas;
    
    JLabel[] lbDica;
    
    int[] posNasPalavras;
    
    int[] posNoPivout;
    
    int quantidadeLetras;
    
    Jogador player1;
    Jogador player2;
    Jogador jogadorJogando;
    
    
    public UI_Partida(String[][] bancoPalavras, int quantidadePalavras, int[] PosNela, int[] PosPivout, Jogador player1, Jogador player2) 
    {
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
        this.player1 = player1;
        this.player2 = player2;
        jogadorJogando = player1;
        initMyComponents();
    }
    
    private void initMyComponents()
    {
        initComponents();
        Point localizacao = new Point(450, 50);
        bancoText = new JTextField[quantidadePalavras][];
        textRespostas = new JTextField[quantidadePalavras][];
        lbDica = new JLabel[quantidadePalavras];
        
        
        textPalavra = new JTextField[bancoPalavras[0][1].length()];
        lbDica[0] = new JLabel();
        lbDica[0].setSize(30, 30);
        lbDica[0].setLocation(450, 25);
        lbDica[0].setText("Dica");
        lbDica[0].setToolTipText(bancoPalavras[0][0]);
        lbDica[0].setVisible(true);
        this.add(lbDica[0]);
        bancoText[0] = new JTextField[bancoPalavras[0][1].length()];
        textRespostas[0] = new JTextField[bancoPalavras[0][1].length()];
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
            textRespostas[0][i] = new JTextField();
            bancoText[0][i].addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt)
                        {
                            if(evt.getKeyCode() == KeyEvent.VK_ENTER)
                            {
                                JTextField campo = (JTextField) evt.getComponent();
                                verificaLetra(campo);
                            }
                            if(evt.getKeyCode() == KeyEvent.VK_BACK_SPACE)
                            {
                                JTextField campo = (JTextField) evt.getComponent();
                                campo.setForeground(Color.BLACK);
                            }
                        }
                    });
        }
        textRespostas[0][0].setText(null);
        
        montaTextField();
        
        tabPlacar.setValueAt(player1.getNome(), 0, 0);
        tabPlacar.setValueAt(player1.getPontos(), 0, 1);
        tabPlacar.setValueAt(player2.getNome(), 1, 0);
        tabPlacar.setValueAt(player2.getPontos(), 1, 1);
        
        btProximoJogo.setEnabled(false);
        
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e)
            {
                int result = JOptionPane.showConfirmDialog(null,"Tem certeza que quer sair da partida?", "Sair do Jogo", JOptionPane.YES_NO_OPTION);
                if(result == JOptionPane.YES_OPTION)
                {
                    new UI_Menu().setVisible(true);
                    dispose();
                }
            }
        });
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        

    }
    
    private void montaTextField()
    {
        Point localPivout = new Point(450, 40); 
        for(int i = 1; i < quantidadePalavras; i++)
        {
                                              // -1 * pra ficar negativo | + 1 pq da label é da label                  | + 10 pq da label         
            Point localPalavra = new Point(localPivout.x +( -1 * ((posNasPalavras[i] + 1) * 35)), localPivout.y + (posNoPivout[i] * 35) + 10);
            
            textPalavra = new JTextField[bancoPalavras[i][1].length()];
            lbDica[i] = new JLabel();
            lbDica[i].setSize(30, 30);
            lbDica[i].setLocation(localPalavra);
            lbDica[i].setText("Dica");
            lbDica[i].setToolTipText(bancoPalavras[i][0]);
            lbDica[i].setVisible(true);
            this.add(lbDica[i]);
            localPalavra.setLocation(localPalavra.x + 35, localPalavra.y);
            bancoText[i] = new JTextField[bancoPalavras[i][1].length()];
            textRespostas[i] = new JTextField[bancoPalavras[i][1].length()];
            for(int j = 0; j < bancoPalavras[i][1].length(); j++)
            {
                if(j != posNasPalavras[i])
                {
                    textPalavra[j] = new JTextField();
                    textPalavra[j].setSize(30, 30);
                    textPalavra[j].setLocation(localPalavra);
                    textPalavra[j].setVisible(true);
                    bancoText[i][j] = textPalavra[j];
                    bancoText[i][j].setToolTipText(i + ":" + j);
                    bancoText[i][j].addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt)
                        {
                            if(evt.getKeyCode() == KeyEvent.VK_ENTER)
                            {
                                JTextField campo = (JTextField) evt.getComponent();
                                verificaLetra(campo);
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
                else
                    bancoText[i][j] = bancoText[0][posNoPivout[i]];
                localPalavra.setLocation(localPalavra.x + 35, localPalavra.y);
                textRespostas[i][j] = new JTextField();
                textRespostas[i][j].setText(null);

            }
        }
    }
    
    private void verificaLetra(JTextField campo)
    {
        String[] posicao = campo.getToolTipText().split(":");
        int i = Integer.parseInt(posicao[0]);
        int j = Integer.parseInt(posicao[1]);
        
        String letra = campo.getText().toLowerCase();
        if(letra.equals(bancoPalavras[i][1].substring(j, j + 1)))
        {
            campo.setEnabled(false);
            campo.setForeground(Color.green);
            jogadorJogando.addPontos();
            tabPlacar.setValueAt(player1.getPontos(), 0, 1);
            tabPlacar.setValueAt(player2.getPontos(), 1, 1);

        }
        else
        {
            campo.setForeground(Color.RED);
            
            if(jogadorJogando == player1)
                jogadorJogando = player2;
            else
                jogadorJogando = player1;
        }

        if(player1.getPontos() + player2.getPontos() == quantidadeLetras)
        {
            btProximoJogo.setEnabled(true);
            if(player1.getPontos() > player2.getPontos())
                JOptionPane.showMessageDialog(null, player1.getNome() + " ganhou a partida com " + player1.getPontos() + " pontos.\nPara continuar clique em proximo jogo.", "Partida Finalizada", JOptionPane.PLAIN_MESSAGE);
            else if(player2.getPontos() > player1.getPontos())
                JOptionPane.showMessageDialog(null, player2.getNome() + " ganhou a partida com " + player2.getPontos() + " pontos.\nPara continuar clique em proximo jogo.", "Partida Finalizada", JOptionPane.PLAIN_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, "Jogo acabou empatado!!\nPara continuar clique em proxima partida", "Partida Finalizada", JOptionPane.PLAIN_MESSAGE);
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

        btProximoJogo = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabPlacar = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

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

        jButton1.setText("Novo Jogo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tabPlacar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null}
            },
            new String [] {
                "Jogadores", "Pontos"
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
        tabPlacar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(tabPlacar);
        if (tabPlacar.getColumnModel().getColumnCount() > 0) {
            tabPlacar.getColumnModel().getColumn(0).setResizable(false);
            tabPlacar.getColumnModel().getColumn(1).setResizable(false);
        }

        jMenu1.setText("Ajuda");

        jMenuItem1.setText("Manual");
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Opções");

        jMenuItem2.setText("Placar de Lideres");
        jMenu2.add(jMenuItem2);

        jMenuItem3.setText("Sobre");
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 271, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btProximoJogo)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(607, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(btProximoJogo)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new UI_Menu().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btProximoJogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProximoJogoActionPerformed
        new Partida(player1, player2);
        this.dispose();
    }//GEN-LAST:event_btProximoJogoActionPerformed

    
    
    
    //Variaveis da GUI geradas pelo Netbeans
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btProximoJogo;
    private javax.swing.JButton jButton1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabPlacar;
    // End of variables declaration//GEN-END:variables
    
}

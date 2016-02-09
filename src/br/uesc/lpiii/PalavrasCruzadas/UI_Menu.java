package br.uesc.lpiii.PalavrasCruzadas;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


/**Classe responsavel pela criação do menu principal.
 *
 * @author gabriel
 */

public class UI_Menu extends javax.swing.JFrame {

    /**
     * Cria o novo formulario UI_Menu
     */
    public UI_Menu() 
    {
        initMyComponents();        
    }
    
    private void initMyComponents()
    {
        initComponents(); /**Método criado automaticamente pelo matisse*/        
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e)
            {
                int result = JOptionPane.showConfirmDialog(null,"Tem certeza que quer sair do jogo?", "Sair do Jogo", JOptionPane.YES_NO_OPTION);
                if(result == JOptionPane.YES_OPTION)
                {
                    new UI_Menu().setVisible(true);
                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
            }
        });
        groupButton();
    }
    /**Método responsavel por agrupar os radioButton, fazendo com que a seleção de 1 desative a seleção do outro*/
    private void groupButton()
    {
        ButtonGroup bg = new ButtonGroup();
        
        bg.add(rbIA);
        bg.add(rbJogador);
        
        rbIA.setSelected(true);
        
        textJogador2.setEnabled(false);
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        UI_Placar = new javax.swing.JFrame();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPlacar = new javax.swing.JTable();
        UI_Sobre = new javax.swing.JFrame();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        rbIA = new javax.swing.JRadioButton();
        rbJogador = new javax.swing.JRadioButton();
        btJogar = new javax.swing.JButton();
        textJogador1 = new javax.swing.JTextField();
        textJogador2 = new javax.swing.JTextField();
        lbJogador1 = new javax.swing.JLabel();
        lbJogador2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        btManual = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        btPlacarDeLideres = new javax.swing.JMenuItem();
        btSobre = new javax.swing.JMenuItem();
        btAdicionarPalavras = new javax.swing.JMenuItem();

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
        jScrollPane1.setViewportView(tbPlacar);

        javax.swing.GroupLayout UI_PlacarLayout = new javax.swing.GroupLayout(UI_Placar.getContentPane());
        UI_Placar.getContentPane().setLayout(UI_PlacarLayout);
        UI_PlacarLayout.setHorizontalGroup(
            UI_PlacarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        UI_PlacarLayout.setVerticalGroup(
            UI_PlacarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Palavras Cruzadas");

        rbIA.setText("Contra Computador");
        rbIA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbIAActionPerformed(evt);
            }
        });

        rbJogador.setText("Contra Jogador");
        rbJogador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbJogadorActionPerformed(evt);
            }
        });

        btJogar.setText("Jogar");
        btJogar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btJogarActionPerformed(evt);
            }
        });

        lbJogador1.setText("Jogador 1");

        lbJogador2.setText("Jogador 2");

        jMenu1.setText("Ajuda");

        btManual.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        btManual.setText("Manual");
        btManual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btManualActionPerformed(evt);
            }
        });
        jMenu1.add(btManual);

        jMenuBar1.add(jMenu1);

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

        btAdicionarPalavras.setText("Adicionar Palavra");
        btAdicionarPalavras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAdicionarPalavrasActionPerformed(evt);
            }
        });
        jMenu2.add(btAdicionarPalavras);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbJogador2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lbJogador1)
                            .addGap(74, 74, 74))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textJogador1)
                            .addComponent(textJogador2, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(117, 117, 117)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbIA)
                            .addComponent(rbJogador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(65, 65, 65))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btJogar)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbJogador1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textJogador1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbJogador2)
                        .addGap(10, 10, 10)
                        .addComponent(textJogador2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rbIA)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbJogador)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btJogar)))
                .addContainerGap(122, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbJogadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbJogadorActionPerformed
        textJogador2.setEnabled(true);
    }//GEN-LAST:event_rbJogadorActionPerformed

    private void rbIAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbIAActionPerformed
        textJogador2.setEnabled(false);
    }//GEN-LAST:event_rbIAActionPerformed

    private void btJogarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btJogarActionPerformed
        /**Verifica se os textFields de nome de jogador estão vazio e instancia uma nova partida com os tipos de jogares e nomes*/        
        if((textJogador1.getText().equals("") || textJogador2.getText().equals("")) && rbJogador.isSelected())
            JOptionPane.showMessageDialog(null, "Nome de jogador não pode estar em branco", "Erro", JOptionPane.ERROR_MESSAGE);
        
        else if(textJogador1.getText().equals("") && rbIA.isSelected())   
            JOptionPane.showMessageDialog(null, "Nome de jogador não pode estar em branco", "Erro", JOptionPane.ERROR_MESSAGE);
        else if(rbIA.isSelected())
        {
            Jogador jogador1 = new Jogador(textJogador1.getText(), 0);
            Jogador IA = new Computador(0);
            new Partida(jogador1, IA, 0);
            this.dispose();
        }
        
        else
        {
            Jogador jogador1 = new Jogador(textJogador1.getText(), 0);
            Jogador jogador2 = new Jogador(textJogador2.getText(), 0);
            new Partida(jogador1, jogador2, 0);
            this.dispose();
        }
            
    }//GEN-LAST:event_btJogarActionPerformed
    /**Método responsavel por abrir o manual do jogo*/
    private void btManualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btManualActionPerformed
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
    }//GEN-LAST:event_btManualActionPerformed
    /**Método responsavel pela criação da janela de adicionar uma nova palavra ao dicionario*/
    private void btAdicionarPalavrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAdicionarPalavrasActionPerformed
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
    }//GEN-LAST:event_btAdicionarPalavrasActionPerformed
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

    private void btSobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSobreActionPerformed
        UI_Sobre.setVisible(true);
        UI_Sobre.setLocationRelativeTo(null);
        UI_Sobre.pack();
        UI_Sobre.setTitle("Sobre");
    }//GEN-LAST:event_btSobreActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame UI_Placar;
    private javax.swing.JFrame UI_Sobre;
    private javax.swing.JMenuItem btAdicionarPalavras;
    private javax.swing.JButton btJogar;
    private javax.swing.JMenuItem btManual;
    private javax.swing.JMenuItem btPlacarDeLideres;
    private javax.swing.JMenuItem btSobre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbJogador1;
    private javax.swing.JLabel lbJogador2;
    private javax.swing.JRadioButton rbIA;
    private javax.swing.JRadioButton rbJogador;
    private javax.swing.JTable tbPlacar;
    private javax.swing.JTextField textJogador1;
    private javax.swing.JTextField textJogador2;
    // End of variables declaration//GEN-END:variables
}

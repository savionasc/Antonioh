package Antonioh;

import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author savio
 */
public class ModeloBackup extends javax.swing.JFrame {

    List<Carta> cemiterio = new ArrayList<>();
    HashMap m = new HashMap();
    HashMap am = new HashMap();
    HashMap ar = new HashMap();
    public ModeloBackup() {
        //initComponents();
        
        int fase = 0;

        //Cartas magicas
        //case 1: destroi 1 inimigo
        //case 2: destroi todos monstros do inimigo
        //case 3: equipa 1 monstro em ataque
        //case 4: equipa 1 monstro em defesa
        
        //Construindo monstros
        CartaMonstro magoNegro = new CartaMonstro("Mago Negro", "", 2500, 2100);
        CartaMonstro dragaoBranco = new CartaMonstro("Dragão Branco", "", 3000, 2500);
        CartaMonstro obelisk = new CartaMonstro("Obelisk", "", 4000, 4000);
        CartaMagica raigeki = new CartaMagica("Raigeki", "", 1, 1);
        CartaMagica lendarySword = new CartaMagica("Lendary Sword", "", 3, 300);
        CartaMagica darkHole = new CartaMagica("Dark Hole", "", 2, 0);
        CartaArmadilha mirrorForce = new CartaArmadilha("Mirror Force", "", 2);
        CartaArmadilha trapHole = new CartaArmadilha("Trap Hole", "", 1);
        Carta aux;
        
        //adicionando no campo (Os monstros)
        m.put("a", magoNegro);
        m.put("b", dragaoBranco);
        m.put("c", obelisk);

        //adicionando no campo (As Magicas)
        am.put("r", raigeki);
        am.put("l", lendarySword);
        am.put("h", darkHole);

        //adicionando no campo (As Armadilhas)
        ar.put("m", mirrorForce);
        ar.put("t", trapHole);
        
        //Mostrando as cartas no console
        Iterator it = m.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
                System.out.println(pair.getKey() + " = " + ((CartaMonstro)pair.getValue()).getNome());
                System.out.println("Ataque: " + ((CartaMonstro)pair.getValue()).getAtaque());
                System.out.println("Defesa: " + ((CartaMonstro)pair.getValue()).getDefesa());
        }
        
        it = am.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + ((CartaMagica)pair.getValue()).getNome());
        }
        
        it = ar.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + ((CartaArmadilha)pair.getValue()).getNome());
        }
        
        
        //Modo de batalha
        String cartaATK = JOptionPane.showInputDialog("Escolha uma carta para atacar um monstro");
        
        JOptionPane.showMessageDialog(rootPane, "Tamanho: "+(m.size()+am.size()+ar.size()));
        JOptionPane.showMessageDialog(rootPane, ((CartaMonstro) m.get(cartaATK)).getNome());
        String atacadoL = JOptionPane.showInputDialog("Escolha o monstro para ser atacada");
        CartaMonstro atacado = (CartaMonstro) m.get(atacadoL);
        
        fase = 1;
        //Monstros atacando
        //Se carta armadilha nao for ativada
        if(!efeitoArmadilha((Carta)m.get(cartaATK), cartaATK)){
            if(atacado.isAtk()){
                if ( atacado.getAtaque() > ((CartaMonstro) m.get(cartaATK)).getAtaque() ){
                    aux = (Carta) m.get(cartaATK);
                    m.remove(cartaATK);
                    cemiterio.add(aux);
                } else if(atacado.getAtaque() < ((CartaMonstro) m.get(cartaATK)).getAtaque()){
                    aux = (Carta) m.get(atacadoL);
                    m.remove(atacadoL);
                    cemiterio.add(aux);
                }
            }else{
                if ( atacado.getDefesa() > ((CartaMonstro) m.get(cartaATK)).getAtaque() ){
                    aux = (Carta) m.get(cartaATK);
                    m.remove(cartaATK);
                    cemiterio.add(aux);
                } else if(atacado.getDefesa() < ((CartaMonstro) m.get(cartaATK)).getAtaque()){
                    aux = (Carta) m.get(atacadoL);
                    m.remove(atacadoL);
                    cemiterio.add(aux);
                }
            }
        }
        
        
        //Cartas magicas
        //case 1: destroi 1 inimigo
        //case 2: destroi todos monstros do inimigo
        //case 3: equipa 1 monstro em ataque
        //case 4: equipa 1 monstro em defesa
        //case 5: revive 1 monstro em ataque
        
        //Ativando uma carta magica
        String magica = JOptionPane.showInputDialog("Escolha a magica");
        CartaMagica cartaMagica = (CartaMagica) am.get(magica);
        
        JOptionPane.showMessageDialog(rootPane, "Escolhida: "+cartaMagica.getNome());
        
        switch (cartaMagica.getEspecialidade()) {
            case 1:{
                    String mATK = JOptionPane.showInputDialog("Escolha a monstro");
                    if(mATK != null){
                        CartaMonstro cartaMonstro = (CartaMonstro) m.get(mATK);
                        System.out.println("Monstro destruído: "+cartaMonstro.getNome());
                        cemiterio.add((Carta) m.get(mATK));
                        m.remove(mATK);
                    }
            break;
            }case 2:{
                    System.out.println("Todos os monstros foram destruídos.");
                    it = m.entrySet().iterator();
                    while(it.hasNext()){
                        Map.Entry pair = (Map.Entry)it.next();
                        cemiterio.add((Carta) m.get(pair.getKey()));
                    }
                    
                    m.clear();
            break;
            }case 3:{
                    String mATK = JOptionPane.showInputDialog("Escolha a monstro");
                    if(mATK != null){
                        CartaMonstro cartaMonstro = (CartaMonstro) m.get(mATK);
                        cartaMonstro.setAtaque(cartaMonstro.getAtaque()+cartaMagica.getNumber());
                        System.out.println("Monstro Equipado: "+cartaMonstro.getNome());
                        m.replace(mATK, cartaMonstro);
                    }
            break;
            }case 4:{
                    String mATK = JOptionPane.showInputDialog("Escolha a monstro");
                    if(mATK != null){
                        CartaMonstro cartaMonstro = (CartaMonstro) m.get(mATK);
                        cartaMonstro.setDefesa(cartaMonstro.getDefesa()+cartaMagica.getNumber());
                        System.out.println("Monstro Equipado: "+cartaMonstro.getNome());
                        m.replace(mATK, cartaMonstro);
                    }
            break;
            }
        }

        cemiterio.add((Carta) m.get(magica));
        am.remove(magica);
        
        //Mostrando as cartas no console
        it = m.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + ((CartaMonstro)pair.getValue()).getNome());
            System.out.println("Ataque: " + ((CartaMonstro)pair.getValue()).getAtaque());
            System.out.println("Defesa: " + ((CartaMonstro)pair.getValue()).getDefesa());        }
        
        it = am.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + ((CartaMagica)pair.getValue()).getNome());
        }
        
        it = ar.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + ((CartaArmadilha)pair.getValue()).getNome());
        }
        
        System.out.println("Cartas do cemiterio: "+cemiterio.size());
        for (int j = 0; j < cemiterio.size(); j++) {
            System.out.println("i: " + ((Carta)cemiterio.get(j)).getNome());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jButton35 = new javax.swing.JButton();
        jButton36 = new javax.swing.JButton();
        jButton37 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        jButton38 = new javax.swing.JButton();
        jButton39 = new javax.swing.JButton();
        jButton34 = new javax.swing.JButton();
        jButton40 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton41 = new javax.swing.JButton();
        jButton42 = new javax.swing.JButton();
        jButton43 = new javax.swing.JButton();
        jButton44 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton21.setIcon(new javax.swing.ImageIcon("C:\\Users\\savio\\Desktop\\Cards\\200x295\\1624300-fairyguardian.jpg")); // NOI18N
        jButton21.setText("jButton1");
        jButton21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton21MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton21MouseEntered(evt);
            }
        });

        jButton32.setIcon(new javax.swing.ImageIcon("C:\\Users\\savio\\Desktop\\Cards\\200x295\\1624301-fairymeteorcrush.jpg")); // NOI18N
        jButton32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton32MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton32MouseEntered(evt);
            }
        });

        jButton35.setIcon(new javax.swing.ImageIcon("C:\\Users\\savio\\Desktop\\Cards\\200x295\\1624298-expressroid.jpg")); // NOI18N
        jButton35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton35MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton35MouseEntered(evt);
            }
        });

        jButton36.setIcon(new javax.swing.ImageIcon("C:\\Users\\savio\\Desktop\\Cards\\200x295\\Mensageiro_da_Paz.jpg")); // NOI18N
        jButton36.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton36MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton36MouseEntered(evt);
            }
        });

        jButton37.setIcon(new javax.swing.ImageIcon("C:\\Users\\savio\\Desktop\\Cards\\200x295\\1624306-falchionb.jpg")); // NOI18N
        jButton37.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton37MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton37MouseEntered(evt);
            }
        });

        jButton33.setIcon(new javax.swing.ImageIcon("C:\\Users\\savio\\Desktop\\Cards\\200x295\\1624295-exodiustheultimateforbiddenlord.jpg")); // NOI18N
        jButton33.setText("jButton1");
        jButton33.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton33MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton33MouseEntered(evt);
            }
        });

        jButton38.setIcon(new javax.swing.ImageIcon("C:\\Users\\savio\\Desktop\\Cards\\200x295\\5138517_t.jpg")); // NOI18N
        jButton38.setText("jButton1");
        jButton38.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton38MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton38MouseEntered(evt);
            }
        });

        jButton39.setIcon(new javax.swing.ImageIcon("C:\\Users\\savio\\Desktop\\Cards\\200x295\\TimeWiz.jpg")); // NOI18N
        jButton39.setText("jButton1");
        jButton39.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton39MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton39MouseEntered(evt);
            }
        });

        jButton34.setIcon(new javax.swing.ImageIcon("C:\\Users\\savio\\Desktop\\Cards\\200x295\\Dragão_branco_de_olhos_azuis.jpg")); // NOI18N
        jButton34.setText("jButton1");
        jButton34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton34MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton34MouseEntered(evt);
            }
        });

        jButton40.setIcon(new javax.swing.ImageIcon("C:\\Users\\savio\\Desktop\\Cards\\200x295\\obilesk.jpg")); // NOI18N
        jButton40.setText("jButton1");
        jButton40.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton40MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton40MouseEntered(evt);
            }
        });

        jButton41.setText("Cemiterio");
        jButton41.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton41MouseEntered(evt);
            }
        });

        jButton42.setText("0");
        jButton42.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton42MouseEntered(evt);
            }
        });

        jButton43.setText("Cemiterio");
        jButton43.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton43MouseEntered(evt);
            }
        });

        jButton44.setText("0");
        jButton44.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton44MouseEntered(evt);
            }
        });

        jLabel1.setText("YUGI");

        jLabel2.setText("KAIBA");

        jLabel3.setText("Pontos de Vida");

        jLabel4.setText("8000");

        jLabel5.setText("8000");

        jLabel6.setText("Pontos de Vida");

        jButton2.setText("Fim do turno");

        jButton5.setText("Proxima Fase");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jButton37, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jButton36, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jButton35, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(99, 99, 99)
                                        .addComponent(jButton41)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton42, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton5)
                                        .addGap(31, 31, 31)
                                        .addComponent(jButton2))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(68, 68, 68)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(29, 29, 29)
                                                .addComponent(jButton43)
                                                .addGap(28, 28, 28)
                                                .addComponent(jButton44, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel2)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jLabel6)
                                                        .addGap(41, 41, 41)
                                                        .addComponent(jLabel5)
                                                        .addGap(12, 12, 12))))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(50, 50, 50)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jButton38, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(jButton39, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(jButton34, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(jButton40, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel4))
                                .addGap(27, 27, 27)))))
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton38, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton39, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton34, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton40, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addGap(99, 99, 99)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton35, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton37, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton36, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton41, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton42, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton5))
                        .addGap(60, 60, 60)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton43, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton44, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton21MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton21MouseEntered
        // TODO add your handling code here:
        ImageIcon aux = (ImageIcon) jButton21.getIcon();
        Image img = aux.getImage();
        Image newimg;
        newimg = img.getScaledInstance(200, 295, Image.SCALE_SMOOTH);
        Icon icon = new ImageIcon(newimg);
        jButton1.setIcon(icon);
        String texto = "Carta\nSem descrição.";
        jTextArea1.setText(quebraLinha(texto));
    }//GEN-LAST:event_jButton21MouseEntered

    private void jButton32MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton32MouseEntered
        // TODO add your handling code here:
        ImageIcon aux = (ImageIcon) jButton32.getIcon();
        Image img = aux.getImage();
        Image newimg;
        newimg = img.getScaledInstance(200, 295, Image.SCALE_SMOOTH);
        Icon icon = new ImageIcon(newimg);
        jButton1.setIcon(icon);
        String texto = "Carta\nSem descrição.";
        jTextArea1.setText(quebraLinha(texto));
    }//GEN-LAST:event_jButton32MouseEntered

    private void jButton35MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton35MouseEntered
        // TODO add your handling code here:
        ImageIcon aux = (ImageIcon) jButton35.getIcon();
        Image img = aux.getImage();
        Image newimg;
        newimg = img.getScaledInstance(200, 295, Image.SCALE_SMOOTH);
        Icon icon = new ImageIcon(newimg);
        jButton1.setIcon(icon);
        String texto = "Carta\nSem descrição.";
        jTextArea1.setText(quebraLinha(texto));
    }//GEN-LAST:event_jButton35MouseEntered

    private void jButton36MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton36MouseEntered
        // TODO add your handling code here:
        ImageIcon aux = (ImageIcon) jButton36.getIcon();
        Image img = aux.getImage();
        Image newimg;
        newimg = img.getScaledInstance(200, 295, Image.SCALE_SMOOTH);
        Icon icon = new ImageIcon(newimg);
        jButton1.setIcon(icon);
        String texto = "Carta\nSem descrição.";
        jTextArea1.setText(quebraLinha(texto));
    }//GEN-LAST:event_jButton36MouseEntered

    private void jButton37MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton37MouseEntered
        // TODO add your handling code here:
        ImageIcon aux = (ImageIcon) jButton37.getIcon();
        Image img = aux.getImage();
        Image newimg;
        newimg = img.getScaledInstance(200, 295, Image.SCALE_SMOOTH);
        Icon icon = new ImageIcon(newimg);
        jButton1.setIcon(icon);
        String texto = "Carta\nSem descrição.";
        jTextArea1.setText(quebraLinha(texto));
    }//GEN-LAST:event_jButton37MouseEntered

    private void jButton33MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton33MouseEntered
        ImageIcon aux = (ImageIcon) jButton33.getIcon();
        Image img = aux.getImage();
        Image newimg;
        newimg = img.getScaledInstance(200, 295, Image.SCALE_SMOOTH);
        Icon icon = new ImageIcon(newimg);
        jButton1.setIcon(icon);
        String texto = "Exxod, Mestre da Guarda\nEfeito: Esta carta não pode ser invocada normalmente ou virado para baixo."
                + " Esse monstro não pode ser invocado especialmente exceto sacrificando 1 monstro do seu lado do campo que tenha \" Esfinge \" no nome. Enquanto esta carta estiver de face para cima no campo, uma vez por turno invoque virando 1 monstro atributo TERRA e imponha 1000 pontos de dano aos pontos de vida de seu oponente.";
        jTextArea1.setText(quebraLinha(texto));

    }//GEN-LAST:event_jButton33MouseEntered

    private void jButton38MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton38MouseEntered
        // TODO add your handling code here:
        ImageIcon aux = (ImageIcon) jButton38.getIcon();
        Image img = aux.getImage();
        Image newimg;
        newimg = img.getScaledInstance(200, 295, Image.SCALE_SMOOTH);
        Icon icon = new ImageIcon(newimg);
        jButton1.setIcon(icon);
        String texto = "Mago Negro\nO mago definitivo em termos de ataque e defesa.";
        jTextArea1.setText(quebraLinha(texto));
    }//GEN-LAST:event_jButton38MouseEntered

    private void jButton39MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton39MouseEntered
        // TODO add your handling code here:
        ImageIcon aux = (ImageIcon) jButton39.getIcon();
        Image img = aux.getImage();
        Image newimg;
        newimg = img.getScaledInstance(200, 295, Image.SCALE_SMOOTH);
        Icon icon = new ImageIcon(newimg);
        jButton1.setIcon(icon);
        String texto = "Mago do tempo\nUma vez por turno você pode lançar uma moeda e escolher cara ou coroa. Se você ganhar, destrua todos os monstros que seu oponente controla."
                + "Se você perder, destrua todos os monstros que você controla e se isso acontecer, você sofre dano igual a metade do ATK total que esses mosntros destruídos tinham no campo.";
        jTextArea1.setText(quebraLinha(texto));
    }//GEN-LAST:event_jButton39MouseEntered

    private void jButton34MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton34MouseEntered
        // TODO add your handling code here:
        ImageIcon aux = (ImageIcon) jButton34.getIcon();
        Image img = aux.getImage();
        Image newimg;
        newimg = img.getScaledInstance(200, 295, Image.SCALE_SMOOTH);
        Icon icon = new ImageIcon(newimg);
        jButton1.setIcon(icon);
        String texto = "Dragão Branco de Olhos Azuis\nEste dragão lendário é uma poderosa máquina de destruição."
                + "Praticamente invencível, muito poucos enfrentaram esta magnífica criatura sobreviveram para contar a história.";
        jTextArea1.setText(quebraLinha(texto));
    }//GEN-LAST:event_jButton34MouseEntered

    private void jButton40MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton40MouseEntered
        // TODO add your handling code here:
        ImageIcon aux = (ImageIcon) jButton40.getIcon();
        Image img = aux.getImage();
        Image newimg;
        newimg = img.getScaledInstance(200, 295, Image.SCALE_SMOOTH);
        Icon icon = new ImageIcon(newimg);
        jButton1.setIcon(icon);
        String texto = "Obelisco, o Atormentador\nRequer 3 Tributos para ser Invocado por Invocação-Normal (não pode ser Baixado Normalmente)."
                + " A Invocação-Normal deste card não pode ser negada."
                + " Quando Invocado por Invocação-Normal, cards e efeitos não podem ser ativados."
                + " Não pode ser alvo de Magias, Armadilhas ou efeitos de card."
                + " Durante a Fase Final, se este card foi Invocado por Invocação-Especial: envie-o para o Cemitério."
                /*+ " Você pode oferecer 2 monstros como Tributo; destrua todos os monstros que seu oponente controla."
                + " Este card não pode declarar um ataque no turno em que este efeito for ativado."*/;
        jTextArea1.setText(quebraLinha(texto));
    }//GEN-LAST:event_jButton40MouseEntered

    private void jButton41MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton41MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton41MouseEntered

    private void jButton42MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton42MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton42MouseEntered

    private void jButton43MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton43MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton43MouseEntered

    private void jButton44MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton44MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton44MouseEntered

    private void jButton33MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton33MouseClicked
        // TODO add your handling code here:
        ImageIcon aux = (ImageIcon) jButton33.getIcon();
        Image img = aux.getImage();
        Image newimg;
        newimg = img.getScaledInstance(105, 128, Image.SCALE_SMOOTH);
        Icon icon = new ImageIcon(newimg);
        jButton16.setIcon(icon);
        
        jButton33.setIcon(RetirarCarta("Back-TF-EN-VG.png"));

    }//GEN-LAST:event_jButton33MouseClicked

    private void jButton38MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton38MouseClicked
        ImageIcon aux = (ImageIcon) jButton38.getIcon();
        Image img = aux.getImage();
        Image newimg;
        newimg = img.getScaledInstance(105, 128, Image.SCALE_SMOOTH);
        Icon icon = new ImageIcon(newimg);
        jButton15.setIcon(icon);
        
        jButton38.setIcon(RetirarCarta("Back-TF-EN-VG.png"));
        
    }//GEN-LAST:event_jButton38MouseClicked

    private void jButton39MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton39MouseClicked
        // TODO add your handling code here:
        ImageIcon aux = (ImageIcon) jButton39.getIcon();
        Image img = aux.getImage();
        Image newimg;
        newimg = img.getScaledInstance(105, 128, Image.SCALE_SMOOTH);
        Icon icon = new ImageIcon(newimg);
        jButton14.setIcon(icon);
        
        jButton39.setIcon(RetirarCarta("Back-TF-EN-VG.png"));

    }//GEN-LAST:event_jButton39MouseClicked

    private void jButton34MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton34MouseClicked
        // TODO add your handling code here
        ImageIcon aux = (ImageIcon) jButton34.getIcon();
        Image img = aux.getImage();
        Image newimg;
        newimg = img.getScaledInstance(105, 128, Image.SCALE_SMOOTH);
        Icon icon = new ImageIcon(newimg);
        jButton13.setIcon(icon);
        
        jButton34.setIcon(RetirarCarta("Back-TF-EN-VG.png"));

    }//GEN-LAST:event_jButton34MouseClicked

    private void jButton40MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton40MouseClicked
        // TODO add your handling code here:
        ImageIcon aux = (ImageIcon) jButton40.getIcon();
        Image img = aux.getImage();
        Image newimg;
        newimg = img.getScaledInstance(105, 128, Image.SCALE_SMOOTH);
        Icon icon = new ImageIcon(newimg);
        jButton12.setIcon(icon);
        
        jButton40.setIcon(RetirarCarta("Back-TF-EN-VG.png"));

    }//GEN-LAST:event_jButton40MouseClicked

    private void jButton21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton21MouseClicked
        // TODO add your handling code here:
        ImageIcon aux = (ImageIcon) jButton21.getIcon();
        Image img = aux.getImage();
        Image newimg;
        newimg = img.getScaledInstance(105, 128, Image.SCALE_SMOOTH);
        Icon icon = new ImageIcon(newimg);
        jButton26.setIcon(icon);
        
        jButton21.setIcon(RetirarCarta("Back-TF-EN-VG.png"));

    }//GEN-LAST:event_jButton21MouseClicked

    private void jButton37MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton37MouseClicked
        // TODO add your handling code here:
        ImageIcon aux = (ImageIcon) jButton37.getIcon();
        Image img = aux.getImage();
        Image newimg;
        newimg = img.getScaledInstance(105, 128, Image.SCALE_SMOOTH);
        Icon icon = new ImageIcon(newimg);
        jButton25.setIcon(icon);
        
        jButton37.setIcon(RetirarCarta("Back-TF-EN-VG.png"));

    }//GEN-LAST:event_jButton37MouseClicked

    private void jButton32MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton32MouseClicked
        // TODO add your handling code here:
        ImageIcon aux = (ImageIcon) jButton32.getIcon();
        Image img = aux.getImage();
        Image newimg;
        newimg = img.getScaledInstance(105, 128, Image.SCALE_SMOOTH);
        Icon icon = new ImageIcon(newimg);
        jButton30.setIcon(icon);
        
        jButton32.setIcon(RetirarCarta("Back-TF-EN-VG.png"));

    }//GEN-LAST:event_jButton32MouseClicked

    private void jButton35MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton35MouseClicked
        // TODO add your handling code here:
        ImageIcon aux = (ImageIcon) jButton35.getIcon();
        Image img = aux.getImage();
        Image newimg;
        newimg = img.getScaledInstance(105, 128, Image.SCALE_SMOOTH);
        Icon icon = new ImageIcon(newimg);
        jButton22.setIcon(icon);
        
        jButton35.setIcon(RetirarCarta("Back-TF-EN-VG.png"));

    }//GEN-LAST:event_jButton35MouseClicked

    private void jButton36MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton36MouseClicked
        // TODO add your handling code here:
        ImageIcon aux = (ImageIcon) jButton36.getIcon();
        Image img = aux.getImage();
        Image newimg;
        newimg = img.getScaledInstance(105, 128, Image.SCALE_SMOOTH);
        Icon icon = new ImageIcon(newimg);
        jButton29.setIcon(icon);
        
        jButton36.setIcon(RetirarCarta("Back-TF-EN-VG.png"));

    }//GEN-LAST:event_jButton36MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ModeloBackup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModeloBackup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModeloBackup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModeloBackup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModeloBackup().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton42;
    private javax.swing.JButton jButton43;
    private javax.swing.JButton jButton44;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
    String quebraLinha(String texto) {
        if (texto.length() > 47) {
            int position = 47;
            while (texto.charAt(position) != ' ') {
                position--;
            }
            return (texto.substring(0, position) + "\n" + quebraLinha(texto.substring(position, texto.length())));
            } else {
            return texto;
        }

    }

    Icon RetirarCarta(String end){
        ImageIcon aux = new javax.swing.ImageIcon("C:\\Users\\savio\\Desktop\\Cards\\200x295\\"+end);
        Image img = aux.getImage();
        Image newimg = img.getScaledInstance(105, 128, Image.SCALE_SMOOTH);
        Icon icon = new ImageIcon(newimg);
        return icon;
    }
    
    boolean efeitoArmadilha(Carta carta, String key){
        if(ar.size() > 0){
            System.out.println("Você ativou a minha armadilha");
            JOptionPane.showMessageDialog(rootPane, "Você ativou a minha armadilha");

            //Ativando uma carta armadilha        
            String armadilha = JOptionPane.showInputDialog("Escolha a armadilha");
            CartaArmadilha cartaArmadilha = (CartaArmadilha) ar.get(armadilha);
            JOptionPane.showMessageDialog(rootPane, "Escolhida: "+cartaArmadilha.getNome());
            
            switch (cartaArmadilha.getEspecialidade()) {
                case 1:{
                    if(carta.tipo == Carta.tipoC.MONSTRO){
                        CartaMonstro cartaMonstro = (CartaMonstro) m.get(key);
                        System.out.println("Monstro destruído: "+cartaMonstro.getNome());
                        cemiterio.add((Carta) m.get(key));
                        m.remove(key);
                    }else if(carta.tipo == Carta.tipoC.MAGICA){
                        CartaMagica cartaMagica = (CartaMagica) am.get(key);
                        System.out.println("Magica destruída: "+cartaMagica.getNome());
                        cemiterio.add((Carta) am.get(key));
                        am.remove(key);
                    }
                break;
                }case 2:{
                        System.out.println("Todos os monstros foram destruídos.");
                        m.clear();                    
                break;
                }
            }
            cemiterio.add((Carta) ar.get(armadilha));            
            ar.remove(armadilha);
            return true;
        }
        return false;
    }

    void mandarCemiterio(Carta carta){
        m.put(m.size(), carta);
    }
}
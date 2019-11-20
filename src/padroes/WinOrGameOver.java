package padroes;

import campominado.IntroductionCM;
import campominado.RecordesCM;
import campominado.ScoreCM;
import componentes.Btn;
import componentes.Frame;
import componentes.Lb;
import componentes.Pn;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.Border;
import wordpuzzle.IntroductionWP;
import wordpuzzle.RecordesWP;
import wordpuzzle.ScoreWP;

public class WinOrGameOver extends Frame{
    private int nivel;
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    private Frame game;
    public WinOrGameOver(Frame game){
        setSize(486,294);
        setLocationRelativeTo(null);
        this.game = game;
    }
    
    private Pn pnGO;
    public void addGameOverCM(){
        int backPos[] = {2,2,480,288}; int rectPos[] = {100,143,325,90};
        int btnHome[] = {84,170,142,53}; int btnRanking[] = {271,170,142,53};
        ImageIcon btn_home[] = {im.addImagem("btn_home_go_cm_wp"),im.addImagem("btn_home_go_cm_wp_t"),im.addImagem("btn_home_go_cm_wp_p")};
        ImageIcon btn_ranking[] = {im.addImagem("btn_ranking_go_cm_wp"),im.addImagem("btn_ranking_go_cm_wp_t"),im.addImagem("btn_ranking_go_cm_wp_p")};
        Component cp[] = {
            new Btn(btn_home, btnHome, new EventBtns(2)),
            new Btn(btn_ranking, btnRanking, new EventBtns(1)),
            new Lb(im.addImagem("rect_black"),rectPos),
            new Lb(im.addGif("go"), backPos)
        };
        pnGO = new Pn(backPos, cp);
        Border b = BorderFactory.createLineBorder(Color.white, 4);
        pnGO.setBorder(b);
        add(pnGO);
    }
    
    public class EventBtns implements ActionListener{
        private int esc;
        public EventBtns(int esc){this.esc = esc;}
        @Override
        public void actionPerformed(ActionEvent ae) {
            game.dispose();dispose();
            if(esc==2){ //
                IntroductionCM cm = new IntroductionCM(); cm.intro(); cm.show();
            }else if(esc==1){
                RecordesCM r = new RecordesCM(nivel);
                r.tabela();r.show();
            }else if(esc==4){
                IntroductionWP wp = new IntroductionWP(); wp.intro(); wp.show();
            }else{
                RecordesWP r = new RecordesWP(nivel);
                r.tabela();r.show();
            }
        }
    }
    
    private Pn pnWin;
    public void addWinCMWP(int jogo){
        int backPos[] = {2,2,480,288}; int titlePos[] = {146,49,216,52};
        int btnHome[] = {90,161,142,53}; int btnRanking[] = {290,161,142,53};
        ImageIcon btn_home[] = {im.addImagem("btn_home_go_cm_wp"),im.addImagem("btn_home_go_cm_wp_t"),im.addImagem("btn_home_go_cm_wp_p")};
        ImageIcon btn_ranking[] = {im.addImagem("btn_ranking_go_cm_wp"),im.addImagem("btn_ranking_go_cm_wp_t"),im.addImagem("btn_ranking_go_cm_wp_p")};
        Component cp[] = {
            new Btn(btn_home, btnHome, new EventBtns(jogo)),
            new Btn(btn_ranking, btnRanking, new EventBtns(jogo-1)),
            new Lb(im.addImagem("title_win_cm_wp"), titlePos),
            new Lb(im.addGif("back_win_cm_wp"), backPos)
        };
        pnWin = new Pn(backPos, cp);
        Border b = BorderFactory.createLineBorder(Color.white, 4);
        pnWin.setBorder(b);
        pnWin.setBackground(Color.black);
        add(pnWin);
    }
    
}

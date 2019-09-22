package jogodavelha;
import padroes.IntroductionGame;
import componentes.Btn;
import componentes.Lb;
import componentes.Pn;
import componentes.Txt;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.Border;
public class IntroductionJDV extends IntroductionGame{

    public IntroductionJDV(int x, int y) {
        super(x, y);
        intro();
        setVisible(true);
    }
    @Override
    public void tutorial() {
    }
    
    private Pn pnEsc;
    private Txt jog1;
    private Txt jog2;
    private ImageIcon imBtn = new ImageIcon(getClass().getResource("start.png"));
    public void escJog(){
        pnIntro.setVisible(false);
        Font txt = new Font("Arial", Font.PLAIN, 20);
        Border b = BorderFactory.createLineBorder(Color.black, 3);
        int txtjog1[] = {50,260,200,40}; int txtjog2[] = {330,260,200,40};
        jog1 = new Txt(txtjog1, txt, Color.cyan, b); jog2 = new Txt(txtjog2, txt, Color.cyan, b);
        int lbjog1[] = {30,70,250,100}; int lbjog2[] = {310,70,250,100};
        int lbn1[] = {50,210,200,40}; int lbn2[] = {330,210,200,40};
        int lbx[] = {96,330,100,100}; int lby[] = {376,330,100,100}; int btnP[] = {460,450,100,100};
        Font jog = new Font("Arial", Font.PLAIN, 30);
        Font lb = new Font("Arial", Font.PLAIN, 20);
        Font xo = new Font("Arial", Font.PLAIN, 70);
        Component cp[] = {
            new Lb("NOME", lb, lbn1, Color.black, null),
            new Lb("NOME", lb, lbn2, Color.black, null),
            new Lb("X", xo, lbx, Color.white, b),
            new Lb("O", xo, lby, Color.white, b),
            new Lb("Jogador 01", jog, lbjog1, Color.black, b),
            new Lb("Jogador 02", jog, lbjog2, Color.black, b),
            new Btn(imBtn, btnP, null, false, null),
            jog1, jog2
        };
        int pnEscP[] = {0,0,600,600};
        pnEsc = new Pn(pnEscP, cp);
        pnEsc.setBackground(Color.darkGray);
        add(pnEsc);
    }
    
    private Pn pnIntro;
    private final ImageIcon imFundo = new ImageIcon(getClass().getResource("fundo.png"));
    @Override
    public void intro(){
        int btnClassicP[] = {175,200,250,80}; int btnPersoP[] = {175,290,250,80}; int btnTutP[] = {175,380,250,80};
        int lbFundoP[] = {0,0,600,551}; int lbtitleP[] = {125,20,350,80};
        Font lb = new Font("Arial", Font.PLAIN, 40);
        Font btn = new Font("Arial", Font.PLAIN, 35);
        Border b = BorderFactory.createLineBorder(Color.black, 3);
        Btns b1 = new Btns(1); Btns b2 = new Btns(2); Btns b3 = new Btns(3);
        Component cp[] = {
            new Lb("JOGO DA VELHA", lb, lbtitleP, Color.white, b),
            new Btn("Classic", btn, Color.green, Color.black, btnClassicP, b, true, false, b1),
            new Btn("Personalizado", btn, Color.green, Color.black, btnPersoP, b, true, false, b2),
            new Btn("Tutorial", btn, Color.green, Color.black, btnTutP, b, true, false, b3),
            new Lb(imFundo, lbFundoP)
        };
        int pnIntroP[] = {0,0,600,600};
        pnIntro = new Pn(pnIntroP, cp, Color.lightGray);
        
        add(pnIntro);
    }
    private class Btns implements ActionListener{
        private int n;
        public Btns(int n) {this.n = n;}
        @Override
        public void actionPerformed(ActionEvent ae) {
            escJog();
            switch (n) {
                case 1:escJog();break;
                case 2:escJog();break;
                case 3:tutorial();break;
                default:break;
            }
        }
        
    }
}

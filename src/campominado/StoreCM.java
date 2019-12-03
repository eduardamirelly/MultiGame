package campominado;
import componentes.Btn;
import componentes.Lb;
import componentes.Pn;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import padroes.ItemsTela;
import padroes.Store;
import user.Conta;
import user.User;
public class StoreCM extends Store{
    private ArrayList<ImageIcon[]> btn_niveis = new ArrayList<>();
    private ArrayList<ImageIcon> btn_bombs = new ArrayList<>();
    private ArrayList<ImageIcon> btn_flags = new ArrayList<>();
    
    public StoreCM(){
        btnNiveis(ims[user.getEmUsoCM().get(2).indexOf("1")]);
        btnNiveis(ims[user.getEmUsoCM().get(2).indexOf("1")], "t");
        btnNiveis(ims[user.getEmUsoCM().get(2).indexOf("1")], "p");
        btnBombs(ims[user.getEmUsoCM().get(1).indexOf("1")]);
        btnFlags(ims[user.getEmUsoCM().get(0).indexOf("1")]);
    }
    
    public void btnFlags(String esc){
        btn_flags.clear();
        ImageIcon flagE = im.addImagem("flagF_"+esc);ImageIcon flagM = im.addImagem("flagM_"+esc);ImageIcon flagH = im.addImagem("flagD_"+esc);
        btn_flags.add(flagE);btn_flags.add(flagM);btn_flags.add(flagH);
    }
    
    public void btnBombs(String esc){
        btn_bombs.clear();
        ImageIcon bomb = im.addImagem("bomb_cm_easy_"+esc);ImageIcon bomb_m = im.addImagem("bomb_cm_medium_"+esc);ImageIcon bomb_h = im.addImagem("bomb_cm_hard_"+esc);
        btn_bombs.add(bomb); btn_bombs.add(bomb_m);btn_bombs.add(bomb_h);
    }
    
    public void btnNiveis(String esc){
        btn_niveis.clear();
        ImageIcon btn_niveis[] = {im.addImagem("btn_cm_easy_"+esc),im.addImagem("btn_cm_medium_"+esc),im.addImagem("btn_cm_hard_"+esc)};
        this.btn_niveis.add(btn_niveis);
    }
    public void btnNiveis(String esc, String complemento){
        ImageIcon btn_niveis[] = {im.addImagem("btn_cm_easy_"+complemento+"_"+esc),im.addImagem("btn_cm_medium_"+complemento+"_"+esc),im.addImagem("btn_cm_hard_"+complemento+"_"+esc)};
        this.btn_niveis.add(btn_niveis);
    }

    public ArrayList<ImageIcon> getBtn_flags() {
        return btn_flags;
    }
    public ArrayList<ImageIcon> getBtn_bombs() {
        return btn_bombs;
    }
    public ArrayList<ImageIcon[]> getBtn_niveis() {
        return btn_niveis;
    }
    public void definirTelaVoltar(int x){
        it.setTelaAntIntro(x);
    }
    private ItemsTela it = new ItemsTela();
    private Pn pnIntro;
    private Btn btnButtons;
    private Btn btnFlags;
    private Btn btnBombs;
    @Override
    public void intro(){
        int backPos[] = {0,0,1200,700}; int btnFlagPos[] = {150,38,255,72}; int btnButtonPos[] = {834,38,255,72};
        int btnBombsPos[] = {487,38,255,72};
        ImageIcon btn_flag[] = {im.addImagem("btn_flags_cm"),im.addImagem("btn_flags_cm_t"),im.addImagem("btn_flags_cm_p")};
        ImageIcon btn_bombs[] = {im.addImagem("btn_bombs_cm"),im.addImagem("btn_bombs_cm_t"),im.addImagem("btn_bombs_cm_p")};
        ImageIcon btn_buttons[] = {im.addImagem("btn_buttons_cm"),im.addImagem("btn_buttons_cm_t"),im.addImagem("btn_buttons_cm_p")};
        if(it.getTelaAntIntro()==0){it.setTelaAntIntro(1);}else{it.setTelaAntIntro(5);}
        btnButtons = new Btn(btn_buttons, btnButtonPos, new EventBtnsIntro(3));
        btnFlags = new Btn(btn_flag, btnFlagPos, new EventBtnsIntro(1));
        btnBombs = new Btn(btn_bombs, btnBombsPos, new EventBtnsIntro(2));
        Component cp[] = {
            it.btnClose(), it.returnGames(this),
            btnButtons, btnFlags, btnBombs,
            new Lb(im.addImagem("back_store"), backPos)
        };
        pnIntro = new Pn(backPos, cp);
        add(pnIntro);
    }
    public void loja(Pn pn){
        int backPos[] = {0,0,1200,700};
        Component cp[] = {
            it.btnClose(), it.returnGames(this),
            btnButtons, btnFlags, btnBombs,
            pn
        };
        pnIntro = new Pn(backPos, cp);
        add(pnIntro);
    }
    
    public class EventBtnsIntro implements ActionListener{
        private int btn;
        public EventBtnsIntro(int btn){
            this.btn = btn;
        }
        @Override
        public void actionPerformed(ActionEvent ae) {
            pnIntro.setVisible(false);
            btnButtons.setIcon(im.addImagem("btn_buttons_cm"));
            btnBombs.setIcon(im.addImagem("btn_bombs_cm"));
            btnFlags.setIcon(im.addImagem("btn_flags_cm"));
            if(btn==1){
                loja(addStoreFlags());
            }else if(btn==2){
                loja(addStoreBombs());
            }else{
                loja(addStoreButtons());
            }
        }
    }
    
    private Pn pnItemsBombs;
    public Pn addStoreBombs(){
        btnBombs.setIcon(btnBombs.getRolloverIcon());
        pnItemsBombs = new Pn(); pnItemsBombs.setLayout(null); pnItemsBombs.setBounds(0, 0, 1200, 700);
        for(int i = 0; i<10; i++){
            pnItemsBombs.add(new Lb(im.addImagem("bomb_cm_easy_"+ims[i]), posBtns[i]));
        }
        addBtnBasic(pnItemsBombs, 1);
        return pnItemsBombs;
    }
    
    private Pn pnItemsFlags;
    public Pn addStoreFlags(){
        btnFlags.setIcon(btnFlags.getRolloverIcon());
        pnItemsFlags = new Pn(); pnItemsFlags.setLayout(null); pnItemsFlags.setBounds(0, 0, 1200, 700);
        for(int i = 0; i<10; i++){
            pnItemsFlags.add(new Lb(im.addImagem("flagF_"+ims[i]), posBtns[i]));
        }
        addBtnBasic(pnItemsFlags, 0);
        return pnItemsFlags;
    }
    
    private Pn pnItemsButtons;
    public Pn addStoreButtons(){
        btnButtons.setIcon(btnButtons.getRolloverIcon());
        pnItemsButtons = new Pn(); pnItemsButtons.setLayout(null); pnItemsButtons.setBounds(0, 0, 1200, 700);
        for(int i = 0; i<10; i++){
            pnItemsButtons.add(new Lb(im.addImagem("btn_cm_easy_"+ims[i]), posBtns[i]));
        }
        addBtnBasic(pnItemsButtons, 2);
        return pnItemsButtons;
    }
    
    private Btn btnsComprado[][] = new Btn[3][10];
    private Btn btnsUso[][] = new Btn[3][10];
    public void addBtnBasic(Pn pn, int tipo){
        int backPos[] = {0,0,1200,700};
        for(int i = 0; i<10; i++){
            if(user.getStoreCM().get(tipo).get(i).equals("1")){
                btnsComprado[tipo][i] = new Btn(im.addImagem("comprado_store"), posBtnsComprado[i], new EventComprar(i, tipo));
            }else{
                btnsComprado[tipo][i] = new Btn(im.addImagem("valor_btn_cm_store"), posBtnsComprado[i], new EventComprar(i, tipo));
            }
            if(user.getEmUsoCM().get(tipo).get(i).equals("1")){
                btnsUso[tipo][i] = new Btn(im.addImagem("btn_uso_store"), posBtnsUso[i], new EventUso(i, tipo));
            }else{
                btnsUso[tipo][i] = new Btn(im.addImagem("sem_uso_btn_cm_store"), posBtnsUso[i], new EventUso(i, tipo));
            }
            pn.add(btnsComprado[tipo][i]);
            pn.add(btnsUso[tipo][i]);
        }
        pn.add(new Lb(im.addImagem("back_store"), backPos));
    }
    
    public class EventComprar implements ActionListener{
        private int btn; private int tipo;
        public EventComprar(int btn, int tipo){
            this.btn = btn; this.tipo = tipo;
        }
        @Override
        public void actionPerformed(ActionEvent ae) {
            if(user.getStoreCM().get(tipo).get(btn).equals("0")&&user.getMoedas()-1000>=0){
                user.getStoreCM().get(tipo).set(btn, "1");
                user.setMoedas(-1000);
                btnsComprado[tipo][btn].setIcon(im.addImagem("comprado_store"));
                Conta c = new Conta(user); c.gravar(); User.setUser(user);
            }else if(user.getStoreCM().get(tipo).get(btn).equals("1")){
                System.out.println("Item já foi comprado");
            }else{
                System.out.println("Dinheiro insuficiente");
            }
        }
    }
    
    public class EventUso implements ActionListener{
        public int btn; private int tipo;
        public EventUso(int btn, int tipo){
            this.btn = btn; this.tipo = tipo;
        }
        @Override
        public void actionPerformed(ActionEvent ae) {
            if(user.getEmUsoCM().get(tipo).get(btn).equals("0")&&user.getStoreCM().get(tipo).get(btn).equals("1")){
                btnsUso[tipo][user.getEmUsoCM().get(tipo).indexOf("1")].setIcon(im.addImagem("sem_uso_btn_cm_store"));
                user.getEmUsoCM().get(tipo).set(user.getEmUsoCM().get(tipo).indexOf("1"), "0");
                user.getEmUsoCM().get(tipo).set(btn, "1");
                btnsUso[tipo][btn].setIcon(im.addImagem("btn_uso_store"));
                btnNiveis(ims[btn]);
                btnNiveis(ims[btn], "t");
                btnNiveis(ims[btn], "p");
                btnBombs(ims[btn]);
                btnFlags(ims[btn]);
                Conta c = new Conta(user); c.gravar(); User.setUser(user);
            }
        }
    }
    
}

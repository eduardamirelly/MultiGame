package caçapalavras;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
public class Palavras {
    private final Random sortear = new Random();
    private final ArrayList<String[]> words = new ArrayList<String[]>(); //Pavras do arquivo txt
    private final String[] alfabeto = {"A","B","C","D","E","F","G","H","I","J","K","L","M",
                                 "N","O","P","Q","R","S","T","U","V","W","X","Y","Z",};
    private ArrayList<String[]> mapawords = new ArrayList<String[]>(); //Mapear palavras na tela, para que não sejam repetidas.
    private int x, y;
    private String[][] m; //Matriz de letras
    private int[][] m2; //Mapear palavras
    
    public Palavras(int n){
        niveis(n);
        m = new String[x][y];
        m2 = new int[x][y];
        //letras();
        addWord(3, 2, words.get(0));        /*
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(m2[i][j]);
            }
            System.out.println();
        }*/
    }
    
    public void addWord(int orientacao, int invertida, String[] palavra){
        int cont = (invertida==1)?0:palavra.length-1; 
        int tamWord = palavra.length;
        int linha = 0, coluna = 0;
        if(orientacao==1){
            linha = sortear.nextInt(x);
            coluna = lineAndColumn(tamWord);
            for(int i = coluna; i<coluna+palavra.length; i++){
                m2[linha][i] = 1;
                m[linha][i] = palavra[cont].toUpperCase();
                if(invertida==1){cont++;}else{cont--;}
            }
        }else if(orientacao==2){
            linha = lineAndColumn(tamWord);
            coluna = sortear.nextInt(x);
            for(int i = linha; i<linha+palavra.length; i++){
                m2[i][coluna] = 1;
                m[i][coluna] = palavra[cont].toUpperCase();
                if(invertida==1){cont++;}else{cont--;}
            }
        }else if(orientacao==3){
            linha = coluna = lineAndColumn(tamWord);
            for(int i = linha; i<linha+palavra.length; i++){
                m2[i][i] = 1;
                m[i][i] = palavra[cont].toUpperCase();
                if(invertida==1){cont++;}else{cont--;}
            }
        }else if(orientacao==4){
            linha = lineAndColumn(tamWord);
            
        }
    }
    
    public int lineAndColumn(int tamWord){
        while(true){
            int sorteado = sortear.nextInt(x);
            if(sorteado+tamWord<x){
                return sorteado;
            }
        }
    }
    
    private void niveis(int n){
        switch(n){
            case 1: x = y = 8; break;
            case 2: x = y = 10; break;
            case 3: x = y = 12; break;
            default: break;
        }
        arqGet();
    }
    
    public void arqGet(){ //Pegar palavras do arquivo txt.
        try{
            FileInputStream f = new FileInputStream("questions/caçapalavras/words.txt");
            InputStreamReader i = new InputStreamReader(f);
            BufferedReader b = new BufferedReader(i);
            String linha;
            while((linha = b.readLine())!=null){
                String[] p = linha.split("");
                words.add(p);
            }
            b.close();
            f.close();
        }catch(FileNotFoundException e){}catch(IOException e){}
    }
    public void letras(){
        for(int i = 0; i<x ; i++){
            for(int j = 0; j<y; j++){
                m[i][j] = alfabeto[sortear.nextInt(8)];
            }
        }
    }
    public String getM(int i, int j) {
        return m[i][j];
    }
}

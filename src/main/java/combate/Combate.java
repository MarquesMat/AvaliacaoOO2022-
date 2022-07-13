package combate;

import ficha.Personagem;
import ficha.Dados;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Combate {
    public static int dist;
    
    public static void distancia() {
        switch(dist) {
            case 1 -> System.out.println("\n---Voce esta de frente para o seu inimigo---");
            case 2 -> System.out.println("\n---Voce esta proximo do seu inimigo---");
            default -> System.out.println("\n---Voce esta distante do seu inimigo---");
        }
    }
    
    public static boolean turnoInimigo() {
        int padrao = 1, mov = 1;
        boolean atacar = false;
        while(padrao+mov > 0) {
            if(dist == 1 && padrao > 0) {
                atacar = true;
                padrao = 0;
                mov = 0;
                System.out.println("\n---O inimigo atacou!---");
            } else if(dist > 1 && mov > 0) {
                mov = 0;
                dist--;
                System.out.println("\n---O inimigo esta se aproximando---");
            } else if(dist > 1 && padrao > 0) {
                padrao = 0;
                dist--;
                System.out.println("\n---O inimigo esta se aproximando---");
            }
        }
        return atacar;
    }
    
    public static int ataque(int atq, int def, int pv, int dado, int margem, int critico) {
        int x = 1, dano;
        int golpe = Dados.dado(1, 20);
        if(golpe >= margem) {
            System.out.println("\n---Acerto critico!---");
            x = critico;
            dano = Dados.dado(x, dado);
            return (pv-dano);
        }
        if(golpe+atq >= def) {
            System.out.println("\n---Ataque certeiro!---");
            dano = Dados.dado(x, dado);
            return (pv-dano);
        }
        System.out.println("\n---Ataque bloqueado!---");
        return pv;
    }
    
    public static void opcoes(int padrao) {
        System.out.println("\nOPCOES:");
        System.out.println("1 -> AVANCAR");
        if(dist == 1) System.out.println("\n---Voce esta de frente para o seu inimigo---");
        System.out.println("2 -> RECUAR");
        if(dist == 3) System.out.println("\n---Voce esta no limite da distancia---");
        System.out.println("3 -> ATACAR");
        if(padrao == 0) System.out.println("\n---Voce nao possui mais acao padrao---");
        System.out.println("4 -> USAR PODER");
        if(padrao == 0) System.out.println("\n---Voce nao possui mais acao padrao---");
        System.out.print("ESCOLHA UMA OPCAO: ");
    }
    
    public static void luta(Inimigo inimigo) {
        Scanner teclado = new Scanner(System.in);
        int pvH = Personagem.getPv(), manaH = Personagem.getMana(), defH = Personagem.getDefesa(), deslocamentoH = Personagem.getDeslocamento(), atqH = Personagem.getAtq();
        int pvV = inimigo.getPv(), defV = inimigo.getDefesa(), deslocamentoV = inimigo.getDeslocamento(), atqV = inimigo.getAtq();
        System.out.println("\n---Um "+inimigo.getNome()+" apareceu!---");
        System.out.println("---Role Iniciativa para tentar agir primeiro---");
        int iniH = Dados.dado(1, 20) + deslocamentoH;
        int iniV = Dados.dado(1, 20) + deslocamentoV;
        System.out.println("\nROLANDO OS DADOS...\n");
        System.out.println("\nSEU RESULTADO FOI "+iniH);
        System.out.println("O RESULTADO DO "+inimigo.getNome()+" FOI "+iniV);
        System.out.println("\n---O combate comecou!---");
        if(iniH >= iniV) System.out.println("\n---Voce ira agir primeiro---");
        else System.out.println("\n---"+inimigo.getNome()+" ira agir primeiro---");
        dist = Dados.dado(1, 3);
        int mov, padrao;
        do {
            distancia();
            if(iniH < iniV) {
                if (turnoInimigo());
                dist = 1;
                pvH = ataque(atqV, defH, pvH, inimigo.getArma().getDano(), inimigo.getArma().getMargem(), inimigo.getArma().getCritico());
            }
            
            mov = 1;
            padrao = 1;
            int op;
            do {
                try{
                    opcoes(padrao);
                    op = teclado.nextInt();
                }catch(InputMismatchException e){
                      System.out.println("\nOPCAO INVALIDA! DIGITE UMA OPCAO VALIDA.");
                      continue;}
   
                switch(op) {
                    case 1 -> {
                        if(dist>1 && mov+padrao>0) {
                            dist--;
                            if(mov==1) mov = 0;
                            else padrao = 0;
                            System.out.println("\n---Se aproximando do inimigo---");
                        } else System.out.println("\nOPCAO INVALIDA!");
                    }
                    case 2 -> {
                        if(dist<3 && mov+padrao>0) {
                            dist++;
                            if(mov==1) mov = 0;
                            else padrao = 0;
                            System.out.println("\n---Se afastando do inimigo---");
                        } else System.out.println("\nOPCAO INVALIDA!");
                    }
                    case 3 -> {
                        if(padrao>0 && Personagem.getArma().getAlcance()>=dist) {
                            pvV = ataque(atqH, defV, pvV, inimigo.getArma().getDano(), inimigo.getArma().getMargem(), inimigo.getArma().getCritico());
                            padrao = 0;
                        } else System.out.println("\nOPCAO INVALIDA!");
                    }
                    default -> {
                            System.out.println("\nOPCAO INVALIDA! DIGITE UMA OPCAO VALIDA.");}
                }
            } while(mov+padrao>0);
            distancia();
        
            if(iniH >= iniV) {
                if (turnoInimigo());
                dist = 1;
                pvH = ataque(atqV, defH, pvH, inimigo.getArma().getDano(), inimigo.getArma().getMargem(), inimigo.getArma().getCritico());
            }
        } while(pvH > 0 || pvV > 0);
        System.out.println("\n---FIM DO COMBATE---");
    }
}
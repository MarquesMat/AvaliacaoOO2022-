package ficha;

import racasClasses.BancoDeClasses;
import racasClasses.BancoDeRacas;
import equipamentos.BancoDeArmaduras;
import equipamentos.BancoDeArmas;
import poderes.Poderes;
import combate.BancoDeInimigos;
import combate.Combate;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class TrabalhoPOO {
    public static void imprimirFichaProvisoria() {
        System.out.print("\nNome: "+Personagem.getNome());
        System.out.print("      Raca: "+Personagem.getRaca().getNome());
        System.out.print("      Classe: "+Personagem.getClasse().getNome());
        System.out.println("      Nivel: "+Personagem.getNivel());
        Personagem.imprimeAtributos();
    }
    
    public static void imprimirFicha() {
        System.out.print("\n\nNome: "+Personagem.getNome());
        System.out.print("      Raca: "+Personagem.getRaca().getNome());
        System.out.print("      Classe: "+Personagem.getClasse().getNome());
        System.out.println("      Nivel: "+Personagem.getNivel());
        Personagem.imprimeAtributos();
        System.out.print("\nPV: "+Personagem.getPv());
        System.out.println("        Mana: "+Personagem.getMana());
        System.out.print("\nAtaque: "+Personagem.getAtq()+"     Defesa: "+Personagem.getDefesa());
        System.out.println("        Deslocamento: "+Personagem.getDeslocamento());
        System.out.println("Poderes: ");
        System.out.println(Poderes.mapPoderes.get(Personagem.getRaca().getPoder()));
        System.out.println(Poderes.mapPoderes.get(Personagem.getClasse().getPoder()));
    }
    
    public static <T extends Object> void gravarObjeto(String nomeArq, T objeto) {
        FileOutputStream fluxo;
        try {
            fluxo = new FileOutputStream(nomeArq);
            ObjectOutputStream objarq = new ObjectOutputStream(fluxo);
            objarq.writeObject(objeto);
            objarq.close();
        } catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        /*
        Clasees alteradas:
        Menu
        Personagem
        DadoInvalidoException
        */
        BancoDeRacas bancoDeRacas = new BancoDeRacas();
        BancoDeClasses bancoDeClasses = new BancoDeClasses();
        BancoDeArmaduras bancoDeArmaduras = new BancoDeArmaduras();
        BancoDeArmas bancoDeArmas = new BancoDeArmas();
        Poderes poderes = new Poderes();
        BancoDeInimigos bancoDeInimigos = new BancoDeInimigos();
        //os construtores preparam os dicionarios
        Personagem personagem = new Personagem();
        
        Menu.exec();
        Personagem.iniciar();
        
        gravarObjeto("raca.ser", Personagem.getRaca());
        gravarObjeto("classe.ser", Personagem.getClasse());
        gravarObjeto("atributos.ser", Personagem.getAtributos());
        try {
            if(Personagem.getAva().getMatricula() < 0) throw new AvaliacaoOO2022NaoInformadaException("\n---Preencha seu cadastro antes de continuar---");
            gravarObjeto("ava.ser", Personagem.getAva());
        } catch(AvaliacaoOO2022NaoInformadaException e) {
            System.out.println(e.getMessage());
            Menu.cadastro();
        }
        //o personagem só vai ser gravado depois que o atributo ava for gravado
        
        imprimirFichaProvisoria();
        Menu.exec2();
        Personagem.equipar();
        Personagem.poderRaca();
        imprimirFicha();
        
        gravarObjeto("personagem.ser", personagem);
        
        boolean vencer;
        if(Menu.encerrar()) return;
        do {
            vencer = Combate.luta(BancoDeInimigos.mapClasses.get(1));
            if(vencer) {
                Personagem.subirNivel();
                gravarObjeto("personagem.ser", personagem);
                System.out.println("---Parabens pela vitoria!---");
            }
            else System.out.println("---Tente de novo!---");
        } while(!vencer);        
        imprimirFicha();
        if(Menu.encerrar()) return;
        do {
            vencer = Combate.luta(BancoDeInimigos.mapClasses.get(2));
            if(vencer) {
                Personagem.subirNivel();
                gravarObjeto("personagem.ser", personagem);
                System.out.println("---Parabens pela vitoria!---");
            }
            else System.out.println("---Tente de novo!---");
        } while(!vencer);        
        imprimirFicha();
        if(Menu.encerrar()) return;
        do {
            vencer = Combate.luta(BancoDeInimigos.mapClasses.get(3));
            if(vencer) {
                Personagem.subirNivel();
                gravarObjeto("personagem.ser", personagem);
                System.out.println("---Parabens pela vitoria!---");
            }
            else System.out.println("---Tente de novo!---");
        } while(!vencer);        
        imprimirFicha();
    }
}

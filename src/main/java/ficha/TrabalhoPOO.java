package ficha;

import racasClasses.BancoDeClasses;
import racasClasses.BancoDeRacas;
import equipamentos.BancoDeArmaduras;
import equipamentos.BancoDeArmas;
import poderes.Poderes;
import combate.BancoDeInimigos;
import combate.Combate;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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
    
    public static void main(String[] args) {
        
        
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
        System.out.println(personagem);
        //imprimirFichaProvisoria();
        //Menu.exec2();
        //Personagem.equipar();
        //Personagem.poderRaca();
        //imprimirFicha();
        
        FileOutputStream fluxo;
        try {
            fluxo = new FileOutputStream("ava.ser");
            ObjectOutputStream objarq = new ObjectOutputStream(fluxo);
            objarq.writeObject(personagem);
            objarq.close();
            System.out.println("Objeto gravado");
        } catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
        
        Personagem ava;
        FileInputStream fluxo1;
        try {
            fluxo1 = new FileInputStream("ava.ser");
            ObjectInputStream objarq = new ObjectInputStream(fluxo1);
            ava = (Personagem) objarq.readObject();
            objarq.close();
            System.out.println("Objeto lido\n"+ava);
            /*
            System.out.println("Nome: \n"+ava1.getNome());
            System.out.println("Matricula: \n"+ava1.getMatricula());
            System.out.println("Nota: \n"+ava1.getNota());
            */
        } catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch(IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        
        boolean vencer;
        if(Menu.encerrar()) return;
        do {
            vencer = Combate.luta(BancoDeInimigos.mapClasses.get(1));
            if(vencer) {
                Personagem.subirNivel();
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
                System.out.println("---Parabens pela vitoria!---");
            }
            else System.out.println("---Tente de novo!---");
        } while(!vencer);        
        imprimirFicha();
    }
}

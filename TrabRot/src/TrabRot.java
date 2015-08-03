/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 *
 * @author Andressa Freitas
 */
public class TrabRot {

    public static final int comp = 2;
    public static final ArrayList<String> ips = new ArrayList<String>();
    public static final long[][][] tabela = new long[comp][comp][2];
    public static long tempoInicial;

    public static void main(String[] args) {

        tempoInicial = new GregorianCalendar().getTimeInMillis();
        System.out.println("Digite o seu IP");
        for (int cont = 0; cont < comp; cont++) {
            for (int cont2 = 0; cont2 < comp; cont2++) {
                if (cont == cont2) {
                    tabela[cont][cont][0] = 0;
                } else {
                    tabela[cont][cont2][0] = Long.MAX_VALUE;
                }
                tabela[cont][cont2][1] = 0;
            }
        }

        Scanner entrada = new Scanner(System.in);

        String ip = "";
        while (!(ip.equals("0"))) {
            ip = entrada.nextLine();
            ips.add(ip);
            System.out.println("Agora, digite os Ips restantes:");
        }

        new RecebeTabela().start();
        new RecebeMsg().start();
        new RecebePing().start();
        
        System.out.println("Aperte enter para iniciar");
        entrada.next();
        new EnviaPing().start();
        new EnviaMsg().start();
        new EnviaTabela().start();
        

    }

    public static int devolveIndice(String ip) {
        int indice = 0;
        for (int cont = 0; cont < TrabRot.ips.size(); cont++) {
            if (TrabRot.ips.get(cont).equals(ip)) {
                indice = cont;
                return indice;
            }
        }
        return indice;
    }
}

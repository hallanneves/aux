/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andressa Freitas
 */
public class RecebeTabela extends Thread {

    @Override
    public void run() {
        try {
            ServerSocket recebeConexao = new ServerSocket(50002);
            while (true) {
                Conexao conexao = new Conexao(recebeConexao.accept());
                System.out.println("Recebeu ip e tabela de " + conexao.getIP());
                ArrayList<String> ips = (ArrayList<String>) conexao.recebeObjeto();
                long[][][] tabela = (long[][][]) conexao.recebeObjeto();

                for (int cont = 0; cont < ips.size(); cont++) {
                    String ip = ips.get(cont);
                    boolean diferente = true;
                    for (int cont2 = 0; cont2 < TrabRot.ips.size(); cont2++) {
                        if (ip.equals(TrabRot.ips.get(cont2))) {
                            diferente = false;
                        }
                    }
                    if (diferente) {
                        TrabRot.ips.add(ip);
                        System.out.println("Adicionando ip : " + TrabRot.ips.get(cont));
                    }
                }

                int[] comparacao = new int[TrabRot.ips.size()];

                for (int cont = 0; cont < comparacao.length; cont++) {
                    comparacao[cont] = -1;
                    for (int cont2 = 0; cont2 < ips.size(); cont2++) {
                        if (TrabRot.ips.get(cont).equals(ips.get(cont2))) {
                            comparacao[cont] = cont2;
                        }
                    }
                }

                for (int cont = 0; cont < TrabRot.comp; cont++) {
                    if (comparacao[cont] != -1) {
                        for (int cont2 = 0; cont2 < TrabRot.comp; cont2++) {
                            if (comparacao[cont2] != -1) {
                                if (TrabRot.tabela[cont][cont2][1] < tabela[comparacao[cont]][comparacao[cont2]][1]) {
                                    TrabRot.tabela[cont][cont2][0] = tabela[comparacao[cont]][comparacao[cont2]][0];
                                    System.out.print("Salvando indice [" + cont + "]" + "[" + cont2 + "] =" + TrabRot.tabela[cont][cont2][0]);
                                }
                            }
                        }
                    }
                }

                conexao.close();
            }

        } catch (IOException ex) {
            Logger.getLogger(RecebePing.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RecebeTabela.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabrot;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Thread.sleep;
import java.net.ServerSocket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andressa Freitas
 */
public class RecebeMsg extends Thread {

    @Override
    public void run() {
        try {
            ServerSocket recebeConexao = new ServerSocket(50003);
            Dijkstra dijkstra = new Dijkstra();
            while (true) {
                Conexao conexao = new Conexao(recebeConexao.accept());

                String ip = conexao.recebe();
                String mensagem = conexao.recebe();

                if (TrabRot.ips.get(0).equals(ip)) {
//                    new Janela(mensagem).start();
                      System.out.println(mensagem);
                } else {
                    dijkstra.Dijkstra();

                    int indice = TrabRot.devolveIndice(ip);

                    while (dijkstra.anterior[indice] != 0) {
                        indice = dijkstra.anterior[indice];
                    }

                    try {
                        conexao = new Conexao(TrabRot.ips.get(indice), 50003);
                        conexao.envia(ip);
                        conexao.envia(mensagem);
                    } catch (IOException ex) {
                        Logger.getLogger(EnviaMsg.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

                conexao.close();
            }

        } catch (IOException ex) {
            Logger.getLogger(RecebePing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

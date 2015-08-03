/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.net.ServerSocket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andressa Freitas
 */
public class RecebePing extends Thread {

    @Override
    public void run() {
        try {
            ServerSocket recebeConexao = new ServerSocket(50001);
            while (true) {
                Conexao conexao = new Conexao(recebeConexao.accept());

                System.out.println("Recebeu mensagem de ping " + conexao.recebe() + " de " + conexao.getIP());
                sleep(new Random().nextInt(400));
                conexao.envia("Ola");
                System.out.println("Enviou mensagem para " + conexao.getIP() + " Ola");
                conexao.close();
            }

        } catch (IOException ex) {
            Logger.getLogger(RecebePing.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(RecebePing.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

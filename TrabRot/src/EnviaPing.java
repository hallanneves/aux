/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andressa Freitas
 */
public class EnviaPing extends Thread {

    @Override
    public void run() {
        System.out.println("Abriu thread EnviaPing");
        try {
            while (true) {
                for (int cont = 1; cont < TrabRot.comp; cont++) {
                    Conexao conexao = new Conexao(TrabRot.ips.get(cont), 50001);
                    long tempo = new GregorianCalendar().getTimeInMillis();
                    System.out.println("Enviando mensagem para " + TrabRot.ips.get(cont) + " Oi");
                    conexao.envia("Oi");
                    System.out.println("Recebeu mensagem " + conexao.recebe() + " de " + conexao.getIP());
                    tempo = new GregorianCalendar().getTimeInMillis() - tempo;
                    int indice = 0;
                    TrabRot.tabela[0][cont][0] = tempo;
                    TrabRot.tabela[0][cont][1] = new GregorianCalendar().getTimeInMillis() - TrabRot.tempoInicial;
                    System.out.println("atualizou tabela");
                    conexao.close();
                }
                sleep(5000);
            }

        } catch (IOException ex) {
            Logger.getLogger(RecebePing.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(EnviaPing.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

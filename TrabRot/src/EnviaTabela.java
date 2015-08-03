/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andressa Freitas
 */
public class EnviaTabela extends Thread {

    @Override
    public void run() {
        System.out.println("Abrindo thread Tabela");
        try {
            while (true) {
                for (int cont = 1; cont < TrabRot.comp; cont++) {
                    Conexao conexao = new Conexao(TrabRot.ips.get(cont), 50002);
                    conexao.enviaObjeto(TrabRot.ips);
                    conexao.enviaObjeto(TrabRot.tabela);
                    System.out.println("Enviando ips e tabelas !");
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


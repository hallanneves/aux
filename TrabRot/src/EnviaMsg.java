/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andressa Freitas
 */
public class EnviaMsg extends Thread {

    @Override
    public void run() {
        System.out.println("Abriu thread Envia Mensagem");
        Dijkstra dijkstra = new Dijkstra();

        while (true) {

//            String ip = JOptionPane.showInputDialog("Digite o ip da mensagem a ser enviada:");
//            String mensagem = JOptionPane.showInputDialog("Digite a mensagem a ser enviada:");

            dijkstra.Dijkstra();

            Scanner entrada = new Scanner(System.in);
            
            System.out.println("Digite o ip da mensagem a ser enviada:");
            String ip = entrada.nextLine();
            
            System.out.println("Digite a mensagem a ser enviada:");
            String mensagem = entrada.nextLine();
            int indice = TrabRot.devolveIndice(ip);

            while (dijkstra.anterior[indice] != 0) {
                indice = dijkstra.anterior[indice];
            }

            try {
                Conexao conexao;
                conexao = new Conexao(TrabRot.ips.get(indice), 50003);
                conexao.envia(ip);
                conexao.envia(mensagem);
            } catch (IOException ex) {
                Logger.getLogger(EnviaMsg.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.swing.JOptionPane;

/**
 *
 * @author Andressa Freitas
 */
public class Janela extends Thread {
    
    String mensagem;
    
    public Janela(String janela) {
        this.mensagem = janela;
    }
    
    public void run() {
        JOptionPane.showMessageDialog(null,this.mensagem);
    }
    
}



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public final class Conexao {

    private final Socket socket;
    private BufferedReader input = null;
    private PrintWriter out = null;
    private ObjectInputStream objInput = null;
    private ObjectOutputStream objOut = null;

    public Conexao(Socket socket) throws IOException {
        this.socket = socket;
    }

    public Conexao(String IP, int porta) throws IOException {
        socket = new Socket(IP, porta);
    }

    public void envia(Object mensagem) throws IOException {
        if (out == null) {
            out = new PrintWriter(socket.getOutputStream(), true);
        }
        out.println(mensagem.toString());
    }

    public String recebe() throws IOException {
        if (input == null) {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        return input.readLine();
    }

    
    public void enviaObjeto(Object o) throws IOException {
        if (objOut == null) {
            objOut = new ObjectOutputStream(socket.getOutputStream());
        }
        objOut.writeObject(o);
    }
 
    public Object recebeObjeto() throws IOException, ClassNotFoundException {
        if(objInput == null){
            objInput = new ObjectInputStream(socket.getInputStream());            
        }
        return objInput.readObject();
    }
    
    public void close() throws IOException {
        socket.close();
    }

    public String getIP() {
        return socket.getInetAddress().toString().replace("/", "");
    }

}

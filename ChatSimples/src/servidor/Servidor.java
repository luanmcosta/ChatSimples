package servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Mensagem;

public class Servidor {

    public static void main(String[] args) {
        System.out.println("Servidor Iniciado!");
        
        // Socket do Servidor
        ServerSocket servidor;
        
        // Socket(conexao) com o cliente
        Socket cliente;
        
        // Canais de Entrada e Saida
        ObjectOutputStream saidaDados;
        ObjectInputStream entradaDados;
        
        // Declaração da mensagem
        Mensagem mensagem;

        try {
            // Tentar criar conexão como servidor
            servidor = new ServerSocket(20171);
            
            // Esperar conexão do usuário
            cliente = servidor.accept();
            
            // Realizar conexão com os canais de transmissão do cliente
            saidaDados = new ObjectOutputStream(cliente.getOutputStream());
            entradaDados = new ObjectInputStream(cliente.getInputStream());
            
            // Laço infinito de requisições
            while(true){
                // Receber dados da mensagem
                mensagem = (Mensagem) entradaDados.readObject();
                
                // Imprimir requisição
                System.out.println(mensagem.getRemetente() + ": " + mensagem.getTexto());              
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}

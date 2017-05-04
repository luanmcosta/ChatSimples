package cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Mensagem;
import servidor.Servidor;

public class Cliente {

    public static void main(String[] args) {
        
        // Canais de Entrada e Saida
        ObjectOutputStream saidaDados;
        ObjectInputStream entradaDados;

        // Socket(conexao) com o servidor
        Socket cliente_servidor;

        // Scanner (entrada de dados)
        Scanner sc = new Scanner(System.in);

        // Declarar e Instanciar Operação
        Mensagem mensagem = new Mensagem();
        
        // Obter informação do cliente
        System.out.print("Digite seu nome: ");
        mensagem.setRemetente(sc.nextLine());
        
        try {
            // Tentar realizar conexão com o servidor
            cliente_servidor = new Socket("localhost", 20171);
            
            // Realizar conexão com os canais de transmissão
            saidaDados = new ObjectOutputStream(cliente_servidor.getOutputStream());
            entradaDados = new ObjectInputStream(cliente_servidor.getInputStream());
            
            // Laço infinito de requisições
            while(true){
                System.out.print("Eu: ");
                mensagem.setTexto(sc.nextLine());
                // Enviar operação
                saidaDados.writeObject(new Mensagem(mensagem));
            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}

/**
 * Laboratorio 3  
 * Autores: Vítor Augusto Ozanick e Adalberto Teixeira Guedes
 * Ultima atualizacao: 10/10/2023
 */
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;


public class ClienteRMI {
    
	public static void read (IMensagem meio_envio) {
		try {
			Mensagem resposta = meio_envio.enviar(new Mensagem("", "1"));
			System.out.println(resposta.getMensagem());
		} 
		catch (RemoteException e) {
            e.printStackTrace();
        }
	}
	
	public static void write (IMensagem meio_envio, String fortune) {
        try {
            Mensagem resposta = meio_envio.enviar(new Mensagem(fortune, "2"));
            System.out.println(resposta.getMensagem());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
	}
	
	
    public static void main(String[] args) {
                
        try {
                        
            Registry registro = LocateRegistry.getRegistry("127.0.0.1", 1099); 
            
            IMensagem stub =
                    (IMensagem) registro.lookup("servidorFortunes");  
                        
            String opcao = "";
            Scanner leitura = new Scanner(System.in);
            
            do {
            	System.out.println("1) Read");
            	System.out.println("2) Write");
            	System.out.println("x) Exit");
            	System.out.print(">> ");
            	opcao = leitura.next();
            	switch(opcao){
            	case "1": {
            		read(stub);
            		break;
            	}
            	case "2": {
            		System.out.print("Add fortune: ");
            		String fortune = leitura.next();
            		
            		write(stub, fortune);
            		break;
            	}
            	}
            } while(!opcao.equals("x"));
            
            leitura.close();         
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        
    }
    
}
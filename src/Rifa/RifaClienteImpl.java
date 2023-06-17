package Rifa;

import java.rmi.*;

public class RifaClienteImpl implements RifaCliente{
	
	public void notificarGanhador(String ganhador) throws RemoteException {
        System.out.println("Você é o ganhador! Parabéns: " + ganhador);
    }

    public static void main(String[] args) {
        try {
            RifaClienteImpl cliente = new RifaClienteImpl();
            Naming.bind("rmi://localhost/" + args[0], (Remote) cliente);
            System.out.println("Cliente pronto!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

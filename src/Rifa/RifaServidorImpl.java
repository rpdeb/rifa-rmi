package Rifa;

import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.List;

public class RifaServidorImpl extends UnicastRemoteObject implements RifaServidor{
	
	private static final long serialVersionUID = 1L;
	
	private List<String> clientes;
	    private boolean sorteioRealizado;

	    public RifaServidorImpl() throws RemoteException {
	        clientes = new ArrayList<>();
	        sorteioRealizado = false;
	    }

	    public void registrarCliente(String nome) throws RemoteException {
	        clientes.add(nome);
	        System.out.println("Cliente registrado: " + nome);
	    }

	    public void realizarSorteio() throws RemoteException {
	        if (clientes.size() > 0 && !sorteioRealizado) {
	            // Realizar o sorteio
	            int ganhadorIndex = (int) (Math.random() * clientes.size());
	            String ganhador = clientes.get(ganhadorIndex);

	            System.out.println("Sorteio realizado! O ganhador é: " + ganhador);

	            sorteioRealizado = true;

	            // Notificar os clientes sobre o ganhador
	            for (String cliente : clientes) {
	                try {
	                    RifaCliente clienteRemoto = (RifaCliente) Naming.lookup(cliente);
	                    clienteRemoto.notificarGanhador(ganhador);
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	    }

	    public static void main(String[] args) {
	        try {
	            RifaServidorImpl servidor = new RifaServidorImpl();
	            Naming.bind("rmi://localhost/RifaServidor", servidor);
	            System.out.println("Servidor pronto!");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}

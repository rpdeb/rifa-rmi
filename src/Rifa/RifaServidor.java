package Rifa;

import java.rmi.*;
import java.rmi.server.*;
import java.rmi.RemoteException;

public interface RifaServidor {
	void registrarCliente(String nome) throws RemoteException;
    void realizarSorteio() throws RemoteException;
}

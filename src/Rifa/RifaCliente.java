package Rifa;

import java.rmi.*;

public interface RifaCliente {
	 void notificarGanhador(String ganhador) throws RemoteException;
}

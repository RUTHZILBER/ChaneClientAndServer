package changeserver;

import java.lang.Math;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

class Server {
//server socket and vector with weight values, the server socket enters to port 8000. 
//it can return the vector. (for sychronize...)

    private ServerSocket serverSocket;
    private static Vector<Double> weightConvertVector = new Vector<Double>();

    public Server() throws IOException {
        double toTon = Math.pow(10, -3);
        double kiloTon = Math.pow(10, -3) * 9.8;
        double nenoGram = Math.pow(10, 12);
        double masaAtom = Math.pow(10, 26) * 6;
        weightConvertVector.add(1.0); // kg
        weightConvertVector.add(toTon); // ton
        weightConvertVector.add(kiloTon); // kiloTon
        weightConvertVector.add(10.0); // hectogram
        weightConvertVector.add(100.0); // dag
        weightConvertVector.add(1000.0); // gram
        weightConvertVector.add(5000.0); // karat
        weightConvertVector.add(100000.0); // tzetigram
        weightConvertVector.add(1000000.0); // mg
        weightConvertVector.add(1000000000.0); // microgram
        weightConvertVector.add(nenoGram); // nenogram
        weightConvertVector.add(2.2); // paund
        weightConvertVector.add(15432.4); // gr
        weightConvertVector.add(0.2); // ston
        weightConvertVector.add(35.8); // onkia
        weightConvertVector.add(4.7); // mark
        weightConvertVector.add(643.0); // agora
        weightConvertVector.add(masaAtom); // masaAtom

        try {
            serverSocket = new ServerSocket(8000);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Socket Accept() {
        try {
            return serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Vector<Double> getWeightConvertVector() {
        return weightConvertVector;
    }

}

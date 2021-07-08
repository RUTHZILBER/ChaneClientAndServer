/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package changeserver;

import changeclient.ConvertWeight;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;

class ClientHandler implements Runnable {

//get in and output stream from client, and the class cw from client, and the vector.
//it calculate the weight value

    private Socket socket; // A connected socket

    private ObjectInputStream inputFromClient;
    private ObjectOutputStream outputToClient;

    private ConvertWeight inputObject = new ConvertWeight();
    private ConvertWeight outputObject = new ConvertWeight();

    private Vector weightConvertVector = Server.getWeightConvertVector();

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public ConvertWeight getinputObject() {
        return inputObject;
    }

    public ConvertWeight getoutputObject() {
        return outputObject;
    }

    public void run() {
        try {
            // Create data input and output streams
            outputToClient = new ObjectOutputStream(socket.getOutputStream());
            inputFromClient = new ObjectInputStream(socket.getInputStream());

            // Continuously serve the client
            while (true) {
                // Receive inputString from the client
                try {
                    inputObject = (ConvertWeight) inputFromClient.readObject();
                    System.out.println(inputObject + "-----found entering to server");
                } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                // Compute  change
                outputObject = weightConverter(inputObject);
/////
                // Send converted weight back to the client //outputObject
                outputToClient.writeObject(inputObject);

            }
        } catch (IOException err) {
            System.err.println(err);
        }
    }

    public ClientHandler() {
    }

    synchronized private ConvertWeight weightConverter(ConvertWeight inputObject) {
        int indexA = inputObject.getIndexA(), indexB = inputObject.getIndexB();
        double amountA = inputObject.getAmountA(), amountB = 0;
        System.out.print("amount A " + amountA + "\n");//got   1000 sm
        System.out.print(" amount B" + amountB + "\n");////the result 1 km
        System.out.print(" index A " + indexA + "\n");//which : kilo or... sm
        System.out.print(" index B " + indexB + "\n");//to what? km
        if (indexA == 0) // original index means to kilogram (kilo)
        {
            amountB = (amountA * ((Double) (weightConvertVector.elementAt(indexB))).doubleValue());
            inputObject.setAmountB(amountB);
            return inputObject;
        } else {
            if (indexB == 0) {
                amountB = (amountA * (1.0 / (Double) weightConvertVector.elementAt(indexA)));
                inputObject.setAmountB(amountB);
                return inputObject;

            } else {
                amountA = (amountA * (1.0 / (Double) weightConvertVector.elementAt(indexA)));
                indexA = 0;
                amountB = (amountA * ((Double) (weightConvertVector.elementAt(indexB))).doubleValue());
                inputObject.setAmountB(amountB);
                return inputObject;
            }

        }

    }

}

package com.example.controlpunetas;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Observable;

public class Comunicacion extends Observable implements Runnable{

    private DatagramSocket socket;
    private InetAddress cliente;

  

    public Comunicacion(){
  
        try {
            socket= new DatagramSocket(5000);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
    if(socket!=null) {
        try {

            cliente = InetAddress.getByName("192.168.100.128");

            while (true) {
                byte[] buff = new byte[200];
                DatagramPacket packet = new DatagramPacket(buff, buff.length);
                socket.receive(packet);
                String mensaje = new String(packet.getData());
                System.out.println(mensaje);
                notifyObservers(mensaje);
                clearChanged();

            }

        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(socket);
    }
    }

    public void enviar( final String mensaje) {

        if (socket != null) {


            new Thread(

                    new Runnable() {
                        @Override
                        public void run() {
                            DatagramPacket packet = new DatagramPacket(mensaje.getBytes(), mensaje.getBytes().length, cliente, 5000);
                            try {
                                socket.send(packet);
                                System.out.println("enviado");
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }


                        }
                    }


            ).start();

        }


    }



}
package noghtekhat_server;


import java.awt.Color;
import java.net.*;
import java.io.*;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Engine extends Thread{
    private DatagramSocket socket;
    private LinkedList<Address> addresses;
    DatagramPacket packet;
    Address a;
    Map map;
    StatusForm statusForm;
    
    public Engine(Map map) throws IOException {
        this("QuoteServer",map);
    }

    public Engine(String name, Map map) throws IOException {
        super(name);
        socket = new DatagramSocket(1835);
        this.map = map;
        addresses = new LinkedList<>();
        
    }  
    
    public void run(){
        byte[] buf = new byte[256];
        statusForm = new StatusForm(map.playerCount, "192.168.1.106", 1835);
        statusForm.setVisible(true);
        for(int i=0; i<map.playerCount; i++){
            try {
                boolean exists = false;
                buf = new byte[256];
                packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                a = new Address(packet.getAddress().getHostAddress(), packet.getPort(), packet.getAddress());
                String massage = Correct(new String(buf));
                if(massage.equals("Request")){
                    for(int j=0; j<addresses.size(); j++){
                        if(Address.IsSimilar(addresses.get(j),a)){
                            exists = true;
                            break;
                        }
                    }
                    if(!exists){
                        statusForm.boxPlayers[i].setSelected(true);
                        addresses.add(a);
                    }
                    else{
                        i--;
                    }
                }
                else{
                    i--;
                }
                 
            } catch (IOException ex) {
                System.out.println("Error");
            }
        }
        for(int i=0 ;i<map.playerCount; i++){
            map.color[i] = i;
        }
        for(int i=0; i<map.playerCount; i++){
            buf = ("Start$" + (i+"") + "$" + (map.size+"") + "$").getBytes();
            packet = new DatagramPacket(buf, buf.length, addresses.get(i).inetAddress, addresses.get(i).port);
            try {
                socket.send(packet);
            } catch (IOException ex) {
                System.out.println("Error in sending data");
            }
        }
        
        map.turn = 0;
        
        
        while(!map.isFinished()){
            buf = "Turn$".getBytes();
            packet = new DatagramPacket(buf, buf.length, addresses.get(map.turn).inetAddress, addresses.get(map.turn).port);
            try {
                socket.send(packet);
            } catch (IOException ex) {
            }
            
            packet = new DatagramPacket(buf, buf.length);
            try {
                socket.receive(packet);
            } catch (IOException ex) {}
            a = new Address(packet.getAddress().getHostAddress(), packet.getPort(), packet.getAddress());
            if(Address.IsSimilar(a, addresses.get(map.turn))){
                String massage = Correct(new String(buf));
                int num = -1;
                try{
                    num = Integer.parseInt(massage);
                    map.add(num, map.turn);
                    for(int i=0; i<map.playerCount; i++){
                        String temp = (num + "") + "$" + (map.color[map.turn] + "") + "$";
                        packet = new DatagramPacket(temp.getBytes(), temp.getBytes().length, addresses.get(i).inetAddress, addresses.get(i).port);
                        try {
                            socket.send(packet);
                        } catch (IOException ex) {
                        }
                    }
                }catch(Exception e){
                }
                try {
                    sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(map.CheckBoxCreated())
                    map.turn--;
            }
            map.turn ++;
            map.turn %= map.playerCount;
            
        }
        socket.close();
    }
    
    
    
    private String Correct(String s){
        String ret = "";
        for(int i=0;;i++){
            if(s.charAt(i) == '$')
                break;
            ret += s.charAt(i);
        }
        return ret;
    }
    
    
}

class Address{
    String IP;
    int port;
    InetAddress inetAddress;
    public Address(String IP, int port, InetAddress inetAdress){
        this.IP = IP;
        this.port = port;
        this.inetAddress = inetAdress;
    } 
    public static boolean IsSimilar(Address a1, Address a2){
        if(a1.port == a2.port && a1.IP.equals(a2.IP))
            return true;
        return false;
    }
}

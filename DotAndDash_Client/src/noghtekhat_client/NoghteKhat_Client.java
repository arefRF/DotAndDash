package noghtekhat_client;

import java.awt.Color;
import java.io.IOException;
import java.net.*;
import java.net.*;

public class NoghteKhat_Client extends Thread{

    public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException{
        /*InetAddress address = InetAddress.getByName("127.0.0.1");
        Socket client = new Socket(address, 1835);*/
        
        
        
        
        byte[] buf = "Request$".getBytes();
        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName("192.168.1.106");
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 1835);
        socket.send(packet);
        
        buf = new byte[100];
        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        String str1 = new String(packet.getData());
        String s = Correct(str1);
        if(s.equals("Start")){
            int color = Integer.parseInt(new String(packet.getData()).charAt(6) + "");
            System.out.println(color);
            String string = "";
            for(int i=8;;i++){
                if(new String(packet.getData()).charAt(i) == '$')
                    break;
                string += str1.charAt(i);
            }
            
            Map map = new Map(Integer.parseInt(string),GetColor(color));
            Form form = new Form(Integer.parseInt(string), map);
            form.setVisible(true);
            
            
            while(true){
                buf = new byte[10];
                packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                String str = new String(packet.getData());
                s = Correct(new String(packet.getData()));
                if(s.equals("Turn")){
                    form.isTurn = true;
                    form.turnLabel.setText("Your Turn");
                    while(form.dummy == 0){
                        sleep(500);
                    }
                    form.dummy = 0;
                    buf = (form.selected + "$").getBytes();
                    packet = new DatagramPacket(buf, buf.length, address, 1835);
                    socket.send(packet);
                }
                else{
                    String t1="",t2="";
                    int i;
                    for(i=0;;i++){
                        if(str.charAt(i) == '$'){
                            break;
                        }
                        t1 += str.charAt(i);
                    }
                    for(i++;;i++){
                        if(str.charAt(i) == '$'){
                            break;
                        }
                        t2 += str.charAt(i);
                    }
                    form.UpdateFromServer(Integer.parseInt(t1),GetColor(Integer.parseInt(t2)));
                }    
            }
        }
        
    }
    
    private static String Correct(String s){
        String ret = "";
        for(int i=0;;i++){
            if(s.charAt(i) == '$')
                break;
            ret += s.charAt(i);
        }
        return ret;
    }
    public static Color GetColor(int i){
        switch(i){
            case 0:return Color.RED;
            case 1:return Color.BLUE;
            case 2:return Color.GREEN;
            case 3:return Color.YELLOW;
            case 4:return Color.ORANGE;
            case 5:return Color.CYAN;
            case 6:return Color.MAGENTA;
            case 7:return Color.PINK;
        }
        return null;
    }
    
}

package noghtekhat_server;

import java.io.IOException;

public class NoghteKhat_Server {

    public static void main(String[] args) throws IOException {
        Map map = new Map(2,10);
        Thread t = new Engine(map);
        t.start();
    }
    
}

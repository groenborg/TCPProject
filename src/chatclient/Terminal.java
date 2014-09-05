package chatclient;

/**
 *
 * @author simon
 */
public class Terminal implements Observer {

    public static void main(String[] args) {
        String inetAddress = "127.0.0.1";
        int port = 80;
        if(args.length == 2){
            inetAddress = args[0];
            port = Integer.parseInt(args[1]);
        }
        ChatClient c = new ChatClient(inetAddress,port);
           
        
        
    }

    
    
    
    
    
    
    @Override
    public void recieveMessage(String message) {
        System.out.println(message);
    }

}

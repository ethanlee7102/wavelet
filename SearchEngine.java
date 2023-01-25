import java.io.IOException;
import java.net.URI;
import java.util.*;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    int num = 0;
    ArrayList<String> addList = new ArrayList<String>();
    ArrayList<String> searchList = new ArrayList<String>();

    public String handleRequest(URI url) {
        if (url.getPath().contains("/search")) {
            String[] parameters = url.getQuery().split("=");
            for (int i = 0; i < addList.size(); i++){
                if (addList.get(i).contains(parameters[1])){
                    searchList.add(addList.get(i));
                }
            }
            return "" + searchList;
        } else if (url.getPath().contains("/add")) {
            String[] parameters = url.getQuery().split("=");
            addList.add(parameters[1]);   
            return String.format("%s has been added!", parameters[1]);
        }
        return "404 Not Found!";

    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }
    
        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}

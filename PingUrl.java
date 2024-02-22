import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PingUrl implements Runnable{

    private String url;

    public PingUrl(String url){
        this.url=url;
    }

    @Override
    public void run() {

        String result="";
        int code = 200;

        try {
            URL siteUrl =new URL(url);
            HttpURLConnection connection = (HttpURLConnection) siteUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.connect();
            code = connection.getResponseCode();

            if(code == 200){
                result = "->Green \t\t "+"Code: "+code;
            }
            else{
                result = "->Yellow \t\t "+"Code: "+code;
            }

        } catch (Exception e) {
            result = "->Red \t\t "+"Wrong domain"+"Code: "+code+ e.getMessage();
        }

        System.out.println(url+ "\t\t Status: "+result);

    }
}

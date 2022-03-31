
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {

        try (InputStream input = Main.class.getClassLoader().getResourceAsStream("application.properties")) {

            Properties properties = new Properties();

            properties.load(input);

            String url = properties.getProperty("IMBD_URL");
            String key = properties.getProperty("IMBD_KEY");

            System.out.println(url);
            System.out.println(key);

        } catch (IOException e){
            e.printStackTrace();
        }


        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(""))
                    .timeout(Duration.of(10, ChronoUnit.SECONDS))
                    .GET()
                    .build();

            HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.body());
        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

}

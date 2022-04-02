
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

        try {

            final String imbdUrl = PropertyManager.getPropertyValueByKey("IMBD_URL");
            final String imbdKey = PropertyManager.getPropertyValueByKey("IMBD_KEY");

            HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(imbdUrl + imbdKey))
                .timeout(Duration.of(10, ChronoUnit.SECONDS))
                .GET()
                .build();

            HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.body());

            List<String> title = getJsonContentByAttribute(response.body(), "title");
            List<String> image = getJsonContentByAttribute(response.body(), "image");

            System.out.println(title);
            System.out.println(image);
        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static List<String> getJsonContentByAttribute(String httpResponse, String attribute) {
        final String regex = "(?<=\"" + attribute + "\":\")(.*?)(?=\")";

        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(httpResponse);

        List<String> result = new ArrayList<>();

        while (matcher.find()) {
            result.add(matcher.group(0));
        }

        return result;
    }

}

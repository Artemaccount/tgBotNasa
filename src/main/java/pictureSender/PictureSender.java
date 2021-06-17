package pictureSender;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

/**
 * Класс отправки фотографий
 */

public class PictureSender {
    public static final String NASA_URI =
            "https://api.nasa.gov/planetary/apod?api_key=";
    private final String NASA_API_KEY;
    public static ObjectMapper mapper = new ObjectMapper();

    public PictureSender(String nasaApiKey) {
        this.NASA_API_KEY = nasaApiKey;
    }

    private NasaResponse nasaResponse() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false)
                        .build())
                .build();

        HttpGet request = new HttpGet(NASA_URI + NASA_API_KEY);
        request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());
        CloseableHttpResponse response = httpClient.execute(request);
        NasaResponse nasaResponse = mapper.readValue(
                response.getEntity().getContent(),
                new TypeReference<NasaResponse>() {
                });
        return nasaResponse;
    }

    public String getExplanation() throws IOException {
        return nasaResponse().getExplanation();
    }

    public String getUrl() throws IOException {
        return nasaResponse().getHdUrl();
    }
}

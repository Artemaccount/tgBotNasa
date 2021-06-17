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

public class GetPicture {

    public static final String NASA_API_KEY = "jvCHMt2rwGHg6JeGgmoYbRgnofh5Aml2IXxf4ned";
    public static final String NASA_URI =
            "https://api.nasa.gov/planetary/apod?api_key=";

    public static ObjectMapper mapper = new ObjectMapper();

    public NasaRequest nasaRequest() throws IOException {
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
        NasaRequest nasaRequest = mapper.readValue(
                response.getEntity().getContent(),
                new TypeReference<NasaRequest>() {
                });
        return nasaRequest;
    }

    public String getExplanation() throws IOException{
            return nasaRequest().getExplanation();
    }

    public String getUrl() throws IOException {
        return nasaRequest().getHdUrl();
    }
}


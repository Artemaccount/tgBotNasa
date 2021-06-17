package pictureSender;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Класс для разбора ответа с сайта Nasa.com
 */

public class NasaResponse {
    private final String copyright;
    private final String date;
    private final String explanation;
    private final String hdUrl;
    private final String mediaType;
    private final String serviceVersion;
    private final String title;
    private final String url;


    public String getHdUrl() {
        return hdUrl;
    }

    public String getExplanation() {
        return explanation;
    }

    public NasaResponse(
            @JsonProperty("copyright") String copyright,
            @JsonProperty("date") String date,
            @JsonProperty("explanation") String explanation,
            @JsonProperty("hdurl") String hdUrl,
            @JsonProperty("media_type") String mediaType,
            @JsonProperty("service_version") String serviceVersion,
            @JsonProperty("title") String title,
            @JsonProperty("url") String url) {

        this.copyright = copyright;
        this.date = date;
        this.explanation = explanation;
        this.hdUrl = hdUrl;
        this.mediaType = mediaType;
        this.serviceVersion = serviceVersion;
        this.title = title;
        this.url = url;
    }
}

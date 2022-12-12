package com.moha.demo.Conroller;

import com.google.gson.Gson;
import com.moha.demo.Transcript;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
public class test extends AbstractController {
    @Value("${Token_Id}")
    private String ID;
    @GetMapping("/test")
    public String get() throws  URISyntaxException, IOException, InterruptedException {
        Transcript transcript =new Transcript("https://raw.githubusercontent.com/johnmarty3/JavaAPITutorial/main/Thirsty.mp4");
        Gson gson= new Gson();
        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(new URI("https://api.assemblyai.com/v2/transcript"))
                .headers("Authorization",this.ID)
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(transcript)))
                .build();
        HttpClient httpClient=HttpClient.newHttpClient();
        HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());

        transcript = gson.fromJson(postResponse.body(),Transcript.class);


        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("https://api.assemblyai.com/v2/transcript/"+transcript.getId()))
                .headers("Authorization",this.ID)
                .GET()
                .build();
        while (true) {
            HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(getResponse.body());

            transcript = gson.fromJson(getResponse.body(), Transcript.class);
            System.out.println(transcript.getStatus());
            if (transcript.getStatus().equals("completed")||transcript.getStatus().equals("error"))
             break;

            Thread.sleep(1000);
        }

        return transcript.getText();


    }


    @Override
    void test()   {


    }
}

package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.api.response.eleventLabs.TextToSpeech;
import net.engineeringdigest.journalApp.api.response.eleventLabs.VoiceIdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
@Slf4j
public class ElevenLabsService {

    @Value("${elevenLabs.api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    public String getTextToVoiceFile(String text) {
        String finalApiToGetVoices = "https://api.elevenlabs.io/v1/voices";

        ResponseEntity<VoiceIdResponse> getVoiceResponse = restTemplate.exchange(finalApiToGetVoices, HttpMethod.GET, null, VoiceIdResponse.class);
        String voiceId = getVoiceResponse
                .getBody()
                .getVoices()
                .stream()
                .map(VoiceIdResponse.Voice::getVoiceId)
                .findFirst().orElse(null);

        String finalApi = "https://api.elevenlabs.io/v1/text-to-speech/" + voiceId;
        HttpEntity<TextToSpeech> httpEntity = getTextToSpeechHttpEntity(text);

        String path = "";

        ResponseEntity<byte[]> response = restTemplate.exchange(finalApi, HttpMethod.POST, httpEntity, byte[].class);
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            byte[] mp3Bytes = response.getBody();
            path = "output" + LocalDateTime.now() + ".mp3";
            // Save the MP3 to a file
            try (FileOutputStream fileOutputStream = new FileOutputStream(path)) {
                fileOutputStream.write(mp3Bytes);
                log.info("MP3 file saved successfully.");
            } catch (IOException e) {
                path = null;
                log.error("Error saving MP3 file", e);
            }
            return path;
        } else {
            log.error("Failed to get MP3 response: {}", response.getStatusCode());
        }

        log.info("{}",  response);
        return path;
    }

    private HttpEntity<TextToSpeech> getTextToSpeechHttpEntity(String text) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("xi-api-key", apiKey);

        TextToSpeech requestBody = new TextToSpeech();
        requestBody.setText(text);
        TextToSpeech.VoiceSettings voiceSettings = new TextToSpeech.VoiceSettings();
        voiceSettings.setStability(0.4);
        voiceSettings.setSimilarityBoost(0.87);
        requestBody.setVoiceSettings(voiceSettings);

        HttpEntity<TextToSpeech> httpEntity = new HttpEntity<>(requestBody, httpHeaders);
        return httpEntity;
    }
}

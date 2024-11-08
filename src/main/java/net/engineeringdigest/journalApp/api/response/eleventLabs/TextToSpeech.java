package net.engineeringdigest.journalApp.api.response.eleventLabs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TextToSpeech {
    private String text;
    private VoiceSettings voiceSettings;

    @Getter
    @Setter
    public static class VoiceSettings {
        private double stability;
        @JsonProperty("similarity_boost")
        private double similarityBoost;
    }
}

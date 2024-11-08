package net.engineeringdigest.journalApp.api.response.eleventLabs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VoiceIdResponse {
    private List<Voice> voices;

    @Getter
    @Setter
    public static class Voice {
        @JsonProperty("voice_id")
        private String voiceId;
    }
}



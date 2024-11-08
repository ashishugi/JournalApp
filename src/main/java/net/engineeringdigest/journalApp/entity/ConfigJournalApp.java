package net.engineeringdigest.journalApp.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("config_journal_app")
@Data
public class ConfigJournalApp {
    private String key;
    private String value;
}

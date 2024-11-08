package net.engineeringdigest.journalApp.config;

import jakarta.annotation.PostConstruct;
import net.engineeringdigest.journalApp.entity.ConfigJournalApp;
import net.engineeringdigest.journalApp.repository.ConfigJournalAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class AppCache {

    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;

    public Map<String, String> CACHE;

    @PostConstruct
    public void init() {
        CACHE = new HashMap<>();
        List<ConfigJournalApp> results = configJournalAppRepository.findAll();

        CACHE = results.stream()
                .collect(Collectors.toMap(ConfigJournalApp::getKey, ConfigJournalApp::getValue));
    }
}

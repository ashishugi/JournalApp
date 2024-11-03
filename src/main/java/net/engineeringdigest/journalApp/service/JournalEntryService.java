package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalRepository;
    @Autowired
    private UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(JournalEntryService.class);

    public List<JournalEntry> getAllJournal() {
        return this.journalRepository.findAll();
    }

    @Transactional
    public void saveJournalEntry(JournalEntry journalEntry, String username) {
        try {
            User user = userService.findByUsername(username);

            if(user == null) { return; }

            journalEntry.setDate(LocalDateTime.now());
            JournalEntry savedJournalEntry = this.journalRepository.save(journalEntry);
            user.getJournalEntries().add(savedJournalEntry);
            userService.saveUser(user);
        } catch (Exception ex) {
            log.error("exception for username: {} ", username);
            throw new RuntimeException("Issue occurred while saving journal entry.");
        }
    }

    public Optional<JournalEntry> findJournalById(ObjectId id) {
        Optional<JournalEntry> entry = this.journalRepository.findById(id);
        return entry;
    }

    public JournalEntry updateJournalById(String username, ObjectId id, JournalEntry newEntry) {
        User user = userService.findByUsername(username);

        if(user == null || user.getJournalEntries().stream().noneMatch(x -> x.getId().equals(id))) {
            return null;
        }

        Optional<JournalEntry> oldEntry = this.journalRepository.findById(id);

        if(!oldEntry.isPresent()) { return null; }

        oldEntry.get().setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : oldEntry.get().getTitle());
        oldEntry.get().setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : oldEntry.get().getContent());
        return this.journalRepository.save(oldEntry.get());
    }

    @Transactional
    public boolean deleteById(String username, ObjectId id) {
        boolean removed = false;
        try {
            User user = userService.findByUsername(username);
            removed = user.getJournalEntries().removeIf(entry -> entry.getId().equals(id));

            if (removed) {
                userService.saveUser(user);
                this.journalRepository.deleteById(id);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Exception occurred while delete by id with username: " + username);
        }
        return removed;
    }
}

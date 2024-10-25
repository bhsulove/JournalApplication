package com.edigest.journalApp.cache;

import com.edigest.journalApp.entity.ConfigJournalApp;
import com.edigest.journalApp.repository.ConfigJournalAppRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.file.WatchEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {
    public enum keys{
        WEATHER_API;
    }
    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;

    public Map<String,String> appCache;

    @PostConstruct
    public void init(){
        appCache=new HashMap<>();
        List<ConfigJournalApp> all = configJournalAppRepository.findAll();
        for(ConfigJournalApp app : all){
            appCache.put(app.getKey(),app.getValue());
        }
    }
}

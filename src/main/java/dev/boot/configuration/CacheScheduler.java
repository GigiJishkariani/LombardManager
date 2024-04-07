package dev.boot.configuration;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CacheScheduler {

//    @Autowired
//    private BranchService branchService;

    @Scheduled(cron = "0 0 */8 * * *")
    @CacheEvict(value = "branches", allEntries = true)
    public void evictAllCaches() {

    }
}

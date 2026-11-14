package ro.uvt.commands;

import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class DefaultCommandExecutor implements CommandExecutor {

    private final ExecutorService pool = Executors.newFixedThreadPool(4);
    private final Map<String, String> statuses = new ConcurrentHashMap<>();
    private final Map<String, Object> results = new ConcurrentHashMap<>();

    @Override
    public <T> T executeSync(Command<T> command) {
        return command.execute();
    }

    @Override
    public String submitAsync(Command<?> command) {
        String taskId = UUID.randomUUID().toString();
        statuses.put(taskId, "PENDING");
        pool.submit(() -> {
            try {
                statuses.put(taskId, "RUNNING");
                Thread.sleep(3000); // simulam un serviciu extern lent (ISBN)
                Object result = command.execute();
                results.put(taskId, result);
                statuses.put(taskId, "DONE");
            } catch (Exception e) {
                statuses.put(taskId, "FAILED");
            }
        });
        return taskId;
    }

    @Override
    public String getStatus(String taskId) {
        return statuses.getOrDefault(taskId, "NOT_FOUND");
    }

    @Override
    public Object getResult(String taskId) {
        return results.get(taskId);
    }
}
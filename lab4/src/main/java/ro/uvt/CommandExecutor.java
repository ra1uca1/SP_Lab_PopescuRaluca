package ro.uvt.commands;

public interface CommandExecutor {
    <T> T executeSync(Command<T> command);
    String submitAsync(Command<?> command);
    String getStatus(String taskId);
    Object getResult(String taskId);
}
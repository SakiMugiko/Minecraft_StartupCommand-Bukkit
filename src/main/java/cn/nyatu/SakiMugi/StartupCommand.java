package cn.nyatu.SakiMugi;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class StartupCommand extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        FileConfiguration config = getConfig();

        long delaySeconds = Math.max(0L, config.getLong("delay", 5L));
        long delayTicks = Math.max(1L, delaySeconds * 20L);

        getLogger().info(
                "StartupCommand 已加载，将在 " + delaySeconds + " 秒后执行启动命令..."
        );

        Bukkit.getScheduler().runTaskLater(
                this,
                this::executeCommands,
                delayTicks
        );
    }

    private void executeCommands() {

        List<String> commands = getConfig().getStringList("commands");

        if (commands.isEmpty()) {
            getLogger().info("没有配置任何启动命令。");
            return;
        }

        ConsoleCommandSender console = Bukkit.getConsoleSender();

        getLogger().info("开始执行启动命令...");

        for (String command : commands) {

            if (command == null || command.trim().isEmpty()) {
                continue;
            }

            command = command.trim();

            if (command.startsWith("/")) {
                command = command.substring(1);
            }

            getLogger().info("执行命令: /" + command);

            try {
                boolean result = Bukkit.dispatchCommand(console, command);

                if (!result) {
                    getLogger().warning(
                            "命令执行失败或未找到命令: /" + command
                    );
                }

            } catch (Exception e) {
                getLogger().warning(
                        "执行命令时发生异常: /" + command
                );

                e.printStackTrace();
            }
        }

        getLogger().info("启动命令执行完成。");
    }
}

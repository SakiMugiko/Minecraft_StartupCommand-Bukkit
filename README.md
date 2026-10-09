# StartupCommand

轻量级 Bukkit 插件，用于在服务器启动后延迟执行配置的控制台命令

## 默认配置

```yaml
# 服务器启动后等待多少秒
delay: 5

# 按顺序执行的控制台命令
commands:
  - "say Hello, world!"
```

- `delay`：执行命令前等待的秒数，负数按 `0` 处理。
- `commands`：按顺序执行的控制台命令列表。

空命令会被跳过；命令列表为空时不会执行命令。

## 运行环境

- Bukkit / Paper 1.20+
- Java 17+

## 安装

 下载并将 `StartupCommand-1.0.0.jar` 放入服务器的 `plugins` 目录，然后重启服务器。首次启动会生成 `plugins/StartupCommand/config.yml`；修改配置后重启服务器生效。

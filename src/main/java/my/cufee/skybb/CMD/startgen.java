package my.cufee.skybb.CMD;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class startgen implements CommandExecutor {

    private final JavaPlugin plugin;
    private final Random random;

    public startgen(JavaPlugin plugin) {
        this.plugin = plugin;
        this.random = new Random();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        // Планировщик задач Bukkit
        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                // Выдача случайной вещи каждому игроку
                for (Player player : Bukkit.getOnlinePlayers()) {
                    final ItemStack randomItem = new ItemStack(getRandomItem().getType(), getRandomAmount());
                    int randomAmount = getRandomAmount();

                    player.getInventory().addItem(randomItem);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2&o Вы получили ") + randomItem.getAmount() + " " + ChatColor.translateAlternateColorCodes('&', "&c") + randomItem.getType().name());
                }
            }
        };

        // Запуск задачи каждую минуту (1200 тиков)
        task.runTaskTimer(plugin, 0, 1200);

        return true;
    }

    private ItemStack getRandomItem() {
        // Получение случайного типа предмета из перечисления Material
        Material[] materials = Material.values();
        Material randomMaterial = materials[random.nextInt(materials.length)];

        return new ItemStack(randomMaterial);
    }

    private int getRandomAmount() {
        // Получение случайного количества от 1 до 10
        return random.nextInt(10) + 1;
    }
}
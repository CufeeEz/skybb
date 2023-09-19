package my.cufee.skybb.CMD;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Events implements CommandExecutor {
    private Random random;

    public Events() {
        this.random = new Random();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        // Ваш код команды

        EventChest();

        return false;
    }

    private void EventChest(){
        List<String> Players = new ArrayList<>();

        for (Player player : Bukkit.getOnlinePlayers()) {
            Players.add(player.getName()); // Берем имя игрока, а не преобразуем его в строку
        }
        int randomIndex = getRandomAmount(Bukkit.getOnlinePlayers().size());
        Bukkit.broadcastMessage("Hello " + Players.get(randomIndex));
    }

    private int getRandomAmount(int max) {
        return random.nextInt(max);
    }
}
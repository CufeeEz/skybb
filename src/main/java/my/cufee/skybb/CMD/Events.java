package my.cufee.skybb.CMD;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Events implements CommandExecutor {
    private final Random random;

    public Events() {
        this.random = new Random();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {



        setRandomChest();
        return false;
    }

    private void setRandomChest(){
        Player targetPlayer = Bukkit.getPlayer(getRandomPlayer());
        Location LocationSpawnedChest = targetPlayer.getLocation();
        LocationSpawnedChest.add(1.0, -1.0, 1.0);
        LocationSpawnedChest.getBlock().setType(Material.CHEST);
        Bukkit.broadcastMessage("Заспавнился сундучок для "+ targetPlayer.getName() + " X= "+ ((int) LocationSpawnedChest.getX()) + " Y= "+ ((int) LocationSpawnedChest.getY()) + " Z= "+ ((int) LocationSpawnedChest.getZ()));




    }

    private String getRandomPlayer(){ // метод получения рандомного игрока из списка онлайна
        List<String> Players = new ArrayList<>();

        for (Player player : Bukkit.getOnlinePlayers()) {
            Players.add(player.getName()); // Берем имя игрока.
        }
        int maxPlayerOnServer = getRandomAmount(Bukkit.getOnlinePlayers().size());
        // Bukkit.broadcastMessage("Hello" + Players.get(maxPlayerOnServer));
        return Players.get(maxPlayerOnServer);
    }

    private int getRandomAmount(int max) {
        return random.nextInt(max);
    }
}
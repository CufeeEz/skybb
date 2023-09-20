package my.cufee.skybb.CMD;

import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.inventory.ItemStack;


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


        EventTNT();

        return false;
    }
    private void EventTNT() {
        Player targetPlayer = Bukkit.getPlayer(getRandomPlayer());
        Bukkit.broadcastMessage("5 тонн тротила было сброшено на " + targetPlayer.getName());
        Location playerLocation = targetPlayer.getLocation();
        TNTPrimed tnt = (TNTPrimed) playerLocation.getWorld().spawnEntity(playerLocation, EntityType.PRIMED_TNT); // Создаем зажженный ТНТ
        tnt.setYield(5); // Установка мощности взрыва
        tnt.setFuseTicks(1);// Зажигаем ТНТ
    }
    private void setRandomChest(){
        Player targetPlayer = Bukkit.getPlayer(getRandomPlayer());
        Location LocationSpawnedChest = targetPlayer.getLocation();
        LocationSpawnedChest.add(1.0 + getRandomAmountForSpawnChest(), -1.0, 1.0 + getRandomAmountForSpawnChest());

        Block chestBlock = LocationSpawnedChest.getBlock();
        chestBlock.setType(Material.CHEST);
        Chest chest = (Chest) chestBlock.getState();

        for (int i = 0; i < 27; i++) {
            if (getRandomAmountForItemChest() >= 12 ){
                ItemStack Item = new ItemStack(getRandomItem().getType(), getRandomAmountForItemChest());
                chest.getInventory().setItem(i, Item);
            }
        }
        Bukkit.broadcastMessage("Заспавнился сундучок для "+ targetPlayer.getName() + " X= "+ ((int) LocationSpawnedChest.getX()) + " Y= "+ ((int) LocationSpawnedChest.getY()) + " Z= "+ ((int) LocationSpawnedChest.getZ()));




    }
    private ItemStack getRandomItem() {
        // Получение случайного типа предмета из перечисления Material
        Material[] materials = Material.values();
        Material randomMaterial = materials[random.nextInt(materials.length)];

        return new ItemStack(randomMaterial);
    }
    private String getRandomPlayer(){ // метод получения рандомного игрока из списка онлайна
        List<String> Players = new ArrayList<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            Players.add(player.getName()); // Берем имя игрока.
        }
        int maxPlayerOnServer = getRandomAmount(Bukkit.getOnlinePlayers().size());
        return Players.get(maxPlayerOnServer);
    }

    private int getRandomAmount(int max) {
        return random.nextInt(max);
    }
    private int getRandomAmountForItemChest() {
        // Получение случайного количества от 1 до 15
        return random.nextInt(15) + 1;
    }
    private int getRandomAmountForSpawnChest(){
        return random.nextInt(500 - -500 + 1) + -500;
    }
}
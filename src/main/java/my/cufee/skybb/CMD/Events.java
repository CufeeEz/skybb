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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import my.cufee.skybb.util.AllPoitoinEffects;

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

        EventBedrockBox();

        return false;
    }
    private void EventBedrockBox(){
        Player targetPlayer = Bukkit.getPlayer(getRandomPlayer());

        Location playerLocation = targetPlayer.getLocation();
        playerLocation.add(0.0,-1.0,0.0);
        int playerLocationX = (int) playerLocation.getX();
        int playerLocationZ = (int) playerLocation.getZ();
        double playerLocationXdouble = playerLocationX + 0.50000000;
        double playerLocationZdouble = playerLocationZ + 0.50000000;
        Location TeleportCordsPlayer = new Location(Bukkit.getWorld("world"), NormalizedCords(playerLocationXdouble), playerLocation.getY()+1, NormalizedCords(playerLocationZdouble), 0, 0);
        targetPlayer.teleport(TeleportCordsPlayer);
        // да, знаю, можно было и лучше это реализовать, но пока мой мозг не умет такое думать :(
        playerLocation.getBlock().setType(Material.BEDROCK);
        playerLocation.add(0.0,3.0,0.0);
        playerLocation.getBlock().setType(Material.BEDROCK);
        playerLocation.add(1.0,-2.0,0.0);
        playerLocation.getBlock().setType(Material.BEDROCK);
        playerLocation.add(-2.0,0.0,0.0);
        playerLocation.getBlock().setType(Material.BEDROCK);
        playerLocation.add(1.0,0.0,1.0);
        playerLocation.getBlock().setType(Material.BEDROCK);
        playerLocation.add(0.0,0.0,-2.0);
        playerLocation.getBlock().setType(Material.BEDROCK);
     }
    private void EventGetRandomEffect() {
        Player targetPlayer = Bukkit.getPlayer(getRandomPlayer());
        List<PotionEffectType> potionEffects = AllPoitoinEffects.givePoitionEffects();
        PotionEffectType randomPotionEffectType = AllPoitoinEffects.getRandomPotionEffect(potionEffects);

        int duration = random.nextInt(12000) + 1200; //  длительности эффекта
        int amplifier = random.nextInt(7); //  уровня эффекта (от 0 до 7)
        PotionEffect randomPotionEffect = new PotionEffect(randomPotionEffectType, duration, amplifier);
        String effectName = randomPotionEffect.getType().getName();

        targetPlayer.addPotionEffect(randomPotionEffect);
        Bukkit.broadcastMessage("Эффект " + effectName + " " + amplifier+1 + " уровня был выдан " + targetPlayer.getName() + " на " + duration/20 + " секунд");
    }
    private void EventTNT() {
        Player targetPlayer = Bukkit.getPlayer(getRandomPlayer());
        Bukkit.broadcastMessage("5 тонн тротила было сброшено на " + targetPlayer.getName());
        Location playerLocation = targetPlayer.getLocation();
        TNTPrimed tnt = (TNTPrimed) playerLocation.getWorld().spawnEntity(playerLocation, EntityType.PRIMED_TNT); // Создаем зажженный ТНТ
        tnt.setYield(5); // Установка мощности взрыва
        tnt.setFuseTicks(1);// Зажигаем ТНТ
    }
    private void EventRandomChest(){
        Player targetPlayer = Bukkit.getPlayer(getRandomPlayer());
        Location LocationSpawnedChest = targetPlayer.getLocation();
        LocationSpawnedChest.add(getRandomAmountForSpawnChest(), -1.0, getRandomAmountForSpawnChest());

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





    private double NormalizedCords(double cord){
        if (cord <0 ) {
            return cord - 1.0;
        }

        return cord;
    }
    private ItemStack getRandomItem() {
        // Получение случайного типа предмета из перечисления Material
        Material[] materials = Material.values();
        Material randomMaterial = materials[random.nextInt(materials.length)];

        return new ItemStack(randomMaterial);
    }
    private String getRandomPlayer() { // метод получения рандомного игрока из списка онлайна
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
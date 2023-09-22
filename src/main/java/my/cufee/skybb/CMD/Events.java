package my.cufee.skybb.CMD;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.*;

import my.cufee.skybb.util.AllPoitoinEffects;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.*;


public class Events implements CommandExecutor {
    private final Random random;
    private final JavaPlugin plugin;

    public Events(JavaPlugin plugin) {
        this.random = new Random();
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                int chance = getRandomAmount(100);
                if (chance <= 60){
                    giveRandomItem();
                }
                if (chance >= 61 & chance <= 65){
                    EventPoition();
                }
                if (chance >= 66 & chance <= 68) {
                    EventZombie();
                }
                if (chance >= 69 & chance <= 74){
                    EventBedrockBox();
                }
                if (chance >= 75 & chance <= 79){
                    EventGetRandomEffect();
                }
                if (chance >= 80 & chance <= 86){
                    EventTNT();
                }
                if (chance >= 87 & chance <= 100){
                    EventRandomChest();
                }
            }
        };
        task.runTaskTimer(plugin, 0, 1200);

        return false;
    }


    private void EventPoition(){
        Player targetPlayer = Bukkit.getPlayer(getRandomPlayer());
        Location playerLocation = targetPlayer.getLocation();
        ItemStack potion = new ItemStack(Material.LINGERING_POTION);
        PotionMeta potionMeta = (PotionMeta) potion.getItemMeta();
        potionMeta.addCustomEffect(new PotionEffect(PotionEffectType.HARM, 5, 5), true);
// Устанавливаем уровень зелья на 2
        potionMeta.setBasePotionData(new PotionData(PotionType.INSTANT_DAMAGE, false, true));
        potion.setItemMeta(potionMeta);
        targetPlayer.getWorld().spawn(playerLocation.add(0, 1, 0), ThrownPotion.class, thrownPotion -> {
            thrownPotion.setItem(potion);
            thrownPotion.setShooter(targetPlayer);
        });
        Bukkit.broadcastMessage("Аирдроп был сброшен на " + targetPlayer.getName());
    }
    private void EventZombie(){
        Player targetPlayer = Bukkit.getPlayer(getRandomPlayer());
        Location playerLocation = targetPlayer.getLocation();
        Zombie ZombieBaby = (Zombie) playerLocation.getWorld().spawnEntity(playerLocation, EntityType.ZOMBIE);
        ZombieBaby.setBaby(true);
        ZombieBaby.setCustomName("ШКОЛЬНИК");
        ZombieBaby.setCustomNameVisible(true);
        ZombieBaby.setMaxHealth(400D);
        ZombieBaby.setHealth(400D);
        ZombieBaby.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 9999, 3));
        ItemStack ZombieHelmet = new ItemStack(Material.NETHERITE_HELMET);
        ItemStack ZombieChestplate = new ItemStack(Material.NETHERITE_CHESTPLATE);
        ItemStack ZombieLeggins = new ItemStack(Material.NETHERITE_LEGGINGS);
        ItemStack ZombieBoots = new ItemStack(Material.NETHERITE_BOOTS);
        ZombieBaby.getEquipment().setHelmet(ZombieHelmet);
        ZombieBaby.getEquipment().setChestplate(ZombieChestplate);
        ZombieBaby.getEquipment().setLeggings(ZombieLeggins);
        ZombieBaby.getEquipment().setBoots(ZombieBoots);
        ItemStack StickForZombie = new ItemStack(Material.STICK);
        StickForZombie.addUnsafeEnchantment(Enchantment.KNOCKBACK, 3);
        ZombieBaby.getEquipment().setItemInMainHand(StickForZombie);
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
        Bukkit.broadcastMessage(targetPlayer.getName() + " зашел в хату по 228");
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

    private void giveRandomItem(){
        for (Player player : Bukkit.getOnlinePlayers()) {
            final ItemStack randomItem = new ItemStack(getRandomItem().getType(), getRandomAmount(10));
            int randomAmount = getRandomAmount(34);

            player.getInventory().addItem(randomItem);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2&o Вы получили ") + randomItem.getAmount() + " " + ChatColor.translateAlternateColorCodes('&', "&c") + randomItem.getType().name());
        }
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
        return random.nextInt(max) +1;
    }
    private int getRandomAmountForItemChest() {
        // Получение случайного количества от 1 до 15
        return random.nextInt(15);
    }
    private int getRandomAmountForSpawnChest(){
        return random.nextInt(500 - -500 + 1) + -500;
    }
}
package my.cufee.skybb.util;



import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class AllPoitoinEffects {
    public static void main(String[] args) {

    }
    public static List<PotionEffectType> givePoitionEffects(){
        List<PotionEffectType> potionEffects = new ArrayList<>();
        // Заполняем список эффектами
        potionEffects.add(PotionEffectType.ABSORPTION);
        potionEffects.add(PotionEffectType.BAD_OMEN);
        potionEffects.add(PotionEffectType.BLINDNESS);
        potionEffects.add(PotionEffectType.CONDUIT_POWER);
        potionEffects.add(PotionEffectType.CONFUSION);
        potionEffects.add(PotionEffectType.DAMAGE_RESISTANCE);
        potionEffects.add(PotionEffectType.DARKNESS);
        potionEffects.add(PotionEffectType.FAST_DIGGING);
        potionEffects.add(PotionEffectType.FIRE_RESISTANCE);
        potionEffects.add(PotionEffectType.GLOWING);
        potionEffects.add(PotionEffectType.HARM);
        potionEffects.add(PotionEffectType.HERO_OF_THE_VILLAGE);
        potionEffects.add(PotionEffectType.HUNGER);
        potionEffects.add(PotionEffectType.INCREASE_DAMAGE);
        potionEffects.add(PotionEffectType.INVISIBILITY);
        potionEffects.add(PotionEffectType.JUMP);
        potionEffects.add(PotionEffectType.LEVITATION);
        potionEffects.add(PotionEffectType.NIGHT_VISION);
        potionEffects.add(PotionEffectType.POISON);
        potionEffects.add(PotionEffectType.REGENERATION);
        potionEffects.add(PotionEffectType.SATURATION);
        potionEffects.add(PotionEffectType.SLOW);
        potionEffects.add(PotionEffectType.SLOW_DIGGING);
        potionEffects.add(PotionEffectType.SLOW_FALLING);
        potionEffects.add(PotionEffectType.SPEED);
        potionEffects.add(PotionEffectType.UNLUCK);
        potionEffects.add(PotionEffectType.WATER_BREATHING);
        potionEffects.add(PotionEffectType.WEAKNESS);
        potionEffects.add(PotionEffectType.WITHER);
        return potionEffects;
    }
    public static PotionEffectType getRandomPotionEffect(List<PotionEffectType> potionEffects) {
        Random random = new Random();
        int index = random.nextInt(potionEffects.size());
        return potionEffects.get(index);
    }
}

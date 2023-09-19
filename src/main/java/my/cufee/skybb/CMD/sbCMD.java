package my.cufee.skybb.CMD;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class sbCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        int a,b;
        a = Integer.parseInt(args[0]);
        b = Integer.parseInt(args[2]);

        switch (args[1]) {
            case "+":
                commandSender.sendMessage("Result = " + (a + b));
                return true;
            case "-":
                commandSender.sendMessage("Result = " + (a - b));
                return true;
            case "*":
                commandSender.sendMessage("Result = " + (a * b));
                return true;
            case "/":
                commandSender.sendMessage("Result = " + (a / b));
                return true;
        }
        return false;
    }
}

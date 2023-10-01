package mxzx.command;

import co.aikar.commands.annotation.CatchUnknown;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.Default;
import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import mxzx._core.BasedCommand;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.StringReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@CommandAlias("debug")
public class DebugCommand extends BasedCommand {

    @Default
    public void onMessage(Player player) {
        player.sendMessage("/msg <Player> <Message>");
    }

    @CatchUnknown
    @CommandCompletion("input")
    public void onCommand(Player player, String input) {
         String location = input;
         Bukkit.broadcastMessage(location);
         CSVParser parser = new CSVParser();
         CSVReader csvReader = new CSVReader(new StringReader(location));

         try {
             // Read the header line to get column names
             String[] header = csvReader.readNext();
             Bukkit.broadcastMessage(Arrays.toString(header));

             // Create a list to store the maps
             Map<String, String> map;
             Map<String, String> result = new HashMap<>();
         } catch (Exception e) {
             e.printStackTrace();
         }
     }
}

package com.kasetoatz.tridenthacks.config;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.Gson;
import com.kasetoatz.tridenthacks.TridentHacks;
import net.minecraft.util.crash.CrashException;
import net.minecraft.util.crash.CrashReport;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
    public static boolean toggleRiptide = true;
    public static boolean noFallDamage = true;
    public static boolean returnToSameSlot = true;
    public static String riptideOnMessage = "Riptide §aON";
    public static String riptideOffMessage = "Riptide §cOFF";

    public static void load() {
        File file = new File(TridentHacks.client.runDirectory, "config/tridenthacks.json");
        if (!file.exists())
        {
            save();
            return;
        }
        try (FileReader reader = new FileReader(file))
        {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonObject json = gson.fromJson(reader, JsonObject.class);
            if (json.has("toggleRiptide"))
            {
                toggleRiptide = json.get("toggleRiptide").getAsBoolean();
            }
            if (json.has("noFallDamage"))
            {
                noFallDamage = json.get("noFallDamage").getAsBoolean();
            }
            if (json.has("returnToSameSlot"))
            {
                returnToSameSlot = json.get("returnToSameSlot").getAsBoolean();
            }
            if (json.has("riptideOnMessage"))
            {
                riptideOnMessage = json.get("riptideOnMessage").getAsString();
            }
            if (json.has("riptideOffMessage"))
            {
                riptideOffMessage = json.get("riptideOffMessage").getAsString();
            }
        }
        catch (IOException exc)
        {
            throw new CrashException(CrashReport.create(exc, "Loading config file."));
        }
    }

    public static void save()
    {
        JsonObject json = new JsonObject();
        json.addProperty("toggleRiptide", toggleRiptide);
        json.addProperty("noFallDamage", noFallDamage);
        json.addProperty("returnToSameSlot", returnToSameSlot);
        json.addProperty("riptideOnMessage", riptideOnMessage);
        json.addProperty("riptideOffMessage", riptideOffMessage);
        try (FileWriter writer = new FileWriter(new File(TridentHacks.client.runDirectory, "config/tridenthacks.json")))
        {
            new GsonBuilder().setPrettyPrinting().create().toJson(json, writer);
        }
        catch (IOException exc)
        {
            throw new CrashException(CrashReport.create(exc, "Saving config file."));
        }
    }
}

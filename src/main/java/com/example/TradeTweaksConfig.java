package com.example;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("tradetweaks")
public interface TradeTweaksConfig extends Config
{
    @ConfigItem(
        keyName = "spamTrade",
        name = "Trade Request Spam",
        description = "Repeat incoming trade requests seven times in chat"
    )
    default boolean spamTrade()
    {
        return true;
    }
}

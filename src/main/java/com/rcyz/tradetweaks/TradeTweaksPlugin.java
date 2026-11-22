package com.rcyz.tradetweaks;

import com.google.inject.Provides;
import javax.inject.Inject;

import net.runelite.api.Client;
import net.runelite.api.ChatMessageType;
import net.runelite.api.events.ChatMessage;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@PluginDescriptor(
    name = "Trade Tweaks",
    description = "Repeats incoming trade requests in chat",
    tags = {"trade", "chat"}
)
public class TradeTweaksPlugin extends Plugin
{
    @Inject
    private Client client;

    @Inject
    private TradeTweaksConfig config;

    private boolean shouldSpam = true;

    @Provides
    TradeTweaksConfig provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(TradeTweaksConfig.class);
    }

    @Subscribe
    public void onChatMessage(ChatMessage message)
    {
        if (config.spamTrade()
            && message.getType() == ChatMessageType.TRADEREQ
            && shouldSpam)
        {
            shouldSpam = false;

            try
            {
                for (int i = 0; i < 7; i++)
                {
                    client.addChatMessage(
                        message.getType(),
                        message.getName(),
                        message.getMessage(),
                        message.getSender()
                    );
                }
            }
            finally
            {
                shouldSpam = true;
            }
        }
    }
}

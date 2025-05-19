package com.jazzkuh.inventorylib.listeners;

import com.jazzkuh.inventorylib.objects.Menu;
import com.jazzkuh.inventorylib.objects.icon.Icon;
import net.kyori.adventure.sound.Sound;
import net.minestom.server.component.DataComponents;
import net.minestom.server.entity.Player;
import net.minestom.server.event.EventListener;
import net.minestom.server.event.inventory.InventoryPreClickEvent;
import net.minestom.server.inventory.click.Click;
import net.minestom.server.inventory.click.ClickType;
import net.minestom.server.item.ItemStack;
import net.minestom.server.sound.SoundEvent;
import net.minestom.server.tag.Tag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class ButtonInventoryClickListener implements EventListener<InventoryPreClickEvent> {
    
    @Override
    public @NotNull Class<InventoryPreClickEvent> eventType() {
        return InventoryPreClickEvent.class;
    }

    @Override
    public Result run(InventoryPreClickEvent event) {
        ItemStack itemStack = event.getClickedItem();
        if (event.getPlayer().getOpenInventory() == null) return Result.SUCCESS;
        if (!(event.getPlayer().getOpenInventory() instanceof Menu menu)) return Result.SUCCESS;

        if (!(event.getClick() instanceof Click.Left) && !(event.getClick() instanceof Click.Right) && !(event.getClick() instanceof Click.LeftShift) && !(event.getClick() instanceof Click.RightShift)) {
            if (menu.isCancelClick()) event.setCancelled(true);
            return Result.SUCCESS;
        }

        if (menu.isCancelClick()) event.setCancelled(true);

        if (hasCustomData(itemStack, "icon_identifier")) {
            String buttonId = getCustomData(itemStack, "icon_identifier");
            assert buttonId != null;
            Icon icon = menu.getIcon(UUID.fromString(buttonId));
            if (icon != null) {
                if (icon.isSound()) {
                    Player player = event.getPlayer();
                    player.playSound(Sound.sound(SoundEvent.UI_BUTTON_CLICK, Sound.Source.PLAYER, 1F, 1F));
                }

                if (icon.getClickEvent() == null) return Result.SUCCESS;
                icon.getClickEvent().accept(event);
            }
        } else menu.onClick(event);
        return Result.SUCCESS;
    }

    private boolean hasCustomData(ItemStack itemStack, String key) {
        if (!itemStack.has(DataComponents.CUSTOM_DATA)) return false;
        return itemStack.get(DataComponents.CUSTOM_DATA).hasTag(Tag.String(key));
    }

    @Nullable
    private String getCustomData(ItemStack itemStack, String key) {
        if (!itemStack.has(DataComponents.CUSTOM_DATA)) return null;
        if (!hasCustomData(itemStack, key)) return null;
        return itemStack.get(DataComponents.CUSTOM_DATA).getTag(Tag.String(key));
    }
}

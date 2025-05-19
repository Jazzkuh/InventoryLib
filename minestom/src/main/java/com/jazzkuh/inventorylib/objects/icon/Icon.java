package com.jazzkuh.inventorylib.objects.icon;

import lombok.Data;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.minestom.server.component.DataComponents;
import net.minestom.server.event.inventory.InventoryPreClickEvent;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.component.CustomData;
import net.minestom.server.tag.Tag;

import java.util.UUID;
import java.util.function.Consumer;

@Data
public final class Icon {

    private UUID uniqueId;
    private ItemStack item;
    private Consumer<InventoryPreClickEvent> clickEvent;
    private boolean sound;

    private Integer slot;
    
    public Icon(int slot, ItemStack item) {
        this.slot = slot;
        this.item = item;
        this.sound = true;
        this.clickEvent = null;
        this.uniqueId = UUID.randomUUID();
    }

    public Icon(int slot, ItemStack item, Consumer<InventoryPreClickEvent> clickEvent) {
        this.slot = slot;
        this.item = item;
        this.sound = true;
        this.clickEvent = clickEvent;
        this.uniqueId = UUID.randomUUID();
    }

    public Icon(int slot, ItemStack item, boolean hasSound) {
        this.slot = slot;
        this.item = item;
        this.sound = hasSound;
        this.clickEvent = null;
        this.uniqueId = UUID.randomUUID();
    }

    public Icon(int slot, ItemStack item, boolean hasSound, Consumer<InventoryPreClickEvent> clickEvent) {
        this.slot = slot;
        this.item = item;
        this.sound = hasSound;
        this.clickEvent = clickEvent;
        this.uniqueId = UUID.randomUUID();
    }

    public Icon(ItemStack item) {
        this.slot = null;
        this.item = item;
        this.sound = true;
        this.clickEvent = null;
        this.uniqueId = UUID.randomUUID();
    }

    public Icon(ItemStack item, Consumer<InventoryPreClickEvent> clickEvent) {
        this.slot = null;
        this.item = item;
        this.sound = true;
        this.clickEvent = clickEvent;
        this.uniqueId = UUID.randomUUID();
    }

    public Icon(ItemStack item, boolean hasSound) {
        this.slot = null;
        this.item = item;
        this.sound = hasSound;
        this.clickEvent = null;
        this.uniqueId = UUID.randomUUID();
    }

    public Icon(ItemStack item, boolean hasSound, Consumer<InventoryPreClickEvent> clickEvent) {
        this.item = item;
        this.sound = hasSound;
        this.clickEvent = clickEvent;
        this.uniqueId = UUID.randomUUID();
    }

    public ItemStack getItem() {
        return this.getItem(true);
    }

    public ItemStack getItem(boolean withIdentifier) {
        if (!withIdentifier) return this.item;

        ItemStack itemStack = this.item;
        if (itemStack.has(DataComponents.CUSTOM_DATA)) {
            CustomData customData = itemStack.get(DataComponents.CUSTOM_DATA);
            customData = customData.withTag(Tag.String("icon_identifier"), this.uniqueId.toString());
            itemStack = itemStack.with(DataComponents.CUSTOM_DATA, customData);
        } else {
            itemStack = itemStack.with(DataComponents.CUSTOM_DATA, new CustomData(CompoundBinaryTag.builder().putString("icon_identifier", this.uniqueId.toString()).build()));
        }

        return itemStack;
    }

}

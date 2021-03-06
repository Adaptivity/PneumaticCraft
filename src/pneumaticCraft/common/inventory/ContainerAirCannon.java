package pneumaticCraft.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import pneumaticCraft.common.item.Itemss;
import pneumaticCraft.common.tileentity.TileEntityAirCannon;

public class ContainerAirCannon extends Container{
    TileEntityAirCannon te;

    public ContainerAirCannon(InventoryPlayer inventoryPlayer, TileEntityAirCannon teAirCannon){
        te = teAirCannon;
        te.openGUI();

        // add the cannoned slot.
        addSlotToContainer(new Slot(teAirCannon, 0, 79, 40));

        // add the gps slot
        addSlotToContainer(new SlotItemSpecific(teAirCannon, Itemss.GPSTool, 1, 51, 29));

        // add the upgrade slots
        addSlotToContainer(new SlotUpgrade(teAirCannon, 2, 8, 29));
        addSlotToContainer(new SlotUpgrade(teAirCannon, 3, 26, 29));
        addSlotToContainer(new SlotUpgrade(teAirCannon, 4, 8, 47));
        addSlotToContainer(new SlotUpgrade(teAirCannon, 5, 26, 47));

        // Add the player's inventory slots to the container
        for(int inventoryRowIndex = 0; inventoryRowIndex < 3; ++inventoryRowIndex) {
            for(int inventoryColumnIndex = 0; inventoryColumnIndex < 9; ++inventoryColumnIndex) {
                addSlotToContainer(new Slot(inventoryPlayer, inventoryColumnIndex + inventoryRowIndex * 9 + 9, 8 + inventoryColumnIndex * 18, 84 + inventoryRowIndex * 18));
            }
        }

        // Add the player's action bar slots to the container
        for(int actionBarSlotIndex = 0; actionBarSlotIndex < 9; ++actionBarSlotIndex) {
            addSlotToContainer(new Slot(inventoryPlayer, actionBarSlotIndex, 8 + actionBarSlotIndex * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player){

        return te.isGuiUseableByPlayer(player);
    }

    /**
     * @param itemStack
     *            ItemStack to merge into inventory
     * @param start
     *            minimum slot to attempt fill
     * @param end
     *            maximum slot to attempt fill
     * @param backwards
     *            go backwards
     * @return true if stacks merged successfully public boolean
     *         mergeItemStack(itemStack, start, end, backwards)
     */

    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2){

        ItemStack var3 = null;
        Slot var4 = (Slot)inventorySlots.get(par2);

        if(var4 != null && var4.getHasStack()) {
            ItemStack var5 = var4.getStack();
            var3 = var5.copy();

            if(par2 < 6) {
                if(!mergeItemStack(var5, 6, 41, false)) return null;
                var4.onSlotChange(var5, var3);
            } else {
                if(var3.getItem() == Itemss.GPSTool) {
                    if(!mergeItemStack(var5, 1, 2, false)) return null;
                } else if(var3.getItem() == Itemss.machineUpgrade) {
                    if(!mergeItemStack(var5, 2, 6, false)) return null;
                } else {
                    if(!mergeItemStack(var5, 0, 1, false)) return null;
                }
                var4.onSlotChange(var5, var3);
            }

            if(var5.stackSize == 0) {
                var4.putStack((ItemStack)null);
            } else {
                var4.onSlotChanged();
            }

            if(var5.stackSize == var3.stackSize) return null;

            var4.onPickupFromSlot(par1EntityPlayer, var5);
        }

        return var3;
    }

    @Override
    public void onContainerClosed(EntityPlayer par1EntityPlayer){
        super.onContainerClosed(par1EntityPlayer);
        te.closeGUI();
    }

}

package pneumaticCraft.common.thirdparty.igwmod;

import igwmod.gui.GuiWiki;
import igwmod.gui.tabs.BaseWikiTab;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import pneumaticCraft.common.block.Blockss;

public class PneumaticCraftWikiTab extends BaseWikiTab{

    public PneumaticCraftWikiTab(){
        pageEntries.add("worldgen");
        pageEntries.add("baseConcepts");
        pageEntries.add("machineUpgrades");
        pageEntries.add("generatingPressure");
        pageEntries.add("pressureTubes");
        pageEntries.add("pressureChamber");
        skipLine();
        pageEntries.add("block/omnidirectionalHopper");
        pageEntries.add("block/airCannon");
        pageEntries.add("elevator");
        pageEntries.add("pneumaticDoor");
        pageEntries.add("block/universalSensor");
        pageEntries.add("block/aerialInterface");
        skipLine();
        pageEntries.add("block/chargingStation");
        pageEntries.add("item/pneumaticHelmet");
        pageEntries.add("item/drone");
        skipLine();
        pageEntries.add("printedCircuitBoards");
        pageEntries.add("assemblyMachines");
        pageEntries.add("block/aphorismTile");
        pageEntries.add("block/securityStation");
        skipLine();
        pageEntries.add("ic2Integration");
        pageEntries.add("bcIntegration");
        pageEntries.add("ccIntegration");

    }

    @Override
    public String getName(){
        return "PneumaticCraft";
    }

    @Override
    public ItemStack renderTabIcon(GuiWiki gui){
        return new ItemStack(Blockss.airCannon);
    }

    @Override
    protected String getPageName(String pageEntry){
        if(pageEntry.startsWith("item") || pageEntry.startsWith("block")) {
            return I18n.format(pageEntry.replace("/", ".").replace("block", "tile") + ".name");
        } else {
            return I18n.format("igwtab.entry." + pageEntry);
        }
    }

    @Override
    protected String getPageLocation(String pageEntry){
        if(pageEntry.startsWith("item") || pageEntry.startsWith("block")) return pageEntry;
        return "menu/" + pageEntry;
    }

}

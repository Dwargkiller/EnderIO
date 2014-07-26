package crazypants.enderio.machine.farm;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public final class FarmersRegistry {

  public static void addFarmers() {
    //vanilla
    FarmersCommune.instance.joinCommune(new MelonFarmer(Blocks.melon_stem, Blocks.melon_block, new ItemStack(Items.melon_seeds)));
    FarmersCommune.instance.joinCommune(new MelonFarmer(Blocks.pumpkin_stem, Blocks.pumpkin, new ItemStack(Items.pumpkin_seeds)));
    FarmersCommune.instance.joinCommune(new SeedFarmer(Blocks.potatoes, new ItemStack(Items.potato)));
    FarmersCommune.instance.joinCommune(new SeedFarmer(Blocks.wheat, new ItemStack(Items.wheat_seeds)));
    FarmersCommune.instance.joinCommune(new SeedFarmer(Blocks.carrots, new ItemStack(Items.carrot)));
    FarmersCommune.instance.joinCommune(new NetherWartFarmer());
    FarmersCommune.instance.joinCommune(new StemFarmer(Blocks.reeds, new ItemStack(Items.reeds)));
    FarmersCommune.instance.joinCommune(new StemFarmer(Blocks.cactus, new ItemStack(Blocks.cactus)));
    FarmersCommune.instance.joinCommune(new TreeFarmer(Blocks.sapling, Blocks.log));
    FarmersCommune.instance.joinCommune(new TreeFarmer(Blocks.sapling, Blocks.log2));

    addExtraUtilities();
    addNutura();
    addTiC();
    addStillHungry();
  }

  private static void addStillHungry() {
    String mod = "stillhungry";          
    addSeed(mod, "strawberryBlock", "StillHungry_strawberrySeed");        
    addPickable(mod, "grapeBlock", "StillHungry_grapeSeed");
    addSeed(mod, "riceBlock", "StillHungry_riceSeed");
  }


  private static void addExtraUtilities() {
    String mod = "ExtraUtilities";
    String name = "plant/ender_lilly";
    
    SeedFarmer farmer = addSeed(mod, name, name, Blocks.end_stone, GameRegistry.findBlock(mod, "decorativeBlock1"));
    if(farmer != null) {
      farmer.setIgnoreGroundCanSustainCheck(true);
    }
  }
  
  private static void addPickable(String mod, String blockName, String itemName) {
    Block cropBlock = GameRegistry.findBlock(mod, blockName);
    if(cropBlock != null) {
      Item seedItem = GameRegistry.findItem(mod, itemName);
      if(seedItem != null) {
        FarmersCommune.instance.joinCommune(new PickableFarmer(cropBlock, new ItemStack(seedItem)));
      }
    }
  }
  
  private static SeedFarmer addSeed(String mod, String blockName, String itemName, Block... extraFarmland) {
    Block cropBlock = GameRegistry.findBlock(mod, blockName);
    if(cropBlock != null) {
      Item seedItem = GameRegistry.findItem(mod, itemName);
      if(seedItem != null) {
        SeedFarmer farmer = new SeedFarmer(cropBlock, new ItemStack(seedItem));
        if(extraFarmland != null) {
          for(Block farmland : extraFarmland) {
            if(farmland != null) {
              farmer.addTilledBlock(farmland);
            }
          }
        }
        FarmersCommune.instance.joinCommune(farmer);
        return farmer;
      }
    }
    return null;
  }

  private static void addTiC() {
    String mod = "TConstruct";
    String blockName = "ore.berries.two";

    Block cropBlock = GameRegistry.findBlock(mod, blockName);
    if(cropBlock != null) {
      Item seedItem = GameRegistry.findItem(mod, blockName);
      if(seedItem != null) {
        for (int i = 0; i < 2; i++) {
          PickableFarmer farmer = new NaturaBerryFarmer(cropBlock, i, 12 + i, new ItemStack(seedItem, 1, 8 + i));
          farmer.setRequiresFarmland(false);
          FarmersCommune.instance.joinCommune(farmer);
        }
      }
    }

    blockName = "ore.berries.one";
    cropBlock = GameRegistry.findBlock(mod, blockName);
    if(cropBlock != null) {
      Item seedItem = GameRegistry.findItem(mod, blockName);
      if(seedItem != null) {
        for (int i = 0; i < 4; i++) {
          PickableFarmer farmer = new NaturaBerryFarmer(cropBlock, i, 12 + i, new ItemStack(seedItem, 1, 8 + i));
          farmer.setRequiresFarmland(false);
          FarmersCommune.instance.joinCommune(farmer);
        }
      }
    }

  }

  private static void addNutura() {
    String mod = "Natura";
    String blockName = "N Crops";

    Block cropBlock = GameRegistry.findBlock(mod, blockName);
    if(cropBlock != null) {
      Item seedItem = GameRegistry.findItem(mod, "barley.seed");
      if(seedItem != null) {
        //barley
        FarmersCommune.instance.joinCommune(new SeedFarmer(cropBlock, 3, new ItemStack(seedItem)));
        // cotton
        FarmersCommune.instance.joinCommune(new PickableFarmer(cropBlock, 4, 8, new ItemStack(seedItem, 1, 1)));
      }
    }

    blockName = "BerryBush";
    cropBlock = GameRegistry.findBlock(mod, blockName);
    if(cropBlock != null) {
      Item seedItem = GameRegistry.findItem(mod, blockName);
      if(seedItem != null) {
        for (int i = 0; i < 4; i++) {
          PickableFarmer farmer = new NaturaBerryFarmer(cropBlock, i, 12 + i, new ItemStack(seedItem, 1, 12 + i));
          farmer.setRequiresFarmland(false);
          FarmersCommune.instance.joinCommune(farmer);
        }
      }
    }
    
    blockName = "florasapling";
    Block saplingBlock = GameRegistry.findBlock(mod, blockName);
    if(saplingBlock != null) {                 
      FarmersCommune.instance.joinCommune(new TreeFarmer(saplingBlock, 
          GameRegistry.findBlock(mod, "tree"), 
          GameRegistry.findBlock(mod, "willow"),
          GameRegistry.findBlock(mod, "Dark Tree")));                
    }
    blockName = "Rare Sapling";
    saplingBlock = GameRegistry.findBlock(mod, blockName);
    if(saplingBlock != null) {                 
      FarmersCommune.instance.joinCommune(new TreeFarmer(saplingBlock, GameRegistry.findBlock(mod, "Rare Tree")));                
    }
       
  }

  private FarmersRegistry() {
  }

}

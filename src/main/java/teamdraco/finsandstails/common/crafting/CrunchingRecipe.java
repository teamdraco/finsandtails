package teamdraco.finsandstails.common.crafting;

import com.google.gson.JsonObject;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;
import teamdraco.finsandstails.registry.FTBlocks;
import teamdraco.finsandstails.registry.FTItems;
import teamdraco.finsandstails.registry.FTRecipes;

public class CrunchingRecipe implements IRecipe<CraftingInventory> {
    private final Ingredient base;
    private final Ingredient addition;
    private final ItemStack result;
    private final ResourceLocation recipeId;

    public CrunchingRecipe(ResourceLocation recipeId, Ingredient base, Ingredient addition, ItemStack result) {
        this.recipeId = recipeId;
        this.base = base;
        this.addition = addition;
        this.result = result;
    }

    @Override
    public boolean matches(CraftingInventory inv, World worldIn) {
        return this.base.test(inv.getItem(0)) && this.addition.test(inv.getItem(1));
    }

    @Override
    public ItemStack assemble(CraftingInventory inv) {
        ItemStack itemstack = this.result.copy();
        return itemstack;
    }

    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    public ItemStack getResultItem() {
        return this.result;
    }

    public ItemStack getToastSymbol() {
        return new ItemStack(FTBlocks.CRAB_CRUNCHER.get());
    }

    public ResourceLocation getId() {
        return this.recipeId;
    }

    public IRecipeSerializer<?> getSerializer() {
        return FTRecipes.CRUNCHING_SERIALIZER.get();
    }

    public IRecipeType<?> getType() {
        return FTRecipes.CRUNCHING_TYPE;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> i = NonNullList.create();

        i.add(base);
        i.add(addition);
        i.add(Ingredient.of(new ItemStack(FTItems.CRAB_CRUNCHER.get())));

        return i;
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<CrunchingRecipe> {

        @Override
        public CrunchingRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            Ingredient ingredient = Ingredient.fromJson(JSONUtils.getAsJsonObject(json, "base"));
             Ingredient ingredient1 = Ingredient.fromJson(JSONUtils.getAsJsonObject(json, "addition"));
            ItemStack itemstack = ShapedRecipe.itemFromJson(JSONUtils.getAsJsonObject(json, "result"));
            return new CrunchingRecipe(recipeId, ingredient, ingredient1, itemstack);
        }

        @Override
        public CrunchingRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
            Ingredient ingredient = Ingredient.fromNetwork(buffer);
            Ingredient ingredient1 = Ingredient.fromNetwork(buffer);
            ItemStack itemstack = buffer.readItem();
            return new CrunchingRecipe(recipeId, ingredient, ingredient1, itemstack);
        }

        @Override
        public void toNetwork(PacketBuffer buffer, CrunchingRecipe recipe) {
            recipe.base.toNetwork(buffer);
            recipe.addition.toNetwork(buffer);
            buffer.writeItem(recipe.result);
        }
    }
}

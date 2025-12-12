package com.github.frixel.frixelengine.item.recipe

import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.Recipe
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.inventory.ShapelessRecipe
import org.bukkit.plugin.java.JavaPlugin

fun Recipe.builder(key: NamespacedKey, item: ItemStack): RecipeBuilder {
    return RecipeBuilder(key, item)
}

class RecipeBuilder(
    private val key: NamespacedKey,
    private val item: ItemStack
) {
    private lateinit var recipe: Recipe

    @JvmName("addShapeRecipeMaterial")
    fun addShapeRecipe(shape: Array<Material>) {
        addShapeRecipe(shape.map { ItemStack(it) }.toTypedArray())
    }

    fun addShapeRecipe(shape: Array<ItemStack>) {
        recipe = ShapedRecipe(key, item)

        val char = "ABCDEFGHI"
        shape.forEachIndexed { index, item ->
            (recipe as ShapedRecipe).shape("ABC", "DEF", "GHI")
            (recipe as ShapedRecipe).setIngredient(char[index], item)
        }
    }

    @JvmName("addShapelessRecipeMaterial")
    fun addShapelessRecipe(items: Map<Material, Int>) {
        addShapelessRecipe(items.mapKeys { ItemStack(it.key) }.toMap())
    }

    fun addShapelessRecipe(items: Map<ItemStack, Int>) {
        recipe = ShapelessRecipe(key, item)
        items.forEach { (material, amount) ->
            (recipe as ShapelessRecipe).addIngredient(amount, material)
        }
    }

    fun build(plugin: JavaPlugin) {
        if (::recipe.isInitialized) {
            plugin.server.addRecipe(recipe)
        } else {
            throw IllegalStateException("Recipe is not initialized. Please add a shape or shapeless recipe before building.")
        }
    }
}
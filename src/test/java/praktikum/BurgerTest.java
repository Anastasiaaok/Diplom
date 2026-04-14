package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class BurgerTest {

    private Burger burger;
    private Bun bun;
    private Ingredient ingredient;

    private final float bunPrice;
    private final float ingredientPrice;
    private final float expectedPrice;

    public BurgerTest(float bunPrice, float ingredientPrice, float expectedPrice) {
        this.bunPrice = bunPrice;
        this.ingredientPrice = ingredientPrice;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {100f, 50f, 250f},
                {200f, 30f, 430f},
                {150f, 70f, 370f}
        });
    }

    @Before
    public void setUp() {
        burger = new Burger();

        bun = mock(Bun.class);
        when(bun.getPrice()).thenReturn(bunPrice);
        when(bun.getName()).thenReturn("Булка");

        ingredient = mock(Ingredient.class);
        when(ingredient.getPrice()).thenReturn(ingredientPrice);
        when(ingredient.getName()).thenReturn("Ингредиент");
        when(ingredient.getType()).thenReturn(IngredientType.FILLING);
    }

    @Test
    public void setBunsShouldSetBun() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientShouldIncreaseSize() {
        burger.addIngredient(ingredient);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void addIngredientShouldAddElement() {
        burger.addIngredient(ingredient);
        assertEquals(ingredient, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientShouldDecreaseSize() {
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);
        assertEquals(0, burger.ingredients.size());
    }

    @Test
    public void moveIngredientShouldChangePosition() {
        Ingredient secondIngredient = mock(Ingredient.class);

        burger.addIngredient(ingredient);
        burger.addIngredient(secondIngredient);

        burger.moveIngredient(0, 1);

        assertEquals(secondIngredient, burger.ingredients.get(0));
    }

    @Test
    public void getPriceShouldCalculateCorrectly() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        assertEquals(expectedPrice, burger.getPrice(), 0.001);
    }

    @Test
    public void getReceiptShouldContainBun() {
        burger.setBuns(bun);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("Булка"));
    }

    @Test
    public void getReceiptShouldContainIngredient() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("Ингредиент"));
    }
}

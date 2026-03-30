package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class BurgerTest {

    private Burger burger;
    private Bun bun;

    private Ingredient ingredient1;
    private Ingredient ingredient2;

    private float bunPrice;
    private float ingredientPrice1;
    private float ingredientPrice2;
    private float expectedPrice;

    public BurgerTest(float bunPrice, float ingredientPrice1, float ingredientPrice2, float expectedPrice) {
        this.bunPrice = bunPrice;
        this.ingredientPrice1 = ingredientPrice1;
        this.ingredientPrice2 = ingredientPrice2;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {100f, 50f, 30f, 280f},
                {200f, 0f, 0f, 400f},
                {50f, 25f, 25f, 150f}
        };
    }

    @Before
    public void setUp() {
        burger = new Burger();

        bun = mock(Bun.class);
        when(bun.getPrice()).thenReturn(bunPrice);
        when(bun.getName()).thenReturn("Булка");

        ingredient1 = mock(Ingredient.class);
        when(ingredient1.getPrice()).thenReturn(ingredientPrice1);
        when(ingredient1.getName()).thenReturn("Ингредиент1");
        when(ingredient1.getType()).thenReturn(IngredientType.FILLING);

        ingredient2 = mock(Ingredient.class);
        when(ingredient2.getPrice()).thenReturn(ingredientPrice2);
        when(ingredient2.getName()).thenReturn("Ингредиент2");
        when(ingredient2.getType()).thenReturn(IngredientType.SAUCE);

        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
    }

    @Test
    public void setBunsTest() {
        Burger burger = new Burger();
        Bun bun = mock(Bun.class);

        burger.setBuns(bun);

        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientTest() {
        Burger burger = new Burger();
        Ingredient ingredient = mock(Ingredient.class);

        burger.addIngredient(ingredient);

        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredient, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientTest() {
        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredient2, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientTest() {
        burger.moveIngredient(0, 1);

        assertEquals(ingredient2, burger.ingredients.get(0));
        assertEquals(ingredient1, burger.ingredients.get(1));
    }

    @Test
    public void getPriceTest() {
        float actualPrice = burger.getPrice();
        assertEquals(expectedPrice, actualPrice, 0.001);
    }

    @Test
    public void getReceiptTest() {
        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("Булка"));
        assertTrue(receipt.contains("Ингредиент1"));
        assertTrue(receipt.contains("Ингредиент2"));
        assertTrue(receipt.contains("filling"));
        assertTrue(receipt.contains("sauce"));
        assertTrue(receipt.contains("Price"));
    }
}

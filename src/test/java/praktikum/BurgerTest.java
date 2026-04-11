package praktikum;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BurgerTest {

    private Burger burger;
    private Bun bun;
    private Ingredient ingredient;

    @Before
    public void setUp() {
        burger = new Burger();

        bun = mock(Bun.class);
        when(bun.getPrice()).thenReturn(100f);
        when(bun.getName()).thenReturn("Булка");

        ingredient = mock(Ingredient.class);
        when(ingredient.getPrice()).thenReturn(50f);
        when(ingredient.getName()).thenReturn("Ингредиент");
        when(ingredient.getType()).thenReturn(IngredientType.FILLING);
    }

    @Test
    public void setBunsTest() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientTest_size() {
        burger.addIngredient(ingredient);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void addIngredientTest_element() {
        burger.addIngredient(ingredient);
        assertEquals(ingredient, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientTest_size() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);
        assertEquals(0, burger.ingredients.size());
    }

    @Test
    public void moveIngredientTest_position() {
        Ingredient secondIngredient = mock(Ingredient.class);

        burger.addIngredient(ingredient);
        burger.addIngredient(secondIngredient);

        burger.moveIngredient(0, 1);

        assertEquals(secondIngredient, burger.ingredients.get(0));
    }

    @Test
    public void getPriceTest() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        float expectedPrice = 100f * 2 + 50f;

        assertEquals(expectedPrice, burger.getPrice(), 0.001);
    }

    @Test
    public void getReceiptTest_containsBun() {
        burger.setBuns(bun);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("Булка"));
    }

    @Test
    public void getReceiptTest_containsIngredient() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("Ингредиент"));
    }
}

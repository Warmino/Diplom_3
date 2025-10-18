import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import pages.BurgerBuilderSectionsPage;

public class BurgerBuilderSectionsTest extends BaseTest {

    @Test
    @DisplayName("Навигация к секции 'Булки'")
    @Description("Проверка активации секции 'Булки' после перехода")
    public void testNavigateToBunsSection()  {


        BurgerBuilderSectionsPage sectionsPage = new BurgerBuilderSectionsPage(driver);


        sectionsPage.navigateToSaucesSection();
        sectionsPage.navigateToBunsSection();


        boolean isBunsSectionActive = sectionsPage.isSectionActive("Булки");

        Assert.assertTrue("Секция 'Булки' не активна", isBunsSectionActive);


    }

    @Test
    @DisplayName("Навигация к секции 'Соусы'")
    @Description("Проверка активации секции 'Соусы' после перехода")
    public void testNavigateToSaucesSection() {


        BurgerBuilderSectionsPage sectionsPage = new BurgerBuilderSectionsPage(driver);


        sectionsPage.navigateToSaucesSection();


        boolean isSaucesSectionActive = sectionsPage.isSectionActive("Соусы");

        Assert.assertTrue("Секция 'Соусы' не активна", isSaucesSectionActive);


    }

    @Test
    @DisplayName("Навигация к секции 'Начинки'")
    @Description("Проверка активации секции 'Начинки' после перехода")
    public void testNavigateToFillingsSection() {


        BurgerBuilderSectionsPage sectionsPage = new BurgerBuilderSectionsPage(driver);


        sectionsPage.navigateToFillingsSection();


        boolean isFillingsSectionActive = sectionsPage.isSectionActive("Начинки");

        Assert.assertTrue("Секция 'Начинки' не активна", isFillingsSectionActive);


    }
}

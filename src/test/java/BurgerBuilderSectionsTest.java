import Base.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import pages.BurgerBuilderSectionsPage;

public class BurgerBuilderSectionsTest extends BaseTest {

    @Test
    public void testNavigateToBunsSection()  {


        BurgerBuilderSectionsPage sectionsPage = new BurgerBuilderSectionsPage(driver);


        sectionsPage.navigateToSaucesSection();
        sectionsPage.navigateToBunsSection();


        boolean isBunsSectionActive = sectionsPage.isSectionActive("Булки");

        Assert.assertTrue("Секция 'Булки' не активна", isBunsSectionActive);


    }

    @Test
    public void testNavigateToSaucesSection() {


        BurgerBuilderSectionsPage sectionsPage = new BurgerBuilderSectionsPage(driver);


        sectionsPage.navigateToSaucesSection();


        boolean isSaucesSectionActive = sectionsPage.isSectionActive("Соусы");

        Assert.assertTrue("Секция 'Соусы' не активна", isSaucesSectionActive);


    }

    @Test
    public void testNavigateToFillingsSection() {


        BurgerBuilderSectionsPage sectionsPage = new BurgerBuilderSectionsPage(driver);


        sectionsPage.navigateToFillingsSection();


        boolean isFillingsSectionActive = sectionsPage.isSectionActive("Начинки");

        Assert.assertTrue("Секция 'Начинки' не активна", isFillingsSectionActive);


    }
}

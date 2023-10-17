package mds.framework.test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class mytest {

    @Test
    void test(){
    Playwright.CreateOptions createOptions = new Playwright.CreateOptions();
    Map<String,String> map = new HashMap<>();
        map.put("PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD", "1");
        createOptions.setEnv(map);
        try (Playwright playwright = Playwright.create(createOptions)) {
        Browser browser = playwright.chromium().launch();
        Page page = browser.newPage();
        page.navigate("http://playwright.dev");

        // Expect a title "to contain" a substring.
        assertThat(page).hasTitle(Pattern.compile("Playwright"));

        // create a locator
        Locator getStarted = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Get Started"));

        // Expect an attribute "to be strictly equal" to the value.
        assertThat(getStarted).hasAttribute("href", "/docs/intro");

        // Click the get started link.
        getStarted.click();

        // Expects page to have a heading with the name of Installation.
        assertThat(page.getByRole(AriaRole.HEADING,
                new Page.GetByRoleOptions().setName("Installation"))).isVisible();
        }
    }
}

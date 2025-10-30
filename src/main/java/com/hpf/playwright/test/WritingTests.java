package com.hpf.playwright.test;

import com.hpf.playwright.utils.PlaywrightUtils;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 *
 */
public class WritingTests {

    public static void main(String[] args) {
        PlaywrightUtils.executeBrowser(WritingTests::assertThatTest);
    }

    public static void assertThatTest(Browser browser) {
        BrowserContext browserContext = browser.newContext();
        Page page = browserContext.newPage();
        page.navigate("https://playwright.dev");
        assertThat(page).hasTitle(Pattern.compile("Playwright"));

        Locator getStarted = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Get started"));
        getStarted.click();

        assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Installation"))).isVisible();
    }
}

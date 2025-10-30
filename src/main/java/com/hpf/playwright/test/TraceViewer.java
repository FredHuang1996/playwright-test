package com.hpf.playwright.test;

import com.hpf.playwright.utils.PlaywrightUtils;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Tracing;
import com.microsoft.playwright.options.AriaRole;

import java.nio.file.Paths;
import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class TraceViewer {

    public static void main(String[] args) {
        PlaywrightUtils.executeBrowserContext(browserContext -> {
            browserContext.tracing().start(new Tracing.StartOptions().setScreenshots(true).setSnapshots(true).setSources(true));
            Page page = browserContext.newPage();
            page.navigate("https://playwright.dev");
            assertThat(page).hasTitle(Pattern.compile("Playwright"));

            Locator getStarted = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Get started"));
            getStarted.click();

            assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Installation"))).isVisible();

            browserContext.tracing().stop(new Tracing.StopOptions().setPath(Paths.get("trace.zip")));
        });
    }
}

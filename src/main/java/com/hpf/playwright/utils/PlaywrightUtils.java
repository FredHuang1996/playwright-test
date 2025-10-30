package com.hpf.playwright.utils;

import com.microsoft.playwright.*;

import java.util.function.Consumer;

/**
 * 工具类
 */
public class PlaywrightUtils {

    /**
     * 执行测试
     * @param browserConsumer
     */
    public static void executeBrowser(Consumer<Browser> browserConsumer){
        Playwright.CreateOptions createOptions = new Playwright.CreateOptions();
        try (Playwright playwright = Playwright.create(createOptions)){
            BrowserType chromium = playwright.chromium();
            try (Browser browser = chromium.launch()){
                browserConsumer.accept(browser);
            }
        }
    }

    /**
     * 执行测试
     * @param browserContextConsumer
     */
    public static void executeBrowserContext(Consumer<BrowserContext> browserContextConsumer){
        Playwright.CreateOptions createOptions = new Playwright.CreateOptions();
        try (Playwright playwright = Playwright.create(createOptions)){
            BrowserType chromium = playwright.chromium();
            try (Browser browser = chromium.launch()){
                BrowserContext browserContext = browser.newContext();
                browserContextConsumer.accept(browserContext);
            }
        }
    }

    /**
     * 执行命令
     * @param url
     */
    public static void playwrightCodeGen(String url) {
        String[] args = new String[]{"codegen", url};
        try {
            CLI.main(args);
        }catch (Exception ignored){

        }
    }


    /**
     * 执行记录
     *
     * @param zipName
     */
    public static void playwrightShowTrace(String zipName) {
        String[] args = new String[]{"show-trace", zipName};
        try {
            CLI.main(args);
        }catch (Exception ignored){

        }
    }
    public static void main(String[] args) {
//        playwrightCodeGen("demo.playwright.dev/todomvc");
        playwrightShowTrace("trace.zip");
    }
}

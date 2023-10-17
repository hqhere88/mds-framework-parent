package mds.framework.test;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import java.nio.file.Paths;
import java.util.Arrays;

public class DownloadTest {
    public void downloadTest() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setArgs(Arrays.asList("-start-maximized"))
                    .setChannel("chrome")
                    .setHeadless(false));
            BrowserContext context = browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
            Page page = context.newPage();
            page.navigate("https://the-internet.herokuapp.com/download");
            Download download = page.waitForDownload(() -> {
                page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("LambdaTest.txt")).click();
            });
            // 输出结果：文件下载链接：https://the-internet.herokuapp.com/download/LambdaTest.txt
            System.out.printf("文件下载链接：%s%n",download.url());
            // 获取下载所属的页面
            System.out.printf("获取下载所属的页面：%s%n",download.page());
            // 返回下载页面标题
            System.out.printf("获取要下载的文件名：%s%n",download.page().title());
            // 取消下载
            download.cancel();
            // 返回下载失败，如 cancel
            System.out.printf("文件下载失败：%s%n",download.failure());
            // 返回此下载的建议文件名。它通常由浏览器从 Content-Disposition 响应头或 download 属性计算。
            // 查看whatwg的规范。不同的浏览器可以使用不同的逻辑来计算它。
            System.out.printf("获取下载所属的页面：%s%n",download.suggestedFilename());
            // 返回文件的临时保存路径：/var/folders/z9/cgrwh_ln7w31pj_j3wj791bh0000gn/T/playwright-artifacts-qit3Fe/3ea564b4-1d4b-4801-b359-e2a6b9b7592d
            System.out.printf("文件下载保存路径：%s%n",download.path());
            // 为下载的文件指定保存路径
            download.saveAs(Paths.get("src/main/resources/LambdaTest.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DownloadTest download = new DownloadTest();
        download.downloadTest();
    }
}

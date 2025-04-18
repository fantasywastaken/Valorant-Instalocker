package instalocker;

import org.apache.commons.io.FileUtils;
import instalocker.utils.files;
import instalocker.utils.ui.gui;
import java.io.File;

public class start {

    public static String NAME = "instalocker";
    public static String DATA_LINK = "https://raw.githubusercontent.com/fantasywastaken/Valorant-Instalocker/refs/heads/main/data.json";
    public static String ASSETS_LINK = "https://github.com/fantasywastaken/Valorant-Instalocker/raw/refs/heads/main/assets.zip";

    public static void main(String[] args) {
        files.downloadFile(ASSETS_LINK, new File(System.getProperty("java.io.tmpdir") + File.separator + "assets.zip"));
        files.unzip(System.getProperty("java.io.tmpdir") + File.separator + "assets.zip", System.getProperty("java.io.tmpdir") + File.separator + NAME);
        final Thread mainThread = Thread.currentThread();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                FileUtils.deleteDirectory(new File(System.getProperty("java.io.tmpdir") + File.separator + NAME));
                new File(System.getProperty("java.io.tmpdir") + File.separator + "assets.zip").delete();
                mainThread.join();
            } catch (Exception ignored) {}
        }));
        new gui();
    }
}

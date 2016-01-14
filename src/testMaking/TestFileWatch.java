/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testMaking;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class TestFileWatch {

    public static void main(String[] args) throws URISyntaxException,
            IOException, InterruptedException {
        a();
    }

    static void a() throws URISyntaxException, IOException,
            InterruptedException {

        URL url = TestFileWatch.class.getResource("C:/Users/00499/Desktop/ボディー");
        Path path = new File(url.toURI()).toPath();
        FileSystem fileSystem = path.getFileSystem();
        WatchService watcher = fileSystem.newWatchService();
        WatchKey watchKey = path.register(watcher, new WatchEvent.Kind[] {
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_MODIFY,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.OVERFLOW });
        while (true) {
            final WatchKey key = watcher.take();
            for (WatchEvent<?> watchEvent : key.pollEvents()) {

                final WatchEvent.Kind<?> kind = watchEvent.kind();

                if (kind == StandardWatchEventKinds.OVERFLOW) {
                    continue;
                }

                final WatchEvent<Path> watchEventPath = (WatchEvent<Path>) watchEvent;
                final Path entry = watchEventPath.context();

                System.out.println(kind + " ====>[" + entry + "]");
            }

            key.reset();

            if (!key.isValid()) {
                break;
            }
        }

    }
}

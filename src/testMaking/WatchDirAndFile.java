/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testMaking;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

/**
 *
 * @author 00499
 */
public class WatchDirAndFile {
     private static volatile boolean stop = false;

    public void watchStart(WD wd,String dirPath) {
        FileSystem fileSystem = FileSystems.getDefault();
        // 監視対象ディレクトリのPathを
        Path path = fileSystem.getPath(dirPath);
        try {
            // WatchServiceの取得
            WatchService watchService = fileSystem.newWatchService();
            WatchEvent.Kind<?>[] events = {
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_MODIFY,
                    StandardWatchEventKinds.ENTRY_DELETE
            };
            // 捕捉したいイベント種別とWatchServiceをPathに登録
            path.register(watchService, events);
            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    stop = false;
                }
            });
            while (!Thread.currentThread().isInterrupted() && !stop) {
                System.out.println("Watching...");
                wd.setJTextAreaAnser("Watching...");
                try {
                    // イベントが発生するまで待機
                    WatchKey watchKey = watchService.take();
                    String watchableName = watchKey.watchable().toString();
                    System.out.println("Watchable : " + watchableName);
                    
                    if (watchKey.isValid()) {
                        for (WatchEvent<?> event : watchKey.pollEvents()) {
                            System.out.println("イベント種別 : " + event.kind());
                            System.out.println("対象コンテンツ : " + event.context());
                            System.out.println("イベント回数 : " + event.count());
                            wd.setJTextAreaAnser("イベント種別 : " + event.kind());
                            wd.setJTextAreaAnser("対象コンテンツ : " + event.context());
                            wd.setJTextAreaAnser("イベント回数 : " + event.count());
                            Path targetPath = FileSystems.getDefault().getPath(watchableName + "\\" + event.context());
                            processContent(targetPath, event);
                            System.out.println();
                        }
                        if (!watchKey.reset()) {
                            // Is this right ?
                            System.out.println("The watch key might be invalid.");
                            wd.setJTextAreaAnser("The watch key might be invalid.");
                            break;
                        }
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("See you.");
            wd.setJTextAreaAnser("See you.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static final void processContent(final Path targetPath, final WatchEvent<?> watchEvent) {
        // コンテンツがファイルかつ更新イベントの場合コンテンツ内容を出力
        if (Files.isRegularFile(targetPath)) {
            if (watchEvent.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
                try {
                    if (Files.size(targetPath) > 0) {
                        try(BufferedReader reader = Files.newBufferedReader(targetPath, Charset.defaultCharset())) {
                            String line = null;
                            while ((line = reader.readLine()) != null) {
                                System.out.println(line);
                                //wd.setJTextAreaAnser(line);
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

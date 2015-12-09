/*
 * http://waman.hatenablog.com/entry/20120816/1345150695 を参考に改編
 * ファイルの操作が衝突しないようにSynchronizid処理が必要と思われる。
 */
package afgms;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;

/**
 *
 * @author 00499
 */
public class CopyFiles {

    // クラスごとSynchronized にする必要がある。
    private static MainJFrame mainJFrame; //コールバック用

    public void copySibling(String srcDir, String targetDir) throws IOException {
        Path src = Paths.get(srcDir).getParent();
        Path target = Paths.get(targetDir);
        copy(src, target);
    }

    public void copy(Path src, Path target) throws IOException {
        Files.createDirectories(target);
        execute(src, target);
    }

    public static void execute(Path src, Path target) throws IOException {
        FileVisitor<Path> visitor = new CopyVisitor(src, target);
        Files.walkFileTree(src, visitor);
    }

    /**
     * @return the mainJFrame
     */
    public MainJFrame getMainJFrame() {
        return mainJFrame;
    }

    /**
     * @param mainJFrame the mainJFrame to set
     */
    public void setMainJFrame(MainJFrame mainJFrame) {
        this.mainJFrame = mainJFrame;
    }

    /**
     * ビジターの実装
     */
    static class CopyVisitor extends SimpleFileVisitor<Path> {

        private final Path source;
        private final Path target;

        CopyVisitor(Path source, Path target) {
            this.source = source;
            this.target = target;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes atts)
                throws IOException {
            Path targetDir = this.target.resolve(this.source.relativize(dir));
            try {
                Files.copy(dir, targetDir);
                System.out.println("[COPY DIR] " + targetDir);
                mainJFrame.setMessagejTextAreaRedirectErrorStream("[COPY DIR] " + targetDir);
            } catch (FileAlreadyExistsException ex) {
                if (!Files.isDirectory(targetDir)) {
                    throw ex;
                }
            }

            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes atts)
                throws IOException {
            Path targetFile = this.target.resolve(this.source.relativize(file));
            // 各々の確認じぶん
            /*
             System.out.println("Path file " + file.toString());
             System.out.println("BFA " + atts.toString());
             System.out.println("targetFile " + targetFile.toString());
             System.out.println("this.source.relativize(file) " + this.source.relativize(file));
             */
            if (file.toFile().lastModified() > targetFile.toFile().lastModified()) {
                //System.out.println(targetFile + " 上書きします");
                Files.copy(file, targetFile, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("[COPY FILE] " + targetFile);
                mainJFrame.setMessagejTextAreaRedirectErrorStream("[COPY FILE] " + targetFile);

            } else {
                //System.out.println(targetFile + " スキップします");
                System.out.println("[SKIP FILE] " + targetFile);
                mainJFrame.setMessagejTextAreaRedirectErrorStream("[SKIP FILE] " + targetFile);
            }

            return FileVisitResult.CONTINUE;
        }
    }
}

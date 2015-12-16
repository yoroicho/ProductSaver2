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
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author 00499
 */
public class CopyFiles {

    // クラスごとSynchronized にする必要がある。
    public static final int COPY_PASTE = 0; // 上書きコピーの定数
    public static final int COPY_FORK = 1; // 世代分離コピーの定数

    private static MainJFrame mainJFrame; //コールバック用

    public String calcForkDirName(String srcFileName) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("_yyyyMMddHHmmssSSS");
        String strDate = srcFileName + sdf.format(cal.getTime());
        System.out.println(strDate);
        return strDate;
    }

    public void copySibling(String srcDir, String targetDir, int copyMode)
            throws IOException { // 新型・（コピーﾓｰﾄﾞの選択を実装）
        Path src = Paths.get(srcDir).getParent();
        Path target = Paths.get(targetDir); // バグ修正テスト（直ったと思われる）
        if (copyMode == CopyFiles.COPY_PASTE) { // 上書きの場合、フォルダ名を固定とする。
            target = Paths.get(targetDir, src.getFileName().toString() + "_AFGMS_BAK");
            if (target.toFile().mkdir()) {
                mainJFrame.setMessagejTextAreaRedirectErrorStream("初回バックアップ保存 " + target.toString());
            } else {
                if (!target.toFile().exists()) { // フォルダが作成されず、存在もしない
                    mainJFrame.setMessagejTextAreaRedirectErrorStream("フォルダを作成出来ませんでした(上書)");
                } else {
                    mainJFrame.setMessagejTextAreaRedirectErrorStream("上書きバックアップ保存");
                }

            }
        } else if (copyMode == CopyFiles.COPY_FORK) {
            target = Paths.get(targetDir, calcForkDirName(src.getFileName().toString()));
            if (target.toFile().mkdir()) {
                mainJFrame.setMessagejTextAreaRedirectErrorStream("新しいフォルダ " + target.toString());
            } else {
                mainJFrame.setMessagejTextAreaRedirectErrorStream("フォルダを作成出来ませんでした(分岐)");
                return;
            }
        }

        copy(src, target);
    }

    public void copySibling(String srcDir, String targetDir) throws IOException { // 旧 PasteかForkかの選択なし
        Path src = Paths.get(srcDir).getParent();
        calcForkDirName(src.getFileName().toString()); // テスト用このメソッドでは使わない

        Path target = Paths.get(targetDir, calcForkDirName(src.getFileName().toString()));
        System.out.println("新ターゲット" + target.toString());
        target.toFile().mkdir();
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

            System.out.println("Path file " + file.toString());
            System.out.println("BFA " + atts.toString());
            System.out.println("targetFile " + targetFile.toString());
            System.out.println("this.source.relativize(file) " + this.source.relativize(file));

            if (!(targetFile.toFile().exists())) {

                System.out.println("相手方にファイルが存在しない");
            }

            if (file.toFile().lastModified() > targetFile.toFile().lastModified() || !(targetFile.toFile().exists())) {
                if (targetFile.toFile().exists()) { // もともとファイルが存在する（上書き）の場合
                    targetFile.toFile().setWritable(true); // 元の状態がどうあれ読取専用を解除し、上書きできるようにする。
                }
                Files.copy(file, targetFile, StandardCopyOption.REPLACE_EXISTING);
                // コピー後のファイルを読み取り専用にする。実装するか否か未定
                targetFile.toFile().setReadOnly(); // 読取専用へ再び戻す。
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

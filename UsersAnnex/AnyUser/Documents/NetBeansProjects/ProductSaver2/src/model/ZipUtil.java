package model;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
/**
 * ZIPファイルの展開・圧縮を実行する .
 * <pre>
 * Java 7 から導入された FileSystems を使っています。
 *
 * Java 6 までの ZipInputStream, ZipOutputStream では、ZIP書庫内のファイル名が
 * UTF-8 固定でした。日本では、ZIP書庫内のファイル名は SJIS で格納します。(ZIP
 * の規格上は、ZIP書庫内のファイル名のエンコードは規定されておらず、日本では事実上の
 * 標準として SJIS が使われています)
 * </pre>
* @author kagyuu@hondou.homedns.org
 */
public class ZipUtil {
 
    /**
     * SJIS.
     */
    private static final String SJIS = "Windows-31J";
 
    /**
     * Macの場合には、展開時にファイル名を変態Unicode(NFD, UTF-8-MAC)にする必要がある.
     */
    private static final boolean isMac 
            = System.getProperty("os.name").toLowerCase().contains("mac");
 
    /**
     * 圧縮します.
     * <pre>
     * srcFile にディレクトリを指定した場合、配下のファイルを zipFile に再帰的に格納します
     * </pre>
     * @param zipFileName zipファイル
     * @param srcFileNames 圧縮元ファイル
     * @throws IOException 圧縮に失敗した
     */
    public static void deflate(final String zipFileName, final String... srcFileNames) throws IOException {
        List<File> srcFileList = new ArrayList<>();
        for (String srcFileName : srcFileNames) {
            srcFileList.add(new File(srcFileName));
        }
 
        deflate(new File(zipFileName), srcFileList.toArray(new File[0]));
    }
 
    /**
     * 圧縮します.
     * <pre>
     * srcFile にディレクトリを指定した場合、配下のファイルを zipFile に再帰的に格納します
     * </pre>
     * @param zipFile zipファイル
     * @param srcFiles 圧縮元ファイル
     * @throws IOException 圧縮に失敗した
     */
    public static void deflate(final File zipFile, final File... srcFiles) throws IOException {
        // あったら削除する
        Files.deleteIfExists(zipFile.toPath());
 
        URI zipUri = createZipUri(zipFile);
        Map<String, String> env = new HashMap<>();
        env.put("create", "true");
        env.put("encoding", SJIS);
        try (FileSystem zipfs = FileSystems.newFileSystem(zipUri, env)) {
            for (File srcFile : srcFiles) {
                if (srcFile.isDirectory()) {
                    // ディレクトリに場合には、再帰的に読み込む
                    final Path root = srcFile.getParentFile().toPath();
                    Files.walkFileTree(srcFile.toPath(), new SimpleFileVisitor<Path>() {
                        /**
                         * ファイルを発見した .
                         */
                        @Override
                        public FileVisitResult visitFile(Path file,
                                BasicFileAttributes attrs) throws IOException {
                            final String pathInZip = relative(root, file);
                            System.out.println("Deflate File : " + pathInZip);
                            Files.copy(file, zipfs.getPath(pathInZip), StandardCopyOption.REPLACE_EXISTING);
                            return FileVisitResult.CONTINUE;
                        }
 
                        /**
                         * ディレクトリに入る .
                         */
                        @Override
                        public FileVisitResult preVisitDirectory(Path dir,
                                BasicFileAttributes attrs) throws IOException {
                            final String pathInZip = relative(root, dir);
                            System.out.println("Deflate Dir : " + pathInZip);
                            Files.createDirectory(zipfs.getPath(pathInZip));
                            return FileVisitResult.CONTINUE;
                        }
                    });
                } else {
                    // ファイルの場合には、Zip の root に格納
                    final Path dest = zipfs.getPath(srcFile.getName());
                    System.out.println("Deflate File : " + dest);
                    Files.copy(srcFile.toPath(), dest, StandardCopyOption.REPLACE_EXISTING);
                }
            }
        }
    }
 
    /**
     * Zip書庫を展開します.
     *
     * @param zipFile zip 書庫
     * @param destDir 展開ディレクトリ
     * @throws IOException 展開に失敗
     */
    public static void inflate(final String zipFile, final String destDir) throws IOException {
        inflate(new File(zipFile), new File(destDir));
    }
     
    /**
     * Zip書庫を展開します.
     *
     * @param zipFile zip 書庫
     * @param destDir 展開ディレクトリ
     * @throws IOException 展開に失敗
     */
    public static void inflate(final File zipFile, final File destDir) throws IOException {
        URI zipUri = createZipUri(zipFile);
        // "SJIS" must be needed (in Japan), because default encoding is "UTF-8" even if inflate.
        // If "SJIS" is not, it may be occur java.lang.IllegalArgumentException: MALFORMED[1].
        Map<String, String> env = new HashMap<>();
        env.put("create", "false");
        env.put("encoding", SJIS);
        try (FileSystem zipfs = FileSystems.newFileSystem(zipUri, env)) {
            final Path root = zipfs.getPath("/");
            Files.walkFileTree(root, new SimpleFileVisitor<Path>() {
                /**
                 * ファイルを発見した .
                 */
                @Override
                public FileVisitResult visitFile(Path file,
                        BasicFileAttributes attrs) throws IOException {
                    final Path destFile = createDestFilePath(destDir, file);
                    System.out.println("Inflate File : " + destFile);
                    Files.copy(file, destFile, StandardCopyOption.REPLACE_EXISTING);                    
                    return FileVisitResult.CONTINUE;
                }
 
                /**
                 * ディレクトリに入る .
                 */
                @Override
                public FileVisitResult preVisitDirectory(Path dir,
                        BasicFileAttributes attrs) throws IOException {
                    final Path dirToCreate = createDestFilePath(destDir, dir);
                    System.out.println("Inflate Dir: " + dirToCreate);
                    if (Files.notExists(dirToCreate)) {
                        Files.createDirectory(dirToCreate);
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        }
    }
     
    /**
     * 出力ファイルパスを作成します.
     * <pre>
     * Mac で実行中の場合には、変態 Unicode (NFD, UTF-8-MAC) にします。
     * UTF-8-MAC では、濁点付きの文字は、濁点なしの文字と濁点の合成文字になります。
     * 
     * NFC : が =（U+304C）
     * NFD : が =「か」（U+304B）+「゙」（U+3099）の合成文字
     * </pre>
     * @param root 出力ファイルパス
     * @param file 出力ファイル
     * @return 出力ファイルパス
     */
    private static Path createDestFilePath(final File root, final Path file) {
        if (isMac) {
            return Paths.get(
                    Normalizer.normalize(root.getAbsolutePath(), Normalizer.Form.NFD),
                    Normalizer.normalize(file.toString(), Normalizer.Form.NFD)
                    );
        } else {
            return Paths.get(root.toString(), file.toString());
        }
    }
     
    /**
     * Zipファイルを URI 形式に変換します.
     * <pre>
     * Windows の場合には、指定されたファイル名の先頭に "/" を補完します
     * </pre>
     * @param zipFile Zipファイル
     * @return URI形式
     */
    private static URI createZipUri(final File zipFile) {
        String normalizedPath = normalize(zipFile.getAbsolutePath());
        if (normalizedPath.startsWith("/")) {
            // Unix
            return URI.create("jar:file:" + normalizedPath);
        } else {
            // Windows
            return URI.create("jar:file:/" + normalizedPath);
        }
    }
 
    /**
     * パス区切りを / に統一します.
     *
     * @param path パス名
     * @return パス名
     */
    private static String normalize(final String path) {
        return path.toString().replaceAll("\\\\", "/");
    }
 
    /**
     * file の root からの相対パス名を得る.
     *
     * @param file ファイル
     * @param root 基底ディレクトリ
     * @return 相対パス
     */
    private static String relative(final Path root, final Path file) {
        String rootPath = root.toString();
        String entryPath = file.toString();
        String retPath = entryPath.replace(rootPath, "");
        // Windows XP/Vista の標準の展開ツールは先頭が"/"だとファイル名として認識しないための対策
        if (retPath.charAt(0) == '/') {
            retPath = retPath.substring(1);
        }
        return normalize(retPath);
    }
}
// http://hondou.homedns.org/pukiwiki/pukiwiki.php?JavaSE%20ZIP%B0%B5%BD%CC%2F%B2%F2%C5%E0%20Java7
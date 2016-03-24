package testMaking;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import static java.nio.file.FileVisitResult.CONTINUE;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.COPY_ATTRIBUTES;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;

/**
 *
 * @author
 * http://javatutorialhq.com/java/example-source-code/io/nio/folder-copy/
 * 上のURLでOverrideのエラーがでるのでこのソースは下のURL
 * http://www.ne.jp/asahi/hishidama/home/tech/java/files.html
 * からこのソースを作成した。
 */
/**
 * This source code is for copying a folder or files recursively using java nio
 *
 */
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class FolderCopyNIO {

    public static Path source = Paths.get("E:/tmp/java/tutorial/nio/file/copy/source");
    public static Path target = Paths.get("D:/target");

    public static void main(String[] args) throws IOException {

        Path dir = new File("D:\\My Documents").toPath();
        final List<Path> list = new ArrayList<>();
        Files.walkFileTree(dir, new FileVisitor<Path>() {

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
               list.add(dir);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        list.add(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }
        });
        for (Path path : list) {
            System.out.println(path);
        }
System.out.println(list.size());
        /*
        //java nio folder copy
		EnumSet options = EnumSet.of(FileVisitOption.FOLLOW_LINKS);
		//check first if source is a directory
		if(Files.isDirectory(source)){
			System.out.println("source is a directory");

			Files.walkFileTree(source, options, Integer.MAX_VALUE, new FileVisitor() {

				@Override
				public FileVisitResult postVisitDirectory(Path dir,
						IOException exc) throws IOException {
					//System.out.println(source);
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult preVisitDirectory(Path dir,
						BasicFileAttributes attrs)  {
					CopyOption[] opt = new CopyOption[]{COPY_ATTRIBUTES,REPLACE_EXISTING};
					System.out.println("Source Directory "+dir);
					Path newDirectory = target.resolve(source.relativize(dir));
					System.out.println("Target Directory "+newDirectory);
					try{
						System.out.println("creating directory tree "+Files.copy(dir, newDirectory,opt));
					}
					catch(FileAlreadyExistsException x){
					}
					catch(IOException x){
						return FileVisitResult.SKIP_SUBTREE;
					}

					return CONTINUE;
				}

				@Override
				public FileVisitResult visitFile(Path file,
						BasicFileAttributes attrs) throws IOException {
					//System.out.println("results");
					System.out.println("Copying file:"+file);
					kopya(file, target.resolve(source.relativize(file)));
					return CONTINUE;
				}

				@Override
				public FileVisitResult visitFileFailed(Path file,
						IOException exc) throws IOException {
					return CONTINUE;
				}
			});
		}

	}
	public static void kopya(Path source,Path target) throws IOException{
		CopyOption[] options = new CopyOption[]{REPLACE_EXISTING,COPY_ATTRIBUTES};
		System.out.println("Copied file "+Files.copy(source, target,options));

*/
    }
        
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;



/**
 * This source code is for copying a folder or files recursively using java nio
 * http://javatutorialhq.com/java/example-source-code/io/nio/folder-copy/
 */
import java.nio.file.*;
import static java.nio.file.StandardCopyOption.*;
import java.nio.file.attribute.*;
import static java.nio.file.FileVisitResult.*;
import java.io.IOException;
import java.util.*;

public class FolderCopyNIO {
	public static Path source = Paths.get("E:/tmp/java/tutorial/nio/file/copy/source");
	public static Path target = Paths.get("D:/target");

	public static void main(String[] args) throws IOException {
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

	}
}
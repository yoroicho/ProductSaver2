/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testMaking;

import java.io.File;

/**
 *
 * @author 00499
 */
public class DelDirAndFile {

    public static void main(String[] args) {
        File delFile;
        /*
         * ファイルを削除する
         */
        //delFile = new File("E:\\del_file.txt");
        //delete(delFile);

        /*
         * ディレクトリを削除する
         *   ディレクトリ内にファイルや他のディレクトリが存在たら、
         *   それらもすべて削除する
         */
        delFile = new File("C:\\Users\\00499\\Desktop\\DESKTO~1\\テスト~1\\DESKTO~1\\テスト~1\\DESKTO~1\\テスト~1\\DESKTO~1\\テスト~1\\DESKTO~1\\テスト~1\\DESKTO~1\\テスト~1\\DESKTO~1\\テスト~1\\DESKTO~1\\テスト~1\\DESKTO~1\\テスト~1\\DESKTO~1\\テストトレイ\\Desktop_AFGMS_BAK");
        delete(delFile);
        System.out.println("Deled");
        // 以下でも良い
        //delFile = new File("E:\\del_dir");
    }

    /*
     * ファイルおよびディレクトリを削除する
     */
    private static void delete(File f) {
        /*
         * ファイルまたはディレクトリが存在しない場合は何もしない
         */
        if (f.exists() == false) {
            return;
        }

        if (f.isFile()) {
            /*
             * ファイルの場合は削除する
             */
            System.out.println("Del" + f.toString());
            f.delete();

        } else if (f.isDirectory()) {

            /*
             * ディレクトリの場合は、すべてのファイルを削除する
             */

            /*
             * 対象ディレクトリ内のファイルおよびディレクトリの一覧を取得
             */
            File[] files = f.listFiles();

            /*
             * ファイルおよびディレクトリをすべて削除
             */
            for (int i = 0; i < files.length; i++) {
                /*
                 * 自身をコールし、再帰的に削除する
                 */
                delete(files[i]);
            }
            /*
             * 自ディレクトリを削除する
             */
            System.out.println("Del" + f.toString());
            f.delete();
        }
    }

}

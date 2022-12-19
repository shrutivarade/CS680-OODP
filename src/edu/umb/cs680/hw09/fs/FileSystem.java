package edu.umb.cs680.hw09.fs;


import java.util.LinkedList;

public class FileSystem {


    private static FileSystem fileSystem = null; //Instance for singleton
    private LinkedList<Directory> RootDirs = new LinkedList<Directory>();

    private FileSystem() { //private constructor
    }

    public static FileSystem getFileSystem() { //Static Factory Method
        if(fileSystem == null)
            fileSystem = new FileSystem();
        return fileSystem;
    }

    public LinkedList<Directory> getRootDirs() { //getter

        return RootDirs;
    }

    public void appendRootDirs(Directory root){ //public method

        this.RootDirs.add(root);
    }



}

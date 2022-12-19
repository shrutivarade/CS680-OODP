package edu.umb.cs680.hw10.fs;

import edu.umb.cs680.hw10.SecurityContext;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class Directory extends FSElement {

    public LinkedList<FSElement> children = new LinkedList<FSElement>();
    public LinkedList<Directory> SubDirectories;
    public LinkedList<File> Files;
    public int totalSize;

    public Directory(Directory parent, String name, int size, LocalDateTime creationTime) { //constructor
        super(parent, name, 0, creationTime);
    }

    public LinkedList<FSElement> getChildren() { //getter
        System.out.println(this.children);
        return this.children;
    }

    public LinkedList<Directory> getSubDirectories() { //getter
        for (FSElement fs : children) {
            if (fs.isDirectory()) {
                SubDirectories.add((Directory) fs);
            }
        }
        return SubDirectories;
    }

    public LinkedList<File> getFiles() { //getter
        for (FSElement fs : children) {
            if (fs.isFile()) {
                Files.add((File) fs);
            }
        }
        return Files;
    }

    public void appendChild(FSElement child){ //public method
            this.children.add(child);
    }

    public int countChildren(){ //public method

        return this.children.size();
    }

    public int getTotalSize() { //public method
        totalSize=0;
        for (FSElement fse : children) {
            if (fse.isDirectory()) {
                totalSize += ((Directory)fse).getTotalSize();
            } else {
                totalSize += fse.getSize();
            }
        }
        return totalSize;
    }

    @Override
    public boolean isDirectory() { //public method

        return true;
    }

    @Override
    public boolean isFile() { //public method

        return false;
    }

    @Override
    public boolean isLink() { //public method

        return false;
    }

    @Override
    public void accept(FSVisitor v, SecurityContext ctx) throws Exception {
        if(ctx.isActive()){
            v.visit(this);
            for(FSElement fse: children){
                fse.accept(v,ctx);
            }
        }else{
            System.out.println("Stop in Directory");
            throw new Exception();
        }


    }
}

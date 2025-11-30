package year2022.day07;

import lombok.AllArgsConstructor;
import lombok.Data;
import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class Day07 {
    @AllArgsConstructor
    @Data
    static class Folder {
        String name;
        List<Folder> folders;
        List<File> files;
        Folder parent;
        Long size;

        public Folder(String name) {
            this.name=name;
            folders=new ArrayList<>();
            files=new ArrayList<>();
            parent=null;
        }
        public void addFolder(Folder folder) {
            folders.add(folder);
        }
        public void addFile(File file) {
            files.add(file);
        }
        public long getSize() {
            long size = 0L;
            for(Folder folder : folders) {
                size+=folder.getSize();
            }
            for(File file : files) {
                size+=file.getSize();
            }
            return size;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Folder folder = (Folder) o;
            return name.equals(folder.name) &&
                    parent.equals(folder.parent) &&
                    folders.equals(folder.folders) &&
                    files.equals(folder.files) &&
                    Objects.equals(size, folder.size);
        }
        @Override
        public int hashCode() {
            if(parent!=null)
                return parent.getName().hashCode()+name.hashCode();
            return "".hashCode()+name.hashCode();

        }

    }
    @AllArgsConstructor
    @Data
    static class File {
        String name;
        long size;
    }

    static Object solveA(List<String> values) {
        Folder rootFolder = new Folder("/");
        HashSet<Folder> allFolders=new HashSet<>();
        allFolders.add(rootFolder);
        Folder currentFolder=rootFolder;

        for(String s : values) {
            if(s.startsWith("$")) {
                if (s.equals("$ cd ..")) {
                    currentFolder = currentFolder.getParent();
                } else if (s.startsWith("$ cd ")) {
                    String name = s.split(" ")[2];
                    for(Folder c : currentFolder.getFolders()) {
                        if(c.getName().equals(name)) {
                            currentFolder=c;
                        }
                    }
                }

            } else {

                if(s.startsWith("dir ")) {
                    Folder newSubFolder=new Folder(s.split(" ")[1]);
                    newSubFolder.setParent(currentFolder);
                    currentFolder.addFolder(newSubFolder);
                    allFolders.add(newSubFolder);

                } else {
                    File file = new File(s.split(" ")[1], Long.parseLong(s.split(" ")[0]));
                    currentFolder.addFile(file);
                }
            }

        }
        int result=0;
        for(Folder folder : allFolders) {
            if (folder.getSize() <= 100000L) {
                result+= (int) folder.getSize();
            }
        }



        return result;
    }
    static Object solveB(List<String> values) {
        Folder rootFolder = new Folder("/");
        HashSet<Folder> allFolders=new HashSet<>();
        allFolders.add(rootFolder);
        Folder currentFolder=rootFolder;

        for(String s : values) {
            if(s.startsWith("$")) {
                if (s.equals("$ cd ..")) {
                    currentFolder = currentFolder.getParent();
                } else if (s.startsWith("$ cd ")) {
                    String name = s.split(" ")[2];
                    for(Folder c : currentFolder.getFolders()) {
                        if(c.getName().equals(name)) {
                            currentFolder=c;
                        }
                    }
                }

            } else {

                if(s.startsWith("dir ")) {
                    Folder newSubFolder=new Folder(s.split(" ")[1]);
                    newSubFolder.setParent(currentFolder);
                    currentFolder.addFolder(newSubFolder);
                    allFolders.add(newSubFolder);

                } else {
                    File file = new File(s.split(" ")[1], Long.parseLong(s.split(" ")[0]));
                    currentFolder.addFile(file);
                }
            }

        }

        long totalSize = 70000000L;
        long neededSpace = 30000000L;
        long usedSpace = rootFolder.getSize();
        long freeSpace=totalSize-usedSpace;

        long missingSpace= neededSpace-freeSpace;

        long smallestOK=Long.MAX_VALUE;
        for(Folder folder : allFolders) {
            if(folder.getSize()>=missingSpace) {
                smallestOK=Math.min(smallestOK, folder.getSize());
            }
        }

            return smallestOK;
    }
    public static void main(){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2022/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = t1-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //1325919
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //2050735
    }
}

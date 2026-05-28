import core.FileSystem;
import entity.FileSystemEntity;
import permissions.Permission;

public class Main {

    public static void main(String[] args) {
        FileSystem fs = FileSystem.getInstance();

        Permission permission = new Permission(true, true, true, true, false);

        fs.mkdir("/root/docs", permission);

        fs.touch("/root/docs/a.txt", permission, "Hello", "txt");

        System.out.println(fs.cat("/root/docs/a.txt"));

        fs.write("/root/docs/a.txt", "Hello World");

        fs.undo("/root/docs/a.txt");

        System.out.println(fs.cat("/root/docs/a.txt"));

        fs.redo("/root/docs/a.txt");

        System.out.println(fs.cat("/root/docs/a.txt"));

        fs.rm("/root/docs/a.txt");
    }
}
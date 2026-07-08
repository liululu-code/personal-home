package top.lll44556.common;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.nio.file.Path;

public class MainTest {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\ADMIN\\Downloads\\Student_20260708154149008";
        Resource zipResource = new FileSystemResource(Path.of(filePath));
        System.out.println(zipResource.getFilename());
    }
}

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.testng.Assert.assertEquals;

public class SimpleTest {

    boolean interactive = false;
    String createFilePath = "C:\\TMP\\test1.txt";
    String deleteFilePath = "C:\\TMP\\test1.txt";
    String renameFilePathOld = "C:\\TMP\\testren.txt";
    String renameFilePathNew = "C:\\TMP\\testrennew.txt";

    @Parameters({"interactive"})
    SimpleTest(boolean interactive) {
        this.interactive = interactive;
    }

    @BeforeClass
    public void setUp() {
        // code that will be invoked when this test is instantiated
    }

    @Parameters({"myParam"})
    @Test(groups = {"min"})
    public void testMethod1(String myParam) {
        System.out.println("I got a parameter: " + myParam);
    }

    @Test(groups = {"min"})
    public void testMethod2() {
        FileManager fileManager = new FileManager(interactive);
        fileManager.createFile_sn(createFilePath);
        System.out.println(Files.exists(Paths.get(createFilePath)));
        assertEquals(true, Files.exists(Paths.get(createFilePath)));
    }
    @Test(groups = {"min"})
    public void testMethod4() throws IOException {
        FileManager fileManager = new FileManager(interactive);
        fileManager.createFile(createFilePath);
        System.out.println(Files.exists(Paths.get(createFilePath)));
        assertEquals(true, Files.exists(Paths.get(createFilePath)));
    }

   @Test(groups = {"min"})
    public void testMethod3() {
       FileManager fileManager = new FileManager(interactive);
        assertEquals(true, Files.exists(Paths.get(deleteFilePath)));
        fileManager.deleteFile(deleteFilePath);
        System.out.println(Files.exists(Paths.get(deleteFilePath)));
       assertEquals(false, Files.exists(Paths.get(deleteFilePath)));
   }

   @Test(groups = {"mid"})
    public void testRenameFile() throws  Exception {
       FileManager fileManager = new FileManager(interactive);
      assertEquals(true, Files.exists(Paths.get(renameFilePathOld)));
      fileManager.renameFile( renameFilePathNew,renameFilePathOld);
     assertEquals(false, Files.exists(Paths.get(renameFilePathOld)));
       assertEquals(true, Files.exists(Paths.get(renameFilePathNew)));
    }

//    @Test(groups = {"mid"})
//    public void testRenameFile() throws  Exception {
//        FileManager fileManager = new FileManager(interactive);
//        assertEquals(true, Files.exists(Paths.get(renameFilePathOld)));
//        fileManager.renameFile_sn(renameFilePathOld,renameFilePathNew);
//        assertEquals(false, Files.exists(Paths.get(renameFilePathOld)));
//        assertEquals(true, Files.exists(Paths.get(renameFilePathNew)));
//    }
}


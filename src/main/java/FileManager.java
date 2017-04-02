import java.io.*;

import java.io.IOException;


public class FileManager {
    public int err_newfile = 0;
    boolean err_chars=true;
    boolean interactive;

    FileManager(boolean mode) {
        interactive = mode;
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


    public void createFile_sn(String filePath) throws IOException {
        if (interactive) {
            try {
               filePath = pathInput();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        boolean result = false;

        File file = new File(filePath);
        try {
            result = file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (result) {
            printLogMessage("File has been created");
        } else {
                        printLogMessage("File already exists");
        }
    }

    public void createFile( String newFilename) throws IOException {

        boolean result;
        String filenameLab1 =  newFilename;
        File file1 =  new File(filenameLab1);
        String [] specialCharacters = {"!", ";"};

        for (int i = 0; i < specialCharacters.length; i++) {
            if (file1.getPath().contains(specialCharacters[i])) {
                try {
                    throw new UnsupportedOSCharactersException("Your path contains unsupported chars");

                } catch (UnsupportedOSCharactersException e) {
                    e.printStackTrace(); // порядок вызова методов
                } finally {
                    err_newfile = 2; // что делать в любом случае
                }
                err_chars =false;
            }
        }
        if (err_chars==true) {
            result = file1.createNewFile();
            if (result) {
                System.out.println("File created: " + file1.getAbsolutePath());
                FileWriter fr = null;
                BufferedWriter br = null;
                String dataWithNewLine = "Gruppa QA_10 labwork #1 ";
                fr = new FileWriter(file1);
                br = new BufferedWriter(fr);

                for (int i = 5; i > 0; i--) {
                    br.write(dataWithNewLine);
                }
                br.close();
                fr.close();
            } else {
                err_newfile = 2;
                System.out.println("not create file");
            }
        }else {
            System.out.println("not create file, unsupported chars");
            err_newfile = 2;

        }

    }

    public void deleteFile( String newFilename)
    {
        boolean result;
        String FILENAME_lab1 = newFilename;
        File file1 = new File(FILENAME_lab1);

        result=file1.delete();
        if (result)
        {
            System.out.println("File deleted: " + file1.getAbsolutePath());
        } else {
            System.out.println("File not deleted");

        }
    }

    public void renameFile( String newFilename, String oldFilename) throws IOException
    {

        final File oldFile = new File(oldFilename);
        final File newFile = new File(newFilename);


        if (oldFile.exists())
        {
            if (oldFile.renameTo(newFile))
            {
                System.out.println("File renamed");
            } else {
                System.out.println("File not renamed");
                err_newfile = 1;

            }

        } else {
            System.out.println("File " + oldFilename + " does not exist. File not renamed");
            err_newfile = 1;

        }

    }

    public void renameFile_sn(String filePath,String pathNew) throws IOException {
        boolean result;


        if (interactive) {
            try {
                filePath = pathInput();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        File file = new File(filePath);
        printLogMessage("Please, input a path and new file name: ");

        //pathNew = reader.readLine();
        File fileNew = new File(pathNew);
        result = file.renameTo(fileNew);

        if (result) {
            printLogMessage("File has been renamed");
        } else {
            printLogMessage("File hasn't been renamed");
        }
    }

    public String pathInput() throws IOException {
        String os;
        String path;

        os = System.getProperty("os.name");
        printLogMessage("Your operation system is " + os);

        if (os.contains("Windows")) {
            printLogMessage("Please, input a path and file name in format (example): C:\\Users\\user\\test.txt");
            path = reader.readLine();
        } else if (os.contains("Mac")) {
            printLogMessage("Please, input a path and file name in format (example) /Users/user/projects/test.txt");
            path = reader.readLine();
        } else if (os.contains("Linux")) {
            printLogMessage("Please, input a path and file name in format (example) /home/user/projects/test.txt");
            path = reader.readLine();
        } else {
            printLogMessage("Please, input a path and file name");
            path = reader.readLine();
        }

        return path;
    }


    private void printLogMessage(String message) {
        if (interactive) {
            System.out.println(message);
        }
    }
}


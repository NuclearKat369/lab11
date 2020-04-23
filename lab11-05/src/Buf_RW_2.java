import java.io.*;

public class Buf_RW_2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = null;
        PrintWriter out = null;

        try {
            //создание потоков
            br = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream("C:\\Users\\User\\Documents\\MyFile2.txt"), "cp1251"));
            out = new PrintWriter("C:\\Users\\User\\Documents\\MyFile7.txt", "cp1251");
            //переписывание информации из одного файла в другой
            int lineCount = 0;
            String s;

            while ((s = br.readLine()) != null) {
                lineCount++;
                out.println(lineCount + ": " + s);
            }
        } catch (IOException e) {
            System.out.println("Ошибка!!!");
        } finally {
            br.close();
            out.flush();
            out.close();
        }
    }
}

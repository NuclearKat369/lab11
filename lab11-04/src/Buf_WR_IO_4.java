import java.io.*;

public class Buf_WR_IO_4 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = null;
        BufferedWriter bw = null;

        try {
            //создание потоков для чтения и записи с нужной кодировкой
            br = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream("C:\\Users\\User\\Documents\\MyFile2.txt"), "cp1251"));

            bw = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream("C:\\Users\\User\\Documents\\MyFile6.txt"), "cp1251"));

            //переписывание информации из одного файла в другой
            int lineCount = 0;
            String s;
            while ((s = br.readLine()) != null) {
                lineCount++;
                System.out.println(lineCount + ": " + s);
                bw.write(lineCount + ": " + s);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Ошибка!!!" + e);
        } finally {
            br.close();
            bw.flush();
            bw.close();
        }
    }
}

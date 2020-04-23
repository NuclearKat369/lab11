import java.io.*;

public class poem {

    public static void main(String[] args) throws IOException {

        BufferedReader br = null;
        PrintWriter pr = null;
        try {
            br = new BufferedReader
                    (new InputStreamReader(new FileInputStream
                            ("D:\\Документы\\lab11\\lab11-08\\Стих.txt")));
            pr = new PrintWriter("D:\\Документы\\lab11\\lab11-08\\СтихNew.txt");

            int lineCount = 0;
            int wordCount;

            String letter = String.valueOf(br.readLine().charAt(0));
            br.close();
            br = new BufferedReader
                    (new InputStreamReader(new FileInputStream
                            ("D:\\Документы\\lab11\\lab11-08\\Стих.txt")));

            String s;
            while ((s = br.readLine()) != null) {
                String[] str = s.split(" ");
                lineCount++;
                wordCount = 0;
                String line = "";
                for (int i = 0; i < str.length; i++) {
                        if (str[i].toUpperCase().startsWith(letter.toUpperCase())) {
                            wordCount++;
                            line = line + (str[i] + " ");
                        }

                }
                if (wordCount > 0) {
                    System.out.print(line);
                    String info = ("Номер строки: " +
                            lineCount + " Слов в строке: " + wordCount + "\n");
                    pr.printf("%-35s%35s", line, info);
                    System.out.println();
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            br.close();
            pr.flush();
            pr.close();
        }
    }
}

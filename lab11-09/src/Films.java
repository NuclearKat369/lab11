import java.io.*;
import java.util.Scanner;

public class Films {

    public static void main(String[] args) {

        try {
            File folder = new File("D:\\Документы\\lab11\\lab11-09");
            if (!folder.exists())
                folder.mkdir();
            File f1 = new File("D:\\Документы\\lab11\\lab11-09\\films.txt");
            if (f1.exists()){
                f1.delete();
            }
            f1.createNewFile();

            RandomAccessFile rf = new RandomAccessFile(f1, "rw"); // чтение и запись
            Scanner sc = new Scanner(System.in, "cp1251");
            System.out.print("Введите количество фильмов для записи в файл\n"
                    + "=> ");
            int kol = sc.nextInt();
            sc.nextLine(); // очистка буфера после ввода числа
            String title, country, genre;
            int year;
            double budget;

            for (int i = 0; i < kol; i++) {
                System.out.print("Введите название " + (i + 1) + " фильма => ");
                title = sc.nextLine();
                rf.seek(rf.length());
                rf.writeUTF(title);
                for (int j = 0; j < 50 - title.length(); j++)
                    rf.writeByte(1);

                System.out.print("Введите год выпуска => ");
                year = sc.nextInt();
                rf.writeInt(year);
                sc.nextLine();

                System.out.print("Введите страну производства  => ");
                country = sc.next();
                rf.seek(rf.length());
                rf.writeUTF(country);
                for (int j = 0; j < 20 - country.length(); j++)
                    rf.writeByte(1);

                System.out.print("Введите жанр => ");
                genre = sc.next();
                rf.seek(rf.length());
                rf.writeUTF(genre);
                for (int j = 0; j < 20 - genre.length(); j++)
                    rf.writeByte(1);

                System.out.print("Введите его бюджет => ");
                budget = sc.nextDouble();
                sc.nextLine();
                rf.writeDouble(budget);

            }
            rf.close();

            File f2 = new File("D:\\Документы\\lab11\\lab11-09\\filmsOut.txt");
            if (f2.exists()){
                f2.delete();
            }
            f2.createNewFile();

            rf = new RandomAccessFile(f1, "r");
            PrintWriter rw = new PrintWriter(f2, "cp1251");

            rf.seek(0); // перемещение в начало файла

            for (int i = 0; i < kol; i++) {

                title = rf.readUTF();
                for (int j = 0; j < 50 - title.length(); j++)
                    rf.readByte();

                year = rf.readInt();

                country = rf.readUTF();
                for (int j = 0; j < 20 - country.length(); j++)
                    rf.readByte();

                genre = rf.readUTF();
                for (int j = 0; j < 20 - genre.length(); j++)
                    rf.readByte();

                budget = rf.readDouble();

                String ru = new String ("Россия".getBytes(), "cp1251");
                String ussr = new String ("СССР".getBytes(), "cp1251");

                if (country.equals(ru) || country.equals(ussr)){
                    rw.print(title + "\t\t" + year + "\t\t" + country
                            + "\t\t" + genre + "\t\t" + budget + "\n");
                }
            }
            rf.close();
            rw.flush();
            rw.close();
        } catch (IOException e) {
            System.out.println("End of file " + e);
        }
    }
}
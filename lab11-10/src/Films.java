import java.io.*;
import java.util.Scanner;

class Film implements Serializable {

    String title, country, genre;
    int year;
    double budget;
}

public class Films {

    public static void main(String[] args) throws ClassNotFoundException {

        try {

            File f1 = new File("D:\\���������\\lab11\\lab11-10\\films.txt");
            if (f1.exists()) {
                f1.delete();
            }
            f1.createNewFile();

            FileOutputStream fos = new FileOutputStream(f1);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            Scanner sc = new Scanner(System.in, "cp1251");
            System.out.print("������� ���������� ������� ��� ������ � ����\n"
                    + "=> ");
            int kol = sc.nextInt();
            sc.nextLine(); // ������� ������ ����� ����� �����

            int ruCount = 0;
            String ru = new String("������".getBytes(), "cp1251");
            String ussr = new String("����".getBytes(), "cp1251");

            Film[] kino = new Film[kol];
            for (int i = 0; i < kino.length; i++) {
                kino[i] = new Film();
                System.out.print("������� �������� " + (i + 1) + " ������ => ");
                kino[i].title = sc.nextLine();

                System.out.print("������� ��� ������� => ");
                kino[i].year = sc.nextInt();
                sc.nextLine();

                System.out.print("������� ������ ������������  => ");
                kino[i].country = sc.next();

                if (kino[i].country.equals(ru) || kino[i].country.equals(ussr)
                        || kino[i].country.equalsIgnoreCase("USSR") || kino[i].country.equals("Russia"))
                    ruCount++;

                System.out.print("������� ���� => ");
                kino[i].genre = sc.next();

                System.out.print("������� ��� ������ => ");
                kino[i].budget = sc.nextDouble();
                sc.nextLine();

                oos.writeObject(kino[i]);

            }
            oos.flush();
            oos.close();

            File f2 = new File("D:\\���������\\lab11\\lab11-10\\filmsOut.txt");
            if (f2.exists()) {
                f2.delete();
            }
            f2.createNewFile();

            FileInputStream fis = new FileInputStream(f1);
            ObjectInputStream oin = new ObjectInputStream(fis);
            fos = new FileOutputStream(f2);
            oos = new ObjectOutputStream(fos);

            Film[] ckino = new Film[ruCount];
            int j = 0;

            for (int i = 0; i < kino.length; i++) {

                kino[i] = (Film) oin.readObject();

                if (kino[i].country.equals(ru) || kino[i].country.equals(ussr) ||
                        kino[i].country.equalsIgnoreCase("USSR") ||
                        kino[i].country.equalsIgnoreCase("Russia")) {
                    ckino[j] = kino[i];
                    oos.writeObject(ckino[j]);
                    j++;
                }
            }
            oos.flush();
            oos.close();

            fis = new FileInputStream(f2);
            oin = new ObjectInputStream(fis);

            for (int i = 0; i < ckino.length; i++) {

                ckino[i] = (Film) oin.readObject();
                System.out.println("��������: " + ckino[i].title);
                System.out.println("��� �������: " + ckino[i].year);
                System.out.println("������: " + ckino[i].country);
                System.out.println("����: " + ckino[i].genre);
                System.out.println("������: " + ckino[i].budget);
            }
        } catch (IOException e) {
            System.out.println("End of file " + e);
        }
    }
}

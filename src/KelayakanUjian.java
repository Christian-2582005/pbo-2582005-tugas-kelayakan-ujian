import java.util.Scanner;

public class KelayakanUjian {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Kehadiran (%) : ");
        int kehadiran = scanner.nextInt();

        System.out.print("Nilai tugas : ");
        int nilaiTugas = scanner.nextInt();

        System.out.print("Dispensasi : ");
        boolean dispensasi = scanner.nextBoolean();

        // ===== Tiga versi kurung untuk syarat yang "sama" =====
        boolean a = kehadiran >= 75 && nilaiTugas >= 60 || dispensasi;
        boolean b = (kehadiran >= 75 && nilaiTugas >= 60) || dispensasi;
        boolean c = kehadiran >= 75 && (nilaiTugas >= 60 || dispensasi);

        // Hasilnya a selalu sama dengan b, tidak pernah sama dengan c
        // (kecuali kebetulan untuk kombinasi input tertentu).
        // Kesimpulan: && mengikat lebih kuat daripada ||, jadi Java otomatis
        // membaca "x && y || z" sebagai "(x && y) || z" tanpa diminta.
        // Itu sebabnya a persis seperti b, karena b memang cuma menulis ulang
        // urutan yang sudah dilakukan Java secara default di a.
        // c beda karena kurungnya sengaja "digeser" memaksa || dievaluasi
        // duluan di dalam kurung, mengubah makna keseluruhan ekspresi.

        boolean tidakDispensasi = !dispensasi;

        // ===== Pembuktian short-circuit evaluation =====
        int cek = 0;
        boolean x = (kehadiran >= 75) && (cek++ >= 0);
        boolean y = (nilaiTugas >= 60) || (cek++ >= 0);

        // Kenapa cek bisa berakhir di 0:
        // Untuk x: operator && cuma lanjut mengevaluasi sisi kanan KALAU sisi
        // kiri true. Kalau kehadiran >= 75 sudah false, Java tidak perlu tahu
        // sisi kanan apa pun hasilnya (false && apa pun pasti false) -- jadi
        // (cek++ >= 0) dilewati sama sekali, cek++ tidak pernah dieksekusi.
        // Untuk y: operator || cuma lanjut mengevaluasi sisi kanan KALAU sisi
        // kiri false. Kalau nilaiTugas >= 60 sudah true, Java tidak perlu tahu
        // sisi kanan (true || apa pun pasti true) -- jadi (cek++ >= 0) juga
        // dilewati. Karena kedua sisi kanan tidak pernah dieksekusi, cek tetap
        // di nilai awal 0. Ini membuktikan Java benar-benar "malas" mengevaluasi
        // ekspresi yang hasilnya sudah pasti, bukan cuma teori di slide.

        System.out.println();
        System.out.println("===== KELAYAKAN UJIAN =====");
        System.out.println("Kehadiran : " + kehadiran + "%");
        System.out.println("Nilai tugas : " + nilaiTugas);
        System.out.println("Dispensasi : " + dispensasi);
        System.out.println();
        System.out.println("a (tanpa kurung) : " + a);
        System.out.println("b (kurung precedence) : " + b);
        System.out.println("c (kurung digeser) : " + c);

        scanner.close();
    }
}
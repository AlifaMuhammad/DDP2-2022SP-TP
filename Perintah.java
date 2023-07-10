
import java.awt.Dimension;

/**
 * 
 * Perintah.java
 * <br><br>
 * Class {@code Perintah} merepresentasikan perintah-perintah umum yang 
 * dapat diberikan kepada kura-kura. Termasuk dalam class ini adalah
 * proses untuk membaca input (saat ini baru melalui satu baris perintah)
 * dan memanggil method yang berkesesuaian.
 * Dalam kelas ini juga disediakan method-method yang merupakan kumpulan-kumpulan
 * perintah berurutan yang dapat diterima oleh kurakura dan menghasilkan gambar 
 * tertentu. 
 * <br><br>
 * Tugas anda pada file ini: <br>
 * - Lengkapi javadoc comment pada tiap deklarasi method.<br>
 * - Lengkapi inline comment untuk tiap baris perintah yang penting.<br>
 * - Perbaiki method {@code lakukan} agar tidak menimbulkan error bila input salah<br>
 * - Buat (1) perintah {@code mundur <x>}" agar kura-kura bisa mundur sebanyak x pixel satuan.
 * - Buat (2) perintah {@code hadap kanan} dan {@code hadap kiri} yang akan membuat kura-kura 
 *   menghadap ke kanan (rotasi 90) dan ke kiri (rotasi -90) 
 * - Dapatkah anda membuat (3) kura-kura mengenali perintah 
 *   {@code loop 10 perintah-perintah} <br>
 *   yang artinya melakukan perintah-perintah sebanyak 10 kali? <br>
 *   contoh: "loop 10 rotasi 30 maju 30" <br>
 *           yang artinya akan melakukan perintah "rotasi 30", dilanjutkan <br>
 *           perintah "maju 30", secara berulang-ulang sebanyak 10 kali<br>
 *   contoh: "loop 5 maju 20 hadap kanan maju 30" <br>
 *           yang artinya akan melakukan perintah "maju 20", dilanjutkan <br>
 *           "hadap kanan", kemudian perintah "maju 10", <br> 
 *           secara berulang-ulang sebanyak 5 kali<br>
 * 
 * @author Ade Azurat for DPBO 2008 @ Fasilkom UI
 * @author Ade Azurat for DDP2 2023 @ Fasilkom UI
 */
public class Perintah {
    Canvas canvas;
    Kurakura kurakuraku; 
    
    /** Creates a new instance of Perintah */
    public Perintah(Kurakura k, Canvas canvas) {
        kurakuraku = k;
        this.canvas = canvas;
    }

    // Dapatkan anda membuat method ini lebih baik dan lebih mudah ditambahkan
    // atau di ubah? 
    public String lakukan(String inputPerintah){
        String[] in = inputPerintah.split(" ");
        if (in[0].equalsIgnoreCase("selesai"))
            System.exit(0);
        else if (in[0].equalsIgnoreCase("reset"))
            kurakuraku.reset();
        else if (in[0].equalsIgnoreCase("maju"))
            if (in.length > 1) {
                kurakuraku.maju(Integer.parseInt(in[1]));
            }
            else{
                return "perintah tidak dipahami";
            }
        else if (in[0].equalsIgnoreCase("mundur"))
                kurakuraku.mundur(Integer.parseInt(in[1]));
        else if (in[0].equalsIgnoreCase("rotasi"))
                kurakuraku.rotasi(Integer.parseInt(in[1]));
        else if (in[0].equalsIgnoreCase("kotak"))
                buatKotak(Integer.parseInt(in[1]));
        else if (in[0].equalsIgnoreCase("boxes"))
                buatBoxes(Integer.parseInt(in[1]));
        else if (in[0].equalsIgnoreCase("bintang"))
                buatBintang(Integer.parseInt(in[1]));
        else if (in[0].equalsIgnoreCase("sierpinski"))
                buatSierpinski(Integer.parseInt(in[1]));
        else if (in[0].equalsIgnoreCase("persegi"))
                buatPersegi(Integer.parseInt(in[1]), Integer.parseInt(in[2])); 
        else if (in[0].equalsIgnoreCase("segitiga"))
                buatSegitiga(Integer.parseInt(in[1]));
        else if (in[0].equalsIgnoreCase("Segitigasikusiku"))
                buatSegitigasikusiku(Integer.parseInt(in[1]), Integer.parseInt(in[2])); 
        else if (in[0].equalsIgnoreCase("pohon"))
                buatPohon();        
        else if (in[0].equalsIgnoreCase("jejak"))
                kurakuraku.setJejak(Boolean.parseBoolean(in[1]));
        else if (in[0].equalsIgnoreCase("pindah"))
                kurakuraku.setPosition(new Dimension(Integer.parseInt(in[1]),Integer.parseInt(in[2])));
        else{
                canvas.repaint(); 
                return "Perintah tidak dipahami.";
            }
        canvas.repaint();    
        return "Perintah sudah dilaksanakan.";
    }
    
    public void buatKotak(int ukuran ){        
        for (int i=0;i<4;i++){
            kurakuraku.maju(ukuran);
            kurakuraku.rotasi(90);
        }
    }

    public void buatPersegi(int panjang, int lebar){
        for (int i=0;i<2;i++){
            kurakuraku.maju(panjang);
            kurakuraku.rotasi(90);
            kurakuraku.maju(lebar);
            kurakuraku.rotasi(90);
        }

    }

    private void buatSegitigasikusiku(int panjang, int tinggi){
        double n = (panjang*panjang)+(tinggi*tinggi);
        double akar = Math.sqrt(n); // hasil akar adalah float atau double
        double sudut = 180 - Math.toDegrees(Math.atan(panjang / tinggi));
        kurakuraku.maju(panjang);
        kurakuraku.rotasi(90);
        kurakuraku.maju(tinggi);
        kurakuraku.rotasi(sudut);
        kurakuraku.maju(akar);
        

    }

    public void buatSegitiga(int ukuran){
        for (int i = 0; i < 3; i++) {
            kurakuraku.maju(ukuran);
            kurakuraku.rotasi(-120);
        }

    }        
    
    /**
     * Method ini membuat bentuk kotak menjadi lebih kecil dengan metode rekursif
     * tentukan ukuran
     * kurakura akan membentuk kotak
     * ukuran kotak akan berkurang 20 dengan metode rekursif 
     * @param ukuran
     */
    public void buatBoxes(int ukuran){
        if (ukuran >= 0) {
            buatKotak(ukuran);
            kurakuraku.setJejak(false);
            kurakuraku.maju(10);
            kurakuraku.rotasi(90);
            kurakuraku.maju(10);
            kurakuraku.rotasi(-90);
            kurakuraku.setJejak(true);
            buatBoxes(ukuran-20);
        }
    }

    /**
     * Method ini membuat bentuk bintang yang akan menjadi lebih kecil dengan metode rekursif
     * tentukan ukuran bintang
     * kurakura akan membentuk bintang dengan sudut yg sudah diperkirakan 
     * ukuran bintang akan berkurang setengahnya dengan metode rekursi
     * @param ukuran
     */
    public void buatBintang(int ukuran){
        if (ukuran >= 2){    
            for (int i = 0; i < 5; i++) {
                kurakuraku.maju(ukuran);
                kurakuraku.rotasi(-72);
                kurakuraku.maju(ukuran);
                kurakuraku.rotasi(144);                
            }
        kurakuraku.setJejak(false);
        kurakuraku.maju(ukuran/2+ukuran/4);
        kurakuraku.rotasi(90);
        kurakuraku.maju(ukuran/3);
        kurakuraku.rotasi(-90);
        kurakuraku.setJejak(true);
        buatBintang(ukuran/2);        
        }
    }

    /**
     * Method ini akan membuat kurakura menjadi bentuk sierpinski atau segitiga fractal
     * tentukan ukuran segitiga 
     * pertama-tama kura-kura akan membuat segitiga yang paling besar kemudian dilanjutkan dengan segitiga yg terletak ditengah
     * selanjutnya tentukan posisinya 
     * selanjutnya dengan memanggil method sierpinskirekursif kura-kura akan membuat segitiga dibagian kanan dengan metode rekursif(segitiga semakin kecil)
     * selanjutnya kura-kura akan membuat sisi bagian kiri dilanjutkan dengan bagian atas dengan menggunakan metode rekursif yang sama 
     * @param ukuran
     */

    public void buatSierpinski(int ukuran){
        buatSegitiga(ukuran);
        kurakuraku.maju(ukuran/2);
        sierpinskirekursif(ukuran/2);
    }

    public void sierpinskirekursif(int ukuran){
        if (ukuran > 4) {
            kurakuraku.rotasi(-60);
            buatSegitiga(ukuran);
            kurakuraku.rotasi(60);
            Dimension pos = kurakuraku.getPosition();
            kurakuraku.setJejak(false);
            kurakuraku.maju(ukuran/2);
            kurakuraku.setJejak(true);
            sierpinskirekursif(ukuran/2);// Membuat segitiga bagian kanan 
            kurakuraku.reset();
            kurakuraku.setPosition(pos);
            kurakuraku.setJejak(false);
            kurakuraku.mundur(ukuran/2);
            kurakuraku.setJejak(true);
            sierpinskirekursif(ukuran/2);// Membuat segitiga bagian kiri
            kurakuraku.reset();
            kurakuraku.setPosition(pos);
            kurakuraku.setJejak(false);
            kurakuraku.rotasi(-60);
            kurakuraku.maju(ukuran);
            kurakuraku.rotasi(-120);
            kurakuraku.maju(ukuran/2);
            kurakuraku.rotasi(180);
            kurakuraku.setJejak(true);
            sierpinskirekursif(ukuran/2);// Membuat segitiga bagian atas

        }
    }

    public void buatPohon(){        
        kurakuraku.setJejak(false);
        kurakuraku.reset();     
        kurakuraku.rotasi(90);
        kurakuraku.maju(100);
        kurakuraku.rotasi(180);
        buatPohon(6,50);        
        kurakuraku.reset();
    }
    
    private void buatPohon(int ukuran, int tinggi){
        if (ukuran>0){
            kurakuraku.setJejak(true);
            kurakuraku.maju(tinggi);                        
            kurakuraku.rotasi(-45);
            Dimension posAwal = kurakuraku.getPosition();
            double arah = kurakuraku.getArah();
            double sudut = arah;
            for(int i=0;i<3;i++){ 
                if (ukuran == 1){kurakuraku.setPosition(posAwal);// pada saat berada di ujung ranting atau pada loop terakhir
                    buatKotak(3);
                } 
                buatPohon(ukuran-1,(int)(tinggi/1.5));
                kurakuraku.setJejak(false);
                kurakuraku.setPosition(posAwal);
                kurakuraku.setArah(arah);                
                sudut+=45;
                kurakuraku.rotasi(sudut);  
            }                 
        }        
        kurakuraku.reset();
    }
}

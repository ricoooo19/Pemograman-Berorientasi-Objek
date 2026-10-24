package com.kasir.service;

/**
 * =========================================================================
 * KONSEP OOP: ABSTRAKSI (Interface)
 * =========================================================================
 * ICetakStruk mendefinisikan kontrak kemampuan mencetak struk belanja.
 * Kelas manapun yang mengimplementasikan interface ini harus menyediakan
 * realisasi pencetakan struk.
 */
public interface ICetakStruk {

    /**
     * Mencetak struk standar
     */
    void cetakStruk();

    /**
     * POLIMORFISME STATIS (Method Overloading)
     * Mencetak struk dengan pesan tambahan/promo kustom pada footer.
     */
    void cetakStruk(String pesanPromo);
}

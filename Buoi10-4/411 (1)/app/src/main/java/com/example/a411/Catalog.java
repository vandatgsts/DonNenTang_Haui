package com.example.a411;

import java.util.ArrayList;

public class Catalog {
    private String maDM,tenDM;
    private ArrayList<product> dsSanPham;
    public Catalog(String ma, String ten) {
// TODO Auto-generated constructor stub
        maDM=ma;tenDM=ten;
        dsSanPham = new ArrayList<product>();
    }
    /*
     * Kiểm tra sản phẩm đã tồn tại trong danh mục chưa
     * @para p - Product
     * @return true nếu tồn tại
     */
    public boolean kiemTraSanPham(product p){
        for(product p1:dsSanPham){
            if
            (p1.getMaSP().trim().equalsIgnoreCase(p.getMaSP().trim
                    ()))
                return true;
        }
        return false;
    }
    /*
     * Thêm 1 sản phẩm vào danh mục
     * thêm thành công true
     */
    public boolean addSP(product p) {
        boolean kiemTra=kiemTraSanPham(p);
        if (!kiemTra)
        {
            dsSanPham.add(p);
            return true;
        }
        else return false;
    }
    public ArrayList<product> getDsSanPham() {
        return dsSanPham;
    }
    @Override
    public String toString() {
        return maDM + "-" + tenDM ;
    }
}


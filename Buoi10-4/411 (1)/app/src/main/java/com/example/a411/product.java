package com.example.a411;

public class product extends good{

    //luu thông tin sản phẩm.

        private int soLuong;
        public product() {
// TODO Auto-generated constructor stub
            super();
            soLuong=0;
        }
        public product(String maSP, String tenSP, int
                soLuong) {
            super(maSP, tenSP);
            this.soLuong = soLuong;
        }
        @Override
        public String toString() {
            return super.toString() + " \t số lượng="+soLuong;
        }
        public int getSoLuong() {
            return soLuong;
        }
        public void setSoLuong(int soLuong) {
            this.soLuong = soLuong;
        }
    }

package com.example.genius.takeoutshop;

import java.util.List;

public class MyMenu {

    private List<MenuBean> menu;

    public List<MenuBean> getMenu() {
        return menu;
    }

    public void setMenu(List<MenuBean> menu) {
        this.menu = menu;
    }

    public static class MenuBean {
        /**
         * num : 101
         * name : 无辣不欢组合
         * introduce : 麦辣鸡翅4块，麦辣鸡腿堡1只，中雪碧1杯
         * price : 43
         * discount : null
         * sales : 208
         * C_num : 1
         */

        private String num;
        private String name;
        private String introduce;
        private String price;
        private Object discount;
        private String sales;
        private String C_num;

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public Object getDiscount() {
            return discount;
        }

        public void setDiscount(Object discount) {
            this.discount = discount;
        }

        public String getSales() {
            return sales;
        }

        public void setSales(String sales) {
            this.sales = sales;
        }

        public String getC_num() {
            return C_num;
        }

        public void setC_num(String C_num) {
            this.C_num = C_num;
        }
    }
}

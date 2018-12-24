package com.example.genius.takeoutshop;

import java.util.List;

public class AllOrder {

    private List<AllOrderBean> allOrder;

    public List<AllOrderBean> getAllOrder() {
        return allOrder;
    }

    public void setAllOrder(List<AllOrderBean> allOrder) {
        this.allOrder = allOrder;
    }

    public static class AllOrderBean {
        /**
         * orderNum : 2100453172
         * orderSum : 13
         * orderTime : 2018-10-27 11:12:00
         * orderDeliveryFee : 1
         * orderBoxFee : 1
         * deliveryPhone : 18266593356
         * userPhone : 18406539456
         * userAddress : 太原市万柏林区城区恒大雅苑10号楼3单元1501
         * commentContent : 刚开始觉得还可以，今天的量不够也就算了，感觉饭里面有一种怪怪的味道。
         * commentGrade : 刚开始觉得还可以，今天的量不够也就算了，感觉饭里面有一种怪怪的味道。
         * commentTime : 2018/10/27 12:40
         * allMeal : [{"name":"无辣不欢组合","count":"86"},{"name":"童子炸鸡","count":"88"},{"name":"孜然牛肉炒面套餐","count":"48"}]
         */

        private String orderNum;
        private String orderSum;
        private String orderTime;
        private String orderDeliveryFee;
        private String orderBoxFee;
        private String deliveryPhone;
        private String userPhone;
        private String userAddress;
        private String commentContent;
        private String commentGrade;
        private String commentTime;
        private List<AllMealBean> allMeal;

        public String getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(String orderNum) {
            this.orderNum = orderNum;
        }

        public String getOrderSum() {
            return orderSum;
        }

        public void setOrderSum(String orderSum) {
            this.orderSum = orderSum;
        }

        public String getOrderTime() {
            return orderTime;
        }

        public void setOrderTime(String orderTime) {
            this.orderTime = orderTime;
        }

        public String getOrderDeliveryFee() {
            return orderDeliveryFee;
        }

        public void setOrderDeliveryFee(String orderDeliveryFee) {
            this.orderDeliveryFee = orderDeliveryFee;
        }

        public String getOrderBoxFee() {
            return orderBoxFee;
        }

        public void setOrderBoxFee(String orderBoxFee) {
            this.orderBoxFee = orderBoxFee;
        }

        public String getDeliveryPhone() {
            return deliveryPhone;
        }

        public void setDeliveryPhone(String deliveryPhone) {
            this.deliveryPhone = deliveryPhone;
        }

        public String getUserPhone() {
            return userPhone;
        }

        public void setUserPhone(String userPhone) {
            this.userPhone = userPhone;
        }

        public String getUserAddress() {
            return userAddress;
        }

        public void setUserAddress(String userAddress) {
            this.userAddress = userAddress;
        }

        public String getCommentContent() {
            return commentContent;
        }

        public void setCommentContent(String commentContent) {
            this.commentContent = commentContent;
        }

        public String getCommentGrade() {
            return commentGrade;
        }

        public void setCommentGrade(String commentGrade) {
            this.commentGrade = commentGrade;
        }

        public String getCommentTime() {
            return commentTime;
        }

        public void setCommentTime(String commentTime) {
            this.commentTime = commentTime;
        }

        public List<AllMealBean> getAllMeal() {
            return allMeal;
        }

        public void setAllMeal(List<AllMealBean> allMeal) {
            this.allMeal = allMeal;
        }

        public static class AllMealBean {
            /**
             * name : 无辣不欢组合
             * count : 86
             */

            private String name;
            private String count;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }
        }
    }
}

package com.naruto.chapter01.em;

public enum Status {

    /**
     * 工作状态
     */
    FREE(1, "空闲"),
    BUSY(2, "忙碌"),
    VOCATION(3, "休假");

    private Integer code;

    private String info;

    Status() {
    }

    Status(Integer code, String info) {
        this.code = code;
        this.info = info;
    }

    public static void main(String[] args) {
        //枚举类型
        System.out.println(Status.FREE);
        //String类型
        System.out.println(Status.FREE.name());
        System.out.println(Status.FREE.equals(Status.FREE.name()));
        System.out.println(Status.FREE.getClass());
        System.out.println(Status.FREE.name().getClass());

        System.out.println(Status.FREE.code);
        System.out.println(Status.FREE.info);
    }
}

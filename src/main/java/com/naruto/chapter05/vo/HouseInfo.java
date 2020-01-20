package com.naruto.chapter05.vo;

import lombok.Data;

/**
 * 二代征信住房信息
 * @author gin
 * @date 2020/1/19 11:14
 */
@Data
public class HouseInfo {

    /**
     * 居住状况: 1-自置 2-按揭 3-亲属楼宇 4-集体宿舍 5-租房 6-共有住宅 7-其他 11-自有 12-借住 9-未知
     */
    private String liveCondition;

    private String liveAddr;

    private String livePhone;

    private String updateDate;

}

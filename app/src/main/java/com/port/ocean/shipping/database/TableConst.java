package com.port.ocean.shipping.database;
/**
 * Created by 超悟空 on 2015/9/21.
 */

/**
 * 各表数据库常量
 *
 * @author 超悟空
 * @version 1.0 2016/3/16
 * @since 1.0
 */
public interface TableConst {

    /**
     * 所有需要数据库初始化时创建的数据表的建表语句集合
     */
    String[] CREATE_TABLE_SQL_ARRAY = {CargoType.CREATE_TABLE_SQL , VehicleType.CREATE_TABLE_SQL};

    /**
     * 货物类别
     */
    interface CargoType {
        /**
         * 表名
         */
        String TABLE_NAME = "tb_cargo_type";

        /**
         * 建表语句
         */
        String CREATE_TABLE_SQL = String.format("CREATE TABLE IF NOT EXISTS %s ( %s INTEGER " +
                "PRIMARY KEY, %s TEXT)", TABLE_NAME, CommonConst._ID, CommonConst.NAME);
    }

    /**
     * 车型
     */
    interface VehicleType {
        /**
         * 表名
         */
        String TABLE_NAME = "tb_vehicle_type";

        /**
         * 建表语句
         */
        String CREATE_TABLE_SQL = String.format("CREATE TABLE IF NOT EXISTS %s ( %s INTEGER " +
                "PRIMARY" + " KEY, %s TEXT)", TABLE_NAME, CommonConst._ID, CommonConst.NAME);
    }
}

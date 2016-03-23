package com.port.ocean.shipping.function;
/**
 * Created by 超悟空 on 2015/9/22.
 */

import android.content.Context;
import android.util.Log;

import com.port.ocean.shipping.util.StaticValue;

import org.mobile.library.global.GlobalApplication;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 基础数据列表工具管理器
 *
 * @author 超悟空
 * @version 1.0 2016/3/16
 * @since 1.0
 */
public class DataListManager {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "DataListManager.";

    /**
     * 线程池线程数
     */
    private static final int POOL_COUNT = Runtime.getRuntime().availableProcessors() * 2 + 2;

    /**
     * 线程池
     */
    private static ExecutorService taskExecutor = Executors.newFixedThreadPool(POOL_COUNT);

    /**
     * 功能数据工具列表
     */
    private static Map<String, BaseDataListFunction> functionList = new ConcurrentHashMap<>();

    /**
     * 创建指定的数据列表工具
     *
     * @param tag 工具索引标签
     */
    public static void create(String tag) {
        create(tag, false);
    }

    /**
     * 升级创建指定的数据列表工具，
     * 当基础数据需要升级时调用
     *
     * @param tag 工具索引标签
     */
    public static void updateCreate(String tag) {

    }

    /**
     * 升级创建指定的数据列表工具，
     * 当基础数据需要升级时调用
     *
     * @param tag      工具索引标签
     * @param recreate 标识当工具已存在时是否重新创建工具对象，true表示重新创建，默认为false
     */
    public static void updateCreate(String tag, boolean recreate) {
        updateCreate(tag, null, recreate);
    }

    /**
     * 升级创建指定的数据列表工具，
     * 当基础数据需要升级时调用
     *
     * @param tag        工具索引标签
     * @param parameters 创建工具所需的参数，没有则传入null
     */
    public static void updateCreate(final String tag, final String[] parameters) {
        updateCreate(tag, parameters, false);
    }

    /**
     * 升级创建指定的数据列表工具，
     * 当基础数据需要升级时调用
     *
     * @param tag        工具索引标签
     * @param parameters 创建工具所需的参数，没有则传入null
     * @param recreate   标识当工具已存在时是否重新创建工具对象，true表示重新创建，默认为false
     */
    public static void updateCreate(final String tag, final String[] parameters, final boolean
            recreate) {
        Log.i(LOG_TAG + "create", "create tag:" + tag + " recreate tag:" + recreate);

        onCreate(tag, parameters, recreate, true);
    }

    /**
     * 创建指定的数据列表工具
     *
     * @param tag      工具索引标签
     * @param recreate 标识当工具已存在时是否重新创建工具对象，true表示重新创建，默认为false
     */
    public static void create(String tag, boolean recreate) {
        create(tag, null, recreate);
    }

    /**
     * 创建指定的数据列表工具
     *
     * @param tag        工具索引标签
     * @param parameters 创建工具所需的参数，没有则传入null
     */
    public static void create(final String tag, final String[] parameters) {
        create(tag, parameters, false);
    }

    /**
     * 创建指定的数据列表工具
     *
     * @param tag        工具索引标签
     * @param parameters 创建工具所需的参数，没有则传入null
     * @param recreate   标识当工具已存在时是否重新创建工具对象，true表示重新创建，默认为false
     */
    public static void create(final String tag, final String[] parameters, final boolean recreate) {
        Log.i(LOG_TAG + "create", "create tag:" + tag + " recreate tag:" + recreate);

        onCreate(tag, parameters, recreate, false);
    }

    /**
     * 创建指定的数据列表工具
     *
     * @param tag        工具索引标签
     * @param parameters 创建工具所需的参数，没有则传入null
     * @param recreate   标识当工具已存在时是否重新创建工具对象，true表示重新创建，默认为false
     * @param update     标识是否需要升级
     */
    private static void onCreate(final String tag, final String[] parameters, final boolean
            recreate, final boolean update) {
        Log.i(LOG_TAG + "onCreate", "create tag:" + tag + " recreate tag:" + recreate);

        taskExecutor.submit(new Runnable() {
            @Override
            public void run() {
                Log.i(LOG_TAG + "onCreate", "task run");
                // 尝试获取指定标签的工具对象
                BaseDataListFunction codeList = get(tag);

                if (codeList != null) {
                    // 目标已存在
                    if (recreate) {
                        // 需要替换
                        // 取消正在加载的对象
                        codeList.cancel();
                    } else {
                        // 不必替换且
                        if (codeList.isLoading()) {
                            // 正在加载
                            return;
                        } else {
                            // 加载完毕
                            // 通知加载完成
                            codeList.notifyExist();
                            return;
                        }
                    }
                }

                onCreate(tag, parameters, update);
            }
        });
    }

    /**
     * 创建指定的数据列表工具
     *
     * @param tag        工具索引标签
     * @param parameters 创建工具所需的参数，没有则传入null
     * @param update     标识是否需要升级
     */
    private static void onCreate(String tag, String[] parameters, boolean update) {
        BaseDataListFunction codeList = null;

        Context context = GlobalApplication.getGlobal();

        switch (tag) {
            case StaticValue.DataListTag.CARGO_TYPE_LIST:
                // 货物类别
                codeList = new CargoTypeListFunction(context);
                break;
            case StaticValue.DataListTag.VEHICLE_TYPE_LIST:
                // 车型
                codeList = new VehicleTypeListFunction(context);
                break;
        }

        if (codeList != null) {
            Log.i(LOG_TAG + "onCreate", "put list");
            functionList.put(tag, codeList);
            if (update) {
                codeList.onUpdate();
            } else {
                codeList.onCreate();
            }
        }
    }

    /**
     * 获取功能数据列表工具
     *
     * @param tag 工具标签
     *
     * @return 列表工具对象，没有返回null
     */
    public static BaseDataListFunction get(String tag) {
        Log.i(LOG_TAG + "get", "object exist is " + functionList.containsKey(tag));
        return functionList.get(tag);
    }
}

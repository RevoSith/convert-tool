package com.revosith.tool.util;

import net.sf.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc :  简单复制类.  仅支持无特殊构造函数的copy
 * Author: 左轮(haiyong.he@luckincoffee.com)
 * Date : 2019/5/28
 */
public class CopyUtils {

    /**
     * 单值拷贝
     *
     * @param targetClazz 目标类
     * @param sourceData  源数据
     * @param <T>         目标泛型
     * @param <S>         源泛型
     * @return 拷贝对象
     */
    public static <T, S> T copySigle(Class<T> targetClazz, S sourceData) {
        //复制器 默认带缓存.所以不用存起来.
        BeanCopier beanCopier = BeanCopier.create(sourceData.getClass(), targetClazz, false);
        return copySigle(targetClazz, sourceData, beanCopier);
    }

    /**
     * 单值复制器
     *
     * @param targetClazz 目标类
     * @param sourceData  源数据
     * @param beanCopier  复制器
     * @param <T>         目标泛型
     * @param <S>         源泛型
     * @return 复制结果
     */
    private static <T, S> T copySigle(Class<T> targetClazz, S sourceData, BeanCopier beanCopier) {
        T temp;
        try {
            temp = targetClazz.newInstance();
            beanCopier.copy(sourceData, temp, null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return temp;
    }

    /**
     * 列表拷贝
     *
     * @param targetClazz    目标类
     * @param sourceDataList 源数据
     * @param <T>            目标泛型
     * @param <S>            源泛型
     * @return 拷贝列表
     */
    public static <T, S> List<T> copyMulti(Class<T> targetClazz, List<S> sourceDataList) {

        if (CollectionUtils.isEmpty(sourceDataList)) {
            return null;
        }
        List<T> result = new ArrayList<>(sourceDataList.size());
        BeanCopier beanCopier = BeanCopier.create(sourceDataList.get(0).getClass(), targetClazz, false);

        for (S temp : sourceDataList) {
            result.add(copySigle(targetClazz, temp, beanCopier));
        }
        return result;
    }
}

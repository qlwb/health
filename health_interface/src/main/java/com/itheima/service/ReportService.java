package com.itheima.service;

import java.util.Map;

/**
 * @Author: dxw
 * @Date: 2019/11/24 16:31
 */
public interface ReportService {
    //获取运营数据
    Map<String,Object> getBusinessReport() throws Exception;
}

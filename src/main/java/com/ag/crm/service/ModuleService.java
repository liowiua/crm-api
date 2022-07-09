package com.ag.crm.service;

import com.ag.crm.domain.ResultVO;

public interface ModuleService {

    ResultVO selectAll(int pageNum, int pageSize);

    ResultVO hideModule(Integer id, Boolean hidden);

        /**
         * 批量/
    //    @Transactional
    //    int close(List<Long> ids, String note);

        /**
         * 批量删除
         */

//    int delete(List<Integer> ids);

//    int create(Module module);

//    int update(Integer id, Module module);

}
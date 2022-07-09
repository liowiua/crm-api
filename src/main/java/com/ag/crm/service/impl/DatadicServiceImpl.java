package com.ag.crm.service.impl;

import com.ag.crm.dao.DatadicMapper;
import com.ag.crm.domain.Datadic;
import com.ag.crm.domain.ResultVO;
import com.ag.crm.service.DatadicService;
import com.ag.crm.utils.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class DatadicServiceImpl implements DatadicService {

    @Autowired
    private DatadicMapper datadicMapper;

    @Override
    public ResultVO selectAll(Integer pageCount,Integer pageSize) {
        int start = (pageCount-1)*pageSize;
        List<Datadic> datadics = datadicMapper.selectAll(start,pageSize);

        int count = datadicMapper.selectCount();
        int pageCounts = count%pageSize==0?count/pageSize:count/pageSize+1;
        return ResultVO.success("查询成功",new PageHelper<>(count,pageCounts,datadics));

    }

    @Override
    public ResultVO insertDatadic(Datadic datadic) {
        Datadic datadic1 = datadicMapper.selectByNameAndValue(datadic.getDataDicName()
                                                ,datadic.getDataDicValue());
        if (datadic1 != null){
            return ResultVO.fail("该数据已存在");
        }
        datadic.setCreateDate(new Date());
        datadic.setIsValid(1);
        datadic.setUpdateDate(new Date());
        int insert = datadicMapper.insert(datadic);
        if (insert > 0){
            return ResultVO.success("添加成功",null);
        }
        return ResultVO.fail("添加失败");
    }

    @Override
    public ResultVO updateDatadic(Datadic datadic) {

        datadic.setUpdateDate(new Date());
        int i = datadicMapper.updateById(datadic);
        if (i > 0){
            return ResultVO.success("修改成功",null);
        }

        return ResultVO.fail("修改失败");
    }

    @Override
    public ResultVO deleteDatadicById(Integer id) {

        int i = datadicMapper.deleteByPrimaryKey(id);
        if (i > 0){
            return ResultVO.success("删除成功",null);
        }

        return ResultVO.fail("删除失败");
    }

    @Override
    @Transactional
    public ResultVO deleteDatadicByIds(String ids) {

        String[] split = ids.split(",");
        for (String s : split) {
            int i = datadicMapper.deleteByPrimaryKey(Integer.parseInt(s));
            if (i < 1){
                throw new RuntimeException("删除失败");
            }
        }

        return ResultVO.success("删除成功",null);
    }
}

package com.ag.crm.service.impl;

import com.ag.crm.dao.CustomerLinkmanMapper;
import com.ag.crm.dao.CustomerMapper;
import com.ag.crm.domain.*;
import com.ag.crm.utils.UserThreadLocal;
import com.ag.crm.utils.PageHelper;

import com.ag.crm.dao.SaleChanceMapper;
import com.ag.crm.dao.UserMapper;
import com.ag.crm.service.SaleChanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SaleChanceServiceImpl implements SaleChanceService {

    @Autowired
    private SaleChanceMapper saleChanceMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CustomerLinkmanMapper customerLinkmanMapper;

    @Override
    public ResultVO selectAllByPage(Integer pageCount,Integer pageSize) {
        int start = (pageCount-1)*pageSize;
        List<SaleChance> saleChances = saleChanceMapper.selectAllByPage(start,pageSize);
        List<SaleChanceVO> saleChanceVOS = new ArrayList<>();
        for (SaleChance saleChance : saleChances) {
            SaleChanceVO saleChanceVO = new SaleChanceVO();
            if (saleChance.getCreateMan() != null && saleChance.getCreateMan() != 0){
                String userName = userMapper.selectUserNameById(saleChance.getCreateMan());
                saleChanceVO.setCreateMan(userName);

            }
            if (saleChance.getAssignMan() != null && saleChance.getAssignMan() != 0){
                String userName = userMapper.selectUserNameById(saleChance.getAssignMan());
                saleChanceVO.setAssignMan(userName);
            }
            saleChanceVO = copy(saleChance,saleChanceVO);
            saleChanceVOS.add(saleChanceVO);
        }
        int count = saleChanceMapper.selectCount();
        int pageCounts = count%pageSize==0?count/pageSize:count/pageSize+1;
        return ResultVO.success("查询成功",new PageHelper<>(count,pageCounts,saleChanceVOS));
    }

    private SaleChanceVO copy(SaleChance saleChance, SaleChanceVO saleChanceVO) {

        saleChanceVO.setAssignTime(saleChance.getAssignTime());
        saleChanceVO.setCgjl(saleChance.getCgjl());
        saleChanceVO.setChanceSource(saleChance.getChanceSource());
        saleChanceVO.setCreateDate(saleChance.getCreateDate());
        saleChanceVO.setCustomerName(saleChance.getCustomerName());
        saleChanceVO.setDescription(saleChance.getDescription());
        saleChanceVO.setDevResult(saleChance.getDevResult());
        saleChanceVO.setId(saleChance.getId());
        saleChanceVO.setIsValid(saleChance.getIsValid());
        saleChanceVO.setLinkMan(saleChance.getLinkMan());
        saleChanceVO.setLinkPhone(saleChance.getLinkPhone());
        saleChanceVO.setOverview(saleChance.getOverview());
        saleChanceVO.setState(saleChance.getState());
        saleChanceVO.setUpdateDate(saleChance.getUpdateDate());
        return saleChanceVO;
    }

    @Override
    public ResultVO selectByCondition(SaleChanceDTO saleChanceDTO) {

        int start = (saleChanceDTO.getPageCount()-1)*saleChanceDTO.getPageSize();
        int userId = -1;
        if (saleChanceDTO.getCreateMan() != null && saleChanceDTO.getCreateMan() != ""){
            userId = userMapper.selectIdByUserName(saleChanceDTO.getCreateMan());
        }
        List<SaleChanceVO> saleChanceVOS = new ArrayList<>();
        List<SaleChance> saleChances = saleChanceMapper.selectByCondition(saleChanceDTO,userId,start);
        for (SaleChance saleChance : saleChances) {
            SaleChanceVO saleChanceVO = new SaleChanceVO();
            if (saleChance.getCreateMan() != null&&saleChance.getCreateMan() != 0){
                String userName = userMapper.selectUserNameById(saleChance.getCreateMan());
                saleChanceVO.setCreateMan(userName);

            }
            if (saleChance.getAssignMan() != null && saleChance.getAssignMan() != 0){
                String userName = userMapper.selectUserNameById(saleChance.getAssignMan());
                saleChanceVO.setAssignMan(userName);
            }
            saleChanceVO = copy(saleChance,saleChanceVO);
            saleChanceVOS.add(saleChanceVO);
        }
        int count = saleChanceMapper.selectCount();
        int pageCounts = count%saleChanceDTO.getPageSize()==0?count/saleChanceDTO.getPageSize():count/saleChanceDTO.getPageSize()+1;

        return ResultVO.success("查询成功",new PageHelper<>(count,pageCounts,saleChanceVOS));
    }

    @Override
    public ResultVO addSaleChance(SaleChance saleChance) {

        if (saleChance.getAssignMan() == null){
            saleChance.setState(0);
        }else{
            saleChance.setState(1);
            saleChance.setAssignTime(new Date());
        }
        saleChance.setDevResult(0);
        saleChance.setIsValid(1);
        saleChance.setCreateDate(new Date());

        int insert = saleChanceMapper.insert(saleChance);
        if (insert > 0){
            return ResultVO.success("添加成功",null);
        }
        return ResultVO.fail("添加失败");
    }

    @Override
    public ResultVO delectASaleChance(Integer saleChanceId) {
        User user = UserThreadLocal.get();
        SaleChance saleChance = saleChanceMapper.selectByPrimaryKey(saleChanceId);
        if (saleChance.getCreateMan() == user.getId()){
            return ResultVO.fail("不是您本人所创建，不可删除");
        }
        if (saleChance.getAssignMan() != null){
            return ResultVO.fail("已分配管理员，不可删除");
        }
        int i = saleChanceMapper.deleteByPrimaryKey(saleChanceId);
        if (i > 0){
            return ResultVO.success("删除成功",null);
        }
        return ResultVO.fail("删除失败");
    }

    @Transactional
    @Override
    public ResultVO delectMultipleSaleChance(String saleChanceIds) {
        User user = UserThreadLocal.get();
        String[] split = saleChanceIds.split(",");
        for (String s : split) {
            SaleChance saleChance = saleChanceMapper.selectByPrimaryKey(Integer.parseInt(s));
            if (saleChance.getCreateMan() == user.getId()){
                return ResultVO.fail("不是您本人所创建，不可删除");
            }
            if (saleChance.getAssignMan() != null){
                return ResultVO.fail("已分配管理员，不可删除");
            }
            saleChanceMapper.deleteByPrimaryKey(Integer.parseInt(s));
        }

        return ResultVO.success("删除成功",null);
    }

    @Override
    public ResultVO editSaleChance(SaleChanceVO saleChance) {
        saleChance.setUpdateDate(new Date());
        saleChance.setAssignTime(new Date());
        User user = UserThreadLocal.get();
//        saleChance.setCreateMan(user.getUserName());
        if (saleChance.getAssignMan() == null){
            return ResultVO.fail("分配人不能为空");
        }
        Integer integer = userMapper.selectIdByUserName(saleChance.getAssignMan());
        SaleChance saleChance1 = copy2(new SaleChance(), saleChance);
        saleChance1.setAssignMan(integer);
        if (integer == null){
            return ResultVO.fail("没有这个客户经理");
        }
        saleChance.setAssignMan(integer.toString());

        int i = saleChanceMapper.updateByPrimaryKey(saleChance1);
        if (i > 0){
            return ResultVO.success("修改成功",null);
        }
        return ResultVO.fail("修改失败");
    }

    private SaleChance copy2(SaleChance saleChance, SaleChanceVO saleChanceVO) {

        saleChance.setAssignTime(saleChanceVO.getAssignTime());
        saleChance.setCgjl(saleChanceVO.getCgjl());
        saleChance.setChanceSource(saleChanceVO.getChanceSource());
        saleChance.setCreateDate(saleChanceVO.getCreateDate());
        saleChance.setCustomerName(saleChanceVO.getCustomerName());
        saleChance.setDescription(saleChanceVO.getDescription());
        saleChance.setDevResult(saleChanceVO.getDevResult());
        saleChance.setId(saleChanceVO.getId());
        saleChance.setIsValid(saleChanceVO.getIsValid());
        saleChance.setLinkMan(saleChanceVO.getLinkMan());
        saleChance.setLinkPhone(saleChanceVO.getLinkPhone());
        saleChance.setOverview(saleChanceVO.getOverview());
        saleChance.setState(saleChanceVO.getState());
        saleChance.setUpdateDate(saleChanceVO.getUpdateDate());
        return saleChance;
    }

    @Override
    public ResultVO selectSaleChanceById(Integer id) {
        SaleChance saleChance = saleChanceMapper.selectByPrimaryKey(id);
        if (saleChance != null){
            return ResultVO.success("查询成功",saleChance);
        }
        throw new RuntimeException("查询失败");
    }

    @Override
    @Transactional
    public ResultVO updateDevResult(Integer id, Integer devResult) {

        if (id == null||devResult == null){
            return ResultVO.fail("id或开发结果不完整");
        }
        int i = saleChanceMapper.updateDevResult(id,devResult);
        if (i > 0){
            if (devResult == 2){
                Customer customer = new Customer();
                SaleChance saleChance = saleChanceMapper.selectByPrimaryKey(id);
                customer.setName(saleChance.getCustomerName());
                customer.setCusManagerId(saleChance.getAssignMan());
                customer.setIsValid(1);
                customer.setCreateDate(new Date());
                customer.setLevel(1);
                customer.setUpdateDate(new Date());
                String khno = "KH_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
                customer.setKhno(khno);
                customerMapper.insertCustomer(customer);
                int cId = customerMapper.selectIdByKhno(khno);
                CustomerLinkman customerLinkman = new CustomerLinkman();
                customerLinkman.setUpdateDate(new Date());
                customerLinkman.setIsValid(1);
                customerLinkman.setCeateDate(new Date());
                customerLinkman.setCusId(cId);
                customerLinkman.setLinkName(saleChance.getLinkMan());
                customerLinkman.setPhone(saleChance.getLinkPhone());
                customerLinkmanMapper.insert(customerLinkman);
            }
        }
        return ResultVO.success("更新成功",null);
    }


}

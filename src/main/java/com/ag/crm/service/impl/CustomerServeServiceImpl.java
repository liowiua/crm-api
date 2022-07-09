package com.ag.crm.service.impl;

import com.ag.crm.dao.CustomerMapper;
import com.ag.crm.dao.CustomerServeMapper;
import com.ag.crm.dao.DatadicMapper;
import com.ag.crm.dao.UserMapper;
import com.ag.crm.domain.*;
import com.ag.crm.service.CustomerServeService;
import com.ag.crm.utils.PageHelper;
import com.ag.crm.utils.UserThreadLocal;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CustomerServeServiceImpl implements CustomerServeService {

    @Autowired
    private CustomerServeMapper customerServeMapper;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DatadicMapper datadicMapper;

    @Override
    public ResultVO selectByParams(CustomerServeDTO customerServeDTO, Integer flag) {


        int start = (customerServeDTO.getPageCount()-1)*customerServeDTO.getPageSize();
        customerServeDTO.setPageCount(start);
        if (flag != null&&flag == 1){
            User user = UserThreadLocal.get();
            customerServeDTO.setAssigner(user.getId());
        }
        List<CustomerServe> customerServes = customerServeMapper.selectByParams(customerServeDTO);
        List<CustomerServeVO> customerServeVOS = new ArrayList<>();
        customerServes.forEach(
                customerServe -> {
                    CustomerServeVO customerServeVO = copy(customerServe);
                    Datadic datadic = datadicMapper.selectByPrimaryKey(customerServe.getServeType());
                    if (datadic != null && datadic.getDataDicName()!= null)
                    customerServeVO.setDicValue(datadic.getDataDicValue());
                    Customer customer = customerMapper.selectByPrimaryKey(customerServe.getCustomer());
                    customerServeVO.setCustomer(customer.getName());
                    String assigner = userMapper.selectUserNameById(customerServe.getAssigner());
                    String createPeople = userMapper.selectUserNameById(customerServe.getCreatePeople());
                    customerServeVO.setAssigner(assigner);
                    customerServeVO.setCreatePeople(createPeople);
                    if (customerServe.getServiceProcePeople() != null){
                        String procePeople = userMapper.selectUserNameById(customerServe.getServiceProcePeople());
                        customerServeVO.setServiceProcePeople(procePeople);
                    }

                    customerServeVOS.add(customerServeVO);
                }
        );
        int count = customerServeMapper.selectCount(customerServeDTO);
        int pageCounts = count%customerServeDTO.getPageSize()==0?count/customerServeDTO.getPageSize():count/customerServeDTO.getPageSize()+1;

        return ResultVO.success("查询成功",new PageHelper<>(count,pageCounts,customerServeVOS));
    }

    private CustomerServeVO copy(CustomerServeVO2 customerServe) {

        CustomerServeVO customerServeVO = new CustomerServeVO();
        customerServeVO.setAssignTime(customerServe.getAssignTime());
        customerServeVO.setCreateDate(customerServe.getCreateDate());
        customerServeVO.setId(customerServe.getId());
        customerServeVO.setIsValid(customerServe.getIsValid());
        customerServeVO.setMyd(customerServe.getMyd());
        customerServeVO.setOverview(customerServe.getOverview());
        customerServeVO.setServeType(customerServe.getServeType());
        customerServeVO.setServiceProce(customerServe.getServiceProce());
        customerServeVO.setServiceProceResult(customerServe.getServiceProceResult());
//        customerServeVO.setServiceProcePeople(customerServe.getServiceProcePeople());
        customerServeVO.setServiceProceTime(customerServe.getServiceProceTime());
        customerServeVO.setServiceRequest(customerServe.getServiceRequest());
        customerServeVO.setState(customerServe.getState());
        customerServeVO.setUpdateDate(customerServe.getUpdateDate());

        return customerServeVO;
    }

    private CustomerServeVO copy(CustomerServe customerServe) {

        CustomerServeVO customerServeVO = new CustomerServeVO();
        customerServeVO.setAssignTime(customerServe.getAssignTime());
        customerServeVO.setCreateDate(customerServe.getCreateDate());
        customerServeVO.setId(customerServe.getId());
        customerServeVO.setIsValid(customerServe.getIsValid());
        customerServeVO.setMyd(customerServe.getMyd());
        customerServeVO.setOverview(customerServe.getOverview());
        customerServeVO.setServeType(customerServe.getServeType());
        customerServeVO.setServiceProce(customerServe.getServiceProce());
        customerServeVO.setServiceProceResult(customerServe.getServiceProceResult());
//        customerServeVO.setServiceProcePeople(customerServe.getServiceProcePeople());
        customerServeVO.setServiceProceTime(customerServe.getServiceProceTime());
        customerServeVO.setServiceRequest(customerServe.getServiceRequest());
        customerServeVO.setState(customerServe.getState());
        customerServeVO.setUpdateDate(customerServe.getUpdateDate());

        return customerServeVO;
    }


    /**
     * 添加服务操作
     *  1. 参数校验
     *      客户名 customer
     *          非空，客户表中存在客户记录
     *      服务类型 serveType
     *          非空
     *      服务请求内容  serviceRequest
     *          非空
     *  2. 设置参数的默认值
     *      服务状态
     *          服务创建状态  fw_001
     *      是否有效
     *      创建时间
     *      更新时间
     *      创建人 createPeople
     *          （前端页面中通过cookie获取，传递到后台）
     *  2. 执行添加操作，判断受影响的行数
     *
     *
     * 乐字节：专注线上IT培训
     * 答疑老师微信：lezijie
     * @param customerServeVO
     * @return void
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public ResultVO insertCustomerServe(CustomerServeVO customerServeVO) {

        /* 1. 参数校验 */
        // 客户名 customer     非空
        if (StringUtils.isBlank(customerServeVO.getCustomer())){
            return ResultVO.fail("客户名不能为空！");
        }
        CustomerServe customerServe = copy(customerServeVO);
        // 客户名 customer     客户表中存在客户记录
        Customer customer = customerMapper.selectByCName(customerServeVO.getCustomer());
        if (customer == null){
            return ResultVO.fail("客户不存在！");
        }
        customerServe.setCustomer(customer.getId());


        // 服务类型 serveType  非空
        if (customerServeVO.getServeType()==null){
            return ResultVO.fail("请选择服务类型！");
        }

        //  服务请求内容  serviceRequest  非空
        if (StringUtils.isBlank(customerServeVO.getServiceRequest())){
            return ResultVO.fail("服务请求内容不能为空！");
        }

        /* 2. 设置参数的默认值 */
        //  服务状态    服务创建状态  fw_001
        customerServe.setState(CustomerServeStatus.CREATED.getState());
        customerServe.setIsValid(1);
        customerServe.setCreateDate(new Date());
        customerServe.setUpdateDate(new Date());



        /* 2. 执行添加操作，判断受影响的行数 */
        if (customerServeMapper.insert(customerServe) > 0){
            return ResultVO.success("添加成功",null);
        }
        return ResultVO.fail("添加失败");
    }


    private CustomerServe copy(CustomerServeVO customerServeVO) {

        CustomerServe customerServe = new CustomerServe();
        customerServe.setAssignTime(customerServeVO.getAssignTime());
        if(customerServeVO.getCreatePeople() != null && customerServeVO.getCreatePeople()!= ""){
            customerServe.setCreatePeople(Integer.parseInt(customerServeVO.getCreatePeople()));
        }

        customerServe.setCreateDate(customerServeVO.getCreateDate());
        customerServe.setId(customerServeVO.getId());
        customerServe.setIsValid(customerServeVO.getIsValid());
        customerServe.setMyd(customerServeVO.getMyd());
        customerServe.setOverview(customerServeVO.getOverview());
        customerServe.setServeType(customerServeVO.getServeType());
        customerServe.setServiceProce(customerServeVO.getServiceProce());
        customerServe.setServiceProceResult(customerServeVO.getServiceProceResult());
        if (customerServeVO.getServiceProcePeople()!= null){
            customerServe.setServiceProcePeople(Integer.parseInt(customerServeVO.getServiceProcePeople()));
        }
//        customerServe.setServiceProcePeople(customerServeVO.getServiceProcePeople());
        customerServe.setServiceProceTime(customerServeVO.getServiceProceTime());
        customerServe.setServiceRequest(customerServeVO.getServiceRequest());
        customerServe.setState(customerServeVO.getState());
        customerServe.setUpdateDate(customerServeVO.getUpdateDate());

        return customerServe;
    }


    /**
     * 服务分配/服务处理/服务反馈
     *  1. 参数校验与设置参数的默认值
     *      客户服务ID
     *          非空，记录必须存在
     *      客户服务状态
     *          如果服务状态为 服务分配状态 fw_002
     *              分配人
     *                  非空，分配用户记录存在
     *              分配时间
     *                  系统当前时间
     *              更新时间
     *                  系统当前时间
     *
     *          如果服务状态为 服务处理状态 fw_003
     *              服务处理内容
     *                  非空
     *              服务处理时间
     *                  系统当前时间
     *              更新时间
     *                  系统当前时间
     *
     *          如果服务状态是 服务反馈状态  fw_004
     *              服务反馈内容
     *                  非空
     *              服务满意度
     *                  非空
     *              更新时间
     *                  系统当前时间
     *              服务状态
     *                  设置为 服务归档状态 fw_005
     *
     * 2. 执行更新操作，判断受影响的行数
     *
     *
     * 乐字节：专注线上IT培训
     * 答疑老师微信：lezijie
     * @param customerServeVO
     * @return void
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public ResultVO updateCustomerServe(CustomerServeVO customerServeVO) {
        // 客户服务ID  非空且记录存在
        if(customerServeVO.getId() == null
                || customerServeMapper.selectByPrimaryKey(customerServeVO.getId()) == null){
            return ResultVO.fail("待更新的服务记录不存在！");
        }

        CustomerServe customerServe = copy(customerServeVO);
        // 判断客户服务的服务状态
        if (CustomerServeStatus.ASSIGNED.getState().equals(customerServeVO.getState())) {
            // 服务分配操作
            // 分配人       非空，分配用户记录存在
            if (StringUtils.isBlank(customerServeVO.getAssigner())){
                return ResultVO.fail("待分配用户不能为空！");
            }
            User user = userMapper.selectByUserName(customerServeVO.getAssigner());
            if (user == null){
                return ResultVO.fail("待分配用户不存在！");
            }
            // 分配时间     系统当前时间
            customerServe.setAssignTime(new Date());

            customerServe.setAssigner(user.getId());


        } else if (CustomerServeStatus.PROCED.getState().equals(customerServeVO.getState())) {
            // 服务处理操作
            // 服务处理内容   非空
            if (StringUtils.isBlank(customerServeVO.getServiceProce())){
                return ResultVO.fail("服务处理内容不能为空！");
            }
            // 服务处理时间   系统当前时间
            customerServe.setServiceProceTime(new Date());

        } else if (CustomerServeStatus.FEED_BACK.getState().equals(customerServeVO.getState())) {
            // 服务反馈操作
            // 服务反馈内容   非空
            if (StringUtils.isBlank(customerServeVO.getServiceProceResult())){
                return ResultVO.fail("服务反馈内容不能为空！");
            }
            // 服务满意度     非空
            if (customerServeVO.getMyd() == null){
                return ResultVO.fail("请选择服务反馈满意度！");
            }
            // 服务状态      设置为 服务归档状态 fw_005
            customerServe.setState(CustomerServeStatus.ARCHIVED.getState());

        }

        // 更新时间     系统当前时间
        customerServe.setUpdateDate(new Date());

        // 执行更新操作，判断受影响的行数
        if (customerServeMapper.updateByPrimaryKeySelective(customerServe)>0){
            return ResultVO.success("更新成功",null);
        }
        return ResultVO.fail("更新失败");

    }

    @Override
    public ResultVO archivedCustomerServe(Integer pageCount, Integer pageSize) {
        int start = (pageCount-1)*pageSize;
        List<CustomerServe>  customerServes = customerServeMapper.selectByPage(start,pageSize);

        int count = customerServeMapper.selectAllCount();
        int pageCounts = count%pageSize==0?count/pageSize:count/pageSize+1;
        return ResultVO.success("查询成功",new PageHelper<>(count,pageCounts,customerServes));

    }

}

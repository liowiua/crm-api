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

        return ResultVO.success("????????????",new PageHelper<>(count,pageCounts,customerServeVOS));
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
     * ??????????????????
     *  1. ????????????
     *      ????????? customer
     *          ???????????????????????????????????????
     *      ???????????? serveType
     *          ??????
     *      ??????????????????  serviceRequest
     *          ??????
     *  2. ????????????????????????
     *      ????????????
     *          ??????????????????  fw_001
     *      ????????????
     *      ????????????
     *      ????????????
     *      ????????? createPeople
     *          ????????????????????????cookie???????????????????????????
     *  2. ?????????????????????????????????????????????
     *
     *
     * ????????????????????????IT??????
     * ?????????????????????lezijie
     * @param customerServeVO
     * @return void
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public ResultVO insertCustomerServe(CustomerServeVO customerServeVO) {

        /* 1. ???????????? */
        // ????????? customer     ??????
        if (StringUtils.isBlank(customerServeVO.getCustomer())){
            return ResultVO.fail("????????????????????????");
        }
        CustomerServe customerServe = copy(customerServeVO);
        // ????????? customer     ??????????????????????????????
        Customer customer = customerMapper.selectByCName(customerServeVO.getCustomer());
        if (customer == null){
            return ResultVO.fail("??????????????????");
        }
        customerServe.setCustomer(customer.getId());


        // ???????????? serveType  ??????
        if (customerServeVO.getServeType()==null){
            return ResultVO.fail("????????????????????????");
        }

        //  ??????????????????  serviceRequest  ??????
        if (StringUtils.isBlank(customerServeVO.getServiceRequest())){
            return ResultVO.fail("?????????????????????????????????");
        }

        /* 2. ???????????????????????? */
        //  ????????????    ??????????????????  fw_001
        customerServe.setState(CustomerServeStatus.CREATED.getState());
        customerServe.setIsValid(1);
        customerServe.setCreateDate(new Date());
        customerServe.setUpdateDate(new Date());



        /* 2. ????????????????????????????????????????????? */
        if (customerServeMapper.insert(customerServe) > 0){
            return ResultVO.success("????????????",null);
        }
        return ResultVO.fail("????????????");
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
     * ????????????/????????????/????????????
     *  1. ???????????????????????????????????????
     *      ????????????ID
     *          ???????????????????????????
     *      ??????????????????
     *          ????????????????????? ?????????????????? fw_002
     *              ?????????
     *                  ?????????????????????????????????
     *              ????????????
     *                  ??????????????????
     *              ????????????
     *                  ??????????????????
     *
     *          ????????????????????? ?????????????????? fw_003
     *              ??????????????????
     *                  ??????
     *              ??????????????????
     *                  ??????????????????
     *              ????????????
     *                  ??????????????????
     *
     *          ????????????????????? ??????????????????  fw_004
     *              ??????????????????
     *                  ??????
     *              ???????????????
     *                  ??????
     *              ????????????
     *                  ??????????????????
     *              ????????????
     *                  ????????? ?????????????????? fw_005
     *
     * 2. ?????????????????????????????????????????????
     *
     *
     * ????????????????????????IT??????
     * ?????????????????????lezijie
     * @param customerServeVO
     * @return void
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public ResultVO updateCustomerServe(CustomerServeVO customerServeVO) {
        // ????????????ID  ?????????????????????
        if(customerServeVO.getId() == null
                || customerServeMapper.selectByPrimaryKey(customerServeVO.getId()) == null){
            return ResultVO.fail("????????????????????????????????????");
        }

        CustomerServe customerServe = copy(customerServeVO);
        // ?????????????????????????????????
        if (CustomerServeStatus.ASSIGNED.getState().equals(customerServeVO.getState())) {
            // ??????????????????
            // ?????????       ?????????????????????????????????
            if (StringUtils.isBlank(customerServeVO.getAssigner())){
                return ResultVO.fail("??????????????????????????????");
            }
            User user = userMapper.selectByUserName(customerServeVO.getAssigner());
            if (user == null){
                return ResultVO.fail("???????????????????????????");
            }
            // ????????????     ??????????????????
            customerServe.setAssignTime(new Date());

            customerServe.setAssigner(user.getId());


        } else if (CustomerServeStatus.PROCED.getState().equals(customerServeVO.getState())) {
            // ??????????????????
            // ??????????????????   ??????
            if (StringUtils.isBlank(customerServeVO.getServiceProce())){
                return ResultVO.fail("?????????????????????????????????");
            }
            // ??????????????????   ??????????????????
            customerServe.setServiceProceTime(new Date());

        } else if (CustomerServeStatus.FEED_BACK.getState().equals(customerServeVO.getState())) {
            // ??????????????????
            // ??????????????????   ??????
            if (StringUtils.isBlank(customerServeVO.getServiceProceResult())){
                return ResultVO.fail("?????????????????????????????????");
            }
            // ???????????????     ??????
            if (customerServeVO.getMyd() == null){
                return ResultVO.fail("?????????????????????????????????");
            }
            // ????????????      ????????? ?????????????????? fw_005
            customerServe.setState(CustomerServeStatus.ARCHIVED.getState());

        }

        // ????????????     ??????????????????
        customerServe.setUpdateDate(new Date());

        // ?????????????????????????????????????????????
        if (customerServeMapper.updateByPrimaryKeySelective(customerServe)>0){
            return ResultVO.success("????????????",null);
        }
        return ResultVO.fail("????????????");

    }

    @Override
    public ResultVO archivedCustomerServe(Integer pageCount, Integer pageSize) {
        int start = (pageCount-1)*pageSize;
        List<CustomerServe>  customerServes = customerServeMapper.selectByPage(start,pageSize);

        int count = customerServeMapper.selectAllCount();
        int pageCounts = count%pageSize==0?count/pageSize:count/pageSize+1;
        return ResultVO.success("????????????",new PageHelper<>(count,pageCounts,customerServes));

    }

}

package com.crm.workbench.customer;

import com.crm.common.Vo.ReturnObject;
import com.crm.common.constants.ConstantsEnum;
import com.crm.common.utils.DateTimeUtil;
import com.crm.common.utils.UUIDUtils;
import com.crm.settings.entity.User;
import com.crm.settings.service.UserService;
import com.crm.workbench.entity.Customer;
import com.crm.workbench.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Classname CustomerController
 * @Description TODO
 * @Version 1.0.0
 * @Date 2023/3/11 15:36
 * @Author RainGrd
 */
@Controller
public class CustomerController {

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/workbench/customer/toIndex.do")
    public ModelAndView toIndex() {
        ModelAndView modelAndView = new ModelAndView();
        //查詢所有者
        List<User> users = userService.queryAllUsers();
        modelAndView.addObject("users", users);
        modelAndView.setViewName("/workbench/customer/index");
        return modelAndView;
    }

    @RequestMapping("/workbench/customer/queryCustomerByConditionForPage.do")
    @ResponseBody
    public ReturnObject queryCustomerByConditionForPage(@RequestBody Map<String, String> map) {
        return ReturnObject.success(customerService.queryCustomerByConditionForPage(map));
    }


    @RequestMapping("/workbench/customer/saveCustomer.do")
    @ResponseBody
    public ReturnObject saveCustomer(@RequestBody Customer customer, HttpSession session) {
        //封装数据
        customer.setId(UUIDUtils.getUUID());
        //取出当前的用户对象
        User user = (User) session.getAttribute(ConstantsEnum.SESSION_USER.getStr());
        customer.setCreateTime(DateTimeUtil.convertDateCustomStringFormat(new Date()));
        customer.setCreateBy(user.getId());
        return customerService.saveCustomer(customer) > 0 ? ReturnObject.success() : ReturnObject.error();
    }

}

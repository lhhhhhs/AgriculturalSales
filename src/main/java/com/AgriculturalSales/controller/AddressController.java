package com.AgriculturalSales.controller;

import com.AgriculturalSales.bean.Address;
import com.AgriculturalSales.bean.Order;
import com.AgriculturalSales.bean.User;
import com.AgriculturalSales.service.AddressService;
import com.AgriculturalSales.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@Controller
public class AddressController {


    @Autowired
    AddressService addressService;

    @Autowired
    OrderService orderService;

    /**
     *进入地址页面
     * @return
     */
    @RequestMapping("/address")
    public String address(HttpSession session , @RequestParam(value = "orderId",defaultValue = "null") String orderId , Map<String,Object> map,@RequestParam(value="msg",defaultValue=" ")String msg){
        User user = (User)session.getAttribute("user");
        if("null".equals(orderId)) {
            map.put("msg", msg);
            return "redirect:/addressDefault";
        }else {
            List<Address> addressList = addressService.getAddressListByUser(user.getId());
            Order orderById = orderService.getOrderById(orderId);
            Integer addressId = orderById.getAddressId();
            if (addressList != null) {
                map.put("addressList", addressList);
            }
            map.put("addressId", addressId);
            map.put("id", orderId);
        }
        map.put("msg", msg);
        return "user/address";
    }

    /**
     * 添加地址
     * @param address
     * @param bindingResult
     * @param orderId
     * @param session
     * @param map
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/AddAddress")
    public String addAddress(@Valid Address address , BindingResult bindingResult, @RequestParam(value = "orderId",defaultValue = "null") String orderId, HttpSession session ,Map<String,Object> map) throws UnsupportedEncodingException {
        if(bindingResult.hasErrors()){
            return "redirect:/address?orderId="+orderId+"&msg="+URLEncoder.encode("地址输入有误","UTF-8");
        }
        User user = (User) session.getAttribute("user");
        Boolean b = addressService.checkAddressByUser(user.getId());
        Address address1 = addressService.codeToAddress(address);
        address1.setUid(user.getId());
        if (b){
            address1.setDefaultStatus(0);
        }else {
            address1.setDefaultStatus(1);
        }
        if("null".equals(orderId)) {
            return "redirect:/addressDefault";
        }
        addressService.saveAddress(address1);
        return "redirect:/address?orderId="+orderId;
    }

    @RequestMapping("/AddAddressDefault")
    public String AddAddressDefault(@Valid Address address , BindingResult bindingResult,  HttpSession session ,Map<String,Object> map) throws UnsupportedEncodingException {
        if(bindingResult.hasErrors()){
            return "redirect:/addressDefault?msg="+URLEncoder.encode("地址输入有误","UTF-8");
        }
        User user = (User) session.getAttribute("user");
        Boolean b = addressService.checkAddressByUser(user.getId());
        Address address1 = addressService.codeToAddress(address);
        address1.setUid(user.getId());
        if (b){
            address1.setDefaultStatus(0);
        }else {
            address1.setDefaultStatus(1);
        }
        addressService.saveAddress(address1);
        return "redirect:/addressDefault";
    }

//    /**
//     * 编辑地址
//     * @param addressId
//     * @param orderId
//     * @param session
//     * @return
//     */
//    @RequestMapping("/editAddress")
//    public String editAddress(Integer addressId,String orderId,HttpSession session){
//        User user = (User) session.getAttribute("user");
//        return "redirect:/address?orderId="+orderId;
//    }


    /**
     * 订单地址页面删除地址
     * @param addressId
     * @param orderId
     * @param session
     * @return
     */
    @RequestMapping("/delAddress")
    public String delAddress(Integer addressId,@RequestParam(value = "orderId",defaultValue = "null") String orderId,HttpSession session) throws UnsupportedEncodingException {
        Boolean b = addressService.checkAddressByOrder(addressId);
        Boolean defaultByOrder = addressService.checkAddressIsDefaultByOrder(addressId);
        if(defaultByOrder) {
            return "redirect:/address?orderId=" + orderId + "&msg=" + URLEncoder.encode("请先把其他地址设为默认地址！", "UTF-8");
        }else {
            if (b) {
                addressService.delAdd(addressId);
                return "redirect:/address?orderId=" + orderId;
            } else {
                return "redirect:/address?orderId=" + orderId + "&msg=" + URLEncoder.encode("该地址下还有订单未完成！", "UTF-8");
            }
        }
    }

    /**
     * 基本地址页面删除地址
     * @param addressId
     * @param session
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/delAddressDefault")
    public String delAddressDefault(Integer addressId,HttpSession session) throws UnsupportedEncodingException {
        Boolean b = addressService.checkAddressByOrder(addressId);
        Boolean defaultByOrder = addressService.checkAddressIsDefaultByOrder(addressId);
        if(defaultByOrder) {
            return "redirect:/addressDefault?msg=" + URLEncoder.encode("请先把其他地址设为默认地址！", "UTF-8");
        }else {
            if (b) {
                addressService.delAdd(addressId);
                return "redirect:/addressDefault";
            } else {
                return "redirect:/addressDefault?msg=" + URLEncoder.encode("该地址下还有订单未完成！", "UTF-8");
            }
        }
    }


    /**
     * 为当前订单设置地址
     * @param addressId
     * @param map
     * @param orderId
     * @return
     */
    @RequestMapping("/selectAddress")
    public String selectAddress(Integer addressId,Map<String,Object> map,String orderId) throws UnsupportedEncodingException {
        Boolean b = orderService.checkOrderStatus(orderId);
        if(b){
            return "redirect:/address?orderId="+orderId+"&msg="+URLEncoder.encode("该订单已发货！","UTF-8");
        }
        Order order = new Order(orderId, null, null, null, addressId, null);
        orderService.updateOrder(order);
        return "redirect:/address?orderId="+orderId;
    }

    /**
     * 跳转到更新页面
     * @param addressId
     * @param map
     * @param orderId
     * @return
     */
    @RequestMapping("/editAddress")
    public String editAddress(Integer addressId,Map<String,Object> map,@RequestParam(value = "orderId",defaultValue = "null") String orderId){
        Address addressById = addressService.getAddressById(addressId);
        map.put("address", addressById);
        map.put("orderId", orderId);
        return "user/addressUpdate";
    }

    /**
     * 把地址设为默认地址
     * @param addressId
     * @param map
     * @param orderId
     * @return
     */
    @RequestMapping("/defaultAddress")
    public String defaultAddress(Integer addressId,Map<String,Object> map,@RequestParam(value = "orderId",defaultValue = "null") String orderId,HttpSession session){
        User user = (User) session.getAttribute("user");
        addressService.setDefaultAddress(addressId,user);
        return "redirect:/address?orderId="+orderId;
    }

    /**
     * 基本地址页面设置默认地址
     * @param addressId
     * @param map
     * @param session
     * @return
     */
    @RequestMapping("/defaultAddressDefault")
    public String defaultAddressDefault(Integer addressId,Map<String,Object> map,HttpSession session){
        User user = (User) session.getAttribute("user");
        addressService.setDefaultAddress(addressId,user);
        return "redirect:/addressDefault";
    }


    /**
     * 保存更新地址
     * @param address
     * @param bindingResult
     * @param map
     * @param orderId
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/updateAddress")
    public String updateAddress(@Valid Address address , BindingResult bindingResult,Map<String,Object> map,@RequestParam(value = "orderId",defaultValue = "null")String orderId) throws UnsupportedEncodingException {
        if (bindingResult.hasErrors()){
            return "redirect:/address?orderId="+orderId+"&msg="+URLEncoder.encode("地址输入有误,未完成修改！","UTF-8");
        }
        addressService.updateAddress(address);
        return "redirect:/address?orderId="+orderId;
    }


    /**
     * 跳转到基本的地址设置页面
     * @param map
     * @param session
     * @param msg
     * @return
     */
    @RequestMapping("/addressDefault")
    public String addressDefault(Map<String,Object> map,HttpSession session,@RequestParam(value="msg",defaultValue=" ")String msg)  {
        User user = (User)session.getAttribute("user");
        List<Address> addressListByUser = addressService.getAddressListByUser(user.getId());
        if (addressListByUser!=null){
            map.put("addressList", addressListByUser);
        }
        map.put("msg", msg);
        return "user/defaultAddress";
    }
}

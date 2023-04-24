package com.crm.settings.dictionary.controller.value;

import com.crm.common.Vo.ReturnObject;
import com.crm.common.utils.UUIDUtils;
import com.crm.settings.entity.DicValue;
import com.crm.settings.service.DicTypeService;
import com.crm.settings.service.DicValueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @Classname DicValueController
 * @Description TODO
 * @Version 1.0.0
 * @Date 2023/3/9 16:41
 * @Author RainGrd
 */
@Controller
@Slf4j
public class DicValueController {

    @Autowired
    private DicValueService dicValueService;

    @Autowired
    private DicTypeService dicTypeService;

    /**
     * 跳转至字典纸首页
     *
     * @return
     */
    @RequestMapping("/settings/dictionary/value/toValueIndex.do")
    public String toValueIndex() {
        return "settings/dictionary/value/index";
    }

    /**
     * 查询
     *
     * @param beginNo
     * @param pageSize
     * @return
     */
    @RequestMapping("/settings/dictionary/type/queryDicValueList.do")
    @ResponseBody
    public ReturnObject queryDicValueList(@RequestParam("beginNo") String beginNo, @RequestParam("pageSize") String pageSize) {
        log.info("beginNo:{},pageSize:{}", beginNo, pageSize);
        return ReturnObject.success(dicValueService.queryDicValueForPage(beginNo, pageSize));
    }

    @RequestMapping("/settings/dictionary/value/toSaveValue.do")
    public ModelAndView toSaveValue() {
        ModelAndView modelAndView = new ModelAndView();
        //查询所有字典类型编码
        List<String> dictionaryTypeCodeList = dicTypeService.queryDictionaryTypeCodeList();
        modelAndView.addObject("dictionaryTypeCodeList", dictionaryTypeCodeList);
        modelAndView.setViewName("settings/dictionary/value/save");
        return modelAndView;
    }


    @RequestMapping("/settings/dictionary/value/toEditValue.do")
    public ModelAndView toEditValue(@RequestParam("id") String id) {
        ModelAndView modelAndView = new ModelAndView();
        //根据Id查询数据
        DicValue dicValue = dicValueService.queryDicValueById(id);
        modelAndView.addObject("dicValue", dicValue);
        modelAndView.setViewName("settings/dictionary/value/edit");
        return modelAndView;
    }

    @RequestMapping("/settings/dictionary/value/saveDivValue.do")
    @ResponseBody
    public ReturnObject saveDivValue(@RequestBody DicValue dicValue) {
        log.info("要保存的数据字典值:{}", dicValue);
        /*封装参数*/
        dicValue.setId(UUIDUtils.getUUID());
        int i = dicValueService.saveDivValue(dicValue);
        return i > 0 ? ReturnObject.success() : ReturnObject.error();
    }

    @RequestMapping("/settings/dictionary/value/editDicValue.do")
    @ResponseBody
    public ReturnObject editDicValue(@RequestBody DicValue dicValue) {
        return dicValueService.editDicValue(dicValue) > 0 ? ReturnObject.success() : ReturnObject.error();
    }

    @RequestMapping("/settings/dictionary/value/removeDicValueById.do")
    @ResponseBody
    public ReturnObject removeDicValueById(@RequestParam("ids[]") String[] ids) {
        log.info("ids:{}",ids[0]);
        return dicValueService.removeDicValueByIds(ids) > 0 ? ReturnObject.success() : ReturnObject.error();
    }
}

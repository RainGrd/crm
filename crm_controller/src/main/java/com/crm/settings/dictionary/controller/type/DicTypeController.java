package com.crm.settings.dictionary.controller.type;

import com.crm.common.Vo.ReturnObject;
import com.crm.settings.entity.DictionaryType;
import com.crm.settings.service.DicTypeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

/**
 * @Classname DicTypeController
 * @Description TODO
 * @Version 1.0.0
 * @Date 2023/3/7 15:32
 * @Author RainGrd
 */
@Controller
@Slf4j
public class DicTypeController {

    @Autowired
    private DicTypeService dicTypeService;


    /**
     * 跳转数据字典类型页面
     */
    @RequestMapping("/settings/dictionary/type/toDicTypeIndex.do")
    public ModelAndView toDicTypeIndex() {
        ModelAndView modelAndView = new ModelAndView();
        //查询数据
        List<DictionaryType> dictionaryTypes = dicTypeService.queryDictionaryTypeList();
        modelAndView.addObject("dictionaryTypes", dictionaryTypes);
        modelAndView.setViewName("settings/dictionary/type/index");
        return modelAndView;
    }

    /**
     * 用于跳转值type文件夹的修改和新增页面
     *
     * @return
     */
    @RequestMapping("/settings/dictionary/type/toSave.do")
    public String toSave() {
        //跳转视图
        return "settings/dictionary/type/save";
    }


    /**
     * 保存数据字典类型
     *
     * @param dictionaryType
     * @return
     */
    @RequestMapping(value = "/settings/dictionary/type/saveDicType.do", method = RequestMethod.POST)
    @ResponseBody
    public ReturnObject saveDicType(@RequestBody DictionaryType dictionaryType) {
        log.info("要保存的数据字典类型对象:{}", dictionaryType);
        int i = dicTypeService.saveDicType(dictionaryType);
        if (i > 0) {
            return ReturnObject.success();
        }
        return ReturnObject.error("编码不可重复");
    }

    /**
     * 修改数据字典类型
     *
     * @param dictionaryType
     * @return
     */
    @RequestMapping("/settings/dictionary/type/editDicType.do")
    @ResponseBody
    public ReturnObject editDicType(@RequestBody DictionaryType dictionaryType) {
        log.info("要修改的数据字典类型:{}", dictionaryType);
        return dicTypeService.editDicType(dictionaryType) > 0 ? ReturnObject.success() : ReturnObject.error();
    }

    @RequestMapping("/settings/dictionary/type/toEdit.do")
    public ModelAndView toEdit(@Param("code") String code) {
        ModelAndView modelAndView = new ModelAndView();
        //根据code查询数据字典类型对象
        DictionaryType dictionaryType = dicTypeService.queryDicTypeByCode(code);
        log.info("查询出来的数据字典类型对象:{}", dictionaryType);
        modelAndView.addObject("dictionaryType", dictionaryType);
        modelAndView.setViewName("settings/dictionary/type/edit");
        return modelAndView;
    }

    @RequestMapping("/settings/dictionary/type/deleteDicType.do")
    @ResponseBody
    public ReturnObject deleteDicType(@RequestParam("code") String code) {
        String[] codes = code.split(",");
        log.info("要删除的数据字典类型数组:{}", code);
        //根据code删除数据字典类型
        if (dicTypeService.deleteDicTypeByCode(codes) > 0) {
            return ReturnObject.success();
        }
        return ReturnObject.error();
    }

    /**
     * 查询数据字典类型列表
     *
     * @return
     */
    @RequestMapping("/settings/dictionary/type/queryDictionaryTypeList.do")
    @ResponseBody
    public ReturnObject queryDictionaryTypeList() {
        List<DictionaryType> dictionaryTypes = dicTypeService.queryDictionaryTypeList();
        return ReturnObject.success(dictionaryTypes);
    }
}


package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 学生考勤详情
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/xueshengKaoqinList")
public class XueshengKaoqinListController {
    private static final Logger logger = LoggerFactory.getLogger(XueshengKaoqinListController.class);

    private static final String TABLE_NAME = "xueshengKaoqinList";

    @Autowired
    private XueshengKaoqinListService xueshengKaoqinListService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private BanjiService banjiService;//班级
    @Autowired
    private ChengjiService chengjiService;//成绩
    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private GonggaoService gonggaoService;//公告
    @Autowired
    private LaoshiService laoshiService;//老师
    @Autowired
    private XueshengService xueshengService;//学生
    @Autowired
    private XueshengKaoqinService xueshengKaoqinService;//学生考勤
    @Autowired
    private XueshengqingjiaService xueshengqingjiaService;//学生请假
    @Autowired
    private UsersService usersService;//管理员


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("学生".equals(role))
            params.put("xueshengId",request.getSession().getAttribute("userId"));
        else if("老师".equals(role))
            params.put("laoshiId",request.getSession().getAttribute("userId"));
        CommonUtil.checkMap(params);
        PageUtils page = xueshengKaoqinListService.queryPage(params);

        //字典表数据转换
        List<XueshengKaoqinListView> list =(List<XueshengKaoqinListView>)page.getList();
        for(XueshengKaoqinListView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        XueshengKaoqinListEntity xueshengKaoqinList = xueshengKaoqinListService.selectById(id);
        if(xueshengKaoqinList !=null){
            //entity转view
            XueshengKaoqinListView view = new XueshengKaoqinListView();
            BeanUtils.copyProperties( xueshengKaoqinList , view );//把实体数据重构到view中
            //级联表 学生
            //级联表
            XueshengEntity xuesheng = xueshengService.selectById(xueshengKaoqinList.getXueshengId());
            if(xuesheng != null){
            BeanUtils.copyProperties( xuesheng , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "xueshengId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setXueshengId(xuesheng.getId());
            }
            //级联表 学生考勤
            //级联表
            XueshengKaoqinEntity xueshengKaoqin = xueshengKaoqinService.selectById(xueshengKaoqinList.getXueshengKaoqinId());
            if(xueshengKaoqin != null){
            BeanUtils.copyProperties( xueshengKaoqin , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "xueshengId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setXueshengKaoqinId(xueshengKaoqin.getId());
            }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody XueshengKaoqinListEntity xueshengKaoqinList, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,xueshengKaoqinList:{}",this.getClass().getName(),xueshengKaoqinList.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("学生".equals(role))
            xueshengKaoqinList.setXueshengId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<XueshengKaoqinListEntity> queryWrapper = new EntityWrapper<XueshengKaoqinListEntity>()
            .eq("xuesheng_id", xueshengKaoqinList.getXueshengId())
            .eq("xuesheng_kaoqin_id", xueshengKaoqinList.getXueshengKaoqinId())
            .eq("xuesheng_kaoqin_list_types", xueshengKaoqinList.getXueshengKaoqinListTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        XueshengKaoqinListEntity xueshengKaoqinListEntity = xueshengKaoqinListService.selectOne(queryWrapper);
        if(xueshengKaoqinListEntity==null){
            xueshengKaoqinList.setInsertTime(new Date());
            xueshengKaoqinList.setCreateTime(new Date());
            xueshengKaoqinListService.insert(xueshengKaoqinList);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody XueshengKaoqinListEntity xueshengKaoqinList, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,xueshengKaoqinList:{}",this.getClass().getName(),xueshengKaoqinList.toString());
        XueshengKaoqinListEntity oldXueshengKaoqinListEntity = xueshengKaoqinListService.selectById(xueshengKaoqinList.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("学生".equals(role))
//            xueshengKaoqinList.setXueshengId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        xueshengKaoqinList.setUpdateTime(new Date());

            xueshengKaoqinListService.updateById(xueshengKaoqinList);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<XueshengKaoqinListEntity> oldXueshengKaoqinListList =xueshengKaoqinListService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        xueshengKaoqinListService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer xueshengId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //.eq("time", new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
        try {
            List<XueshengKaoqinListEntity> xueshengKaoqinListList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            XueshengKaoqinListEntity xueshengKaoqinListEntity = new XueshengKaoqinListEntity();
//                            xueshengKaoqinListEntity.setXueshengId(Integer.valueOf(data.get(0)));   //学生 要改的
//                            xueshengKaoqinListEntity.setXueshengKaoqinId(Integer.valueOf(data.get(0)));   //考勤 要改的
//                            xueshengKaoqinListEntity.setXueshengKaoqinListTypes(Integer.valueOf(data.get(0)));   //打卡状态 要改的
//                            xueshengKaoqinListEntity.setInsertTime(date);//时间
//                            xueshengKaoqinListEntity.setUpdateTime(sdf.parse(data.get(0)));          //打卡时间 要改的
//                            xueshengKaoqinListEntity.setCreateTime(date);//时间
                            xueshengKaoqinListList.add(xueshengKaoqinListEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        xueshengKaoqinListService.insertBatch(xueshengKaoqinListList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }




}


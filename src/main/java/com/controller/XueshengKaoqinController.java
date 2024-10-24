
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
 * 学生考勤
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/xueshengKaoqin")
public class XueshengKaoqinController {
    private static final Logger logger = LoggerFactory.getLogger(XueshengKaoqinController.class);

    private static final String TABLE_NAME = "xueshengKaoqin";

    @Autowired
    private XueshengKaoqinService xueshengKaoqinService;


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
    private XueshengKaoqinListService xueshengKaoqinListService;//学生考勤详情
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
        PageUtils page = xueshengKaoqinService.queryPage(params);

        //字典表数据转换
        List<XueshengKaoqinView> list =(List<XueshengKaoqinView>)page.getList();
        for(XueshengKaoqinView c:list){
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
        XueshengKaoqinEntity xueshengKaoqin = xueshengKaoqinService.selectById(id);
        if(xueshengKaoqin !=null){
            //entity转view
            XueshengKaoqinView view = new XueshengKaoqinView();
            BeanUtils.copyProperties( xueshengKaoqin , view );//把实体数据重构到view中
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
    public R save(@RequestBody XueshengKaoqinEntity xueshengKaoqin, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,xueshengKaoqin:{}",this.getClass().getName(),xueshengKaoqin.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<XueshengKaoqinEntity> queryWrapper = new EntityWrapper<XueshengKaoqinEntity>()
            .eq("xuesheng_kaoqin_name", xueshengKaoqin.getXueshengKaoqinName())
            .eq("xuesheng_kaoqin_types", xueshengKaoqin.getXueshengKaoqinTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        XueshengKaoqinEntity xueshengKaoqinEntity = xueshengKaoqinService.selectOne(queryWrapper);
        if(xueshengKaoqinEntity==null){
            xueshengKaoqin.setInsertTime(new Date());
            xueshengKaoqin.setCreateTime(new Date());
            xueshengKaoqinService.insert(xueshengKaoqin);
            List<XueshengEntity> xueshengEntityList = xueshengService.selectList(new EntityWrapper<XueshengEntity>()
            );
            if(xueshengEntityList == null){
            return R.error("没有被考勤的人群,请核实后再添加");
            }
            List<XueshengKaoqinListEntity> xueshengKaoqinListList = new ArrayList<>();
            for(XueshengEntity x:xueshengEntityList){
                XueshengKaoqinListEntity xueshengKaoqinListEntity = new XueshengKaoqinListEntity();
                xueshengKaoqinListEntity.setXueshengId(x.getId());//注册表
                xueshengKaoqinListEntity.setXueshengKaoqinId(xueshengKaoqin.getId());//考勤表
                xueshengKaoqinListEntity.setXueshengKaoqinListTypes(1);
                xueshengKaoqinListEntity.setCreateTime(new Date());
                xueshengKaoqinListEntity.setInsertTime(new Date());
                xueshengKaoqinListList.add(xueshengKaoqinListEntity);

            }
            xueshengKaoqinListService.insertBatch(xueshengKaoqinListList);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody XueshengKaoqinEntity xueshengKaoqin, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,xueshengKaoqin:{}",this.getClass().getName(),xueshengKaoqin.toString());
        XueshengKaoqinEntity oldXueshengKaoqinEntity = xueshengKaoqinService.selectById(xueshengKaoqin.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        if("".equals(xueshengKaoqin.getXueshengKaoqinContent()) || "null".equals(xueshengKaoqin.getXueshengKaoqinContent())){
                xueshengKaoqin.setXueshengKaoqinContent(null);
        }

            xueshengKaoqinService.updateById(xueshengKaoqin);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<XueshengKaoqinEntity> oldXueshengKaoqinList =xueshengKaoqinService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        xueshengKaoqinService.deleteBatchIds(Arrays.asList(ids));
        xueshengKaoqinListService.delete(new EntityWrapper<XueshengKaoqinListEntity>().in("xuesheng_kaoqin_id",ids));

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
            List<XueshengKaoqinEntity> xueshengKaoqinList = new ArrayList<>();//上传的东西
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
                            XueshengKaoqinEntity xueshengKaoqinEntity = new XueshengKaoqinEntity();
//                            xueshengKaoqinEntity.setXueshengKaoqinUuidNumber(data.get(0));                    //考勤唯一编号 要改的
//                            xueshengKaoqinEntity.setXueshengKaoqinName(data.get(0));                    //考勤标题 要改的
//                            xueshengKaoqinEntity.setXueshengKaoqinTypes(Integer.valueOf(data.get(0)));   //学生考勤类型 要改的
//                            xueshengKaoqinEntity.setXueshengKaoqinContent("");//详情和图片
//                            xueshengKaoqinEntity.setInsertTime(date);//时间
//                            xueshengKaoqinEntity.setJiezhiTime(sdf.parse(data.get(0)));          //考勤截止时间 要改的
//                            xueshengKaoqinEntity.setCreateTime(date);//时间
                            xueshengKaoqinList.add(xueshengKaoqinEntity);


                            //把要查询是否重复的字段放入map中
                                //考勤唯一编号
                                if(seachFields.containsKey("xueshengKaoqinUuidNumber")){
                                    List<String> xueshengKaoqinUuidNumber = seachFields.get("xueshengKaoqinUuidNumber");
                                    xueshengKaoqinUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> xueshengKaoqinUuidNumber = new ArrayList<>();
                                    xueshengKaoqinUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("xueshengKaoqinUuidNumber",xueshengKaoqinUuidNumber);
                                }
                        }

                        //查询是否重复
                         //考勤唯一编号
                        List<XueshengKaoqinEntity> xueshengKaoqinEntities_xueshengKaoqinUuidNumber = xueshengKaoqinService.selectList(new EntityWrapper<XueshengKaoqinEntity>().in("xuesheng_kaoqin_uuid_number", seachFields.get("xueshengKaoqinUuidNumber")));
                        if(xueshengKaoqinEntities_xueshengKaoqinUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(XueshengKaoqinEntity s:xueshengKaoqinEntities_xueshengKaoqinUuidNumber){
                                repeatFields.add(s.getXueshengKaoqinUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [考勤唯一编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        xueshengKaoqinService.insertBatch(xueshengKaoqinList);
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



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
 * 成绩
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/chengji")
public class ChengjiController {
    private static final Logger logger = LoggerFactory.getLogger(ChengjiController.class);

    private static final String TABLE_NAME = "chengji";

    @Autowired
    private ChengjiService chengjiService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private BanjiService banjiService;//班级
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
        PageUtils page = chengjiService.queryPage(params);

        //字典表数据转换
        List<ChengjiView> list =(List<ChengjiView>)page.getList();
        for(ChengjiView c:list){
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
        ChengjiEntity chengji = chengjiService.selectById(id);
        if(chengji !=null){
            //entity转view
            ChengjiView view = new ChengjiView();
            BeanUtils.copyProperties( chengji , view );//把实体数据重构到view中
            //级联表 老师
            //级联表
            LaoshiEntity laoshi = laoshiService.selectById(chengji.getLaoshiId());
            if(laoshi != null){
            BeanUtils.copyProperties( laoshi , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "xueshengId"
, "laoshiId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setLaoshiId(laoshi.getId());
            }
            //级联表 学生
            //级联表
            XueshengEntity xuesheng = xueshengService.selectById(chengji.getXueshengId());
            if(xuesheng != null){
            BeanUtils.copyProperties( xuesheng , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "xueshengId"
, "laoshiId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setXueshengId(xuesheng.getId());
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
    public R save(@RequestBody ChengjiEntity chengji, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,chengji:{}",this.getClass().getName(),chengji.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("学生".equals(role))
            chengji.setXueshengId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        else if("老师".equals(role))
            chengji.setLaoshiId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        if(chengji.getChengjiFenshu()<60){
            chengji.setChengjiTypes(3);
        }else if(chengji.getChengjiFenshu()<80){
            chengji.setChengjiTypes(2);
        }else{
            chengji.setChengjiTypes(1);
        }
            chengji.setInsertTime(new Date());
            chengji.setCreateTime(new Date());
            chengjiService.insert(chengji);
            return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ChengjiEntity chengji, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,chengji:{}",this.getClass().getName(),chengji.toString());
        ChengjiEntity oldChengjiEntity = chengjiService.selectById(chengji.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("学生".equals(role))
//            chengji.setXueshengId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
//        else if("老师".equals(role))
//            chengji.setLaoshiId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        if("".equals(chengji.getChengjiFile()) || "null".equals(chengji.getChengjiFile())){
                chengji.setChengjiFile(null);
        }
        if("".equals(chengji.getChengjiContent()) || "null".equals(chengji.getChengjiContent())){
                chengji.setChengjiContent(null);
        }

        if(chengji.getChengjiFenshu()<60){
            chengji.setChengjiTypes(3);
        }else if(chengji.getChengjiFenshu()<80){
            chengji.setChengjiTypes(2);
        }else{
            chengji.setChengjiTypes(1);
        }
            chengjiService.updateById(chengji);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<ChengjiEntity> oldChengjiList =chengjiService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        chengjiService.deleteBatchIds(Arrays.asList(ids));

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
            List<ChengjiEntity> chengjiList = new ArrayList<>();//上传的东西
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
                            ChengjiEntity chengjiEntity = new ChengjiEntity();
//                            chengjiEntity.setLaoshiId(Integer.valueOf(data.get(0)));   //老师 要改的
//                            chengjiEntity.setXueshengId(Integer.valueOf(data.get(0)));   //学生 要改的
//                            chengjiEntity.setChengjiUuidNumber(data.get(0));                    //成绩编号 要改的
//                            chengjiEntity.setChengjiName(data.get(0));                    //成绩标题 要改的
//                            chengjiEntity.setKemuTypes(Integer.valueOf(data.get(0)));   //科目 要改的
//                            chengjiEntity.setChengjiFile(data.get(0));                    //附件 要改的
//                            chengjiEntity.setChengjiFenshu(data.get(0));                    //分数 要改的
//                            chengjiEntity.setChengjiTypes(Integer.valueOf(data.get(0)));   //成绩类型 要改的
//                            chengjiEntity.setChengjiContent("");//详情和图片
//                            chengjiEntity.setInsertTime(date);//时间
//                            chengjiEntity.setCreateTime(date);//时间
                            chengjiList.add(chengjiEntity);


                            //把要查询是否重复的字段放入map中
                                //成绩编号
                                if(seachFields.containsKey("chengjiUuidNumber")){
                                    List<String> chengjiUuidNumber = seachFields.get("chengjiUuidNumber");
                                    chengjiUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> chengjiUuidNumber = new ArrayList<>();
                                    chengjiUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("chengjiUuidNumber",chengjiUuidNumber);
                                }
                        }

                        //查询是否重复
                         //成绩编号
                        List<ChengjiEntity> chengjiEntities_chengjiUuidNumber = chengjiService.selectList(new EntityWrapper<ChengjiEntity>().in("chengji_uuid_number", seachFields.get("chengjiUuidNumber")));
                        if(chengjiEntities_chengjiUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(ChengjiEntity s:chengjiEntities_chengjiUuidNumber){
                                repeatFields.add(s.getChengjiUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [成绩编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        chengjiService.insertBatch(chengjiList);
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


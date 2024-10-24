
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
 * 学生请假
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/xueshengqingjia")
public class XueshengqingjiaController {
    private static final Logger logger = LoggerFactory.getLogger(XueshengqingjiaController.class);

    private static final String TABLE_NAME = "xueshengqingjia";

    @Autowired
    private XueshengqingjiaService xueshengqingjiaService;


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
    private XueshengKaoqinListService xueshengKaoqinListService;//学生考勤详情
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
        PageUtils page = xueshengqingjiaService.queryPage(params);

        //字典表数据转换
        List<XueshengqingjiaView> list =(List<XueshengqingjiaView>)page.getList();
        for(XueshengqingjiaView c:list){
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
        XueshengqingjiaEntity xueshengqingjia = xueshengqingjiaService.selectById(id);
        if(xueshengqingjia !=null){
            //entity转view
            XueshengqingjiaView view = new XueshengqingjiaView();
            BeanUtils.copyProperties( xueshengqingjia , view );//把实体数据重构到view中
            //级联表 学生
            //级联表
            XueshengEntity xuesheng = xueshengService.selectById(xueshengqingjia.getXueshengId());
            if(xuesheng != null){
            BeanUtils.copyProperties( xuesheng , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "xueshengId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
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
    public R save(@RequestBody XueshengqingjiaEntity xueshengqingjia, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,xueshengqingjia:{}",this.getClass().getName(),xueshengqingjia.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("学生".equals(role))
            xueshengqingjia.setXueshengId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<XueshengqingjiaEntity> queryWrapper = new EntityWrapper<XueshengqingjiaEntity>()
            .eq("xuesheng_id", xueshengqingjia.getXueshengId())
            .eq("xueshengqingjia_name", xueshengqingjia.getXueshengqingjiaName())
            .eq("xueshengqingjia_types", xueshengqingjia.getXueshengqingjiaTypes())
            .eq("xueshengqingjia_number", xueshengqingjia.getXueshengqingjiaNumber())
            .in("xueshengqingjia_yesno_types", new Integer[]{1,2})
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        XueshengqingjiaEntity xueshengqingjiaEntity = xueshengqingjiaService.selectOne(queryWrapper);
        if(xueshengqingjiaEntity==null){
            xueshengqingjia.setInsertTime(new Date());
            xueshengqingjia.setXueshengqingjiaYesnoTypes(1);
            xueshengqingjia.setCreateTime(new Date());
            xueshengqingjiaService.insert(xueshengqingjia);
            return R.ok();
        }else {
            if(xueshengqingjiaEntity.getXueshengqingjiaYesnoTypes()==1)
                return R.error(511,"有相同的待审核的数据");
            else if(xueshengqingjiaEntity.getXueshengqingjiaYesnoTypes()==2)
                return R.error(511,"有相同的审核通过的数据");
            else
                return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody XueshengqingjiaEntity xueshengqingjia, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,xueshengqingjia:{}",this.getClass().getName(),xueshengqingjia.toString());
        XueshengqingjiaEntity oldXueshengqingjiaEntity = xueshengqingjiaService.selectById(xueshengqingjia.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("学生".equals(role))
//            xueshengqingjia.setXueshengId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        if("".equals(xueshengqingjia.getXueshengqingjiaText()) || "null".equals(xueshengqingjia.getXueshengqingjiaText())){
                xueshengqingjia.setXueshengqingjiaText(null);
        }
        if("".equals(xueshengqingjia.getXueshengqingjiaYesnoText()) || "null".equals(xueshengqingjia.getXueshengqingjiaYesnoText())){
                xueshengqingjia.setXueshengqingjiaYesnoText(null);
        }

            xueshengqingjiaService.updateById(xueshengqingjia);//根据id更新
            return R.ok();
    }


    /**
    * 审核
    */
    @RequestMapping("/shenhe")
    public R shenhe(@RequestBody XueshengqingjiaEntity xueshengqingjiaEntity, HttpServletRequest request){
        logger.debug("shenhe方法:,,Controller:{},,xueshengqingjiaEntity:{}",this.getClass().getName(),xueshengqingjiaEntity.toString());

        XueshengqingjiaEntity oldXueshengqingjia = xueshengqingjiaService.selectById(xueshengqingjiaEntity.getId());//查询原先数据

//        if(xueshengqingjiaEntity.getXueshengqingjiaYesnoTypes() == 2){//通过
//            xueshengqingjiaEntity.setXueshengqingjiaTypes();
//        }else if(xueshengqingjiaEntity.getXueshengqingjiaYesnoTypes() == 3){//拒绝
//            xueshengqingjiaEntity.setXueshengqingjiaTypes();
//        }
        xueshengqingjiaEntity.setXueshengqingjiaShenheTime(new Date());//审核时间
        xueshengqingjiaService.updateById(xueshengqingjiaEntity);//审核

        return R.ok();
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<XueshengqingjiaEntity> oldXueshengqingjiaList =xueshengqingjiaService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        xueshengqingjiaService.deleteBatchIds(Arrays.asList(ids));

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
            List<XueshengqingjiaEntity> xueshengqingjiaList = new ArrayList<>();//上传的东西
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
                            XueshengqingjiaEntity xueshengqingjiaEntity = new XueshengqingjiaEntity();
//                            xueshengqingjiaEntity.setXueshengId(Integer.valueOf(data.get(0)));   //学生 要改的
//                            xueshengqingjiaEntity.setXueshengqingjiaName(data.get(0));                    //请假标题 要改的
//                            xueshengqingjiaEntity.setXueshengqingjiaText(data.get(0));                    //请假缘由 要改的
//                            xueshengqingjiaEntity.setXueshengqingjiaTypes(Integer.valueOf(data.get(0)));   //请假类型 要改的
//                            xueshengqingjiaEntity.setInsertTime(date);//时间
//                            xueshengqingjiaEntity.setXueshengqingjiaTime(sdf.parse(data.get(0)));          //请假时间 要改的
//                            xueshengqingjiaEntity.setXueshengqingjiaNumber(Integer.valueOf(data.get(0)));   //请假天数 要改的
//                            xueshengqingjiaEntity.setXueshengqingjiaYesnoTypes(Integer.valueOf(data.get(0)));   //申请状态 要改的
//                            xueshengqingjiaEntity.setXueshengqingjiaYesnoText(data.get(0));                    //处理意见 要改的
//                            xueshengqingjiaEntity.setXueshengqingjiaShenheTime(sdf.parse(data.get(0)));          //审核时间 要改的
//                            xueshengqingjiaEntity.setCreateTime(date);//时间
                            xueshengqingjiaList.add(xueshengqingjiaEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        xueshengqingjiaService.insertBatch(xueshengqingjiaList);
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


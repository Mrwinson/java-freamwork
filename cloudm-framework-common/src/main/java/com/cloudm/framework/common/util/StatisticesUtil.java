package com.cloudm.framework.common.util;

import com.cloudm.framework.common.sms.ExectTimeBO;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @description:
 * @author: Courser
 * @date: 2017/6/6
 * @version: V1.0
 */
@Slf4j
public class StatisticesUtil {

    private static String VIEWSTRING = "[";
    private static String APPLYSTRING = "Time==>";
    private static String SEARCHSTRING = "INFO";
    private static String USERTAG = "class";

    /**
     * 调用方法名，调用时间，方法名以key-value方式存储
     *
     */
    public static List<ExectTimeBO> readFileByLines(String fileName,Integer system_id) {
        Calendar now = Calendar.getInstance();
        String a =String.valueOf(now.get(Calendar.YEAR));
        List<ExectTimeBO> boList=new ArrayList<ExectTimeBO>();
        //首先获取本机使用系统
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
            reader = new BufferedReader(isr);
            String tempString = null;
            Map<String, List<ExectTimeBO>> map = new HashMap<>();
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                if (tempString.contains(SEARCHSTRING)) {
                    ExectTimeBO exectTimeBO = new ExectTimeBO();
                    //获取方法名开始位置
                    int classindex = tempString.indexOf(USERTAG);
                    //字符串分割获取方法名
                    String classname = tempString.substring(classindex, tempString.indexOf(",", classindex));
                    //获取开始时间位置
                    int start_timeindex = tempString.indexOf(VIEWSTRING);
                    //分割获取时间
                    String start_time = a+tempString.substring(start_timeindex + 1, tempString.indexOf(",", start_timeindex));
                    try
                    {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
                        Date starttime = sdf.parse(start_time);
                        exectTimeBO.setStarttime(starttime);
                    }
                    catch (ParseException e)
                    {
                        log.error(e.getClass()+e.getMessage()+"出错了");
                    }
                    //获取调用时间
                    int timeindex = tempString.indexOf(APPLYSTRING);
                    String time = tempString.substring(timeindex + 7, tempString.length() - 2);
                    // todo set值
                    exectTimeBO.setTime(time);
                    exectTimeBO.setClassname(classname);
                    exectTimeBO.setSystem_id(system_id);
                    if (classname != null && classname != "") {
                        try {
                            if (map.containsKey(classname)) {
                                List<ExectTimeBO> timeBOList = map.get(classname);
                                timeBOList.add(exectTimeBO);
                                map.put(classname, timeBOList);
                            } else {
                                List<ExectTimeBO> exectTimeBOList = new ArrayList();
                                exectTimeBOList.add(exectTimeBO);
                                map.put(classname, exectTimeBOList);
                            }
                        } catch (Exception e) {
                            log.error(e.getClass()+e.getMessage()+"出错了{}");
                            e.printStackTrace();
                        }
                    }
                }
            }
            reader.close();
            boList=forCount(map);
        } catch (IOException e) {
            log.error(e.getClass()+"IO流错误");
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return boList;
    }

    //封装对象
    public static void setStatistic(Date starttime,String classname,int mintime,int maxtime,int avgtime,int calls,int system_id){
        ExectTimeBO exectTimeBO = new ExectTimeBO();
        exectTimeBO.setStarttime(starttime);
        exectTimeBO.setClassname(classname);
        exectTimeBO.setMintime(mintime);
        exectTimeBO.setMaxtime(maxtime);
        exectTimeBO.setAvgtime(avgtime);
        exectTimeBO.setCalls(calls);
        exectTimeBO.setSystem_id(system_id);
    }

    //获取平均值，最大最小时间和次数
    public static List<ExectTimeBO> forCount(Map<String, List<ExectTimeBO>> logMap) {
        ExectTimeBO exectTimeBO = new ExectTimeBO();
        List<ExectTimeBO> boList=new ArrayList<ExectTimeBO>();
        Date starttime = null;
        String classname = null;
        int calls = 0;
        int maxtime = 0;
        int mintime = 0;
        int avgtime = 0;
        int system_id=0;

        Iterator<Map.Entry<String, List<ExectTimeBO>>> it = logMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, List<ExectTimeBO>> entry = it.next();
            //System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
            //当只调用一次的方法得出平均，最大，最小时间和调用次数
            List infoList = entry.getValue();
            //判断次数并给出所耗时间
            if (infoList.size() == 1) {
                Iterator iterator = infoList.iterator();
                while (iterator.hasNext()) {
                    ExectTimeBO dd = (ExectTimeBO) iterator.next();
                    maxtime = Integer.parseInt(dd.getTime());
                    starttime = dd.getStarttime();
                    classname = dd.getClassname();
                    system_id=dd.getSystem_id();
                }
                calls = 1;
                mintime = maxtime;
                avgtime = maxtime;
                //System.out.println(starttime + "=============" + maxtime + "-----------" + classname + "---------");
                //将数据放入对象中
                setStatistic(starttime,classname,mintime,maxtime,avgtime,calls,system_id);
            } else {
                String[] times = new String[infoList.size()];
                int i = 0;
                Iterator iterator = infoList.iterator();
                while (iterator.hasNext()) {
                    ExectTimeBO dd = (ExectTimeBO) iterator.next();
                    starttime = dd.getStarttime();
                    classname = dd.getClassname();
                    String str = dd.getTime();
                    system_id=dd.getSystem_id();
                    times[i] = str;
                    i++;
                }
                int[] iTime = new int[times.length];
                for (int j = 0; j < times.length; j++) {
                    iTime[j] = Integer.parseInt(times[j]);
                }
                calls = iTime.length;
                maxtime = mintime = iTime[0];
                int count = 0;
                for (int j = 0; j < iTime.length; j++) {
                    if (iTime[j] > maxtime) {
                        maxtime = iTime[j];
                    }
                    if (iTime[j] < mintime) {
                        mintime = iTime[j];
                    }
                    count = count + iTime[j];
                }
                avgtime = count / calls;
                //将数据放入对象中
                setStatistic(starttime,classname,mintime,maxtime,avgtime,calls,system_id);
            }
            boList.add(exectTimeBO);
        }
        return boList;
    }
}

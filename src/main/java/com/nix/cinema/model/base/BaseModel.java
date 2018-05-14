package com.nix.cinema.model.base;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

/**
 *
 * @author 11723
 * @date 2017/5/4
 */
public class BaseModel<M extends BaseModel<M>> {

    protected Integer id;

    protected Date createDate;

    protected Date updateDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    private Object givePrivateValue(Object o, Field field) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class clazz = o.getClass();
        Method method = clazz.getMethod((field.getType().getSimpleName().matches(".*oolean.*")?"is":"get") + chanIndexZero(field.getName()));
        return method.invoke(o);
    }

    private Object settingPrivateValue(Object o,Field field,Object value) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class clazz = o.getClass();
        Method method = clazz.getMethod("set" + chanIndexZero(field.getName()),field.getType());
        return method.invoke(o,value);
    }
    private String chanIndexZero(String str) {
        char[] c = str.toCharArray();
        c[0] -= 32;
        return String.valueOf(c);
    }

    /**
     * 自动将model对象转为dto对象 <br />
     * 在懒加载中  如果返回model给前端懒加载会不起作用 只有将model转为dto才行
     * */
    public Object toDto(){
        Class clazz;
        Object dto;
        try {
            String clazzName = this.getClass().getSimpleName().replaceFirst("Model","Dto");
            clazz = Class.forName("com.nix.dto." + clazzName.substring(0,clazzName.indexOf("_")));
            dto = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        Field[] fields = clazz.getDeclaredFields();
        for (Field field:fields){
            try {
                settingPrivateValue(dto,field,givePrivateValue(this,field));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dto;
    }

    public String generateSn() {
        return String.valueOf(System.currentTimeMillis());
    }
}

package com.lsh.demo.global;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * 全局Jackson转换器
 * @author lish [254174981@qq.com]
 * @date 18-1-16
 */
public class GlobalBeanToJacksonMapper extends ObjectMapper {

    public GlobalBeanToJacksonMapper() {
        super();
        // JSON节点不包含属性值为NULL
        this.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 时间转换
        this.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        // 基本数据类型转换String
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(int.class, ToStringSerializer.instance);
        simpleModule.addSerializer(byte.class, ToStringSerializer.instance);
        simpleModule.addSerializer(short.class, ToStringSerializer.instance);
        simpleModule.addSerializer(long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(float.class, ToStringSerializer.instance);
        simpleModule.addSerializer(double.class, ToStringSerializer.instance);

        simpleModule.addSerializer(Byte.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Integer.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Short.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Float.class, DecimalToStringSerializer.instance);
        simpleModule.addSerializer(Double.class, DecimalToStringSerializer.instance);

        simpleModule.addSerializer(BigDecimal.class, ToStringSerializer.instance);
        simpleModule.addSerializer(BigInteger.class, ToStringSerializer.instance);
        this.registerModule(simpleModule);

    }

    /**
     * 小数 转 string 不使用科学计数法
     */
    public static class DecimalToStringSerializer extends ToStringSerializer{
        /**
         * Singleton instance to use.
         */
        public final static DecimalToStringSerializer instance = new DecimalToStringSerializer();

        @Override
        public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {

            // 将double，float类型转为非科学技术的类型
            // 精度为 4
            if(value instanceof Double ||value instanceof Float){

                DecimalFormat decimalFormat = new DecimalFormat("#.####");
                value = decimalFormat.format(value);
            }

            gen.writeString(value.toString());
        }
    }

}

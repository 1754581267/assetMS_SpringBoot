package bao.xy.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @Description: 序列化工具类
 * @CreateTime: 2020-10-17-21-48
 */
public class SerializeUtils {

    /**
     * 打开autotype功能
     */
    static {
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
    }

    /**
     * 将Object序列化成byte[]
     *
     * @param object Object数据
     * @return byte[]数据
     */
    public static byte[] serializeToBytes(Object object) {
        return JSONObject.toJSONBytes(object, SerializerFeature.WriteClassName);
    }

    /**
     * 将byte[]反序列化成Object
     *
     * @param bytes byte[]数据
     * @return Object数据
     */
    public static Object deserialize(byte[] bytes) {
        return JSONObject.parse(bytes, Feature.IgnoreNotMatch);
    }

    /**
     * 将Object序列化成String
     *
     * @param object Object数据
     * @return String数据
     */
    public static String serializeToString(Object object) {
        return JSONObject.toJSONString(object, SerializerFeature.WriteClassName);
    }

    /**
     * 将String反序列化成Object
     *
     * @param data String数据
     * @return Object数据
     */
    public static Object deserialize(String data) {
        return JSONObject.parse(data, Feature.IgnoreNotMatch);
    }

}

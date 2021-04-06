package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * Description:统一接口处理
 * User: starry
 * Date: 2021 -04 -06
 * Time: 16:05
 */
public class ResultJSONUtils {

    /**
     * 给前端输出json数据
     * @param response
     * @param data
     * @throws IOException
     */
    public static void write(HttpServletResponse response,
                             HashMap<String,Object> data) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        writer.println(mapper.writeValueAsString(data));
    }

}

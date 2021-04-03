import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: starry
 * Date: 2021 -04 -01
 * Time: 20:42
 */

@MultipartConfig
public class MyUploadServlet extends HttpServlet {
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // 编码和数据类型
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html");

            //全球唯一ID
            UUID uuid = UUID.randomUUID();

            //保存图片
            Part part =request.getPart("myfile");
            //保存图片
            part.write(String.format("D:\\%s%s",uuid,part.getSubmittedFileName()));

            PrintWriter writer = response.getWriter();
            writer.println("<h1>图片保存成功</h1>");
        }

}

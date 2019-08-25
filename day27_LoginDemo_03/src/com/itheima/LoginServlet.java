package com.itheima;

import com.domian.User;
import com.utils.JDBCUtil;
import com.utils.JDBCUtil;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/demo")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response );
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //1.获取客户端携带过来的请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //2.校验用户名和密码
        //3.通过resp对象响应客户端
        PrintWriter writer = response.getWriter();
        //使用JDBC到数据库校验账号和密码
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtil.getDataSource());
        String sql="select * from user where username=? and password =?";
        User users=null;
        try {
         users = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class),username,password);
        } catch (DataAccessException e) {
            //e.printStackTrace();
        }
        //判断是否登录成功
        if (users!=null){
            writer.write("登入成功");
        }else {
            writer.write("登入失败");
        }
    }
}

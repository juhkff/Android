package controller;

import tools.Conn;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Controller
@RequestMapping(value = "/index")
public class IndexController {
    @ResponseBody
    @RequestMapping(value = "/Register", method = RequestMethod.GET)
    public String requestReg(@RequestParam("userName") String userName, @RequestParam("passWord") String passWord) {
        Connection connection = Conn.getConnection();
        String sql = "INSERT INTO users(userName, passWord) VALUES (?,?)";
        int resultInt = -1;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, passWord);
            resultInt = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conn.Close();
        }
        if (resultInt > 0)
            return "Success";
        else
            return "Failed";
    }

    @ResponseBody
    @RequestMapping(value = "/Login",method = RequestMethod.POST)
    public String requestLogin(@RequestParam("userName") String userName, @RequestParam("passWord") String passWord) {
        Connection connection = Conn.getConnection();
        String sql = "SELECT passWord FROM users WHERE userName=?";
        String qPassWord = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                qPassWord = resultSet.getString(1);
            } else {
                return "none";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conn.Close();
        }
        if (qPassWord==null)
            return "none";
        else {
            if (!qPassWord.equals(passWord))
                return "Failed";
            else
                return "Success";
        }
    }
}

package controller;

import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tools.Conn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Controller
@RequestMapping(value = "/home")
public class homeController {
    @ResponseBody
    @RequestMapping(value = "/Connect",method = RequestMethod.POST)
    public String connect(@RequestParam("userName")String userName){
        Connection connection = Conn.getConnection();
        String sql = "UPDATE users SET online=1 WHERE userName=?";
        int resultInt = -1;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,userName);
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
    @RequestMapping(value = "/GetList",method = RequestMethod.POST)
    public String getList(@RequestParam("userName")String userName){
        Connection connection=Conn.getConnection();
        String sql="SELECT userName FROM users WHERE online=1 AND userName!=?";
        ArrayList<String> list=new ArrayList<String>();
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,userName);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                list.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Gson().toJson(list);
    }

    @ResponseBody
    @RequestMapping(value = "/Exit",method = RequestMethod.POST)
    public String exit(@RequestParam("userName")String userName){
        Connection connection = Conn.getConnection();
        String sql = "UPDATE users SET online=0 WHERE userName=?";
        int resultInt = -1;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,userName);
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
}

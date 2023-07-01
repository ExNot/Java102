package xCourse.View;

import xCourse.Helper.Config;
import xCourse.Helper.Helper;

import javax.swing.*;

public class StudentGui extends JFrame{
    private JPanel wrapper;

    public StudentGui(){
        add(wrapper);
        setSize(400,400);
        setLocation(Helper.screenCenterLoc("x", getSize()), Helper.screenCenterLoc("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);
    }
}

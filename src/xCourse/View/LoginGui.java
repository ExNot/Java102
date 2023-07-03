package xCourse.View;

import xCourse.Helper.Config;
import xCourse.Helper.Helper;
import xCourse.Model.Operator;
import xCourse.Model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class LoginGui  extends JFrame{
    private JPanel wrapper;
    private JPanel wtop;
    private JPanel wbottom;
    private JTextField fld_user_name;
    private JTextField fld_user_pass;
    private JButton btn_login;
    public int logged_in_user_id;

    public LoginGui(){
        add(wrapper);
        setSize(400,400);
        setLocation(Helper.screenCenterLoc("x", getSize()), Helper.screenCenterLoc("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);


        btn_login.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_user_name) || Helper.isFieldEmpty(fld_user_pass)){
                Helper.showMsg("fill");
            }else {


                User u = User.getFetch(fld_user_name.getText(), fld_user_pass.getText());

                logged_in_user_id = u.getId();

                if (u == null){
                    Helper.showMsg("Incorrect username or password!");
                }
                else {
                    switch (u.getType()){
                        case "operator":
                            OperatorGUI opGui = new OperatorGUI((Operator) u);
                            break;
                        case"educator":
                            EducatorGui eduGui = new EducatorGui(u);
                            break;
                        case"student":
                            StudentGui stGui = new StudentGui(u);
                            break;
                    }
                    dispose();
                }

            }
        });
    }

    public static void main(String[] args) {
        Helper.setLayout();
        LoginGui loginGui = new LoginGui();
    }
}

package xCourse.Helper;

import xCourse.Model.User;

import javax.swing.*;
import java.awt.*;

public class Helper {

    public static int screenCenterLoc(String axis, Dimension dimension){

        int point;
        switch (axis){
            case "x": point = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - dimension.width)/2;
            break;
            case "y": point = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - dimension.height)/2;
            break;
            default:
                point = 0;
        }
        return point;

    }

    public static void setLayout(){
        for (UIManager.LookAndFeelInfo info: UIManager.getInstalledLookAndFeels()){
            if (info.getName().equals("Nimbus")){
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                         UnsupportedLookAndFeelException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
        }
    }
    public static boolean isFieldEmpty(JTextField jTextField){

        return jTextField.getText().trim().isEmpty();

    }

    public static void showMsg(String msg){
        optionPaneLang();
        String message;
        String title;

        switch (msg){
            case "fill":
                message = "Please fill all blanks!";
                title = "ERROR";
                break;
            case "done":
                message = "Process Successful";
                title = "Result";
                break;

            case "error":
                message = "There is an error!";
                title = "ERROR";
                break;

                default:
                 message = msg;
                 title = "Message";
        }
        JOptionPane.showMessageDialog(null,message,title,JOptionPane.INFORMATION_MESSAGE);

    }

    public static boolean confirm(){

        if (JOptionPane.showConfirmDialog(null,"Are You Sure to delete this?","DELETE", JOptionPane.YES_NO_OPTION)== 0)
            return true;
        else
            return false;

    }


    public static void optionPaneLang(){

        UIManager.put("OptionPane.okButtonText", "OKEY!");

    }



}

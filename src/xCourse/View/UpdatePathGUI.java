package xCourse.View;

import com.mysql.cj.xdevapi.DeleteStatement;
import xCourse.Helper.Config;
import xCourse.Helper.Helper;
import xCourse.Model.Paths;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdatePathGUI extends JFrame {
    private JPanel wrapper;
    private JTextField fld_path_update;
    private JButton btn_path_update;
    private Paths paths;

    public UpdatePathGUI(Paths paths) {

        this.paths = paths;
        add(wrapper);
        setSize(300, 150);
        setLocation(Helper.screenCenterLoc("x", getSize()), Helper.screenCenterLoc("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        fld_path_update.setText(paths.getName());

        btn_path_update.addActionListener(e -> {
            if (fld_path_update.getText().equals("")) {
                Helper.showMsg("fill");
            } else {
                if (Paths.update(paths.getId(), fld_path_update.getText()))
                    Helper.showMsg("done");
                else
                    Helper.showMsg("error");
                dispose();
            }
        });
    }

}

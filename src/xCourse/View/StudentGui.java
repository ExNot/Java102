package xCourse.View;

import NestedClass.Out;
import xCourse.Helper.Config;
import xCourse.Helper.Helper;
import xCourse.Model.Course;
import xCourse.Model.Paths;
import xCourse.Model.Student_Infos;
import xCourse.Model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentGui extends JFrame{
    private JPanel wrapper;
    private JTabbedPane tab_student;
    private JScrollPane scrl_path_list;
    private JTable tbl_path_list;
    private JPanel tbl_chosed_paths_courses;
    private JScrollPane scrl_courses;
    private JTable tbl_courses;
    private JPanel pnl_course_reg;
    private JButton btn_register_course;
    private JLabel lbl_course_name;

    private DefaultTableModel mdl_path_list;
    private Object[] row_path_list;

    private DefaultTableModel mdl_course_list;
    private Object[] row_course_list;

    private final User student;

    public StudentGui(User student){
        this.student = student;

        add(wrapper);
        setSize(500,600);
        setLocation(Helper.screenCenterLoc("x", getSize()), Helper.screenCenterLoc("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        mdl_path_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        Object[] col_path_list = {"ID", "Path Name"};
        mdl_path_list.setColumnIdentifiers(col_path_list);

        row_path_list = new Object[col_path_list.length];

        tbl_path_list.setModel(mdl_path_list);

        loadPathModel();

        tbl_path_list.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(78);



        mdl_course_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        Object[] col_course_list = {"ID", "Course Name", "Programming Language", "Path", "Educator"};
        mdl_course_list.setColumnIdentifiers(col_course_list);
        row_course_list = new Object[col_course_list.length];

        tbl_courses.getTableHeader().setReorderingAllowed(false);

        tbl_courses.setModel(mdl_course_list);
        tbl_courses.getColumnModel().getColumn(0).setMaxWidth(78);
        tbl_courses.getTableHeader().setReorderingAllowed(false);






        tbl_path_list.getSelectionModel().addListSelectionListener(e ->{
            try {
                String a = tbl_path_list.getValueAt(tbl_path_list.getSelectedRow(),0).toString();
                int selected_path_id = Integer.parseInt(a);
                loadCourseModel(selected_path_id);




            }catch (Exception exception){
                exception.printStackTrace();
            }
        });


        btn_register_course.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (tbl_courses.getSelectionModel().getSelectedItemsCount() != 0){
                    String selected_course_id = (tbl_courses.getValueAt(tbl_courses.getSelectedRow(),0).toString());
                    lbl_course_name.setText(Course.getFetch(Integer.parseInt(selected_course_id)).getName().toString());
                    Student_Infos.setInfosCourse(selected_course_id, StudentGui.this.student);

                }
                else
                    Helper.showMsg("Please choose a course");


            }
        });
    }

    private void loadCourseModel(int path_id) {

        int rowCount = tbl_courses.getRowCount();
        for (int i = rowCount-1; i>=0;i--){

            tbl_courses.removeRowSelectionInterval(i,i);

        }
        int i;

        DefaultTableModel clearModel = (DefaultTableModel) tbl_courses.getModel();

        clearModel.getDataVector().removeAllElements();

        clearModel.setRowCount(0);
        for (Course obj : Course.getList()) {
            if (obj.getPaths().getId() == path_id){
                i =0;

                row_course_list[i++] = obj.getId();
                row_course_list[i++] = obj.getName();
                row_course_list[i++] = obj.getLang();
                row_course_list[i++] = obj.getPaths().getName();
                row_course_list[i] = obj.getEducator().getName();
                mdl_course_list.addRow(row_course_list);
            }

        }

    }


    private void loadPathModel() {

        DefaultTableModel clearModel = (DefaultTableModel) tbl_path_list.getModel();
        clearModel.setRowCount(0);

        for (Paths path : Paths.getList()){

            int i = 0;
            row_path_list[i++] = path.getId();
            row_path_list[i] = path.getName();
            mdl_path_list.addRow(row_path_list);

        }

    }

}

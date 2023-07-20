package xCourse.View;

import xCourse.Helper.Config;
import xCourse.Helper.Helper;
import xCourse.Model.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.ArrayList;


public class StudentGui extends JFrame {
    private JPanel wrapper;
    private JTabbedPane tab_lessons;
    private JScrollPane scrl_path_list;
    private JTable tbl_path_list;
    private JPanel tbl_chosed_paths_courses;
    private JScrollPane scrl_courses;
    private JTable tbl_courses;
    private JPanel pnl_course_reg;
    private JButton btn_register_course;
    private JLabel lbl_course_name;
    private JPanel pnl_not_reg;
    private JPanel pnl_reg_les;
    private JScrollPane scrl_nor_reg;
    private JScrollPane scr_reg_les;
    private JTable tbl_not_reg;
    private JTable tbl_reg_les;
    private JPanel pnl_regless_1;
    private JPanel pnl_regless_2;
    private JButton btn_reg_less;
    private JLabel lbl_choosed_less;

    private DefaultTableModel mdl_path_list;
    private Object[] row_path_list;

    private DefaultTableModel mdl_course_list;
    private Object[] row_course_list;


    private DefaultTableModel mdl_notreg_less;
    private Object[] row_notreg_less;

    private DefaultTableModel mdl_reg_less;
    private Object[] row_reg_less;




    private final User student;

    public StudentGui(User student) {
        this.student = student;

        add(wrapper);
        setSize(500, 600);
        setLocation(Helper.screenCenterLoc("x", getSize()), Helper.screenCenterLoc("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);





        tab_lessons.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int selected_index = tab_lessons.getSelectedIndex();

                switch (selected_index){
                    case 0:
                        setSize(500, 600);
                        break;
                    case 1:
                        setSize(800,1000);
                        break;
                    default:
                        setSize(500,600);

                }
            }
        });






        //@@@@@@@@@@@@@@@@      MODEL FOR PATH      @@@@@@@@@@@@@@@@@@
        mdl_path_list = new DefaultTableModel() {
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


        //@@@@@@@@@@@@@@@@      MODEL FOR COURSE      @@@@@@@@@@@@@@@@@@
        mdl_course_list = new DefaultTableModel() {
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


        tbl_path_list.getSelectionModel().addListSelectionListener(e -> {
            try {
                String a = tbl_path_list.getValueAt(tbl_path_list.getSelectedRow(), 0).toString();
                int selected_path_id = Integer.parseInt(a);
                loadCourseModel(selected_path_id);


            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });


        btn_register_course.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (tbl_courses.getSelectionModel().getSelectedItemsCount() != 0) {
                    String selected_course_id = (tbl_courses.getValueAt(tbl_courses.getSelectedRow(), 0).toString());
                    lbl_course_name.setText(Course.getFetch(Integer.parseInt(selected_course_id)).getName().toString());
                    if (Student_Infos.setInfosCourse(selected_course_id, StudentGui.this.student))
                        Helper.showMsg("done");
                        loadNotRegTbl();
                } else
                    Helper.showMsg("Please choose a course");


            }
        });




        //@@@@@@@@@@@@@@@@      MODEL FOR NOT REGISTERED LESSON      @@@@@@@@@@@@@@@@@@
        mdl_notreg_less = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        Object[] col_notreg_lesson = {"ID", "Course", "Educator", "Title", "Description"};
        mdl_notreg_less.setColumnIdentifiers(col_notreg_lesson);

        row_notreg_less = new Object[col_notreg_lesson.length];

        tbl_not_reg.setModel(mdl_notreg_less);
        tbl_not_reg.getTableHeader().setReorderingAllowed(false);

        loadNotRegTbl();


        btn_reg_less.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tbl_not_reg.getSelectionModel().getSelectedItemsCount() != 0){
                    String str_id = tbl_not_reg.getValueAt(tbl_not_reg.getSelectedRow(),0).toString();
                    int selected_lesson_id = Integer.parseInt(str_id);
                    if (Student_Infos.setInfosLesson(student.getId(), selected_lesson_id)){
                        Helper.showMsg("done");
                        loadNotRegTbl();
                        loadRegTbl();
                    }
                    else
                        Helper.showMsg("error");
                }
                else Helper.showMsg("Please select a lesson");





            }
        });
        //@@@@@@@@@@@@@@@@      MODEL FOR REGISTERED LESSON      @@@@@@@@@@@@@@@@@@

        mdl_reg_less = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        Object[] col_reg_lesson = {"ID", "Course", "Educator", "Title", "Description"};
        mdl_reg_less.setColumnIdentifiers(col_reg_lesson);

        row_reg_less = new Object[col_reg_lesson.length];
        tbl_reg_les.setModel(mdl_reg_less);
        tbl_reg_les.getTableHeader().setReorderingAllowed(false);
        loadRegTbl();



    }












    private void loadRegTbl() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_reg_les.getModel();
        clearModel.setRowCount(0);
        String[] regLessons = Student_Infos.regLessons(this.student).split(",");

        for (Integer i : Lesson.lessonIdList()) {
            for (String s : regLessons) {
                Integer a;
                if (!s.equals("")) {
                    a = Integer.parseInt(s);
                } else {
                    a = -1;
                }

                if (Objects.equals(i, a)) {
                    if (Arrays.asList(regLessons).contains(i.toString()));
                    Lesson less = Lesson.getFetch(i);
                    int j = 0;
                    row_reg_less[j++] = less.getId();
                    row_reg_less[j++] = less.getCourse().getName();
                    row_reg_less[j++] = less.getEducator().getName();
                    row_reg_less[j++] = less.getTitle().toString();
                    row_reg_less[j] = less.getDescription().toString();
                    mdl_reg_less.addRow(row_reg_less);

                }

            }

        }

    }







    private void loadNotRegTbl() {

        int rowCount = tbl_not_reg.getRowCount();
        for (int j = rowCount - 1; j >= 0; j--) {

            tbl_not_reg.removeRowSelectionInterval(j, j);

        }


        DefaultTableModel clearModel = (DefaultTableModel) tbl_not_reg.getModel();

        clearModel.getDataVector().removeAllElements();

        clearModel.setRowCount(0);




        for (Integer i : Lesson.lessonIdList()) {

            //The reason for repeating the query in each loop iteration is to retrieve the registered ID when adding a row.!!!!

            String[] regLessons = Student_Infos.regLessons(this.student).split(",");
            String[] regCourses = Student_Infos.regCourses(this.student).split(",");

            for (String s : regLessons) {
                regLessons = Student_Infos.regLessons(this.student).split(",");

                String c = Integer.toString(Lesson.getFetch(i).getCourse().getId());

                ArrayList<Integer> lessIDs = new ArrayList<>();
                for (String o : regLessons){
                    if (!o.equals(""))
                        lessIDs.add(Integer.parseInt(o));
                }

                int size = tbl_not_reg.getRowCount();
                for (int l = 0; l<size; l++){

                    lessIDs.add(Integer.parseInt(tbl_not_reg.getValueAt(l, 0).toString()));

                }

                if (Arrays.asList(regCourses).contains(c) && !lessIDs.contains(i)) {

                    Lesson less = Lesson.getFetch(i);
                    lessIDs.add(less.getId());

                    int j = 0;
                    row_notreg_less[j++] = less.getId();
                    row_notreg_less[j++] = less.getCourse().getName().toString();
                    row_notreg_less[j++] = less.getEducator().getName().toString();
                    row_notreg_less[j++] = less.getTitle().toString();
                    row_notreg_less[j] = less.getDescription().toString();
                    mdl_notreg_less.addRow(row_notreg_less);

                }

            }

        }
    }

    private void loadCourseModel(int path_id) {

        int rowCount = tbl_courses.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {

            tbl_courses.removeRowSelectionInterval(i, i);

        }
        int i;

        DefaultTableModel clearModel = (DefaultTableModel) tbl_courses.getModel();

        clearModel.getDataVector().removeAllElements();

        clearModel.setRowCount(0);
        for (Course obj : Course.getList()) {
            if (obj.getPaths().getId() == path_id) {
                i = 0;

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



        for (Paths path : Paths.getList()) {



            int i = 0;
            row_path_list[i++] = path.getId();
            row_path_list[i] = path.getName();
            mdl_path_list.addRow(row_path_list);

        }

    }

}

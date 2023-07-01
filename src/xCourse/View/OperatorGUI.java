package xCourse.View;

import xCourse.Helper.Config;
import xCourse.Helper.Helper;
import xCourse.Helper.Item;
import xCourse.Model.Course;
import xCourse.Model.Operator;
import xCourse.Model.Paths;
import xCourse.Model.User;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class OperatorGUI extends JFrame {
    private JPanel wraper;
    private JTabbedPane tab_operator;
    private JLabel lbl_welcome;
    private JPanel pnl_top;
    private JButton btn_Exit;
    private JPanel pnl_user_list;
    private JScrollPane scrl_user_list;
    private JTable tbl_user_list;
    private JTextField fld_name;
    private JTextField fld_user_name;
    private JComboBox cmb_account_type;
    private JPasswordField fld_password;
    private JButton add_to_DB;
    private JTextField fld_delete_byId;
    private JButton btn_delete_byID;
    private JTextField fld_search_name;
    private JTextField fld_sch_username;
    private JPanel fld_search_username;
    private JComboBox cmb_search_accountType;
    private JButton btn_user_search;
    private JPanel fld_paths;
    private JScrollPane scrl_paths_pane;
    private JTable tbl_paths_list;
    private JPanel pnl_path_add;
    private JTextField fld_path_add;
    private JButton btn_path_add;
    private JPanel pnl_course_list;
    private JScrollPane scrl_course_list;
    private JTable tbl_course_list;
    private JPanel pnl_course_add;
    private JTextField fld_course_name;
    private JTextField fld_course_lang;
    private JComboBox cmb_course_path;
    private JComboBox cmb_course_educator;
    private JButton btn_course_add;
    private JPanel pnl_course_update;
    private JTextField fld_update_coursename;
    private JTextField fld_update_prog_lang;
    private JComboBox cmb_path_update;
    private JComboBox cmb_edu_update;
    private JButton btn_update_course;
    private JLabel lbl_id_update;
    private JButton btn_dlt_course;
    private JButton clear_selection;
    private DefaultTableModel mdl_user_list;
    private Object[] row_user_list;
    private DefaultTableModel mdl_paths_list;
    private Object[] row_paths_list;
    private JPopupMenu path_menu;
    private DefaultTableModel mdl_course_list;
    private Object[] row_course_list;

    private final Operator operator;

    public OperatorGUI(Operator operator) {
        this.operator = operator;


        add(wraper);
        setSize(1000, 600);
        int x = Helper.screenCenterLoc("x", getSize());
        int y = Helper.screenCenterLoc("y", getSize());
        setLocation(x, y);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        lbl_welcome.setText("Welcome " + operator.getName());

        //@@@@@@@@@@@           START OF USER LIST
        mdl_user_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0) {
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };
        Object[] col_user_list = {"ID", "Name", "User Name", "Password", "Account Type"};
        mdl_user_list.setColumnIdentifiers(col_user_list);

        row_user_list = new Object[col_user_list.length];


        tbl_user_list.setModel(mdl_user_list);
        tbl_user_list.getTableHeader().setReorderingAllowed(false);

        loadUser();

        tbl_user_list.getSelectionModel().addListSelectionListener(e -> {

            try {
                String selected_user_id = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 0).toString();
                fld_delete_byId.setText(selected_user_id);
            } catch (Exception exception) {

            }

        });

        tbl_user_list.getModel().addTableModelListener(e -> {

            if (e.getType() == TableModelEvent.UPDATE) {

                int user_id = Integer.parseInt(tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 0).toString());
                String name_surname = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 1).toString();
                String user_name = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 2).toString();
                String password = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 3).toString();
                String type = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 4).toString();


                if (User.updateById(user_id, name_surname, user_name, password, type)) {
                    Helper.showMsg("done");
                    loadUser();
                    loadEducatorCombo();
                    loadCourseModel();
                }
                loadUser();
            }

        });


        add_to_DB.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_name) || Helper.isFieldEmpty(fld_user_name) || Helper.isFieldEmpty(fld_password)) {
                Helper.showMsg("fill");
            } else {
                String uname = fld_user_name.getText().toString();
                String name = fld_name.getText().toString();
                String accountType = cmb_account_type.getSelectedItem().toString();
                String pass = fld_password.getText().toString();

                if (User.add(name, uname, pass, accountType)) {
                    Helper.showMsg("done");
                    loadUser();
                    loadEducatorCombo();
                    fld_password.setText(null);
                    fld_name.setText(null);
                    fld_user_name.setText(null);
                }


            }
        });


        btn_delete_byID.addActionListener(e -> {


            if (Helper.isFieldEmpty(fld_delete_byId)) {

                Helper.showMsg("fill");
            } else {

                int deleteId = Integer.parseInt(fld_delete_byId.getText());
                List<Course> courseList = Course.getList(deleteId);
                String user_name = User.getFetch(deleteId).getName();

                if (courseList.size() != 0) {

                    StringBuilder stringBuilder = new StringBuilder();
                    for (Course c : courseList) {
                        stringBuilder.append(c.getName()).append("\n");
                    }

                    int answer = JOptionPane.showConfirmDialog(this, stringBuilder.toString(), user_name + "'s Course List", JOptionPane.INFORMATION_MESSAGE);

                    if (answer == JOptionPane.YES_OPTION) {

                        if (User.deleteById(deleteId)) {
                            if (User.deleteCourses(deleteId)) {

                                Helper.showMsg("done");
                                loadUser();
                                loadEducatorCombo();
                                loadPathCombo();
                                loadCourseModel();
                                fld_delete_byId.setText(null);

                            } else {
                                Helper.showMsg("error");
                            }

                        } else {
                            Helper.showMsg("error");
                        }


                    }

                } else {

                    if (Helper.confirm()) {
                        int deleteById = Integer.parseInt(fld_delete_byId.getText());
                        if (User.deleteById(deleteById)) {

                            Helper.showMsg("done");
                            loadUser();
                            loadEducatorCombo();

                            fld_delete_byId.setText(null);


                        } else {
                            Helper.showMsg("error");
                        }
                    }

                }

                loadPathCombo();

            }


        });


        btn_user_search.addActionListener(e -> {
            String name = fld_search_name.getText().toString();
            String userName = fld_sch_username.getText().toString();
            String type = cmb_search_accountType.getSelectedItem().toString();
            String query = User.searchQuery(name, userName, type);
            loadUser(User.searchUserList(query));

        });
        btn_Exit.addActionListener(e -> {

            dispose();
            LoginGui login = new LoginGui();
        });


        //@@@@@@@@@@@@@@        END OF USER LIST


        //@@@@@@@@@@@           START OF PATHS LIST
        path_menu = new JPopupMenu();
        JMenuItem updateMenu = new JMenuItem("Update");
        JMenuItem deleteMenu = new JMenuItem("Delete");

        path_menu.add(updateMenu);
        path_menu.add(deleteMenu);

        updateMenu.addActionListener(e -> {
            int selected_id = Integer.parseInt(tbl_paths_list.getValueAt(tbl_paths_list.getSelectedRow(), 0).toString());
            UpdatePathGUI updatePathGUI = new UpdatePathGUI(Paths.getFetch(selected_id));
            updatePathGUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadPathModel();
                    loadPathCombo();
                    loadEducatorCombo();
                }
            });
        });


        deleteMenu.addActionListener(e -> {
            if (Helper.confirm()) {
                int selected_id = Integer.parseInt(tbl_paths_list.getValueAt(tbl_paths_list.getSelectedRow(), 0).toString());
                if (Paths.delete(selected_id)) {
                    Paths.delete(selected_id);
                    loadPathModel();
                    loadPathCombo();
                    Helper.showMsg("done");
                } else {
                    Helper.showMsg("error");
                }
            }
        });


        mdl_paths_list = new DefaultTableModel();
        Object[] col_paths_list = {"ID", "Path Name"};
        mdl_paths_list.setColumnIdentifiers(col_paths_list);
        row_paths_list = new Object[col_paths_list.length];
        loadPathModel();

        tbl_paths_list.setModel(mdl_paths_list);
        tbl_paths_list.setComponentPopupMenu(path_menu);

        tbl_paths_list.getColumnModel().getColumn(0).setResizable(false);
        tbl_paths_list.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(78);

        tbl_paths_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point selected_row_point = e.getPoint();
                int selected_row_int = tbl_paths_list.rowAtPoint(selected_row_point);
                tbl_paths_list.setRowSelectionInterval(selected_row_int, selected_row_int);

            }
        });


        btn_path_add.addActionListener(e -> {

            if (Helper.isFieldEmpty(fld_path_add)) {
                Helper.showMsg("fill");
            } else {
                if (Paths.addPath(fld_path_add.getText())) {
                    Helper.showMsg("done");
                    loadPathModel();
                    loadPathCombo();
                    fld_path_add.setText(null);
                } else {

                    Helper.showMsg("error");

                }
            }

        });


        //@@@@@@@@@@@           END OF PATHS LIST


        //@@@@@@@@@@@           START OF COURSE LIST


        mdl_course_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        Object[] col_course_list = {"ID", "Course Name", "Programming Language", "Path", "Educator"};
        mdl_course_list.setColumnIdentifiers(col_course_list);
        row_course_list = new Object[col_course_list.length];
        loadCourseModel();
        tbl_course_list.getTableHeader().setReorderingAllowed(false);


        tbl_course_list.setModel(mdl_course_list);
        tbl_course_list.getColumnModel().getColumn(0).setMaxWidth(78);
        tbl_course_list.getTableHeader().setReorderingAllowed(false);

        loadPathCombo();
        loadEducatorCombo();


        btn_course_add.addActionListener(e -> {
            Item path = (Item) cmb_course_path.getSelectedItem();
            Item educator = (Item) cmb_course_educator.getSelectedItem();
            if (Helper.isFieldEmpty(fld_course_name) || Helper.isFieldEmpty(fld_course_lang) || path == null || educator == null) {
                Helper.showMsg("fill");
            } else {
                if (Course.add(fld_course_name.getText(), fld_course_lang.getText(), path, educator)) {
                    Helper.showMsg("done");
                    loadCourseModel();
                    fld_course_lang.setText(null);
                    fld_course_name.setText(null);

                } else {
                    Helper.showMsg("error");
                }


            }


        });

        tbl_course_list.getSelectionModel().addListSelectionListener(e -> {

            if (tbl_course_list.getSelectedRow() != -1){
                load_cmb_edu_update();
                load_cmb_path_update();

                String selected_course_id = tbl_course_list.getValueAt(tbl_course_list.getSelectedRow(),0).toString();
                String course_name = tbl_course_list.getValueAt(tbl_course_list.getSelectedRow(),1).toString();
                String program_lang = tbl_course_list.getValueAt(tbl_course_list.getSelectedRow(),2).toString();
                String path_name = tbl_course_list.getValueAt(tbl_course_list.getSelectedRow(),3).toString();
                String edu_name = tbl_course_list.getValueAt(tbl_course_list.getSelectedRow(),4).toString();



                fld_update_coursename.setText(course_name);
                fld_update_prog_lang.setText(program_lang);
                lbl_id_update.setText(selected_course_id);

                int j = -1;
                for (Paths paths : Paths.getList()) {
                    j++;
                    if (paths.getName().equals(path_name)){
                        cmb_path_update.setSelectedIndex(j);
                    }
                }

                User Itemedu = User.getFetchWithName(edu_name);

                Item educator = new Item(Itemedu.getId(), Itemedu.getName());

                int i = -1;
                for (User user : User.getListByType("educator")) {
                    i++;
                    if (user.getName().equals(Itemedu.getName())){
                        cmb_edu_update.setSelectedIndex(i);
                    }
                }
            }
            else {
                fld_update_coursename.setText(null);
                fld_update_prog_lang.setText(null);
                lbl_id_update.setText(null);
                cmb_path_update.setSelectedItem(null);
                cmb_edu_update.setSelectedItem(null);
            }




        });



        btn_update_course.addActionListener(e -> {


            String selected_course_id = tbl_course_list.getValueAt(tbl_course_list.getSelectedRow(),0).toString();
            String course_name = fld_update_coursename.getText().toString();
            String program_lang = fld_update_prog_lang.getText().toString();
            String path_name = cmb_path_update.getSelectedItem().toString();
            String edu_name = cmb_edu_update.getSelectedItem().toString();

            int path_id = Paths.getFetch(path_name).getId();
            int user_id = User.getFetchWithName(edu_name).getId();



            if (Helper.isFieldEmpty(fld_update_coursename) || Helper.isFieldEmpty(fld_update_prog_lang)) {
                Helper.showMsg("fill");
            } else {


                if (Course.updateById(Integer.parseInt(selected_course_id),user_id,path_id,course_name,program_lang)) {
                    Helper.showMsg("done");


                     loadCourseModel();


                    /*loadPathModel();
                    loadPathCombo();*/
                    //fld_path_add.setText(null);
                } else {

                    Helper.showMsg("error");

                }
            }


        });

        btn_dlt_course.addActionListener(e -> {

            if (tbl_course_list.getSelectionModel().getSelectedItemsCount() != 0){
                int selected_course_id =Integer.parseInt(tbl_course_list.getValueAt(tbl_course_list.getSelectedRow(),0).toString());
                Course.delete(selected_course_id);
                loadCourseModel();
                Helper.showMsg("done");
            }

            else {
                Helper.showMsg("choose");
            }

        });

    }




        //@@@@@@@@@@@           END OF COURSE LIST




    private void load_cmb_edu_update() {

        cmb_edu_update.removeAllItems();
        for (User user : User.getListByType("educator")) {
            cmb_edu_update.addItem(new Item(user.getId(), user.getName()));
        }

    }

    private void load_cmb_path_update() {

        cmb_path_update.removeAllItems();
        for (Paths paths : Paths.getList()) {
            cmb_path_update.addItem(new Item(paths.getId(), paths.getName()));
        }

    }



    private void loadCourseModel() {

        int rowCount = tbl_course_list.getRowCount();
        for (int i = rowCount-1; i>=0;i--){

            tbl_course_list.removeRowSelectionInterval(i,i);

        }
        int i;

        DefaultTableModel clearModel = (DefaultTableModel) tbl_course_list.getModel();

        clearModel.getDataVector().removeAllElements();

        clearModel.setRowCount(0);
        for (Course obj : Course.getList()) {
            i =0;

            row_course_list[i++] = obj.getId();
            row_course_list[i++] = obj.getName();
            row_course_list[i++] = obj.getLang();
            row_course_list[i++] = obj.getPaths().getName();
            row_course_list[i] = obj.getEducator().getName();
            mdl_course_list.addRow(row_course_list);
        }

    }

    private void loadPathModel() {

        DefaultTableModel clearModel = (DefaultTableModel) tbl_paths_list.getModel();
        clearModel.setRowCount(0);
        int i = 0;
        for (Paths obj : Paths.getList()) {
            i = 0;
            row_paths_list[i++] = obj.getId();
            row_paths_list[i++] = obj.getName();
            mdl_paths_list.addRow(row_paths_list);

        }

    }


    public void loadUser() {

        DefaultTableModel clearModel = (DefaultTableModel) tbl_user_list.getModel();
        clearModel.setRowCount(0);

        for (User obj : User.getList()) {

            int i = 0;
            row_user_list[i++] = obj.getId();
            row_user_list[i++] = obj.getName();
            row_user_list[i++] = obj.getUserName();
            row_user_list[i++] = obj.getPassword();
            row_user_list[i++] = obj.getType();
            mdl_user_list.addRow(row_user_list);

        }
    }

    public void loadUser(ArrayList<User> searchingUsers) {

        DefaultTableModel clearModel = (DefaultTableModel) tbl_user_list.getModel();
        clearModel.setRowCount(0);

        for (User obj : searchingUsers) {

            int i = 0;
            row_user_list[i++] = obj.getId();
            row_user_list[i++] = obj.getName();
            row_user_list[i++] = obj.getUserName();
            row_user_list[i++] = obj.getPassword();
            row_user_list[i++] = obj.getType();
            mdl_user_list.addRow(row_user_list);

        }
    }

    public void loadPathCombo() {

        cmb_course_path.removeAllItems();
        for (Paths paths : Paths.getList()) {
            cmb_course_path.addItem(new Item(paths.getId(), paths.getName()));
        }

    }

    public void loadEducatorCombo() {
        cmb_course_educator.removeAllItems();
        for (User user : User.getListByType("educator")) {
            cmb_course_educator.addItem(new Item(user.getId(), user.getName()));
        }
    }


    public static void main(String[] args) {
        Helper.setLayout();
        Operator op = new Operator();
        op.setId(1);
        op.setName("ee BB");
        op.setPassword("1234");
        op.setType("Operator");
        op.setUserName("BB ee");


        OperatorGUI opGui = new OperatorGUI(op);


    }

}

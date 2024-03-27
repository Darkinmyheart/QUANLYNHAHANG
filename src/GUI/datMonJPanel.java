/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import Dao.SanPhamDao;
import Model.NhanVien;
import Model.SanPham;
import ServerController.Server;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

/**
 *
 * @author lethi
 */
public class datMonJPanel extends javax.swing.JPanel {
    public HashMap<String,JScrollPane> listorderpanel = new HashMap<>();
    public static ArrayList<String> listOrder = new ArrayList<>();
    public static ArrayList<String> dsban = new ArrayList<>();
    NhanVien nv = new NhanVien();
    SanPhamDao monAnDAO = new SanPhamDao();
    /**
     * Creates new form datMonJPanel
     */
    public datMonJPanel() {
        initComponents();
    }

    public void createOrder(Object[] x, ArrayList<Object[]> ds) { // x[số bàn, số lần bàn đó gọi, số idclient]
        
        String idorder = new SimpleDateFormat("ddMMyy").format(new Date()) + listOrder.size();
        listOrder.add(idorder);
        int width = 200;
        int height = 513;
        JPanel order = new JPanel(new BorderLayout());
//        order.setPreferredSize(new Dimension(width, height));
//        order.setBorder(new EmptyBorder(10, 10, 10, 10));
        order.setBackground(Color.white);
        
        JPanel sobanJPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        sobanJPanel.setPreferredSize(new Dimension(width, 100));
        
        JLabel soban = new JLabel(x[0].toString());
        soban.setHorizontalAlignment(SwingConstants.CENTER);
        soban.setPreferredSize(new Dimension(width, 50));
        soban.setFont(new Font("Aria", Font.BOLD, 16));
        
        JLabel solan = new JLabel("Hoá đơn: " + x[1].toString() + " Time: " + new SimpleDateFormat("ss:mm:hh").format(new Date()));
        soban.setHorizontalAlignment(SwingConstants.CENTER);
        soban.setPreferredSize(new Dimension(width, 50));
        soban.setFont(new Font("Aria", Font.BOLD, 15));
        sobanJPanel.add(soban);
        sobanJPanel.add(solan);
        
        JPanel monan = new JPanel(new FlowLayout(FlowLayout.LEFT));
        monan.setOpaque(false);
        monan.setPreferredSize(new Dimension(width, height - 10));
        for (Object[] o : ds) {
            String mon = monAnDAO.getObjectById(o[0].toString()).getTen();
            JLabel tenlabel = new JLabel(" *** Tên: " + mon);
            tenlabel.setPreferredSize(new Dimension(width, 16));
            
            JLabel soluonglabel = new JLabel(" *** Số lượng: " + o[1]);
            soluonglabel.setPreferredSize(new Dimension(width, 16));
            
            JLabel line = new JLabel("--------------------------------------");
            soluonglabel.setPreferredSize(new Dimension(width, 16));
            monan.add(tenlabel);
            monan.add(soluonglabel);
            monan.add(line);
            
        }
        JButton doneButton = new JButton();
        doneButton.setText("Hoàn thành đơn");
        doneButton.setPreferredSize(new Dimension(width, 20));
        doneButton.addActionListener(doneAction);
        doneButton.setName(idorder + "," + x[2]); //setName [id của order, client id]
        
        order.add(sobanJPanel, BorderLayout.NORTH);
        order.add(monan, BorderLayout.CENTER);
        order.add(doneButton, BorderLayout.SOUTH);
        JScrollPane scroll = new JScrollPane(order);
        scroll.setPreferredSize(new Dimension(width, height));
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.getVerticalScrollBar().setUnitIncrement(20);
        
        listorderpanel.put(idorder,scroll);
        Danhsachbanpanel.add(scroll);
//        Danhsachbanpanel.revalidate();
//        Danhsachbanpanel.repaint();
        dsban.add(x[0].toString());
        Map<String, ArrayList<?>> mapArr = new HashMap<>();
        ArrayList<String> ordernum = new ArrayList<>();
        ordernum.add(idorder);
        mapArr.put("order-number", ordernum);
        Server.SocketsSever.messageByServerThread(x[2].toString(), "Order-Succesful", mapArr);
        
    }
    
    ActionListener doneAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = new JButton();
            button = (JButton) e.getSource();
            String[] name = button.getName().split(",");
            
            ArrayList<String> arr = new ArrayList<>();
            arr.add(name[0]);
            Map<String, ArrayList<?>> mapArr = new HashMap<>();
            mapArr.put("idorder", arr);
            Server.SocketsSever.messageByServerThread(name[1], "Complete-order", mapArr);
            Danhsachbanpanel.removeAll();
            Danhsachbanpanel.revalidate();
            Danhsachbanpanel.repaint();
            listorderpanel.remove(name[0]);
            for (Map.Entry<String, JScrollPane> entry : listorderpanel.entrySet()) {
                Danhsachbanpanel.add(entry.getValue());
            }
        }
    };
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Danhsachbanpanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();

        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1050, 558));

        Danhsachbanpanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jScrollPane1.setViewportView(Danhsachbanpanel);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        jTabbedPane1.addTab("ORDER", jPanel2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("tab2", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Danhsachbanpanel;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}

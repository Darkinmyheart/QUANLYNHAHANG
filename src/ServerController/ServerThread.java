package ServerController;

import Dao.*;
import Model.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author lethi
 */
public class ServerThread implements Runnable {
//    private tai user;

    private NhanVien nv;
    private Socket socketOfServer;
    private ObjectOutputStream os;
    private ObjectInputStream is;
    boolean flag = true;
    private String serverThreadId;
//    private boolean isClosed;
//    private String clientIP;

    public ServerThread(Socket socketOfServer) {
        this.socketOfServer = socketOfServer;
    }

    public String getServerThreadId() {
        return serverThreadId;
    }

    public void setServerThreadId(String serverThreadId) {
        this.serverThreadId = serverThreadId;
    }

    @Override
    public void run() {
        for (ServerThread s : Server.SocketsSever.listServerThread) {
            System.out.println("Có serverthread mang id: " + s.getServerThreadId());
        }

        try {
            is = new ObjectInputStream(socketOfServer.getInputStream());
            os = new ObjectOutputStream(socketOfServer.getOutputStream());
            //Gửi id-default
            Map<String, ArrayList<?>> mapiddefault = new HashMap<>();
            ArrayList<String> arriddefault = new ArrayList<>();
            arriddefault.add(serverThreadId);
            mapiddefault.put("id", arriddefault);
            write("id-default", mapiddefault);

            Map<String, ArrayList<?>> message;
            while (this.flag) {
                message = (Map<String, ArrayList<?>>) is.readObject();
                if (message.isEmpty()) {
                    break;
                } else {
                    switch (message.get("sign").get(0).toString()) {
                        case "request-Login":
                            ArrayList<String> arrAcount = (ArrayList<String>) message.get("Account");
                            nv = new TaiKhoanDao().checkLogin(arrAcount.get(0), arrAcount.get(1));
                            String idCreate = String.valueOf(new TaiKhoanDao().getTaiKhoanByIDUser(nv.getID()).getId());
                            boolean flag = true;
                            if (nv != null) {
                                for (ServerThread o : Server.SocketsSever.listServerThread) {
                                    if (o.getServerThreadId().equalsIgnoreCase(idCreate)) {
                                        write("Dup-Acount", new HashMap<>());
                                        flag = false;
                                        break;
                                    }
                                }
                                if (flag) {

                                    serverThreadId = String.valueOf(idCreate);
                                    ArrayList<LoaiSanPham> dsloai = new LoaiSanPhamDao().GetArrayListAll();
                                    ArrayList<SanPham> dsMA = new SanPhamDao().GetArrayListAll();
                                    ArrayList<Object[]> dssoluong = new ArrayList<>();
                                    for (SanPham sanPham : dsMA) {
                                        dssoluong.add(new Object[]{sanPham.getID(), new CongThucDAO().SoluongSanPhamTheoNguyenLieu(sanPham.getID())});
                                    }
                                    ArrayList<NhanVien> arrnv = new ArrayList<>();
                                    ArrayList<String> arrid = new ArrayList<>();
                                    ArrayList<KhachHang> arrkh = new KhachHangDao().GetArrayListAll();
                                    ArrayList<TaiKhoan> arrtk = new ArrayList<>();
                                    arrtk.add(new TaiKhoanDao().getTaiKhoanByIDUser(nv.getID()));
                                    System.out.println("id account = " + idCreate);
                                    new LichSuDangNhapDAO().InsertSQL(new LichSuDangNhap(1, new TaiKhoanDao().getTaiKhoanByIDUser(nv.getID()).getId(), null, null));
                                    arrid.add(serverThreadId);
                                    arrnv.add(nv);
                                    Map< String, ArrayList<?>> map = new HashMap<>();
                                    map.put("khachhang", arrkh);
                                    map.put("soluong", dssoluong);
                                    map.put("dsMA", dsMA);
                                    map.put("dsloai", dsloai);
                                    map.put("nhanvien", arrnv);
                                    map.put("client_id", arrid);
                                    map.put("taikhoan", arrtk);
                                    write("login-Succesfull", map);
                                }
                            } else {
                                write("login-Fail", new HashMap<>());
                            }

                            break;
                        case "request-Login-Email":
                            ArrayList<String> arrAcountmail = (ArrayList<String>) message.get("Account");
                            nv = new TaiKhoanDao().checkLoginByEmail(arrAcountmail.get(0), arrAcountmail.get(1));
                            boolean flagloginmail = true;
                            String idCreateEmail = String.valueOf(new TaiKhoanDao().getTaiKhoanByIDUser(nv.getID()).getId());

                            if (nv != null) {
                                for (ServerThread o : Server.SocketsSever.listServerThread) {
                                    if (o.getServerThreadId().equalsIgnoreCase(idCreateEmail)) {
                                        write("Dup-Acount", new HashMap<>());
                                        flagloginmail = false;
                                        break;
                                    }
                                }
                                if (flagloginmail) {
                                    serverThreadId = idCreateEmail;
                                    ArrayList<LoaiSanPham> dsloai = new LoaiSanPhamDao().GetArrayListAll();
                                    ArrayList<SanPham> dsMA = new SanPhamDao().GetArrayListAll();
                                    ArrayList<Object[]> dssoluong = new ArrayList<>();
                                    for (SanPham sanPham : dsMA) {
                                        dssoluong.add(new Object[]{sanPham.getID(), new CongThucDAO().SoluongSanPhamTheoNguyenLieu(sanPham.getID())});
                                    }
                                    ArrayList<NhanVien> arrnv = new ArrayList<>();
                                    ArrayList<String> arrid = new ArrayList<>();
                                    ArrayList<KhachHang> arrkh = new KhachHangDao().GetArrayListAll();
                                    ArrayList<TaiKhoan> arrtk = new ArrayList<>();
                                    arrtk.add(new TaiKhoanDao().getTaiKhoanByIDUser(nv.getID()));
                                    System.out.println("id account = " + idCreateEmail);
                                    new LichSuDangNhapDAO().InsertSQL(new LichSuDangNhap(1, new TaiKhoanDao().getTaiKhoanByIDUser(nv.getID()).getId(), null, null));
                                    arrid.add(serverThreadId);
                                    arrnv.add(nv);
                                    Map< String, ArrayList<?>> map = new HashMap<>();
                                    map.put("khachhang", arrkh);
                                    map.put("soluong", dssoluong);
                                    map.put("dsMA", dsMA);
                                    map.put("dsloai", dsloai);
                                    map.put("nhanvien", arrnv);
                                    map.put("taikhoan", arrtk);
                                    map.put("client_id", arrid);
                                    write("login-Succesfull", map);
                                }
                            } else {
                                write("login-Fail", new HashMap<>());
                            }

                            break;
                        case "Doi_MK":
                            ArrayList<String> arrDoiMK = new ArrayList<>();
                            arrDoiMK = (ArrayList<String>) message.get("mk");//<ClientID,NVID,MK_New>
                            int checkDoiMK = new TaiKhoanDao().UpdateSQL(new NhanVienDao().getObjectById(arrDoiMK.get(1)).getEmail(),arrDoiMK.get(2));
                            if(checkDoiMK==1){
                                Server.SocketsSever.messageByServerThread(arrDoiMK.get(0),"Doi_MK_Succesful", new HashMap<>());
                            }
                            break;
                        case "Send-Code-email":
                            ArrayList<String> sendcodearr = (ArrayList<String>) message.get("email-code");//[email,idclient]
                            NhanVien nvsendcodeemail = new NhanVienDao().checkGmail(sendcodearr.get(0));
                            if (nvsendcodeemail != null) {
                                String randomcodemail = Utils.RandomMa.generateRandomCode();
                                Utils.SendMail.SendMail(sendcodearr.get(0), randomcodemail);

                                Map<String, ArrayList<?>> MapSendCodeEmail = new HashMap<>();
                                ArrayList<String> arrCode = new ArrayList<>();
                                arrCode.add(randomcodemail);
                                ArrayList<NhanVien> arrCodenv = new ArrayList<>();
                                arrCodenv.add(nvsendcodeemail);
                                nv = nvsendcodeemail;
                                MapSendCodeEmail.put("code", arrCode);
                                MapSendCodeEmail.put("nv", arrCodenv);
                                Server.SocketsSever.messageByServerThread(sendcodearr.get(1), "send-Code-Succesfull", MapSendCodeEmail);
                            } else {
                                Server.SocketsSever.messageByServerThread(sendcodearr.get(1), "send-Code-Fail", new HashMap<>());
                            }
                            break;
                        // Yêu cầu đóng
                        case "close":
                            System.out.println(nv.getTen() + " đã ngắt kết nối");
                            Server.SocketsSever.remove(serverThreadId);
                            break;
                        case "Reset-Password":
                            ArrayList<String> arrresetpassword = (ArrayList<String>) message.get("mk-new");//[mật khẩu mới, idnhanvien,clientID]
                            int check = new TaiKhoanDao().UpdateSQL(new TaiKhoan(1, arrresetpassword.get(1), "1", arrresetpassword.get(0)));
                            if (check == 1) {
                                for (ServerThread s : Server.SocketsSever.listServerThread) {
                                    if (s.getServerThreadId().equalsIgnoreCase(arrresetpassword.get(2))) {
                                        s.serverThreadId = arrresetpassword.get(1);
                                        break;
                                    }
                                }
                                ArrayList<LoaiSanPham> dsloai = new LoaiSanPhamDao().GetArrayListAll();
                                ArrayList<SanPham> dsMA = new SanPhamDao().GetArrayListAll();
                                ArrayList<NhanVien> arrnv = new ArrayList<>();
                                ArrayList<String> arrid = new ArrayList<>();
                                System.out.println("id account = " + new TaiKhoanDao().getTaiKhoanByIDUser(nv.getID()).getId());
                                new LichSuDangNhapDAO().InsertSQL(new LichSuDangNhap(1, new TaiKhoanDao().getTaiKhoanByIDUser(nv.getID()).getId(), null, null));
                                arrid.add(serverThreadId);
                                arrnv.add(nv);
                                Map< String, ArrayList<?>> map = new HashMap<>();
                                map.put("dsMA", dsMA);
                                map.put("dsloai", dsloai);
                                map.put("nhanvien", arrnv);
                                map.put("client_id", arrid);
                                write("login-Succesfull", map);
                            }
                            break;
                        case "Create-order": // Tạo đơn
                            Object[] table = (Object[]) message.get("table").get(0);
                            ArrayList<Object[]> ds = (ArrayList<Object[]>) message.get("dsKhachChon");

                            Server.testGui.datMonJPanel1.createOrder(table, ds);
                            break;
                        case "SoBanDaChon_Request":
                            String clientid = (String) message.get("idclient").get(0);
                            ServerThread sv = Server.SocketsSever.getServerThread(clientid);

                            Map<String, ArrayList<?>> maparr = new HashMap<>();
                            maparr.put("dsban", Server.testGui.datMonJPanel1.dsban);
                            Server.SocketsSever.messageByServerThread(clientid, "SoBanDaChon_Send", maparr);
                            break;
                        case "Complete-Bill":
//                            map.put("listHoaDonChiTiet", dstaohoadon);
//                            ArrayList<String> arrs = new ArrayList<>();
//                            arrs.add(idhoadon);
//                            arrs.add(idkh);
//                            map.put("idhoadon-idkh", arrs);
                            ArrayList<Object[]> arr = (ArrayList<Object[]>) message.get("listHoaDonChiTiet");//[idmonan,soluong,gia,ten]
                            ArrayList<String> arrs = (ArrayList<String>) message.get("idhoadon-idkh-idclient");
                            String idhoadon = arrs.get(0);
                            int tongtien = 0;
                            for (Object[] a : arr) {
                                tongtien = tongtien + (Integer.valueOf(a[1].toString()) * Integer.valueOf(a[2].toString().substring(0, a[2].toString().indexOf("."))));
                            }
                            int checkCompleteBill = new HoaDonDao().InsertSQL(new HoaDon(arrs.get(0), BigDecimal.valueOf(tongtien), new Date(), Integer.valueOf(arrs.get(2)), arrs.get(1)));
                            if (checkCompleteBill == 1) {
                                ArrayList<Model.HoaDonCT> dshdct = new ArrayList<>();
                                for (Object[] a : arr) {
                                    dshdct.add(new HoaDonCT(idhoadon, a[0].toString(), Integer.valueOf(a[1].toString()), ""));
                                    System.out.println(new HoaDonCT(idhoadon, a[0].toString(), Integer.valueOf(a[1].toString()), "").toString());
                                }
                                new HoaDonCTDAO().InsertSQL(dshdct);
                                Server.testGui.thongKePanel2.reset();
                                JOptionPane.showMessageDialog(Server.testGui, new NhanVienDao().getObjectById(new TaiKhoanDao().getObjectById(arrs.get(2)).getIdNhanVien()).getTen() + "Đã hoàn thành hoá đơn " + arrs.get(0));
                            }
                            break;
                        case "Create-KhachHang":
                            ArrayList<String> dsCreateKhachHang = (ArrayList<String>) message.get("Info_KhachHang");//<idclient,Tenkh,sdt> 
                            if (new KhachHangDao().checkDup(dsCreateKhachHang.get(2)) == 0) {
                                int checkCreateKhachHang = new KhachHangDao().InsertSQL(new KhachHang(new KhachHangDao().CreateKH(), dsCreateKhachHang.get(1),
                                        dsCreateKhachHang.get(2), "", "", dsCreateKhachHang.get(0)));
                                if (checkCreateKhachHang == 1) {
                                    Map<String, ArrayList<?>> mapCreateKhachHang = new HashMap<>();
                                    mapCreateKhachHang.put("dskhachhang", new KhachHangDao().GetArrayListAll());
                                    Server.SocketsSever.messageByServerThread(dsCreateKhachHang.get(0), "Create_KhachHang_Succesful", mapCreateKhachHang);
                                }
                            } else {
                                Server.SocketsSever.messageByServerThread(dsCreateKhachHang.get(0), "Create_KhachHang_Dup", new HashMap<>());
                            }
                            break;
                    }
                }
            }

        } catch (IOException ex) {
            this.flag = false;

            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void write(String sign, Map<String, ArrayList<?>> map) {
        try {
            Map<String, ArrayList<?>> mapWrite = new HashMap<>();
            ArrayList<String> o = new ArrayList<>();
            o.add(sign);
            mapWrite.put("sign", o);
            for (Map.Entry<String, ArrayList<?>> key : map.entrySet()) {
                mapWrite.put(key.getKey(), key.getValue());
            }
            os.reset();
            os.writeObject(mapWrite);

            os.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

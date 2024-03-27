
package Model;

import java.util.Date;

/**
 *
 * @author lethi
 */
public class LichSuDangNhap {
    private int ID;
    private int IDAccount;
    private Date TimeLogin;
    private Date TimeLogout;

    public LichSuDangNhap() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIDAccount() {
        return IDAccount;
    }

    public void setIDAccount(int IDAccount) {
        this.IDAccount = IDAccount;
    }

    public Date getTimeLogin() {
        return TimeLogin;
    }

    public void setTimeLogin(Date TimeLogin) {
        this.TimeLogin = TimeLogin;
    }

    public Date getTimeLogout() {
        return TimeLogout;
    }

    public void setTimeLogout(Date TimeLogout) {
        this.TimeLogout = TimeLogout;
    }

    public LichSuDangNhap(int ID, int IDAccount, Date TimeLogin, Date TimeLogout) {
        this.ID = ID;
        this.IDAccount = IDAccount;
        this.TimeLogin = TimeLogin;
        this.TimeLogout = TimeLogout;
    }
    
}

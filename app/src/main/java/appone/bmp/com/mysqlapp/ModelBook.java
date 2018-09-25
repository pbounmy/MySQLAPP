package appone.bmp.com.mysqlapp;

import android.util.Log;

import java.sql.*;

public class ModelBook implements ActionDB {
    private String id;
    private String _bname;
    private int _bprice;
    private int _bpage;
    Connection c;

    public ModelBook(Connection c) {
        this.c = c;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String get_bname() {
        return _bname;
    }

    public void set_bname(String _bname) {
        this._bname = _bname;
    }

    public int get_bprice() {
        return _bprice;
    }

    public void set_bprice(int _bprice) {
        this._bprice = _bprice;
    }

    public int get_bpage() {
        return _bpage;
    }

    public void set_bpage(int _bpage) {
        this._bpage = _bpage;
    }

    @Override
    public ResultSet SelectData() {
        try {
            String sql="select * from tbbook";
            ResultSet rs = c.createStatement().executeQuery(sql);
            return rs;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public int InsertData()  {
                try {
                    String sql = "Insert Into tbbook(id,bookname,price,page) values(?,?,?,?)";
                    PreparedStatement ps = c.prepareStatement(sql);
                    ps.setString(1,id);
                    ps.setString(2,   _bname);
                    ps.setInt(3,   _bprice);
                    ps.setInt(4,   _bpage);

                    int r = ps.executeUpdate();
                    Log.i("valueR", "r==>" + r);
                    return r;
                }catch (Exception e){
                    e.getMessage().toString();
                    return 0;
                }

    }

    @Override
    public int UpdateData() {

            try {
                String sql = "Update tbbook SET bookname=?,price=?,page=? where id=?)";
                PreparedStatement ps = c.prepareStatement(sql);
                ps.setString(4,id);
                ps.setString(1,_bname);
                ps.setInt(2,_bprice);
                ps.setInt(3,_bpage);
                int r = ps.executeUpdate();
                return r;
            }catch (Exception e){
                e.getMessage().toString();
                return 0;
            }

    }

    @Override
    public int DeleteData() {
        try {
            String sql="delete from id=?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1,id);
            int r=ps.executeUpdate();
            return r;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }

    }
}

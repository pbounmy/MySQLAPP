package appone.bmp.com.mysqlapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.*;
import java.util.ArrayList;

public class FragmentShowData extends Fragment {
    ModelBook mdb;
    private Button btnshow;
    private ListView lvitm;
    TextView tvshow;
    Connection c;
    ArrayList<String> arrlist = new ArrayList<String>();
    ArrayAdapter<String> adt = null;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View v =inflater.inflate(R.layout.fragmentshowdata,container,false);
       btnshow = v.findViewById(R.id.btnload);
       tvshow =v.findViewById(R.id.tvlist);
       lvitm = v.findViewById(R.id.lvshowdata);

        btnshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TastSelect().execute();
            }
        });
        return v;
    }
    private class TastSelect extends AsyncTask<String,String,String> {
        String msg="";
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            msg="ກຳລັງເຊື່ອມຕໍ່ໍຂໍ້ມູນ";
            tvshow.setText(msg);

        }

        @Override
        protected void onPostExecute(String Strings) {
            super.onPostExecute(Strings);
            adt = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, arrlist);
            lvitm.setAdapter(adt);
            tvshow.setText(Strings);
        }
        @Override
        protected String doInBackground(String... s) {
            try {
                Connection c = DBConnect.getConnect();
                ModelBook mdb = new ModelBook(c);
                ResultSet rs = mdb.SelectData();
                arrlist.clear();
                while (rs.next()) {
                    arrlist.add(rs.getString(2));
                }
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            msg="ດຶງຂໍ້ມູນແລ້ວ";
            return msg;

        }
    }

}

package appone.bmp.com.mysqlapp;

import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.*;
public class FragmentInsertData extends Fragment {
private Button btnback,btnInsertData;
private EditText txtid,txtname,txtprice,txtpage;
private TextView tvshowmsg;
Connection c=null;
ResultSet rs=null;
int r;
ModelBook mdb;
   // AlertDialog.Builder alert ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View v = inflater.inflate(R.layout.fragmentsavedata,container,false);
      txtid = v.findViewById(R.id.txtbookid);
        txtname = v.findViewById(R.id.txtbookname);
        txtprice = v.findViewById(R.id.txtbookprice);
        txtpage = v.findViewById(R.id.txtbookpage);

      btnback = v.findViewById(R.id.btnback);
      tvshowmsg = v.findViewById(R.id.tvshowconnect);
        btnInsertData = v.findViewById(R.id.btnSaveData);
        btnInsertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyTask().execute();
            }
        });
      btnback.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              getActivity()
                      .getSupportFragmentManager()
                      .beginTransaction()
                      .replace(R.id.contentMain,new FragmentMenu())
                      .addToBackStack(null)
                      .commit();
          }
      });
        return v;
    }
    private class MyTask extends AsyncTask<String,String,String>{
        String msg="";
        @Override
        protected void onPreExecute() {
                msg ="Please wait Inserting.....";
            //Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();alert =new AlertDialog.Builder(getActivity());
            //alert.setMessage(msg);
            tvshowmsg.setText(msg);
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                c = DBConnect.getConnect();

                if(c ==null){
                    msg="Connecting wrong";
                }else{
                    msg="Connecting to mysql complete";
                }

            }catch (Exception e){
                e.printStackTrace();
            }
            return msg;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mdb = new ModelBook(c);
            mdb.setId(txtid.getText().toString());
            mdb.set_bname(txtname.getText().toString().trim());
           mdb.set_bprice(Integer.parseInt(txtprice.getText().toString().trim()));
           mdb.set_bpage(Integer.parseInt(txtpage.getText().toString().trim()));
            String str =mdb.getId()+"\n"+mdb.get_bname()+"\n"+mdb.get_bprice()+" \n"+mdb.get_bpage();



                r = mdb.InsertData();

            Log.e("Data","Data==>"+str);

                Log.e("Data","Data==>"+r);

            if(r !=0){
               msg="Save Data complete" ;
            }else{
                msg="Can not save data";
            }
            Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
            tvshowmsg.setText(s);

        }
    }

}

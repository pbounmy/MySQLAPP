package appone.bmp.com.mysqlapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentMenu extends Fragment {
    private Button btnsave,btninsert,btnupdate,btndelete,btnshow;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View v =inflater.inflate(R.layout.fragmentmenu,container,false);
        btnsave = v.findViewById(R.id.btn_Save);
         btnshow = v.findViewById(R.id.btnSelect);
         btnshow.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 getActivity()
                         .getSupportFragmentManager()
                         .beginTransaction()
                         .replace(R.id.contentMain,new FragmentShowData())
                         .addToBackStack(null)
                         .commit();
             }
         });

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentMain,new FragmentInsertData())
                        .addToBackStack(null)
                        .commit();
            }
        });




       return v;
    }
}

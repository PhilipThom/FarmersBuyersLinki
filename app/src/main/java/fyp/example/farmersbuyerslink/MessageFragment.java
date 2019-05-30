package fyp.example.farmersbuyerslink;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import fyp.example.farmersbuyerslink.Model.FarmersUploads;
import fyp.example.farmersbuyerslink.Model.Users;

public class MessageFragment extends Fragment {

    private Button UploadBtn;
    private EditText Quantity;
    private EditText Type;
    private ProgressDialog loading;
    long maxid=0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_message,container,false);


        Quantity=(EditText)view.findViewById(R.id.uploads_quantity);
        Type=(EditText)view.findViewById(R.id.uploads_type);
        final FarmersUploads farmersUploads=new FarmersUploads();
        final Users users=new Users();
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference().child("Users");
        RootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    maxid = (dataSnapshot.getChildrenCount());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        Button btnfragment_uploads=(Button)view.findViewById(R.id.farmers_uploads);
        btnfragment_uploads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quantity = Quantity.getText().toString().trim();
                String type = Type.getText().toString().trim();
                //int quantity=Integer.parseInt(Quantity.getText().toString().trim());


                if (TextUtils.isEmpty(type)) {
                    Toast.makeText(getActivity(), "What Type Of Crops You Want To Sell", Toast.LENGTH_SHORT).show();
                } else {


                    users.setQuantity(quantity);
                    users.setType(Type.getText().toString().trim());

                    RootRef.child(String.valueOf(maxid + 1)).setValue(users);

                    Toast.makeText(getActivity(), "Uploaded", Toast.LENGTH_SHORT).show();


                }
            }

                



        });
        return view;
    }




}




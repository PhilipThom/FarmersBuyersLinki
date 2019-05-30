 package fyp.example.farmersbuyerslink;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import fyp.example.farmersbuyerslink.Model.FarmersUploads;
import fyp.example.farmersbuyerslink.Model.Users;

public class AllFarmersUploadsActivity extends AppCompatActivity {

    private ListView listView;
    DatabaseReference RootRef;
    List<Users> usersList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_farmers_uploads);

        listView=findViewById(R.id.list_view);
        RootRef= FirebaseDatabase.getInstance().getReference("Users");

        usersList=new ArrayList<>();


    }

    @Override
    protected void onStart() {
        super.onStart();
        RootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot usersSnapshot:dataSnapshot.getChildren()) {

                    Users users=usersSnapshot.getValue(Users.class);

                    usersList.add(users);
                }
                Farmersinfo farmersinfo=new Farmersinfo(AllFarmersUploadsActivity.this,usersList);
                listView.setAdapter(farmersinfo);

            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

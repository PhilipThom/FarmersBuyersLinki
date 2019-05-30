package fyp.example.farmersbuyerslink;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements ExBottomSheetDialog.BottomSheetListener {
    private Button signUpNowButton,Login;
    private Button buyerLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //signUpNowButton=(Button) findViewById(R.id.main_sign_up_btn);
        Login=(Button) findViewById(R.id.main_login_btn);
        buyerLogin=(Button) findViewById(R.id.main_buyer_login);

        buyerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent (MainActivity.this,BuyerLoginActivity.class);
                startActivity(intent);
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        Button buttonOpenBottomSheet=findViewById(R.id.main_sign_up_btn);
        buttonOpenBottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExBottomSheetDialog bottomSheet=new ExBottomSheetDialog();
                bottomSheet.show(getSupportFragmentManager(),"exampleBottomSheet");
            }
        });





    }


    @Override
    public void onButtonClicked(String text) {

    }
}

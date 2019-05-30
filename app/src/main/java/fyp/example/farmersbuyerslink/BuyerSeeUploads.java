package fyp.example.farmersbuyerslink;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BuyerSeeUploads extends AppCompatActivity {
    private Button buyerViewUploads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_see_uploads);

        buyerViewUploads=(Button) findViewById(R.id.get_uploads_btn);
        buyerViewUploads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new  Intent(BuyerSeeUploads.this,AllFarmersUploadsActivity.class);
                startActivity(intent);
            }
        });
    }
}

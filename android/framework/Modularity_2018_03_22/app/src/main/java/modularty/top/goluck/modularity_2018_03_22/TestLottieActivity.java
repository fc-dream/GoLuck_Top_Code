package modularty.top.goluck.modularity_2018_03_22;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.airbnb.lottie.ImageAssetDelegate;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieImageAsset;
import com.airbnb.lottie.OnCompositionLoadedListener;

import modularty.top.goluck.resource.LogUtil;

public class TestLottieActivity extends AppCompatActivity {

    private SplitImageView animationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_lottie);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        animationView = findViewById(R.id.animationView);
        animationView.setImageAssetDelegate(new ImageAssetDelegate() {
            @Override
            public Bitmap fetchBitmap(LottieImageAsset asset) {
                LogUtil.i("-----------------------asset getDirName"+ asset.getDirName());
                LogUtil.i("-----------------------asset getFileName"+ asset.getFileName());
                LogUtil.i("-----------------------asset getId"+ asset.getId());
                LogUtil.i("-----------------------asset getHeight"+ asset.getHeight());
                LogUtil.i("-----------------------asset getWidth"+ asset.getWidth());
                return null;
            }
        });
//        LottieComposition.Factory.fromAssetFileName(this,"AndroidWave.json", new OnCompositionLoadedListener() {
//            @Override
//            public void onCompositionLoaded(@Nullable LottieComposition composition) {
//                animationView.setComposition(composition);
//            }
//        });

        LottieComposition.Factory.fromAssetFileName(this,"Logo/LogoSmall.json", new OnCompositionLoadedListener() {
            @Override
            public void onCompositionLoaded(@Nullable LottieComposition composition) {
                animationView.setComposition(composition);
            }
        });
        animationView.setRepeatCount(-1);
        animationView.playAnimation();

        animationView.setOnSplitClickListener(new Split.onSplitClickListener() {
            @Override
            public void onSplitClick(View v, int position) {
                Toast.makeText(getApplicationContext(),"你点击了我："+position,Toast.LENGTH_LONG).show();
            }
        });

    }

}

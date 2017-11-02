package ir.alirezanazari.tutorial;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.koushikdutta.async.future.Future;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.ProgressCallback;

import java.io.File;

public class MainActivity extends AppCompatActivity  implements BaseSliderView.OnSliderClickListener{

    SeekBar seekBar ;
    Button btn_dl ;
    String filePath ;
    Future<File> downloading ;

    SliderLayout slider ;
    String [] img = {

            "https://images.kojaro.com/2016/9/68d308f5-3024-42fc-93f5-3839c1e69977.jpg" ,
            "http://uzxalqharakati.com/wp-content/uploads/2017/09/76098122_WindowsLiveWriter_d856c0192ca2_16AC_RRSRRSRSRyoRyo_SRRRSR__RRR_SRRyoRRSS_SRSSRRS_RyoRRyo_RRRRS_45_863113113631462b87961acbbd96d8ce.jpg" ,
            "http://media.alalam.ir/news/image/855x495//2016/05/15/alalam_635989322493890850_25f_4x3.jpg"

    };

    String desc [] = {"طبیعت زیبا" , "غروب دل انگیز" , "پاییز چشم نواز"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //dlVoid();
        sliderConfig();



    }

    private void sliderConfig() {

        //init
        slider = (SliderLayout) findViewById(R.id.slider);

        for (int i = 0 ; i < img.length ; i++){
            CustomTextSliderView sItem = new CustomTextSliderView(this);
            sItem
                    .description(desc[i])
                    .image(img[i])
                    .setOnSliderClickListener(this)
                    .bundle(new Bundle())
                    .getBundle().putString("desc" , desc[i]);

            slider.addSlider(sItem);
        }

        slider.setPresetTransformer(SliderLayout.Transformer.Fade);
        slider.setPresetIndicator(SliderLayout.PresetIndicators.Left_Bottom);
        slider.setCustomAnimation(new DescriptionAnimation());
        slider.setDuration(3000);



    }


    private void dlVoid(){

//        seekBar  = (SeekBar) findViewById(R.id.seekbar);
//        btn_dl   = (Button)  findViewById(R.id.btn_dl);

        filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/1app/";


        btn_dl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (downloading != null && !downloading.isCancelled()) {
                    downloading.cancel();
                    downloading = null;
                    return;
                }

                downloading = Ion.with(getBaseContext())
                        .load("http://static2.varzesh3.com/files/picture/01249175.png")
                        .progressHandler(new ProgressCallback() {

                            @Override
                            public void onProgress(long downloaded, long total) {

                                seekBar.setMax((int) total);
                                seekBar.setProgress((int) downloaded);
                            }
                        })
                        .write(new File(filePath + "111.png"))
                        .setCallback(new FutureCallback<File>() {
                            @Override
                            public void onCompleted(Exception e, File file) {

                                if (e == null){
                                    Toast.makeText(getApplicationContext() , "File Downloaded" , Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(getApplicationContext() , "Failed to Download" , Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
            }
        });
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

        Toast.makeText(getApplicationContext() , slider.getBundle().getString("desc"), Toast.LENGTH_SHORT).show();
    }
}

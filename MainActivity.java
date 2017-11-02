package ir.alirezanazari.tutorial;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;



public class MainActivity extends AppCompatActivity  implements BaseSliderView.OnSliderClickListener{

  
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



    @Override
    public void onSliderClick(BaseSliderView slider) {

        Toast.makeText(getApplicationContext() , slider.getBundle().getString("desc"), Toast.LENGTH_SHORT).show();
    }
}

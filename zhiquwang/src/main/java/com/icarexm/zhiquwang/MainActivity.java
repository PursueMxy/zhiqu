package com.icarexm.zhiquwang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    @BindView(R.id.compadd_img)
    ImageView comImag;
    @BindView(R.id.main_tv_jiaodu)
    TextView tv_jiaodu;

    private float    _decDegree = 0;
    private String mOrientaionText[] = new String[]{"北","东北","东","东南","南","西南","西","西北"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        Sensor mag_sensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        Sensor acc_sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(sensorEventListener,mag_sensor, SensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(sensorEventListener,acc_sensor,SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (sensorManager!=null)
        {
            sensorManager.unregisterListener(sensorEventListener);
        }
    }

    private SensorEventListener sensorEventListener = new SensorEventListener() {
        float[] acceValues = new float[3];
        float[] magnValues = new float[3];
        private float lastRoateDegree;

        @Override
        public void onSensorChanged(SensorEvent event) {
            if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
            {
                acceValues = event.values.clone();
            }
            else if(event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
            {
                magnValues = event.values.clone();
            }
            float[] values = new float[3];
            float[] R = new float[9];
            SensorManager.getRotationMatrix(R, null, acceValues, magnValues);
            SensorManager.getOrientation(R,values);
            float rotateDeg = (float) Math.toDegrees(values[0]);
            if(Math.abs(rotateDeg - lastRoateDegree) > 1)
            {
                RotateAnimation animation = new RotateAnimation(lastRoateDegree,rotateDeg, Animation.RELATIVE_TO_SELF,0.5f,
                        Animation.RELATIVE_TO_SELF,0.5f);
                animation.setFillAfter(true);
                comImag.startAnimation(animation);
                lastRoateDegree = rotateDeg;
                Log.d("ssssssssssssss",lastRoateDegree+"");
            }
            tv_jiaodu.setText(mOrientaionText[((int) (rotateDeg+22.5f)%360)/45]+(rotateDeg));

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };



}

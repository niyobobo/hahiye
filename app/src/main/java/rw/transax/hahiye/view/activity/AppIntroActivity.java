package rw.transax.hahiye.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.doctoror.particlesdrawable.ParticlesDrawable;

import rw.transax.hahiye.R;

public class AppIntroActivity extends AppCompatActivity implements
        ViewPager.OnPageChangeListener, View.OnClickListener {

    private LinearLayout dotsLayout;
    private int[] viewLayouts;
    private ParticlesDrawable particlesDrawable = new ParticlesDrawable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_intro);

        findViewById(R.id.particles).setBackground(particlesDrawable);

        Button login = findViewById(R.id.btn_signIn);
        Button register = findViewById(R.id.btn_signUp);
        login.setOnClickListener(this);
        register.setOnClickListener(this);

        dotsLayout = findViewById(R.id.linear_dots);
        viewLayouts = new int[]{
                R.layout.slider_1,
                R.layout.slider_2,
                R.layout.slider_3
        };

        ViewPager viewPager = findViewById(R.id.viewPager_slider);
        viewPager.setAdapter(new MyViewPagerAdapter());
        viewPager.addOnPageChangeListener(this);
        viewPager.setOffscreenPageLimit(2);

        addLayoutDots(0);
    }

    private void addLayoutDots(int page) {
        TextView[] dots = new TextView[viewLayouts.length];

        int[] activeDot = getResources().getIntArray(R.array.dots_active);
        int[] inactiveDot = getResources().getIntArray(R.array.dots_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(30);
            dots[i].setPadding(5, 5, 5, 5);
            dots[i].setTextColor(inactiveDot[page]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[page].setTextColor(activeDot[page]);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        addLayoutDots(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_signIn:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.btn_signUp:
                startActivity(new Intent(this, SignUpActivity.class));
                break;
            default:
                break;
        }
    }

    private class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater mLayoutInflater;
        private View view;

        MyViewPagerAdapter() {
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            mLayoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (mLayoutInflater != null)
                view = mLayoutInflater.inflate(viewLayouts[position], container, false);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            View view = (View) object;
            container.removeView(view);
        }

        @Override
        public int getCount() {
            return viewLayouts.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        particlesDrawable.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        particlesDrawable.stop();
    }
}

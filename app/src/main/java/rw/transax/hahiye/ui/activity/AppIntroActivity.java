package rw.transax.hahiye.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.doctoror.particlesdrawable.ParticlesDrawable;
import com.doctoror.particlesdrawable.ParticlesView;
import com.google.android.material.button.MaterialButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import rw.transax.hahiye.R;

public class AppIntroActivity extends AppCompatActivity implements
        ViewPager.OnPageChangeListener, View.OnClickListener {

    @BindView(R.id.linear_dots)
    LinearLayout dotsLayout;
    @BindView(R.id.particles)
    ParticlesView particlesView;
    @BindView(R.id.btn_signIn)
    MaterialButton login;
    @BindView(R.id.btn_signUp)
    MaterialButton register;
    @BindView(R.id.viewPager_slider)
    ViewPager viewPager;

    private int[] viewLayouts;
    private ParticlesDrawable particlesDrawable = new ParticlesDrawable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_intro);
        ButterKnife.bind(this);

        particlesView.setBackground(particlesDrawable);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        viewLayouts = new int[]{
                R.layout.slider_1,
                R.layout.slider_2,
                R.layout.slider_3
        };

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

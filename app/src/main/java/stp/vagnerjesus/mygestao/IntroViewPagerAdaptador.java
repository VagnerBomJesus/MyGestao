package stp.vagnerjesus.mygestao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class IntroViewPagerAdaptador extends PagerAdapter {
    Context mContext;
    List<ScreenItem> mListScreen;

    public IntroViewPagerAdaptador(Context mContext, List<ScreenItem> mListScreen){
        this.mContext = mContext;
        this.mListScreen = mListScreen;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container,int position){
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View layoutScreen = inflater.inflate(R.layout.layout_screen, null);

        ImageView imgSlide = layoutScreen.findViewById(R.id.img_intro);
        TextView title = layoutScreen.findViewById(R.id.tv_title);
        TextView title1 = layoutScreen.findViewById(R.id.tv_title1);
        TextView description  = layoutScreen.findViewById(R.id.tv_description);

        title.setText(mListScreen.get(position).getTitle());
        title1.setText(mListScreen.get(position).getTitle1());
        description.setText(mListScreen.get(position).getDescription());
        imgSlide.setImageResource(mListScreen.get(position).getScreenImg());

        container.addView(layoutScreen);
        return layoutScreen;
    }

    @Override
    public int getCount(){
        return mListScreen.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o){
        return view == o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object){
        container.removeView((View) object);
    }
}
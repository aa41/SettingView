package com.example.mydemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sharkhu on 2016/3/28.
 */
public class SettingView extends LinearLayout {
    private ImageView iconImg;
    private TextView textSetting;
    private ImageView arrowIcon;

    private String text;
    private Drawable icon;
    private Drawable arrow;
    private int textColor;
    private int textsize;
    private int settingBackground;
    private boolean singleMode;
    private boolean hasIcon;
    private float iconWidth,iconHeight,arrowWidth,arrowHeight;
    private int padding;
    private float iconMarginText;
    private float settingsMarginTop;
    private float settingsMarginBottom;

    private String[] textArrays;
    private int[] iconArrays;
    private int arrowId;

    public SettingView(Context context) {
        this(context, null);
    }

    public SettingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SettingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SettingView, defStyleAttr, 0);
        text=ta.getString(R.styleable.SettingView_textSetting);
        icon=ta.getDrawable(R.styleable.SettingView_iconSetting);
        arrow=ta.getDrawable(R.styleable.SettingView_arrowSetting);
        textColor=ta.getColor(R.styleable.SettingView_textSettingColor, 0x000);
        textsize= (int) ta.getDimension(R.styleable.SettingView_textSize,14);
        singleMode=ta.getBoolean(R.styleable.SettingView_singleMode, true);
        hasIcon=ta.getBoolean(R.styleable.SettingView_hasIcon,true);
        settingBackground=ta.getColor(R.styleable.SettingView_settingBackgourndColor, 0xfff);
        iconWidth=ta.getDimension(R.styleable.SettingView_iconWidth, 0);
        iconHeight=ta.getDimension(R.styleable.SettingView_iconHeight,0);
        arrowWidth=ta.getDimension(R.styleable.SettingView_arrowWidth,0);
        arrowHeight=ta.getDimension(R.styleable.SettingView_arrowHeight,0);
        iconMarginText=ta.getDimension(R.styleable.SettingView_textMarginIcon,0);
        padding= (int) ta.getDimension(R.styleable.SettingView_settingPadding,0);
        settingsMarginBottom=ta.getDimension(R.styleable.SettingView_settingsMarginBottom,0);
        settingsMarginTop=ta.getDimension(R.styleable.SettingView_settingsMarginTop,0);

        ta.recycle();

        init(context);
    }

    private void init(Context context){
        if(singleMode){
            setOrientation(HORIZONTAL);
            setBackgroundColor(settingBackground);
            setPadding(padding, padding, padding, padding);
            setGravity(Gravity.CENTER_VERTICAL);
            setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            iconImg=new ImageView(context);
            if(!hasIcon){
                iconImg.setVisibility(GONE);
            }else{
                if(iconWidth==0 | iconHeight==0){
                    iconImg.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                }else{
                    iconImg.setLayoutParams(new LayoutParams((int)iconWidth, (int)iconHeight));
                }
                if(icon==null){
                    iconImg.setVisibility(INVISIBLE);
                }else{
                    iconImg.setImageDrawable(icon);
                }
            }
            addView(iconImg);


            textSetting=new TextView(context);
           if(iconMarginText!=0){
               LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
               lp.setMargins((int) iconMarginText,0,0,0);
               textSetting.setLayoutParams(lp);
           }else{
               textSetting.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
           }
            textSetting.setText(text);
            textSetting.setTextSize(textsize);
            textSetting.setTextColor(textColor);

            addView(textSetting);

            arrowIcon=new ImageView(context);
            LinearLayout.LayoutParams lp;
            if(arrowHeight==0 | arrowWidth==0){
               lp=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            }else{
                lp=new LayoutParams((int)arrowWidth, (int)arrowHeight);
            }
            if(arrow==null){
                arrowIcon.setBackgroundResource(R.mipmap.arrow);
            }else{
                arrowIcon.setImageDrawable(arrow);
            }
            addView(arrowIcon,lp);

        }

    }

    protected void setSettingList(Context context,String[] textArrays,int[] iconArrays,int arrowId){
        this.textArrays=textArrays;
        this.iconArrays=iconArrays;
        this.arrowId=arrowId;
        if(singleMode){
            return;
        }
        if(textArrays.length!=iconArrays.length){
            return;
        }
        if(textArrays.length==0 &&  iconArrays.length==0){
            return;
        }
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER_VERTICAL);
        for(int i=0;i<textArrays.length;i++){
            final LinearLayout childSettingLayout=new LinearLayout(context);
            childSettingLayout.setOrientation(HORIZONTAL);
            childSettingLayout.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            LayoutParams layoutLp = new LayoutParams(childSettingLayout.getLayoutParams());
            if(settingsMarginBottom!=0 | settingsMarginTop!=0){
                layoutLp.setMargins(0, (int) settingsMarginTop,0, (int) settingsMarginBottom);
            }

            childSettingLayout.setBackgroundColor(settingBackground);
            TextView settingText=new TextView(context);
            ImageView iconImg2=new ImageView(context);
            ImageView arrowImg2=new ImageView(context);
            if(!hasIcon){
                iconImg2.setVisibility(GONE);
            }else{
                if(iconWidth==0 | iconHeight==0){
                    iconImg2.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                }else{
                    iconImg2.setLayoutParams(new LayoutParams((int)iconWidth, (int)iconHeight));
                }
                iconImg2.setBackgroundResource(iconArrays[i]);
            }
            childSettingLayout.addView(iconImg2);


            if(iconMarginText!=0){
                LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
                lp.setMargins((int) iconMarginText,0,0,0);
                settingText.setLayoutParams(lp);
            }else{
                settingText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
            }
            settingText.setText(textArrays[i]);
            settingText.setTextSize(textsize);
            settingText.setTextColor(textColor);
            childSettingLayout.addView(settingText);


            LinearLayout.LayoutParams lp;
            if(arrowHeight==0 | arrowWidth==0){
                lp=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            }else{
                lp=new LayoutParams((int)arrowWidth, (int)arrowHeight);
            }
            arrowImg2.setBackgroundResource(arrowId);
            childSettingLayout.addView(arrowImg2, lp);
            childSettingLayout.setTag(i);
            childSettingLayout.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListner != null) {
                        onItemClickListner.clickListner(v, (Integer) v.getTag());
                    }

                }
            });
            addView(childSettingLayout, layoutLp);
            childSettingLayout.setPadding(padding,padding,padding,padding);
        }





    }
    public interface OnItemClickListner{
        void clickListner(View v,int position);
    }
    public  OnItemClickListner onItemClickListner;
    public  void setOnItemClickListner (OnItemClickListner onItemClickListner){
        this.onItemClickListner=onItemClickListner;
    }
}

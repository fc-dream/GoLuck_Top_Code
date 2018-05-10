package cn.tuhu.home.util;

import android.app.Activity;
import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.tuhu.home.R;

/**
 * 作者：luck on 2017/3/3 10:51
 * 邮箱：fc_dream@163.com
 * Android
 */
public class ChoicenessViewUtil extends BaseIncludeViewUtil {

    private ImageView choiceness_img1;
    private TextView choiceness_text1,choiceness_text2;
    private TextView choiceness_text3;


    public ChoicenessViewUtil(Activity activity, View view) {
        super(activity, view);
        choiceness_img1 = getView(R.id.choiceness_img1);
        choiceness_text1 = getView(R.id.choiceness_text1);
        choiceness_text2 = getView(R.id.choiceness_text2);
        choiceness_text2.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        choiceness_text3 = getView(R.id.choiceness_text3);
    }

    public void bindDataandListener() {

    }
}

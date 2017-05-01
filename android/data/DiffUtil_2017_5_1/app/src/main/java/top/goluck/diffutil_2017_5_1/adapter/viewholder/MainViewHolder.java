package top.goluck.diffutil_2017_5_1.adapter.viewholder;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import top.goluck.diffutil_2017_5_1.R;
import top.goluck.diffutil_2017_5_1.model.Item;

/**
 * 作者：luck on 2017/5/1 13:23
 * 邮箱：fc_dream@163.com
 * DiffUtil_2017_5_1
 */
public class MainViewHolder extends RecyclerView.ViewHolder {

    private TextView main_title, main_content, main_footer;

    public MainViewHolder(View itemView) {
        super(itemView);
        main_title = (TextView) itemView.findViewById(R.id.main_title);
        main_content = (TextView) itemView.findViewById(R.id.main_content);
        main_footer = (TextView) itemView.findViewById(R.id.main_footer);
    }

    public void bindDataandListener(Item item, List payloads) {
        // 关键辅助核心代码
        if (payloads != null && payloads.size() > 0) {
            Bundle o = (Bundle) payloads.get(0);
            for (String key : o.keySet()) {
                switch (key) {
                    case Item.KEY_TITLE:
                        item.setTitle(o.getString(Item.KEY_TITLE));
                        break;
                    case Item.KEY_CONTENT:
                        item.setContent(o.getString(Item.KEY_CONTENT));
                        break;
                    case Item.KEY_FOOTER:
                        item.setFooter(o.getString(Item.KEY_FOOTER));
                        break;
                }
            }
        }
        main_title.setText(item.getTitle());
        main_content.setText(item.getContent());
        main_footer.setText(item.getFooter());
    }

}

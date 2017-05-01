package top.goluck.diffutil_2017_5_1.adapter.callback;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import java.util.List;

import top.goluck.diffutil_2017_5_1.model.Item;

/**
 * 作者：luck on 2017/5/1 13:06
 * 邮箱：fc_dream@163.com
 */
public class MainDiffCallback extends DiffUtil.Callback {

    private List<Item> oldList;
    private List<Item> newList;

    public MainDiffCallback(List<Item> oldList, List<Item> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getId() == newList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        Item oldItem = oldList.get(oldItemPosition);
        Item newItem = newList.get(newItemPosition);
        Bundle diffBundle = new Bundle();
        if (!newItem.getTitle().equals(oldItem.getTitle())) {
            diffBundle.putString(Item.KEY_TITLE, newItem.getTitle());
        }
        if (!newItem.getContent().equals(oldItem.getContent())) {
            diffBundle.putString(Item.KEY_CONTENT, newItem.getContent());
        }
        if (!newItem.getFooter().equals(oldItem.getFooter())) {
            diffBundle.putString(Item.KEY_FOOTER, newItem.getFooter());
        }
        return diffBundle;
    }
}
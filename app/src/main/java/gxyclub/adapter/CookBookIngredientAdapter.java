package gxyclub.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.gxyclub.R;
import gxyclub.bean.CookBookIngredientBean;

/**
 * 菜谱 详情
 */

public class CookBookIngredientAdapter extends BaseQuickAdapter<CookBookIngredientBean, BaseViewHolder> {

    private List<CookBookIngredientBean> list;

    //注意super里面需要把list添加进去
    public CookBookIngredientAdapter(List<CookBookIngredientBean> list) {
        super(R.layout.item_cookbook_ingredient,list);
        this.list = list;
    }

    @Override
    protected void convert(BaseViewHolder helper, CookBookIngredientBean item) {
        helper.setText(R.id.tv_name,item.getTitle());


    }


}

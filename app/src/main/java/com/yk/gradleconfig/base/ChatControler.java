package com.yk.gradleconfig.base;

import android.content.Context;

/**
 * author: kun .
 * date:   On 2019/3/26
 */
public interface ChatControler {

    interface View extends BaseView{

        void getMessage(String msg);
    }

    abstract class Presenter extends BasePresenter<ChatControler.View> {

        public Presenter(Context context, ChatControler.View view) {
            super(context, view);
        }
    }

}

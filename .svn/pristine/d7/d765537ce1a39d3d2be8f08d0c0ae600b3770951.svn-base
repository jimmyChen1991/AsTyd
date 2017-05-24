package com.hhyg.TyClosing.presenter;

import android.os.Message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.hhyg.TyClosing.allShop.handler.SimpleHandler;
import com.hhyg.TyClosing.config.Constants;
import com.hhyg.TyClosing.global.MyApplication;
import com.hhyg.TyClosing.global.NetCallBackHandlerException;
import com.hhyg.TyClosing.global.ProcMsgHelper;
import com.hhyg.TyClosing.info.PrivilegeInfo;
import com.hhyg.TyClosing.mgr.ClosingRefInfoMgr;
import com.hhyg.TyClosing.util.ToastUtil;
import com.hhyg.TyClosing.view.BindPrivilegeView;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by user on 2017/5/23.
 */

public class BindPrivilegeCodePresenter extends BasePresenter<BindPrivilegeView>{

    private final String BIND_URL = Constants.getIndexUrl() + "?r=privilege/bound";
    private final String MYPRIVILEGE_URL = Constants.getIndexUrl() + "?r=privilege/own";
    private final String ISPRIVILEGE_URL = Constants.getIndexUrl() + "?r=privilege/isprivilege";
    private final String CANCELBING_URL = Constants.getIndexUrl() + "?r=privilege/unbound";

    public void BindPrivilege(String code){
        if(mView != null){
            mView.startProgress();
        }
        mHttpRequester.post(BIND_URL,makeBindParam(code),new NetCallBackHandlerException(new BindExceptionHandler(),new BindProc()));
    }

    public void fetchPrivilegeInfo(){
        mHttpRequester.post(MYPRIVILEGE_URL,makeMyPrivilegeParam(),new NetCallBackHandlerException(new MyPrivilegeExceptionHandler(),new MyPrivilegeProc()));
    }

    public void isPrivilegeUser(){
        mHttpRequester.post(ISPRIVILEGE_URL,makeIsPrivilegeparam(),new NetCallBackHandlerException(new IsPrivilegeUserExceptionHanlder(),new IsPrivilegeProc()));
    }

    public void cancelBindStatus(){
        mHttpRequester.post(CANCELBING_URL,makeCancelBindStatus(),new NetCallBackHandlerException(new BindExceptionHandler(),new CancelBindStatusProc()));
    }

    private String makeBindParam(String code){
        JSONObject param = new JSONObject();
        param.put("op", "privilege_bound");
        param.put("imei", MyApplication.GetInstance().getAndroidId());
        param.put("shopid", ClosingRefInfoMgr.getInstance().getShopId());
        param.put("channel", ClosingRefInfoMgr.getInstance().getChannelId());
        param.put("codes",code);
        param.put("platformId",3);
        return param.toString();

    }

    private String makeMyPrivilegeParam(){
        JSONObject param = new JSONObject();
        param.put("op", "privilege_own");
        param.put("imei", MyApplication.GetInstance().getAndroidId());
        param.put("shopid", ClosingRefInfoMgr.getInstance().getShopId());
        param.put("channel", ClosingRefInfoMgr.getInstance().getChannelId());
        param.put("platformId",3);
        return param.toString();
    }

    private String makeIsPrivilegeparam(){
        JSONObject param = new JSONObject();
        param.put("op", "privilege_isprivilege");
        param.put("imei", MyApplication.GetInstance().getAndroidId());
        param.put("shopid", ClosingRefInfoMgr.getInstance().getShopId());
        param.put("channel", ClosingRefInfoMgr.getInstance().getChannelId());
        param.put("platformId",3);
        return param.toString();
    }

    private String makeCancelBindStatus(){
        JSONObject param = new JSONObject();
        param.put("op", "privilege_unbound");
        param.put("imei", MyApplication.GetInstance().getAndroidId());
        param.put("shopid", ClosingRefInfoMgr.getInstance().getShopId());
        param.put("channel", ClosingRefInfoMgr.getInstance().getChannelId());
        param.put("platformId",3);
        return param.toString();
    }

    class BindProc implements ProcMsgHelper{
        @Override
        public void ProcMsg(String msgBody) throws JSONException, IOException {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    fetchPrivilegeInfo();
                }
            });
        }
    }

    class BindExceptionHandler extends SimpleHandler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(mView != null){
                mView.disProgress();
            }
            if(msg.what == 4){
                ToastUtil.getShortToastByString(MyApplication.GetInstance(), (String) msg.obj);
            }
        }
    }

    class MyPrivilegeProc implements ProcMsgHelper{
        @Override
        public void ProcMsg(String msgBody) throws JSONException, IOException {
            JSONObject Res_JOBJ = JSON.parseObject(msgBody);
            JSONArray dataArr = Res_JOBJ.getJSONArray("data");
            PrivilegeInfo info = null;
            if(dataArr != null && dataArr.size() > 0){
                JSONObject privilegeJobj = dataArr.getJSONObject(0);
                JSONArray code_Jobj = privilegeJobj.getJSONArray("codes");
                if(code_Jobj != null && code_Jobj.size() > 0){
                    JSONObject code_item = code_Jobj.getJSONObject(0);
                    info = new PrivilegeInfo();
                    info.setDetailDesc(code_item.getString("description"));
                    info.setDiscountDesc(code_item.getString("discountDesc"));
                    info.setStartTime(code_item.getString("startTime"));
                    info.setEndTime(code_item.getString("endTime"));
                    info.setTitle(code_item.getString("codeName"));
                    JSONArray activitys_Jobj = code_item.getJSONArray("activityCodes");
                    if(activitys_Jobj != null && activitys_Jobj.size() >0){
                        ArrayList<String> activitys = new ArrayList<>();
                        for(int idx = 0 ;idx < activitys_Jobj.size() ; idx ++){
                            activitys.add(activitys_Jobj.getString(idx));
                        }
                        info.setActivityCodes(activitys);
                    }
                }
            }
            final PrivilegeInfo pInfo  = info;
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    if(mView == null){
                        return;
                    }
                    if(pInfo != null){
                        mView.showMyPrivilegeInfo(pInfo);
                    }else{
                        mView.ShowExceptionInfo();
                    }
                    mView.disProgress();
                }
            });
        }
    }

    class MyPrivilegeExceptionHandler extends SimpleHandler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(mView != null){
                mView.disProgress();
            }
        }
    }

    class IsPrivilegeProc implements ProcMsgHelper{
        @Override
        public void ProcMsg(String msgBody) throws JSONException, IOException {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    fetchPrivilegeInfo();
                }
            });
        }
    }

    class IsPrivilegeUserExceptionHanlder extends SimpleHandler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 4){
                if(mView != null){
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mView.isNotPrivilegeUser();
                        }
                    });
                }
            }
        }
    }

    class CancelBindStatusProc implements ProcMsgHelper{
        @Override
        public void ProcMsg(String msgBody) throws JSONException, IOException {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    if(mView != null){
                        mView.cancelBindStatus();
                    }
                }
            });
        }
    }


}

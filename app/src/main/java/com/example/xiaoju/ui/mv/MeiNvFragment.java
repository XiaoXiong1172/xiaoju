package com.example.xiaoju.ui.mv;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xiaoju.R;
import com.example.xiaoju.model.DataBean;
import com.example.xiaoju.ui.sh.SheHuiFragment;
import com.example.xiaoju.ui.sh.SheHuiViewModel;
import com.example.xiaoju.utils.DBUtil;

public class MeiNvFragment extends Fragment {
    public String title;
    private MeiNvViewModel mViewModel;

    private BaseAdapter adapter;
    private SwipeRefreshLayout refreshLayout;

    public static MeiNvFragment newInstance(String title) {
        return new MeiNvFragment(title);
    }
    public MeiNvFragment(String title){
        this.title = title;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mei_nv_fragment, container, false);
        final RecyclerView recyclerView = view.findViewById(R.id.recycleview);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        refreshLayout = view.findViewById(R.id.refresh);
        adapter = new BaseAdapter(getContext());
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.d("tag", "onHiddenChanged: shehui "+hidden);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MeiNvViewModel.class);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        DBUtil.getAppDatabase().dataBeanDao().deleteAll(title);
                    }
                }).start();
            }
        });
        mViewModel.getDataBean(this.title).observe(this.getViewLifecycleOwner(), new Observer<PagedList<DataBean>>() {
            @Override
            public void onChanged(PagedList<DataBean> dataBeans) {
                adapter.submitList(dataBeans);
                refreshLayout.setRefreshing(false);
            }
        });
    }


}

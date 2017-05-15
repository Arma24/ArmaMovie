package id.sch.smktelkom_mlg.privateassignment.xirpl105.armamovie.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl105.armamovie.R;
import id.sch.smktelkom_mlg.privateassignment.xirpl105.armamovie.adapter.PAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl105.armamovie.model.PResponse;
import id.sch.smktelkom_mlg.privateassignment.xirpl105.armamovie.model.Popular;
import id.sch.smktelkom_mlg.privateassignment.xirpl105.armamovie.service.GsonGetRequest;
import id.sch.smktelkom_mlg.privateassignment.xirpl105.armamovie.service.VolleySingleton;

/**
 * A simple {@link Fragment} subclass.
 */
public class PFragment extends Fragment {

    PAdapter mAdapter;
    ArrayList<Popular> mList = new ArrayList<>();


    public PFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rv, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mAdapter = new PAdapter(this.getActivity(), mList);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        downloadDataSources();
    }

    private void downloadDataSources() {
        String url = "https://api.themoviedb.org/3/movie/popular?api_key=ed7e8da2746875e6501655d8f016b19f&language=en-US&page=1";

        GsonGetRequest<PResponse> myRequest = new GsonGetRequest<PResponse>
                (url, PResponse.class, null, new Response.Listener<PResponse>() {

                    @Override
                    public void onResponse(PResponse response) {
                        Log.d("FLOW", "onResponse: " + (new Gson().toJson(response)));
                        mList.addAll(response.results);
                        mAdapter.notifyDataSetChanged();
                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("FLOW", "onErrorResponse: ", error);
                    }
                });
        VolleySingleton.getInstance(this.getActivity()).addToRequestQueue(myRequest);
    }

}
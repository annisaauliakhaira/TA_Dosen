package com.example.ta;

import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ta.API.BaseApiService;
import com.example.ta.API.SharedPrefManager;
import com.example.ta.API.UtilsApi;
import com.example.ta.Adapter.ExamAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExamscheduleFragment extends Fragment  {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
    private BaseApiService baseApiService;
    private SharedPrefManager sharedPrefManager;
    private JSONArray examschedule;
    public ExamAdapter examAdapter;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view =  inflater.inflate(R.layout.fragment_examschedule, container, false);
        return view;

    }

    public void onViewCreated(View view, Bundle savedInstanceState){
        sharedPrefManager = new SharedPrefManager(getContext());
        super.onViewCreated(view, savedInstanceState);
        if (!sharedPrefManager.getToken().equals("")){
            String accessToken = sharedPrefManager.getToken();
            baseApiService = UtilsApi.getAPIService();

            recyclerView = view.findViewById(R.id.rv_exam);
            refresh(accessToken);
        }
    }

    public void refresh(String accessToken){
        baseApiService.getClass(accessToken)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                examschedule = jsonRESULTS.getJSONArray("classes");
                                recyclerView.setHasFixedSize(true);
                                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                                Log.e("tst", examschedule.toString());
                                recyclerView.setAdapter(new ExamAdapter(examschedule, new ExamAdapter.OnItemClickListener() {

                                    @Override
                                    public void onItemClick(JSONObject item) throws JSONException {
                                        Toast.makeText(getContext(),"ok",Toast.LENGTH_SHORT).show();
                                    }
                                }));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Terjadi Error di aplikasi", Toast.LENGTH_SHORT).show();
                            Log.d("onResponse", "onResponse: " + response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
    }

}

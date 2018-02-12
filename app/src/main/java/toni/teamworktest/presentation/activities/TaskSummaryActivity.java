package toni.teamworktest.presentation.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import toni.teamworktest.R;
import toni.teamworktest.TeamworkApplication;
import toni.teamworktest.presentation.model.Project;
import toni.teamworktest.presentation.model.TaskSummary;
import toni.teamworktest.presentation.presenter.TaskSummaryPresenter;
import toni.teamworktest.presentation.views.TaskSummaryView;

public class TaskSummaryActivity extends BaseActivity implements TaskSummaryView {

    private static final String PROJECT = "PROJECT";
    private Project mProject;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_my_tasks)
    TextView mMyTasksTextView;
    @BindView(R.id.tv_all_tasks)
    TextView mAllTasksTextView;
    @BindView(R.id.chart_my_tasks)
    PieChart mMyTasksPieChart;
    @BindView(R.id.chart_all_tasks)
    PieChart mAllTasksPieChart;

    @Inject
    TaskSummaryPresenter mTaskSummaryPresenter;

    public static Intent createIntent(Context context, Project project){
        Intent intent = new Intent(context, TaskSummaryActivity.class);
        intent.putExtra(PROJECT, project);
        return intent;
    }

    // ---------------------------------------------------------------------------------------------
    // OVERRIDE METHODS
    // ---------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_summary);
        TeamworkApplication.getAppComponent().inject(this);
        ButterKnife.bind(this);
        getExtras();
        setupActionBar();
        mTaskSummaryPresenter.setView(this);
        getTaskSummary();
    }

    @Override
    public void showTaskSummary(TaskSummary mine, TaskSummary all) {
        mMyTasksTextView.setText(getString(R.string.mytasks, mine.getActive()));
        mAllTasksTextView.setText(getString(R.string.alltasks, all.getActive()));

        setupPieChart(mMyTasksPieChart, mine);
        setupPieChart(mAllTasksPieChart, all);
    }

    // ---------------------------------------------------------------------------------------------
    // PRIVATE METHODS
    // ---------------------------------------------------------------------------------------------

    private void setupActionBar() {
        mToolbar.setTitle(mProject.getName());
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getExtras() {
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            mProject = extras.getParcelable(PROJECT);
        }
    }

    private void getTaskSummary() {
        mTaskSummaryPresenter.getTaskSummary(mProject.getId());
    }

    private void setupPieChart(PieChart pieChart, TaskSummary taskSummary) {

        int[] legendColors = getResources().getIntArray(R.array.legendColors);
        ArrayList<Integer> colors = new ArrayList<>();
        for(int c: legendColors) colors.add(c);

        List<PieEntry> tasksEntries = new ArrayList<>();
        tasksEntries.add(new PieEntry(taskSummary.getLate().floatValue(), getString(R.string.late, taskSummary.getLate())));
        tasksEntries.add(new PieEntry(taskSummary.getStarted().floatValue(), getString(R.string.started, taskSummary.getStarted())));
        tasksEntries.add(new PieEntry(taskSummary.getToday().floatValue(), getString(R.string.today, taskSummary.getToday())));
        tasksEntries.add(new PieEntry(taskSummary.getUpcoming().floatValue(), getString(R.string.upcoming, taskSummary.getUpcoming())));
        tasksEntries.add(new PieEntry(taskSummary.getNoDate().floatValue(), getString(R.string.nodate, taskSummary.getNoDate())));

        PieDataSet tasksSet = new PieDataSet(tasksEntries, "");
        PieData tasksData = new PieData(tasksSet);
        tasksData.setDrawValues(false);
        tasksData.setHighlightEnabled(false);
        tasksSet.setColors(colors);

        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setFormSize(10);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setTextSize(14.0f);
        legend.setTextColor(Color.GRAY);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setYOffset(40);

        Description description = new Description();
        description.setText("");
        pieChart.setDescription(description);
        pieChart.setDrawEntryLabels(false);
        pieChart.setExtraLeftOffset(50);
        pieChart.setData(tasksData);
        pieChart.spin( 500,0,-360f, Easing.EasingOption.EaseInOutQuad);
        pieChart.invalidate();
    }
}

package toni.teamworktest.presentation.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cunoraz.tagview.TagView;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import toni.teamworktest.R;
import toni.teamworktest.presentation.model.Project;
import toni.teamworktest.presentation.model.Tag;

public class ProjectsAdapter extends RecyclerView.Adapter<ProjectsAdapter.ProjectViewHolder> {

    public interface OnProjectClickListener {
        void onProjectClicked(Project project);
    }

    private List<Project> mProjectsList;
    private final LayoutInflater mLayoutInflater;
    private OnProjectClickListener mOnProjectClickListener;

    // ---------------------------------------------------------------------------------------------
    // PUBLIC METHODS
    // ---------------------------------------------------------------------------------------------

    public ProjectsAdapter(Context context) {
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mProjectsList = Collections.emptyList();
    }

    public void setProjectsList(Collection<Project> projectsList) {
        mProjectsList = (List<Project>) projectsList;
        notifyDataSetChanged();
    }

    public void setOnProjectClickListener(OnProjectClickListener onProjectClickListener) {
        mOnProjectClickListener = onProjectClickListener;
    }


    // ---------------------------------------------------------------------------------------------
    // OVERRIDE METHODS
    // ---------------------------------------------------------------------------------------------

    @Override
    public int getItemCount() {
        return (mProjectsList != null) ? mProjectsList.size() : 0;
    }

    @Override
    public ProjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = this.mLayoutInflater.inflate(R.layout.item_project, parent, false);
        return new ProjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProjectViewHolder holder, final int position) {
        final Project project = this.mProjectsList.get(position);

        holder.projectNameTextView.setText(project.getName());
        holder.companyNameTextView.setText(project.getCompany().getName());
        holder.startEndDatesTextView.setText(project.getStartDate() + " - " + project.getEndDate());
        holder.descriptionTextView.setText(project.getDescription());
        setupTags(holder.tagViewGroup, project.getTagList());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnProjectClickListener != null) {
                    mOnProjectClickListener.onProjectClicked(project);
                }
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // ---------------------------------------------------------------------------------------------
    // PRIVATE METHODS
    // ---------------------------------------------------------------------------------------------

    private void setupTags(TagView tagGroup, List<Tag> tagList) {

        for (Tag tag : tagList) {

            com.cunoraz.tagview.Tag tagView = new com.cunoraz.tagview.Tag(tag.getName());
            tagView.layoutColor = Color.parseColor(tag.getColor());
            tagView.tagTextColor = Color.WHITE;
            tagGroup.addTag(tagView);
        }
    }

    // ---------------------------------------------------------------------------------------------
    // VIEW HOLDER
    // ---------------------------------------------------------------------------------------------

    static class ProjectViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cv_project)
        CardView projectCardView;
        @BindView(R.id.tv_project_name)
        TextView projectNameTextView;
        @BindView(R.id.tv_company_name)
        TextView companyNameTextView;
        @BindView(R.id.tag_group)
        TagView tagViewGroup;
        @BindView(R.id.tv_start_end_dates)
        TextView startEndDatesTextView;
        @BindView(R.id.tv_description)
        TextView descriptionTextView;

        ProjectViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

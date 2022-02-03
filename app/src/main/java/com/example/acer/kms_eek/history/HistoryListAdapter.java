package com.example.acer.kms_eek.history;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.acer.kms_eek.R;

import java.util.ArrayList;


/**
 * A custom adapter to use with the RecyclerView widget.
 */
public class HistoryListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private String mHeaderTitle;

    private OnHeaderClickListener mHeaderClickListener;

    private Context mContext;
    private ArrayList<EntitasHistory> modelList;

    private OnItemClickListener mItemClickListener;


    public HistoryListAdapter(Context context, ArrayList<EntitasHistory> modelList, String headerTitle) {
        this.mContext = context;
        this.modelList = modelList;
        this.mHeaderTitle = headerTitle;
    }

    public void updateList(ArrayList<EntitasHistory> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_header, parent, false);
            return new HeaderViewHolder(v);
        } else if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history_list, parent, false);
            return new ViewHolder(v);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof HeaderViewHolder) {
            HeaderViewHolder headerHolder = (HeaderViewHolder) holder;

            headerHolder.txtTitleHeader.setText(mHeaderTitle);

        } else if (holder instanceof ViewHolder) {
            final EntitasHistory model = getItem(position - 1);
            ViewHolder genericViewHolder = (ViewHolder) holder;
            genericViewHolder.txtJudulHistory.setText(model.getJudul_history());
            genericViewHolder.txtKonten.setText(model.getKonten());

            Glide.with(mContext)
                    .load(model.getGambar_path())
                    .placeholder(R.drawable.ic_image_placeholder)
                    .into(genericViewHolder.imgUser);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position)) {
            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }


    @Override
    public int getItemCount() {

        return modelList.size() + 1;
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public void SetOnHeaderClickListener(final OnHeaderClickListener headerClickListener) {
        this.mHeaderClickListener = headerClickListener;
    }

    private EntitasHistory getItem(int position) {
        return modelList.get(position);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, EntitasHistory model);
    }

    public interface OnHeaderClickListener {
        void onHeaderClick(View view, String headerTitle);
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitleHeader;

        public HeaderViewHolder(final View itemView) {
            super(itemView);
            this.txtTitleHeader = (TextView) itemView.findViewById(R.id.txt_header);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    mHeaderClickListener.onHeaderClick(itemView, mHeaderTitle);
                }
            });

        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgUser;
        private TextView txtJudulHistory;
        private TextView txtKonten;

        public ViewHolder(final View itemView) {
            super(itemView);

            this.imgUser = (ImageView) itemView.findViewById(R.id.img_thumbnail);
            this.txtJudulHistory = (TextView) itemView.findViewById(R.id.txt_judul_history);
            this.txtKonten = (TextView) itemView.findViewById(R.id.txt_konten);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition() - 1));
                }
            });

        }
    }

}


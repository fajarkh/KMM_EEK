package com.example.acer.kms_eek.kategori;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import android.support.v7.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.example.acer.kms_eek.R;

import static com.example.acer.kms_eek.R.id.imageView;
import static com.example.acer.kms_eek.R.id.img_user;


/**
 * A custom adapter to use with the RecyclerView widget.
 */
public class KategoriListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private String mHeaderTitle;

    private OnHeaderClickListener mHeaderClickListener;

    private Context mContext;
    private ArrayList<EntitasKategori> modelList;

    private OnItemClickListener mItemClickListener;


    public KategoriListAdapter(Context context, ArrayList<EntitasKategori> modelList, String headerTitle) {
        this.mContext = context;
        this.modelList = modelList;
        this.mHeaderTitle = headerTitle;
    }

    public void updateList(ArrayList<EntitasKategori> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_header, parent, false);
            return new HeaderViewHolder(v);
        } else if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kstegori_list, parent, false);
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
            final EntitasKategori model = getItem(position - 1);
            ViewHolder genericViewHolder = (ViewHolder) holder;
            genericViewHolder.txtJudulBerita.setText(model.getJudul_berita());
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

    private EntitasKategori getItem(int position) {
        return modelList.get(position);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, EntitasKategori model);
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
        private TextView txtJudulBerita;
        private TextView txtKonten;

        public ViewHolder(final View itemView) {
            super(itemView);

            this.imgUser = (ImageView) itemView.findViewById(R.id.img_user);
            this.txtJudulBerita = (TextView) itemView.findViewById(R.id.txt_judul_berita);
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

